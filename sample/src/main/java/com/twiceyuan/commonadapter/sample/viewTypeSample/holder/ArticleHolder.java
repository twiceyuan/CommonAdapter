package com.twiceyuan.commonadapter.sample.viewTypeSample.holder;

import android.view.View;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Article;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_article)
public class ArticleHolder extends CommonHolder<Article> {

    @ViewId(R.id.textTitle) TextView textTitle;
    @ViewId(R.id.textContent) TextView textContent;

    public ArticleHolder(View itemView) {
        super(itemView);
    }

    @Override public void bindData(Article article) {
        textTitle.setText(article.title);
        textContent.setText(article.content);
    }
}
