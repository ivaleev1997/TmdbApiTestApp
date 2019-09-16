package com.firebasetestapp.tmdbapitestapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Movie.class, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    private static AppDb INSTANCE;

    public abstract MovieDao getMovieDao();

    public static AppDb getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDb.class) {
                INSTANCE = createAppDbInstance(context);
            }
        }

        return INSTANCE;
    }

    private static AppDb createAppDbInstance(Context context) {
        return Room.databaseBuilder(context, AppDb.class, "AppDb").build();
    }
}
