package com.example.mani.classifyimg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mani.classifyimg.R;
import com.example.mani.classifyimg.model.ImageItem;

import java.util.ArrayList;
import java.util.List;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> implements View.OnClickListener {

    private List<ImageItem> mImageList;

    public ImagesAdapter(List<ImageItem> mImageList) {
        this.mImageList = mImageList;
        selectedItems = new SparseBooleanArray();

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_IMAGE) {
            // Inflate the custom layout
            View imageView = inflater.inflate(R.layout.image_item_layout, parent, false);
            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(imageView);
            return viewHolder;
        } else {
            View emptyView = inflater.inflate(R.layout.empty_layout, parent, false);
            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(emptyView);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_IMAGE) {
            // Get the data model based on position
            ImageItem imageItem = mImageList.get(position);

            // Set item views based on your views and data model
            ImageView imageView = viewHolder.nameImageView;
            imageView.setTag(position);
            imageView.setImageResource(imageItem.getmImageName());
            imageView.setOnClickListener(this);
            ((RelativeLayout) imageView.getParent()).setActivated(selectedItems.get(position, false));
        } else {

        }

    }

    @Override
    public int getItemCount() {

        if(mImageList.size() == 0){
            return 1;
        }else {
            return mImageList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mImageList.size() == 0) {
            return VIEW_TYPE_EMPTY;
        }else{
            return VIEW_TYPE_IMAGE;
        }
    }

    @Override
    public void onClick(View v) {
        toggleSelection((Integer) v.getTag());
    }

    public void removeSelectedData() {
        for (int i = selectedItems.size()-1;i >= 0;i--) {
            mImageList.remove(i);
        }
        clearSelections();
    }

        public static class ViewHolder extends ParentViewHolder {
            public ImageView nameImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                nameImageView = (ImageView) itemView.findViewById(R.id.image_item);
            }
        }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {

        public ParentViewHolder(View itemView) {
            super(itemView);
        }
    }

    private SparseBooleanArray selectedItems;

    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items =
                new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    private static final int VIEW_TYPE_IMAGE = 1;
    private static final int VIEW_TYPE_EMPTY = 2;
}
