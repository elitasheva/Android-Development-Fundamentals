package com.example.etasheva.spotifyapp.models;

import java.util.ArrayList;

public class Database {

    private ArrayList<Song> mSongs;

    public Database() {
        this.mSongs = new ArrayList<>();
        fillData();
    }

    public int getSongsCount(){
        return this.mSongs.size();
    }

    public void removeElementByPosition(int position){
        this.mSongs.remove(position);
    }

    public Song getElementByPosition(int postion){
        if (postion >= 0 && postion < this.mSongs.size()){
            return this.mSongs.get(postion);
        }
        return null;
    }

    private void fillData() {
        this.mSongs.add(new Song(1,"Mr. Probz", "Waves", "Non-album singles","waves","waves"));
        this.mSongs.add(new Song(2,"Duke Dumont", "Ocean Drive", "Common Culture, Vol. V","ocean_drive","ocean_drive"));
        this.mSongs.add(new Song(3,"Kwabs", "Walk", "Love + War","walk","walk"));
        this.mSongs.add(new Song(4,"Dua Lipa", "Be The One", "Dua Lipa","be_the_one","be_the_one"));
        this.mSongs.add(new Song(5,"OneRepublic", "Counting Stars", "Native","counting_stars","counting_stars"));
        this.mSongs.add(new Song(6,"OneRepublic", "Love Runs Out", "Native","love_runs_out","love_runs_out"));
        this.mSongs.add(new Song(7,"Years & Years", "Take Shelter", "Communion","take_shelter","take_shelter"));
        this.mSongs.add(new Song(8,"Kings Of Leon", "Use Somebody", "Only by the Night","use_somebody","use_somebody"));
        this.mSongs.add(new Song(9,"Daniel Powter", "Crazy all my life", "Turn On the Lights","crazy_all_my_life","crazy_all_my_life"));
        this.mSongs.add(new Song(10,"David Guetta", "Without You", "Nothing but the Beat","without_you","without_you"));
        this.mSongs.add(new Song(11,"Naughty Boy ft Sam Smith", "La La La", "Hotel Cabana","la_la_la","la_la_la"));
        this.mSongs.add(new Song(12,"Sean Finn feat. Tinka", "Summer Days", "Non-album singles","summer_days","summer_days"));
    }
}
