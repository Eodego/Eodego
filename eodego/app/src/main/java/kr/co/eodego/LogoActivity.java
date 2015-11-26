package kr.co.eodego;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LogoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo_activity);
		
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				//Intent intent = new Intent(LogoActivity.this,HomeActivity.class);
				Intent intent = new Intent(LogoActivity.this,SigninActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		};
		
		handler.sendEmptyMessageDelayed(0, 1000);
		
	}

}
