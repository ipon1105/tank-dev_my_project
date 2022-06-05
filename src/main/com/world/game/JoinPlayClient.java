package main.com.world.game;

import main.com.world.game.Players.FirstMes;
import main.com.world.game.Players.Player;
import main.com.world.game.Players.bullet.BulletManager;
import main.com.world.game.level.FieldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class JoinPlayClient {
    public BulletManager bulletManager;
    public ArrayList<Player> playerList;
    public FieldMap map;

    public JoinPlayClient( FirstMes mes){
        this.bulletManager = mes.getBulletManager();
        this.playerList = mes.getPlayerList();
        this.map = mes.getMap();
    }

    public void render(Graphics2D g){
        map.render(g);

        for (Player P: playerList){
            P.render(g);
        }

        bulletManager.render(g);
    }

}
