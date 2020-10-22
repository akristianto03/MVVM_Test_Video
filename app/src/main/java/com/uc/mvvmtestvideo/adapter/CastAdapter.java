package com.uc.mvvmtestvideo.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.mvvmtestvideo.R;
import com.uc.mvvmtestvideo.model.Cast;
import com.uc.mvvmtestvideo.util.Constants;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private Context context;
    private List<Cast> listCast;

    public void setListCast(List<Cast> listCast) {
        this.listCast = listCast;
        notifyDataSetChanged();
    }

    public CastAdapter(Context context) {
        this.context = context;
        this.listCast = listCast;
    }



    @NonNull
    @Override
    public CastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cast, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.ViewHolder holder, int position) {

        Cast cast = listCast.get(position);
        Glide.with(context).load(Constants.IMG_URL + cast.getPic_url()).into(holder.castPic);
        holder.castName.setText(cast.getName());
        holder.castRole.setText(cast.getRole());

    }

    @Override
    public int getItemCount() {
        return listCast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView castPic;
        TextView castName,castRole;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            castPic = itemView.findViewById(R.id.castPic);
            castName = itemView.findViewById(R.id.castName);
            castRole = itemView.findViewById(R.id.castRole);
        }
    }

}