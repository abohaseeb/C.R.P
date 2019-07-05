package salma.abozid.personalmedicalaccess.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;

import salma.abozid.personalmedicalaccess.Models.PresentBookingsModel;
import salma.abozid.personalmedicalaccess.R;

public class PresentBookingsFragment extends Fragment
{
    View view;
    RecyclerView recyclerView;
    List<PresentBookingsModel> list;
    PresentAdapter presentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.present_bookings_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);

        list = new ArrayList<>();

        list.add(new PresentBookingsModel("Wednesday" , "at 09:00 pm - 11:00 pm"));
        list.add(new PresentBookingsModel("Friday" , "at 09:00 pm - 11:00 pm"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        presentAdapter = new PresentAdapter(list);

        recyclerView.setAdapter(presentAdapter);
    }

    public class PresentAdapter extends RecyclerView.Adapter<PresentAdapter.PresentViewHolder>
    {
        List<PresentBookingsModel> list;

        PresentAdapter(List<PresentBookingsModel> list)
        {
            this.list = list;
        }

        @NonNull
        @Override
        public PresentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.present_item, viewGroup, false);
            return new PresentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PresentViewHolder presentViewHolder, int i)
        {
            final String day = list.get(i).getDay();
            final String time = list.get(i).getTime();

            presentViewHolder.day_txt.setText(day);
            presentViewHolder.time_txt.setText(time);

            presentViewHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getContext(), day + "\n" + time, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return list.size();
        }

        class PresentViewHolder extends RecyclerView.ViewHolder
        {
            TextView day_txt,time_txt;
            MaterialRippleLayout materialRippleLayout;

            PresentViewHolder(@NonNull View itemView)
            {
                super(itemView);

                day_txt = itemView.findViewById(R.id.day_txt);
                time_txt = itemView.findViewById(R.id.time_txt);
                materialRippleLayout = itemView.findViewById(R.id.book_mrl);
            }
        }
    }
}
