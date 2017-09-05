package newnews.huiiuh.com.newnews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import newnews.huiiuh.com.newnews.R;
import newnews.huiiuh.com.newnews.Util.SpUtil;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UserInfo_Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplication(), LoginAndRegister.class);
                startActivity(intent);
                SpUtil.putBooelan(getApplicationContext(), "islogined", false);
                SpUtil.putBooelan(getApplicationContext(), "qqdenglu", false);
                SpUtil.putBooelan(getApplicationContext(), "huanxindenglu", false);
            }
        });
    }
}
