package fetl.k.somporn.sompornqrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import static fetl.k.somporn.sompornqrcode.R.id.livProduct;

public class ServiceActivity extends AppCompatActivity {

    //Explicite
    private TextView textView;
    private ImageView barImageView, qrImageView;
    private ListView listView;
    private String nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        //Initial View
        initialView();
        //Show Text
        showText();

        //Create ListView
        createListView();
    }

    private void createListView() {

        try {
            MyContant myContant = new MyContant();
            String urlJSDN = myContant.getUrlGetProduct();

            GetData getdata = new GetData(this);
            getdata.execute(urlJSDN);
            String strJSDN = getdata.get();
            Log.d("18MayV1", "JSDN ==>" + strJSDN);
            JSONArray jsonArray = new JSONArray(strJSDN);
            int i = jsonArray.length();
            String[] iconStrings = new String[i];
            String[] titleStrings = new String[i];
            String[] detailStrings = new String[i];
            for (int i1 =0; i1 < i;i1++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i1);
                iconStrings[i1] = jsonObject.getString("Image");
                titleStrings[i1] = jsonObject.getString("Produce");
                detailStrings[i1] = jsonObject.getString("Detail");

            } // for

            MyAdapter myAdapter = new MyAdapter(this, iconStrings, titleStrings, detailStrings);
            listView.setAdapter(myAdapter);


        } catch (Exception e) {
            Log.d("18MayV1", "e createListView ==> " + e.toString());

        }
    }

    private void showText() {
        nameString = getIntent().getStringExtra("Login");
        textView.setText(nameString);
    }

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtNameLogin);
        barImageView = (ImageView) findViewById(R.id.imvBarCode);
        qrImageView = (ImageView) findViewById(R.id.imvQrCode);
        listView = (ListView) findViewById(R.id.livProduct);
    }
}
