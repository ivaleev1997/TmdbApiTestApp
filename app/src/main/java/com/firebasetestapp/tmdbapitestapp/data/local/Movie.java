package com.firebasetestapp.tmdbapitestapp.data.local;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
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
        return posterPath;
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
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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
