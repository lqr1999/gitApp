package com.example.liang.memoeri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 主页面
 */
public class Mian_personalBackUp extends Activity
{
	private Button madd=null;
	private Button mlist=null;
	private Button mdenglu=null;
	private String flag_name=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenue);
		Intent intent=getIntent();
		flag_name=intent.getStringExtra("username");
		System.out.println("主界面的flag_name"+flag_name);
		madd=(Button)findViewById(R.id.madd);
		madd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.putExtra("name", flag_name);
				intent.setClass(Mian_personalBackUp.this, AddInformationBackUp.class);
				startActivity(intent);
				Mian_personalBackUp.this.finish();
			}
		});
		mlist=(Button)findViewById(R.id.mlist);
		mlist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.putExtra("name", flag_name);
				intent.setClass(Mian_personalBackUp.this, ViewInformationPersonalBackUp.class);
				startActivity(intent);
				Mian_personalBackUp.this.finish();
			}
		});
		mdenglu=(Button)findViewById(R.id.mindex);
		mdenglu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();

				intent.setClass(Mian_personalBackUp.this, LoginPersonalBackUp.class);
				startActivity(intent);
				Mian_personalBackUp.this.finish();
			}
		});
	}

}
