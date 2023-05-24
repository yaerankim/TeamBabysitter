package com.example.firebaseemailaccount;

import static com.example.firebaseemailaccount.R.id.bottomNav;
import static com.example.firebaseemailaccount.Retrofit_client.BASE_URL; // 추가
import static com.example.firebaseemailaccount.Retrofit_client.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
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

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Request;
// import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public int id;
    Call<UserAccount> call;
    private EditText et_email, et_pwd; //로그인 입력필드
    private Button btn_login; // 로그인 버튼

    public static SharedPreferences sharedPref;


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
                    // Call<UserAccount> cookie = Retrofit_client.getUserApiService().getCookie(user);

                    // HTTP 요청 보내기
                    // Request request = new Request.Builder()
                    //         .url(BASE_URL) // 요청할 URL 설정
                    //         .build();

                    call.enqueue(new Callback<UserAccount>() {
                        //콜백 받는 부분
                        @Override
                        public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                            if(response.isSuccessful()){
                                // Response res = client.newCall(request).execute();
                                // UserAccount result = response.body(); // 애초에 user/login/의 response에 token 값밖에 없으므로 result.getEmail() 해봤자 null만 나옴. user.getEmail()이 맞음.
                                // id = user.getId(); // 계속 0만 나옴
                                // HashSet<String> cookies = new Cookie_jar().loadForRequest(response.raw().request().url());
                                // List<Cookie> cookies = new Cookie_jar().loadForRequest(HttpUrl.parse(BASE_URL + "user/login/"));

                                // for (Cookie cookie : cookies) {
                                //     if (cookie.name().equals("jwt")) {
                                //         String token = cookie.value();
                                //         new Cookie_jar().saveFromResponse(HttpUrl.parse(BASE_URL + "user/login/"), cookies);
                                //     }
                                // }

                                // Store the cookies in the CookieJar for subsequent requests
                                // new Cookie_jar().saveFromResponse(HttpUrl.parse(BASE_URL + "user/login/"), cookies);
                                // cookies.isEmpty() // true 나옴(cookie값 제대로 못 받아옴)

                                // 서버에서 설정해놓은 AUTH_HEADER_NAME으로 설정해서 header 값 받아오기
                                // request.META.get('HTTP_AUTHORIZATION');

                                // ---------------- SharedPreferences 저장 방식 ----------------
                                // user_cookie란 이름의 파일에 SharedPreferences 저장소(공유 환경설정 파일) 생성?
                                // SharedPreferences sharedPref = getSharedPreferences("user_cookie", Context.MODE_PRIVATE);
                                // 초기화 느낌?
                                // Set<String> cookies = sharedPref.getStringSet("cookie", new HashSet<String>());
                                // SharedPreferences.Editor editor = sharedPref.edit();
                                // 해당 값 지정하여 전달
                                // 첫번째 이름의 변수에 두번째 인수 값을 저장하여 공유 환경설정 파일에 저장 -> putInt 혹은 putString 사용
                                // 서버로부터 cookies 받아와서 저장해야 함
                                // editor.putStringSet("cookie", cookies);
                                // editor.apply(); // 실제로 저장

                                // ---------------- 로그인 후 받아온 데이터 확인해보기 ----------------
                                // Log() 사용하여 test 삼아 확인
                                Log.v("login-test", "jwtToken:" + response.body().getJwt());

                                // ---------------- SharedPreferences에 저장하기 ----------------
                                // 공유 환경설정 파일 이름 지정
                                sharedPref = getSharedPreferences("user_cookie", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("cookie", response.body().getJwt());
                                editor.apply();

                                // ---------------- (test)SharedPreferences에 저장한 것 읽어와보기 ----------------
                                String test_cookie = sharedPref.getString("cookie", "abcd");

                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_LONG).show();
                                String email = user.getEmail(); // 위에서 바로 지정했으므로 제대로 나옴
                                Intent intent = new Intent(LoginActivity.this, PageActivity.class);
                                intent.putExtra("email", email); // 로그인된 사용자의 email 전달(id는 계속 0만 나와서 사용 불가능)
                                startActivity(intent); // 화면 전환
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

