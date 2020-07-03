package com.icandothisallday2020.ex33thread_networkimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//////////
        iv=findViewById(R.id.iv);
    }

    public void click(View view) {
        //네트워크 작업시 반드시 사용허가(permission)를 받아야함
        //Network 상에 있는 Image 읽어와 ImageView 에 보여주기
        //Android 에서 MainThread:Network 작업 불가
        // ∴ Network 작업을 하는 별도의 Thread 생성
        new Thread(){
            @Override
            public void run() {
                //Network 상의 이미지 주소 전달
                String imgUrl="https://m.donggangmaru.com/file_data/ywnh/2016/10/10/e1bd25f75242c1b493bf49a650179217.jpg";
                String imgUrl2="https://lh3.googleusercontent.com/proxy/3eLTU1pcemIwRX-pYfBwZc2M5Gf0bPLi-d-kFfBM4Rl93f4_39_iSrAKrpRcxZAeCBQXlJCjtjsmRi4XLH-kkatw5-GxrNrC-sZ7G9gE1zE";                String imgUrl3="https://sikdorak365.co.kr/web/upload/NNEditor/20190523/dduk01.jpg";                String imgUrl4="https://www.dailysecu.com/news/photo/201908/62670_53668_1127.jpg";
                //파일까지 연결되는 무지개로드(Stream)을 만들어주는 해임달(URL)객체 생성
                try {
                    URL url=new URL(imgUrl2);
                    InputStream is=url.openStream();//해임달에게 무지개로드 열어달라고 요청
                    //무지개 로드를 통해 이미지 데이터를 읽어와
                    //안드로이드 이미지 관리객체인 Bitmap 객체로 만들고 이미지뷰에 설정
                    final Bitmap bm= BitmapFactory.decodeStream(is);//static method/decode:해독하다
                    //별도의 스레드는 Activity 화면을 변경할 수 없음
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bm);
                        }
                    });
                } catch (MalformedURLException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
            }
        }.start();
    }
}
