package fetl.k.somporn.sompornqrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //explicite ประกาศตัวแปล
    private EditText userEditText, passEditText;
    private TextView textView;
    private Button button;
    private  String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View is การผูกความสัมพัน ตัวแปล กับ in wiew
        initialView();
        //controller
        controller();

    }  // main method

    private void controller() {
        textView.setOnClickListener(MainActivity.this);
        button.setOnClickListener(MainActivity.this);
    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.editUser);
        passEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtNewRegis);
        button = (Button) findViewById(R.id.btnLogin);

    }

    @Override
    public void onClick(View v) {

        //For textview
        if (v == textView) {
            //Intent to Register
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);

        }
       //for buttom
        if (v ==button) {

            //get value from edit text
            userString = userEditText.getText().toString().trim();
            passwordString = passEditText.getText().toString().trim();
             //check spapce
            if (userString.equals("") || passwordString.equals("")) {
                //have space
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDiaLog(getResources().getString(R.string.title_HaveSpace),
                        getResources().getString(R.string.message_HaveSpace));
            } else {
                //no space
                checkUserAnPass();


            }


        }

    }

    private void checkUserAnPass() {

        try {

            GetData getData = new GetData(this);
            MyConstatnt myConstant = new MyConstatnt();
            getData.execute(myConstant.getUrlGetUser());
            String strJSCN = getData.get();
            Log.d("17MayV2", "JSCN ==> " + strJSCN);
          //  showMessage(strJSCN);

            JSONArray jsonArray = new JSONArray(strJSCN);
            boolean b = true; // user fail
            String strName = null, strPassword = null;
            MyAlert myAlert = new MyAlert(this);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObj.getString("User"))) {
                    b = false;
                    strName = jsonObj.getString("Name");
                    strPassword = jsonObj.getString("Password");
                }
            } // for

            if (b) {
                //User False
                myAlert.myDiaLog(getResources().getString(R.string.title_UserFalse),
                        getResources().getString(R.string.message_UserFalse));

            } else if (passwordString.equals(strPassword)) {
                    // Password true
                Toast.makeText(MainActivity.this, "Welcome" + strName, Toast.LENGTH_SHORT).show();

                //intent to service
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                intent.putExtra("Login", strName); // putnใส่ค่า
                startActivity(intent);
                finish();
            } else {
                myAlert.myDiaLog(getResources().getString(R.string.tiltle_Password_fasle),
                        getResources().getString(R.string.message_Passwordfalse));
            }

        } catch (Exception e) {
            Log.d("17MayV2", "e checkUser ==> " + e.toString());
        }
    }

    private void showMessage(String strJSCN) {
       Toast.makeText(MainActivity.this, strJSCN, Toast.LENGTH_SHORT).show();
    }


}   // Main Class คลาสหลัก
