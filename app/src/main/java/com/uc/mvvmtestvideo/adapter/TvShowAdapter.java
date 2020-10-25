package com.uc.mvvmtestvideo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.mvvmtestvideo.R;
import com.uc.mvvmtestvideo.model.TvShow;
import com.uc.mvvmtestvideo.ui.main.tvShow.TvShowFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewHolder> {

    private Context context;
    private List<TvShow> listTvShow;

    private List<TvShow> getListTvShow() {
        return  listTvShow;
    }

    public void setListTvShow(List<TvShow> listTvShow){
        this.listTvShow = listTvShow;
    }

    public TvShowAdapter(Context context){
        this.listTvShow = new ArrayList<TvShow>();
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new TvShowAdapter.CardViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        TvShow tvShow = getListTvShow().get(position);
        Glide.with(context).load(tvShow.getCover()).centerCrop().into(holder.image);
        holder.title.setText(tvShow.getTitle());
        holder.rating.setText(tvShow.getPopularity());

        holder.itemView.setOnClickListener(v -> {
            NavDirections action = TvShowFragmentDirections.actionTvtoDetailFragment(null, tvShow);
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,rating;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pic);
            title = itemView.findViewById(R.id.titleFilm);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
