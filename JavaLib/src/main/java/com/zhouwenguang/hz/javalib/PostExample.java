package com.zhouwenguang.hz.javalib;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
    public class PostExample {
        public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        void post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            MultipartBody multipartBody=new MultipartBody.Builder()
                    .addFormDataPart("image", "logo-square.png",
                            RequestBody.create(MediaType.parse("image/png"), new File("website/static/logo-square.png")))
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String a=response.toString();
                    System.out.println(a);
                }
            });

        }

        String bowlingJson(String player1, String player2) {
            return "{'winCondition':'HIGH_SCORE',"
                    + "'name':'Bowling',"
                    + "'round':4,"
                    + "'lastSaved':1367702411696,"
                    + "'dateStarted':1367702378785,"
                    + "'players':["
                    + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                    + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                    + "]}";
        }

        public static void main(String[] args) throws IOException {
            PostExample example = new PostExample();
            String json = example.bowlingJson("Jesse", "Jake");
             example.post("http://www.roundsapp.com/post", json);
        }
    }