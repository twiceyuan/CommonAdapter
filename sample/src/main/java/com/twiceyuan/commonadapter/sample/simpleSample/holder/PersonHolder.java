package com.twiceyuan.commonadapter.sample.simpleSample.holder;

import android.util.Log;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.Singleton;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.simpleSample.model.Person;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_person)
public class PersonHolder extends CommonHolder<Person> {

    private static final String TAG = "PersonHolder";

    @ViewId(R.id.name) public  TextView name;
    @ViewId(R.id.email) public TextView email;

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
        name.setText(person.name);
        email.setText(person.email);

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
