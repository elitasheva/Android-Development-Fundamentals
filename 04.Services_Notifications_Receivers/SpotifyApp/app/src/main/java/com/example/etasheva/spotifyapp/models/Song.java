package com.example.etasheva.spotifyapp.models;

public class Song {

    private int mSongId;
    private String mAuthor;
    private String mTitle;
    private String mAlbum;
    private String mFileSong;
    private String mFilePoster;

    public Song(int songId,String author, String title, String album, String fileSong, String filePoster) {
        this.mSongId = songId;
        this.mAuthor = author;
        this.mTitle = title;
        this.mAlbum = album;
        this.mFileSong = fileSong;
        this.mFilePoster = filePoster;
    }

    public int getSongId() {
        return this.mSongId;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public String getFileSong() {
        return this.mFileSong;
    }

    public String getFilePoster() {
        return this.mFilePoster;
    }
}
