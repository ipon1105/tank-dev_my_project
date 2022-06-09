package main.com.world.game.Players.viewer;

import main.com.world.display.Display;
import main.com.world.graphics.TextureAtlas;

public class ImageViewerB extends ImageViewer {

    public ImageViewerB(float scale) {
        super(scale);

        for (int i = 0; i < 32; i++)
            Display.textureBuffer.resize_index(Texture.bUP_1.ordinal() + i,
                    (int) ((int)Display.textureBuffer.get(Texture.fromOrdinal(Texture.bUP_1.ordinal() + i)).getWidth() * 0.65),
                    (int) ((int)Display.textureBuffer.get(Texture.fromOrdinal(Texture.bUP_1.ordinal() + i)).getWidth() * 0.65));

        for (int i = 0; i < 32; i++)
            Tank[(int) Math.ceil(i/8)][i%8] = Display.textureBuffer.get(Texture.fromOrdinal(Texture.bUP_1.ordinal() + i));
    }


        //Tank[0][0] = atlas.cut((1 * SIZE + 11), (1 * SIZE + 3), X2, Y2);
        //Tank[0][1] = atlas.cut((2 * SIZE + 11), (1 * SIZE + 3), X2, Y2);
        //Tank[0][2] = atlas.cut((3 * SIZE + 11), (1 * SIZE + 3), X2, Y2);
        //Tank[0][3] = atlas.cut((4 * SIZE + 11), (1 * SIZE + 3), X2, Y2);
        //Tank[0][4] = atlas.cut((5 * SIZE + 11), (1 * SIZE + 3), X2, Y2);
        //Tank[0][5] = atlas.cut((6 * SIZE + 11), (1 * SIZE + 3), X2, Y2);
        //Tank[0][6] = atlas.cut((7 * SIZE + 11), (1 * SIZE + 3), X2, Y2);
        //Tank[0][7] = atlas.cut((11), (2 * SIZE + 3), X2, Y2);
//
        //Tank[1][0] = atlas.cut("resource_right.png", 2 * SIZE + 6, 1 * SIZE + 11, Y2, X2);
        //Tank[1][1] = atlas.cut("resource_right.png", 2 * SIZE + 6, 2 * SIZE + 11, Y2, X2);
        //Tank[1][2] = atlas.cut("resource_right.png", 2 * SIZE + 6, 3 * SIZE + 11, Y2, X2);
        //Tank[1][3] = atlas.cut("resource_right.png", 2 * SIZE + 6, 4 * SIZE + 11, Y2, X2);
        //Tank[1][4] = atlas.cut("resource_right.png", 2 * SIZE + 6, 5 * SIZE + 11, Y2, X2);
        //Tank[1][5] = atlas.cut("resource_right.png", 2 * SIZE + 6, 6 * SIZE + 11, Y2, X2);
        //Tank[1][6] = atlas.cut("resource_right.png", 2 * SIZE + 6, 7 * SIZE + 11, Y2, X2);
        //Tank[1][7] = atlas.cut("resource_right.png", 1 * SIZE + 6, 0 * SIZE + 11, Y2, X2);
//
        //Tank[2][0] = atlas.cut("resource_down.png", 6 * SIZE + 11, 2 * SIZE + 6, X2,Y2);
        //Tank[2][1] = atlas.cut("resource_down.png", 5 * SIZE + 11, 2 * SIZE + 6, X2,Y2);
        //Tank[2][2] = atlas.cut("resource_down.png", 4 * SIZE + 11, 2 * SIZE + 6, X2,Y2);
        //Tank[2][3] = atlas.cut("resource_down.png", 3 * SIZE + 11, 2 * SIZE + 6, X2,Y2);
        //Tank[2][4] = atlas.cut("resource_down.png", 2 * SIZE + 11, 2 * SIZE + 6, X2,Y2);
        //Tank[2][5] = atlas.cut("resource_down.png", 1 * SIZE + 11, 2 * SIZE + 6, X2,Y2);
        //Tank[2][6] = atlas.cut("resource_down.png", 0 * SIZE + 11, 2 * SIZE + 6, X2,Y2);
        //Tank[2][7] = atlas.cut("resource_down.png", 7 * SIZE + 11, 1 * SIZE + 6, X2,Y2);
//
        //Tank[3][0] = atlas.cut("resource_left.png", 1 * SIZE + 3, 6 * SIZE + 11, Y2, X2 );
        //Tank[3][1] = atlas.cut("resource_left.png", 1 * SIZE + 3, 5 * SIZE + 11, Y2, X2);
        //Tank[3][2] = atlas.cut("resource_left.png", 1 * SIZE + 3, 4 * SIZE + 11, Y2, X2);
        //Tank[3][3] = atlas.cut("resource_left.png", 1 * SIZE + 3, 3 * SIZE + 11, Y2, X2);
        //Tank[3][4] = atlas.cut("resource_left.png", 1 * SIZE + 3, 2 * SIZE + 11, Y2, X2);
        //Tank[3][5] = atlas.cut("resource_left.png", 1 * SIZE + 3, 1 * SIZE + 11, Y2, X2);
        //Tank[3][6] = atlas.cut("resource_left.png", 1 * SIZE + 3, 0 * SIZE + 11, Y2, X2);
        //Tank[3][7] = atlas.cut("resource_left.png", 2 * SIZE + 3, 7 * SIZE + 11, Y2, X2);

}
