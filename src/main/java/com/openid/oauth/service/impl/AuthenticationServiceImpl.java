package com.openid.oauth.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.google.gson.*;

import com.openid.oauth.SimpleOauthTester.AuthenticationResultModel;
import com.openid.oauth.SimpleOauthTester.OAuthClientConfig;
import com.openid.oauth.SimpleOauthTester.OAuthHttpRequest;
import com.openid.oauth.service.AuthenticationService;

//import net.minidev.json.parser.JSONParser;

/*import static com.openid.oauth.SimpleOauthTester.OAuthClientConfig.ID;
import static com.openid.oauth.SimpleOauthTester.OAuthClientConfig.SECRETE;
import static com.openid.oauth.SimpleOauthTester.OAuthClientConfig.SCOPE;*/

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	private final String ID 		= "client_id";
	private final String SECRETE 	= "client_secret";
	private final String SCOPE 		= "scope";
	private final String AUTHENTICATION_TYPE = "auth_type";
	
	@Autowired 
	OAuthClientConfig config;

	@Override
	public AuthenticationResultModel requestAuthorizeCode(List<BasicNameValuePair> params) {
		   
		
		params.add( new BasicNameValuePair("client_id",config.getClientID()));
		params.add( new BasicNameValuePair("client_secret",config.getClientSecret()));

		AuthenticationResultModel result = this.request( config.getAuthorizeURI(), null, params );
		
		return result;
	}

	@Override
	public AuthenticationResultModel requestClientCredential(List<BasicNameValuePair> params) {
		
		params.add( new BasicNameValuePair("client_id",config.getClientID()));
		params.add( new BasicNameValuePair("client_secret",config.getClientSecret()));
	
		
		AuthenticationResultModel result = this.request( config.getTokenURI(), null, params );
		return result;
	}

	@Override
	public AuthenticationResultModel requestResourceOwner(List<BasicNameValuePair> params) {
		params.add( new BasicNameValuePair("client_id",config.getClientID()));
		params.add( new BasicNameValuePair("client_secret",config.getClientSecret()));
		
		
		AuthenticationResultModel result = this.request( config.getTokenURI(), null, params );
		return result;
	}

	@Override
	public AuthenticationResultModel requestOthers(String url, List<BasicNameValuePair> params) {
		params.add( new BasicNameValuePair("client_id",config.getClientID()));
		params.add( new BasicNameValuePair("client_secret",config.getClientSecret()));
//		params.add( new BasicNameValuePair("grant_type",config.get()));
		// TODO Auto-generated method stub
		AuthenticationResultModel result = this.request( url, null, params );
		return null;
	}



	List<BasicNameValuePair> params = new ArrayList<>();
	String targetURL;
	
	
  
	public AuthenticationResultModel request(String targetURL,Header headers,List<BasicNameValuePair> params) {
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse result				= null;

		for(BasicNameValuePair p : params ) {
			logger.info(p.getName()+ " : " +p.getValue());
		}
		AuthenticationResultModel model = new AuthenticationResultModel();
		try {
			HttpPost post = new HttpPost(targetURL);
			

			post.setEntity( new UrlEncodedFormEntity(params,"UTF-8"));
		
			result = client.execute( post );
			int resultCode = result.getStatusLine().getStatusCode();
			
			System.out.println( resultCode +" ... ");
					
			if( resultCode == 200 ){
				
				StringBuilder total 		  = new StringBuilder();
				String line 				  = null;
		
					
				
				model = new Gson().fromJson(new InputStreamReader(result.getEntity().getContent()), AuthenticationResultModel.class);
				
				model.setHttpCode(resultCode);
				
			    	
			}else {
				model = new Gson().fromJson(new InputStreamReader(result.getEntity().getContent()), AuthenticationResultModel.class);
				model.setHttpCode(resultCode);
				model.setErrorMessage("httpErrorMessage");
				
				//obj.put("message","http ErrorMessage");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			model.setHttpCode(9999);
			model.setErrorMessage("IOException");
		//	 obj.

		} catch (Exception e) {
			//Connection refused 처리
			e.printStackTrace();
			model.setHttpCode(9999);
			model.setErrorMessage("Exception");

			
		}
		return model;
	}
	
	
}
