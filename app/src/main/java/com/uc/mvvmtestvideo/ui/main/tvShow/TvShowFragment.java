package com.uc.mvvmtestvideo.ui.main.tvShow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uc.mvvmtestvideo.R;
import com.uc.mvvmtestvideo.adapter.TvShowAdapter;
import com.uc.mvvmtestvideo.model.TvShow;
import com.uc.mvvmtestvideo.ui.MainActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFragment extends Fragment {

    @BindView(R.id.recViewFilm)
    RecyclerView recViewFilm;

    private TvShowViewModel viewModel;
    private TvShowAdapter tvShowAdapter;

    public TvShowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setIcon(R.drawable.ic_icon);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle("   Tv Shows");

        tvShowAdapter = new TvShowAdapter(getContext());
        recViewFilm.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);

    }

    private Observer<List<TvShow>> observeViewModel = new Observer<List<TvShow>>() {
        @Override
        public void onChanged(List<TvShow> tvShows) {
            if(tvShows != null){
                tvShowAdapter.setListTvShow(tvShows);
                tvShowAdapter.notifyDataSetChanged();
                recViewFilm.setAdapter(tvShowAdapter);
            }
        }
    };

}