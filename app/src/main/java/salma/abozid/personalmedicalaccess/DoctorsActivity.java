package salma.abozid.personalmedicalaccess;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import salma.abozid.personalmedicalaccess.Fragments.PastBookingsFragment;
import salma.abozid.personalmedicalaccess.Models.BookModel;
import salma.abozid.personalmedicalaccess.Models.DoctorModel;

public class DoctorsActivity extends AppCompatActivity
{
    String KEY;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<DoctorModel, BooksViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        KEY = getIntent().getStringExtra("spec");

        recyclerView = findViewById(R.id.recyclerview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.keepSynced(true);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);



        allBooks(KEY);
    }

    private void allBooks(String key)
    {
        Query query = FirebaseDatabase.getInstance().getReference().child("Doctors").child(key).limitToLast(50);

        FirebaseRecyclerOptions<DoctorModel> options = new FirebaseRecyclerOptions.Builder<DoctorModel>().setQuery(query, DoctorModel.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DoctorModel, BooksViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull final BooksViewHolder holder, int position, @NonNull final DoctorModel model)
            {
                final String key = getRef(position).getKey();

                holder.BindPlaces(model);

                holder.materialRippleLayout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        intent.putExtra("doc", key);
                        intent.putExtra("spec",KEY);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.doctor_item, parent, false);
                return new BooksViewHolder(view);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder
    {
        RatingBar ratingBar ;
        ImageView imageView;
        TextView day_txt,time_txt , priceTxt;
        MaterialRippleLayout materialRippleLayout;

        BooksViewHolder(View itemView)
        {
            super(itemView);
             priceTxt  = itemView.findViewById(R.id.price_txt);
            ratingBar = itemView.findViewById(R.id.ratingBar_home);
            imageView = itemView.findViewById(R.id.book_image);
            day_txt = itemView.findViewById(R.id.day_txt);
            time_txt = itemView.findViewById(R.id.time_txt);
            materialRippleLayout = itemView.findViewById(R.id.book_mrl);
        }

        void BindPlaces(final DoctorModel chatModel)
        {
            priceTxt.setText(chatModel.getPrice());
            day_txt.setText(chatModel.getFullname());
            time_txt.setText(chatModel.getMobile());
            ratingBar.setRating(chatModel.getRating());

            Picasso.get()
                    .load(chatModel.getImageurl())
                    .placeholder(R.drawable.ic_stopwatch1)
                    .error(R.drawable.ic_stopwatch1)
                    .into(imageView);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if (firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();

        if (firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.stopListening();
        }
    }

    private String getUID()
    {
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return id;
    }
}
