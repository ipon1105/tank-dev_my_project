package main.com.world.setting;

public abstract class Setting {
    //Player_A settings
    public static final int SPEED_A = 1;
    public static final int SCALE_A = 0;

    //Player_B settings
    public static final int SPEED_B = 1;
    public static final int SCALE_B= 0;

    //Game settings
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1000;

    public static final String FILENAME = "/resource/levels/level_1.lvl";

    //Bullet Type_1
    public static final int A_BULLET_SPEED = 15;
    public static final int A_BULLET_DAMAGE = 5;
    public static final int A_BULLET_COOLDOWN = 100;

    //Bullet Type_2
    public static final int B_BULLET_SPEED = 25;
    public static final int B_BULLET_DAMAGE = 1;
    public static final int B_BULLET_COOLDOWN = 300;

    //Bullet Type_3
    public static final int C_BULLET_SPEED = 10;
    public static final int C_BULLET_DAMAGE = 35;
    public static final int C_BULLET_COOLDOWN = 30;

    //BulletSettings
    public static Boolean BULLET_HITBOX_RENDER = false;

    //Bullet Type_Default
    public static final int DEFAULT_BULLET_SPEED = 5;
    public static final int DEFAULT_BULLET_DAMAGE = 20;
    public static final int DEFAULT_BULLET_COOLDOWN = 120;
    //Field wall_2
    public static final int WALL_2_HP = 100;

    //Online
    public static String IP4 = "127.0.0.1";
    public static int PORT = 56566;
    public static int BACKLOG = 4;

}
