package kr.co.eodego;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	
	ImageView[] iv_list;
	TextView[] tv_list;
	public static int current_fragment_position = 0;
	public static final int frgmt_1 = 0;
	public static final int frgmt_2 = 1;
	public static final int frgmt_center = 2;
	public static final int frgmt_3 = 3;
	public static final int frgmt_4 = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		setBottomMenu();
		fragmentReplace(frgmt_1);
		
	}
	
	private Fragment getFragment(int pos){
		
		Fragment frgmt = null;
		switch(pos){
			case frgmt_1:
				frgmt = new HomeFragment();
				break;
		}
		return frgmt;
		
		
	}
	
	private void fragmentReplace(int pos) {
		 
        Fragment newFragment = null;
       
        
        
        //Log.d( TAG, "fragmentReplace " + reqNewFragmentIndex);
 
        newFragment = getFragment(pos);
 
        // replace fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(newFragment != null ){
        	transaction.replace(R.id.main_fragment_layout, newFragment);
        
        // Commit the transaction
        transaction.commitAllowingStateLoss();
       // transaction.commit();
        
        }
    }
	
	private void setBottomMenu(){
		
		//왼쪽부터 순서대로  1,2,center,3,4
		LinearLayout but_1 = (LinearLayout)findViewById(R.id.main_bottom_1_but);
		LinearLayout but_2 = (LinearLayout)findViewById(R.id.main_bottom_2_but);
		LinearLayout but_center = (LinearLayout)findViewById(R.id.main_bottom_center_but);
		LinearLayout but_3 = (LinearLayout)findViewById(R.id.main_bottom_3_but);
		LinearLayout but_4 = (LinearLayout)findViewById(R.id.main_bottom_4_but);
		ImageView iv_1 = (ImageView)findViewById(R.id.main_bottom_1_iv);
		ImageView iv_2 = (ImageView)findViewById(R.id.main_bottom_2_iv);
		ImageView iv_center = (ImageView)findViewById(R.id.main_bottom_center_iv);
		ImageView iv_3 = (ImageView)findViewById(R.id.main_bottom_3_iv);
		ImageView iv_4 = (ImageView)findViewById(R.id.main_bottom_4_iv);
		TextView tv_1 = (TextView)findViewById(R.id.main_bottom_1_tv);
		TextView tv_2 = (TextView)findViewById(R.id.main_bottom_2_tv);
		TextView tv_3 = (TextView)findViewById(R.id.main_bottom_3_tv);
		TextView tv_4 = (TextView)findViewById(R.id.main_bottom_4_tv);
		iv_list = new ImageView[]{iv_1, iv_2, iv_3, iv_4};
		tv_list = new TextView[]{tv_1, tv_2, tv_3, tv_4};
		but_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBut(0);
			}
		});
		but_2.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						setBut(1);
					}
				});
		but_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBut(2);
			}
		});
		but_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBut(3);
			}
		});
	}
	
	private void setBut(int pos){
		for(int i=0;i<iv_list.length;i++){
			iv_list[i].setColorFilter(null);
			tv_list[i].setTextColor(Color.parseColor("#c9c9c9"));
		}
		ColorFilter cf = new LightingColorFilter(0, Color.parseColor("#05C9C9"));
		iv_list[pos].setColorFilter(cf);
		tv_list[pos].setTextColor(Color.parseColor("#05C9C9"));
	}

}
