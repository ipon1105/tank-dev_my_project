package main.com.world.game.Players;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObjects extends HitBox {
    public float x;
    public float y;
    public float x2;
    public float y2;

    public BufferedImage image;

    public GameObjects(float x, float y, BufferedImage image){
        super();
        this.x = x;
        this.y = y;
        this.image = image;

        if (image==null)return;
        this.x2 = x+image.getWidth();
        this.y2 = y+image.getHeight();
    }

    public void render(Graphics2D g){
        g.drawImage(image, (int)x,(int)y, image.getWidth(),image.getHeight(),null);
        if (hitBox)
            hitBoxRender(g);
    }

    public void hitBoxRender(Graphics2D g){
        g.drawRect((int)x-1,(int)y-1,(int)image.getWidth(),(int)image.getHeight()+1);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getX2(){
        return x+image.getWidth();
    }

    public float getY2(){
        return y+image.getHeight();
    }

    public Boolean Collision(GameObjects G){
        return (((x<=G.getX2())&&(G.getX2()<=getX2())||(x<=G.getX())&&(G.getX()<=getX2()))&&((getY2()>=G.getY())&&(G.getY()>=y)||(getY2()>=G.getY2())&&(G.getY2()>=y)));
    }
}
