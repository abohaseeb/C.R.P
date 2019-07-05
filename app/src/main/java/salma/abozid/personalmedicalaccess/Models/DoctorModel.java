package salma.abozid.personalmedicalaccess.Models;

public class DoctorModel
{
    String fullname,email,mobile,imageurl,speciality ,price;




    float rating ;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public DoctorModel() {
    }

    public DoctorModel(String fullname, String email, String mobile, String imageurl, String speciality , float rating  , String price) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.imageurl = imageurl;
        this.speciality = speciality;
        this.rating= rating ;
        this.price = price ;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
