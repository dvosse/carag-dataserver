package com.carag.dataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.HttpsURLConnection;

@SpringBootApplication
public class DataserverApplication {

	public static void main(String[] args) {

        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
        HttpsURLConnection.setFollowRedirects(true);

        SpringApplication.run(DataserverApplication.class, args);
	}

}
