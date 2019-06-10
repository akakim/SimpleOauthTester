package com.openid.oauth.SimpleOauthTester;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Session;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;


import org.json.simple.JSONObject;

import net.minidev.json.parser.JSONParser;


/**
 * Default 요청시 x-form-urlencoded 방식 
 * 응답시 Json 형태 
 * @author dream
 *
 */
public class OAuthHttpRequest {

	List<BasicNameValuePair> params = new ArrayList<>();
	String targetURL;
	
	
    public OAuthHttpRequest(String targetURL , List<BasicNameValuePair> param) {
    	
    	this.targetURL = targetURL;
    	this. params = new ArrayList<>();
    	params.addAll( 0, param);
    	
    }
	public JSONObject request() {
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse result				= null;
		JSONObject obj = new JSONObject();
		try {
			HttpPost post = new HttpPost(targetURL);
			
			post.setEntity( new UrlEncodedFormEntity(params,"UTF-8"));
		
			result = client.execute( post );
			int resultCode = result.getStatusLine().getStatusCode();
			
			System.out.println( resultCode +" ... ");
					
			if( resultCode == 200 ){
				
				StringBuilder total 		  = new StringBuilder();
				String line 				  = null;
		
	
				JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
				
				
				// Data Object 
				obj = (JSONObject)parser.parse(new InputStreamReader(result.getEntity().getContent()) );
				
				
				/*
				 * session.removeValue("grant_type");
	 			session.removeValue( "access_token");
				session.removeValue( "expires_in");
				session.removeValue( "token_type");
				session.removeValue( "scope");
				session.removeValue("refresh_token");
					
				session.putValue("grant_type",CREDENTIAL_TYPE);
	 			session.putValue( "access_token", obj.get("access_token"));
				session.putValue( "expires_in", obj.get("expires_in"));
				session.putValue( "token_type", obj.get("token_type"));
				session.putValue( "scope", obj.get("scope"));*/

	/* 			bufferedReader.close(); */
				
//				response.sendRedirect("./inc/sessionView.jsp");			
							
			}else {
				obj.put("code", resultCode);
				obj.put("message","http ErrorMessage");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			obj.put("code", 1);
			obj.put("message","IOException");
		//	 obj.

		} catch (Exception e) {
			//Connection refused 처리
			e.printStackTrace();
			obj.put("code", 2);
			obj.put("message","Unexpected ");

			
		}
		return obj;
	}
	
	private JSONObject convertJson(String result) {
		
		JSONObject resultObject=  new JSONObject();
		
		return resultObject;
		
	}
}
