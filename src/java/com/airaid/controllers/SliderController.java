/*
 * Created by Osman Balci on 2018.08.03
 * Copyright © 2018 Osman Balci. All rights reserved.
 */
package com.airaid.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "sliderController")
@RequestScoped

public class SliderController {

    // Each String object in the List contains the image filename, e.g., photo1.png
    private List<String> sliderImages;

    /*
    The PostConstruct annotation is used on a method that needs to be executed after
    dependency injection is done to perform any initialization. The initialization 
    method init() is the first method invoked before this class is put into service. 
    */
    @PostConstruct
    public void init() {

        sliderImages = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            sliderImages.add("photo" + i + ".jpeg");
        }
    }

    /*
    =============
    Getter Method
    =============
     */
    public List<String> getSliderImages() {
        System.out.println(sliderImages.get(0));
        return sliderImages;
    }

}
