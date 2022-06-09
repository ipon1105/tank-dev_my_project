package main.com.world.game.Players;

import main.com.world.display.Display;
import main.com.world.game.Players.viewer.Texture;
import main.com.world.game.level.Coord;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObjects extends Coord implements Render {
    protected BufferedImage image;
    public Boolean hitBox;

    public GameObjects(int x, int y, BufferedImage image){
        super(x, y);
        this.x = x;
        this.y = y;

        this.image = image;
        this.hitBox = false;
    }

    public GameObjects(int x, int y, Texture texture) {
        this.x = x;
        this.y = y;

        this.image = Display.textureBuffer.get(texture);
        this.hitBox = false;
    }

    public void render(Graphics2D g){
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(),null);
        if (hitBox)
            g.drawRect(x - 1,y - 1, image.getWidth() + 1,image.getHeight() + 1);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getX2(){
        return x + image.getWidth();
    }

    public int getY2(){
        return y + image.getHeight();
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public Boolean Collision(GameObjects G){
        return (
                ((x <= G.getX2()) && (G.getX2() <= getX2()) ||
                (x <= G.getX())  && (G.getX()  <= getX2())) &&
                ((getY2() >= G.getY()) && (G.getY() >= y) ||
                (getY2() >= G.getY2()) && (G.getY2()>=y))
        );
    }

    public void setHitBox(Boolean hitBox){
        this.hitBox = hitBox;
    }

    public Boolean getHitBox(){
        return this.hitBox;
    }
}
