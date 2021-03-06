package com.example.etasheva.kinveytest.models;

import android.os.Parcel;
import android.os.Parcelable;

public class LaptopSqlite implements Parcelable {

    private String model;
    private String capacity_ram;
    private String capacity_hdd;
    private String processor_type;
    private String video_card_type;
    private String display_size;
    private String currency;
    private String price;
    private String image;

    public LaptopSqlite(String model,
                  String capacity_ram,
                  String capacity_hdd,
                  String processor_type,
                  String video_card_type,
                  String display_size,
                  String currency,
                  String price,
                  String image) {
        this.model = model;
        this.capacity_ram = capacity_ram;
        this.capacity_hdd = capacity_hdd;
        this.processor_type = processor_type;
        this.video_card_type = video_card_type;
        this.display_size = display_size;
        this.currency = currency;
        this.price = price;
        this.image = image;
    }

    public LaptopSqlite(){

    }

    public LaptopSqlite(Parcel in) {
        model = in.readString();
        capacity_ram = in.readString();
        capacity_hdd = in.readString();
        processor_type = in.readString();
        video_card_type = in.readString();
        display_size = in.readString();
        currency = in.readString();
        price = in.readString();
        image = in.readString();
    }

    public static final Creator<LaptopSqlite> CREATOR = new Creator<LaptopSqlite>() {
        @Override
        public LaptopSqlite createFromParcel(Parcel in) {
            return new LaptopSqlite(in);
        }

        @Override
        public LaptopSqlite[] newArray(int size) {
            return new LaptopSqlite[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.model);
        dest.writeString(this.capacity_ram);
        dest.writeString(this.capacity_hdd);
        dest.writeString(this.processor_type);
        dest.writeString(this.video_card_type);
        dest.writeString(this.display_size);
        dest.writeString(this.currency);
        dest.writeString(this.price);
        dest.writeString(this.image);

    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCapacity_ram() {
        return capacity_ram;
    }

    public void setCapacity_ram(String capacity_ram) {
        this.capacity_ram = capacity_ram;
    }

    public String getCapacity_hdd() {
        return capacity_hdd;
    }

    public void setCapacity_hdd(String capacity_hdd) {
        this.capacity_hdd = capacity_hdd;
    }

    public String getProcessor_type() {
        return processor_type;
    }

    public void setProcessor_type(String processor_type) {
        this.processor_type = processor_type;
    }

    public String getVideo_card_type() {
        return video_card_type;
    }

    public void setVideo_card_type(String video_card_type) {
        this.video_card_type = video_card_type;
    }

    public String getDisplay_size() {
        return display_size;
    }

    public void setDisplay_size(String display_size) {
        this.display_size = display_size;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
