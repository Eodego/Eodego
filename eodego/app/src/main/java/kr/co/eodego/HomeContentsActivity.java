package kr.co.eodego;

import java.util.ArrayList;

import com.androidquery.AQuery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class HomeContentsActivity extends BaseActivity{

	AQuery aq;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.home_contents_activity);
		
		aq = new AQuery(this);
		
		aq.id(R.id.home_contents_menu_tv).text("교촌치킨");
		aq.id(R.id.home_contents_back_but).clicked(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		aq.id(R.id.home_contents_submenu_1_tv).background(R.drawable.border_contents_box_selected).clicked(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setInflate(R.layout.home_contents_info_layout);
				aq.id(R.id.home_contents_submenu_1_tv).background(R.drawable.border_contents_box_selected);
				aq.id(R.id.home_contents_submenu_2_tv).background(R.drawable.border_contents_box);
				aq.id(R.id.home_contents_submenu_3_tv).background(R.drawable.border_contents_box);
			}
		});
		
		aq.id(R.id.home_contents_submenu_2_tv).background(R.drawable.border_contents_box).clicked(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						setInflate(R.layout.home_contents_menu_layout);
						aq.id(R.id.home_contents_submenu_2_tv).background(R.drawable.border_contents_box_selected);
						aq.id(R.id.home_contents_submenu_1_tv).background(R.drawable.border_contents_box);
						aq.id(R.id.home_contents_submenu_3_tv).background(R.drawable.border_contents_box);
					}
				});
		
		aq.id(R.id.home_contents_submenu_3_tv).background(R.drawable.border_contents_box).clicked(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setInflate(R.layout.home_contents_review_layout);
				aq.id(R.id.home_contents_submenu_3_tv).background(R.drawable.border_contents_box_selected);
				aq.id(R.id.home_contents_submenu_2_tv).background(R.drawable.border_contents_box);
				aq.id(R.id.home_contents_submenu_1_tv).background(R.drawable.border_contents_box);
			}
		});
		
		setInflate(R.layout.home_contents_info_layout);
	}
	
	private void setInflate(int res){
		LayoutInflater vi = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout inflate_layout = (RelativeLayout)findViewById(R.id.home_contents_inflate_layout);
		inflate_layout.removeAllViews();
		if(res==R.layout.home_contents_info_layout){
			LinearLayout convertView = (LinearLayout)vi.inflate(res, null);
			convertView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			AQuery aq_inflate = new AQuery(convertView);
			inflate_layout.addView(convertView);
		}
		else if(res==R.layout.home_contents_menu_layout){
			RelativeLayout convertView = (RelativeLayout)vi.inflate(res, null);
			convertView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
			ArrayList<MenuGridArticle> list = new ArrayList<MenuGridArticle>();
			MenuGridArticle mga = new MenuGridArticle("test", "10000");
			list.add(mga);
			mga = new MenuGridArticle("test", "10000");
			list.add(mga);
			mga = new MenuGridArticle("test", "10000");
			list.add(mga);
			mga = new MenuGridArticle("test", "10000");
			list.add(mga);
			mga = new MenuGridArticle("test", "10000");
			list.add(mga);
			
			MenuGridAdapter adapter = new MenuGridAdapter(HomeContentsActivity.this, R.layout.home_contents_menu_grid, list); 
			MultiGridView gridView = (MultiGridView)convertView.findViewById(R.id.home_contents_menu_gridview);
			gridView.setAdapter(adapter);
			inflate_layout.addView(convertView); 
		}
		else if(res==R.layout.home_contents_review_layout){
			RelativeLayout convertView = (RelativeLayout)vi.inflate(res, null);
			convertView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
			ArrayList<ReviewListArticle> list = new ArrayList<ReviewListArticle>();
			ReviewListArticle rla = new ReviewListArticle("eodego1", "1시간 전", "5.0", "라인스토어", "신사동");
			list.add(rla);
			rla = new ReviewListArticle("eodego2", "2시간 전", "5.0", "라인스토어", "신사동");
			list.add(rla);
			
			ReviewListAdapter adapter = new ReviewListAdapter(HomeContentsActivity.this, R.layout.home_contents_review_list, list); 
			MultiListView listView = (MultiListView)convertView.findViewById(R.id.home_contents_review_listview);
			listView.setAdapter(adapter);
			inflate_layout.addView(convertView); 
			
		}
	}
	
	private class ReviewListAdapter extends ArrayAdapter<ReviewListArticle>{

    	
    	Context context;
    	ArrayList<ReviewListArticle> list = null;
    	private ReviewListViewHolder viewHolder;
    	AQuery aq_list;
    	
    	
    	
		public ReviewListAdapter(Context context, int ResourceId,
				ArrayList<ReviewListArticle> objects) {
			super(context, ResourceId, objects);
			this.context = context;
			this.list = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.home_contents_review_list, null);
				aq_list = new AQuery(convertView);
				viewHolder = new ReviewListViewHolder();
				viewHolder.profile_iv = aq_list.id(R.id.home_contents_review_list_profile_iv).getImageView();
				viewHolder.picture_iv = aq_list.id(R.id.home_contents_review_list_picture_iv).getImageView();
				viewHolder.id_tv = aq_list.id(R.id.home_contents_review_list_id_tv).getTextView();
				viewHolder.point_tv = aq_list.id(R.id.home_contents_review_list_point_tv).getTextView();
				viewHolder.point_bar = aq_list.id(R.id.home_contents_review_list_progress).getProgressBar();
				viewHolder.time_tv = aq_list.id(R.id.home_contents_review_list_time_tv).getTextView();
				viewHolder.position_tv = aq_list.id(R.id.home_contents_review_list_position_tv).getTextView();
				viewHolder.position_address_tv = aq_list.id(R.id.home_contents_review_list_position_address_tv).getTextView();
				convertView.setTag(viewHolder);
			}
			else{
				viewHolder = (ReviewListViewHolder)convertView.getTag();
			}
			
			ReviewListArticle rla = list.get(position);
			
			if(rla!=null){
				
			}
			
			return convertView;
		}
		
		
    }
	
	private class MenuGridAdapter extends ArrayAdapter<MenuGridArticle>{

    	
    	Context context;
    	ArrayList<MenuGridArticle> list = null;
    	private MenuGridViewHolder viewHolder;
    	AQuery aq_grid;
    	
    	
    	
		public MenuGridAdapter(Context context, int ResourceId,
				ArrayList<MenuGridArticle> objects) {
			super(context, ResourceId, objects);
			this.context = context;
			this.list = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.home_contents_menu_grid, null);
				aq_grid = new AQuery(convertView);
				viewHolder = new MenuGridViewHolder();
				viewHolder.title_tv = aq_grid.id(R.id.home_contents_menu_grid_title_tv).getTextView();
				viewHolder.price_tv = aq_grid.id(R.id.home_contents_menu_grid_price_tv).getTextView();
				convertView.setTag(viewHolder);
			}
			else{
				viewHolder = (MenuGridViewHolder)convertView.getTag();
			}
			
			MenuGridArticle mga = list.get(position);
			
			if(mga!=null){
				viewHolder.title_tv.setText(mga.menu_title);
				viewHolder.price_tv.setText(mga.price);
			}
			
			return convertView;
		}
		
		
    }
	
	private class MenuGridViewHolder{
		TextView title_tv;
		TextView price_tv;
	}
	
	private class MenuGridArticle{
		String menu_title;
		String price;
		
		MenuGridArticle(String menu_title, String price){
			this.menu_title = menu_title;
			this.price = price;
		}
	}
	
	private class ReviewListViewHolder{
		ImageView profile_iv;
		ImageView picture_iv;
		TextView id_tv;
		TextView point_tv;
		ProgressBar point_bar;
		TextView time_tv;
		TextView position_tv;
		TextView position_address_tv;
	}
	
	private class ReviewListArticle{
		String id;
		String time;
		String point;
		String position;
		String position_address;
		
		ReviewListArticle(String id, String time, String point, String position, String position_address){
			this.id = id;
			this.time = time;
			this.point = point;
			this.position = position;
			this.position_address = position_address;
		}
	}


}
