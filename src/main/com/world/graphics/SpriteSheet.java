package main.com.world.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;
    private int spriteCount;
    private int scale;
    private int spritesInWidth;
    private final int SIZE = 84;

    public SpriteSheet(BufferedImage sheet, int spriteCount,int scale){
        this.scale = scale;
        this.sheet = sheet;
        this.spriteCount = spriteCount;

        spritesInWidth = sheet.getWidth()/scale;
    }
    //index - от 0 до 7 - первый ряд;
    //index - от 8 до 15 - второй ряд;
    //index - от 16 до 23 - третий ряд;
    //index - от 24 до 31 - четвёртый ряд;
    public BufferedImage getSprite(int index){
        index = index % spriteCount;

        int x = index % spritesInWidth * scale;
        int y = index / spritesInWidth* scale;

        return sheet.getSubimage(x,y,scale,scale);
    }

}
