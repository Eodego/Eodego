package kr.co.eodego;

import java.util.ArrayList;

import com.androidquery.AQuery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	Context mContext;
	View view;
	AQuery aq;
	int[] menu_tv_id_list;
	ArrayList<HomeListArticle> list;
	HomeListAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.home_fragment, null);
		aq = new AQuery(view);
		
		list = new ArrayList<HomeListArticle>(); 
		HomeListArticle hla = new HomeListArticle(1, 7, "교촌치킨", 1500, 1500, 1.2f, 4.0f, "");
		list.add(hla);
		hla = new HomeListArticle(1, 2, "태평양 수산회", 140, 200, 1.2f, 4.0f, "");
		list.add(hla);
		hla = new HomeListArticle(3, 8, "북서울 꿈의 숲", 99, 13, 1.3f, 4.0f, "");
		list.add(hla);
		hla = new HomeListArticle(2, 9, "한일당구장", 14, 8, 1.8f, 4.0f, "");
		list.add(hla);
		adapter = new HomeListAdapter(getActivity(), R.layout.home_list, list);
		aq.id(R.id.home_listview).adapter(adapter).itemClicked(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent(getActivity(), HomeContentsActivity.class);
				startActivity(intent);
			}
		});
		setTopMenu();
		return view;
	}
	
	
	private void setTopMenu(){
		menu_tv_id_list = new int[]{R.id.home_top_menu_1_tv,
			R.id.home_top_menu_2_tv,
			R.id.home_top_menu_3_tv,
			R.id.home_top_menu_4_tv,
			R.id.home_top_menu_5_tv};
		
		for(int i=0;i<menu_tv_id_list.length;i++){
			final int pos = i;
			aq.id(menu_tv_id_list[i]).clicked(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setTopBut(pos);
				}
			});
		}
	}
	
	private void setTopBut(int pos){
		for(int i=0;i<menu_tv_id_list.length;i++){
			TextView tv = aq.id(menu_tv_id_list[i]).getTextView();
			tv.setTextColor(Color.parseColor("#C9C9C9"));
			tv.setBackgroundResource(R.drawable.border_menu_box);
		}
		
		TextView tv_selected = aq.id(menu_tv_id_list[pos]).getTextView();
		tv_selected.setTextColor(Color.parseColor("#FFFFFF"));
		tv_selected.setBackgroundResource(R.drawable.border_menu_box_selected);
	}
	
	private class HomeListViewHolder {
		TextView title_tv; //상호명
		TextView division_tv;
		TextView info_tv;
		TextView point_tv; //평점
		TextView hashtag_tv; //해시태그
		ImageView thumb_iv;
	}
	
	private class HomeListAdapter extends ArrayAdapter<HomeListArticle>{

    	
    	Context context;
    	ArrayList<HomeListArticle> list = null;
    	private HomeListViewHolder viewHolder;
    	AQuery aq_list;
    	
    	
    	
		public HomeListAdapter(Context context, int ResourceId,
				ArrayList<HomeListArticle> objects) {
			super(context, ResourceId, objects);
			this.context = context;
			this.list = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.home_list, null);
				aq_list = new AQuery(convertView);
				viewHolder = new HomeListViewHolder();
				viewHolder.title_tv = aq_list.id(R.id.home_list_title_tv).getTextView();
				viewHolder.thumb_iv = aq_list.id(R.id.home_list_thumb_iv).getImageView();
				viewHolder.info_tv = aq_list.id(R.id.home_list_info_tv).getTextView();
				viewHolder.point_tv = aq_list.id(R.id.home_list_point_tv).getTextView();
				viewHolder.division_tv = aq_list.id(R.id.home_list_division_tv).getTextView();
				viewHolder.hashtag_tv = aq_list.id(R.id.home_list_hashtag_tv).getTextView();
				convertView.setTag(viewHolder);
			}
			else{
				viewHolder = (HomeListViewHolder)convertView.getTag();
			}
			
			HomeListArticle hla = list.get(position);
			
			if(hla!=null){
				viewHolder.title_tv.setText(hla.title);
				viewHolder.point_tv.setText(hla.point+"/"+"5.0");
				
				String major ="";
				if(hla.major_key==1){
					major = "먹거리";
				}
				else if(hla.major_key==2){
					major = "놀거리";
				}
				else if(hla.major_key==3){
					major = "볼거리";	
				}
				
				String minor = "";
				if(hla.minor_key==1){
					minor ="한식";				
				}
				else if(hla.minor_key==2){
					minor ="일식";				
				}
				else if(hla.minor_key==3){
					minor ="중식";				
				}
				else if(hla.minor_key==4){
					minor ="분식";				
				}
				else if(hla.minor_key==5){
					minor ="카페";				
				}
				else if(hla.minor_key==6){
					minor ="레스토랑";				
				}
				else if(hla.minor_key==7){
					minor ="피자/치킨";				
				}
				else if(hla.minor_key==8){
					minor ="공원";				
				}
				else if(hla.minor_key==9){
					minor ="당구장";				
				}
				viewHolder.division_tv.setText(major+"·"+minor);
				String favorite = "";
				if(hla.favorite>999){
					favorite = "999+";
				}
				else{
					favorite = hla.favorite + "";
				}
				String review = "";
				if(hla.review>999){
					review = "999+";
				}
				else{
					review = hla.review + "";
				}
				viewHolder.info_tv.setText("즐겨찾기 "+favorite+"·리뷰수"+review+"·거리 "+hla.distance+"km");
				viewHolder.hashtag_tv.setText("#허니콤보");
			}
			
			return convertView;
		}
		
		
    }
	
	
	
	private class HomeListArticle{
		int major_key; //대분류
		int minor_key; //중분류
		String title; //상호명
		int favorite; //즐겨찾기 수
		int review; //리뷰수
		float distance; //거리
		float point; //평점
		String hashtag; //해시태그
		
		HomeListArticle(int major_key, int minor_key, String title, 
				int favorite, int review, float distance, 
				float point, String hashtag){
			this.major_key = major_key; //대분류
			this.minor_key = minor_key; //소분류
			this.title = title; //상호명
			this.favorite = favorite; //즐겨찾기 수
			this.review = review; //리뷰수
			this.distance = distance; //거리
			this.point = point; //평점
			this.hashtag = hashtag; //해시태그	
			
		}
		
	}
	

}
