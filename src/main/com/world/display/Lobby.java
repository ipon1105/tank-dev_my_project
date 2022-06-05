package main.com.world.display;

import main.com.world.game.Game;
import main.com.world.graphics.TextureAtlas;
import main.com.world.setting.Setting;
import main.com.world.utils.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

public class Lobby {
    private JFrame frame;
    private Canvas content;

    private Graphics2D graphics;
    private TextureAtlas atlas;
    private JFileChooser jchooser;
    JComboBox<String> levelList;
    private int map[][];
    public Lobby(JFrame frame, Canvas content) {
        this.content = content;
        this.frame = frame;

        atlas = new TextureAtlas(Game.ATLAS_FILENAME);
        jchooser = new JFileChooser();

    }

    public void run() {
        createMenu();

    }

    private void createMenu(){
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridBagLayout());

        //Инициализация левой панели
        {

            JButton btnSet = new JButton("Готово");
            JButton btnLook = new JButton("Обзор");
            JButton btnBack = new JButton("Назад");

            btnSet.addActionListener(new Start());
            btnLook.addActionListener(new Look());
            btnBack.addActionListener(new Back());

            //Инициализировать списки для карт
            {
                Vector<String> levelStringList = new Vector<String>();

                levelStringList.add("Не выбрано");
                levelStringList.add("1 карта");
                levelStringList.add("2 карта");
                levelStringList.add("3 карта");
                levelStringList.add("4 карта");
                levelStringList.add("5 карта");

                levelList = new JComboBox<String>(levelStringList);
                levelList.addActionListener(new Box());
            }

            GridBagConstraints c = new GridBagConstraints();

            c.anchor = GridBagConstraints.NORTH;
            c.gridx = 0; c.gridy = 0;
            leftPanel.add(levelList, c);
            c.gridx = 1;
            leftPanel.add(btnSet, c);
            c.gridx = 0; c.gridy = 1;
            leftPanel.add(btnLook, c);
            c.gridx = 1; c.gridy = 1;
            leftPanel.add(btnBack, c);

            mainPanel.add(leftPanel, BorderLayout.WEST);
        }

        mainPanel.add(content, BorderLayout.CENTER);

        mainPanel.setBackground(new Color(Game.CLEAR_COLOR));
        leftPanel.setBackground(new Color(Game.CLEAR_COLOR));

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.repaint();
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    private void draw(){
        if (map == null)
            return;

        int krow = (int) Math.ceil((double) content.getHeight() / (double) map.length) - 1;
        int kcol = (int) Math.ceil((double) content.getWidth() / (double) map[0].length) - 1;

        BufferedImage a = atlas.cut(0, 0, 84, 84);
        BufferedImage b = atlas.cut(2 * 84, 3 * 84, 84, 84);
        BufferedImage c = atlas.cut("wall.png", 0, 0, 1754, 1753);
        BufferedImage bbt = atlas.cut(1 * 84, 0 * 84, 84, 84);
        BufferedImage gbt = atlas.cut(1 * 84, 1 * 84, 84, 84);

        a =    Utils.resize(a,   kcol, krow);
        b =     Utils.resize(b,  kcol, krow);
        c =     Utils.resize(c,  kcol, krow);
        gbt = Utils.resize(gbt,  kcol, krow);
        bbt =  Utils.resize(bbt, kcol, krow);

        Display.clear();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        graphics.drawImage(a, j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 1:
                        graphics.drawImage(b, j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 2:
                        graphics.drawImage(c,    j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 3:
                        graphics.drawImage(a, j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 4:
                        graphics.drawImage(bbt,  j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 5:
                        graphics.drawImage(gbt, j * kcol, i * krow, kcol, krow, null);
                        break;
                }
            }
        }

        Display.swapBuffers();
    }

    private void render(String word) {
        Display.clear();
        graphics.drawString(word, Game.WIDTH / 2 - 30, Game.HEIGHT / 2);
        Display.swapBuffers();
    }
    class Box implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            String item = (String) box.getSelectedItem();

            map = null;
            switch (item){
                case "Не выбрано":
                    map = null;
                    break;
                case "1 карта":
                    map = Utils.levelParser(Setting.FILENAME);
                    break;
                case "2 карта":
                    map = Utils.levelParser(Setting.FILENAME);
                    break;
                case "3 карта":
                    map = Utils.levelParser(Setting.FILENAME);
                    break;
                case "4 карта":
                    map = Utils.levelParser(Setting.FILENAME);
                    break;
                case "5 карта":
                    map = Utils.levelParser(Setting.FILENAME);
                    break;
            }
            draw();
        }
    }

    class Start implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if (map == null) {
                render("Выберите карту");
                return;
            }
            Display.game = new Game(map);

            Display.createGame();
            Display.game.start();
        }
    }

    class Back implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Display.createMenu();
        }
    }

    class Look implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jchooser.setDialogTitle("Выбор файла");

            jchooser.addChoosableFileFilter(new FileFilter() {
                private String getFileExtension(File file) {
                    String fileName = file.getName();

                    if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
                        return fileName.substring(fileName.lastIndexOf(".")+1);
                    else return "";
                }

                @Override
                public boolean accept(File f) {
                    if(f != null) {
                        if (f.isDirectory())
                            return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return ".lvl";
                }
            });
            jchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "levels | .lvl, .txt", "lvl", "txt");
            jchooser.setFileFilter(filter);

            if (jchooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION )
                map = Utils.levelParser(new File(String.valueOf(jchooser.getSelectedFile())));

            draw();
        }
    }
}
