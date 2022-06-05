package main.com.world.display;

import main.com.world.Counter;
import main.com.world.IO.Input;
import main.com.world.game.Game;
import main.com.world.setting.Setting;
import main.com.world.utils.ResourceLoader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {

    public static void playSound(int type) {
        new Thread(new Runnable() {
            String url = null;

            public void run() {

                switch (type) {
                    case 1:
                        url = "boom.wav";
                        break;
                    case 2:
                        url = "shoot.wav";
                        break;
                    case 3:
                        url = "win.wav";
                        break;
                    case 4:
                        break;

                }

                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Display.class.getResourceAsStream("/resource/sound/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }


    private static JFrame window;
    private static Canvas content;
    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    public static Game game;
    private static BufferStrategy bufferStrategy;

    private static JLabel cA;
    private static JLabel cB;

    public static void main(String[] args){
        game = null;
        createDisplay();
    }

    public static void createDisplay(){

        window = new JFrame("Танки 2");
        window.setResizable(false);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(Setting.WIDTH, Setting.HEIGHT));
        window.setIconImage(ResourceLoader.loadImage("icon.png"));

        createMenu();
        window.getContentPane().setBackground(Color.DARK_GRAY);

        window.pack();
        window.setVisible(true);
    }

    public static void createMenu(){
        window.getContentPane().removeAll();

        final JPanel menuPanel = new JPanel();
        final PicPanel leftPanel = new PicPanel("img.png");

        final Font F = new Font("TimesRoman",Font.BOLD, 22);
        final JButton btnStartGame = new JButton("Играть");
        final JButton btnCreateMap = new JButton("Создать карту");
        final JButton btnOnline = new JButton("Сетевая игра");
        final JButton btnExit = new JButton("Выход");

        btnStartGame.setFont(F);
        btnCreateMap.setFont(F);
        btnOnline.setFont(F);
        btnExit.setFont(F);

        btnStartGame.addActionListener(new clickStart());
        btnCreateMap.addActionListener(new clickCreateMap());
        btnOnline.addActionListener(new clickOnline());
        btnExit.addActionListener(new clickExit());

        leftPanel.setLayout(new GridBagLayout());
        menuPanel.setLayout(new BorderLayout());

        JSeparator space1 = new JSeparator();
        JSeparator space2 = new JSeparator();
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = c.gridy = 0;
        c.weighty = 5;

        leftPanel.add(space1, c);

        c.gridy = 1;
        c.weighty = 1;

        leftPanel.add(btnStartGame, c);

        c.weighty = 1;
        c.gridy = 2;

        leftPanel.add(btnCreateMap, c);

        c.weighty = 1;
        c.gridy = 3;

        leftPanel.add(btnOnline, c);

        c.weighty = 1;
        c.gridy = 4;

        leftPanel.add(btnExit, c);

        c.gridy = 5;
        c.weighty = 5;

        leftPanel.add(space2, c);

        menuPanel.add(leftPanel, BorderLayout.CENTER);

        //leftPanel.setBackground(new Color(Game.CLEAR_COLOR));

        window.getContentPane().add(menuPanel);
        window.pack();
        window.repaint();
    }

    public static void createGame(){
        window.getContentPane().removeAll();

        final JPanel mainPanel = new JPanel();
        final JButton btnBack = new JButton("Назад");

        JPanel pCount = new JPanel(new GridBagLayout());
        cA = new JLabel("A: Убийств: ");
        cB = new JLabel("B: Убийств: ");

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        pCount.add(btnBack, c);
        c.gridx = 0; c.gridy = 1;
        pCount.add(cA, c);
        c.gridx = 0; c.gridy = 2;
        pCount.add(cB, c);

        mainPanel.setLayout(new FlowLayout());

        content = new Canvas();
        content.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));

        btnBack.addActionListener(new clickBack());

        mainPanel.add(pCount,BorderLayout.NORTH);
        mainPanel.add(content, BorderLayout.CENTER);

        window.add(mainPanel);

        contentService();

        window.pack();
        window.repaint();
        clear();
    }

    public static void contentService() {

        buffer = new BufferedImage(Game.WIDTH, Game.HEIGHT,BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D)(bufferGraphics)).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = Game.CLEAR_COLOR;

        content.createBufferStrategy(Game.NUM_BUFFERS);

        bufferStrategy = content.getBufferStrategy();
    }

    public static void clear(){
        Arrays.fill(bufferData, clearColor);
    }

    public static void swapBuffers(){
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer,0,0,null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics(){
        return (Graphics2D)bufferGraphics;
    }

    public static void addInputListener(Input inputListener){
        window.add(inputListener);
    }

    public static void setTitle(String Title){
        window.setTitle(Title);
    }

    static class clickStart implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            content = new Canvas();

            Lobby mc = new Lobby(window, content);
            mc.run();

            contentService();
            mc.setGraphics(getGraphics());
            return;

        }
    }

    static class clickOnline implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            UIManager.put("OptionPane.yesButtonText","Создать сервер");
            UIManager.put("OptionPane.noButtonText","Присоединиться");
            UIManager.put("OptionPane.canelButtonText", "Отмена");

            int res = JOptionPane.showConfirmDialog(null,"Выберети дейтсвие...");

            createGame();

            switch (res){
                case 0:
                    //Для хостинга
                    //Для присоединения
                    break;
                case 1:
                    //Для присоединения
                    break;
            }
        }
    }

    static class clickExit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    static class clickCreateMap implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            content = new Canvas();

            MapCreater mc = new MapCreater(window, content);
            mc.run();

            contentService();
            mc.setGraphics(getGraphics());
        }
    }

    static class clickBack implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.stop();
        }
    }

    public static void updateCount(){
        cA.setText("A: Убийств: " + Counter.KILL_A);
        cB.setText("B: Убийств: " + Counter.KILL_B);

        cA.update(getGraphics());
        cB.update(getGraphics());
    }

}


class PicPanel extends JPanel {

    private final BufferedImage image;
    private final int w;
    private final int h;

    public PicPanel(String fname) {

        //reads the image

        image = ResourceLoader.loadImage(fname);
        w = image.getWidth();
        h = image.getHeight();

    }

    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }
    //this will draw the image
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}