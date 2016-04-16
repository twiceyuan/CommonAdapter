package com.twiceyuan.commonadapter.sample.wrapperSample;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
 *
 * 添加 Header 和 Footer 的 RecyclerView 演示
 */
public class WrapperSampleActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    WrapperAdapter<Person, PersonHolder> mAdapter;
    CommonAdapter<Person, PersonHolder> mChildAdapter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper_adapter);

        final TextView header = (TextView) View.inflate(this, R.layout.header_or_footer, null);
        final TextView footer = (TextView) View.inflate(this, R.layout.header_or_footer, null);

        final String labelHeader = "I'm a Header!";
        final String labelFooter = "I'm a Footer!";

        header.setText(labelHeader);
        footer.setText(labelFooter);

        header.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(WrapperSampleActivity.this, labelHeader, Toast.LENGTH_SHORT).show();
            }
        });

        footer.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(WrapperSampleActivity.this, labelFooter, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mChildAdapter = new CommonAdapter<>(this, PersonHolder.class);
        mAdapter = new WrapperAdapter<>(mChildAdapter);

        mRecyclerView.setAdapter(mAdapter);

        new Thread(new Runnable() {
            @Override public void run() {

                toast("add data after 3 seconds");
                SystemClock.sleep(3000);
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        for (int i = 0; i < 20; i++) {
                            mAdapter.add(new Person("twiceYuan" + i, "twiceyuan@gmail.com"));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });

                toast("add header after 3 seconds");
                SystemClock.sleep(3000);
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        mAdapter.setHeaderView(header);
                        mAdapter.setFooterView(footer);
                    }
                });
            }
        }).start();
    }

    public void toast(final String message) {
        runOnUiThread(new Runnable() {
            @Override public void run() {
                Toast.makeText(WrapperSampleActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
