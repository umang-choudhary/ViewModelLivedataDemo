package com.demo.viewmodellivedata;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final String TAG = this.getClass().getSimpleName();
    private MutableLiveData<String> myRandomNumber;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getNumber() {
        Log.i(TAG, "Get number");
        if (myRandomNumber == null) {
            myRandomNumber = new MutableLiveData<String>();
            createNumber();
        }
        return myRandomNumber;
    }

    private void createNumber() {
        Log.i(TAG, "Create new number");

        executor.execute(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                Random random = new Random();
                myRandomNumber.setValue("Number: " + (random.nextInt(100 - 1) + 1));
            });
        });

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }
}
