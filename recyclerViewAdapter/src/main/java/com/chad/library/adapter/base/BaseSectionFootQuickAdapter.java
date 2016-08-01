package com.chad.library.adapter.base;

import android.view.ViewGroup;

import com.chad.library.adapter.base.entity.SectionFootEntity;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class BaseSectionFootQuickAdapter<T extends SectionFootEntity> extends BaseQuickAdapter {


    protected int mSectionHeadResId;
    protected int mFootResId;
    protected static final int SECTION_HEADER_VIEW = 0x00000444;
    protected static final int SECTION_FOOTER_VIEW = 0x00000445;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public BaseSectionFootQuickAdapter(int layoutResId, int sectionHeadResId, int footResId, List<T> data) {
        super(layoutResId, data);
        this.mSectionHeadResId = sectionHeadResId;
        this.mFootResId = footResId;
    }

    @Override
    protected int getDefItemViewType(int position) {
        boolean isHeader = ((SectionFootEntity) mData.get(position)).isHeader;
        boolean isFooter = ((SectionFootEntity) mData.get(position)).isFooter;

        if (isHeader) {
            return SECTION_HEADER_VIEW;
        } else if (isFooter) {
            return SECTION_FOOTER_VIEW;
        } else {
            return 0;
        }
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_HEADER_VIEW) {
            return new BaseViewHolder(getItemView(mSectionHeadResId, parent));
        } else if (viewType == SECTION_FOOTER_VIEW) {
            return new BaseViewHolder(getItemView(mFootResId, parent));
        }

        return super.onCreateDefViewHolder(parent, viewType);
    }

    /**
     * @param holder A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder holder, Object item) {
        switch (holder.getItemViewType()) {
            case SECTION_HEADER_VIEW:
                setFullSpan(holder);
                convertHead(holder, (T) item);
                break;
            case SECTION_FOOTER_VIEW:
                setFullSpan(holder);
                convertFoot(holder, (T) item);
                break;
            default:
                convert(holder, (T) item);
                break;
        }
    }

    protected abstract void convertHead(BaseViewHolder helper, T item);

    protected abstract void convertFoot(BaseViewHolder helper, T item);

    protected abstract void convert(BaseViewHolder helper, T item);


}
