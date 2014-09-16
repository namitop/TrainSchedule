package com.example.shiraki_hirotomo.trainschedule;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by shiraki-hirotomo on 2014/09/14.
 */
public class TokyuUtils {
    //「http://gihyo.jp/dev/serial/01/androidapp/0009」を参照
    public String doGet( String url )
    {
        try
        {
            HttpGet method = new HttpGet( url );

            DefaultHttpClient client = new DefaultHttpClient();

            // ヘッダを設定する
            method.setHeader( "Connection", "Keep-Alive" );

            HttpResponse response = client.execute( method );
            int status = response.getStatusLine().getStatusCode();
            if ( status != HttpStatus.SC_OK )
                throw new Exception( "" );

            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        catch ( Exception e )
        {
            return null;
        }
    }
}
