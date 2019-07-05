package salma.abozid.personalmedicalaccess;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import salma.abozid.personalmedicalaccess.Models.BookModel;
import salma.abozid.personalmedicalaccess.Models.PatientModel;
import salma.abozid.personalmedicalaccess.Models.PresentBookingsModel;

public class StartActivity extends AppCompatActivity
{
    FloatingActionButton more_fab;
    CircleImageView circleImageView;
    Button select_date_btn;

    MaterialRippleLayout book_now_btn;

    RadioGroup radioGroup;
    RadioButton radio_1,radio_2,radio_3;

    BookModel bookModel;

    String name,imageurl,KEY;

    Calendar calendar;
    int day;
    String s_date,book_time = "";


    RatingBar ratingBar  ;
    String spec  ;

    float finalRate ;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        KEY = getIntent().getStringExtra("doc");

        spec = getIntent().getStringExtra("spec");



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.keepSynced(true);

        more_fab = findViewById(R.id.more_fab);
        ratingBar = findViewById(R.id.ratingBar);
        circleImageView = findViewById(R.id.profile_image);
        select_date_btn = findViewById(R.id.select_date_btn);
        book_now_btn = findViewById(R.id.book_now_btn);

        radioGroup = findViewById(R.id.radio_group);
        radio_1 = findViewById(R.id.radio_btn_1);
        radio_2 = findViewById(R.id.radio_btn_2);
        radio_3 = findViewById(R.id.radio_btn_3);

        returndata(getUID());
        onRadioButtonClicked();

