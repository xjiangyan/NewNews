package newnews.huiiuh.com.newnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import newnews.huiiuh.com.newnews.R;


/**
 * Created by hp on 2017/7/30.
 */

public class FragmentItemView extends RelativeLayout {
    private TextView tv_messagenum;
    private TextView tv_fragmentitem;
    private ImageView iv_fragmentitem;
    private int NormalIcon, FouseIcon;

    public FragmentItemView(Context context) {
        this(context, null);
    }

    public FragmentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.fragment_gov, this);
        iv_fragmentitem = (ImageView) findViewById(R.id.iv_fragmentitem);
        tv_fragmentitem = (TextView) findViewById(R.id.tv_fragmentitem);
        tv_messagenum = (TextView) findViewById(R.id.tv_messagenum);
    }

    public void setTabTitle(String tabTitle) {
        tv_fragmentitem.setText(tabTitle);
    }

    public void setSelected(boolean selected) {
        if (selected) {
            iv_fragmentitem.setImageResource(FouseIcon);
        } else {
            iv_fragmentitem.setImageResource(NormalIcon);
        }
    }


    public void setTabIcon(int NormalIcon, int FouseIcon) {
        this.NormalIcon = NormalIcon;
        this.FouseIcon = FouseIcon;
        iv_fragmentitem.setImageResource(NormalIcon);
    }

    public void setMessagenum(int num) {
        if (num <= 0) {

            tv_messagenum.setVisibility(INVISIBLE);
        } else {
            if (num >= 99) {
                tv_messagenum.setText("99+");
            } else {
                tv_messagenum.setText(num + "");
            }
            tv_messagenum.setVisibility(VISIBLE);
        }
    }

}
