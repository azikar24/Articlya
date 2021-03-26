package com.azikar24.articlya.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azikar24.articlya.Models.Article;
import com.azikar24.articlya.Repository.ArticleRepository;

import java.util.ArrayList;

public class ArticleListFragmentViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Article>> mArticle;

    private ArticleRepository mRepo;

    /**
     * Initiate the mArticle with loading it from the repository
     * @param context : getBaseContext() or getContext()
     * @param option : Period option  ArticleRepository.DAY, ArticleRepository.WEEK, ArticleRepository.MONTH
     */
    public void init(Context context, int option)   {
        if (mArticle != null) {
            return;
        }
        mRepo = ArticleRepository.getInstance(context);
        mArticle = mRepo.getArticles(option);
    }


    /**
     * Retrieve the mArticle LiveData
     * @return mArticle
     */
    public LiveData<ArrayList<Article>> getArticles() {
        if (mArticle == null) {
            mArticle = new MutableLiveData<>();
        }
        return mArticle;
    }


}