        forRating();



        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day)
        {
            case Calendar.SATURDAY:
                radio_1.setText("09:00 am - 12:00 pm");
                radio_2.setText("12:00 pm - 04:00 pm");
                radio_3.setText("07:00 pm - 09:00 pm");
                radio_1.setEnabled(true);
                radio_2.setEnabled(true);
                radio_3.setEnabled(true);
                select_date_btn.setText("select day (SATURDAY)");
                s_date = "SATURDAY";
                break;
            case Calendar.SUNDAY:
                radio_1.setText("09:00 am - 12:00 pm");
                radio_2.setText("12:00 pm - 04:00 pm");
                radio_3.setText("07:00 pm - 09:00 pm");
                radio_1.setEnabled(true);
                radio_2.setEnabled(true);
                radio_3.setEnabled(true);
                select_date_btn.setText("select day (SUNDAY)");
                s_date = "SUNDAY";
                break;
            case Calendar.MONDAY:
                radio_1.setText("Off-Working\nPlease Select Another Day");
                radio_2.setText("Off-Working\nPlease Select Another Day");
                radio_3.setText("Off-Working\nPlease Select Another Day");
                radio_1.setEnabled(false);
                radio_2.setEnabled(false);
                radio_3.setEnabled(false);
                select_date_btn.setText("select day (MONDAY)");
                s_date = "MONDAY";
                break;
            case Calendar.TUESDAY:
                radio_1.setText("09:00 am - 12:00 pm");
                radio_2.setText("12:00 pm - 04:00 pm");
                radio_3.setText("07:00 pm - 09:00 pm");
                radio_1.setEnabled(true);
                radio_2.setEnabled(true);
                radio_3.setEnabled(true);
                select_date_btn.setText("select day (TUESDAY)");
                s_date = "TUESDAY";
                break;
            case Calendar.WEDNESDAY:
                radio_1.setText("09:00 am - 12:00 pm");
                radio_2.setText("12:00 pm - 04:00 pm");
                radio_3.setText("07:00 pm - 09:00 pm");
                radio_1.setEnabled(true);
                radio_2.setEnabled(true);
                radio_3.setEnabled(true);
                select_date_btn.setText("select day (WEDNESDAY)");
                s_date = "WEDNESDAY";
                break;
            case Calendar.THURSDAY:
                radio_1.setText("09:00 am - 12:00 pm");
                radio_2.setText("12:00 pm - 04:00 pm");
                radio_3.setText("07:00 pm - 09:00 pm");
                radio_1.setEnabled(true);
                radio_2.setEnabled(true);
                radio_3.setEnabled(true);
                select_date_btn.setText("select day (THURSDAY)");
                s_date = "THURSDAY";
                break;
        }

        select_date_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu popup = new PopupMenu(StartActivity.this, select_date_btn);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.time_table_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.saturday:
                                Toast.makeText(getApplicationContext(), "Saturday Clicked", Toast.LENGTH_SHORT).show();
                                radio_1.setText("09:00 am - 12:00 pm");
                                radio_2.setText("12:00 pm - 04:00 pm");
                                radio_3.setText("07:00 pm - 09:00 pm");
                                radio_1.setEnabled(true);
                                radio_2.setEnabled(true);
                                radio_3.setEnabled(true);
                                select_date_btn.setText("select day (SATURDAY)");
                                s_date = "SATURDAY";
                                return true;
                            case R.id.sunday:
                                Toast.makeText(getApplicationContext(), "Sunday Clicked", Toast.LENGTH_SHORT).show();
                                radio_1.setText("09:00 am - 12:00 pm");
                                radio_2.setText("12:00 pm - 04:00 pm");
                                radio_3.setText("07:00 pm - 09:00 pm");
                                radio_1.setEnabled(true);
                                radio_2.setEnabled(true);
                                radio_3.setEnabled(true);
                                select_date_btn.setText("select day (SUNDAY)");
                                s_date = "SUNDAY";
                                return true;
                            case R.id.tuesday:
                                Toast.makeText(getApplicationContext(), "Tuesday Clicked", Toast.LENGTH_SHORT).show();
                                radio_1.setText("09:00 am - 12:00 pm");
                                radio_2.setText("12:00 pm - 04:00 pm");
                                radio_3.setText("07:00 pm - 09:00 pm");
                                radio_1.setEnabled(true);
                                radio_2.setEnabled(true);
                                radio_3.setEnabled(true);
                                select_date_btn.setText("select day (TUESDAY)");
                                s_date = "TUESDAY";
                                return true;
                            case R.id.wednesday:
                                Toast.makeText(getApplicationContext(), "Wednesday Clicked", Toast.LENGTH_SHORT).show();
                                radio_1.setText("09:00 am - 12:00 pm");
                                radio_2.setText("12:00 pm - 04:00 pm");
                                radio_3.setText("07:00 pm - 09:00 pm");
                                radio_1.setEnabled(true);
                                radio_2.setEnabled(true);
                                radio_3.setEnabled(true);
                                select_date_btn.setText("select day (WEDNESDAY)");
                                s_date = "WEDNESDAY";
                                return true;
                            case R.id.thursday:
                                Toast.makeText(getApplicationContext(), "Thursday Clicked", Toast.LENGTH_SHORT).show();
                                radio_1.setText("09:00 am - 12:00 pm");
                                radio_2.setText("12:00 pm - 04:00 pm");
                                radio_3.setText("07:00 pm - 09:00 pm");
                                radio_1.setEnabled(true);
                                radio_2.setEnabled(true);
                                radio_3.setEnabled(true);
                                select_date_btn.setText("select day (THURSDAY)");
                                s_date = "THURSDAY";
                                return true;
                            default:
                                return true;
                        }
                    }});
                popup.show(); //showing popup menu
            }
        });

        more_fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu popup = new PopupMenu(StartActivity.this, more_fab);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.more_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.diagnosis:
                                Intent intent = new Intent(getApplicationContext(), DiagnosisActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.bookings:
                                Intent intent2 = new Intent(getApplicationContext(), BookingsActivity.class);
                                startActivity(intent2);
                                return true;
                            case R.id.signout:
                                FirebaseAuth.getInstance().signOut();

                                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent3);
                                return true;
                            default:
                                return true;
                        }
                    }});
                popup.show(); //showing popup menu
            }
        });

        book_now_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (book_time.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "please choose day and time firstly", Toast.LENGTH_SHORT).show();
                    return;
                }

                bookModel = new BookModel(name, imageurl, s_date, book_time);

                databaseReference.child("AllBooks").child(KEY).child(getUID()).setValue(bookModel);
                Toast.makeText(getApplicationContext(), "Booking Done ..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void returndata(String key)
    {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);


        mDatabase.child("Patients").child(key).addListenerForSingleValueEvent(
                new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        // Get user value
                        PatientModel patientModel = dataSnapshot.getValue(PatientModel.class);

                        name = patientModel.getFullname();
                        imageurl = patientModel.getImageurl();

                        Picasso.get()
                                .load(imageurl)
                                .placeholder(R.drawable.addphoto)
                                .error(R.drawable.addphoto)
                                .into(circleImageView);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                        Toast.makeText(getApplicationContext(), "can\'t fetch data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String getUID()
    {
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return id;
    }

    public void onRadioButtonClicked()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.radio_btn_1 :
                        book_time = radio_1.getText().toString();
                        //Toast.makeText(MainActivity.this, "French : " + price, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_btn_2 :
                        book_time = radio_2.getText().toString();
                        //Toast.makeText(MainActivity.this, "Turkish : " + price, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_btn_3 :
                        book_time = radio_3.getText().toString();
                        //Toast.makeText(MainActivity.this, "Esspresso : " + price, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void forRating (){


      ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
          @Override
          public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

             checkIfDoctorHasRate(rating);

          }
      });




    }



    private void getRateFromFireBase(final float numOfRat){

        databaseReference.child("Doctors").child(spec).child(KEY).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                float previousRate =  dataSnapshot.getValue(Float.class);

                if(previousRate > numOfRat){

                    finalRate = (float) (previousRate - 0.3);

                    databaseReference.child("Doctors").child(spec).child(KEY).child("rating").setValue(finalRate);

                }else if(numOfRat > 0.1 && numOfRat < 1 ){

                    finalRate = (float) (previousRate + 0.1);
                    databaseReference.child("Doctors").child(spec).child(KEY).child("rating").setValue(finalRate);

                }else if (numOfRat > 1 && numOfRat <2){

                    finalRate = (float) (previousRate + 0.2);
                    databaseReference.child("Doctors").child(spec).child(KEY).child("rating").setValue(finalRate);
                }
                else if (numOfRat > 2  && numOfRat < 3 ){

                    finalRate = (float) (previousRate + 0.3);
                    databaseReference.child("Doctors").child(spec).child(KEY).child("rating").setValue(finalRate);
                }else if (numOfRat > 3 && numOfRat < 4){
                    finalRate = (float) (previousRate + 0.4);
                    databaseReference.child("Doctors").child(spec).child(KEY).child("rating").setValue(finalRate);

                }else if(numOfRat > 4 && numOfRat <= 5){

                    finalRate = (float) (previousRate + 0.5);
                    databaseReference.child("Doctors").child(spec).child(KEY).child("rating").setValue(finalRate);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void checkIfDoctorHasRate(final float numOfRate ){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("Doctors").child(spec).child(KEY).hasChild("rating")) {

                   getRateFromFireBase(numOfRate);


                }else {

                    databaseReference.child("Doctors").child(spec).child(KEY).child("rating").setValue(3);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    private long exitTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000)
        {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finishAffinity();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed()
    {
        doExitApp();
    }
}
