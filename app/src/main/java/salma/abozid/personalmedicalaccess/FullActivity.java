package salma.abozid.personalmedicalaccess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullActivity extends AppCompatActivity
{
    ImageView imageView;

    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);

        imageView = findViewById(R.id.image);

        URL = getIntent().getStringExtra("url");

        Picasso.get()
                .load(URL)
                .placeholder(R.drawable.ic_stopwatch1)
                .error(R.drawable.ic_stopwatch1)
                .into(imageView);
    }
}
