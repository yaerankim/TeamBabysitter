package com.example.firebaseemailaccount;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    // TextView textView;
    EditText title_text, content_text;
    Button register_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_test);


        title_text = findViewById(R.id.title_text);
        content_text = findViewById(R.id.content_text);
        register_button = findViewById(R.id.register_button);

        // title_text.setText("");
        // content_text.setText("");

        register_button.setOnClickListener(view -> {
            Data_model post = new Data_model(title_text.getText().toString(), content_text.getText().toString());
            call = Retrofit_client.getApiService().community_post(post);

            call.enqueue(new Callback<Data_model>() {
                //콜백 받는 부분
                @Override
                public void onResponse(Call<Data_model> call, Response<Data_model> response) {
                    Data_model result = response.body();
                }

                @Override
                public void onFailure(Call<Data_model> call, Throwable t) {

                }
            });
        });
    }
}

