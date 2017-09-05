package newnews.huiiuh.com.newnews.Pager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import newnews.huiiuh.com.newnews.Activity.WebView_activity;
import newnews.huiiuh.com.newnews.Base.Base_Fragment;
import newnews.huiiuh.com.newnews.Bean.Bean;
import newnews.huiiuh.com.newnews.R;
import newnews.huiiuh.com.newnews.Util.SpUtil;

import static android.view.View.inflate;


/**
 * Created by hp on 2017/7/30.
 */

public class NewsDetail_Pager extends Base_Fragment {

    public static final String ISREADED = "isreaded";
    private final String type;
    private View mView;
    private ListView mLv_detail_top;
    private Bean mDataBean;
    private ArrayList<Bean> mList = new ArrayList<>();
    private PullToRefreshListView mPulltorefreshlistview;
    private MyBaseAdapter mMyBaseAdapter;
    private String url;
    Toast mToast;

    public NewsDetail_Pager(String url, String type) {
        this.url = url;
        this.type = type;
    }


    public View initView() {

        if (type == "shehui") {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        mView = inflate(getContext(), R.layout.detail_top, null);

        //   mLv_detail_top = (ListView) mView.findViewById(R.id.lv_detail_top);
        mPulltorefreshlistview = (PullToRefreshListView) mView.findViewById(R.id.pull_refresh_list);
        //        ListView listview = mPulltorefreshlistview.getRefreshableView();


        //   ListView listview = mPulltorefreshlistview.getRefreshableView();
        //        listview.addHeaderView();
        mPulltorefreshlistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mPulltorefreshlistview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        downData();
                        //    mMyBaseAdapter.notifyDataSetChanged();

                        mPulltorefreshlistview.onRefreshComplete();
//                        mToast = Toast.makeText(getContext(), "下拉刷新成功", Toast.LENGTH_SHORT);
//                        mToast.show();
                    }
                }, 1500);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mPulltorefreshlistview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        downData();

                        //  mMyBaseAdapter.notifyDataSetChanged();
                        mPulltorefreshlistview.onRefreshComplete();
//                        mToast = Toast.makeText(getContext(), "上拉刷新成功", Toast.LENGTH_SHORT);
//                        mToast.show();
                    }
                }, 1500);
            }
        });
        mPulltorefreshlistview.setOnItemClickListener(new MyOnItemClick());
        return mView;
    }


    public void initData() {
        downData();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {

                ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
                File file = new File(Environment.getExternalStorageDirectory().getPath(), type + ".text");


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
        //        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index?type=top&key=4ba71cc1dc85a5e47cf5ae9490833a71");
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(result);
                mMyBaseAdapter = new MyBaseAdapter();
                mPulltorefreshlistview.setAdapter(mMyBaseAdapter);


                //                Log.d("NewsDetail_Pager", "下载的数据================" + mDataBean.getResult().getData().toString());
                Log.d("NewsDetail_Pager", "下载的数据================" + result);
                Log.d("NewsDetail_Pager", "下载的数据数量================" + mDataBean.getResult().getData().size());


                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    try {

                        File file = new File(Environment.getExternalStorageDirectory().getPath(), type + ".text");


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
                //


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if(type=="shehui"){

                    mToast = Toast.makeText(getContext(), "网络出错了！", Toast.LENGTH_SHORT);
                    mToast.show();
                }
                try {

                    File file = new File(Environment.getExternalStorageDirectory().getPath(), type + ".text");

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
                        // Toast.makeText(mContext, "缓存的内容" + stream.toString(), Toast.LENGTH_SHORT).show();

                        processData(stream.toString());
                        mMyBaseAdapter = new MyBaseAdapter();
                        //  mPulltorefreshlistview.setAdapter(mMyBaseAdapter);
                        mMyBaseAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.e("文本获取失败");
                    //  Toast.makeText(mContext, "文本获取失败", Toast.LENGTH_SHORT).show();
                }
                Log.e("tag", ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                // Toast.makeText(getContext(), "联网取消！", Toast.LENGTH_SHORT).show();
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
        Log.d("NewsDetail_Pager", "解析成功");
    }


    //给item点击设置灰色
    public class MyOnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String isreaded = SpUtil.getString(getContext(), ISREADED, "");
            if (!isreaded.contains(mDataBean.getResult().getData().get(position - 1).getUniquekey())) {
                SpUtil.putString(getContext(), ISREADED, isreaded + mDataBean.getResult().getData().get(position - 1).getUniquekey() + ",");
                isreaded = SpUtil.getString(getContext(), ISREADED, "");
                Log.d("MyOnItemClick", "推荐添加后数量=====================" + isreaded);
                Log.d("MyOnItemClick", "推荐click位置" + position);
            }


            Intent intent = new Intent(getActivity(), WebView_activity.class);
            intent.putExtra("url", mDataBean.getResult().getData().get(position - 1).getUrl());
            intent.putExtra("title", mDataBean.getResult().getData().get(position - 1).getTitle());
            intent.putExtra("Thumbnail_pic_s", mDataBean.getResult().getData().get(position - 1).getThumbnail_pic_s());
            startActivity(intent);
            mMyBaseAdapter.notifyDataSetChanged();

        }
    }


    private class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDataBean.getResult().getData().size();
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

            tv_title.setText(mDataBean.getResult().getData().get(position).getTitle());
            tv_title2.setText(mDataBean.getResult().getData().get(position).getCategory());
            tv_title3.setText(mDataBean.getResult().getData().get(position).getDate());
            //            SmartImageView smartImage = (SmartImageView) view.findViewById(R.id.smartiv_news_image);
            //            smartImage.setImageUrl(mDataBean.getResult().getData().get(position).getThumbnail_pic_s());

            final ImageView smartImage = (ImageView) view.findViewById(R.id.smartiv_news_image);
            Glide.with(mContext).load(mDataBean.getResult().getData().get(position).getThumbnail_pic_s())
                    .into(smartImage);

            String isreadeds = SpUtil.getString(getContext(), ISREADED, "");

            if (isreadeds.contains(mDataBean.getResult().getData().get(position).getUniquekey())) {
                tv_title.setTextColor(Color.GRAY);
                tv_title2.setTextColor(Color.GRAY);
                tv_title3.setTextColor(Color.GRAY);
                Log.d("MyOnItemClick", "首页getview位置" + position);

            } else {
                tv_title.setTextColor(Color.BLACK);
                tv_title2.setTextColor(Color.BLACK);
                tv_title3.setTextColor(Color.BLACK);

            }
            return view;
        }
    }


    @Override
    //回调方法，跟onActivityResult一样
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  File file = new File(Environment.getExternalStorageDirectory().getPath(), "tuijian.text");
                Toast.makeText(mContext, "授权成功", Toast.LENGTH_SHORT).show();
            } else {
                // Permission Denied
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mToast != null) {

            mToast.cancel();
        }
    }
}
