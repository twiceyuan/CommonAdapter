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

import com.twiceyuan.commonadapter.library.adapter.Adapter;
import com.twiceyuan.commonadapter.library.adapter.SimpleListAdapter;
import com.twiceyuan.commonadapter.library.adapter.SimpleRecyclerAdapter;
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
        SimpleRecyclerAdapter<Person> recyclerAdapter = new SimpleRecyclerAdapter<>(this, PersonHolder.class);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // set adapter
        mRecyclerView.setAdapter(recyclerAdapter);

        // mock data
        mockData(recyclerAdapter);

        // set elements click listener
        recyclerAdapter.setOnElementClickListener(R.id.name, new SimpleRecyclerAdapter.OnViewClickListener<Person>() {
            @Override public void onClick(int position, Person person) {
                toast("你点的是 RecyclerView 中" + person.name + "的名字");
            }
        });

        recyclerAdapter.setOnElementClickListener(R.id.email, new SimpleRecyclerAdapter.OnViewClickListener<Person>() {
            @Override public void onClick(int position, Person person) {
                toast("你点的是 RecyclerView 中" + person.name + "的邮箱");
            }
        });

        /**
         * ListView Adapter Sample
         */
        // build adapter
        SimpleListAdapter<Person> listAdapter = new SimpleListAdapter<>(this, PersonHolder.class);
        mListView.setAdapter(listAdapter);

        // mock data
        mockData(listAdapter);

        listAdapter.setOnElementClickListener(R.id.name, new SimpleListAdapter.OnViewClickListener<Person>() {
            @Override public void onClick(int position, Person person) {
                toast("你点击的是 ListView 中" + person.name + "的名字");
            }
        });

        listAdapter.setOnElementClickListener(R.id.email, new SimpleListAdapter.OnViewClickListener<Person>() {
            @Override public void onClick(int position, Person person) {
                toast("你点击的是 ListView 中" + person.name + "的邮箱");
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

    void mockData(Adapter<Person> personAdapter) {
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
