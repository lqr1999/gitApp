package com.example.liang.memoeri;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 注册一个用户
 */
public class RegisterPersonalBackUp extends Activity
{


	private Button tijiaoBtn=null;
	private Button backIndexBtn=null;
	private EditText usernameE=null;
	private EditText userpasswordE=null;
	private EditText userpassword2E=null;
	private EditText passwordTishiE=null;
	private String username=null;
	private String userpassword1=null;
	private String userpassword2=null;
	private String userpasswordTishi=null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		usernameE=(EditText)findViewById(R.id.riusername);
		usernameE.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				username=usernameE.getText().toString();

			}
		});
		userpasswordE=(EditText)findViewById(R.id.riuserpassword);
		userpasswordE.setOnFocusChangeListener(new EditText.OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				userpassword1=userpasswordE.getText().toString();
			}});
		userpassword2E=(EditText)findViewById(R.id.rriuserpassword);
		userpassword2E.setOnFocusChangeListener(new EditText.OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				userpassword2=userpassword2E.getText().toString();
			}});
		passwordTishiE=(EditText)findViewById(R.id.rritishipassword);
		passwordTishiE.setOnKeyListener(new EditText.OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				userpasswordTishi=passwordTishiE.getText().toString();
				return false;
			}



		});


		//提交按钮事件
		/**
		 * 改事件完成的事项有：检验两次输入的密码是否一致
		 * 用户名和密码是否为空
		 * 向数据库添加数据
		 */
		tijiaoBtn=(Button)findViewById(R.id.tijiao);
		tijiaoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userInformationCheck();

			}
		});
		//返回首页按钮事件
		backIndexBtn=(Button)findViewById(R.id.backindex);
		backIndexBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(RegisterPersonalBackUp.this, LoginPersonalBackUp.class);
				startActivity(intent);
				RegisterPersonalBackUp.this.finish();
			}
		});
	}
	//此方法用于检查用户、密码是否为空，两次输入的密码是否相同
	public void userInformationCheck()
	{
		SQLiteManager manager=new SQLiteManager(RegisterPersonalBackUp.this);

		if(username.equals("")||userpassword1.equals("")||userpassword2.equals(""))
		{
			Toast.makeText(RegisterPersonalBackUp.this, "用户名或秘密不能为空！", Toast.LENGTH_SHORT).show();


		}
		else
		{
			if(!(userpassword1.equals(userpassword2)))
			{
				Toast.makeText(RegisterPersonalBackUp.this, "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
				userpasswordE.setText("");
				userpassword2E.setText("");

			}
			else
			{
				SQLiteManager  sqliteManager=new SQLiteManager(RegisterPersonalBackUp.this);
				sqliteManager.open();
				Cursor cursor=sqliteManager.YanZheng();
				int index=0;
				if(cursor.getCount()<=0)
				{ index=0;
					sqliteManager.zhuce(index,username,userpassword1,userpassword2);
					Toast.makeText(RegisterPersonalBackUp.this, "添加用户成功！", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(RegisterPersonalBackUp.this, "对不起，你已经添加过用户了，只可以拥有一个用户名，请使用以添加的用户名登陆！", Toast.LENGTH_SHORT).show();

				}

				sqliteManager.close();
			}
		}
	}
}
