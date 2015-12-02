package com.example.swipemenulistview;



import java.util.ArrayList;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuStateChangeListener;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	private	ArrayAdapter<String> adapter;
	private	ArrayList<String> items;
	private	Context context;

	private	SwipeMenuListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context=this;
		setContentView(R.layout.activity_main);

		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				
				SwipeMenuItem openItem = new SwipeMenuItem(context);
				openItem.setBackground(new ColorDrawable(Color.GREEN));
				openItem.setWidth(dp2px(90));
				openItem.setTitle("打开");
				openItem.setTitleSize(20);
				openItem.setTitleColor(Color.WHITE);
				menu.addMenuItem(openItem);				

				SwipeMenuItem deleteItem = new SwipeMenuItem(context);
				deleteItem.setBackground(new ColorDrawable(Color.LTGRAY));
				deleteItem.setWidth(dp2px(90));
				deleteItem.setIcon(android.R.drawable.ic_delete);
				menu.addMenuItem(deleteItem);
			}
		};
		
		listView = (SwipeMenuListView) findViewById(R.id.listView);
		listView.setMenuCreator(creator);

		listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu,int index) {
				//index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
				//从0开始，依次是：0、1、2、3...
				switch (index) {
				case 0:
					Toast.makeText(context, "打开:"+position,Toast.LENGTH_SHORT).show();
					
					break;

				case 1:
					Toast.makeText(context, "删除:"+position,Toast.LENGTH_SHORT).show();
					//isDelete=true;
					listView.smoothCloseMenu();
					
					break;
				}

				// false : 当用户触发其他地方的屏幕时候，自动收起菜单。
				// true : 不改变已经打开菜单的样式，保持原样不收起。
				return false;
			}
		});

		// 监测用户在ListView的SwipeMenu侧滑事件。
		listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

			@Override
			public void onSwipeStart(int pos) {
				Log.d("位置:" + pos, "开始侧滑...");
			}

			@Override
			public void onSwipeEnd(int pos) {
				Log.d("位置:" + pos, "侧滑结束.");
			}
		});

		
		listView.setOnMenuStateChangeListener(new OnMenuStateChangeListener(){

			@Override
			public void onMenuOpen(int position) {
				
			}

			@Override
			public void onMenuClose(int position) {
				Log.d("位置:" + position, "关闭");
				
			}});
		
		
		
		//测试数据集。
		items=new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			items.add("测试数据:" + i);
		}

		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
		listView.setAdapter(adapter);
	}

	public int dp2px(float dipValue) {
		final float scale = this.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
}
