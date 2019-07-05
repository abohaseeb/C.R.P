package salma.abozid.personalmedicalaccess.Models;

public class PresentBookingsModel
{
    String day,time;

    public PresentBookingsModel() {
    }

    public PresentBookingsModel(String day, String time) {
        this.day = day;
        this.time = time;
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
