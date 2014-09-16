package com.example.shiraki_hirotomo.trainschedule;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by shiraki-hirotomo on 2014/09/15.
 */
public class HttpAsyncLoader extends AsyncTaskLoader<String> {
    private String url = null; // WebAPIのURL

    public HttpAsyncLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public String loadInBackground() {
        // ここでWebAPIの呼び出し（HTTP通信）を行う    HttpClient httpClient = new DefaultHttpClient();

        HttpClient httpClient = new DefaultHttpClient();

        try {
            String responseBody = httpClient.execute(new HttpGet(this.url), // （1）

                    // UTF-8でデコードするためhandleResponseをオーバーライドする （2）
                    new ResponseHandler<String>() {

                        @Override
                        public String handleResponse(HttpResponse response)
                                throws ClientProtocolException, IOException {

                            // レスポンスコードが、
                            // HttpStatus.SC_OK（HTTP 200）の場合のみ、結果を返す
                            if (HttpStatus.SC_OK ==
                                    response.getStatusLine().getStatusCode()){
                                return EntityUtils.toString(
                                        response.getEntity(), "UTF-8"); // （3）
                            }
                            return null;
                        }
                    });

            return responseBody;
        }
        catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage());
        }
        finally {
            // 通信終了時は、接続を閉じる （4）
            httpClient.getConnectionManager().shutdown();
        }
        return null;
    }
}
