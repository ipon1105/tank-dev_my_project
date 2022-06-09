package main.com.world.game.level;

public abstract class Coord {
    protected int x;
    protected int y;

    public Coord(){
        this.x = 0;
        this.y = 0;
    }

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

}
