package main.com.world.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static BufferedImage resize(BufferedImage image, int width,int height) {

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image,0,0,width,height,null);

        return newImage;
    }

    public static int[][] levelParser(String filePath){
        if (filePath == null)
            return null;

        Integer[][] result = null;
        int[][] res;

        try {
            InputStream in = Utils.class.getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            List<Integer[]> lvlLines = new ArrayList<Integer[]>();
            while ((line = reader.readLine())!=null){
                lvlLines.add(str2int_arrays(line.split(" ")));
            }
            result = new Integer[lvlLines.size()][lvlLines.get(0).length];
            for (int i = 0; i<lvlLines.size();i++){
                result[i] = lvlLines.get(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        res = new int[result.length][result[0].length];

        for (int i =0; i< res.length; i++){
            for (int j =0; j< res[0].length; j++){
                res[i][j] = result[i][j];
            }
        }

        return res;
    }

    public static int[][] levelParser(File fullPath){
        if (fullPath == null)
            return null;

        Integer[][] result = null;
        int[][] res;

        try {
            InputStream in = new FileInputStream(fullPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            List<Integer[]> lvlLines = new ArrayList<Integer[]>();
            while ((line = reader.readLine())!=null){
                lvlLines.add(str2int_arrays(line.split(" ")));
            }
            result = new Integer[lvlLines.size()][lvlLines.get(0).length];
            for (int i = 0; i<lvlLines.size();i++){
                result[i] = lvlLines.get(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        res = new int[result.length][result[0].length];

        for (int i =0; i< res.length; i++)
            for (int j =0; j< res[0].length; j++)
                res[i][j] = result[i][j];



        return res;
    }

    public static final Integer[] str2int_arrays(String[] sArr){
        Integer[] result = new Integer[sArr.length];
        for (int i = 0; i < sArr.length; i++){
            result[i] = Integer.parseInt(sArr[i]);
        }
        return result;
    }

    public static void levelSave(int map[][], File filename) throws IOException {
        if (!isExtensionLvlOrTxt(filename.getAbsolutePath()))
            filename = new File(filename.getAbsolutePath() + ".lvl");

        FileWriter out = new FileWriter(filename);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++)
                out.write(String.valueOf(map[i][j]) + " ");
            out.write("\n");
        }
        out.close();
    }

    private static Boolean isExtensionLvlOrTxt(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null!");
        }

        String extension = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0)
            extension = fileName.substring(index + 1);



        return extension.equals("lvl") || extension.equals("txt");

    }
}
