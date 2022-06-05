package main.com.world.setting;

public abstract class Setting {
    //Player_A settings
    public static final float SPEED_A = 1.5f;
    public static final float SCALE_A = 0.25f;
    public static final float HP_A= 100f;

    //Player_B settings
    public static final float SPEED_B = 1.5f;
    public static final float SCALE_B= 0.25f;
    public static final float HP_B= 100f;

    //Game settings
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1000;

    //FieldMap settings
    public static float FIELD_IN_SCALE = 0.25f;
    public static final float FIELD_ON_ATLAS = 84f;
    public static final float FIELD_ON_ONE = FIELD_ON_ATLAS * FIELD_IN_SCALE;
    public static final String FILENAME = "/resource/levels/level_1.lvl";

    //Bullet Type_1
    public static final float A_BULLET_SPEED = 6.0f;
    public static final float A_BULLET_DAMAGE = 5.0f;
    public static final float A_BULLET_COOLDOWN = 100.0f;

    //Bullet Type_2
    public static final float B_BULLET_SPEED = 3.0f;
    public static final float B_BULLET_DAMAGE = 15.0f;
    public static final float B_BULLET_COOLDOWN = 50.0f;

    //Bullet Type_3
    public static final float C_BULLET_SPEED = 2.0f;
    public static final float C_BULLET_DAMAGE = 35.0f;
    public static final float C_BULLET_COOLDOWN = 30.0f;

    //BulletSettings
    public static Boolean BULLET_HITBOX_RENDER = false;

    //Bullet Type_Default
    public static final float DEFAULT_BULLET_SPEED = 5.0f;
    public static final float DEFAULT_BULLET_DAMAGE = 20.0f;
    public static final float DEFAULT_BULLET_COOLDOWN = 120.0f;

    //Field floor
    public static final int FLOOR_HP = 1;

    //Field wall_1
    public static final int WALL_1_HP = 1;

    //Field wall_2
    public static final int WALL_2_HP = 100;

    //Field base
    public static final int BASE_HP = 1000;

    //Online
    public static String IP4 = "127.0.0.1";
    public static int PORT = 56566;
    public static int BACKLOG = 4;

}
