package salma.abozid.personalmedicalaccess.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import salma.abozid.personalmedicalaccess.DiagnosisActivity;
import salma.abozid.personalmedicalaccess.Models.BookModel;
import salma.abozid.personalmedicalaccess.Models.ImageModel;
import salma.abozid.personalmedicalaccess.R;

public class    PastBookingsFragment extends Fragment
{
    View view;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<BookModel, BooksViewHolder> firebaseRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.past_bookings_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.keepSynced(true);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        allBooks(getUID());
    }

    private void allBooks(String key)
    {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("PastBooks")
                .child(key)
                .limitToLast(50);

        FirebaseRecyclerOptions<BookModel> options =
                new FirebaseRecyclerOptions.Builder<BookModel>()
                        .setQuery(query, BookModel.class)
                        .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<BookModel, BooksViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull final BooksViewHolder holder, int position, @NonNull final BookModel model)
            {
                final String key = getRef(position).getKey();

                holder.BindPlaces(model);
            }

            @NonNull
            @Override
            public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.present_item, parent, false);
                return new BooksViewHolder(view);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder
    {
        TextView day_txt,time_txt;
        MaterialRippleLayout materialRippleLayout;

        BooksViewHolder(View itemView)
        {
            super(itemView);

            day_txt = itemView.findViewById(R.id.day_txt);
            time_txt = itemView.findViewById(R.id.time_txt);
            materialRippleLayout = itemView.findViewById(R.id.book_mrl);
        }

        void BindPlaces(final BookModel chatModel)
        {
            day_txt.setText(chatModel.getDay());
            time_txt.setText(chatModel.getTime());
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
