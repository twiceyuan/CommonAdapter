package com.twiceyuan.commonadapter.sample.simpleSample.holder;

import android.content.Context;
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

    @ViewId(R.id.name) public TextView name;
    @ViewId(R.id.email) public TextView email;

    @Singleton
    private Context mContext;

    @Override
    public void initSingleton() {
        mContext = getItemView().getContext();
    }

    @Override public void bindData(Person person) {
        name.setText(person.name);
        email.setText(person.email);

        Log.i(TAG, "Singleton context = " + mContext);
    }
}
