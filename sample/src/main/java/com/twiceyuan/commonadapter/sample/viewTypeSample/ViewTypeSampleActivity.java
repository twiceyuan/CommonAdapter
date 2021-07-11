package com.twiceyuan.commonadapter.sample.viewTypeSample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.viewTypeSample.holder.ArticleHolder;
import com.twiceyuan.commonadapter.sample.viewTypeSample.holder.PhotoHolder;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Article;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.BaseContent;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Photo;

/**
 * Created by twiceYuan on 3/4/16.
 * <p/>
 * 多个 ViewType 的 RecyclerView Adapter 实例
 */
public class ViewTypeSampleActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_type_sample);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        // 构造器中提供，数据和 Holder 的关系映射，可以设定多种 Holder 在一个 Recycler 中，原理是使用 viewType
        final MultiTypeAdapter<BaseContent> adapter;

        adapter = new MultiTypeAdapter<>(this);
        adapter.registerViewType(Photo.class, PhotoHolder.class);
        adapter.registerViewType(Article.class, ArticleHolder.class);

        recyclerView.setAdapter(adapter);

        //noinspection AlibabaAvoidManuallyCreateThread
        new Thread(() -> {
            //noinspection AlibabaUndefineMagicConstant
            for (int i = 0; i < 10000; i++) {
                adapter.add(mockArticle());
                adapter.add(mockArticle());
                adapter.add(mockPhoto());
            }
            runOnUiThread(adapter::notifyDataSetChanged);
        }).start();

        adapter.setOnItemClickListener((position, item) -> {
            if (item instanceof Article) {
                Toast.makeText(ViewTypeSampleActivity.this, "你点击了一篇文章，位置" + position, Toast.LENGTH_SHORT).show();
            }
            if (item instanceof Photo) {
                Toast.makeText(ViewTypeSampleActivity.this, "你点击了一张照片，位置" + position, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.addHolderListener(Article.class, ArticleHolder.class, (position, article, holder) -> holder.textTitle.setOnClickListener(v -> Toast.makeText(ViewTypeSampleActivity.this, "你点击了文章的标题", Toast.LENGTH_SHORT).show()));

        adapter.addHolderListener(Photo.class, PhotoHolder.class, (position, photo, holder) -> holder.binding.imagePicture.setOnClickListener(v -> Toast.makeText(ViewTypeSampleActivity.this, "你点击了一张图片", Toast.LENGTH_SHORT).show()));
    }

    public Article mockArticle() {
        Article article = new Article();
        article.title = getString(R.string.mock_article_title);
        article.content = getString(R.string.mock_article_content);
        return article;
    }

    public Photo mockPhoto() {
        Photo photo = new Photo();
        photo.photoId = R.drawable.img_sample;
        photo.description = getString(R.string.mock_image_desc);
        return photo;
    }
}
