package newnews.huiiuh.com.newnews.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import newnews.huiiuh.com.newnews.R;
import newnews.huiiuh.com.newnews.Util.DensityUtil;
import newnews.huiiuh.com.newnews.Util.SpUtil;

public class Splash_Activity extends AppCompatActivity {

    private ViewPager mViewpager_splash;
    private int[] mDrawables;
    private ArrayList<View> mList;
    private ImageView[] imagedos;
    private Button mBtn_fotostart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SpUtil.getBooelan(getApplicationContext(), "isused", false)) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {

            setContentView(R.layout.activity_splash);
            mDrawables = new int[]{R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4, R.drawable.logo5};
            mList = new ArrayList<>();
            init();
            initdos();
        }

    }

    private void init() {

        ImageView mImageView;
        for (int i = 0; i < mDrawables.length; i++) {
            mImageView = new ImageView(this);
            mImageView.setImageResource(mDrawables[i]);
            mList.add(mImageView);
        }
        mViewpager_splash = (ViewPager) findViewById(R.id.viewpager_splash);
        mViewpager_splash.setAdapter(new MyPagerAdapter());
        //  mViewpager_splash.setCurrentItem(50 * mDrawables.length);
        mViewpager_splash.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < imagedos.length; i++) {
                    if (i == position % mDrawables.length) {
                        imagedos[i].setBackgroundResource(R.drawable.dot_selected);
                    } else {
                        imagedos[i].setBackgroundResource(R.drawable.dot_unselected);
                    }
                    if (position % mDrawables.length == mDrawables.length - 1) {
                        mBtn_fotostart.setVisibility(View.VISIBLE);
                    } else {
                        mBtn_fotostart.setVisibility(View.INVISIBLE);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBtn_fotostart = (Button) findViewById(R.id.btn_gotostart);
        mBtn_fotostart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.putBooelan(getApplicationContext(), "isused", true);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mDrawables.length * 1000;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position % mDrawables.length));
            return mList.get(position % mDrawables.length);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position % mDrawables.length));
            //  super.destroyItem(container, position, object);
        }
    }

    /**
     * 初始化viewpager下的小圆点
     */
    private void initdos() {
        int width = new DensityUtil().dip2px(getApplicationContext(), 10);
        imagedos = new ImageView[mDrawables.length];
        for (int i = 0; i < mDrawables.length; i++) {
            LinearLayout linear = (LinearLayout) findViewById(R.id.dos_linear);
            imagedos[i] = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            params.leftMargin = 10;
            imagedos[i].setLayoutParams(params);
            if (i == 0) {

                imagedos[i].setBackgroundResource(R.drawable.dot_selected);
            } else {
                imagedos[i].setBackgroundResource(R.drawable.dot_unselected);

            }
            linear.addView(imagedos[i]);
        }
    }
}
