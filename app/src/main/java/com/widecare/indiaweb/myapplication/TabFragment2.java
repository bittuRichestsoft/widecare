package com.widecare.indiaweb.myapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.Shaved_shared_preferences;


public class TabFragment2 extends Fragment implements Asnychronus_notifier {
    Spinner initmatespinner;
    String initmatedate,initmatedob,initmateplace,initmatetime,initmatemobile,initmateemail,initmatedecription;
    TextView initmatedoc1,initmatedoc2,initmatedoc3;
    CheckBox initmatecheck;
    LinearLayout initmateSubmit,initmateReset;
    String stateselcted;
    ArrayList<String> arrayList;
    int connection=0;
    Calendar calendar;
    String idd;
    int openDialog = 0;
    int year;
    int pos=0;
    int month;
    int day;
    Boolean BOOL = false;
    Bitmap bitmap;
    ImageView pick,camera;
    Button submitimage;
    Bitmap image1,image2;
    LinearLayout t7,t8,t9;
    String initmatedobb,initmatedatee,catname,picturePath;
    String picturePath1,picturePath2,picturePath3;
    ImageView showimage;
    Boolean addedimage=false;
    Boolean bol1=false,bol2=false,bol3=false;
    Bitmap EcImage;
    private ProgressDialog pDialog;
    Shaved_shared_preferences shaved_shared_preferences;
    String proname,catid,proid,order;
    private Uri mImageUri;
    View rootview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.tab_fragment_2, container, false);

        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        idd = shaved_shared_preferences.get_userid();

        String catnamee = shaved_shared_preferences.get_catname();
        proname = shaved_shared_preferences.get_proname();
        catid = shaved_shared_preferences.get_catid();
        proid = shaved_shared_preferences.get_proid();
        order = shaved_shared_preferences.get_order();


        stateselcted = shaved_shared_preferences.get_spinner();
        initmatetime = shaved_shared_preferences.get_initmatetime();
        initmatedate = shaved_shared_preferences.get_initmatedate();
        initmatedecription = shaved_shared_preferences.get_initmatedecription();
        initmatedob = shaved_shared_preferences.get_initmatedob();
        initmateplace = shaved_shared_preferences.get_initmateplace();
        initmateemail = shaved_shared_preferences.get_initmateemail();
        initmatemobile = shaved_shared_preferences.get_initmatemobile();

        catname = catnamee.substring(0,1).toUpperCase() + catnamee.substring(1);

        t7 = (LinearLayout)rootview.findViewById(R.id.t7);
        t8 = (LinearLayout)rootview.findViewById(R.id.t8);
        t9 = (LinearLayout)rootview.findViewById(R.id.t9);
        initmatecheck = (CheckBox)rootview.findViewById(R.id.initmatecheck);
        initmatedoc1=(TextView)rootview.findViewById(R.id.initmatedoc1);
        initmatedoc2=(TextView)rootview.findViewById(R.id.initmatedoc2);
        initmatedoc3=(TextView)rootview.findViewById(R.id.initmatedoc3);

        initmateSubmit = (LinearLayout)rootview.findViewById(R.id.initmateSubmit);
        initmateReset = (LinearLayout)rootview.findViewById(R.id.initmateReset);


        initmateReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initmatedoc1.setText(" ");
                initmatedoc2.setText(" ");
                initmatedoc3.setText(" ");
                picturePath1 = null;
                picturePath2 = null;
                picturePath3=null;

                Log.e("RESET ", "DATA");
            }
        });



        t7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                bol1=true;
                bol2=false;
                bol3=false;
                selectImage();
            }
        });

        t8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(initmatedoc1.getText().toString().length()>0)
                {
                    bol1=false;
                    bol2=true;
                    bol3=false;
                    selectImage();
                }
                else
                {
                    Toast.makeText(getActivity(), "ATTACH DOCTUMENT 1", Toast.LENGTH_LONG).show();
                }


            }
        });

        t9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(initmatedoc1.getText().toString().length()>0)
                {
                    if(initmatedoc2.getText().toString().length()>0)
                    {
                        bol1=false;
                        bol2=false;
                        bol3=true;
                        selectImage();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"ATTACH DOCTUMENT 2",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"ATTACH DOCTUMENT 1",Toast.LENGTH_LONG).show();
                }


            }
        });

        initmateSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  if(check()) {
                      if (picturePath1.toString().length() > 0 && picturePath2.toString().length() > 0 && picturePath2.toString().length() > 0) {

                          if (isOnline()) {
                              receive();
                          } else {
                              Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
                          }
                          Log.e("SEND ", "DATA");
                      } else {
                          Toast.makeText(getActivity(), "ATTACH DOCUMENTS", Toast.LENGTH_LONG).show();
                      }
                  }
                else {
                      Intent intent = new Intent(getActivity(), IntimateClaimActivity.class);
                      intent.putExtra("pager", "1");
                      startActivity(intent);
                      getActivity().finish();
                  }

            }
        });
      return  rootview;
    }

    private boolean check()
    {
        Boolean  val =  true;

        stateselcted = shaved_shared_preferences.get_spinner();
        initmatetime = shaved_shared_preferences.get_initmatetime();
     //   initmatedate = shaved_shared_preferences.get_initmatedate();
        initmatedecription = shaved_shared_preferences.get_initmatedecription();
   //     initmatedob = shaved_shared_preferences.get_initmatedob();
        initmateplace = shaved_shared_preferences.get_initmateplace();
     //   initmateemail = shaved_shared_preferences.get_initmateemail();
      //  initmatemobile = shaved_shared_preferences.get_initmatemobile();

        if(stateselcted.equalsIgnoreCase(""))
        {
           Toast.makeText(getActivity(),"Select Damage Type",Toast.LENGTH_LONG).show();

            val =  false;
            return val;
        }
      /*  else if(initmatedate.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Select Intimate Date",Toast.LENGTH_LONG).show();
            val =  false;
            return val;
        }*/
        else if(initmatetime.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Select Intimate Time",Toast.LENGTH_LONG).show();
            val =  false;
            return val;
        }
        else if(initmateplace.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Enter Place of Incidence",Toast.LENGTH_LONG).show();
            val =  false;
            return val;
        }
     /*   else if(initmatedob.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Select Date of Birth",Toast.LENGTH_LONG).show();
            val =  false;
            return val;
        }*/
/*        else if(initmatemobile.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Enter Alternate Number",Toast.LENGTH_LONG).show();
            val =  false;
            return val;
        }
        else if(initmateemail.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Enter Alternate Email Address",Toast.LENGTH_LONG).show();
            val =  false;
            return val;
        }*/
        else if(initmatedecription.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Enter Decription of Loss",Toast.LENGTH_LONG).show();
            val =  false;
            return val;
        }
        return val;
    }


    private void receive()
    {
        Log.e("datassssssss ","send_server "+picturePath1);
        Log.e("datassssssss ","send_server "+picturePath2);
        Log.e("datassssssss ","send_server "+picturePath3);
        pDialog.show();
        try {

            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("orderId");
            param_values.add(""+order);

            params.add("productId");
            param_values.add(""+proid);

            params.add("userId");
            param_values.add(""+idd);

            params.add("CategoryId");
            param_values.add(""+catid);

            params.add("damageType");
            param_values.add(""+stateselcted);

            params.add("dateofincidence");
            param_values.add(""+initmatedate);

            params.add("timeofincidence");
            param_values.add(""+initmatetime);

            params.add("place");
            param_values.add(""+initmateplace);

            params.add("dateofbirth");
            param_values.add(""+initmatedob);

            params.add("mobileno");
            param_values.add(""+initmatemobile);

            params.add("email");
            param_values.add(""+initmateemail);

            params.add("lossDescription");
            param_values.add(""+initmatedecription);

            params.add("document1");
            param_values.add(""+picturePath1);

            params.add("document2");
            param_values.add(""+picturePath2);

            params.add("document3");
            param_values.add("" + picturePath3);

            Log.e("send category id ", "" + catid);

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);
            js.execute(Jason_Urls.intimate_url);
            js.setOnResultsListener(TabFragment2.this);
        } catch (Exception e) {

        }
    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result)
    {
        Log.e("result_of_intimate ", "" + result);
        pDialog.hide();
        if(result.length()>0)
        {
            try {
                pDialog.hide();
                String res = result.getString("type");
             //   String claim_id = result.getString("claim_id");
                Toast.makeText(getActivity(),"Claim "+res+"Sucessfully done",Toast.LENGTH_LONG).show();

                shaved_shared_preferences.set_initmate_process(1);
                shaved_shared_preferences.set_claimId(res);

                Log.e("set_process on ", "---" + shaved_shared_preferences.get_initmate_process());

                Intent intent = new Intent(getActivity(), IntimateClaimActivity.class);
                intent.putExtra("pager", "3");
                startActivity(intent);
                getActivity().finish();
            } catch (JSONException e) {
                e.printStackTrace();

                pDialog.hide();

                Toast.makeText(getActivity(),"SEND ERROR",Toast.LENGTH_LONG).show();
            }

        }
        else {

            pDialog.hide();
            Toast.makeText(getActivity(),"SEND ERROR",Toast.LENGTH_LONG).show();

        }
    }







    ///////////////////////////////////////////////////////////////////////



    private void selectImage () {


   /*     final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Attach Pdf",
                "Cancel"};*/
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(R.layout.new_dialog);
        builder.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        // set values for custom dialog components - text, image and button
        TextView take_photo = (TextView) builder.findViewById(R.id.take_photo);
        TextView choose_gallery = (TextView) builder.findViewById(R.id.choose_gallery);
        TextView attach_pdf = (TextView) builder.findViewById(R.id.attach_pdf);
        TextView cancel_option = (TextView) builder.findViewById(R.id.cancel_option);

        builder.show();

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog = 0;

                boolean result = Utility.checkPermission(getActivity());

                if (result) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    //  File photo = null;
                    try {
                        //   photo = createImageFile();
                        //  mImageUri = Uri.fromFile(photo);
                        mImageUri = FileProvider.getUriForFile(getActivity(), "com.widecare.indiaweb.myapplication.provider", createImageFile());
                        Log.e("valueeeee ", "---cameraIntent pass---" + mImageUri);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);

                        //start camera intent
                        startActivityForResult(intent, 100);


                    } catch (Exception e) {
                        Log.e("TAG", "Can't create file to take picture!");
                        Log.e("TAG", "" + e.getMessage());

                    }
                }
                builder.dismiss();
                builder.hide();
                builder.cancel();
            }
        });

        choose_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog = 1;

                Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhotoIntent, 200);

                builder.dismiss();
                builder.hide();
                builder.cancel();

            }
        });

        attach_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog = 2;


            /*    getDocument();

                Date now = new Date();
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(now);
                filename = timestamp + ".pdf";
                builder.dismiss();
                builder.hide();
                builder.cancel();*/

            }
        });

        cancel_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.dismiss();
                builder.hide();
                builder.cancel();
            }
        });

    }

    private void getDocument()

    {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 300);

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        picturePath = image.getAbsolutePath();

        Log.e("currentPhotoPath___img", "--" + image);
        Log.e("currentPhotoPath", "--" + picturePath);

        return image;
    }
    public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context) {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }}


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request

        //If permission is granted
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Displaying a toast
            Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
        } else {
            //Displaying another toast if permission is not granted
            Toast.makeText(getActivity(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
        }

    }


    public String getStringImage(Bitmap bmp)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("currentPhotoPath ", "--" + requestCode);
        if (requestCode == 200 && data != null
                && data.getData() != null) {

            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(uri, projection, null,
                    null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);

            picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getActivity().getContentResolver(), uri);
                // image1.setImageBitmap(bitmap);

                String imgg = getStringImage(bitmap);

                EcImage = bitmap;

                Log.e("currentPhotoPath_glr ", "" + imgg);

                if(bol1==true)
                {
                    picturePath1 = getStringImage(EcImage);
                    Log.e("Image picturePath1", "" + EcImage);
                    initmatedoc1.setText(picturePath);
                }
                if(bol2==true)
                {
                    picturePath2 = getStringImage(EcImage);
                    Log.e("Image picturePath2", "" + EcImage);
                    initmatedoc2.setText(picturePath);
                }
                if(bol3==true)
                {
                    picturePath3 = getStringImage(EcImage);
                    Log.e("Image picturePath3", "" + EcImage);
                    initmatedoc3.setText(picturePath);
                }


            }
            catch (Exception e)
            {
                e.getMessage();
            }

            Log.e("picturePath1 gallery ","--"+picturePath);
            Log.e("picturePath1 gallery ","--"+bol1);
            Log.e("picturePath1 gallery ","--"+bol2);
            Log.e("picturePath1 gallery ","--"+bol3);
            Log.e("picturePath1 gallery ","--"+picturePath1);
            Log.e("picturePath1 gallery ","--"+picturePath2);
            Log.e("picturePath1 gallery ","--"+picturePath3);

        }
        else  if (requestCode == 100 ) {

            Log.e("currentPhotoPath ", "--" + requestCode);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getActivity().getContentResolver(), mImageUri);
                // image1.setImageBitmap(bitmap);

                String imgg = getStringImage(bitmap);


                Log.e("currentPhotoPath_co ", "" + imgg);

                EcImage = bitmap;

                Log.e("currentPhotoPath_glr ", "" + imgg);

                if(bol1==true)
                {
                    picturePath1 = getStringImage(EcImage);
                    Log.e("Image picturePath1", "" + EcImage);
                    initmatedoc1.setText(picturePath);
                }
                if(bol2==true)
                {
                    picturePath2 = getStringImage(EcImage);
                    Log.e("Image picturePath2", "" + EcImage);
                    initmatedoc2.setText(picturePath);
                }
                if(bol3==true)
                {
                    picturePath3 = getStringImage(EcImage);
                    Log.e("Image picturePath3", "" + EcImage);
                    initmatedoc3.setText(picturePath);
                }

            } catch (Exception e) {
                e.getMessage();
            }

            Log.e("picturePath1 camera ","--"+picturePath);
            Log.e("picturePath1 camera ","--"+bol1);
            Log.e("picturePath1 camera ","--"+bol2);
            Log.e("picturePath1 camera ","--"+bol3);
            Log.e("picturePath1 camera ","--"+picturePath1);
            Log.e("picturePath1 camera ","--"+picturePath2);
            Log.e("picturePath1 camera ","--"+picturePath3);
        }

        else  if (requestCode == 300) {
            // Get the Uri of the selected file

            Log.e("currentPhotoPath ", "--" + requestCode);

            Uri uri = data.getData();
            String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;

            Log.e("currentPhotoPath dt", "--" + data);

            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                        picturePath = displayName;

                        Log.e("currentPhotoPath ", "--" + picturePath);


                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();

                picturePath = displayName;

                Log.e("currentPhotoPath ", "--" + picturePath);
            }

            EcImage = bitmap;

            if(bol1==true)
            {
                picturePath1 = getStringImage(EcImage);
                Log.e("Image picturePath1", "" + EcImage);
                initmatedoc1.setText(picturePath);
            }
            if(bol2==true)
            {
                picturePath2 = getStringImage(EcImage);
                Log.e("Image picturePath2", "" + EcImage);
                initmatedoc2.setText(picturePath);
            }
            if(bol3==true)
            {
                picturePath3 = getStringImage(EcImage);
                Log.e("Image picturePath3", "" + EcImage);
                initmatedoc3.setText(picturePath);
            }

            uri = data.getData();

            Log.e("currentPhotoPath dtt", "--" + data);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getActivity().getContentResolver(), uri);

                Log.e("currentPhotoPath ", "--" + uri);
                Log.e("currentPhotoPath ", "--" + bitmap);

                String imgg = getStringImage(bitmap);


                Log.e("currentPhotoPath_co ", "" + imgg);

                //    send_image1.setText(picturePath);


            } catch (Exception e) {
                e.getMessage();
            }
        }
    }



    public static Bitmap resizeAndCompressImageBeforeSend(String filePath){
        // First decode with inJustDecodeBounds=true to check dimensions of image
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath,options);

        // Calculate inSampleSize(First we are going to resize the image to 800x800 image, in order to not have a big but very low quality image.
        //resizing the image will already reduce the file size, but after resizing we will check the file size and start to compress image
        options.inSampleSize = calculateInSampleSize(options, 300, 300);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inPreferredConfig= Bitmap.Config.ARGB_8888;

        Bitmap bmpPic = BitmapFactory.decodeFile(filePath, options);

        return bmpPic;

    }



    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        String debugTag = "MemoryInformation";
        // Image nin islenmeden onceki genislik ve yuksekligi
        final int height = options.outHeight;
        final int width = options.outWidth;
        Log.d(debugTag, "image height: " + height + "---image width: " + width);
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize = inSampleSize+1;
            }
        }
        Log.d(debugTag, "inSampleSize: " + inSampleSize);
        return inSampleSize;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////




    private String getFileNameByUri (Context context, Uri uri){
        String filepath = "";//default fileName
        //Uri filePathUri = uri;

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file=getOutputMediaFile(1);
        uri = Uri.fromFile(file); // create
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri); // set the image file
        startActivity(i);

        try
        {
            file = new File(new URI(uri.toString()));
            if (file.exists())
                filepath = file.getAbsolutePath();

        }
        catch (URISyntaxException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return filepath;
    }


    private boolean isExternalStorageAvailable() {
        // find out what state external storage is in
        String state = Environment.getExternalStorageState();
        // if external storage is available, return true,
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    private  File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyApplication");

        /**Create the storage directory if it does not exist*/
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        return mediaStorageDir;
    }
    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}