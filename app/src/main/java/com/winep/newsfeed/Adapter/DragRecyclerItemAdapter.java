package com.winep.newsfeed.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.winep.newsfeed.DataModel.NewsGroup;
import com.winep.newsfeed.R;

import java.util.List;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class DragRecyclerItemAdapter extends RecyclerView.Adapter<DragRecyclerItemAdapter.MyViewHolder> implements DraggableItemAdapter<DragRecyclerItemAdapter.MyViewHolder> {

    List<NewsGroup> listNewsGroup;

    public DragRecyclerItemAdapter(List<NewsGroup> myItems) {
        setHasStableIds(true); // this is required for D&D feature.
        listNewsGroup =myItems;
    }

    @Override
    public long getItemId(int position) {
        return listNewsGroup.get(position).getNewsGroupId(); // need to return stable (= not change even after reordered) value
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsGroup item = listNewsGroup.get(position);
        holder.textView.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return listNewsGroup.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        NewsGroup movedItem = listNewsGroup.remove(fromPosition);
        listNewsGroup.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    static class MyViewHolder extends AbstractDraggableItemViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

}

