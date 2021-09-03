package Constant;


import android.content.Context;
import android.content.SharedPreferences;

public class Shaved_shared_preferences {
	Context activity;
	public Shaved_shared_preferences(Context activity)
	{
		this.activity=activity;

	}
	public void set_user_fname(String user_fname){
		SharedPreferences prefs=activity.getSharedPreferences("userfname", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("userfname", user_fname);
		editor.commit();
	}
	public String get_user_fname(){
		SharedPreferences prefs =activity.getSharedPreferences("userfname",activity.MODE_PRIVATE);
		String user_fname=prefs.getString("userfname", null);
		return user_fname;
	}

	public void set_user_display(String user_display){
		SharedPreferences prefs=activity.getSharedPreferences("user_display", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_display", user_display);
		editor.commit();
	}
	public String get_user_display(){
		SharedPreferences prefs =activity.getSharedPreferences("user_display",activity.MODE_PRIVATE);
		String user_display=prefs.getString("user_display", null);
		return user_display;
	}

	public void set_user_cname(String user_cname){
		SharedPreferences prefs=activity.getSharedPreferences("user_cname", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_cname", user_cname);
		editor.commit();
	}
	public String get_user_cname(){
		SharedPreferences prefs =activity.getSharedPreferences("user_cname",activity.MODE_PRIVATE);
		String user_cname=prefs.getString("user_cname", null);
		return user_cname;
	}

	public void set_user_lsname(String user_lsname){
		SharedPreferences prefs=activity.getSharedPreferences("user_lsname", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_lsname", user_lsname);
		editor.commit();
	}
	public String get_user_lsname(){
		SharedPreferences prefs =activity.getSharedPreferences("user_lsname",activity.MODE_PRIVATE);
		String user_lsname=prefs.getString("user_lsname", null);
		return user_lsname;
	}

	public void set_user_add(String user_add){
		SharedPreferences prefs=activity.getSharedPreferences("user_add", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_add", user_add);
		editor.commit();
	}

	public String get_user_add(){
		SharedPreferences prefs =activity.getSharedPreferences("user_add",activity.MODE_PRIVATE);
		String user_add=prefs.getString("user_add", null);
		return user_add;
	}

	public void set_user_add1(String user_add1){
		SharedPreferences prefs=activity.getSharedPreferences("user_add1", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_add", user_add1);
		editor.commit();
	}

	public String get_user_add1(){
		SharedPreferences prefs =activity.getSharedPreferences("user_add1",activity.MODE_PRIVATE);
		String user_add1=prefs.getString("user_add1", null);
		return user_add1;
	}

	public void set_user_phone(String userphone){
		SharedPreferences prefs=activity.getSharedPreferences("userphone", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("userphone", userphone);
		editor.commit();
	}
	public String get_user_phone(){
		SharedPreferences prefs =activity.getSharedPreferences("userphone",activity.MODE_PRIVATE);
		String userphone=prefs.getString("userphone", null);
		return userphone;
	}

	public void set_user_state(String user_state){
		SharedPreferences prefs=activity.getSharedPreferences("user_state", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_state", user_state);
		editor.commit();
	}
	public String get_user_state(){
		SharedPreferences prefs =activity.getSharedPreferences("user_state",activity.MODE_PRIVATE);
		String user_state=prefs.getString("user_state", null);
		return user_state;
	}

	public void set_user_country(String user_country){
		SharedPreferences prefs=activity.getSharedPreferences("user_country", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_country", user_country);
		editor.commit();
	}
	public String get_user_country(){
		SharedPreferences prefs =activity.getSharedPreferences("user_country",activity.MODE_PRIVATE);
		String user_country=prefs.getString("user_country", null);
		return user_country;
	}

	public void set_user_gender(String user_country){
		SharedPreferences prefs=activity.getSharedPreferences("gender", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("gender", user_country);
		editor.commit();
	}
	public String get_user_gender(){
		SharedPreferences prefs =activity.getSharedPreferences("gender",activity.MODE_PRIVATE);
		String gender=prefs.getString("gender", null);
		return gender;
	}

	public void set_user_town(String user_town){
		SharedPreferences prefs=activity.getSharedPreferences("user_town", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("user_town", user_town);
		editor.commit();
	}
	public String get_user_town(){
		SharedPreferences prefs =activity.getSharedPreferences("user_town",activity.MODE_PRIVATE);
		String user_town=prefs.getString("user_town", null);
		return user_town;
	}

	public void set_user_pincode(String pincode){
		SharedPreferences prefs=activity.getSharedPreferences("pincode", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("pincode", pincode);
		editor.commit();
	}
	public String get_user_pincode(){
		SharedPreferences prefs =activity.getSharedPreferences("pincode",activity.MODE_PRIVATE);
		String pincode=prefs.getString("pincode", null);
		return pincode;
	}

	public void set_user_email(String user_email){
		SharedPreferences prefs=activity.getSharedPreferences("email", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("email", user_email);
		editor.commit();
	}
	public String get_user_email(){
		SharedPreferences prefs =activity.getSharedPreferences("email",activity.MODE_PRIVATE);
		String user_email=prefs.getString("email", null);
		return user_email;
	}

	public void set_userid(String id){
		SharedPreferences prefs=activity.getSharedPreferences("id", activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("id", id);
		editor.commit();
	}
	public String get_userid(){
		SharedPreferences prefs =activity.getSharedPreferences("id",activity.MODE_PRIVATE);
		String id=prefs.getString("id", null);
		return id;
	}


	public void set_user_login(int login){
		SharedPreferences prefs = activity.getSharedPreferences("login",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("login", String.valueOf(login));
		editor.commit();
	}
	public int get_user_login(){
		SharedPreferences prefs =activity.getSharedPreferences("login",activity.MODE_PRIVATE);
		int login= Integer.parseInt(prefs.getString("login", "0"));
		return login;

	}

	/////////////////////////////////////////////////
	public void set_catname(String catname){
		SharedPreferences prefs = activity.getSharedPreferences("catname",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("catname", String.valueOf(catname));
		editor.commit();
	}
	public String get_catname(){
		SharedPreferences prefs =activity.getSharedPreferences("catname",activity.MODE_PRIVATE);
		String catname= prefs.getString("catname", null);
		return catname;

	}
	public void set_proname(String proname){
		SharedPreferences prefs = activity.getSharedPreferences("proname",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("proname", String.valueOf(proname));
		editor.commit();
	}
	public String get_proname(){
		SharedPreferences prefs =activity.getSharedPreferences("proname",activity.MODE_PRIVATE);
		String proname= prefs.getString("proname", null);
		return proname;

	}
	public void set_order(String order){
		SharedPreferences prefs = activity.getSharedPreferences("order",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("order", String.valueOf(order));
		editor.commit();
	}
	public String get_order(){
		SharedPreferences prefs =activity.getSharedPreferences("order",activity.MODE_PRIVATE);
		String order= prefs.getString("order", null);
		return order;

	}
	public void set_catid(String catid){
		SharedPreferences prefs = activity.getSharedPreferences("catid",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("catid", String.valueOf(catid));
		editor.commit();
	}
	public String get_catid(){
		SharedPreferences prefs =activity.getSharedPreferences("catid",activity.MODE_PRIVATE);
		String catid= prefs.getString("catid", null);
		return catid;

	}
	public void set_proid(String proid){
		SharedPreferences prefs = activity.getSharedPreferences("proid",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("proid", String.valueOf(proid));
		editor.commit();
	}
	public String get_proid(){
		SharedPreferences prefs =activity.getSharedPreferences("proid",activity.MODE_PRIVATE);
		String proid= prefs.getString("proid", null);
		return proid;

	}
	public void set_MobToken(String proid){
		SharedPreferences prefs = activity.getSharedPreferences("mobileToken",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("mobileToken", String.valueOf(proid));
		editor.commit();
	}
	public String get_MobToken(){
		SharedPreferences prefs =activity.getSharedPreferences("mobileToken",activity.MODE_PRIVATE);
		String proid= prefs.getString("mobileToken", null);
		return proid;

	}

	public void set_initmatedecription(String initmatedecription){
		SharedPreferences prefs = activity.getSharedPreferences("initmatedecription",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("initmatedecription", String.valueOf(initmatedecription));
		editor.commit();
	}
	public String get_initmatedecription(){
		SharedPreferences prefs =activity.getSharedPreferences("initmatedecription",activity.MODE_PRIVATE);
		String initmatedecription= prefs.getString("initmatedecription", null);
		return initmatedecription;

	}

	public void set_initmateemail(String initmateemail){
		SharedPreferences prefs = activity.getSharedPreferences("initmateemail",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("initmateemail", String.valueOf(initmateemail));
		editor.commit();
	}
	public String get_initmateemail(){
		SharedPreferences prefs =activity.getSharedPreferences("initmateemail",activity.MODE_PRIVATE);
		String initmateemail= prefs.getString("initmateemail", null);
		return initmateemail;

	}

	public void set_initmatemobile(String initmatemobile){
		SharedPreferences prefs = activity.getSharedPreferences("initmatemobile",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("initmatemobile", String.valueOf(initmatemobile));
		editor.commit();
	}
	public String get_initmatemobile(){
		SharedPreferences prefs =activity.getSharedPreferences("initmatemobile",activity.MODE_PRIVATE);
		String initmatemobile= prefs.getString("initmatemobile", null);
		return initmatemobile;

	}

	public void set_initmateplace(String initmateplace){
		SharedPreferences prefs = activity.getSharedPreferences("initmateplace",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("initmateplace", String.valueOf(initmateplace));
		editor.commit();
	}
	public String get_initmateplace(){
		SharedPreferences prefs =activity.getSharedPreferences("initmateplace",activity.MODE_PRIVATE);
		String initmateplace= prefs.getString("initmateplace", null);
		return initmateplace;

	}

	public void set_initmatedob(String initmatedob){
		SharedPreferences prefs = activity.getSharedPreferences("initmatedob",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("initmatedate", String.valueOf(initmatedob));
		editor.commit();
	}
	public String get_initmatedob(){
		SharedPreferences prefs =activity.getSharedPreferences("initmatedob",activity.MODE_PRIVATE);
		String initmatedob= prefs.getString("initmatedob", null);
		return initmatedob;

	}

	public void set_initmatedate(String initmatedate){
		SharedPreferences prefs = activity.getSharedPreferences("initmatedate",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("initmatedate", String.valueOf(initmatedate));
		editor.commit();
	}
	public String get_initmatedate(){
		SharedPreferences prefs =activity.getSharedPreferences("initmatedate",activity.MODE_PRIVATE);
		String initmatedate= prefs.getString("initmatedate", null);
		return initmatedate;

	}

	public void set_claimId(String initmatedate){
		SharedPreferences prefs = activity.getSharedPreferences("claimId",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("claimId", String.valueOf(initmatedate));
		editor.commit();
	}
	public String get_claimId(){
		SharedPreferences prefs =activity.getSharedPreferences("claimId",activity.MODE_PRIVATE);
		String initmatedate= prefs.getString("claimId", null);
		return initmatedate;

	}

	public void set_spinner(String spinner){
		SharedPreferences prefs = activity.getSharedPreferences("spinner",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("spinner", String.valueOf(spinner));
		editor.commit();
	}
	public String get_spinner(){
		SharedPreferences prefs =activity.getSharedPreferences("spinner",activity.MODE_PRIVATE);
		String spinner= prefs.getString("spinner", null);
		return spinner;

	}
	public void set_initmatetime(String initmatetime){
		SharedPreferences prefs = activity.getSharedPreferences("initmatetime",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("initmatetime", String.valueOf(initmatetime));
		editor.commit();
	}
	public String get_initmatetime(){
		SharedPreferences prefs =activity.getSharedPreferences("initmatetime",activity.MODE_PRIVATE);
		String initmatetime= prefs.getString("initmatetime", null);
		return initmatetime;

	}
	public void set_initmate_process(int initmatetime){
		SharedPreferences prefs = activity.getSharedPreferences("process",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putInt("process", initmatetime);
		editor.commit();
	}
	public int get_initmate_process(){
		SharedPreferences prefs =activity.getSharedPreferences("process",activity.MODE_PRIVATE);
		int initmatetime= prefs.getInt("process", Integer.parseInt("0"));
		return initmatetime;

	}

	public void set_refferCode(String initmatetime){
		SharedPreferences prefs = activity.getSharedPreferences("refferCode",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("refferCode", initmatetime);
		editor.commit();
	}
	public String get_refferCode(){
		SharedPreferences prefs =activity.getSharedPreferences("refferCode",activity.MODE_PRIVATE);
		String initmatetime= prefs.getString("refferCode", "");
		return initmatetime;
	}

	public void set_refferBy(String refferBy){
		SharedPreferences prefs = activity.getSharedPreferences("refferBy",activity.MODE_PRIVATE);
		SharedPreferences.Editor editor=prefs.edit();
		editor.putString("refferBy", refferBy);
		editor.commit();
	}
	public String get_refferBy(){
		SharedPreferences prefs =activity.getSharedPreferences("refferBy",activity.MODE_PRIVATE);
		String refferBy= prefs.getString("refferBy", "");
		return refferBy;

	}
}



