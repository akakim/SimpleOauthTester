package com.openid.oauth.SimpleOauthTester;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevConfig extends OAuthClientConfig{

	
	
	/*@Override
	public void setUp(){
		
		System.out.println( "DevConfig");
		DatasourceConfig.oauthSettingMap.put(ID, "4e76adcd-4136-4f2f-a64e-d06da21a5039");
		DatasourceConfig.oauthSettingMap.put(SECRETE, "AJGrqh_Po9Fuyb3bVnhks2zMZy8V33RAuTAZYgITnpCi5sKwWUC8ZL4RDcRj0ZJUlYbAw2uDi6OXm0dgBeBmLJE");
		DatasourceConfig.oauthSettingMap.put(SERVER_DOMAIN, "http://10.10.30.196:50001");
		DatasourceConfig.oauthSettingMap.put(RESOURCE_DOMAIN, "http://localhost:50001");
		DatasourceConfig.oauthSettingMap.put(MY_DOMAIN, "http://localhost:50001");
		
		printAllValue();
	}

	@Override
	public String getClientID() {
		// TODO Auto-generated method stub
		return DatasourceConfig.oauthSettingMap.get(ID);
	}

	@Override
	public String getClientSecrete() {
		// TODO Auto-generated method stub
		return DatasourceConfig.oauthSettingMap.get(SECRETE);
	}

	@Override
	public String getServerDomain() {
		// TODO Auto-generated method stub
		return DatasourceConfig.oauthSettingMap.get(SERVER_DOMAIN);
	}

	@Override
	public String getResourceDomain() {
		// TODO Auto-generated method stub
	
		return DatasourceConfig.oauthSettingMap.get(RESOURCE_DOMAIN);
	}

	@Override
	public void printAllValue() {
		
		System.out.println(  "Local Client Setting: " 
		+ "\n [Client id] " + 		DatasourceConfig.oauthSettingMap.getOrDefault(ID, "") 
		+ "\n [Client Secrete]" + DatasourceConfig.oauthSettingMap.getOrDefault(SECRETE, "") 
		+ "\n[Server Domain]" + DatasourceConfig.oauthSettingMap.getOrDefault(SERVER_DOMAIN, "")
		+ "\n[Resource Domain]" + DatasourceConfig.oauthSettingMap.getOrDefault(RESOURCE_DOMAIN, "")
		+ "\n[My Domain]" + DatasourceConfig.oauthSettingMap.getOrDefault(MY_DOMAIN, ""));
		

	}*/
	
/*	@Override
	public String getMyRedirectAuthCodeURI() {
		
		return DatasourceConfig.oauthSettingMap.get(MY_DOMAIN) + CODE_REDIRECT_URI;
	}
	
	@Override
	public String getMyRedirectImplicitURI() {

		return DatasourceConfig.oauthSettingMap.get(MY_DOMAIN) + IMPLICIT_REDIRECT_URI;
	}

	@Override
	public String getAuthorizeURI() {
		// TODO Auto-generated method stub
		return DatasourceConfig.oauthSettingMap.get(SERVER_DOMAIN) + AUTH_END_POINT;
	}

	@Override
	public String getTokenURI() {
		// TODO Auto-generated method stub
		return DatasourceConfig.oauthSettingMap.get(SERVER_DOMAIN) + TOKEN_END_POINT;
	}*/

}
