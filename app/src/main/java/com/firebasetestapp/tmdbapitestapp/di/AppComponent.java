package com.firebasetestapp.tmdbapitestapp.di;

import android.app.Application;

import com.firebasetestapp.tmdbapitestapp.AppController;
import com.firebasetestapp.tmdbapitestapp.di.module.ActivityModule;
import com.firebasetestapp.tmdbapitestapp.di.module.ApiModule;
import com.firebasetestapp.tmdbapitestapp.di.module.AppModule;
import com.firebasetestapp.tmdbapitestapp.di.module.DbModule;
import com.firebasetestapp.tmdbapitestapp.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        DbModule.class,
        ActivityModule.class,
        ViewModelModule.class,
        ApiModule.class,
        AppModule.class,
        AndroidSupportInjectionModule.class
})
@Singleton
public interface AppComponent extends AndroidInjector<AppController> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
