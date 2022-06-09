package main.com.world.game.Players.viewer;

import main.com.world.display.Display;
import main.com.world.graphics.TextureAtlas;
import main.com.world.utils.Utils;

import java.awt.image.BufferedImage;

public class ImageViewer {
    protected final int SIZE = 84;

    public BufferedImage[][] Tank;
    public float speed = 0.1f;
    public Boolean TankAnim;
    public float num = 0;
    public float scale;

    public float BoomCounter = 0;
    public Boolean FinishBoom;
    public Boolean SatrtBoom;

    public ImageViewer(float scale){
        this.scale = scale;
        Tank = new BufferedImage[4][8];

        CleanExplosion();
        TankAnim = true;
    }


    public void update() {

        if (TankAnim) {
            num += speed;
            if (num >= 8) num = 0;
        }

        if (SatrtBoom) {
            BoomCounter += speed;
            if (BoomCounter >= 4) {
                BoomCounter = 0;
                FinishBoom = true;
            }
        }
    }

    public BufferedImage getNowImage(int n) {
        update();
        switch (n) {
            case 0:
                return Tank[0][(int) (num)];
            case 1:
                return Tank[1][(int) (num)];
            case 2:
                return Tank[2][(int) (num)];
            case 3:
                return Tank[3][(int) (num)];
            default:
                return Tank[0][0];
        }
    }

    public BufferedImage getExplosionImage() {
        return Display.textureBuffer.get(Texture.fromOrdinal( Texture.Exp_1.ordinal() + (int) Math.floor(BoomCounter) % 4));
    }

    public void StartExplosion() {
        TankAnim = false;
        SatrtBoom = true;
        FinishBoom = false;
    }

    public void CleanExplosion() {
        TankAnim = true;
        SatrtBoom = false;
        FinishBoom = false;
    }

    public Boolean BoomFinish() {
        return FinishBoom;
    }

    public Boolean BoomStart() {
        return SatrtBoom;
    }
}
