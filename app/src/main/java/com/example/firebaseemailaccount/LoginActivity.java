package com.example.firebaseemailaccount;

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

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;   //파이어베이스 인증처리하는 부분
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd; //로그인 입력필드
    BottomNavigationView buttonview = findViewById(R.id.bottomNav);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //buttonview = findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new HomeActivity()).commit();

// BottomNavigationView 선택 이벤트 처리
        buttonview.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeActivity()).commit();
                    break;
                case R.id.map:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MapActivity()).commit();
                    break;
                case R.id.community:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CommunityActivity()).commit();
                    break;
                case R.id.diary:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new DiaryActivity()).commit();
                    break;
                case R.id.mypage:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MyPageActivity()).commit();
                    break;
            }

            mFirebaseAuth = FirebaseAuth.getInstance();
            mDatabaseRef = FirebaseDatabase.getInstance().getReference("GoingBaby");

            mEtEmail = findViewById(R.id.et_email);
            mEtPwd = findViewById(R.id.et_pwd);

            Button btn_login = findViewById(R.id.btn_login);
            btn_login.setOnClickListener(view -> {
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, PageActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            });

            Button btn_register = findViewById(R.id.btn_register);
            btn_register.setOnClickListener(view -> {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            });

            return true;
        });
    }
}

