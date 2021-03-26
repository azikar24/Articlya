package com.azikar24.articlya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.azikar24.articlya.Adapters.ViewPagerAdapter;
import com.azikar24.articlya.Fragments.ArticlesList;
import com.azikar24.articlya.Repository.ArticleRepository;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPageAdapter;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private TabLayout.OnTabSelectedListener tabLayoutSelectedListener() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (viewPageAdapter.getCount() > 0) {
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }


    /**
     * Initialize the variables then call setup()
     */
    public void init() {
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        tabLayout = findViewById(R.id.period_tab);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPageAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        setup();
    }

    /**
     * Setup the variables to be shown on the user interface
     */
    private void setup() {
        setSupportActionBar(toolbar);
        toolbarTitle.setText(R.string.app_name);
        tabLayout.addOnTabSelectedListener(tabLayoutSelectedListener());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        viewPageAdapter.addFragment(new ArticlesList(ArticleRepository.DAY));
        tabLayout.addTab(tabLayout.newTab().setText("Week"));
        viewPageAdapter.addFragment(new ArticlesList(ArticleRepository.WEEK));
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        viewPageAdapter.addFragment(new ArticlesList(ArticleRepository.MONTH));
        viewPager.setAdapter(viewPageAdapter);
    }
}