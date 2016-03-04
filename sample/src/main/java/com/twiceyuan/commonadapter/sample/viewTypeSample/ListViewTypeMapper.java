package com.twiceyuan.commonadapter.sample.viewTypeSample;

import com.twiceyuan.commonadapter.library.adapter.ViewTypeItem;
import com.twiceyuan.commonadapter.library.adapter.ViewTypeMapper;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.sample.viewTypeSample.holder.ArticleHolder;
import com.twiceyuan.commonadapter.sample.viewTypeSample.holder.PhotoHolder;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Article;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Photo;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 *
 * ViewType 与实体关系的逻辑映射
 */
public class ListViewTypeMapper implements ViewTypeMapper {

    /**
     * 定义两种 ViewType 类型常量
     */
    public static final int VIEW_TYPE_ARTICLE = 1;
    public static final int VIEW_TYPE_PHOTO   = 2;

    /**
     * 实体/Position -> ViewType
     */
    @Override public int getViewType(ViewTypeItem item, int position) {

        if (item instanceof Photo) {
            return VIEW_TYPE_PHOTO;
        }
        if (item instanceof Article) {
            return VIEW_TYPE_ARTICLE;
        }
        return 0;
    }

    /**
     * ViewType -> ViewHolder
     */
    @Override public Class<? extends CommonHolder<? extends ViewTypeItem>> getHolder(int viewType) {
        if (viewType == VIEW_TYPE_ARTICLE) {
            return ArticleHolder.class;
        }
        if (viewType == VIEW_TYPE_PHOTO) {
            return PhotoHolder.class;
        }
        return null;
    }
}
