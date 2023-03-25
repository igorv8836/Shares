package com.example.shares;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.shares.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Disposable disposable;
    ArrayList<SharesItem> array;
    SharesAPI sharesAPI;
    Retrofit retrofit;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        array = new ArrayList<>();


        retrofit = new Retrofit.Builder()
                .baseUrl(SharesAPI.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        sharesAPI = retrofit.create(SharesAPI.class);
        func();

    }

    private void func(){
        disposable = sharesAPI.getShare(SharesAPI.NAME[i], SharesAPI.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError, this::onComplete, this::onSubscribe);
        i++;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private void onSubscribe(Disposable d) {
    }

    private void onComplete() {

    }

    private void onSuccess(SharesItem response) {

        array.add(response);
        if (array.size() == 10) {
            SharesItemAdapter adapter =
                    new SharesItemAdapter(array);
            binding.recyclerView
                    .setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerView.setAdapter(adapter);
            binding.progressBar.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
        }else{
            func();
        }
    }

    private void onError(Throwable t) {
        binding.textView.setText(t.toString());
        if (t instanceof UnknownHostException){
            binding.textView.setText("Нет интернет-подключения или ошибка на стороне провайдера!");
        }
        Log.wtf("MyError", t.toString());
        binding.progressBar.setVisibility(View.GONE);
        binding.textView.setVisibility(View.VISIBLE);
    }
}