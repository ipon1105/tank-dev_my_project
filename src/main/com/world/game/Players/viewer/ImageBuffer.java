package main.com.world.game.Players.viewer;

import main.com.world.graphics.TextureAtlas;
import main.com.world.utils.Utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageBuffer implements Runnable {
    private final int SIZE = 84;
    private ArrayList<BufferedImage> TextureList;
    private TextureAtlas atlas;

    public ImageBuffer(TextureAtlas atlas){
        this.atlas = atlas;

        TextureList = new ArrayList<BufferedImage>();
    }

    @Override
    public void run() {
        load_tiles();
        load_explosion();
        load_green_tank();
        load_blue_tank();

        //Загрузка пули (74)
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 7 * SIZE, SIZE, SIZE));
    }

    private void load_tiles(){
        //0 - 5

        //Загрузка Пола, Кирпичной Стены (а также стен треснутых 1, 2 и 3) и Стальной Стены
        TextureList.add(atlas.cut(0 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("wall.png", 0, 0, 1754, 1753));
        TextureList.add(atlas.cut("wall_changes_1.png", 0, 0, 665, 665));
        TextureList.add(atlas.cut("wall_changes_2.png", 0, 0, 665, 665));
        TextureList.add(atlas.cut("wall_changes_3.png", 0, 0, 665, 665));
        TextureList.add(atlas.cut(2 * SIZE, 3 * SIZE, SIZE, SIZE));
    }

    private void load_explosion(){
        //6 - 9

        //Загрузка взрыва
        TextureList.add(atlas.cut(5 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut(1 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut(2 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut(3 * SIZE, 2 * SIZE, SIZE, SIZE));
    }

    private void load_green_tank(){
        //10 - 41

        //Загрука танка зелёного ВВЕРХ
        TextureList.add(atlas.cut("resource.png",1 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",2 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",3 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",4 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",5 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",6 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",7 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",0 * SIZE, 1 * SIZE, SIZE, SIZE));

        //Загрука танка зелёного ВПРАВО
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE, 4 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE, 5 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE, 6 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE, 7 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 0 * SIZE, SIZE, SIZE));

        //Загрука танка зелёного ВНИЗ
        TextureList.add(atlas.cut("resource_down.png", 6 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 5 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 4 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 3 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 2 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 1 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 0 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 7 * SIZE, 2 * SIZE, SIZE, SIZE));

        //Загрука танка зелёного ВЛЕВО
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE, 6 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE, 5 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE, 4 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 7 * SIZE, SIZE, SIZE));
    }

    private void load_blue_tank(){
        //42 - 73

        //Загрука танка синего ВВЕРХ
        TextureList.add(atlas.cut("resource.png",1 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",2 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",3 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",4 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",5 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",6 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",7 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource.png",0 * SIZE, 2 * SIZE, SIZE, SIZE));

        //Загрука танка синего ВПРАВО
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 4 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 5 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 6 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE, 7 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_right.png", 1 * SIZE, 0 * SIZE, SIZE, SIZE));

        //Загрука танка синего ВНИЗ
        TextureList.add(atlas.cut("resource_down.png", 6 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 5 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 4 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 3 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 2 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 1 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 0 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_down.png", 7 * SIZE, 1 * SIZE, SIZE, SIZE));

        //Загрука танка синего ВЛЕВО
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 6 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 5 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 4 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 3 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 1 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("resource_left.png", 2 * SIZE, 7 * SIZE, SIZE, SIZE));
    }

    //Функция изменяет текущий размер (WxH) всех изображений на новый (scale x scale)
    public void resize(int scale){
        for (int i = 0; i < TextureList.size(); i++)
            TextureList.set(i, Utils.resize(TextureList.get(i), scale, scale));
    }

    public BufferedImage get(Tank tank, boolean isGreen){
        if (isGreen)
            return TextureList.get(tank.ordinal() + 10);
        else
            return TextureList.get(tank.ordinal() + 42);
    }

    public BufferedImage get(Tile tile){
        return TextureList.get(tile.ordinal());
    }

    public BufferedImage get(Explosion exp){
        return TextureList.get(exp.ordinal() + 6);
    }

    public BufferedImage getBullet(){
        return TextureList.get(74);
    }
}
