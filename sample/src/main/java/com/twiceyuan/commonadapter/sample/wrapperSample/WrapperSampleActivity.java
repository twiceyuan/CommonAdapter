package com.twiceyuan.commonadapter.sample.wrapperSample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
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
    private static final int MENU_LinearlayoutManager        = 1001;
    private static final int MENU_GridLayoutManager          = 1002;
    private static final int MENU_StaggeredGridLayoutManager = 1003;

    RecyclerView mRecyclerView;

    WrapperAdapter<Person, PersonHolder> mAdapter;
    CommonAdapter<Person, PersonHolder>  mChildAdapter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper_adapter);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuItem1 = menu.add(Menu.NONE, MENU_LinearlayoutManager, 0, "LinearlayoutManager");
        MenuItem menuItem2 = menu.add(Menu.NONE, MENU_GridLayoutManager, 0, "GridLayoutManager");
        MenuItem menuItem3 = menu.add(Menu.NONE, MENU_StaggeredGridLayoutManager, 0, "StaggeredGridLayoutManager");

        menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem3.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_LinearlayoutManager) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            return true;
        }

        if (item.getItemId() == MENU_GridLayoutManager) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            return true;
        }

        if (item.getItemId() == MENU_StaggeredGridLayoutManager) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
