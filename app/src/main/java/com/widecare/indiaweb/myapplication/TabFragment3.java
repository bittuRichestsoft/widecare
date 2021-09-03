package com.widecare.indiaweb.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import Constant.Shaved_shared_preferences;

/**
 * Created by indiaweb on 1/24/2017.
 */
public class TabFragment3 extends Fragment {
    TextView subitname;
    TextView textView;
    View rootview;
    LinearLayout initmateSubmit;
    String claim_id="";
    Shaved_shared_preferences shaved_shared_preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.tab_fragment_3, container, false);

        subitname=(TextView)rootview.findViewById(R.id.subitname);
        textView=(TextView)rootview.findViewById(R.id.textView);
        initmateSubmit = (LinearLayout)rootview.findViewById(R.id.initmatedone);

        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());

        subitname.setText(shaved_shared_preferences.get_user_fname());

        Log.e("set_process", "---" + shaved_shared_preferences.get_initmate_process());

        claim_id =  shaved_shared_preferences.get_claimId();

        if(shaved_shared_preferences.get_initmate_process()==1){
            textView.setText("\nThanks for submitting the claim details.\n\n" +
                    "Your claim has been successfully registered and Claim No."+claim_id+" has been generated.\n"+getString(R.string.submit_text));
        }
        else {
            textView.setText("Please fill all details to claim!!");
        }

        initmateSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NavigationActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });



        return  rootview;
    }
}