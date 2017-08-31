package newnews.huiiuh.com.newnews.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.image.SmartImageView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import newnews.huiiuh.com.newnews.Activity.WebView_activity;
import newnews.huiiuh.com.newnews.Base.Base_Fragment;
import newnews.huiiuh.com.newnews.Bean.Bean;
import newnews.huiiuh.com.newnews.R;
import newnews.huiiuh.com.newnews.Util.SpUtil;

import static android.view.View.inflate;


/**
 * Created by hp on 2017/7/30.
 */

public class Fragment_TuiJian extends Base_Fragment {

    public static final String ISREADED = "isreaded";
    private View mView;
    private ListView mLv_detail_top;
    private Bean mDataBean;
    private ArrayList<Bean> mList = new ArrayList<>();
    private PullToRefreshListView mPulltorefreshlistview;
    private MyBaseAdapter mMyBaseAdapter;
    private String url;
    private Banner mBanner;
    private ListView mListview;
    private View mBannerview;


    public View initView() {

        mView = View.inflate(getContext(), R.layout.fragment_tuijian, null);

        //   mLv_detail_top = (ListView) mView.findViewById(R.id.lv_detail_top);
        mPulltorefreshlistview = (PullToRefreshListView) mView.findViewById(R.id.pull_refresh_list);
//        ListView listview = mPulltorefreshlistview.getRefreshableView();


        mBannerview = View.inflate(getContext(), R.layout.bannerview, null);
        mBanner = (Banner) mBannerview.findViewById(R.id.banenr);

        mListview = mPulltorefreshlistview.getRefreshableView();

        mListview.addHeaderView(mBannerview);
        mPulltorefreshlistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getContext(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
                mPulltorefreshlistview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        downData();

                        //    mMyBaseAdapter.notifyDataSetChanged();

                        mPulltorefreshlistview.onRefreshComplete();
                    }
                }, 1500);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getContext(), "上拉刷新成功", Toast.LENGTH_SHORT).show();
                mPulltorefreshlistview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        downData();

                        //  mMyBaseAdapter.notifyDataSetChanged();
                        mPulltorefreshlistview.onRefreshComplete();

                    }
                }, 1500);
            }
        });
        mPulltorefreshlistview.setOnItemClickListener(new MyOnItemClick());
        return mView;
    }


    public void initData() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {

                ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
                File file = new File(Environment.getExternalStorageDirectory().getPath(), "tuijian.text");


                if (file.exists()) {

                    FileInputStream is = new FileInputStream(file);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        stream.write(buffer, 0, length);
                    }

                    is.close();
                    stream.close();

                    Log.d("NewsDetail_Pager", "缓存的内容" + stream.toString());
                    processData(stream.toString());
                    mMyBaseAdapter = new MyBaseAdapter();
                    mPulltorefreshlistview.setAdapter(mMyBaseAdapter);
                } else {
                    downData();
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("文本获取失败");
            }
        } else {
            downData();
        }
    }

    private void downData() {
        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index?type=top&key=4ba71cc1dc85a5e47cf5ae9490833a71");
//        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(result);
                Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show();


                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
                    try {

                        ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
                        File file = new File(Environment.getExternalStorageDirectory().getPath(),  "tuijian.text");


                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        //保存文本数据
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(result.getBytes());
                        fileOutputStream.close();
                        Log.d("NewsDetail_Pager", "文本缓存成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("NewsDetail_Pager", "错误信息" + e.getMessage());
                        Log.d("NewsDetail_Pager", "文本数据缓存失败");
                    }
                }
                mMyBaseAdapter = new MyBaseAdapter();
                mPulltorefreshlistview.setAdapter(mMyBaseAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getContext(), "网络出错了！", Toast.LENGTH_SHORT).show();
                Log.e("tag", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(getContext(), "联网取消！", Toast.LENGTH_SHORT).show();
                Log.e("tag", cex.getMessage());
            }

            @Override
            public void onFinished() {
                //  Toast.makeText(getContext(), "联网结束", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processData(String result) {
        Gson gson = new Gson();
        mDataBean = gson.fromJson(result, Bean.class);
        initBanner();

    }


    //给item点击设置灰色
    public class MyOnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String isreaded = SpUtil.getString(getContext(), ISREADED, "");
            if (!isreaded.contains(mDataBean.getResult().getData().get(position + 1).getUniquekey())) {
                SpUtil.putString(getContext(), ISREADED,isreaded+ mDataBean.getResult().getData().get(position + 1).getUniquekey() + ",");

                Log.d("MyOnItemClick", "推荐添加后数量=====================" + isreaded);
                Log.d("MyOnItemClick", "推荐click位置" + position);
            }

            Intent intent = new Intent(getActivity(), WebView_activity.class);
            intent.putExtra("url", mDataBean.getResult().getData().get(position + 1).getUrl());
            intent.putExtra("title", mDataBean.getResult().getData().get(position + 1).getTitle());
            intent.putExtra("Thumbnail_pic_s", mDataBean.getResult().getData().get(position + 1).getThumbnail_pic_s());
            startActivity(intent);
            mMyBaseAdapter.notifyDataSetChanged();

        }
    }


    private class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 27;
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
            View view;
            if (convertView == null) {

                view = inflate(getContext(), R.layout.listview_news_item, null);
            } else {

                view = convertView;
            }

            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_title2 = (TextView) view.findViewById(R.id.tv_title2);
            TextView tv_title3 = (TextView) view.findViewById(R.id.tv_title3);
