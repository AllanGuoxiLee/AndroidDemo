package com.liguoxi.androiddemo.UI.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liguoxi.androiddemo.R;
import com.liguoxi.androiddemo.entity.LocationInfoEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationInfoFragment extends Fragment {

    @Bind(R.id.province_tv)
    TextView provinceTv;
    @Bind(R.id.city_tv)
    TextView cityTv;
    @Bind(R.id.district_tv)
    TextView districtTv;
    @Bind(R.id.address_tv)
    TextView addressTv;
    @Bind(R.id.title_tv)
    TextView titleTv;

    private View rootView;

    private LocationInfoEntity mLocationInfoEntity;

    public LocationInfoFragment() {
        // Required empty public constructor
    }

    private static LocationInfoFragment instance;

    public static LocationInfoFragment getInstance(LocationInfoEntity locationInfoEntity) {
        instance = new LocationInfoFragment();
        instance.mLocationInfoEntity = locationInfoEntity;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_location_info, container, false);
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {
        updateUI();
    }

    private void updateUI() {
        provinceTv.setText(mLocationInfoEntity.getProvince());
        cityTv.setText(mLocationInfoEntity.getCity());
        districtTv.setText(mLocationInfoEntity.getDistrict());
        addressTv.setText(mLocationInfoEntity.getAddress());
        titleTv.setText(mLocationInfoEntity.getTitle());
    }

    public LocationInfoEntity getLocationInfoEntity() {
        return mLocationInfoEntity;
    }

    public void setLocationInfoEntity(LocationInfoEntity locationInfoEntity) {
        this.mLocationInfoEntity = locationInfoEntity;
        updateUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
