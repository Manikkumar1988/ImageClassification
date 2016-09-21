package com.example.mani.classifyimg.model;


import com.example.mani.classifyimg.R;

import java.util.ArrayList;

public class ImageItem {
    private int mImageName;


    public int getmImageName() {
        return mImageName;
    }

    public void setmImageName(int mImageName) {
        this.mImageName = mImageName;
    }

    public static ArrayList<ImageItem> createImageList(int numImageItems) {
        ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();

        for (int i = 0; i < numImageItems; i++) {
            ImageItem imageItem = new ImageItem();
            imageItem.setmImageName(R.mipmap.ic_launcher);
            imageItems.add(imageItem);
        }

        return imageItems;
    }
}
