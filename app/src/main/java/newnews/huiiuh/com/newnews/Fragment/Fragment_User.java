package newnews.huiiuh.com.newnews.Fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.xutils.common.util.MD5;

import java.util.Arrays;
import java.util.List;

import newnews.huiiuh.com.newnews.Activity.LoginAndRegister;
import newnews.huiiuh.com.newnews.Base.Base_Fragment;
import newnews.huiiuh.com.newnews.R;
import newnews.huiiuh.com.newnews.Util.CircleImageView;
import newnews.huiiuh.com.newnews.Util.SpUtil;


/**
 * Created by hp on 2017/7/30.
 */

public class Fragment_User extends Base_Fragment implements View.OnClickListener {
    private Button butSlidelogin;
    private CircleImageView imageUserimage;
    private TextView teUsername;
    private TextView guanzhu;
    private TextView fensi;
    private TextView yuedu;

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_user, null);
        findViews(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SpUtil.getBooelan(mContext, "islogined", false)) {
            butSlidelogin.setVisibility(View.GONE);
            imageUserimage.setVisibility(View.VISIBLE);
            teUsername.setVisibility(View.VISIBLE);
            String imageresouse = SpUtil.getString(mContext, "imageresouse", "");
            if (imageresouse == "") {
                imageUserimage.setImageResource(R.drawable.hugh);
            } else {

                Glide.with(mContext).load(imageresouse).into(imageUserimage);
            }
            String username = SpUtil.getString(getContext(), "nickname", "");
            teUsername.setText(username);
        } else {
            butSlidelogin.setVisibility(View.VISIBLE);
            imageUserimage.setVisibility(View.GONE);
            teUsername.setVisibility(View.GONE);
        }
        String isreaded = SpUtil.getString(getContext(), "isreaded", "");
        Log.d("Fragment_User", "阅读数量" + isreaded);
        if (isreaded != "") {

            int m = howmuch(isreaded);
            if (m > 0) {
                yuedu.setText(m + "");

            }
        }
    }

    public int howmuch(String zifuchuan) {

        String[] values = zifuchuan.split(",");
        List<String> list = Arrays.asList(values);
        int num = values.length;
        return num;
    }

    private void findViews(View view) {
        butSlidelogin = (Button) view.findViewById(R.id.but_slidelogin);
        imageUserimage = (CircleImageView) view.findViewById(R.id.image_userimage);
        teUsername = (TextView) view.findViewById(R.id.te_username);
        guanzhu = (TextView) view.findViewById(R.id.tv_gaunzhu);
        fensi = (TextView) view.findViewById(R.id.tv_fensi);
        yuedu = (TextView) view.findViewById(R.id.yuedu);


        String md5 = MD5.md5("5555");
        Toast.makeText(mContext, "md5加密" + md5, Toast.LENGTH_SHORT).show();
        butSlidelogin.setOnClickListener(this);
        imageUserimage.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        fensi.setOnClickListener(this);
        yuedu.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_slidelogin:
                Intent intent = new Intent(getActivity(), LoginAndRegister.class);
                startActivity(intent);
                break;
            case R.id.image_userimage:
                break;
            case R.id.te_username:
                break;
            case R.id.tv_gaunzhu:
                break;
            case R.id.tv_fensi:
                break;
            case R.id.yuedu:
                break;

        }
    }


}
