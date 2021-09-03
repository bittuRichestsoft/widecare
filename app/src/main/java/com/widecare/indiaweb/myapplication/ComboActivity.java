package com.widecare.indiaweb.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Constant.ComboAdapter;
import Model.Combo;

public class ComboActivity  extends Fragment {
    GridView listView;
    ComboAdapter adapter;
    ArrayList<Combo> arrayList;
    ArrayList<String> names;
    ArrayList<String> images;
    String TAG="ComboActivity ";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_combo, container, false);

        arrayList = new ArrayList<>();
        names = new ArrayList<>();
        images = new ArrayList<>();


        listView = (GridView)rootView.findViewById(R.id.listcombo);


         String[] m_image={
               /* "http://widecare.in/wp-content/uploads/2016/05/Insurance1.jpg",*/
                "http://widecare.in/wp-content/uploads/2016/08/gizmo.jpg"/*,
                "http://widecare.in/wp-content/uploads/2016/08/smashed-iphone.jpg",
                "http://widecare.in/wp-content/uploads/2016/08/assure.jpg",
                "http://widecare.in/wp-content/uploads/2016/05/Insurance3.jpg",
                "http://widecare.in/wp-content/uploads/2016/05/Insurance2.jpg"*/
        };

        String[] m_name={
        /*"Simplify Care",*/"Gizmo Secure (1 Year)"/*,"Gimmick Care (2 Year)","Always Assure","Travel Delight Pack","Health Value Pack"*/
        };

        String[] m_id={
                /*"1235",*/"1237"/*,"1239","1241","1242","1243"*/
        };

        List<String> wordList = Arrays.asList(m_name);
        List<String> wordLists = Arrays.asList(m_image);
        List<String> wordListss = Arrays.asList(m_id);


        for (int i = 0;i<wordList.size();i++)
        {
            Combo combo =  new Combo();

            combo.setName(wordList.get(i));
            combo.setImage(wordLists.get(i));
            combo.setIdd(wordListss.get(i));

            arrayList.add(combo);
        }

 //       List<String> wordLists = Arrays.asList(mStrings);

//        for (String e : wordLists)
//        {
//            Combo combo =  new Combo();
//
//            combo.setImage(e);
//
//        }


        adapter=new ComboAdapter(getActivity(), arrayList);

        //Set adapter to listview
        listView.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onDestroy()
    {
        // Remove adapter refference from list
        listView.setAdapter(null);
        super.onDestroy();
    }


    // Image urls used in LazyImageLoadAdapter.java file


}
