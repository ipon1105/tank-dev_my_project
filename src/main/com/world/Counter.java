package main.com.world;

public abstract class Counter {
    public static int KILL_A;
    public static int KILL_B;

    public static void counterPrint(){
        System.out.println("Убийств А = "+KILL_B+"; \t Убийства Б = " + KILL_A+";");
    }
}
