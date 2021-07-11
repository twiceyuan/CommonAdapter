package com.twiceyuan.commonadapter.sample.complexHolderSample;

import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.holder.ComplexHolder;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.databinding.ItemComplexBinding;

/**
 * Created by twiceYuan on 21/02/2017.
 *
 * 复杂需求 Holder
 */
public class ComplexDataHolder extends ComplexHolder<ComplexData> {

    @Override
    public int getLayoutId() {
        return R.layout.item_complex;
    }

    private ItemComplexBinding binding;

    @Override
    public void initView() {
        super.initView();
        binding = ItemComplexBinding.bind(getItemView());
    }

    @Override
    public void bindData(ComplexData complexData, int position, CommonAdapter<ComplexData, ? extends CommonHolder<ComplexData>> adapter) {
        if (position == 0) {
            binding.tvText.setText("我是头部所以不显示消息");
            return;
        }

        if (position == adapter.getItemCount() - 1) {
            binding.tvText.setText("我是尾部所以也不显示消息");
            return;
        }

        binding.tvText.setText(String.format("消息是%s", complexData.message));
    }
}
