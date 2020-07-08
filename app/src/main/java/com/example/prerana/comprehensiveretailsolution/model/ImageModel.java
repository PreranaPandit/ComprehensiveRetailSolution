package com.example.prerana.comprehensiveretailsolution.model;

/**
 * Created by Prerana Pandit on 30/06/2020.
 */

public class ImageModel {

    private int image_drawable;

    public ImageModel(int image_drawable) {
        this.image_drawable = image_drawable;
    }

    public ImageModel()
    {

    }
    public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }
}
