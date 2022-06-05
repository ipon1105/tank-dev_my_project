package main.com.world.graphics;

import main.com.world.utils.ResourceLoader;

import java.awt.image.BufferedImage;

public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(String imageName){
        image = ResourceLoader.loadImage(imageName);
    }

    public BufferedImage cut(int x,int y,int w, int h){
        return image.getSubimage(x,y,w,h);
    }
    public BufferedImage cut(String imageName,int x,int y,int w, int h){
        return ResourceLoader.loadImage(imageName).getSubimage(x,y,w,h);
    }

    public BufferedImage getImage(String imageName){
        return  ResourceLoader.loadImage(imageName);
    }

}
