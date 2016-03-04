package com.twiceyuan.commonadapter.sample.viewTypeSample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.adapter.ViewTypeItem;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Article;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Photo;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 *
 * 多个 ViewType 的 RecyclerView Adapter 实例
 */
public class ViewTypeSampleActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_type_sample);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CommonAdapter<ViewTypeItem, CommonHolder<ViewTypeItem>> adapter = new CommonAdapter<>(this, new ListViewTypeMapper());
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 10000; i++) {
            adapter.add(mockArticle());
            adapter.add(mockArticle());
            adapter.add(mockPhoto());
        }

        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener<ViewTypeItem>() {
            @Override public void onClick(int position, ViewTypeItem item) {
                if (item instanceof Article) {
                    Toast.makeText(ViewTypeSampleActivity.this, "你点击了一篇文章，位置" + position, Toast.LENGTH_SHORT).show();
                }
                if (item instanceof Photo) {
                    Toast.makeText(ViewTypeSampleActivity.this, "你点击了一张照片，位置" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Article mockArticle() {
        Article article = new Article();
        article.title = "这是一个模拟标题";
        article.content = "这是一个文章的模拟内容，因为是文章的内容，所以不能太短敷衍了事。所以就有现在这么长了。";
        return article;
    }

    public Photo mockPhoto() {
        Photo photo = new Photo();
        photo.photoId = R.drawable.img_sample;
        photo.description = "这是一个图片的介绍";
        return photo;
    }
}
