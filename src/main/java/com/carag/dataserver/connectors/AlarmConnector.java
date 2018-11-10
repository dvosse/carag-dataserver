package com.carag.dataserver.connectors;

import com.carag.dataserver.config.CredentialStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AlarmConnector implements  WebConnector{


    private CredentialStore store;
    private HttpHeaders authHeaders;

    public AlarmConnector(CredentialStore store) {
        this.store = store;
        authenticate();
    }

    public byte[] getImage(String target){

        String url = "https://www.alarm.com/web/Video/GetImage.aspx?res=0&qual=10&deviceID="+target;
        HttpEntity<String> request = new HttpEntity<>(this.authHeaders);
        HttpEntity<byte[]> response = new RestTemplate().exchange(url, HttpMethod.GET,request,byte[].class);

        return response.getBody();
    }

    public void authenticate(){

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().disableRedirectHandling().build());
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        String url = "https://www.alarm.com/web/Default.aspx";
        StringBuilder cookies = new StringBuilder("cookieTest=1;IsFromNewSite=1;loggedInAsSubscriber=1;");
        Set<String> authParams = new HashSet<>(Arrays.asList("afg", "auth_CustomerDotNet", "twoFactorAuthenticationId", "ASP.NET_SessionId"));

        headers.add("Content-Type","application/x-www-form-urlencoded");
        headers.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");

        body.add("IsFromNewSite","1");
        body.add("ctl00$ContentPlaceHolder1$loginform$txtUserName", store.getLloydUser());
        body.add("txtPasswordt", store.getLloydPass());

        HttpEntity<String> response = restTemplate.postForEntity(url, new HttpEntity<>(body, headers),String.class);

        for (String v : new HashSet<>(response.getHeaders().get("Set-Cookie"))) {
            String t = v.split(";")[0];
            if (authParams.contains(t.split("=")[0])) {
                cookies.append(t).append(";");
            }
        }

        headers.add("Cookie", cookies.toString());
        this.authHeaders = headers;
    }

    public CredentialStore getStore() {
        return store;
    }

    public void setStore(CredentialStore store) {
        this.store = store;
    }
}
