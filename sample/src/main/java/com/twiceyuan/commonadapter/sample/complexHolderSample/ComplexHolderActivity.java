package com.twiceyuan.commonadapter.sample.complexHolderSample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.holder.ComplexHolder;
import com.twiceyuan.commonadapter.sample.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by twiceYuan on 21/02/2017.
 * <p>
 * 复杂 Holder Sample
 */
public class ComplexHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_holder);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        CommonAdapter<ComplexData, ComplexHolder<ComplexData>> adapter;
        recyclerView.setAdapter(adapter = new CommonAdapter<>(this, ComplexDataHolder.class));

        adapter.addAll(provideMockData());
    }

    private Collection<? extends ComplexData> provideMockData() {
        List<ComplexData> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ComplexData d = new ComplexData();
            d.message = "Hello" + (i);
            data.add(d);
        }
        return data;
    }
}
