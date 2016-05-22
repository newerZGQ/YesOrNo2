package com.yesorno.zgq.yesorno;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.yesorno.zgq.yesorno.fragment.CustomItemsFragment;
import com.yesorno.zgq.yesorno.fragment.FourItemsFragment;
import com.yesorno.zgq.yesorno.fragment.OneItemsFragment;
import com.yesorno.zgq.yesorno.fragment.ThreeItemsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{
    private static int BACKGROUNDONE = R.color.colorActivityMainBackground1;
    private static int BACKGROUNDTWO = R.color.colorActivityMainBackground2;
    private static int BACKGROUNDTHR = R.color.colorActivityMainBackground3;
    private static int BACKGROUNDFOU = R.color.colorActivityMainBackground4;

    private OneItemsFragment oneItemsFragment;
    private ThreeItemsFragment thrItemsFragment;
    private FourItemsFragment fouItemsFragment;
    private CustomItemsFragment ctmItemsFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    private String oneItems;
    private String thrItems;
    private String fouItems;
    private String ctmItems;
    private List<String> titleList = new ArrayList<>();

    private ViewPager viewPager;
    private PagerTabStrip tabStrip;
    private LinearLayout rootLayout;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.setDebugMode( true );
        setContentView(R.layout.activity_main);
        initFragmentListData();
        initTitleListData();
        getMyFragmentManager();
        initView();
        setViewContent();
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private void getMyFragmentManager(){
        fragmentManager = getSupportFragmentManager();
    }
    private void initFragmentListData(){
        oneItemsFragment = OneItemsFragment.newInstance("","");
        thrItemsFragment = ThreeItemsFragment.newInstance("","");
        fouItemsFragment = FourItemsFragment.newInstance("","");
        ctmItemsFragment = CustomItemsFragment.newInstance("","");
        fragmentList.add(oneItemsFragment);
        fragmentList.add(thrItemsFragment);
        fragmentList.add(fouItemsFragment);
        fragmentList.add(ctmItemsFragment);
    }
    private void initTitleListData(){
        oneItems = getResources().getString(R.string.one_items_title);
        thrItems = getResources().getString(R.string.thr_items_title);
        fouItems = getResources().getString(R.string.fou_items_title);
        ctmItems = getResources().getString(R.string.ctm_items_title);
        titleList.add(oneItems);
        titleList.add(thrItems);
        titleList.add(fouItems);
        titleList.add(ctmItems);
    }
    private void initView(){
        rootLayout = (LinearLayout) findViewById(R.id.activity_main_rootlayout);
        viewPager = (ViewPager) findViewById(R.id.fragment_pager);
        tabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
    }
    private void setViewContent(){
        tabStrip.setDrawFullUnderline(false);
        tabStrip.setTabIndicatorColor(getResources().getColor(R.color.colorTransparent));
        rootLayout.setBackground(getResources().getDrawable(BACKGROUNDONE));
        viewPager.setAdapter(new MyPagerAdapter(fragmentManager, fragmentList, titleList));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onChangeBackground(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void onChangeBackground(int position){
        switch (position){
            case 0:
                rootLayout.setBackground(getResources().getDrawable(BACKGROUNDONE));
                break;
            case 1:
                rootLayout.setBackground(getResources().getDrawable(BACKGROUNDTWO));
                break;
            case 2:
                rootLayout.setBackground(getResources().getDrawable(BACKGROUNDTHR));
                break;
            case 3:
                rootLayout.setBackground(getResources().getDrawable(BACKGROUNDFOU));
                break;
        }
    }
    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String>   titleList;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList){
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return (titleList.size() > position) ? titleList.get(position) : "";
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
