package main.com.world.game.level;

import main.com.world.game.Game;
import main.com.world.game.Players.Player;
import main.com.world.game.Players.bullet.Bullet;
import main.com.world.graphics.TextureAtlas;
import main.com.world.setting.Setting;
import main.com.world.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FieldMap {
    private BufferedImage floor_1;
    private BufferedImage wall_1;
    private BufferedImage wall_2;
    private BufferedImage wall_2_1;
    private BufferedImage wall_2_2;
    private BufferedImage wall_2_3;
    private BufferedImage base;

    private int[][] numericMap;
    private Field[][] fieldMap;

    private Field[] fieldsASpawn;
    private Field[] fieldsBSpawn;

    public FieldMap(TextureAtlas atlas){
        numericMap = Utils.levelParser(Setting.FILENAME);
        fieldMap = new Field[numericMap.length][numericMap[0].length];

        ImageResize(atlas);
        setFields();

        setsFieldsSpawns();
    }

    public FieldMap(TextureAtlas atlas, int map[][]){
        numericMap = map;
        fieldMap = new Field[numericMap.length][numericMap[0].length];

        ImageResize(atlas);
        setFields();

        setsFieldsSpawns();
    }

    private void ImageResize(TextureAtlas atlas) {

        BufferedImage a = atlas.cut(0, 0, 84, 84);
        BufferedImage b = atlas.cut(2 * 84, 3 * 84, 84, 84);
        BufferedImage c = atlas.cut("wall.png", 0, 0, 1754, 1753);
        BufferedImage c_1 = atlas.getImage("wall_changes_1.png");
        BufferedImage c_2 = atlas.getImage("wall_changes_2.png");
        BufferedImage c_3 = atlas.getImage("wall_changes_3.png");
        BufferedImage d = atlas.cut(7 * 84, 2 * 84, 84, 84);

        this.floor_1 = Utils.resize(a, (int) (a.getWidth() * Setting.FIELD_IN_SCALE), (int) (a.getHeight() * Setting.FIELD_IN_SCALE));
        this.wall_1 = Utils.resize(b, (int) (b.getWidth() * Setting.FIELD_IN_SCALE), (int) (b.getHeight() * Setting.FIELD_IN_SCALE));
        this.wall_2 = Utils.resize(c, (int) (b.getWidth() * Setting.FIELD_IN_SCALE -2), (int) (b.getHeight() * Setting.FIELD_IN_SCALE -2));
        this.wall_2_1 = Utils.resize(c_1,(int)(b.getWidth()*Setting.FIELD_IN_SCALE -2),(int)(b.getHeight()*Setting.FIELD_IN_SCALE -2));
        this.wall_2_2 = Utils.resize(c_2,(int)(b.getWidth()*Setting.FIELD_IN_SCALE -2),(int)(b.getHeight()*Setting.FIELD_IN_SCALE -2));
        this.wall_2_3 = Utils.resize(c_3,(int)(b.getWidth()*Setting.FIELD_IN_SCALE -2),(int)(b.getHeight()*Setting.FIELD_IN_SCALE -2));
        this.base = Utils.resize(d, (int) (d.getWidth() * Setting.FIELD_IN_SCALE), (int) (d.getHeight() * Setting.FIELD_IN_SCALE));
    }

    private void setFields() {
        int A = 0;
        int B = 0;

        final float abd = 21;

        for (int i = 0; i < fieldMap.length; i++) {
            for (int j = 0; j < fieldMap[0].length; j++) {
                switch (numericMap[i][j]) {
                    case 1:
                        fieldMap[i][j] = new Field(j * abd, i * abd, wall_1,1000);
                        break;
                    case 2:
                        fieldMap[i][j] = new Field(j * abd+1, i * abd+1, wall_2,100);
                        break;
                    case 3:
                        fieldMap[i][j] = new Field(j * abd, i * abd, base,100);
                        break;
                    case 4:
                        fieldMap[i][j] = new Field(j * abd, i * abd, floor_1,100);
                        A++;
                        break;
                    case 5:
                        B++;
                        fieldMap[i][j] = new Field(j * abd, i * abd, floor_1,100);
                        break;
                    default:
                        fieldMap[i][j] = new Field(j * abd, i * abd, floor_1,100);
                        break;
                }
            }
        }
        fieldsASpawn = new Field[A];
        fieldsBSpawn = new Field[B];
    }

    private void setsFieldsSpawns(){
        int A = 0;
        int B = 0;
        for (int i = 0; i < fieldMap.length; i++) {
            for (int j = 0; j < fieldMap[0].length; j++) {
                if(numericMap[i][j]==4){
                    fieldsASpawn[A] = fieldMap[i][j];
                    A++;
                }
                if(numericMap[i][j]==5){
                    fieldsBSpawn[B] = fieldMap[i][j];
                    B++;
                }
            }
        }
    }

    public void update(){

    }

    public float[] getSpawnA(){
        if (fieldsASpawn.length==1){
            return new float[]{fieldsASpawn[0].getX(),fieldsASpawn[0].getY()};
        }

        Random R = new Random();
        int random = R.nextInt(fieldsASpawn.length);
        return  new float[]{fieldsASpawn[random].getX(),fieldsASpawn[random].getY()};
    }

    public float[] getSpawnB(){
        if (fieldsBSpawn.length==1){
            return new float[]{fieldsBSpawn[0].getX(),fieldsBSpawn[0].getY()};
        }

        Random R = new Random();
        int random = R.nextInt(fieldsBSpawn.length);
        return  new float[]{fieldsBSpawn[random].getX(),fieldsBSpawn[random].getY()};
    }

    //отрисовка карты
    public void render(Graphics2D g){
        for (int i = 0; i < fieldMap.length; i++) {
            for (int j = 0; j < fieldMap[0].length; j++) {
                fieldMap[i][j].render(g);
            }
        }
    }

    //Возвращает можно ли ехать или нет
    public Boolean Collision(Player A, int n) {
        switch (n) {
            case 0:
                return COLLISION_TEST(A.getX(), A.getY() - 1, A.getX2(), A.getY() - 1);
            case 1:
                return COLLISION_TEST(A.getX2() + 1, A.getY(), A.getX2() + 1, A.getY2());
            case 2:
                return COLLISION_TEST(A.getX(), A.getY2() + 1, A.getX2(), A.getY2() + 1);
            case 3:
                return COLLISION_TEST(A.getX() - 1, A.getY(), A.getX() - 1, A.getY2());
            default:
                return false;
        }
    }

    //Вспомогательная функция для определения есть ли стена на стороне движения
    public Boolean COLLISION_TEST(float x, float y, float x2, float y2) {
        Boolean Left = wall(getPos(y), getPos(x));
        Boolean Right = wall(getPos(y2), getPos(x2));
        return (Left || Right);
    }

    //Вспомогательная функция для опреленения есть ли стена или нет
    public Boolean wall(int Row, int Colom) {
        if (Row >= numericMap.length || Colom >= numericMap[0].length)
            return true;
        if (Row < 0 || Colom < 0)
            return true;
        return ((numericMap[Row][Colom] == 1) || (numericMap[Row][Colom] == 2));
    }

    //вспомогательная функция для возврата места на карте
    public int getPos(float num) {
        return (int) Math.floor(num / Setting.FIELD_ON_ONE);
    }

    //Для регистрации поподания в  стены
    public Boolean cheakShoot(Bullet b) {
        return (updateWall(getPos(b.getY()), getPos(b.getX()),b.getDamage()) || updateWall(getPos(b.getY2()), getPos(b.getX()),b.getDamage()) || updateWall(getPos(b.getY()), getPos(b.getX2()),b.getDamage()) || updateWall(getPos(b.getY2()), getPos(b.getX2()),b.getDamage()));
    }

    //Вспомогательная функция для регистрации поподания
    public Boolean updateWall(int Row, int Colom, int Damage) {
        //Проверяет какая стена. Если 1, то возвращает true, если 2, то уничтожат её и возвращает true. Если ни 1 и ни 2, то возваращает false
        if (Row >= numericMap.length || Colom >= numericMap[0].length)
            return true;
        if (Row < 0 || Colom < 0)
            return true;
        if (numericMap[Row][Colom] == 2) {
            fieldMap[Row][Colom].damage(Damage);
            switch (fieldMap[Row][Colom].getPeriod()){
                case 3:
                    break;
                case 2:
                    fieldMap[Row][Colom].image = wall_2_1;
                    break;
                case 1:
                    fieldMap[Row][Colom].image = wall_2_2;
                    break;
                default:
                    fieldMap[Row][Colom].image = wall_2_3;
                    break;
            }
            if (!fieldMap[Row][Colom].getLive()) {
                numericMap[Row][Colom] = 0;
                fieldMap[Row][Colom] = new Field(Colom * Setting.FIELD_ON_ONE,Row * Setting.FIELD_ON_ONE, floor_1, 100);
            }
            return true;
        }
        return (numericMap[Row][Colom] == 1);
    }
}
