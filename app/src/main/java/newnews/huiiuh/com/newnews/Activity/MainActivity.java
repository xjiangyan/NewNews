package newnews.huiiuh.com.newnews.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import newnews.huiiuh.com.newnews.Base.Base_Fragment;
import newnews.huiiuh.com.newnews.Fragment.Fragment_Main;
import newnews.huiiuh.com.newnews.Fragment.Fragment_User;
import newnews.huiiuh.com.newnews.Fragment.Fragment_Video;
import newnews.huiiuh.com.newnews.Fragment.Fragment_TuiJian;
import newnews.huiiuh.com.newnews.R;
import newnews.huiiuh.com.newnews.Util.StatusBarCompat;

public class MainActivity extends FragmentActivity {


    FrameLayout mFrameLayout;
    RadioGroup mRgMain;
    private ArrayList<Base_Fragment> mFragmentlist;
    private int position = 0;
    private FragmentTransaction mFt;
    private SlidingMenu mMenu;
    private Button but_slidelogin;
    private ImageView image_userimage;
    private ListView mLv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        //设置状态栏跟通知栏透明 让窗体控件显示到（拉伸）到通知栏位置 以达到沉浸的效果


        StatusBarCompat.compat(this, Color.parseColor("#ffff4444"));

        CheckNet();
        init();
       initmenu();
    }

    private void CheckNet() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifi = connectivity.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnected();
        boolean network = connectivity.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnected();
        if (!wifi && !network) {
            Toast.makeText(getApplicationContext(), "请连接网络", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();

            if (mMenu.isMenuShowing()) {
                mMenu.toggle();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    private void initmenu() {

        mMenu = new SlidingMenu(this);
        mMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式 全屏触摸
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mMenu.setShadowWidthRes(R.dimen.shadow_width);
        mMenu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        mMenu.setFadeDegree(0.35f);
        // 把滑动菜单添加进所有的Activity中
        mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 为侧滑菜单设置布局
        mMenu.setMenu(R.layout.slidingmenu);

        but_slidelogin = (Button) findViewById(R.id.but_slidelogin);
        image_userimage = (ImageView) findViewById(R.id.image_userimage);
        TextView te_username = (TextView) findViewById(R.id.te_username);
        but_slidelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        LoginAndRegister.class);

                startActivity(intent);
            }
        });
        image_userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, UserInfos.class);

                startActivity(intent1);
            }
        });

        mLv_menu = (ListView) findViewById(R.id.lv_menu);
        final MyBaseAdapter myAdapter = new MyBaseAdapter();
        mLv_menu.setAdapter(myAdapter);
        mLv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:

//                        Message message = Message.obtain();
//                        message.obj = "http:\\qzapp.qlogo.cn/qzapp/222222/9D06FB6C95B06B3F35F803D40E796F8D/100";
//                        message.what = 22;
//                        mHandler.sendMessage(message);
                        Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void init() {
        mFrameLayout = (FrameLayout) findViewById(R.id.main_contain);
        mRgMain = (RadioGroup) findViewById(R.id.rg_main);
        mFragmentlist = new ArrayList<>();
        mFragmentlist.add(new Fragment_Main());
        mFragmentlist.add(new Fragment_TuiJian());
        mFragmentlist.add(new Fragment_Video());
        mFragmentlist.add(new Fragment_User());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction mFt2 = fm.beginTransaction();

        mRgMain.check(R.id.rb_home);
        mFt2.add(R.id.main_contain, mFragmentlist.get(0)).commit();
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentManager fm2 = getSupportFragmentManager();
                mFt = fm2.beginTransaction();
                hideFragments();
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_recommend:
                        position = 1;
                        break;
                    case R.id.rb_video:
                        position = 2;
                        break;
                    case R.id.rb_user:
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                switchfragment(position);

                Log.d("MainActivity", "position:" + position);
            }
        });


    }

    private void switchfragment(int position) {

        if (!mFragmentlist.get(position).isAdded()) {
            mFt.add(R.id.main_contain, mFragmentlist.get(position));
            Log.d("MainActivity", "添加了");
        }
        mFt.show(mFragmentlist.get(position));
        mFt.commit();
    }

    private void hideFragments() {
        for (int i = 0; i < mFragmentlist.size(); i++) {
            mFt.hide(mFragmentlist.get(i));
        }
    }


    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mFragmentlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View views;
            if (convertView == null) {
                views = View.inflate(getApplicationContext(), R.layout.listview_menuitem, null);
            } else {
                views = convertView;
            }
            TextView tv_menuitem = (TextView) views.findViewById(R.id.tv_menuitem);
            tv_menuitem.setText(position + "");


            return views;
        }
    }
}

//    private class MyCheckChangeListener implements RadioGroup.OnCheckedChangeListener {
//        @Override
//        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction transaction = fm.beginTransaction();
//            hideFragments(transaction);
//            switch (checkedId) {
//                case R.id.rt_main:
//                    Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
//                    if (mainactivity == null) {
//                        mainactivity = new Fragment_Main();
//                        //   transaction.replace(R.id.main_contain, new Fragment_Main(), "mainfragment").commit();
//                        transaction.add(R.id.main_contain, mainactivity);
//                    } else {
//
//                        transaction.show(mainactivity);
//                    }
//                    break;
//                case R.id.rt_news:
//                    Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
//                    if (mFragment_news == null) {
//                        mFragment_news = new Fragment_News();
//                        // transaction.replace(R.id.main_contain, new Fragment_News(), "newsfragment").commit();
//                        transaction.add(R.id.main_contain, mFragment_news);
//                    } else {
//                        transaction.show(mFragment_news);
//                    }
//
//                    break;
//                case R.id.rt_smart:
//                    Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
//                    if (mFragment_smart == null) {
//                        mFragment_smart = new Fragment_Video();
//                        //  transaction.replace(R.id.main_contain, new Fragment_Video(), "smartfragment").commit();
//                        transaction.add(R.id.main_contain, mFragment_smart);
//                    } else {
//                        transaction.show(mFragment_smart);
//                    }
//
//
//                    break;
//                case R.id.rt_gov:
//                    Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
//                    if (mFragment_gov == null) {
//                        mFragment_gov = new Fragment_Gov();
//                        //  transaction.replace(R.id.main_contain, new Fragment_Gov(), "govfragment").commit();
//                        transaction.add(R.id.main_contain, mFragment_gov);
//                    } else {
//                        transaction.show(mFragment_gov);
//                    }
//                    break;
//                case R.id.rt_setting:
//                    Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
//                    if (mFragment_setting == null) {
//                        mFragment_setting = new Fragment_Setting();
//                        // transaction.replace(R.id.main_contain, new Fragment_Setting(), "settingfragment").commit();
//                        transaction.add(R.id.main_contain, mFragment_setting);
//                    } else {
//                        transaction.show(mFragment_setting);
//                    }
//                    break;
//            }
//            transaction.commit();
//        }
//
//    }

//    private void hideFragments(FragmentTransaction transaction) {
//        if (mainactivity != null) {
//            transaction.hide(mainactivity);
//        }
//        if (mFragment_news != null) {
//            transaction.hide(mFragment_news);
//        }
//        if (mFragment_smart != null) {
//            transaction.hide(mFragment_smart);
//        }
//        if (mFragment_gov != null) {
//            transaction.hide(mFragment_gov);
//        }
//        if (mFragment_setting != null) {
//            transaction.hide(mFragment_setting);
//        }
//
//    }



