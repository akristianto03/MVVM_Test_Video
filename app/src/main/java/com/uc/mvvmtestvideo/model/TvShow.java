package com.uc.mvvmtestvideo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.uc.mvvmtestvideo.util.Constants;

public class TvShow implements Parcelable {

    @SerializedName("id")
    private String idTv;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String cover;

    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String description;

    @SerializedName("release_date")
    private String releaseDate;

    public TvShow() {

    }

    public TvShow(String idTv, String popularity, String poster, String cover, String title, String description, String releaseDate) {
        this.idTv = idTv;
        this.popularity = popularity;
        this.poster = poster;
        this.cover = cover;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    protected TvShow(Parcel in) {
        idTv = in.readString();
        popularity = in.readString();
        poster = in.readString();
        cover = in.readString();
        title = in.readString();
        description = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getIdTv() {
        return idTv;
    }

    public void setIdTv(String idTv) {
        this.idTv = idTv;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return Constants.IMG_URL + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCover() {
        return Constants.IMG_URL + cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idTv);
        dest.writeString(popularity);
        dest.writeString(poster);
        dest.writeString(cover);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(releaseDate);
    }
}
