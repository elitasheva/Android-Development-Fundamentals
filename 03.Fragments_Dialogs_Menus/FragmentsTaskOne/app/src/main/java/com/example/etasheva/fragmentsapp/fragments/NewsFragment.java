package com.example.etasheva.fragmentsapp.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.etasheva.fragmentsapp.R;
import com.example.etasheva.fragmentsapp.models.Article;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private View mNewsView;
    private Article article;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.mNewsView = inflater.inflate(R.layout.fragment_news, container, false);
        TextView title = (TextView) this.mNewsView.findViewById(R.id.news_title);
        title.setText(this.article.getTitle());
        TextView date = (TextView) this.mNewsView.findViewById(R.id.news_date);
        date.setText(this.article.getDate());
        TextView body = (TextView) this.mNewsView.findViewById(R.id.news_body);
        body.setText(this.article.getText() + System.lineSeparator());

        return this.mNewsView;
    }

    public void updateArticleView(Article article) {
        this.article = article;

    }
}
