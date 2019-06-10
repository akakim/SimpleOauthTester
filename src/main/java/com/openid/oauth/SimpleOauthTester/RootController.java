package com.openid.oauth.SimpleOauthTester;

import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.openid.oauth.service.AuthenticationService;
import com.openid.oauth.service.impl.AuthenticationServiceImpl;

@Controller
@RequestMapping
public class RootController {
	
	public static final String API_URL = "api";
	
	
	static final Logger logger = LoggerFactory.getLogger(RootController.class);
	@Autowired 
	OAuthClientConfig config;
		
	@Autowired 
	AuthenticationService asi;
	
	@RequestMapping({"","home","index"})
	public ModelAndView showHomePage(ModelMap m ) {
		
		ModelAndView mv = new ModelAndView("oauthSample");
		
		String state = new BigInteger(50, new SecureRandom()).toString(16);
	    String nonce = new BigInteger(50, new SecureRandom()).toString(16);
	    
		Map<String,String> result = new HashMap<>();
		
		// code 와 implicit 방식 곹옹
		result.put("authURI",config.getAuthorizeURI());
		result.put("clientId",config.getClientID());
		result.put("scope", config.getScope());
		
		// code 방식의 설정 . 
		result.put("authResponseType","code");
		result.put("state", state);
	
		// implicit방식의 설정. 
		result.put("implResponseType","token");
		result.put("implRedirectURI",config.getImplicitRedirectURI());

		result.put("authAPI",config.getAuthorizeRequestURI(false));
		
		result.put("clientCredentialAPI", "/requestClientCredential");
		result.put("resourceOwnerAPI", "/requestResourceOwner");
		
		mv.addAllObjects(result);
		return mv;
	}
	
	@RequestMapping("/responseCode")
	public ModelAndView responseCodeJSP(@PathVariable("path") String code,@PathVariable("state")String state,ModelMap m ) {
		
		
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add( new BasicNameValuePair("grant_type",config.getGrantTypeCode()));
		params.add( new BasicNameValuePair("code",code));
		params.add( new BasicNameValuePair("state",state));
		params.add( new BasicNameValuePair("scope",config.getScope()));
		
		AuthenticationResultModel response = asi.requestAuthorizeCode(params);

		
		logger.info(response.toString());
		
		if( response.getHttpCode() == 200 ) {
			ModelAndView mv = new ModelAndView("sessionView");
			
			mv.addObject( response);
			mv.addObject("grant_type", config.getGrantTypeCode());
			return mv;			
		}else {
			ModelAndView mv = new ModelAndView("error");
			mv.addObject( response);
			mv.addObject("grant_type", config.getGrantTypeCode());
			return mv;
		}
			

	}
	
	@RequestMapping("/requestImplicit")
	public String responseImplicit(){
		return "responseImplicit";
	}
		
	/**
	 * 만약 scope를 받아야할 일이 생긴다면,@PathVariable을 이용하여 좀 더 값을 추가한다. 
	 * @return
	 */
	@RequestMapping(value= {"/requestClientCredential"})
	public ModelAndView requestClientCredential() {
		
		
		
		
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add( new BasicNameValuePair("grant_type",config.getGrantTypeClientCredential()));
		params.add( new BasicNameValuePair("scope",config.getScope()));
		
		AuthenticationResultModel response = asi.requestClientCredential(params);
		
		logger.info(response.toString());
		if( response.getHttpCode() == 200) {
			ModelAndView mv = new ModelAndView("sessionView");
			mv.addObject( response);
			mv.addObject("grant_type", config.getGrantTypeClientCredential());
			
			
			return mv;			
		}else {
			ModelAndView mv = new ModelAndView("error");
			mv.addObject( response);
			mv.addObject("grant_type", config.getGrantTypeClientCredential());
			return mv;
		}

		
	}
	
	
	@RequestMapping(value= {"/requestResourceOwner"})
	public ModelAndView reuqetResourceOwner(@PathVariable("userId") String userId,@PathVariable("password")String password,ModelMap m) {
		
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add( new BasicNameValuePair("grant_type",config.getGrantTypeResourceOwner()));
		params.add( new BasicNameValuePair("scope",config.getScope()));
		
		AuthenticationResultModel response = asi.requestResourceOwner(params);
		
		logger.info(response.toString());
		if( response.getHttpCode() == 200) {
			ModelAndView mv = new ModelAndView("sessionView");
			mv.addObject( response);
			mv.addObject("grant_type", config.getGrantTypeResourceOwner());
			
			return mv;			
		}else {
			ModelAndView mv = new ModelAndView("error");
			mv.addObject( response);
			mv.addObject("grant_type", config.getGrantTypeResourceOwner());
			return mv;
		}
		
		
	}
	
	// 단순하게 서버 URL만 보여주는 정도로 수정 (JSON)
	@RequestMapping(value= {"/getConfig"})
	public ModelAndView requestConfig() {
		
		
		ModelAndView mv = new ModelAndView("test");
		
		System.out.println("config :"  + config);
		
		return mv;
	}
	
	
	@RequestMapping(value= {"/consoleConfig"})
	public String consoleConfig() {
		
		
		ModelAndView mv = new ModelAndView("test");
		
		System.out.println("config :"  + config);
		
		return "test";
	}
	
	
	
	/*@RequestMapping(value= {""})*/
	
}
