package com.carag.dataserver;

import com.carag.dataserver.api.ImageFetcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageFetcherTest {

    @Inject
    ImageFetcher im;

    @Test
    public void contextLoads() {

        im.getImage("2048");




    }

}
