package com.example.etasheva.spotifyapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private ArrayList<String> mAdapterData;
    public static RecycleViewSelectedElement mListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewLarge;
        private TextView mTextViewMedium;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTextViewLarge = (TextView) itemView.findViewById(R.id.textViewLarge);
            this.mTextViewMedium = (TextView) itemView.findViewById(R.id.textViewMedium);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected(position);
                }
            });
        }

        public void setItemPosition(int position) {
            this.position = position;
        }
    }

    public RecycleViewAdapter(ArrayList<String> data, RecycleViewSelectedElement listener) {
        this.mAdapterData = data;
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return this.mAdapterData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_template, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder != null) {
            String currentSong = this.mAdapterData.get(position);
            String[] parts = currentSong.split("-");

            holder.mTextViewLarge.setText(parts[1]);
            holder.mTextViewMedium.setText(currentSong);
            holder.setItemPosition(position);
        }
    }
}
