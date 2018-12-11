package com.example.liang.memoeri;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登陆
 */
public class LoginPersonalBackUp extends Activity{
	private EditText username=null;//用户名
	private EditText userpassword=null;//密码
	private Button   dengluBtn=null;//登陆按钮
	private Button   exitBtn=null;//退出按钮
	private Button   registerBtn=null;//注册用户按钮
	private Button   serchpwdBtn=null;//找回密码按钮

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		username=(EditText)findViewById(R.id.inusername);
		userpassword=(EditText)findViewById(R.id.inuserpassword);
		//登陆按钮和事件
		dengluBtn=(Button)findViewById(R.id.denglu);
		dengluBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String	name=username.getText().toString();
				String  pwd=userpassword.getText().toString();
				boolean flag=false;
				SQLiteManager sqliteManager=new SQLiteManager(LoginPersonalBackUp.this);
				sqliteManager.open();
				Cursor cursor=sqliteManager.YanZheng();
				while(cursor.moveToNext())
				{
					if(cursor.getString(cursor.getColumnIndex("username")).equals(name)&&cursor.getString(cursor.getColumnIndex("userpassword")).equals(pwd))
					{
						flag=true;
					}

				}

				sqliteManager.close();
				if(flag)
				{
					System.out.println("登陆成功！");
					Intent intent=new Intent();
					intent.putExtra("username",name);
					intent.setClass(LoginPersonalBackUp.this, Mian_personalBackUp.class);
					startActivity(intent);
					LoginPersonalBackUp.this.finish();
				}
				else
				{
					Toast.makeText(LoginPersonalBackUp.this,"密码或用户名有误，请重新输入！",Toast.LENGTH_SHORT).show();
					username.setText("");
					userpassword.setText("");
				}
			}
		});
		//退出按钮和事件
		exitBtn=(Button)findViewById(R.id.exit);
		exitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new LoginPersonalBackUp().finish();
			}
		});
		//添加用户按钮和事件
		registerBtn=(Button)findViewById(R.id.register);
		registerBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(LoginPersonalBackUp.this, RegisterPersonalBackUp.class);
				startActivity(intent);
			}
		});
		//寻找密码按钮和事件
		serchpwdBtn=(Button)findViewById(R.id.retrievepassword);
		serchpwdBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(LoginPersonalBackUp.this,FindPasswordPersonalBackUp.class);
				startActivity(intent);
				LoginPersonalBackUp.this.finish();
			}
		});
	}
}
