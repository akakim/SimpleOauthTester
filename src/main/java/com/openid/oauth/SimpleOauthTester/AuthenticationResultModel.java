package com.openid.oauth.SimpleOauthTester;

public class AuthenticationResultModel {

	private int httpCode;
	private String errorMessage;
	private String accessToken;
	private String refreshToken;
	private String expiredIn;
	private String idToken;
	private String scope;
	private String error;
	private String error_description;
	
	public String getScope() {
		return scope;
	}


	public void setScope(String scope) {
		this.scope = scope;
	}


	public int getHttpCode() {
		return httpCode;
	}


	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public String getRefreshToken() {
		return refreshToken;
	}


	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}


	public String getExpiredIn() {
		return expiredIn;
	}


	public void setExpiredIn(String expiredIn) {
		this.expiredIn = expiredIn;
	}


	public String getIdToken() {
		return idToken;
	}


	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	
	
	public String decodedAccessToken() {
		
		return null;
	}
	
	
	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getError_description() {
		return error_description;
	}


	public void setError_description(String error_description) {
		this.error_description = error_description;
	}


	private String decodeJWT(String jwt) {
		
		return jwt;
	}


	@Override
	public String toString() {
		return "AuthenticationResultModel [httpCode=" + httpCode + ", errorMessage=" + errorMessage + ", accessToken="
				+ accessToken + ", refreshToken=" + refreshToken + ", expiredIn=" + expiredIn + ", idToken=" + idToken
				+ ", scope=" + scope + ", error=" + error + ", error_description=" + error_description + "]";
	}
	
	
	
}
