package newnews.huiiuh.com.newnews.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import newnews.huiiuh.com.newnews.R;

/**
 * Created by hp on 2017/8/10.
 */

class ShowWebImageActivity extends Activity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.showwebimageactivity);
    }
}
