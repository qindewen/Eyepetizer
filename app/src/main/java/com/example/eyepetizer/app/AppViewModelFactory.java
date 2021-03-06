package com.example.eyepetizer.app;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.eyepetizer.data.BaseRepository;
import com.example.eyepetizer.ui.discover.DiscoverViewModel;
import com.example.eyepetizer.ui.home.HomeViewModel;
import com.example.eyepetizer.ui.hot.HotViewModel;
import com.example.eyepetizer.ui.main.MainViewModel;
import com.example.eyepetizer.ui.mine.PersonViewModel;

/**
 * Created by goldze on 2019/3/26.
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final BaseRepository mRepository;

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application, Injection.provideDemoRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private AppViewModelFactory(Application application, BaseRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(HotViewModel.class)) {
            return (T) new HotViewModel(mApplication, mRepository);
        }else if (modelClass.isAssignableFrom(DiscoverViewModel.class)) {
            return (T) new DiscoverViewModel(mApplication, mRepository);
        }else if (modelClass.isAssignableFrom(PersonViewModel.class)) {
            return (T) new PersonViewModel(mApplication, mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
