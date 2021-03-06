package com.truly.ic.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.truly.ic.SalePlatform.R;

public class MyUtils {

	/**
	 * 可以将所有Bean List转换成对应的Map List 主要用于填充SimpleAdapter
	 * 
	 * @param list
	 *            需要转换的实体对象List
	 * @return 已经转换好的Map映射List
	 */
	public static List<HashMap<String, Object>> ConvertToMapList(List<?> list, String[] segments) {

		if (list.isEmpty())
			return null;
		Class<?> T = list.get(0).getClass();
		Field[] fields = T.getDeclaredFields();
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

		for (Object obj : list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (Field f : fields) {

				// 如果字段名不为空而且字段名不包含当前field，则跳过继续
				if (segments != null && !Arrays.asList(segments).contains(f.getName())) {
					continue;
				}

				Object fieldValue = null;
				try {
					Method method = T.getDeclaredMethod("get" + f.getName().substring(0, 1).toUpperCase(Locale.getDefault()) + f.getName().substring(1));
					try {
						fieldValue = method.invoke(obj);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				} catch (NoSuchMethodException e) {
					// 如果没有这个field对应的getter方法，则跳过然后继续
					e.printStackTrace();
					continue;
				}
				map.put(f.getName(), fieldValue);
			}
			result.add(map);
		}
		return result;
	}

	/**
	 * 与一般的标准MD5不同，自己再加了一点变化
	 * 
	 * @param input
	 *            需要md5加密的文本
	 * @return md5加密的结果
	 */
	public static String stringToMyMD5(String input) {

		if (input.length() > 2) {
			input = "Who" + input.substring(2) + "Are" + input.substring(0, 2) + "You";
		} else {
			input = "Who" + input + "Are" + input + "You";
		}

		byte[] hash;

		try {
			hash = MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}

		return hex.toString();
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm:ss 的当前时间
	 */
	public static String getDateTime() {
		Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置显示格式
		String nowTime = "";
		nowTime = df.format(dt);// 用DateFormat的format()方法在dt中获取并以yyyy/MM/dd
								// HH:mm:ss格式显示
		return nowTime;
	}
	
	
	/**
	 * 检查手机是否有网络连接
	 * 
	 * @param _context
	 *            当前Activity上下文
	 * @return true or false
	 */
	public static boolean isConnectingToInternet(Context _context) {
		ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}

	/**
	 * 弹出一个确定按钮的提示框
	 * 
	 * @param context
	 *            当前上下文
	 * @param title
	 *            标题栏
	 * @param message
	 *            提示信息
	 * @param status
	 *            true表示成功提示，false表示错误提示
	 */
	public static void showAlertDialog(Context context, String title, String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	/**
	 * 嵌套在ScrollView中的话需要手动根据内容设置ListView的高度
	 * @param listView
	 */
	 public static void setListViewHeightBasedOnChildren(ListView listView) {   
	        // 获取ListView对应的Adapter   
	        ListAdapter listAdapter = listView.getAdapter();   
	        if (listAdapter == null) {   
	            return;   
	        }   
	   
	        int totalHeight = 0;   
	        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
	            // listAdapter.getCount()返回数据项的数目   
	            View listItem = listAdapter.getView(i, null, listView);   
	            // 计算子项View 的宽高   
	            listItem.measure(0, 0);
	            // 统计所有子项的总高度   
	            totalHeight += listItem.getMeasuredHeight();    
	        }   
	   
	        ViewGroup.LayoutParams params = listView.getLayoutParams();   
	        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount()+1));   
	        // listView.getDividerHeight()获取子项间分隔符占用的高度   
	        // params.height最后得到整个ListView完整显示需要的高度   
	        listView.setLayoutParams(params);   
	    }   
	
	
	public static class AES {

				
		//AES 128位key
		private static String AES_128_key="1987121919890610";
		
		public static String encrypt(String cleartext) throws Exception {
			//byte[] rawKey = getRawKey(AES_128_key.getBytes());
			byte[] rawKey = AES_128_key.getBytes("utf-8");
			byte[] result = encrypt(rawKey, cleartext.getBytes());
			return Base64.encodeToString(result, 0);
			//return toHex(result);
		}

		public static String decrypt(String encrypted) throws Exception {
			//byte[] rawKey = getRawKey(AES_128_key.getBytes());
			byte[] rawKey = AES_128_key.getBytes("utf-8");
			//byte[] enc = toByte(encrypted);
			byte[] enc = Base64.decode(encrypted, 0);
			byte[] result = decrypt(rawKey, enc);
			return new String(result);
		}

/*		private static byte[] getRawKey(byte[] seed) throws Exception {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(seed);
			kgen.init(128, sr); // 192 and 256 bits may not be available
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();
			return raw;
		}*/

		private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(clear);
			return encrypted;
		}

		private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return decrypted;
		}

/*		public static String toHex(String txt) {
			return toHex(txt.getBytes());
		}

		public static String fromHex(String hex) {
			return new String(toByte(hex));
		}

		public static byte[] toByte(String hexString) {
			int len = hexString.length() / 2;
			byte[] result = new byte[len];
			for (int i = 0; i < len; i++)
				result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
			return result;
		}

		public static String toHex(byte[] buf) {
			if (buf == null)
				return "";
			StringBuffer result = new StringBuffer(2 * buf.length);
			for (int i = 0; i < buf.length; i++) {
				appendHex(result, buf[i]);
			}
			return result.toString();
		}

		private final static String HEX = "0123456789ABCDEF";

		private static void appendHex(StringBuffer sb, byte b) {
			sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
		}*/
	}


}
