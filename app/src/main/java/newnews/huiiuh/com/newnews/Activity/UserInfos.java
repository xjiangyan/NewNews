package newnews.huiiuh.com.newnews.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import newnews.huiiuh.com.newnews.R;

public class UserInfos extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo);
		initui();
	}

	private void initui() {
		// TODO Auto-generated method stub
		ListView list = (ListView) findViewById(R.id.list_userinfo);
		list.setAdapter(new myadapter());
	}

	public class myadapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View view;
			TextView text;
			if (arg1 == null) {
				view = View.inflate(getApplicationContext(),
						R.layout.list_item, null);

			} else {
				view = arg1;
			}

			text=(TextView) view.findViewById(R.id.list_textview);
			switch (arg0) {
				case 0:
					text.setText("个人信息");
					break;

				default:
					text.setText("切换用户");
					break;
			}
			return view;
		}

	}
}

