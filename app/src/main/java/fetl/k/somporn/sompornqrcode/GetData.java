package fetl.k.somporn.sompornqrcode;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by somporn.k on 17/05/17.
 */

public class GetData extends AsyncTask<String , Void, String>{

    private Context context;

    public GetData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(params[0]).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("17MayV2", "e doin ==> " + e.toString());
            return null;
        }
        // show log alt+6


    }
} // Main Class
