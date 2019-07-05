package salma.abozid.personalmedicalaccess.Models;

public class ImageModel
{
    String imageurl;

    public ImageModel() {
    }

    public ImageModel(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
