package com.twiceyuan.commonadapter.sample.viewTypeSample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
import com.twiceyuan.commonadapter.library.adapter.ViewTypeItem;
import com.twiceyuan.commonadapter.library.adapter.ViewTypeMapper;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.viewTypeSample.holder.ArticleHolder;
import com.twiceyuan.commonadapter.sample.viewTypeSample.holder.PhotoHolder;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Article;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Photo;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p/>
 * 多个 ViewType 的 RecyclerView Adapter 实例
 */
public class ViewTypeSampleActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_type_sample);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 构造器中提供，数据和 Holder 的关系映射，可以设定多种 Holder 在一个 Recycler 中，原理是使用 viewType
        final MultiTypeAdapter adapter = new MultiTypeAdapter(this, new ViewTypeMapper() {
            @Override
            public Class<? extends CommonHolder<? extends ViewTypeItem>> getViewType(ViewTypeItem item, int position) {

                // 可以根据实体数据的类型，或者位置来设定使用哪种 Holder，但不需要定义 viewType 的常量
                if (item instanceof Photo) {
                    return PhotoHolder.class;
                }
                if (item instanceof Article) {
                    return ArticleHolder.class;
                }
                return null;
            }
        });

        recyclerView.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override public void run() {
                for (int i = 0; i < 10000; i++) {
                    adapter.add(mockArticle());
                    adapter.add(mockArticle());
                    adapter.add(mockPhoto());
                }
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

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

        adapter.addHolderListener(Article.class, ArticleHolder.class, new CommonAdapter.OnBindListener<Article, ArticleHolder>() {
            @Override public void onBind(int position, Article article, ArticleHolder holder) {
                holder.textTitle.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        Toast.makeText(ViewTypeSampleActivity.this, "你点击了文章的标题", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        adapter.addHolderListener(Photo.class, PhotoHolder.class, new CommonAdapter.OnBindListener<Photo, PhotoHolder>() {
            @Override public void onBind(int position, Photo photo, PhotoHolder holder) {
                holder.imagePicture.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        Toast.makeText(ViewTypeSampleActivity.this, "你点击了一张图片", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
