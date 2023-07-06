package com.lx.community.provider;

import com.alibaba.fastjson2.JSON;
import com.lx.community.dto.AccessTokenDto;
import com.lx.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class GithubProvider {
    //读取超时为60s
    private static final long READ_TIMEOUT = 6000000;
    //写入超时为60s
    private static final long WRITE_TIMEOUT = 6000000;
    //连接超时为60s
    private static final long CONNECT_TIMEOUT = 6000000;

    public String getAccessToken(AccessTokenDto accessTokenDto)
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

        final MediaType mediaType = MediaType.get("application/json");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str=  response.body().string();
            String[] split = str.split("&");
            String token =split[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public GithubUser getUser(String accessToken)
    {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .header("Authorization" , "token " + accessToken)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String userString = response.body().string();
            GithubUser githubUser = JSON.parseObject(userString, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
