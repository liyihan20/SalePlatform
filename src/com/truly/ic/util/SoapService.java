package com.truly.ic.util;

import java.util.Iterator;
import java.util.TreeMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.truly.ic.SalePlatform.R.string;

import android.util.Log;

public class SoapService {

	private String accountset = "";
	private String userid = "0";
	private String Tag="SOAP";
	public SoapService(){}
	public SoapService(String _accountset,String _userid){
		this.accountset=_accountset;
		this.userid=_userid;
	}
	
	public String getSoapStringResult(String methodName,
			TreeMap<String, String> args)  throws Exception{
		
		//如果已经登陆，表示有了帐套信息和用户id，那就将这两个参数也设置一下。
		if(!"0".equals(userid)){
			args.put("aaa", accountset);
			args.put("zzz", userid);
		}
		Log.v(Tag,accountset+":"+userid);
		SoapObject obj = getSoapObject(methodName, args);
		if (obj == null)
			return null;
		Log.i(Tag, obj.getProperty(0).toString());
		return obj.getProperty(0).toString();
	}	
	
	private SoapObject getSoapObject(String methodName,
			TreeMap<String, String> Args) throws Exception {

		String nameSpace = "http://truly.com.cn/ic";		
		// EndPoint
		String endPoint = "http://59.37.42.23:80/SalePlatfromServices/SOService.asmx";
		// SOAP Action
		String soapAction = nameSpace + "/" + methodName;

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数,将参数值AES加密
		Iterator<String> iterator = Args.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			rpc.addProperty(key.toString(), MyUtils.AES.encrypt(Args.get(key)));
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		//envelope.setOutputSoapObject(rpc);	
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// 调用WebService			
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		// 获取返回的数据		
		if (envelope.bodyIn instanceof SoapFault) {
			String str = ((SoapFault) envelope.bodyIn).faultstring;	
			Log.e(Tag,str);
			return null;
		} else {
			// 获取返回的结果
			// String result = object.getProperty("33").toString();
			SoapObject obj = (SoapObject) envelope.bodyIn;
			Log.d(Tag, obj.toString());
			return obj;
		}
	}

}
