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
				openItem.setTitle("��");
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
				//index��ֵ������SwipeMenu�������SwipeMenuItem˳��ֵ������������±ꡣ
				//��0��ʼ�������ǣ�0��1��2��3...
				switch (index) {
				case 0:
					Toast.makeText(context, "��:"+position,Toast.LENGTH_SHORT).show();
					
					break;

				case 1:
					Toast.makeText(context, "ɾ��:"+position,Toast.LENGTH_SHORT).show();
					//isDelete=true;
					listView.smoothCloseMenu();
					
					break;
				}

				// false : ���û����������ط�����Ļʱ���Զ�����˵���
				// true : ���ı��Ѿ��򿪲˵�����ʽ������ԭ��������
				return false;
			}
		});

		// ����û���ListView��SwipeMenu�໬�¼���
		listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

			@Override
			public void onSwipeStart(int pos) {
				Log.d("λ��:" + pos, "��ʼ�໬...");
			}

			@Override
			public void onSwipeEnd(int pos) {
				Log.d("λ��:" + pos, "�໬����.");
			}
		});

		
		listView.setOnMenuStateChangeListener(new OnMenuStateChangeListener(){

			@Override
			public void onMenuOpen(int position) {
				
			}

			@Override
			public void onMenuClose(int position) {
				Log.d("λ��:" + position, "�ر�");
				
			}});
		
		
		
		//�������ݼ���
		items=new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			items.add("��������:" + i);
		}

		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
		listView.setAdapter(adapter);
	}

	public int dp2px(float dipValue) {
		final float scale = this.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
}
