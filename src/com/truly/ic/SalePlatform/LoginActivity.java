package com.truly.ic.SalePlatform;

import java.util.Set;
import java.util.TreeMap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.alibaba.fastjson.JSON;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.truly.ic.models.SimpleResultModel;
import com.truly.ic.util.MyUtils;
import com.truly.ic.util.SoapService;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// UI references.
	private BootstrapEditText mUsernameView;
	private BootstrapEditText mPasswordView;
	private RadioButton mOptoRadioView;
	private RadioButton mSemiRadioView;
	private CheckBox mCheckBoxView;
	
	private View mProgressView;
	private View mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_login);
		
		// Set up the login form.
		mOptoRadioView = (RadioButton) findViewById(R.id.opto_radio);
		mSemiRadioView = (RadioButton) findViewById(R.id.semi_radio);
		mUsernameView = (BootstrapEditText) findViewById(R.id.username_login_form);
		mCheckBoxView = (CheckBox) findViewById(R.id.remember_login_form);
		mPasswordView = (BootstrapEditText) findViewById(R.id.password_login_form);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login
								|| id == EditorInfo.IME_ACTION_DONE) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		SharedPreferences sp = getSharedPreferences("userinfo",
				Activity.MODE_PRIVATE);
		if (sp != null) {

			mUsernameView.setText(sp.getString("username", ""));
			mPasswordView.setText(sp.getString("password", ""));
			if ("op".equals(sp.getString("accountset", ""))) {
				mOptoRadioView.setChecked(true);
			} else if ("semi".equals(sp.getString("accountset", ""))) {
				mSemiRadioView.setChecked(true);
			}
			mCheckBoxView.setChecked(sp.getBoolean("remember_password", false));
		}

		BootstrapButton mSignInButton = (BootstrapButton) findViewById(R.id.sign_in_button);
		mSignInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});

		mLoginFormView = findViewById(R.id.login_form);
		mProgressView = findViewById(R.id.login_progress);
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		
		//check network connection
		if(!MyUtils.isConnectingToInternet(getApplicationContext())){			
			MyUtils.showAlertDialog(this, "网络连接检查", "无法连接到网络，请检查系统设置", false);
			return;
		}
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mUsernameView.setError(null);
		mPasswordView.setError(null);
		mSemiRadioView.setError(null);

		// Store values at the time of the login attempt.
		String username = mUsernameView.getText().toString();
		String password = mPasswordView.getText().toString();
		String accountset = "";

		boolean cancel = false;
		View focusView = null;

		if (!mOptoRadioView.isChecked() && !mSemiRadioView.isChecked()) {
			mSemiRadioView
					.setError(getString(R.string.error_accountset_required));
			focusView = mOptoRadioView;
			cancel = true;
		}
		accountset = mOptoRadioView.isChecked() ? "op" : "semi";

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(username)) {
			mUsernameView.setError(getString(R.string.error_field_required));
			focusView = mUsernameView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new UserLoginTask(accountset, username, password);
			mAuthTask.execute((Void) null);
		}
	}

	private boolean isPasswordValid(String password) {
		return password.length() > 5;
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mProgressView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, SimpleResultModel> {

		private final String mUsername;
		private final String mPassword;
		private final String mAccountset;

		UserLoginTask(String accountset, String username, String password) {
			mAccountset = accountset;
			mUsername = username;
			mPassword = password;
		}

		@Override
		protected SimpleResultModel doInBackground(Void... params) {

			SoapService soap = new SoapService();
			TreeMap<String, String> filter = new TreeMap<String, String>();
			filter.put("accountset", mAccountset);
			filter.put("md5Password", MyUtils.stringToMyMD5(mPassword));
			filter.put("userName", mUsername);
			try {
				String result = soap.getSoapStringResult("LoginAndGetUserId",
						filter);
				return JSON.parseObject(result, SimpleResultModel.class);
			} catch (Exception ex) {
				Log.e("Login", ex.toString());
				return null;
			}

		}

		@Override
		protected void onPostExecute(final SimpleResultModel model) {
			mAuthTask = null;
			showProgress(false);

			//用户登录成功
			if (model.getSuccess()) {
				// 保存用户信息到配置文件
				SharedPreferences sr = getSharedPreferences("userinfo",
						Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = sr.edit();
				boolean rememberPassword = mCheckBoxView.isChecked();
				editor.putString("username", mUsername);
				if (rememberPassword) {
					editor.putString("password", mPassword);
				} else {
					editor.putString("password", "");
				}
				// editor.putString("userid", model.getValue());
				editor.putString("last_login", MyUtils.getDateTime());
				editor.putString("accountset", mAccountset);
				editor.putBoolean("remember_password", rememberPassword);
				editor.commit();
				
				//设置全局变量
				final SalePlatformApplication app = (SalePlatformApplication) getApplication();
				app.setAccountset(mAccountset);
				app.setUserid(model.getValue());
				
				//设置Jpush的别名
				JPushInterface.setAlias(LoginActivity.this, mUsername, new TagAliasCallback() {					
					@Override
					public void gotResult(int arg0, String arg1, Set<String> arg2) {
						Log.v("Login", String.valueOf(arg0));						
					}
				});
				
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);

			} else {
				MyUtils.showAlertDialog(LoginActivity.this, "登录提示信息", model.getMsg(), false);
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}		
	}
	
	@Override
	protected void onResume() {
		JPushInterface.onResume(getApplicationContext());
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		JPushInterface.onPause(getApplicationContext());
		super.onPause();
	}
}
