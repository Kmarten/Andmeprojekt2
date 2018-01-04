package com.example.kasutaja.andmeprojekt;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ahto on 28.12.2017.
 */

public class CreateData extends AppCompatActivity {

    private PageAdapter pageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);

        pageAdapter = new PageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CreateFragment(), "Create Fragment");

        viewPager.setAdapter(adapter);
    }


}
