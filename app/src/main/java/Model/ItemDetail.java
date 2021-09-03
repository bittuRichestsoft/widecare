/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Model;

import java.io.Serializable;

public class ItemDetail implements Serializable {
	
	private String id;
	private int imgId;
	private String name;
	private String descr;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	private String type;
	private String serial;
	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private String pan;
	private String dob;
	private String sex;
	private String age;
	private String color;

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getM() {
		return M;
	}

	public void setM(String m) {
		M = m;
	}

	public String getIM() {
		return IM;
	}

	public void setIM(String IM) {
		this.IM = IM;
	}

	public String getP() {
		return P;
	}

	public void setP(String p) {
		P = p;
	}

	public String getD() {
		return D;
	}

	public void setD(String d) {
		D = d;
	}

	private String B;
	private String M;
	private String IM;
	private String P;
	private String D;

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	private String proid;


	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	private String order;

	public String getPolicynum() {
		return policynum;
	}

	public void setPolicynum(String policynum) {
		this.policynum = policynum;
	}

	private String policynum;
	private String price;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	private String start;
	private String end;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public ItemDetail(String id, int imgId, String name, String descr, String price, String order, String policynum,String start,String end,String proid,String M,String B,String IM, String type,String serial, String P,String D,String age,String dob,String sex, String pan,String color)
	{
		this.id = id;
		this.imgId = imgId;
		this.name = name;
		this.descr = descr;
		this.price = price;
		this.order = order;
		this.policynum = policynum;
		this.start = start;
		this.end = end;
		this.proid = proid;
		this.B = B;
		this.M = M;
		this.IM = IM;
		this.type = type;
		this.serial = serial;
		this.P = P;
		this.D = D;

		this.age = age;
		this.dob = dob;
		this.sex= sex;
		this.pan = pan;
		this.color = color;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
