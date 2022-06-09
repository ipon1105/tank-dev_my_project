package main.com.world.game.Players.viewer;

import main.com.world.graphics.TextureAtlas;
import main.com.world.utils.Utils;

import java.awt.*;
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

        //Загрузка пули
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE, 7 * SIZE, SIZE, SIZE));

        load_green_tank();
        load_blue_tank();

    }

    private void load_tiles(){
        //Загрузка Пола, Кирпичной Стены (а также стен треснутых 1, 2 и 3) и Стальной Стены
        TextureList.add(atlas.cut(0 * SIZE, 0 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut("wall.png", 0, 0, 1754, 1753));
        TextureList.add(atlas.cut("wall_changes_1.png", 0, 0, 665, 665));
        TextureList.add(atlas.cut("wall_changes_2.png", 0, 0, 665, 665));
        TextureList.add(atlas.cut("wall_changes_3.png", 0, 0, 665, 665));
        TextureList.add(atlas.cut(2 * SIZE, 3 * SIZE, SIZE, SIZE));
    }

    private void load_explosion(){
        //Загрузка взрыва
        TextureList.add(atlas.cut(5 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut(1 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut(2 * SIZE, 2 * SIZE, SIZE, SIZE));
        TextureList.add(atlas.cut(3 * SIZE, 2 * SIZE, SIZE, SIZE));
    }

    private void load_green_tank(){
        final int X2 = SIZE - 22;
        final int Y2 = SIZE - 9;

        //Загрука танка зелёного ВВЕРХ
        TextureList.add(atlas.cut("resource.png",1 * SIZE + 11, 0 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",2 * SIZE + 11, 0 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",3 * SIZE + 11, 0 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",4 * SIZE + 11, 0 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",5 * SIZE + 11, 0 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",6 * SIZE + 11, 0 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",7 * SIZE + 11, 0 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",0 * SIZE + 11, 1 * SIZE + 3, X2, Y2));

        //Загрука танка зелёного ВПРАВО
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE  + 6, 1 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE  + 6, 2 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE  + 6, 3 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE  + 6, 4 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE  + 6, 5 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE  + 6, 6 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 3 * SIZE  + 6, 7 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE  + 6, 0 * SIZE + 11, Y2, X2));

        //Загрука танка зелёного ВНИЗ
        TextureList.add(atlas.cut("resource_down.png", 6 * SIZE + 11, 3 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 5 * SIZE + 11, 3 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 4 * SIZE + 11, 3 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 3 * SIZE + 11, 3 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 2 * SIZE + 11, 3 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 1 * SIZE + 11, 3 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 0 * SIZE + 11, 3 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 7 * SIZE + 11, 2 * SIZE + 6, X2, Y2));

        //Загрука танка зелёного ВЛЕВО
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE + 3, 6 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE + 3, 5 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE + 3, 4 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE + 3, 3 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE + 3, 2 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE + 3, 1 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 0 * SIZE + 3, 0 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 7 * SIZE + 11, Y2, X2));
    }

    private void load_blue_tank(){
        final int X2 = SIZE - 22;
        final int Y2 = SIZE - 9;

        //Загрука танка синего ВВЕРХ
        TextureList.add(atlas.cut("resource.png",1 * SIZE + 11, 1 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",2 * SIZE + 11, 1 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",3 * SIZE + 11, 1 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",4 * SIZE + 11, 1 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",5 * SIZE + 11, 1 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",6 * SIZE + 11, 1 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",7 * SIZE + 11, 1 * SIZE + 3, X2, Y2));
        TextureList.add(atlas.cut("resource.png",0 * SIZE + 11, 2 * SIZE + 3, X2, Y2));

        //Загрука танка синего ВПРАВО
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE + 6, 1 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE + 6, 2 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE + 6, 3 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE + 6, 4 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE + 6, 5 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE + 6, 6 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 2 * SIZE + 6, 7 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_right.png", 1 * SIZE + 6, 0 * SIZE + 11, Y2, X2));

        //Загрука танка синего ВНИЗ
        TextureList.add(atlas.cut("resource_down.png", 6 * SIZE + 11, 2 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 5 * SIZE + 11, 2 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 4 * SIZE + 11, 2 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 3 * SIZE + 11, 2 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 2 * SIZE + 11, 2 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 1 * SIZE + 11, 2 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 0 * SIZE + 11, 2 * SIZE + 6, X2, Y2));
        TextureList.add(atlas.cut("resource_down.png", 7 * SIZE + 11, 1 * SIZE + 6, X2, Y2));

        //Загрука танка синего ВЛЕВО
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 6 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 5 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 4 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 3 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 2 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 1 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 1 * SIZE + 3, 0 * SIZE + 11, Y2, X2));
        TextureList.add(atlas.cut("resource_left.png", 2 * SIZE + 3, 7 * SIZE + 11, Y2, X2));
    }

    //Функция изменяет текущий размер (WxH) всех изображений на новый (scale x scale)
    public void resize(int scale){
        for (int i = 0; i < TextureList.size(); i++)
            TextureList.set(i, Utils.resize(TextureList.get(i), scale, scale));
    }

    public void resize(int width, int height){
        for (int i = 0; i < TextureList.size(); i++)
            TextureList.set(i, Utils.resize(TextureList.get(i), width, height));
    }

    public void resize_index(int index, int w, int h){
        TextureList.set(index, Utils.resize(TextureList.get(index), w, h));
    }

    public BufferedImage get(Texture texture){
        if (TextureList.isEmpty())
            return null;
        return TextureList.get(texture.ordinal());
    }
}
