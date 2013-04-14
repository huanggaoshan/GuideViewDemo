package com.andyidea.guidedemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class TManagerActivity extends Activity {
	
    private ViewPager viewPager;  
    private ArrayList<View> pageViews;  
    private ViewGroup main, group;  
    private ImageView imageView;  
    private ImageView[] imageViews; 
    private SharedPreferences sp;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        sp=getSharedPreferences("onelogin", 0);
        String one=sp.getString("key", "");
        if("1".equals(one)){
        	setContentView(R.layout.item01);
        }
        else {
        	sp.edit().putString("key", "1").commit();
        
        	LayoutInflater inflater = getLayoutInflater();  
        	View v=inflater.inflate(R.layout.item04, null);
        	Button b=(Button) v.findViewById(R.id.button1);
        	b.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(TManagerActivity.this, abc.class);
					startActivity(intent);
				}
        	});
        	pageViews = new ArrayList<View>();  
        	pageViews.add(inflater.inflate(R.layout.item01, null));  
        	pageViews.add(inflater.inflate(R.layout.item02, null));  
        	pageViews.add(inflater.inflate(R.layout.item03, null));  
        	pageViews.add(v);   
  
        	imageViews = new ImageView[pageViews.size()];  
        	main = (ViewGroup)inflater.inflate(R.layout.mains, null);  
        
        	// group是R.layou.main中的负责包裹小圆点的LinearLayout. 
        	group = (ViewGroup)main.findViewById(R.id.viewGroup);  
  
        	viewPager = (ViewPager)main.findViewById(R.id.guidePages);  
  
        	for (int i = 0; i < pageViews.size(); i++) {  
        		imageView = new ImageView(TManagerActivity.this);  
        		imageView.setLayoutParams(new LayoutParams(20,20));  
        		imageView.setPadding(20, 0, 20, 0);  
        		imageViews[i] = imageView;  
        		if (i == 0) {  
        			//默认选中第一张图片              
        			imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);  
        		} else {  
        			imageViews[i].setBackgroundResource(R.drawable.page_indicator);  
        		}  
        		group.addView(imageViews[i]);  
        	}  
        	setContentView(main);  
        	viewPager.setAdapter(new ViewPagerAdapter(TManagerActivity.this, pageViews));  
        	viewPager.setOnPageChangeListener(new GuidePageChangeListener());  
        }
    }
    /** 指引页面Adapter */
    class GuidePageChangeListener implements OnPageChangeListener {  
  
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void onPageSelected(int arg0) {  
            for (int i = 0; i < imageViews.length; i++) {  
                imageViews[arg0]  
                        .setBackgroundResource(R.drawable.page_indicator_focused);  
                if (arg0 != i) {  
                    imageViews[i]  
                            .setBackgroundResource(R.drawable.page_indicator);  
                }  
            }
  
        }  
  
    }  
    
}