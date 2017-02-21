package com.twiceyuan.commonadapter.sample.complexHolderSample;

import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.holder.ComplexHolder;
import com.twiceyuan.commonadapter.sample.R;

/**
 * Created by twiceYuan on 21/02/2017.
 *
 * 复杂需求 Holder
 */
@LayoutId(R.layout.item_complex)
public class ComplexDataHolder extends ComplexHolder<ComplexData> {

    @ViewId(R.id.tv_text)
    private TextView mTextView;

    @Override
    public void bindData(ComplexData complexData, int position, CommonAdapter<ComplexData, ? extends CommonHolder<ComplexData>> adapter) {
        if (position == 0) {
            mTextView.setText("我是头部所以不显示消息");
            return;
        }

        if (position == adapter.getItemCount() - 1) {
            mTextView.setText("我是尾部所以也不显示消息");
            return;
        }

        mTextView.setText(String.format("消息是%s", complexData.message));
    }
}
