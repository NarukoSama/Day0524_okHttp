package com.bwie.day0524_okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //get同步请求
    public void okHttpGetSyn(View v) {


        executorService.execute(new Runnable() {
            @Override
            public void run() {


                try {
                    OkHttpClient client = new OkHttpClient();

                    Request requst = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

                    Response execute = client.newCall(requst).execute();//执行请求

                    System.out.println(execute.body().string());

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    //get异步请求
    public void okHttpGetASyn(View v) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {


                try {
                    OkHttpClient client = new OkHttpClient();

                    Request requst = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

                    client.newCall(requst).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println(response.body().string());
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    //post同步
    public void okHttpPostSyn(View v) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {


                try {
                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder().add("username", "123456789").add("password", "123456").add("postkey", "1503d").build();

                    Request requst = new Request.Builder().url("http://qhb.2dyt.com/Bwei/login").post(requestBody).build();

                    Response execute = client.newCall(requst).execute();//执行请求

                    System.out.println("=======================" + execute.body().string());

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    //post异步
    public void okHttpPostASyn(View v) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {


                try {
                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder().add("username", "123456789").add("password", "123456").add("postkey", "1503d").build();

                    Request requst = new Request.Builder().url("http://qhb.2dyt.com/Bwei/login").post(requestBody).build();

                    client.newCall(requst).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println("=======================" + response.body().string());

                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
