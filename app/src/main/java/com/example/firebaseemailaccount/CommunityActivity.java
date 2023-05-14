


package com.example.firebaseemailaccount;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityActivity extends Fragment {
    private View view;
    private String TAG = "CommunityFragment";
    Call<Community_model> call;
    EditText title_et, content_et;
    Button reg_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Log.i(TAG,"onCreateView");

        // ----------------------------------- 게시글 upload -----------------------------------
        view = inflater.inflate(R.layout.activity_upload,container,false);

        title_et = view.findViewById(R.id.title_et);
        content_et = view.findViewById(R.id.content_et);
        reg_button = view.findViewById(R.id.reg_button);

        // reg_button을 '누르면'
        // 그 때 title과 content 값 설정하도록 수정
        reg_button.setOnClickListener(view -> {
            Community_model post = new Community_model(title_et.getText().toString(), content_et.getText().toString());
            call = Retrofit_client.getApiService().community_post(post);
            call.enqueue(new Callback<Community_model>() {
                //콜백 받는 부분
                @Override
                public void onResponse(Call<Community_model> call, Response<Community_model> response) {
                    // Community_model result = response.body();
                    if(response.isSuccessful()){
                        Toast.makeText(getContext(), "Saved successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(), "Request failed", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<Community_model> call, Throwable t) {
                    Toast.makeText(getContext(), "Request failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        return view;
    }
}
