package main.com.world.game.Players;

import main.com.world.game.level.FieldMap;

import java.awt.event.KeyEvent;

public class Controller {
    public final int W;
    public final int A;
    public final int S;
    public final int D;
    public final int _1;
    public final int _2;
    public final int _3;
    public final int SHOOT;
    public byte color;
    public Boolean online;

    public Controller(int W, int A, int S, int D, int _1, int _2, int _3, int SHOOT, byte color, Boolean online){
        this.W = W;
        this.A = A;
        this.S = S;
        this.D = D;

        this._1 = _1;
        this._2 = _2;
        this._3 = _3;

        this.SHOOT = SHOOT;
        this.color = color;
        this.online = online;
    }

    public int get_1() {
        return _1;
    }

    public int get_2() {
        return _2;
    }

    public int get_3() {
        return _3;
    }

    public int getA() {
        return A;
    }

    public int getD() {
        return D;
    }

    public int getS() {
        return S;
    }

    public int getSHOOT() {
        return SHOOT;
    }

    public int getW() {
        return W;
    }

    public byte getColor() {
        return color;
    }

    public Boolean getOnline() {
        return online;
    }
}
