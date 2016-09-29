package com.example.etasheva.fragmentsapp.models;

public class Article {

    private String title;
    private String text;
    private String date;
    private boolean isFavorite;

    public Article(String title, String text, String date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public String getDate() {
        return this.date;
    }

    public boolean isFavorite() {
        return this.isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
