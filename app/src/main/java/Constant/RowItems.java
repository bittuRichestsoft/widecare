package Constant;

/**
 * Created by indiaweb on 11/8/2016.
 */
/**
 * Created by indiaweb on 10/3/2016.
 */
public class RowItems {
    private String imageId;
    private String title;
    private String desc;



    public void setCatId(String catId) {
        this.catId = catId;
    }

    private String catId;

    public RowItems() {
        this.imageId = imageId;
        this.title = title;
        this.catId = catId;

    }
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc;
    }
    public String setCatId() {
        return title;
    }
    public String getCatId() {
        return catId;
    }
}

