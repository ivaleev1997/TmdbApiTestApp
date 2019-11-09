package com.firebasetestapp.tmdbapitestapp.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.firebasetestapp.tmdbapitestapp.data.Status.ERROR;
import static com.firebasetestapp.tmdbapitestapp.data.Status.LOADING;
import static com.firebasetestapp.tmdbapitestapp.data.Status.SUCCESS;

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public boolean isSuccess() {
        return status == SUCCESS;
    }

    public boolean isLoading() {
        return status == LOADING;
    }

    public boolean isLoaded() {
        return status != LOADING;
    }

    public boolean isError() {
        return status == ERROR;
    }
}
