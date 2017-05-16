package fetl.k.somporn.sompornqrcode;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by somporn.k on 16/05/17.
 */

public class MyAlert {
    //Explicite
    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void myDiaLog(String strTitle, String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_user);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.show();

    } //Alert over activity
} //Main Class
