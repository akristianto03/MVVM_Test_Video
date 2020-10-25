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
import com.uc.mvvmtestvideo.model.TvShow;
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
    private TvShow tvShow;
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

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(false);
        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        recViewCast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CastAdapter(getActivity());

        if(getArguments() != null){
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvShow();

            if(movie != null){
                setupMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getIdMovie()));

            }else {
                setupTvShow(tvShow);
                observeTvShowViewModel(Integer.parseInt(tvShow.getIdTv()));
            }

        }

    }

    private void observeMovieViewModel(int id){
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

    private void observeTvShowViewModel(int id){
        viewModel.getTvShowGenre(id).observe(requireActivity(),genres -> {
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
        viewModel.getTvShowCast(id).observe(requireActivity(), casts -> {
            if (casts != null){
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

    private void setupTvShow(TvShow tvShow){
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getTitle());
        Glide.with(getActivity()).load(tvShow.getPoster()).into(detailCover);
        Glide.with(getActivity()).load(tvShow.getPoster()).into(detailPoster);
        detailTitle.setText(tvShow.getTitle());
        detailPopular.setText(tvShow.getPopularity());
        detailDesc.setText(tvShow.getDescription());
    }

}