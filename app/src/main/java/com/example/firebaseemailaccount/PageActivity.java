package com.example.firebaseemailaccount;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PageActivity extends AppCompatActivity
{
    BottomNavigationView buttonview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //화면에 보여짐
        setContentView(R.layout.bottom_navigation_container);
        buttonview = findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new HomeActivity()).commit();

// BottomNavigationView 선택 이벤트 처리
        buttonview.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeActivity()).commit();
                    break;
                case R.id.map:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MapFragment()).commit();
                    MapFragment mapFragment = new MapFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.main_frame, mapFragment).commit();
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
            return true;
        });
    }
}
