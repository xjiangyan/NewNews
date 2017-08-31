package newnews.huiiuh.com.newnews.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import newnews.huiiuh.com.newnews.Activity.AppConstant;
import newnews.huiiuh.com.newnews.Base.Base_Fragment;
import newnews.huiiuh.com.newnews.Pager.NewsDetail_Pager;
import newnews.huiiuh.com.newnews.R;

/**
 * Created by hp on 2017/8/23.
 */

public class Fragment_Main extends Base_Fragment {

    @Override
    public void initData() {
        super.initData();
    }


    private ArrayList<Fragment> mMfragment;
    private String[] texts;
    private ViewPager mViewpager_main;


    public View initView() {
        View view = View.inflate(mContext, R.layout.mainfragment, null);

        mMfragment = new ArrayList<>();
//        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl+"top"+AppConstant.LastUrl));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "shehui" + AppConstant.LastUrl, "shehui"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "guonei" + AppConstant.LastUrl, "guonei"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "guoji" + AppConstant.LastUrl, "guoji"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "yule" + AppConstant.LastUrl, "yule"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "tiyu" + AppConstant.LastUrl, "tiyu"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "junshi" + AppConstant.LastUrl, "junshi"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "keji" + AppConstant.LastUrl, "keji"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "caijing" + AppConstant.LastUrl, "caijing"));
        mMfragment.add(new NewsDetail_Pager(AppConstant.FirstUrl + "shishang" + AppConstant.LastUrl, "shishang"));
        texts = new String[]{"社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};


        mViewpager_main = (ViewPager) view.findViewById(R.id.viewpager_main);
        mViewpager_main.setAdapter(new MyAdapter(getFragmentManager()));
        //  mViewpager_main.setAdapter(new MyViewPagerAdapter());
//        TabPageIndicator tabpageindicator = (TabPageIndicator) findViewById(R.id.tabpageindicator);
//        tabpageindicator.setViewPager(mViewpager_main);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewpager_main);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }


    private class MyAdapter extends FragmentPagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return texts[position];
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //  super.destroyItem(container, position, object);
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mMfragment.get(position);
        }

        @Override
        public int getCount() {
            return mMfragment.size();
        }
    }


//    private class MyViewPagerAdapter extends PagerAdapter {
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
////     super.destroyItem(container, position, object);
//        }
//
//        @Override
//        public int getCount() {
//            return texts.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object o) {
//            return view == o;
//        }
//    }
}
