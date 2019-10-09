package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    VpAdapter vpAdapter;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager_menu)
    ViewPager viewPagerMenu;
    @BindView(R.id.tab_layout_menu)
    TabLayout tabLayoutMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        vpAdapter = new VpAdapter(getSupportFragmentManager());
        //fragment
        HomeFragment mHomeFragment = new HomeFragment();
        // ProfileFragment
        ProfileFragment mProfileFragment = new ProfileFragment();
        NotificationFragment mNotificationFragment = new NotificationFragment();
        vpAdapter.addFragment(mHomeFragment, "");
        vpAdapter.addFragment(mProfileFragment, "");
        vpAdapter.addFragment(mNotificationFragment, "");
        viewPagerMenu.setAdapter(vpAdapter);

        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(R.drawable.ic_launcher_background).setText("Home"));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(R.drawable.ic_launcher_background).setText("Profile"));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(R.drawable.ic_launcher_background).setText("Notifikasi"));


        tabLayoutMenu.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMenu.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        inflateDivider();
    }
    void inflateDivider() {
        for (int i = 0; i < tabLayoutMenu.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayoutMenu.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayoutMenu, false);
            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.img_tab);
            tabTextView.setText(tab.getText()); imageView.setImageDrawable(tab.getIcon());
            tab.setCustomView(relativeLayout);
        }
    }
}
