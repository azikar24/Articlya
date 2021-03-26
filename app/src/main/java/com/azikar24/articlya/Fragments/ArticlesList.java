package com.azikar24.articlya.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azikar24.articlya.Adapters.ArticleListAdapter;
import com.azikar24.articlya.Models.Article;
import com.azikar24.articlya.R;
import com.azikar24.articlya.ViewModel.ArticleListFragmentViewModel;

import java.util.List;

public class ArticlesList extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArticleListAdapter adapter;
    private ArticleListFragmentViewModel viewModel;

    private int option;

    public ArticlesList() {
        // Required empty public constructor
    }

    /**
     * Initialize the option variable to get the correct data for the current fragment
     * @param option
     */
    public ArticlesList(int option) {
        this.option = option;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_articles_list, container, false);

        setup();
        return view;
    }

    /**
     * Setup the fragment with initializing the variables and start to observe the data from the modelview
     * to load it on the recyclerview
     */
    private void setup() {
        recyclerView = view.findViewById(R.id.recyclerView);

        viewModel = ViewModelProviders.of(this).get(ArticleListFragmentViewModel.class);
        viewModel.init(getContext(), option);

        viewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles1) {
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new ArticleListAdapter(getContext(), viewModel.getArticles().getValue());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}