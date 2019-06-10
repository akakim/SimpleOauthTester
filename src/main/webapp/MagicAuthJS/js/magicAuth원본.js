/**
 * Dreamsecurity Comp, Oauth Client JS, 2018-10
 */
var magicAuth = function () {
    var authorizeServer = "http://10.10.30.85:8081/openid-connect-server-webapp";
    var resourceServer = "http://10.10.30.85:8082";
    var clientId = "";
    var redirectUri = "";
    var scope = "";
    var nonce = "";
    var state = "";
    var rsObj = "";
    var status = "0";

    function authInit(clientId, redirectUri, scope) {

        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.scope = scope;
        this.state = randomString();
        this.nonce = randomString();

        setStorage('state',this.state);
    }

    // 랜덤 String 값 생성
    function randomString() {
        return Math.random().toString(36).slice(2);
    }

    /**
     *  sessionStorage, cookie 에 state, nonce 저장
     * @returns {*}
     */
    function setStorage(key, value) {
        // 쿠키저장
        const date = new Date();
        date.setDate(date.getTime() + 60 * 5 * 1000); //5분간 유효
        document.cookie = key + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
        // sessionStorage 저장
        sessionStorage.setItem(key, value);
    }

    function removeStorage(key) {
        return sessionStorage.removeItem(key);
    }

    function getStorage(key) {
        return sessionStorage.getItem(key);
    }

    function getCookie(key) {
        const value = document.cookie.match('(^|;) ?' + key + '=([^;]*)(;|$)');
        return value ? value[2] : null;
    }

    function getClientId() {
        return this.clientId;
    }

    function loginAuthorize() {
        var uri = authorizeServer + "/authorize" + "?response_type=token&client_id=" + this.clientId + "&redirect_uri=" + encodeURIComponent(this.redirectUri) + "&state=" + this.state;
        if (this.scope.length > 0) {
            uri = uri + "&scope=" + encodeURIComponent(this.scope);
        }
        location.href = uri;
    }

    function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\#&]" + name + "=([^&#]*)"),
            results = regex.exec(location.hash);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

    function httpGetConnection(aUrl, token, aCallback) {
        var anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() {
            if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
                aCallback(anHttpRequest.responseText);
        };

        anHttpRequest.open( "GET", aUrl, true );
        anHttpRequest.setRequestHeader("Authorization","Bearer " + token);
        anHttpRequest.send( null );
    }
    
    function getTokenValue(key) {
    	return this.rsObj[key];
	}
    
    function getAllTokenValue() {
    	return this.rsObj;
	}

    return {

        init: function (clientId, redirectUri, scope, callback) {
            var accessToken = getParameterByName('access_token');
            var rState = getParameterByName('state');

            if(accessToken || rState){
            	
                console.log('accessToken = ', accessToken);
                console.log('return state = ', rState);

                if(getCookie('state')){
                    this.state = getCookie('state');
                }else if(getStorage('state')){
                    this.state = getStorage('state');
                }
                if(rState === this.state){
                    removeStorage('state');
                    setStorage('access_token', accessToken);

                    httpGetConnection(resourceServer + '/userinfo', accessToken, function(response) {
                        this.rsObj = JSON.parse(response);
                        //console.log(this.rsObj);
                        this.status = '1';
                        return callback(this.status);
                    });

                }else{
                    console.log('요청한 State가 다릅니다 : ', rState);
                }
            }
            else{
                return authInit(clientId, redirectUri, scope);
            }
        },
        login: function () {
            return loginAuthorize();
        },
        test: function () {
            return getClientId();
        },
        userInfo: function (key) {
        	if(key != null){
        		return getTokenValue(key);
        	}
        	else{
        		return getAllTokenValue();
        	}
        }
    }
};
