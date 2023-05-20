package com.example.firebaseemailaccount;

import static com.example.firebaseemailaccount.R.id.bottomNav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Call<UserAccount> call;
    private EditText et_email, et_pwd; //로그인 입력필드
    private Button btn_login; // 로그인 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


            et_email = findViewById(R.id.et_email);
            et_pwd = findViewById(R.id.et_pwd);
            btn_login = findViewById(R.id.btn_login);

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAccount user = new UserAccount(et_email.getText().toString(), et_pwd.getText().toString());
                    call = Retrofit_client.getUserApiService().user_login(user);

                    call.enqueue(new Callback<UserAccount>() {
                        //콜백 받는 부분
                        @Override
                        public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, PageActivity.class);
                                startActivity(intent); // 화면 전환
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<UserAccount> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
                        }
                    });
                }

//                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Intent intent = new Intent(LoginActivity.this, PageActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            });

            Button btn_register = findViewById(R.id.btn_register);
            btn_register.setOnClickListener(view -> {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            });


    }
}

