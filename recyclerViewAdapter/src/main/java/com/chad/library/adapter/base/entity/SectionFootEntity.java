package com.chad.library.adapter.base.entity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class SectionFootEntity<T> {
    public boolean isHeader;
    public boolean isFooter;
    public T t;
    public String header;

    public SectionFootEntity(boolean isHeader, boolean isFooter, String header) {
        this.isHeader = isHeader;
        this.isFooter = isFooter;
        this.header = header;
        this.t = null;
    }

    public SectionFootEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}
