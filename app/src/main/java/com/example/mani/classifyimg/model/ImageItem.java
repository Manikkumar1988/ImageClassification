package com.example.mani.classifyimg.model;


import android.util.SparseArray;

import com.example.mani.classifyimg.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImageItem {
    private int mImageName;

    public ImageItem() {
        mTags = new HashSet<String>();

    }
    public int getmImageName() {
        return mImageName;
    }

    public void setmImageName(int mImageName) {
        this.mImageName = mImageName;
    }

    private Set<String> mTags;

    public void addTags(List<String> tags) {
        for (String allTags : tags){
            mTags.add(new String(allTags));
        }
    }


    public boolean hasTag() {
        if (mTags.size()>0){
            return true;
        }
        return false;
    }

    public static HashMap<Integer,ImageItem> createImageList() {
        HashMap<Integer,ImageItem> imageItemSparseArray = new HashMap<>();

        ImageItem imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.book);
        imageItemSparseArray.put(R.drawable.book,imageItem);

        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.bourne);
        imageItemSparseArray.put(R.drawable.bourne,imageItem);

        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.cacw);
        imageItemSparseArray.put(R.drawable.cacw,imageItem);

        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.deadpool);
        imageItemSparseArray.put(R.drawable.deadpool,imageItem);


        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.doctor);
        imageItemSparseArray.put(R.drawable.doctor,imageItem);

        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.dory);
        imageItemSparseArray.put(R.drawable.dory,imageItem);


        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.hours);
        imageItemSparseArray.put(R.drawable.hours,imageItem);

        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.hunger);
        imageItemSparseArray.put(R.drawable.hunger,imageItem);


        imageItem = new ImageItem();
        imageItem.setmImageName(R.drawable.ipman);
        imageItemSparseArray.put(R.drawable.ipman,imageItem);

        return imageItemSparseArray;
    }

}
