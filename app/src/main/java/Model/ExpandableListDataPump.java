package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by indiaweb on 11/23/2016.
 */
public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> a = new ArrayList<String>();
        a.add("Validity of the device is from date of purchase and registration of policy for period of 1 or 2 year based on plan availed");

        List<String> b = new ArrayList<String>();
        b.add("Fire, Accidental Damage, Liquid Damage and Beyond Economic Repairs");

        List<String> c = new ArrayList<String>();
        c.add("Theft and Burglary");

        List<String> d = new ArrayList<String>();
        d.add("You will be charged 5% or Rs 500/- whichever is higher under Depreciation plan");

        List<String> e = new ArrayList<String>();
        e.add("The Claim will be settled in 30 working days from date of submission of all required documents");

        expandableListDetail.put("What is the validity of the Protection cover and other services?", b);//3b
        expandableListDetail.put("What is covered under Accidental and Liquid Damage?", d);//1d
        expandableListDetail.put("What is covered under Theft Protection?", a);//5a
        expandableListDetail.put("How much will be the Processing fees that would be charged for Accidental Protection?", e);//2e
        expandableListDetail.put("How long will it take to get the Accidental and Theft claims processed?", c);//4c
        return expandableListDetail;
    }
}
