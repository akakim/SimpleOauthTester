package com.openid.oauth.SimpleOauthTester;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("op")
public class OpConfig extends OAuthClientConfig{

	/*
	@Override
	public void setUp(){
		
		System.out.println( " OperatrionConfig");
		OAuthClientConfig.oauthSettingMap.put(ID, "4e76adcd-4136-4f2f-a64e-d06da21a5039");
		OAuthClientConfig.oauthSettingMap.put(SECRETE, "AJGrqh_Po9Fuyb3bVnhks2zMZy8V33RAuTAZYgITnpCi5sKwWUC8ZL4RDcRj0ZJUlYbAw2uDi6OXm0dgBeBmLJE");
		OAuthClientConfig.oauthSettingMap.put(SERVER_DOMAIN, "http://10.10.30.196:50001");
		OAuthClientConfig.oauthSettingMap.put(RESOURCE_DOMAIN, "http://localhost:50001");
		OAuthClientConfig.oauthSettingMap.put(MY_DOMAIN, "http://localhost:50001");
		
		printAllValue();
	}

	@Override
	public String getClientID() {
		// TODO Auto-generated method stub
		return OAuthClientConfig.oauthSettingMap.get(ID);
	}

	@Override
	public String getClientSecrete() {
		// TODO Auto-generated method stub
		return OAuthClientConfig.oauthSettingMap.get(SECRETE);
	}

	@Override
	public String getServerDomain() {
		// TODO Auto-generated method stub
		return OAuthClientConfig.oauthSettingMap.get(SERVER_DOMAIN);
	}

	@Override
	public String getResourceDomain() {
		// TODO Auto-generated method stub
	
		return OAuthClientConfig.oauthSettingMap.get(RESOURCE_DOMAIN);
	}

	@Override
	public void printAllValue() {
		
		System.out.println(  "Local Client Setting: " 
		+ "\n [Client id] " + 		OAuthClientConfig.oauthSettingMap.getOrDefault(ID, "") 
		+ "\n [Client Secrete]" + OAuthClientConfig.oauthSettingMap.getOrDefault(SECRETE, "") 
		+ "\n[Server Domain]" + OAuthClientConfig.oauthSettingMap.getOrDefault(SERVER_DOMAIN, "")
		+ "\n[Resource Domain]" + OAuthClientConfig.oauthSettingMap.getOrDefault(RESOURCE_DOMAIN, "")
		+ "\n[My Domain]" + OAuthClientConfig.oauthSettingMap.getOrDefault(MY_DOMAIN, ""));
		
	}
	
eturn DatasourceConfig.oauthSettingMap.get(SERVER_DOMAIN) + TOKEN_END_POINT;
	}*/
}

