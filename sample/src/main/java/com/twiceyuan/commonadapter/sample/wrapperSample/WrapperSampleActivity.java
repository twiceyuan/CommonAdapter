package com.twiceyuan.commonadapter.sample.wrapperSample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.adapter.WrapperAdapter;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.simpleSample.holder.PersonHolder;
import com.twiceyuan.commonadapter.sample.simpleSample.model.Person;

/**
 * Created by twiceYuan on 4/16/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p/>
 * 添加 Header 和 Footer 的 RecyclerView 演示
 */
public class WrapperSampleActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    WrapperAdapter<Person, PersonHolder> mAdapter;
    CommonAdapter<Person, PersonHolder>  mChildAdapter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper_adapter);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final String labelHeader = "I'm a Header!";
        final String labelFooter = "I'm a Footer!";

        final TextView header = getHeaderOrFooter(labelHeader);
        final TextView footer = getHeaderOrFooter(labelFooter);

        mChildAdapter = new CommonAdapter<>(this, PersonHolder.class);
        mAdapter = new WrapperAdapter<>(mChildAdapter, mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);

        for (int i = 0; i < 20; i++) {
            mChildAdapter.add(new Person("twiceYuan" + i, "twiceyuan@gmail.com"));
        }
        mChildAdapter.notifyDataSetChanged();

        mAdapter.setHeaderView(header);
        mAdapter.setFooterView(footer);
    }

    public TextView getHeaderOrFooter(final String text) {

        final TextView view = (TextView) View.inflate(this, R.layout.header_or_footer, null);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        view.setLayoutParams(params);
        view.setHeight(400);
        view.setText(text);

        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(WrapperSampleActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
