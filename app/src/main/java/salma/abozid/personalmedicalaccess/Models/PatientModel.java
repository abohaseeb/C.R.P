package salma.abozid.personalmedicalaccess.Models;

public class PatientModel
{
    String fullname,email,mobile,imageurl;

    public PatientModel() {
    }

    public PatientModel(String fullname, String email, String mobile, String imageurl) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.imageurl = imageurl;
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
}
