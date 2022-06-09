package main.com.world.game.Players.viewer;

public enum Texture {
    Floor, BrickWall, BrickWall1, BrickWall2, BrickWall3, SteelWall,
    Exp_1, Exp_2, Exp_3, Exp_4,
    Bullet,

    gUP_1, gUP_2, gUP_3, gUP_4, gUP_5, gUP_6, gUP_7, gUP_8,
    gRIGHT_1, gRIGHT_2, gRIGHT_3, gRIGHT_4, gRIGHT_5, gRIGHT_6, gRIGHT_7, gRIGHT_8,
    gDOWN_1, gDOWN_2, gDOWN_3, gDOWN_4, gDOWN_5, gDOWN_6, gDOWN_7, gDOWN_8,
    gLEFT_1, gLEFT_2, gLEFT_3, gLEFT_4, gLEFT_5, gLEFT_6, gLEFT_7, gLEFT_8,

    bUP_1, bUP_2, bUP_3, bUP_4, bUP_5, bUP_6, bUP_7, bUP_8,
    bRIGHT_1, bRIGHT_2, bRIGHT_3, bRIGHT_4, bRIGHT_5, bRIGHT_6, bRIGHT_7, bRIGHT_8,
    bDOWN_1, bDOWN_2,bDOWN_3, bDOWN_4, bDOWN_5, bDOWN_6, bDOWN_7, bDOWN_8,
    bLEFT_1, bLEFT_2,bLEFT_3, bLEFT_4, bLEFT_5, bLEFT_6, bLEFT_7, bLEFT_8;

    private static Texture[] allValues = values();
    public static Texture fromOrdinal(int n) {return allValues[n];}
}
