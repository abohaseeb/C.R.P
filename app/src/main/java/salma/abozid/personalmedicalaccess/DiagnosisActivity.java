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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.victor.loading.rotate.RotateLoading;

import salma.abozid.personalmedicalaccess.Models.ImageModel;

public class DiagnosisActivity extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ImageModel, BooksViewHolder> firebaseRecyclerAdapter;

    RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        recyclerView = findViewById(R.id.recyclerview);
        rotateLoading = findViewById(R.id.rotateloading);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.keepSynced(true);

        recyclerView = findViewById(R.id.recyclerview);
        rotateLoading = findViewById(R.id.rotateloading);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.keepSynced(true);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        allInfo(getUID());
    }

    private void allInfo(String key1)
    {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Info")
                .child(key1)
                .limitToLast(50);

        FirebaseRecyclerOptions<ImageModel> options =
                new FirebaseRecyclerOptions.Builder<ImageModel>()
                        .setQuery(query, ImageModel.class)
                        .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ImageModel, BooksViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull final BooksViewHolder holder, int position, @NonNull final ImageModel model)
            {
                rotateLoading.stop();

                final String key = getRef(position).getKey();

                holder.BindPlaces(model);

                holder.imageView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(getApplicationContext(), FullActivity.class);
                        intent.putExtra("url", model.getImageurl());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.image_item, parent, false);
                return new BooksViewHolder(view);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        rotateLoading.stop();
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        BooksViewHolder(View itemView)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
        }

        void BindPlaces(final ImageModel imageModel)
        {
            Picasso.get()
                    .load(imageModel.getImageurl())
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
