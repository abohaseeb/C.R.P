package salma.abozid.personalmedicalaccess.Models;

public class BookModel
{
    String name,imageurl,day,time;

    public BookModel() {
    }

    public BookModel(String name, String imageurl, String day, String time) {
        this.name = name;
        this.imageurl = imageurl;
        this.day = day;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
