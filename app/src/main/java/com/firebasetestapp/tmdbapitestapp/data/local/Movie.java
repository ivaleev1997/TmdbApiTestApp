package com.firebasetestapp.tmdbapitestapp.data.local;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebasetestapp.tmdbapitestapp.AppConstants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = "id")
public class Movie {

    private int id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName(value="header", alternate={"title", "name"})
    private String header;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName(value="description", alternate={"overview", "synopsis"})
    private String description;

    @SerializedName("release_date")
    private String releaseDate;

    public Movie(int id, String originalTitle, String header, String posterPath, String description, String releaseDate) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.header = header;
        this.posterPath = posterPath;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPosterPath() {
        return AppConstants.TMDB_POSTER_BASE_URL + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        String[] date = releaseDate.split("-"); //1995-02-21 ---> 21.02.95
        StringBuilder result = new StringBuilder();

        if (date.length == 3) {
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(date));
            Collections.reverse(arrayList);
            for (String s: arrayList) {
                if (arrayList.indexOf(s) == arrayList.size() - 1) {
                    s = s.substring(2);
                    result.append(s);
                } else {
                    result.append(s);
                    result.append(".");
                }
            }
        } else result.append(releaseDate);

        //System.out.println(result.toString());
        return result.toString();
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Ignore
    @BindingAdapter("glideImageLoad")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView).load(url).into(imageView);
    }

    @Ignore
    public static DiffUtil.ItemCallback<Movie> DIFF_MOVIE = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.header.equals(newItem.header)
                    && oldItem.releaseDate.equals(newItem.releaseDate)
                    && oldItem.originalTitle.equals(newItem.originalTitle);
        }
    };
}
