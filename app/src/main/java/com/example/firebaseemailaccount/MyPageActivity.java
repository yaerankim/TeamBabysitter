package com.example.firebaseemailaccount;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity { // Fragment
//    private View view;
//    private String TAG = "MyPageFragment";
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//        Log.i(TAG,"onCreateView");
//
//        view=inflater.inflate(R.layout.activity_login,container,false);
//        return view;
//    }

    Call<Data_model> call;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_test);


        textView = findViewById(R.id.textView2);

        call = Retrofit_client.getApiService().test_api_get(1);
        call.enqueue(new Callback<Data_model>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<Data_model> call, Response<Data_model> response) {
                Data_model result = response.body();
                String str;
                str = result.getId() + "\n" +
                        result.getTitle() + "\n" +
                        result.getContent() + "\n" +
                        result.getViewCount();
                textView.setText(str);
            }

            @Override
            public void onFailure(Call<Data_model> call, Throwable t) {

            }
        });


    }
}

