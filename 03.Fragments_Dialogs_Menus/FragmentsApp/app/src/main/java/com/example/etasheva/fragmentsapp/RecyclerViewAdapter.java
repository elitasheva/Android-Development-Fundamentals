package com.example.etasheva.fragmentsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.etasheva.fragmentsapp.models.Article;
import com.example.etasheva.fragmentsapp.models.Database;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Database database;
    private RecycleViewSelectedElement mListener;


    public RecyclerViewAdapter(Context context, RecycleViewSelectedElement listener) {
        this.mContext = context;
        this.database = new Database();
        this.mListener = listener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.row_titles, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder != null) {
            Article currentArticle = this.database.getArticleByPosition(position);
            String title = currentArticle.getTitle();
            holder.mTextView.setText(title);
           if (currentArticle.isFavorite()){
               holder.mButton.setBackgroundResource(R.drawable.btn_star_on);
           }else {
               holder.mButton.setBackgroundResource(R.drawable.btn_star_off);
           }
            holder.setPosition(position);
        }
    }

    @Override
    public int getItemCount() {

        return this.database.getArticlesCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextView;
        private Button mButton;
        private int position;

        public void setPosition(int position) {
            this.position = position;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTextView = (TextView) itemView.findViewById(R.id.card_textView);
            this.mButton = (Button) itemView.findViewById(R.id.favorite_button);
            this.mButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.favorite_button){
                if (database.getArticleByPosition(position).isFavorite()){
                    view.setBackgroundResource(R.drawable.btn_star_off);
                    database.getArticleByPosition(position).setFavorite(false);
                }else {
                    view.setBackgroundResource(R.drawable.btn_star_on);
                    database.getArticleByPosition(position).setFavorite(true);
                }

            }else {

                Article searchedArticle = database.getArticleByPosition(position);
                mListener.onItemSelected(searchedArticle);
            }
        }
    }

}
