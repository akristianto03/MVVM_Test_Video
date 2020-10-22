package com.uc.mvvmtestvideo.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.mvvmtestvideo.R;
import com.uc.mvvmtestvideo.adapter.CastAdapter;
import com.uc.mvvmtestvideo.model.Cast;
import com.uc.mvvmtestvideo.model.Genre;
import com.uc.mvvmtestvideo.model.Movie;
import com.uc.mvvmtestvideo.ui.MainActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.detailCover)
    ImageView detailCover;

    @BindView(R.id.detailPoster)
    ImageView detailPoster;

    @BindView(R.id.detailTitle)
    TextView detailTitle;

    @BindView(R.id.detailPopular)
    TextView detailPopular;

    @BindView(R.id.detailDesc)
    TextView detailDesc;

    @BindView(R.id.detailGenre)
    TextView detailGenre;

    @BindView(R.id.recViewCast)
    RecyclerView recViewCast;

    private Movie movie;
//    private tv
    private DetailViewModel viewModel;
    private CastAdapter adapter;

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        recViewCast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CastAdapter(getActivity());

        if(getArguments() != null){
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            //tv show

            if(movie != null){
                setupMovie(movie);
                observeViewModel(Integer.parseInt(movie.getIdMovie()));

            }else {
                //tv show
            }

        }

    }

    private void observeViewModel(int id){
        viewModel.getMovieGenre(id).observe(requireActivity(),genres -> {
            if(genres != null){
                for (int i=0;i<genres.size();i++){
                    Genre genre = genres.get(i);
                    if (i < genres.size() - 1){
                        detailGenre.append(genre.getName() + " | ");
                    }else{
                        detailGenre.append(genre.getName());
                    }
                }
            }
        });
        viewModel.getMovieCast(id).observe(requireActivity(), casts -> {
            if(casts != null){
                adapter.setListCast(casts);
                adapter.notifyDataSetChanged();
                recViewCast.setAdapter(adapter);
            }
        });
    }

    private void setupMovie(Movie movie){
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getPoster()).into(detailCover);
        Glide.with(getActivity()).load(movie.getPoster()).into(detailPoster);
        detailTitle.setText(movie.getTitle());
        detailPopular.setText(movie.getPopularity());
        detailDesc.setText(movie.getDescription());
    }

}