package com.openid.oauth.SimpleOauthTester;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import org.apache.http.client.utils.URIBuilder;

public class OAuthClientConfig {

	String clientID;
	String clientSecret;
	String serverDomain;
	String defaultResourceDomain;
	String myDomain;
	String scope;
	
	final String codeRedirectURI		= "/responseCode";
	final String implicitRedirectURI 	= "/responseImpl";
	
	final String grantTypeCode 				= "authorization_code";
	final String grantTypeImplicit			= "implicit";
	final String grantTypeClientCredential	= "client_credentials";
	final String grantTypeResourceOwner 		= "password";
	
	final String authEndPoint 			= "/authorize";
	final String tokenEndPoint 			= "/token";
	
	
	
	public OAuthClientConfig() {	}

	
	public OAuthClientConfig(String clientID, String clientSecret, String serverDomain, String defaultResourceDomain,
			String myDomain) {
		super();
		this.clientID = clientID;
		this.clientSecret = clientSecret;
		this.serverDomain = serverDomain;
		this.defaultResourceDomain = defaultResourceDomain;
		this.myDomain = myDomain;
	}

	public String getServerDomain() {
		return serverDomain;
	}


	public String getAuthEndPoint() {
		return authEndPoint;
	}


	public String getTokenEndPoint() {
		return tokenEndPoint;
	}


	public String getClientSecret() {
		return clientSecret;
	}


	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}


	public String getDefaultResourceDomain() {
		return defaultResourceDomain;
	}


	public void setDefaultResourceDomain(String defaultResourceDomain) {
		this.defaultResourceDomain = defaultResourceDomain;
	}


	public String getMyDomain() {
		return myDomain;
	}


	public void setMyDomain(String myDomain) {
		this.myDomain = myDomain;
	}


	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}



	public void setServerDomain(String serverDomain) {
		this.serverDomain = serverDomain;
	}


	public String getScope() {
		return scope;
	}


	public void setScope(String scope) {
		this.scope = scope;
	}
	

	public String getCodeRedirectURI() {
		return codeRedirectURI;
	}


	public String getImplicitRedirectURI() {
		return implicitRedirectURI;
	}


	public String getGrantTypeCode() {
		return grantTypeCode;
	}


	public String getGrantTypeImplicit() {
		return grantTypeImplicit;
	}


	public String getGrantTypeClientCredential() {
		return grantTypeClientCredential;
	}


	public String getGrantTypeResourceOwner() {
		return grantTypeResourceOwner;
	}


	public String getMyRedirectAuthCodeURI() {
		
		return myDomain + this.codeRedirectURI;
	}
	
	public String getMyRedirectImplicitURI() {

		return myDomain + this.implicitRedirectURI;
	}
	
	public String getAuthorizeURI() {
		return serverDomain + authEndPoint;
	}


	public String getTokenURI() {

		return serverDomain + tokenEndPoint;
	}

	public String getAuthorizeRequestURI(Boolean isHTTPS) {
		URIBuilder authBuilder = new URIBuilder();
		if(isHTTPS) {
			authBuilder.setScheme("https");
		}else {
			authBuilder.setScheme("http");
		}

		// code 와 implicit 방식 곹옹
		authBuilder.setHost( getAuthorizeURI() ) ;
		authBuilder.addParameter("response_type", "code");
		authBuilder.addParameter("client_id",getClientID());
		authBuilder.addParameter("scope", getScope());
		try {
			authBuilder.addParameter("redirect_uri", URLEncoder.encode(this.getMyRedirectAuthCodeURI(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			authBuilder.addParameter("redirect_uri", URLEncoder.encode(this.getMyRedirectAuthCodeURI()));
		}

		return authBuilder.toString();
	}

	public String getImplicitURI(Boolean isHTTPS) {
		URIBuilder authBuilder = new URIBuilder();
		if(isHTTPS) {
			authBuilder.setScheme("https");
		}else {
			authBuilder.setScheme("http");
		}

		// code 와 implicit 방식 곹옹
		authBuilder.setHost( getAuthorizeURI() ) ;
		authBuilder.addParameter("response_type", "code");
		authBuilder.addParameter("client_id",getClientID());
		authBuilder.addParameter("scope", getScope());
		try {
			authBuilder.addParameter("redirect_uri", URLEncoder.encode(this.getMyRedirectAuthCodeURI(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			authBuilder.addParameter("redirect_uri", URLEncoder.encode(this.getMyRedirectAuthCodeURI()));
		}

		return authBuilder.toString();
	}
	
	@Override
	public String toString() {
		return "OAuthClientConfig [clientID=" + clientID + ", clientSecret=" + clientSecret + ", serverDomain="
				+ serverDomain + ", defaultResourceDomain=" + defaultResourceDomain + ", myDomain=" + myDomain
				+ ", scope=" + scope + ", codeRedirectURI=" + codeRedirectURI + ", implicitRedirectURI="
				+ implicitRedirectURI + ", AUTH_END_POINT=" + authEndPoint + ", TOKEN_END_POINT=" + tokenEndPoint
				+ "]";
	}

	
	
	
	
}
