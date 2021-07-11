package com.twiceyuan.commonadapter.sample.viewTypeSample.holder;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.sample.R;
import com.twiceyuan.commonadapter.sample.databinding.ItemPhotoBinding;
import com.twiceyuan.commonadapter.sample.viewTypeSample.model.Photo;

/**
 * Created by twiceYuan on 3/4/16.
 */
public class PhotoHolder extends CommonHolder<Photo> {

    @Override
    public int getLayoutId() {
        return R.layout.item_photo;
    }

    public ItemPhotoBinding binding;

    @Override
    public void initView() {
        super.initView();
        binding = ItemPhotoBinding.bind(getItemView());
    }

    @Override
    public void bindData(Photo photo) {
        Context context = getItemView().getContext();
        binding.imagePicture.setImageDrawable(ContextCompat.getDrawable(context, photo.photoId));
        binding.textDesc.setText(photo.description);
    }
}
