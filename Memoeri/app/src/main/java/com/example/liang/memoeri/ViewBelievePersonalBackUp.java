package com.example.liang.memoeri;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class ViewBelievePersonalBackUp extends Activity
{
	private EditText  asubject=null;
	private EditText  adate=null;
	private EditText  ainformation=null;
	private RadioGroup group=null;
	private RadioButton personal=null;
	private RadioButton learning=null;
	private RadioButton work=null;
	private Button update=null;
	private Button delete=null;
	private Button backList=null;
	private String flag=null;//标志添加的内容是哪一个属性
	private String detailRecord=null;
	private String tag=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		//获取从记录列表中传递过来的主题和日期参数
		Intent intent=getIntent();
		String vsubject=intent.getStringExtra("subject");
		String vdate=intent.getStringExtra("date");
		//获取相应的主题和日期的对象后并且将从列表传递的参数进行赋值
		asubject=(EditText)findViewById(R.id.aisubject);
		asubject.setText(vsubject);
		adate=(EditText)findViewById(R.id.aidate);
		adate.setText(vdate);
		//以下是添加相应的记录信息
		SQLiteManager manager=new SQLiteManager(ViewBelievePersonalBackUp.this);
		manager.open();
		Cursor cursor=manager.getRecord();
		boolean vflag=true;

		while(cursor.moveToNext())
		{
			if(vflag)
			{
				if(cursor.getString(cursor.getColumnIndex("subject")).equals(vsubject))
				{
					flag=cursor.getString(cursor.getColumnIndex("flag"));
					detailRecord=cursor.getString(cursor.getColumnIndex("information"));
					vflag=false;
				}
			}
			else
			{
				break;
			}
		}
		manager.close();
		//以下是记录的主要内容控件对象的获取
		ainformation=(EditText)findViewById(R.id.aineirong);
		ainformation.setText(detailRecord);
		group=(RadioGroup)findViewById(R.id.Group);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(personal.isChecked())
					tag=new String("1");
				if(learning.isChecked())
					tag=new String("2");
				if(work.isChecked())
					tag=new String("3");
			}
		});
		//以下是三个单选按钮的对象获取
		personal=(RadioButton)findViewById(R.id.personal);
		learning=(RadioButton)findViewById(R.id.learning);
		work=(RadioButton)findViewById(R.id.work);
		if(flag.equals("1"))
		{
			personal.setChecked(true);
		}
		else
		{
			personal.setChecked(false);
		}
		if(flag.equals("2"))
		{
			learning.setChecked(true);
		}
		else
		{
			learning.setChecked(false);
		}
		if(flag.equals("3"))
		{
			work.setChecked(true);
		}
		else
		{
			work.setChecked(false);
		}
		//修改这条记录的按钮和事件
		update=(Button)findViewById(R.id.vupdate);
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subject=asubject.getText().toString();
				String date=adate.getText().toString();
				String neirong=ainformation.getText().toString();
				SQLiteManager manager=new SQLiteManager(ViewBelievePersonalBackUp.this);
				manager.open();
				int index=0;
				Cursor cursor=manager.getRecord();
				while(cursor.moveToNext())
				{
					if(!asubject.getText().toString().equals(""))
						if(cursor.getString(cursor.getColumnIndex("subject")).equals(asubject.getText().toString()))
						{
							index=Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
						}
				}

				if(!(subject.equals("")&&date.equals("")&&neirong.equals("")))
				{
					manager.updateRecord(index,subject, date, neirong, tag);
				}else
				{
					Toast.makeText(ViewBelievePersonalBackUp.this, "修改失败,有选项为空值！请填写完整", Toast.LENGTH_LONG).show();
				}
				manager.close();
				Intent intent=new Intent();
				intent.setClass(ViewBelievePersonalBackUp.this, ViewInformationPersonalBackUp.class);
				startActivity(intent);
				ViewBelievePersonalBackUp.this.finish();
			}
		});
		//删除这条记录的按钮和事件
		delete=(Button)findViewById(R.id.vdelete);
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteManager manager=new SQLiteManager(ViewBelievePersonalBackUp.this);
				manager.open();
				int index=0;
				Cursor cursor=manager.getRecord();
				while(cursor.moveToNext())
				{
					if(!asubject.getText().toString().equals(""))
						if(cursor.getString(cursor.getColumnIndex("subject")).equals(asubject.getText().toString()))
						{
							index=cursor.getInt(cursor.getColumnIndex("_id"));
						}
				}
				manager.deleteRecord(index);
				manager.close();
				Intent intent=new Intent();
				intent.setClass(ViewBelievePersonalBackUp.this, ViewInformationPersonalBackUp.class);
				startActivity(intent);
				ViewBelievePersonalBackUp.this.finish();
			}
		});
		//返回记录列表按钮和事件
		backList=(Button)findViewById(R.id.vbacklist);
		backList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ViewBelievePersonalBackUp.this, ViewInformationPersonalBackUp.class);
				startActivity(intent);
				ViewBelievePersonalBackUp.this.finish();
			}
		});

	}
}
