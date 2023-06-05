package com.demo.viewmodellivedata;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tvNumber);

        MainActivityViewModel model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<String> myRandomNumber = model.getNumber();
        mTextView.setText(myRandomNumber.getValue());

        myRandomNumber.observe(this, s -> mTextView.setText(s));

        Log.i(TAG, "Random Number Set");
    }

}
