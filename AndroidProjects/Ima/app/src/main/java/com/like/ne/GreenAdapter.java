package com.like.ne;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder>{
    private static final String TAG = GreenAdapter.class.getSimpleName();
    private static int viewHolderCount;
    private int numberItems;
    ListItemClickListener onClickListener;

    public GreenAdapter(int numberOfItems, ListItemClickListener onClickListener) {
        this.numberItems = numberOfItems;
        viewHolderCount = 0;
        this.onClickListener = onClickListener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int position);
    }

    @NonNull
    @Override
    public GreenAdapter.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflate = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflate.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        int backgroundColorForViewHolder = ColorUtils.getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GreenAdapter.NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listItemNumberView;
        TextView viewHolderIndex;

        public NumberViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }

        @Override
        public void onClick(View view) {
            onClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
