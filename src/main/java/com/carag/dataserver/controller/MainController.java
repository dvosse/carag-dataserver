package com.carag.dataserver.controller;

import com.carag.dataserver.config.AllowedTypes;
import com.carag.dataserver.config.ApplicationContextProvider;
import com.carag.dataserver.config.CredentialStore;
import com.carag.dataserver.model.Camera;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Controller
public class MainController {

    @Inject
    private CredentialStore store;

    @Inject
    private ApplicationContextProvider provider;


    @PostConstruct
    public void buildCameraList() throws Exception{

        ApplicationContext ac = provider.getContext();

        Camera c = (Camera) ac.getBean(Camera.class);
        Camera c2 = (Camera) ac.getBean(Camera.class);

        store.setLloydUser("gggggg");

        c2.setName("Hello");


        System.out.println();




      // Camera c = new Camera(AllowedTypes.Alarm, this.store);



    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public Object testGet() throws Exception{




     //   byte [] x = c.getNextFrame().getData();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body("");
    }

}
