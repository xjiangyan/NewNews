package newnews.huiiuh.com.newnews.Pager;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import newnews.huiiuh.com.newnews.R;

/**
 * Created by hp on 2017/8/23.
 */

public class Base_Pager {
    private final Context context;
    private View rootview;
    private ListView mList_newdetail;

    public Base_Pager(Context context) {
        super();
        this.context = context;
        this.rootview = initView();

    }


    private View initView() {
        View view = View.inflate(context, R.layout.basenew_pager, null);
        mList_newdetail = (ListView) view.findViewById(R.id.list_newdetail);
        return mList_newdetail;
    }

    public void initData() {

    }

}
