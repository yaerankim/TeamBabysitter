package com.example.firebaseemailaccount;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends Fragment {
    private View view;
    private String TAG = "MyPageFragment";
    Call<UserAccount> call;
    Button update_button;
    TextView nickname_view, baby_birthday_view, baby_gender_view;

    public static MyPageActivity newInstance() {
        return new MyPageActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Log.i(TAG,"onCreateView");

        view = inflater.inflate(R.layout.activity_mypage,container,false);

        update_button = view.findViewById(R.id.update_button);
        nickname_view = view.findViewById(R.id.nickname_view);
        baby_birthday_view = view.findViewById(R.id.baby_birthday_view);
        baby_gender_view = view.findViewById(R.id.baby_gender_view);

        // 서버에서는 user/view/{id}로 번호 지정해서 user 계정 정보 가져오는 것과
        // 로그인되었을 때에만 접근되는 것도 다 구현됐는데 막상 클라이언트에서 호출하면
        // 다른 activity로 이동하면 로그인이 다시 풀려서 로그인을 했음에도 불구하고 다른 카테고리에 접근이 제대로 되지 않는 상황
        // Intent intent=getActivity().getIntent();
        // id = intent.getIntExtra("id", 1);
        // String c_email = intent.getStringExtra("email");
        // SharedPreferences sharedPref = getSharedPreferences("user_cookie", Context.MODE_PRIVATE);
        String login_cookie = LoginActivity.sharedPref.getString("cookie", "abcd");
        call = Retrofit_client.getUserApiService().user_view(login_cookie);
        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.isSuccessful()) {
                    UserAccount result = response.body();
                    nickname_view.setText(result.getNickname());
                    baby_birthday_view.setText(result.getBabyBirthday());
                    baby_gender_view.setText(result.getBabyGender());
                }
            }
            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {

            }
        });

        // '프로필 편집' 버튼 클릭 시 수정 화면으로 전환
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PageActivity)getActivity()).replaceFragment(MyPageUpdateActivity.newInstance());
            }
        });

        return view;
    }
}

