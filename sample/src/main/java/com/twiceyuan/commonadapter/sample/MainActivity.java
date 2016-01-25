package com.twiceyuan.commonadapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.twiceyuan.commonadapter.library.adapter.DataManager;
import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.adapter.CommonListAdapter;
import com.twiceyuan.commonadapter.sample.model.Person;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_LIST_VIEW     = 1001;
    private static final int MENU_RECYCLER_VIEW = 1002;

    private RecyclerView mRecyclerView;
    private ListView     mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mListView = (ListView) findViewById(R.id.listView);

        /**
         * RecyclerView Adapter Sample
         */
        // build adapter
        CommonAdapter<Person, PersonHolder> recyclerAdapter =
                new CommonAdapter<>(this, PersonHolder.class);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // set adapter
        mRecyclerView.setAdapter(recyclerAdapter);

        // mock data
        mockData(recyclerAdapter);

        // set elements click listener
        recyclerAdapter.setOnBindListener(new CommonAdapter.OnBindListener<Person, PersonHolder>() {
            @Override public void onBind(int position, final Person person, PersonHolder holder) {
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        toast(person.name);
                    }
                });
                holder.email.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        toast(person.email);
                    }
                });
            }
        });

        /**
         * ListView Adapter Sample
         */
        // build adapter
        CommonListAdapter<Person, PersonHolder> listAdapter = new CommonListAdapter<>(this, PersonHolder.class);
        mListView.setAdapter(listAdapter);

        // mock data
        mockData(listAdapter);

        listAdapter.setOnBindListener(new CommonListAdapter.OnBindListener<Person, PersonHolder>() {
            @Override public void onBind(int position, final Person person, PersonHolder holder) {
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        toast(person.name);
                    }
                });
                holder.email.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        toast(person.email);
                    }
                });
            }
        });

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
        Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem1 = menu.add(Menu.NONE, MENU_LIST_VIEW, 0, "ListView Adapter");
        MenuItem menuItem2 = menu.add(Menu.NONE, MENU_RECYCLER_VIEW, 0, "RecyclerView Adapter");
        menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_LIST_VIEW) {
            showListViewSample();
            return true;
        }

        if (item.getItemId() == MENU_RECYCLER_VIEW) {
            showRecyclerViewSample();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void mockData(DataManager<Person> personAdapter) {
        personAdapter.add(new Person("twiceYuan0", "twiceyuan@gmail.com"));
        personAdapter.add(new Person("twiceYuan1", "twiceyuan@gmail.com"));
        personAdapter.add(new Person("twiceYuan2", "twiceyuan@gmail.com"));
        personAdapter.add(new Person("twiceYuan3", "twiceyuan@gmail.com"));
        personAdapter.add(new Person("twiceYuan4", "twiceyuan@gmail.com"));
        personAdapter.add(new Person("twiceYuan5", "twiceyuan@gmail.com"));
        personAdapter.add(new Person("twiceYuan6", "twiceyuan@gmail.com"));
        personAdapter.add(new Person("twiceYuan7", "twiceyuan@gmail.com"));
    }
}
