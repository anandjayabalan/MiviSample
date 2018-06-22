package com.anandj.mivitest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.anandj.mivitest.model.UserInfoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<UserInfoModel> tabList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabList = new ArrayList<>();

        String data = loadJSONFromAssets();

        try {
            if (data != null) {
                //Parsing json data
                JSONObject joData = new JSONObject(data);
                JSONArray jaIncluded = joData.getJSONArray("included");
                for (int i = 0; i < jaIncluded.length(); i++) {
                    JSONObject joItem = jaIncluded.getJSONObject(i);

                    JSONObject joAttributes = joItem.getJSONObject("attributes");
                    tabList.add(new UserInfoModel(joItem.optString("type", ""), joAttributes.toString()));
                }

                //Binding pages to tab
                PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //Adapter for viewpager
    class PagerAdapter extends FragmentStatePagerAdapter {

        PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            //passing info data to Fragment through bundles
            Bundle btl = new Bundle();
            btl.putString("info", tabList.get(position).getUserInfo());
            Fragment fragment = new UserInfoFragment();
            fragment.setArguments(btl);
            return fragment;
        }

        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position).getTabName();
        }
    }

    //Loading data from assets file
    private String loadJSONFromAssets() {
        String sampleJSON = null;
        try {

            InputStream inputStream = getAssets().open("sampleInputs.json");
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            sampleJSON = new String(data, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sampleJSON;
    }
}
