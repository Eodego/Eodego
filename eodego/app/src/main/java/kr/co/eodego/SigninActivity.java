package kr.co.eodego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class SigninActivity extends BaseActivity{

	
	RelativeLayout signin_layout;
	LayoutInflater mInflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_activity);
		
		signin_layout = (RelativeLayout)findViewById(R.id.signin_layout);
		mInflater = (LayoutInflater) getBaseContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		LinearLayout signin_layout_1 = (LinearLayout)mInflater.inflate(R.layout.signin_inflate_layout_1, null);
		signin_layout_1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		ImageView next_but_1 = (ImageView)signin_layout_1.findViewById(R.id.signin_inflate_1_next_but);
		signin_layout.addView(signin_layout_1);
		
		next_but_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {			
				signin_layout.removeAllViews();
				LinearLayout signin_layout_2 = (LinearLayout)mInflater.inflate(R.layout.signin_inflate_layout_2, null);
				signin_layout_2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				
				final Button male_but = (Button) signin_layout_2.findViewById(R.id.signin_inflate_2_male_but);
				final Button female_but = (Button) signin_layout_2.findViewById(R.id.signin_inflate_2_female_but);
				
				male_but.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						male_but.setTextColor(Color.WHITE);
						female_but.setTextColor(Color.rgb(5,201,201));
						male_but.setSelected(true);
						female_but.setSelected(false);
					}
				});
				
				female_but.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						female_but.setTextColor(Color.WHITE);
						male_but.setTextColor(Color.rgb(5,201,201));
						male_but.setSelected(false);
						female_but.setSelected(true);
					}
				});
				
				
				
				ImageView next_but_2 = (ImageView)signin_layout_2.findViewById(R.id.signin_inflate_2_next_but);
				
				signin_layout.addView(signin_layout_2);
				next_but_2.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						signin_layout.removeAllViews();
						LinearLayout signin_layout_3 = (LinearLayout)mInflater.inflate(R.layout.signin_inflate_layout_3, null);
						signin_layout_3.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
						
						final Button phone_but = (Button)signin_layout_3.findViewById(R.id.signin_inflate_3_phone_but);
						phone_but.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								phone_but.setText("인증완료");
								phone_but.setSelected(true);
							}
						});
						
						
						ImageView next_but_3 = (ImageView)signin_layout_3.findViewById(R.id.signin_inflate_3_next_but);
						signin_layout.addView(signin_layout_3);
						next_but_3.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								Intent intent = new Intent(SigninActivity.this, MainActivity.class);
								startActivity(intent);
							}
						});
					}
				});
				
			}
		});
		
		
		
		
		
	}
	
	

}
