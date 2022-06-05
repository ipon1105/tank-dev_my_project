package main.com.world.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class ResourceLoader {
    public static final String PATH = "/resource/";
    static ResourceLoader rl = new ResourceLoader();

    public static BufferedImage loadImage(String fileName){
        BufferedImage image = null;
        try{
            image = ImageIO.read(rl.getClass().getResource(PATH+fileName));
        } catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }

}
