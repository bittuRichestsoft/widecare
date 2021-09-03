package Constant;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.widecare.indiaweb.myapplication.R;


/**
 * Created by indiaweb on 10/15/2016.
 */
public class CustomDialogClass extends Activity implements View.OnClickListener
{
    public Activity c;
    public Dialog d;
    public Button yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.con);
        no = (Button) findViewById(R.id.cart);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

//    public CustomDialogClass(Activity a) {
//        super(a);
//        // TODO Auto-generated constructor stub
//        this.c = a;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.con:
                c.finish();
                break;
            case R.id.cart:
                d.dismiss();
                break;
            default:
                break;
        }
        d.dismiss();
    }
}
