package com.liguoxi.androiddemo.UI.acitivities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.liguoxi.androiddemo.R;
import com.liguoxi.androiddemo.UI.fragments.LocationInfoFragment;
import com.liguoxi.androiddemo.entity.LocationInfoEntity;
import com.liguoxi.androiddemo.main.RollProgressDialog;
import com.liguoxi.androiddemo.util.SnackBarUtil;
import com.liguoxi.behaviors.BottomSheetActivity;
import com.liguoxi.behaviors.behavior.BottomSheetBehaviorGoogleMapsLike;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapBottomSheetActivity extends BottomSheetActivity implements View.OnClickListener {

    @Bind(R.id.map_view)
    TextureMapView mapView;
    @Bind(R.id.center_pointer)
    View centerPointer;
    @Bind(R.id.located_icon)
    ImageView locatedIcon;

    private BaiduMap mBaiduMap;

    private LocationClient mLocationClient;

    private LocationInfoFragment mLocationInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_bottom_sheet);
        ButterKnife.bind(this);
        initView();
        initMap();
    }

    private void initView() {
        locatedIcon.setOnClickListener(this);
        mapView.setOnClickListener(this);
    }

    private void initMap() {
        mBaiduMap = mapView.getMap();


        setCurrentLocation();

    }

    private void setCurrentLocation() {
        mLocationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (bdLocation == null) {
                    SnackBarUtil.getInstance(mapView).showShort("定位失败，请在地图上选取坐标点").show();
                    return;
                }
                double x = bdLocation.getLongitude();
                double y = bdLocation.getLatitude();
                Log.d("定位信息", "经度：" + x + "\t" + "纬度：" + y + "---->" + bdLocation.getCity());
                GeoCoder coder = GeoCoder.newInstance();
                coder.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude())));


                LatLng myLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatus mapStatus = new MapStatus.Builder().target(myLatLng).zoom(18).build();
                MapStatusUpdate update = MapStatusUpdateFactory.newMapStatus(mapStatus);

                mBaiduMap.animateMapStatus(update);
//                mBaiduMap.getUiSettings().setAllGesturesEnabled(false);

                mLocationClient.stop();
            }
        });
        mLocationClient.start();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.located_icon:
                startGetInformation();
                break;

            case R.id.map_view:
                getBottomSheetBehavior().setState(BottomSheetBehaviorGoogleMapsLike.STATE_HIDDEN);
                break;
        }
    }

    private void startGetInformation() {
        GeoCoder geoCoder = GeoCoder.newInstance();
        LatLng latLng = mBaiduMap.getMapStatus().target;
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        showProgressDialog("搜索中");
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                dismissProgressDialog();
                ReverseGeoCodeResult result = reverseGeoCodeResult;
                LocationInfoEntity entity = new LocationInfoEntity(result.getAddress(),result.getAddressDetail().province, result.getAddressDetail().city, result.getAddressDetail().district, result.getAddressDetail().street + result.getAddressDetail().streetNumber);
                setBottomTitle(result.getAddress());
                if (mLocationInfoFragment==null){
                    mLocationInfoFragment = LocationInfoFragment.getInstance(entity);
                    setBottomFragment(mLocationInfoFragment);
                }else {
                    mLocationInfoFragment.setLocationInfoEntity(entity);
                }

                getBottomSheetBehavior().setState(BottomSheetBehaviorGoogleMapsLike.STATE_COLLAPSED);
            }
        });
    }

    private AlertDialog progressDialog;

    protected void showProgressDialog(String message) {
        dismissProgressDialog();
        progressDialog = RollProgressDialog.showNetDialog(this, true, message);
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

}
