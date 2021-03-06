package be.ehb.jokerapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import be.ehb.jokerapp.R;
import be.ehb.jokerapp.model.Joke;
import be.ehb.jokerapp.model.JokeViewModel;
import be.ehb.jokerapp.util.JokeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokesListFragment extends Fragment {


    public JokesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_jokes_list, container, false);


        //verwijzing naar UI
        RecyclerView rvJokes = rootView.findViewById(R.id.rv_jokes);

        //opvulling recycler, kan als lijst of grid
        rvJokes.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));

        //adapter, nodig om data om te zetten in iets visueel
        JokeAdapter adapter = new JokeAdapter();
        rvJokes.setAdapter(adapter);

        //verwijzen naar ViewModel, waar staat alle data
        JokeViewModel model = new ViewModelProvider(this).get(JokeViewModel.class);
        model.getJokes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Joke>>() {
            @Override
            public void onChanged(ArrayList<Joke> jokes) {
                adapter.addItems(jokes);

            }
        });


        return rootView;
    }

}
