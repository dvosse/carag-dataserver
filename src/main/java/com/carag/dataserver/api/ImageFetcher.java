package com.carag.dataserver.api;

import org.apache.http.HttpRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@PropertySource(("classpath:credentialstore.properties"))
@Component
public class ImageFetcher {

    @Value("${lloyd.username}")
    private String username;

    @Value("${lloyd.password}")
    private String password;

    private HttpHeaders authHeaders;

    public Object getImage(String device){
        authenticate();

        String url = "https://www.alarm.com/web/Video/GetImage.aspx?res=0&qual=10&deviceID="+device;

        HttpEntity<String> request = new HttpEntity<>(this.authHeaders);
        HttpEntity<byte[]> response = new RestTemplate().exchange(url, HttpMethod.GET,request,byte[].class);

//        HttpEntity<String> request = new HttpEntity<>(this.authHeaders);
//        String url2 = "http://img.championat.com/news/big/l/c/ujejn-runi_1439911080563855663.jpg";
//        HttpEntity<byte[]>  response =  new RestTemplate().exchange(url2, HttpMethod.GET, request ,byte[].class);

//        String url2 = "http://img.championat.com/news/big/l/c/ujejn-runi_1439911080563855663.jpg";
//        byte[] imageBytes =  new RestTemplate().getForObject(url2, byte[].class);
       // Files.write(Paths.get("image.jpg"), imageBytes);

        return response.getBody();


    }

    private void authenticate(){

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
        body.add("ctl00$ContentPlaceHolder1$loginform$txtUserName", username);
        body.add("txtPasswordt", password);

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






}
