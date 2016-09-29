package com.example.etasheva.spotifyapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.etasheva.spotifyapp.R;
import com.example.etasheva.spotifyapp.interfaces.RecycleViewSelectedElement;
import com.example.etasheva.spotifyapp.models.Database;
import com.example.etasheva.spotifyapp.models.Song;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Database mDatabase;
    private Context mContext;
    public static RecycleViewSelectedElement mListener;


    public RecycleViewAdapter(Context context, RecycleViewSelectedElement listener) {
        this.mListener = listener;
        this.mContext = context;
        this.mDatabase = new Database();
    }

    @Override
    public int getItemCount() {
        return this.mDatabase.getSongsCount();
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
            holder.mTitle.setText(this.mDatabase.getElementByPosition(position).getTitle());
            holder.mAuthor.setText(this.mDatabase.getElementByPosition(position).getAuthor());
            holder.mAlbum.setText(this.mDatabase.getElementByPosition(position).getAlbum());
            if (position % 2 == 0){
                holder.mExplicit.setVisibility(View.GONE);
                holder.mDownload.setVisibility(View.VISIBLE);
            }else {
                holder.mExplicit.setVisibility(View.VISIBLE);
                holder.mDownload.setVisibility(View.GONE);
            }
            holder.setItemPosition(position);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;
        private TextView mAuthor;
        private TextView mAlbum;
        private TextView mDownload;
        private TextView mExplicit;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.mAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            this.mAlbum = (TextView) itemView.findViewById(R.id.tv_album);
            this.mDownload = (TextView) itemView.findViewById(R.id.tv_download);
            this.mExplicit = (TextView) itemView.findViewById(R.id.tv_explicit);

            itemView.setOnClickListener(this);
        }

        public void setItemPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Song currentSong = mDatabase.getElementByPosition(position);
            mListener.onItemSelected(currentSong);
        }
    }
}
