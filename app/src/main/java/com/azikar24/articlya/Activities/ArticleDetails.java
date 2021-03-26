package com.azikar24.articlya.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.azikar24.articlya.Models.Article;
import com.azikar24.articlya.R;
import com.squareup.picasso.Picasso;

public class ArticleDetails extends AppCompatActivity {
    private Article article;
    private Toolbar toolbar;
    private TextView title, abstractt;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        init();
    }

    /**
     * Initialize the variables then call loadValues()
     */
    public void init() {
        article = (Article) getIntent().getSerializableExtra("article");
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        abstractt = findViewById(R.id.abstractt);
        imageView = findViewById(R.id.articleImage);
        loadValues();
    }

    /**
     * Load the data of the article object on the activity
     */
    private void loadValues() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        title.setText(article.getTitle());
        abstractt.setText(article.getAbstractt());
        abstractt.setMovementMethod(new ScrollingMovementMethod());

        Picasso.get().load(article.getImageUrl()).into(imageView);
    }

    /**
     * Visit the website to read more details about the article.
     * @param view : button
     * @return success or failure
     */
    public boolean readmore(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
            startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}