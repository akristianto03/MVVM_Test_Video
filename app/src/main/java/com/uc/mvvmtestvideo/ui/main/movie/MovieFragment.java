package com.uc.mvvmtestvideo.ui.main.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.uc.mvvmtestvideo.R;
import com.uc.mvvmtestvideo.adapter.MovieAdapter;
import com.uc.mvvmtestvideo.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    @BindView(R.id.recViewFilm)
    RecyclerView recViewFilm;

    private MovieViewModel viewModel;
    private MovieAdapter movieAdapter;

    public MovieFragment() {
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

        movieAdapter = new MovieAdapter(getContext());
        recViewFilm.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);

//        Movie movie = new Movie();

//        button.setOnClickListener(v -> {
//            NavDirections action = MovieFragmentDirections.actionDetailFragment(movie);
//            Navigation.findNavController(view).navigate(action);
//        });
    }

    private Observer<List<Movie>> observeViewModel = new Observer<List<Movie>>(){
        @Override
        public void onChanged(List<Movie> movies) {
            if(movies != null){
//                Movie movie = movies.get(0);
//                Toast.makeText(requireActivity(), movie.getTitle(),Toast.LENGTH_SHORT).show();
                movieAdapter.setListMovie(movies);
                movieAdapter.notifyDataSetChanged();
                recViewFilm.setAdapter(movieAdapter);
            }
        }
    };

}