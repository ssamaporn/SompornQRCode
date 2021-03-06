package fetl.k.somporn.sompornqrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // Explicit
    private EditText nameEditText,userEditText, passwordEditText;
    private ImageView imageView;
    private Button button;
    private String nameString, userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initial View
        initialView();

        //controller
        controller();


    } // Main Method

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void controller() {
        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void initialView() {
        nameEditText = (EditText) findViewById(R.id.ediName);
        userEditText = (EditText) findViewById(R.id.editUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        imageView = (ImageView) findViewById(R.id.btnBack);
        button = (Button) findViewById(R.id.btnNewRegis);
    }

    @Override
    public void onClick(View v) {


        //For back
        if (v == imageView){
            finish();
        }

        // for bottom
        if (v == button){

            //get value form EdittText
            nameString = nameEditText.getText().toString().trim();
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            // Check Space
            if (nameString.equals("") || userString.equals("") || passwordString.equals(""))  {
                //have space
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDiaLog(getResources().getString(R.string.title_HaveSpace),
                        getResources().getString(R.string.message_HaveSpace));
            } else {
                //no space
                uploadValueToServer();
            }
        }

    }

    private void uploadValueToServer() {
        // trace to internet สามารถออก net
        try {

            PostData postData = new PostData(this);
            postData.execute(nameString, userString, passwordString);
            String strResult = postData.get();
            Log.d("17MayV1", "Result  ==>" + strResult);

            if (Boolean.parseBoolean(strResult)) {
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Canot Upload Value", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.d("17MayV1", "e upload ==>" + e.toString());

        }

    }
} // Main Class
