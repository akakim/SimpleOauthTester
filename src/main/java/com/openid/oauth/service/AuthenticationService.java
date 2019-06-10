package com.openid.oauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.openid.oauth.SimpleOauthTester.AuthenticationResultModel;

public interface AuthenticationService {

	
	public AuthenticationResultModel requestAuthorizeCode(List<BasicNameValuePair> params);
	
	public AuthenticationResultModel requestClientCredential(List<BasicNameValuePair> params);
	
	public AuthenticationResultModel requestResourceOwner(List<BasicNameValuePair> params);
	
	public AuthenticationResultModel requestOthers(String url,List<BasicNameValuePair> params);
	
	//public Map<String,String>getDefaultOauthSample();
}
