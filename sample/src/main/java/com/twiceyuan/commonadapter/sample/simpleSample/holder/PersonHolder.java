package com.twiceyuan.commonadapter.sample.simpleSample.holder;

import android.view.View;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
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

    @ViewId(R.id.name) TextView name;
    @ViewId(R.id.email) TextView email;

    public PersonHolder(View itemView) {
        super(itemView);
    }

    @Override public void bindData(Person person) {
        name.setText(person.name);
        email.setText(person.email);
    }
}
