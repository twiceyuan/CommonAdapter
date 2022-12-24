package com.twiceyuan.commonadapter.sample.simpleSample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.adapter.CommonListAdapter;
import com.twiceyuan.commonadapter.library.adapter.DataManager;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.complexHolderSample.ComplexHolderActivity;
import com.twiceyuan.commonadapter.sample.simpleSample.holder.PersonHolder;
import com.twiceyuan.commonadapter.sample.simpleSample.model.Person;
import com.twiceyuan.commonadapter.sample.viewTypeSample.ViewTypeSampleActivity;
import com.twiceyuan.commonadapter.sample.wrapperSample.WrapperSampleActivity;

public class SimpleSampleActivity extends AppCompatActivity {

    private static final int MENU_LIST_VIEW        = 1001;
    private static final int MENU_RECYCLER_VIEW    = 1002;
    private static final int MENU_VIEW_TYPE_SAMPLE = 1003;
    private static final int MENU_WRAPPER_ADAPTER  = 1004;
    private static final int MENU_COMPLEX_ADAPTER  = 1005;

    private RecyclerView mRecyclerView;
    private ListView     mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mListView = (ListView) findViewById(R.id.listView);

        /*
          RecyclerView Adapter Sample
         */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setVerticalFadingEdgeEnabled(true);
//        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        mRecyclerView.setFadingEdgeLength(Math.round(30 * Resources.getSystem().getDisplayMetrics().density));

        CommonAdapter<Person, PersonHolder> recyclerAdapter = CommonAdapter.create(this, PersonHolder.class)
                .attach(mRecyclerView)
                // set elements click listener
                .setOnBindListener((position, person, holder) -> {
                    holder.binding.name.setOnClickListener(v -> toast(person.name));
                    holder.binding.email.setOnClickListener(v -> toast(person.email));
                });

        // mock data
        mockData(recyclerAdapter);

        /*
          ListView Adapter Sample
         */
        // build adapter
        CommonListAdapter<Person, PersonHolder> listAdapter = CommonListAdapter.create(this, PersonHolder.class)
                .attach(mListView)
                .setOnBindListener(new CommonListAdapter.OnBindListener<Person, PersonHolder>() {
                    @Override
                    public void onBind(View parentView, int position, final Person person, PersonHolder holder) {
                        holder.binding.name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toast(person.name);
                            }
                        });
                        holder.binding.email.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toast(person.email);
                            }
                        });
                    }
                });

        mListView.setDivider(null);

        // mock data
        mockData(listAdapter);

        showListViewSample();
    }

    public void showListViewSample() {
        setTitle("ListView Adapter Sample");
        mRecyclerView.setVisibility(View.GONE);
        mListView.setVisibility(View.VISIBLE);
    }

    public void showRecyclerViewSample() {
        setTitle("RecyclerView Adapter Sample");
        mRecyclerView.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
    }

    void toast(String toast) {
        Toast.makeText(SimpleSampleActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuItem1 = menu.add(Menu.NONE, MENU_LIST_VIEW, 0, "ListView Adapter");
        MenuItem menuItem2 = menu.add(Menu.NONE, MENU_RECYCLER_VIEW, 0, "RecyclerView Adapter");
        MenuItem menuItem3 = menu.add(Menu.NONE, MENU_VIEW_TYPE_SAMPLE, 0, "ViewType Sample");
        MenuItem menuItem4 = menu.add(Menu.NONE, MENU_WRAPPER_ADAPTER, 0, "Header & Footer");
        MenuItem menuItem5 = menu.add(Menu.NONE, MENU_COMPLEX_ADAPTER, 0, "Complex Holder");

        menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem3.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem4.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem5.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_LIST_VIEW) {
            showListViewSample();
            return true;
        }

        if (item.getItemId() == MENU_RECYCLER_VIEW) {
            showRecyclerViewSample();
            return true;
        }

        if (item.getItemId() == MENU_VIEW_TYPE_SAMPLE) {
            startActivity(new Intent(this, ViewTypeSampleActivity.class));
            return true;
        }

        if (item.getItemId() == MENU_WRAPPER_ADAPTER) {
            startActivity(new Intent(this, WrapperSampleActivity.class));
            return true;
        }

        if (item.getItemId() == MENU_COMPLEX_ADAPTER) {
            startActivity(new Intent(this, ComplexHolderActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void mockData(DataManager<Person> personAdapter) {
        for (int i = 0; i < 10000; i++) {
            personAdapter.add(new Person("twiceYuan" + i, "twiceyuan@gmail.com"));
        }
    }
}