//
            tv_title.setText(mDataBean.getResult().getData().get(position + 3).getTitle());
            tv_title2.setText(mDataBean.getResult().getData().get(position + 3).getCategory());
            tv_title3.setText(mDataBean.getResult().getData().get(position + 3).getDate());
            SmartImageView smartImage = (SmartImageView) view.findViewById(R.id.smartiv_news_image);
            smartImage.setImageUrl(mDataBean.getResult().getData().get(position + 3).getThumbnail_pic_s());

            String isreadeds = SpUtil.getString(getContext(), ISREADED, "");

            if (isreadeds.contains(mDataBean.getResult().getData().get(position + 3).getUniquekey())) {
                tv_title.setTextColor(Color.GRAY);
                tv_title2.setTextColor(Color.GRAY);
                tv_title3.setTextColor(Color.GRAY);
                Log.d("MyOnItemClick", "推荐getview位置" + position);

            } else {
                tv_title.setTextColor(Color.BLACK);
                tv_title2.setTextColor(Color.BLACK);
                tv_title3.setTextColor(Color.BLACK);

            }
            return view;
        }
    }


    private void initBanner() {
        List<String> bannerimages = new ArrayList<>();
        List<String> bannertitle = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            bannerimages.add(mDataBean.getResult().getData().get(i).getThumbnail_pic_s());
            Log.d("Fragment_TuiJian", "连接===========================" + mDataBean.getResult().getData().get(i).getThumbnail_pic_s());
            bannertitle.add(mDataBean.getResult().getData().get(i).getTitle());
            Log.d("Fragment_TuiJian", "标题===========================" + mDataBean.getResult().getData().get(i).getTitle());
        }

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//带标题有圆点指示器的banner
        mBanner.setBannerTitles(bannertitle);
        mBanner.setBannerAnimation(Transformer.Accordion);//切换手风琴效果
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImages(bannerimages).setImageLoader(new GlideImageLoader()).start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), WebView_activity.class);
                intent.putExtra("url", mDataBean.getResult().getData().get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
//        Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//        Uri uri = Uri.parse((String) path);
//        imageView.setImageURI(uri);
        }

//    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//    @Override
//    public ImageView createImageView(Context context) {
//        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//        return simpleDraweeView;
//    }
    }
}
