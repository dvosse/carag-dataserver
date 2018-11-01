package com.carag.dataserver.controller;

import com.carag.dataserver.api.ImageFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @Autowired
    ImageFetcher im;

    public void run(){

       // this.im.getImage("2048");

    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public Object testGet(){

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(im.getImage("2048"));

    }

}
