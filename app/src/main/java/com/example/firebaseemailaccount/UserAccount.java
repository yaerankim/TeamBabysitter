package com.example.firebaseemailaccount;

//사용자 계정 정보 모델 클래스
public class UserAccount
{
    private String idToken;  //Firebase Uid (고유 토큰 정보)
    private String emailId;  //이메일 아이디
    private String password;  //비밀번호

    public UserAccount() { } //파이어베이스 리얼타임데이터베이스를 쓸 때에는 모델 클래스를 이용해서 가지고와야 할 때 꼭 명시해줘야함

    public String getIdToken(){ return idToken; }

    public void setIdToken(String idToken) {this.idToken = idToken;}

    public String getEmailId() {return emailId;}

    public void  setEmailId(String emailId) {this.emailId = emailId;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}


}
