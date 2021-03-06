import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.lang.*;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GenerateTexture {

    public void generateGreyImg() {

        // 68*68 pixel in all, 68*68/17 for each gray level
        int width = 68;
        int height = 68;
        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        BufferedImage img1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //file object
        File f = null;

        int[] countgreyLeft = new int[3];
        Arrays.fill(countgreyLeft, 0);
        int[] countgreyRight = new int[3];
        Arrays.fill(countgreyRight, 0);


        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
//        for (int i = 0; i < 17; i++) {
//            map.put(i, 255 * i / 16);
//        }
        // shift the second lowest gray level to be closed to black, using 1/32
//        map.put(1, 255 / 32);

        map.put(0, 255 * 5 / 16);
        map.put(1, 255 * 11 / 16);

        map1.put(0, 255 / 32);
        map1.put(1, 255 * 6 / 16);
        map1.put(2, 255 * 14 / 16);
//        map1.put(0, 255 * 2 / 16);
//        map1.put(1, 255 * 8 / 16);
//        map1.put(2, 255 * 14 / 16);



        int a = 255;
        int index = 0;
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                double rnd = Math.random();
                if (rnd < 0.1642) {
                    index = 0;
                    if (countgreyLeft[0] == 759) {
                        x--;
                        continue;
                    }
                }
                else if (rnd < 0.1642 + 0.4517) {
                    index = 1;
                    if (countgreyLeft[1] == 2089) {
                        x--;
                        continue;
                    }
                }
                else {
                    index = 2;
                    if (countgreyLeft[2] == 1776) {
                        x--;
                        continue;
                    }
                }

                int r = map1.get(index);
                int g = map1.get(index);
                int b = map1.get(index);
                countgreyLeft[index] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);

            }

//            for (int x = width/2; x < width; x++) {
//
//                double rnd_ = Math.random();
//                if (rnd_ < 0.125) {
//                    index = 0;
//                    if (countgreyRight[0] == 68*68*0.125/2) {
//                        x--;
//                        continue;
//                    }
//                }
//                else if (rnd_ < 0.125 + 0.75) {
//                    index = 1;
//                    if (countgreyRight[1] == 68*68*0.75/2) {
//                        x--;
//                        continue;
//                    }
//                }
//                else {
//                    index = 2;
//                    if (countgreyRight[2] == 68*68*0.125/2) {
//                        x--;
//                        continue;
//                    }
//                }
//                int r = map1.get(index);
//                int g = map1.get(index);
//                int b = map1.get(index);
//                countgreyRight[index] += 1;
//
//
//                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel
//
//                img.setRGB(x, y, p);
//
//            }




//            for (int y = 0; y < height/2; y++) {
//
//                int ind_ = (int) (Math.random() * 17);
//                if (countgrey17[ind_] == 68 * 68 / (17*2)) {
//                    y--;
//                    continue;
//                }
//                int r = map.get(ind_);
//                int g = map.get(ind_);
//                int b = map.get(ind_);
//                countgrey17[ind_] += 1;
//
//                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel
//
//                img.setRGB(x, y, p);
//
//            }
        }

        System.out.println("left" + Arrays.toString(countgreyLeft));
        //System.out.println("right" + Arrays.toString(countgreyRight));
        try {
            f = new File("./1013_3.png");
            ImageIO.write(img, "png", f);
//            f = new File("./outputgrey_mask.png");
//            ImageIO.write(img1, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        GenerateTexture sol = new GenerateTexture();
        sol.generateGreyImg();
    }

}
