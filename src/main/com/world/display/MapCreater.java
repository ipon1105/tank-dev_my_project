package main.com.world.display;

import main.com.world.game.Game;
import main.com.world.graphics.TextureAtlas;
import main.com.world.utils.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MapCreater {
    private int size, kcol, krow;
    private TextureAtlas atlas;
    private JTextArea editTextSize;
    private Graphics2D graphics;
    private JFrame frame;
    private Canvas content;

    private int xbegin, ybegin, xend, yend;

    private BufferedImage floor_1;
    private BufferedImage wall_1;
    private BufferedImage wall_2;
    private BufferedImage wall_2_1;
    private BufferedImage wall_2_2;
    private BufferedImage wall_2_3;
    private BufferedImage greenBase;
    private BufferedImage blueBase;
    private BufferedImage blueTeam;
    private BufferedImage greenTeam;

    private ArrayList<JButton> contentList;



    private JPanel mainPanel, downPanel, scrContentPanel, rDownPanel, lDownPanel;

    private JButton btnDraw, btnSave, btnBack;
    private JLabel labelSize;
    private int nowImage;

    private int[][] numericMap, predMap;

    private JFileChooser fileChooser;

    public MapCreater(JFrame frame, Canvas content) {
        this.content = content;
        this.frame = frame;

        this.content.setPreferredSize(new Dimension(500, 500));

        atlas = new TextureAtlas(Game.ATLAS_FILENAME);

        size = 0;
        numericMap = new int[1][1];
        predMap = new int[1][1];
        contentList = new ArrayList<>();
        fileChooser = new JFileChooser();
    }

    public void run() {
        createMenu();
    }

    private void createMenu() {
        frame.getContentPane().removeAll();

        mainPanel = new JPanel();

        content.addKeyListener(new keyClickCanvas());
        content.addMouseListener(new mouseClickCanvas());
        content.addMouseMotionListener(new mouseClickCanvas());
        frame.addKeyListener(new keyClickFrame());

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        componentInit();
        downPanelInit();

        mainPanel.add(downPanel, BorderLayout.SOUTH);
        mainPanel.add(content, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.repaint();
    }

    private void downPanelInit(){
        downPanel = new JPanel();
        downPanel.setLayout(new BorderLayout());
        downPanel.setBackground(Color.GRAY);

        //Инициализация левой части нижней панели
        {
            lDownPanel = new JPanel();

            lDownPanel.setLayout(new BorderLayout());

            //Инициализация скрола в левой части нижней панели
            {
                JScrollPane scrPanel = new JScrollPane();

                scrContentPanel = new JPanel();
                scrContentPanel.setLayout(new FlowLayout());
                contentList.add(setImage(0,0, new clickFloor()));
                contentList.add(setImage(new clickWall2()));
                contentList.add(setImage(168,252, new clickWall1()));
                contentList.add(setImage(84,0, new clickBlueTeam()));
                contentList.add(setImage(84,84, new clickGreenTeam()));
                //contentList.add(setImage(6 * 84, 168, new clickBlueBase()));
                //contentList.add(setImage(7 * 84, 168, new clickGreenBase()));

                for (JButton panel : contentList) {
                    scrContentPanel.add(panel);
                }
                lDownPanel.add(scrContentPanel, BorderLayout.CENTER);

                scrPanel = new JScrollPane(lDownPanel);
                frame.add(scrPanel);
            }

        }

        //Инициализация правой части нижней панели
        {
            rDownPanel = new JPanel();
            rDownPanel.setLayout(new FlowLayout());

            rDownPanel.add(btnBack);
            rDownPanel.add(labelSize);
            rDownPanel.add(editTextSize);
            rDownPanel.add(btnDraw);
            rDownPanel.add(btnSave);
        }

        downPanel.add(lDownPanel, BorderLayout.WEST);
        downPanel.add(rDownPanel, BorderLayout.CENTER);
    }

    private void componentInit(){
        btnDraw = new JButton("Рисовать");
        labelSize = new JLabel("Размер NxN");
        btnSave = new JButton("Сохранить");
        btnBack = new JButton("Назад");

        editTextSize = new JTextArea(1, 5);

        btnDraw.addActionListener(new clickDraw());
        btnSave.addActionListener(new clickSave());
        btnBack.addActionListener(new clickBack());
    }

    private JButton setImage(int x, int y, ActionListener listener) {
        JButton btn = new JButton(new ImageIcon(Utils.resize(atlas.cut(x,y,84,84), 50,50)));
        btn.addActionListener(listener);
        return btn;
    }

    private JButton setImage(ActionListener listener){
        JButton btn = new JButton(new ImageIcon(Utils.resize(atlas.cut("wall.png", 0, 0, 1754, 1753), 50, 50)));
        btn.addActionListener(listener);
        return btn;
    }

    private void resetPred() {
        numericMap = predMap;
    }

    public void render() {
        Display.clear();

        renderMap();

        Display.swapBuffers();
    }

    private void render(String word) {
        Display.clear();
        graphics.drawString(word, Game.WIDTH / 2 - 30, Game.HEIGHT / 2);
        Display.swapBuffers();
    }

    private void renderMap() {
        if (size <= 0) {
            render("Введите размеры.");
            return;
        }
        if (size >= 40) {
            render("Размеры больше 39x39 нельзя!");
            return;
        }
        if (size <= 6) {
            render("Размеры меньше 7x7 нельзя!");
            return;
        }

        renderFields();
    }

    private void renderFields() {
        for (int i = 0; i < numericMap.length; i++) {
            for (int j = 0; j < numericMap[i].length; j++) {
                switch (numericMap[i][j]) {
                    case 0:
                        graphics.drawImage(floor_1, j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 1:
                        graphics.drawImage(wall_1, j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 2:
                        graphics.drawImage(wall_2,    j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 3:
                        graphics.drawImage(floor_1, j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 4:
                        graphics.drawImage(greenTeam,  j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 5:
                        graphics.drawImage(blueTeam, j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 6:
                        graphics.drawImage(greenBase,  j * kcol, i * krow, kcol, krow, null);
                        break;
                    case 7:
                        graphics.drawImage(blueBase,  j * kcol, i * krow, kcol, krow, null);
                        break;
                    default:
                }
            }
        }
    }

    private void renderSquare(int xb, int yb, int xe, int ye) {
        Graphics2D g = graphics;

        Display.clear();

        renderMap();
        g.setColor(Color.GREEN);

        g.drawRect(xb, yb, xe - xb, ye - yb);

        Display.swapBuffers();
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    private void reNumeric() {
        int[][] newNumericMap = new int[size][size];
        if (newNumericMap.length >= numericMap.length && newNumericMap[0].length >= numericMap[0].length)
            for (int i = 0; i < numericMap.length; i++)
                for (int j = 0; j < numericMap[0].length; j++)
                    newNumericMap[i][j] = numericMap[i][j];
        else
            for (int i = 0; i < newNumericMap.length; i++)
                for (int j = 0; j < newNumericMap[0].length; j++)
                    newNumericMap[i][j] = numericMap[i][j];


        predMap = new int[size][size];
        numericMap = new int[size][size];
        numericMap = newNumericMap;
        predMap = numericMap;
    }

    private void ImageResize(int krow, int kcol) {
        BufferedImage a = atlas.cut(0, 0, 84, 84);
        BufferedImage b = atlas.cut(2 * 84, 3 * 84, 84, 84);
        BufferedImage c = atlas.cut("wall.png", 0, 0, 1754, 1753);
        BufferedImage c_1 = atlas.getImage("wall_changes_1.png");
        BufferedImage c_2 = atlas.getImage("wall_changes_2.png");
        BufferedImage c_3 = atlas.getImage("wall_changes_3.png");
        BufferedImage bb = atlas.cut(6 * 84, 2 * 84, 84, 84);
        BufferedImage gb = atlas.cut(7 * 84, 2 * 84, 84, 84);
        BufferedImage bbt = atlas.cut(1 * 84, 0 * 84, 84, 84);
        BufferedImage gbt = atlas.cut(1 * 84, 1 * 84, 84, 84);

        this.floor_1 =    Utils.resize(a, kcol, krow);
        this.wall_1 =     Utils.resize(b, kcol, krow);
        this.wall_2 =     Utils.resize(c, kcol, krow);
        this.wall_2_1 = Utils.resize(c_1, kcol, krow);
        this.wall_2_2 = Utils.resize(c_2, kcol, krow);
        this.wall_2_3 = Utils.resize(c_3, kcol, krow);
        this.greenBase = Utils.resize(gb, kcol, krow);
        this.blueBase =  Utils.resize(bb, kcol, krow);
        this.greenTeam = Utils.resize(gbt, kcol, krow);
        this.blueTeam =  Utils.resize(bbt, kcol, krow);
    }

    class clickSave implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.setDialogTitle("Сохранение файла");

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "levels | .lvl, .txt", "lvl", "txt");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION )
            {
                JOptionPane.showMessageDialog(frame,
                        "Файл '" + fileChooser.getSelectedFile() +
                                " ) сохранен");
                try {
                    Utils.levelSave(numericMap, fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }


        }
    }


    class clickBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Display.createMenu();
        }
    }

    class clickDraw implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                size = Integer.parseInt(editTextSize.getText());

                krow = (int) Math.round((double) content.getHeight() / (double) size);
                kcol = (int) Math.round((double) content.getWidth() / (double) size);

                kcol = krow;

                ImageResize(krow, kcol);
                reNumeric();
            } catch (Exception em) {
                render("Что то не так. Попробуй ещё.");
                return;
            }

            render();
        }
    }

    //Обработка всех событий
    class clickFloor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nowImage = 0;
        }
    }

    class clickWall1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nowImage = 1;
        }
    }

    class clickWall2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nowImage = 2;
        }
    }

    class clickGreenBase implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nowImage = 6;
        }
    }

    class clickBlueBase implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nowImage = 7;
        }
    }

    class clickBlueTeam implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nowImage = 5;
        }
    }

    class clickGreenTeam implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nowImage = 4;
        }
    }

    class keyClickCanvas implements KeyListener{
        Boolean C = false, Z = false;

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Display.createMenu();
            }
            if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                C = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_Z) {
                Z = true;
            }

            if (C && Z) {
                resetPred();
                render();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                C = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_Z) {
                Z = false;
            }
        }
    }


    class keyClickFrame implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Display.createMenu();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    class mouseClickCanvas implements MouseMotionListener, MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override//Нажал
        public void mousePressed(MouseEvent e) {
            xbegin = e.getX();
            ybegin = e.getY();
            xend = xbegin;
            yend = ybegin;
        }

        @Override//Отпустил
        public void mouseReleased(MouseEvent e) {

            if (krow == 0 || kcol == 0) return;
            int k;


            if (xbegin > xend) {
                k = xbegin;
                xbegin = xend;
                xend = k;
            }

            if (ybegin > yend) {
                k = ybegin;
                ybegin = yend;
                yend = k;
            }

            predMap = numericMap;
            int predItem=nowImage;

            if (e.getButton() == 3)
                nowImage=0;

            for (int i = xbegin / kcol; i <= xend / kcol; i++)
                for (int j = ybegin / krow; j <= yend / krow; j++) {
                    if (i >= size)
                        continue;
                    if (j >= size)
                        continue;
                    numericMap[j][i] = nowImage;
                }


            nowImage=predItem;

            xbegin = ybegin = xend = yend = 0;

            render();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

            xend = e.getX();
            yend = e.getY();

            if (xend < xbegin && yend < ybegin) {
                renderSquare(xend, yend, xbegin, ybegin);
            } else if (xend < xbegin) {
                renderSquare(xend, ybegin, xbegin, yend);
            } else if (yend < ybegin) {
                renderSquare(xbegin, yend, xend, ybegin);
            } else {
                renderSquare(xbegin, ybegin, xend, yend);
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
