package com.example.firebaseemailaccount;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends Fragment {

    // 로그에 사용할 TAG 변수
    final private String TAG = getClass().getSimpleName();

    Call<Community_model> call;

    // 사용할 컴포넌트 선언
    ListView listView;
    ListViewAdapter adapter;
    ListViewItem listViewitem;
    Button reg_button;
    String userid = "";
    Community_model cm;
    int i;
    public int post_id;

    // 리스트뷰에 사용할 제목 배열
    ArrayList<String> titleList = new ArrayList<>();

    // 클릭했을 때 어떤 게시물을 클릭했는지 게시물 번호를 담기 위한 배열
    ArrayList<String> seqList = new ArrayList<>();

    public static ListActivity newInstance() {
        return new ListActivity();
    }

    // ----------------------------------- 게시글 전체 list view -----------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View view = inflater.inflate(R.layout.activity_list, null); // Fragment로 불러올 xml파일을 view로 가져옴
        adapter = new ListViewAdapter();

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        reg_button = view.findViewById(R.id.reg_button);

        // for(i=1;i<=adapter.getCount();i++) { // addItem() 이후에 저장된 데이터들에 대한 개수를 반환해주기 때문에 이 위치에서 제대로 동작X
        // 일단은 게시물 목록 중 특정 개수만 list up
        // for(i=1;i<=result.getRowCount();i++) {
        for(i=1;i<=4;i++) {
            call = Retrofit_client.getApiService().community_detail_get(i);
            call.enqueue(new Callback<Community_model>() {
                @Override
                public void onResponse(Call<Community_model> call, Response<Community_model> response) {
                    Community_model result = response.body();
                    // titleList.add(result.getTitle());
                    adapter.addItem(result.getTitle(), result.getContent());
                    adapter.notifyDataSetChanged(); // 꼭 반영해줘야 list에 제대로 addItem됨
                }

                @Override
                public void onFailure(Call<Community_model> call, Throwable t) {

                }
            });
        }

        // listView의 항목 중 하나 클릭 시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 클릭한 값 관련 Toast 뿌림
                // adapterView.getItemAtPosition(i)+
                // Integer.toString(i)+" 클릭"으로 출력해봤을 때 각 데이터의 id값과 i 불일치
                // Long.toString(adapter.getItemId(i))+ 으로도 불일치
                // (String)adapterView.getAdapter().getItem(i)+ cast 불가능 에러 발생
                // adapter.getCount()
                // Toast.makeText(getContext(), result.getRowCount()+" 클릭", Toast.LENGTH_SHORT).show();
                // 클릭한 해당 게시물로 화면 전환 + 해당 게시물 id값 전달
                // CommunityViewActivity cva = new CommunityViewActivity();
                // cva.postId((int)adapter.getItemId(i)); // https://almost-native.tistory.com/368 참고해서 수정해보기
                ((PageActivity)getActivity()).replaceFragment(CommunityViewActivity.newInstance());
            }
        });

        // 게시글 등록 버튼 클릭 시
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PageActivity)getActivity()).replaceFragment(CommunityActivity.newInstance());
            }
        });

        return view;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
//
//// LoginActivity 에서 넘긴 userid 값 받기
//        userid = getIntent().getStringExtra("userid");
//
//// 컴포넌트 초기화
//        listView = findViewById(R.id.listView);
//
//// listView 를 클릭했을 때 이벤트 추가
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//// 어떤 값을 선택했는지 토스트를 뿌려줌
//                Toast.makeText(ListActivity.this, adapterView.getItemAtPosition(i)+ " 클릭", Toast.LENGTH_SHORT).show();
//
//// 게시물의 번호와 userid를 가지고 DetailActivity 로 이동
//                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
//                intent.putExtra("board_seq", seqList.get(i));
//                intent.putExtra("userid", userid);
//                startActivity(intent);
//
//            }
//        });
//
//// 버튼 컴포넌트 초기화
//        reg_button = findViewById(R.id.reg_button);
//
//// 버튼 이벤트 추가
//        reg_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//// userid 를 가지고 RegisterActivity 로 이동
//                Intent intent = new Intent(ListActivity.this, UploadActivity.class);
//                intent.putExtra("userid", userid);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//    // onResume() 은 해당 액티비티가 화면에 나타날 때 호출됨
//    @Override
//    protected void onResume() {
//        super.onResume();
//// 해당 액티비티가 활성화 될 때, 게시물 리스트를 불러오는 함수를 호출
//        GetBoard getBoard = new GetBoard();
//        getBoard.execute();
//    }
//
//
//    // 게시물 리스트를 읽어오는 함수
//    class GetBoard extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            Log.d(TAG, "onPreExecute");
//        }
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.d(TAG, "onPostExecute, " + result);
//
//// 배열들 초기화
//            titleList.clear();
//            seqList.clear();
//
//            try {
//
//// 결과물이 JSONArray 형태로 넘어오기 때문에 파싱
//                JSONArray jsonArray = new JSONArray(result);
//
//                for(int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    String title = jsonObject.optString("title");
//                    String seq = jsonObject.optString("seq");
//
//// title, seq 값을 변수로 받아서 배열에 추가
//                    titleList.add(title);
//                    seqList.add(seq);
//
//                }
//
//// ListView 에서 사용할 arrayAdapter를 생성하고, ListView 와 연결
//                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, titleList);
//                listView.setAdapter(arrayAdapter);
//
//// arrayAdapter의 데이터가 변경되었을때 새로고침
//                arrayAdapter.notifyDataSetChanged();
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//        @Override
//        protected String doInBackground(String... params) {
////
//// String userid = params[0];
//// String passwd = params[1];
//
//            String server_url = "http://15.164.252.136/load_board.php";
//
//
//            URL url;
//            String response = "";
//            try {
//                url = new URL(server_url);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000);
//                conn.setConnectTimeout(15000);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//                Uri.Builder builder = new Uri.Builder()
//                        .appendQueryParameter("userid", "");
//// .appendQueryParameter("passwd", passwd);
//                String query = builder.build().getEncodedQuery();
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(query);
//                writer.flush();
//                writer.close();
//                os.close();
//
//                conn.connect();
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//                    String line;
//                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    while ((line=br.readLine()) != null) {
//                        response+=line;
//                    }
//                }
//                else {
//                    response="";
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return response;
//        }
//    }
}
