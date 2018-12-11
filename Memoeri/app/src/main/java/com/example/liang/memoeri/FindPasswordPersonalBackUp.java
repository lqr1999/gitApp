package com.example.liang.memoeri;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 找回密码
 */
public class FindPasswordPersonalBackUp extends Activity{
    private Button tijiao=null;
    private Button back=null;
    private EditText userName=null;
    private EditText tishi=null;
    private TextView display=null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpwd);
		userName=(EditText)findViewById(R.id.fiusername);
		tishi=(EditText)findViewById(R.id.fitishi);
		tijiao=(Button)findViewById(R.id.ftijiao);
		display=(TextView)findViewById(R.id.result);
		back=(Button)findViewById(R.id.fback);
		tijiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			        String	name=userName.getText().toString();
				    String  ptishi=tishi.getText().toString();
					SQLiteManager sqliteManager=new SQLiteManager(FindPasswordPersonalBackUp.this);
			    	sqliteManager.open();
			    	Cursor cursor=sqliteManager.YanZheng();
			    	while(cursor.moveToNext())
			    	{
			    		if(cursor.getString(cursor.getColumnIndex("username")).equals(name)&&cursor.getString(cursor.getColumnIndex("tishi")).equals(ptishi))
			    		{
			    			String password=cursor.getString(cursor.getColumnIndex("userpassword"));
			    			display.setText("密码是"+password);
			    		}
			    		else
			    		{
			    			display.setText("用户名或密码提示输入错误");
			    			
			    		}
			    		
			    	}
			    	
			    	sqliteManager.close();
			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(FindPasswordPersonalBackUp.this, LoginPersonalBackUp.class);
				startActivity(intent);
				FindPasswordPersonalBackUp.this.finish();
			}
		});
	}

}
