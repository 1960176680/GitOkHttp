package com.zhouwenguang.hz.javalib;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyClass {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        MyClass example = new MyClass();
        String response = example.run("http://weather.xcyh.org/101051301/json/6");
        System.out.println(response);
    }
}
