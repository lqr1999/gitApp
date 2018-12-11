package com.example.liang.memoeri;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;



public class ViewInformationPersonalBackUp extends ListActivity
{
	private TextView vusername=null;
	private Button backMainMenu=null;
	ListView SubjectList=null;
	private String flag_name=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewinformationlist);
		Intent intent=getIntent();
		flag_name=intent.getStringExtra("name");
		System.out.println("information ----->"+flag_name);
		SubjectList=(ListView)findViewById(android.R.id.list);
		backMainMenu=(Button)findViewById(R.id.vbackmainmenu);
		backMainMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ViewInformationPersonalBackUp.this, Mian_personalBackUp.class);
				startActivity(intent);
				ViewInformationPersonalBackUp.this.finish();

			}
		});
		SubjectList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();

				HashMap<String,String> map=(HashMap<String,String>)SubjectList.getItemAtPosition(arg2);
				String subject=map.get("subject");
				String date=map.get("date");
				intent.putExtra("subject", subject);
				intent.putExtra("date", date);
				intent.putExtra("name", flag_name);
				System.out.println("列表界面的flag_name"+flag_name);
				intent.setClass(ViewInformationPersonalBackUp.this, ViewBelievePersonalBackUp.class);
				startActivity(intent);
			}
		});
		/* Intent intent=getIntent();
		 if(intent!=null)
		 vusername.setText(intent.getStringExtra("name").toString());*/
		SQLiteManager sqliteManager=new SQLiteManager(ViewInformationPersonalBackUp.this);
		sqliteManager.open();
		Cursor cursor=sqliteManager.getRecord();

		if(cursor!=null&&cursor.getCount()>=0)
		{

			ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();

			while(cursor.moveToNext())
			{

				HashMap<String ,String > map1=new HashMap<String,String>();
				map1.put("subject", cursor.getString(cursor.getColumnIndex("subject")));
				map1.put("date", cursor.getString(cursor.getColumnIndex("date")));
				list.add(map1);
			}

			SimpleAdapter listAdapter=new SimpleAdapter(this, list, R.layout.subjectlist,
					new String[]{"subject","date"}, new int[]{R.id.lsubject,R.id.ldate});
			setListAdapter(listAdapter);


		}

		sqliteManager.close();
	}

	public Object getItem(int position) {
		return getItem(position);
	}
	@Override
	public ListView getListView() {
		// TODO Auto-generated method stub
		return super.getListView();
	}




}
