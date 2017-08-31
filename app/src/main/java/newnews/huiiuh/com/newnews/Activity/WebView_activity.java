package newnews.huiiuh.com.newnews.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.onekeyshare.OnekeyShare;
import newnews.huiiuh.com.newnews.R;
import newnews.huiiuh.com.newnews.Util.SpUtil;
import newnews.huiiuh.com.newnews.Util.StatusBarCompat;

public class WebView_activity extends Activity implements View.OnClickListener {

    private WebView mWebView;
    private ImageView mIv_bacak;
    private ImageView mIv_more;


    int num = 100;
    int nums = 2;
    private ProgressBar mNews_load_progress;
    String[] choice = new String[]{"超小字体", "小字体", "普通字体", "大字体", "超大字体"};

    int TextZoom[] = new int[]{50, 75, 100, 150, 200};
    int textsizechoice[] = new int[]{0, 1, 2, 3, 4};
    private PopupWindow mPopupWindow;
    private View mDivier;
    private ImageView mIcon_share;
    private ImageView mWritemessage;
    private ImageView mStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        StatusBarCompat.compat(this, Color.WHITE);

        init();

    }

    private void init() {

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.webview_activity);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置优先使用缓存
        mWebView.loadUrl(url);


        mNews_load_progress = (ProgressBar) findViewById(R.id.news_load_progress);
        mDivier = (View) findViewById(R.id.divier);
        mIv_bacak = (ImageView) findViewById(R.id.iv_back);
        mIv_more = (ImageView) findViewById(R.id.iv_more);
        mIcon_share = (ImageView) findViewById(R.id.icon_share);
        mWritemessage = (ImageView) findViewById(R.id.writemessage);
        mStar = (ImageView) findViewById(R.id.star);
        mIv_bacak.setOnClickListener(this);
        mIv_more.setOnClickListener(this);
        mIcon_share.setOnClickListener(this);
        mWritemessage.setOnClickListener(this);
        mStar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:
                ShowMore();
                break;
            case R.id.share:
                showShare();
                mPopupWindow.dismiss();
                break;
            case R.id.icon_share:
                showShare();

                break;
            case R.id.star:
                if(mStar.isSelected()){
                    mStar.setSelected(false);

                Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                }else if(!mStar.isSelected()){
                    mStar.setSelected(true);
                Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.writemessage:
                Toast.makeText(this, "写评论", Toast.LENGTH_SHORT).show();
                break;
            case R.id.changetextsize:
                final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                nums = SpUtil.getInt(getApplicationContext(), "textsizechoice", 2);
                num = SpUtil.getInt(getApplicationContext(), "textsize", 100);
                mWebView.getSettings().setTextZoom(num);
                alert.setSingleChoiceItems(choice, nums, new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                num = TextZoom[0];
                                nums = textsizechoice[0];
                                break;
                            case 1:
                                num = TextZoom[1];
                                nums = textsizechoice[1];

                                break;
                            case 2:
                                num = TextZoom[2];
                                nums = textsizechoice[2];

                                break;
                            case 3:
                                num = TextZoom[3];
                                nums = textsizechoice[3];

                                break;

                            case 4:
                                num = TextZoom[4];
                                nums = textsizechoice[4];


                                break;
                        }

                    }
                });
                alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mWebView.getSettings().setTextZoom(num);
                        SpUtil.putInt(getApplicationContext(), "textsizechoice", nums);
                        SpUtil.putInt(getApplicationContext(), "textsize", num);

                    }
                });
                alert.show();
                mPopupWindow.dismiss();
                break;
        }
    }

    private void showShare() {

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        String Thumbnail_pic_s = getIntent().getStringExtra("Thumbnail_pic_s");
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);


        // text是分享文本，所有平台都需要这个字段
        oks.setText(title);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(Thumbnail_pic_s);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);


        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(this);
    }

    private void ShowMore() {

        View popupview = View.inflate(getApplicationContext(), R.layout.more_item, null);
        mPopupWindow = new PopupWindow(popupview, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mPopupWindow.showAsDropDown(mDivier, 0, 0, Gravity.RIGHT);
        TextView share = (TextView) popupview.findViewById(R.id.share);
        TextView changetextsize = (TextView) popupview.findViewById(R.id.changetextsize);
        share.setOnClickListener(this);
        changetextsize.setOnClickListener(this);

    }


//    // 注入js函数监听
//    private void addImageClickListner() {
//        // 这段js函数的功能就是，遍历承有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
//        mWebView.loadUrl("javascript:(function(){"
//                + "var objs = document.getElementsByTagName(\"img\"); "
//                + "for(var i=0;i<objs.length;i++)  " + "{"
//                + "    objs[i].onclick=function()  " + "    {  "
//                + "        window.imagelistner.openImage(this.src);  "
//                + "    }  " + "}" + "})()");
//    }
//
//    // js通信接口
//    public class JavascriptInterface {
//
//        private Context context;
//
//        public JavascriptInterface(Context context) {
//            this.context = context;
//        }
//
//        public void openImage(String img) {
//            System.out.println(img);
//            //
//            Intent intent = new Intent();
//            intent.putExtra("image", img);
//            intent.setClass(context, ShowWebImageActivity.class);
//            context.startActivity(intent);
//            System.out.println(img);
//        }
//    }

    // 监听
    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {


            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            // addImageClickListner();
            mWebView.getSettings().setBuiltInZoomControls(true);//添加缩放按钮
            mWebView.getSettings().setUseWideViewPort(true);//双击放大缩小
            mWebView.getSettings().setJavaScriptEnabled(true);//设置支持js
            mNews_load_progress.setVisibility(View.GONE);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            num = SpUtil.getInt(getApplicationContext(), "textsize", 100);
            mWebView.getSettings().setTextZoom(num);

            super.onPageStarted(view, url, favicon);
        }

//        @Override
//        public void onReceivedError(WebView view, int errorCode,
//                                    String description, String failingUrl) {
//
//            super.onReceivedError(view, errorCode, description, failingUrl);
//
//        }
    }
}
