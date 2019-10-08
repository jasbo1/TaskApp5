package com.taskapp.onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.taskapp.R;

public class OnBoardActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tab_layout);
        //ViewPagerAdapter adapter= new ViewPagerAdapter (FragmentManager,1);
        tabLayout.setupWithViewPager(viewPager, true);
       // viewPager.setAdapter(adapter);


        initViewPager();
    }

    private void initViewPager() {

        ViewPager viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),0));
    }
}
