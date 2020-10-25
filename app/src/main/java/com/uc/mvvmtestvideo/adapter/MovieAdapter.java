package com.uc.mvvmtestvideo.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.mvvmtestvideo.R;
import com.uc.mvvmtestvideo.model.Movie;
import com.uc.mvvmtestvideo.ui.main.movie.MovieFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewHolder> {

    private Context context;
    private List<Movie> listMovie;

    public List<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
    }


    public MovieAdapter(Context context) {
        this.context = context;
        this.listMovie = new ArrayList<Movie>();
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new MovieAdapter.CardViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.CardViewHolder holder, int position) {
        Movie movie = getListMovie().get(position);
        Glide.with(context).load(movie.getCover()).centerCrop().into(holder.image);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(movie.getPopularity());

        holder.itemView.setOnClickListener(v -> {
            NavDirections action = MovieFragmentDirections.actionMovietoDetailFragment(movie, null);
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return  getListMovie().size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title, rating;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pic);
            title = itemView.findViewById(R.id.titleFilm);
            rating = itemView.findViewById(R.id.rating);
        }
    }

}