package fetl.k.somporn.sompornqrcode;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by somporn.k on 17/05/17.
 */

public class PostData extends AsyncTask<String,Void, String>{

    private Context context;

    public PostData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {


        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", params[0])
                    .add("User", params[1])
                    .add("Password", params[2])
                    .build();

            MyContant myConstatnt = new MyContant();
            String urlPHP = myConstatnt.getUrlPostUser();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d("17MayV1", "e doin ==>" + e.toString());
            return null;
        } // check error by ตัวแปล
          }// ทำงานเบื้องหลัง
} //mainclass
