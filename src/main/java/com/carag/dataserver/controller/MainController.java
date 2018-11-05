package com.carag.dataserver.controller;

import com.carag.dataserver.config.CredentialStore;
import com.carag.dataserver.connectors.AlarmConnector;
import com.carag.dataserver.model.Camera;
import com.carag.dataserver.model.Image;
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
    private Camera c;

    @PostConstruct
    public void buildCameraList(){

        AlarmConnector alarm = new AlarmConnector(store);
        this.c = new Camera(alarm, "2048");
        c.update();

        c.getNextFrame();
        c.getNextFrame();
        System.out.println();

    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public Object testGet(){

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(c.getNextFrame().getData());
    }

}
