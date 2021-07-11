package com.twiceyuan.commonadapter.sample.simpleSample.holder;

import android.util.Log;

import com.twiceyuan.commonadapter.library.Singleton;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.databinding.ItemPersonBinding;
import com.twiceyuan.commonadapter.sample.simpleSample.model.Person;

/**
 * Created by twiceYuan on 1/20/16.
 */
public class PersonHolder extends CommonHolder<Person> {

    @Override
    public int getLayoutId() {
        return R.layout.item_person;
    }

    private static final String TAG = "PersonHolder";

    public ItemPersonBinding binding;

    @Singleton private Object mObject1;
    @Singleton private Object mObject2;
    @Singleton private Object mObject3;
    @Singleton private Object mObject4;
    @Singleton private Object mObject5;
    @Singleton private Object mObject6;
    @Singleton private Object mObject7;
    @Singleton private Object mObject8;

    private Object mObject;

    @Override
    public void initView() {
        super.initView();
        binding = ItemPersonBinding.bind(getItemView());
    }

    @Override
    public void initSingleton() {
        Log.i(TAG, "Init singleton instance");
        mObject = new Object();
        mObject1 = new Object();
        mObject2 = new Object();
        mObject3 = new Object();
        mObject4 = new Object();
        mObject5 = new Object();
        mObject6 = new Object();
        mObject7 = new Object();
        mObject8 = new Object();
    }

    @Override
    public void bindData(Person person) {
        binding.name.setText(person.name);
        binding.email.setText(person.email);

        Log.i(TAG, "mObject = " + mObject);
        Log.i(TAG, "mObject1 = " + mObject1);
        Log.i(TAG, "mObject2 = " + mObject2);
        Log.i(TAG, "mObject3 = " + mObject3);
        Log.i(TAG, "mObject4 = " + mObject4);
        Log.i(TAG, "mObject5 = " + mObject5);
        Log.i(TAG, "mObject6 = " + mObject6);
        Log.i(TAG, "mObject7 = " + mObject7);
        Log.i(TAG, "mObject8 = " + mObject8);
    }
}
