package com.azikar24.articlya;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.test.rule.ActivityTestRule;

import com.azikar24.articlya.Activities.ArticleDetails;
import com.azikar24.articlya.Models.Article;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleDetailsTest {

    @Rule
    public ActivityTestRule<ArticleDetails> articleDetailsActivityTestRule = new ActivityTestRule<>(ArticleDetails.class, false, false);
    public ArticleDetails articleDetails = null;


    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        Article article = new Article("Title", "by", "00/00/0000", "category", "abstract", "", "https://google.com");
        intent.putExtra("article", article);
        articleDetails = articleDetailsActivityTestRule.launchActivity(intent);

    }

    @After
    public void tearDown() throws Exception {
        articleDetails = null;
    }

    @Test
    public void onCreate() {
        System.out.println("Testing if the activity runs correctly");
        View toolbar = articleDetails.findViewById(R.id.toolbar);
        assertNotNull(toolbar);
    }

    @Test
    public void readmore() {
        System.out.println("Testing the Read More button");
        View btn = articleDetails.findViewById(R.id.button);
        articleDetails.readmore(btn);
    }

    @Test
    public void testImage() {
        System.out.println("Testing the image load");
        ImageView img = articleDetails.findViewById(R.id.articleImage);
        System.out.println("tb: " + img.getDrawable());
    }


}