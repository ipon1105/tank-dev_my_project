package main.com.world.game.level;

import main.com.world.display.Display;
import main.com.world.game.Game;
import main.com.world.game.Players.Player;
import main.com.world.game.Players.Render;
import main.com.world.game.Players.bullet.Bullet;
import main.com.world.game.Players.viewer.Texture;
import main.com.world.graphics.TextureAtlas;
import main.com.world.setting.Setting;
import main.com.world.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class FieldMap extends Coord implements Render {
    private int[][] numericMap;
    private Field[][] fieldMap;

    private ArrayList<Field> fieldsASpawn;
    private ArrayList<Field> fieldsBSpawn;

    public int height = 21;
    public int width = 21;

    public FieldMap(){
        numericMap = Utils.levelParser(Setting.FILENAME);
        fieldMap = new Field[numericMap.length][numericMap[0].length];

        fieldsASpawn = new ArrayList<>();
        fieldsBSpawn = new ArrayList<>();

        ImageResize();
        setFields();
    }

    public FieldMap(int map[][]){
        numericMap = map;
        fieldMap = new Field[numericMap.length][numericMap[0].length];

        fieldsASpawn = new ArrayList<>();
        fieldsBSpawn = new ArrayList<>();

        ImageResize();
        setFields();

    }

    private void ImageResize() {
        //height = 21;
        //width = 21;

        height = Game.HEIGHT / numericMap.length;
        width = Game.WIDTH / numericMap[0].length;

        Display.textureBuffer.resize(width, height);
    }

    private void setFields() {
        for (int i = 0; i < fieldMap.length; i++)
            for (int j = 0; j < fieldMap[0].length; j++) {
                switch (numericMap[i][j]) {
                    case 1:
                        fieldMap[i][j] = new Steal(j * width, i * height);
                        break;
                    case 2:
                        fieldMap[i][j] = new Brick(j * width,  i * height);
                        break;
                    case 4:
                        fieldMap[i][j] = new Floor(j * width, i * height);
                        fieldsASpawn.add(fieldMap[i][j]);
                        break;
                    case 5:
                        fieldMap[i][j] = new Floor(j * width, i * height);
                        fieldsBSpawn.add(fieldMap[i][j]);
                        break;
                    default:
                        fieldMap[i][j] = new Floor(j * width, i * height);
                        break;
                }
            }
    }

    public void update(){

    }

    public int[] getSpawnA(){
        int len = fieldsASpawn.size();

        int random = new Random().nextInt(len);
        return new int[]{fieldsASpawn.get(random).getX(),fieldsASpawn.get(random).getY()};
    }

    public int[] getSpawnB(){
        int len = fieldsBSpawn.size();

        int random = new Random().nextInt(len);
        return new int[]{fieldsBSpawn.get(random).getX(),fieldsBSpawn.get(random).getY()};
    }

    //отрисовка карты
    @Override
    public void render(Graphics2D g){
        for (int i = 0; i < fieldMap.length; i++)
            for (int j = 0; j < fieldMap[0].length; j++)
                fieldMap[i][j].render(g);
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
        Boolean Left = wall(getPosY(y), getPosX(x));
        Boolean Right = wall(getPosY(y2), getPosX(x2));
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
    public int getPosX(float num) {
        return (int) Math.floor(num / width);
    }
    public int getPosY(float num) {
        return (int) Math.floor(num / height);
    }

    //Для регистрации поподания в  стены
    public Boolean cheakShoot(Bullet b) {
        return (updateWall(getPosY(b.getY()), getPosX(b.getX()),b.getDamage()) || updateWall(getPosY(b.getY2()), getPosX(b.getX()),b.getDamage()) || updateWall(getPosY(b.getY()), getPosX(b.getX2()),b.getDamage()) || updateWall(getPosY(b.getY2()), getPosX(b.getX2()),b.getDamage()));
    }

    //Вспомогательная функция для регистрации поподания
    public Boolean updateWall(int Row, int Colom, int Damage) {
        //Проверяет какая стена. Если 1, то возвращает true, если 2, то уничтожат её и возвращает true. Если ни 1 и ни 2, то возваращает false
        if (Row >= numericMap.length || Colom >= numericMap[0].length)
            return true;
        if (Row < 0 || Colom < 0)
            return true;

        if (numericMap[Row][Colom] == 2) {
            Brick brick = (Brick) fieldMap[Row][Colom];
            brick.damage(Damage);
            switch (brick.getPeriod()){
                case 3:
                    break;
                case 2:
                    brick.setImage(Texture.BrickWall1);
                    break;
                case 1:
                    brick.setImage(Texture.BrickWall2);
                    break;
                default:
                    brick.setImage(Texture.BrickWall3);
                    break;
            }
            if (!((SolidField)fieldMap[Row][Colom]).getLive()) {
                numericMap[Row][Colom] = 0;
                fieldMap[Row][Colom] = new Field((int) (Colom * width), (int) (Row * height), Texture.Floor);
            }
            return true;
        }
        return (numericMap[Row][Colom] == 1);
    }

}
