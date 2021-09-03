package Constant;

import android.graphics.Bitmap;

/**
 * Created by indiaweb on 9/22/2016.
 */

public class UserDataItems
{
    private String catId;
    private String name;
    private String cimage;
    private String image;
    private String pname;
    private String pdes;
    private String pId;
    private String htmlPdes;



    private String policyname;
    private String policyorder;
    private String policydate;

    private String ppVId;

    private String ppimage;
    private Bitmap pppimage;
    private String ppquantity;
    private String ppId;
    private String status;

    private String opricer;
    private String oitem;
    private String date;
    private String onum;
    private String ppname;
    private String pprice;

    private String pimage;


    private String itemType;
    private String itemPrice;

 //attribute data for calculatePriceScreen
    private String attributeId;
    private String attributeName;

    //slab data for calculatePriceScreen
     private String slabId;
    private String slabValue;


    public String getSlabId() {
        return slabId;
    }

    public void setSlabValue(String slabValue) {
        this.slabValue = slabValue;
    }

    public String getSlabValue() {
        return slabValue;
    }

    public void setSlabId(String slabId) {
        this.slabId = slabId;
    }


    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPolicyname() {
        return policyname;
    }

    public void setPolicyname(String policyname) {
        this.policyname = policyname;
    }

    public String getPolicyorder() {
        return policyorder;
    }

    public void setPolicyorder(String policyorder) {
        this.policyorder = policyorder;
    }

    public String getPolicydate() {
        return policydate;
    }

    public void setPolicydate(String policydate) {
        this.policydate = policydate;
    }

    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getOpricer() {
        return opricer;
    }

    public void setOpricer(String opricer) {
        this.opricer = opricer;
    }

    public String getOitem() {
        return oitem;
    }

    public void setOitem(String oitem) {
        this.oitem = oitem;
    }

    public Bitmap getPppimage() {
        return pppimage;
    }

    public void setPppimage(Bitmap pppimage) {
        this.pppimage = pppimage;
    }

    public String getPpimage() {
        return ppimage;
    }

    public void setPpimage(String ppimage) {
        this.ppimage = ppimage;
    }


    public String getPpVId() {
        return ppVId;
    }

    public void setPpVId(String ppVId) {
        this.ppVId = ppVId;
    }
    public String getOnum() {
        return onum;
    }

    public void setOnum(String onum) {
        this.onum = onum;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPpname() {
        return ppname;
    }

    public void setPpname(String ppname) {
        this.ppname = ppname;
    }

    public String getPpquantity() {
        return ppquantity;
    }

    public void setPpquantity(String ppquantity) {
        this.ppquantity = ppquantity;
    }

    public String getPpId() {
        return ppId;
    }

    public void setPpId(String ppId) {
        this.ppId = ppId;
    }




    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdes() {
        return pdes;
    }

    public void setPdes(String pdes) {
        this.pdes = pdes;
    }

    public String getHtmlPdes() {
        return htmlPdes;
    }

    public void setHtmlPdes(String htmlPdes) {
        this.htmlPdes= htmlPdes;
    }

    public UserDataItems(String catId, String name, String image, String pname, String pdes, String pId, String pprice, String ppname, String ppquantity, String ppId, String pimage) {
        this.catId = catId;
        this.name = name;
        this.image = image;
        this.pname = pname;
        this.pdes = pdes;
        this.pId = pId;
        this.pprice = pprice;
        this.ppname = ppname;
        this.ppquantity = ppquantity;
        this.ppId = ppId;
        this.pimage = pimage;
    }

    public UserDataItems() {
        // TODO Auto-generated constructor stub
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
