package com.exercise.satheeshkumar_u.proficiencymvvm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exercise.satheeshkumar_u.proficiencymvvm.R;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.Information;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private List<Information> newsList;
    private Context mContext;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView imageView;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            description = view.findViewById(R.id.tvContent);
            imageView = view.findViewById(R.id.ivImage);
        }
    }


    public NewsListAdapter(List<Information> newsList, Context context) {
        this.newsList = newsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Information movie = newsList.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getDescription());
        Glide.with(mContext)
                .load(movie.getImageHref())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}