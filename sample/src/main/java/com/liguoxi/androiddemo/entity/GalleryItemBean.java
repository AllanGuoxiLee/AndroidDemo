package com.liguoxi.androiddemo.entity;

/**
 * Created by Li Guoxi on 2016/8/5.
 */
public class GalleryItemBean {
    private String path;
    private boolean isSelected;
    private boolean isAdd;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
