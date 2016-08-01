package com.liguoxi.androiddemo.entity;

/**
 * Created by Li Guoxi on 2016/8/1.
 */
public class IndexEntity {
    private String title;
    private String subTitle;
    private Class<?> cls;

    public IndexEntity() {
    }

    public IndexEntity(String title, Class<?> cls) {
        this.title = title;
        this.cls = cls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
