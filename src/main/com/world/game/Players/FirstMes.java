package main.com.world.game.Players;

import main.com.world.game.Players.bullet.BulletManager;
import main.com.world.game.level.FieldMap;

import java.util.ArrayList;

public class FirstMes {
    private BulletManager bulletManager;
    private ArrayList<Player> playerList;
    private FieldMap map;

    public FirstMes(ArrayList<Player> playerList, BulletManager bulletManager, FieldMap map){
        this.bulletManager = bulletManager;
        this.playerList = playerList;
        this.map = map;
    }

    public BulletManager getBulletManager() {
        return bulletManager;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public FieldMap getMap() {
        return map;
    }
}
