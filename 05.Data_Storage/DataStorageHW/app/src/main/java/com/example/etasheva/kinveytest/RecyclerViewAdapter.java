package com.example.etasheva.kinveytest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etasheva.kinveytest.models.LaptopSqlite;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<LaptopSqlite> mLaptops;

    public RecyclerViewAdapter(Context context, ArrayList<LaptopSqlite> laptops) {
        this.mContext = context;
        this.mLaptops = laptops;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.row_layout, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder != null) {
            LaptopSqlite current = this.mLaptops.get(position);

            holder.mModel.setText(current.getModel());
            holder.mRam.setText(current.getCapacity_ram());
            holder.mHdd.setText(current.getCapacity_hdd());
            holder.mProcessor.setText(current.getProcessor_type());
            holder.mVideoCard.setText(current.getVideo_card_type());
            holder.mDisplay.setText(current.getDisplay_size());
            holder.mPrice.setText(current.getPrice());
            holder.mCurrency.setText(current.getCurrency());

        }
    }

    @Override
    public int getItemCount() {

        return this.mLaptops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mModel;
        private TextView mRam;
        private TextView mHdd;
        private TextView mProcessor;
        private TextView mVideoCard;
        private TextView mDisplay;
        private TextView mPrice;
        private TextView mCurrency;


        public ViewHolder(View itemView) {
            super(itemView);
            this.mModel = (TextView) itemView.findViewById(R.id.model);
            this.mRam = (TextView) itemView.findViewById(R.id.ram);
            this.mHdd = (TextView) itemView.findViewById(R.id.hdd);
            this.mProcessor = (TextView) itemView.findViewById(R.id.processor);
            this.mVideoCard = (TextView) itemView.findViewById(R.id.video_card);
            this.mDisplay = (TextView) itemView.findViewById(R.id.display);
            this.mPrice = (TextView) itemView.findViewById(R.id.price);
            this.mCurrency = (TextView) itemView.findViewById(R.id.currency);

        }


    }
}
