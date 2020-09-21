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

public class NewTexture {

    public void generateGreyImg() {

        // 68*68 pixel in all, 68*68/17 for each gray level
        int width = 68;
        int height = 68;
        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        BufferedImage img1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //file object
        File f = null;

        int[] countgreyLeft = new int[5];
        Arrays.fill(countgreyLeft, 0);
        int[] countgreyRight = new int[17];
        Arrays.fill(countgreyRight, 0);


        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < 17; i++) {
            map1.put(i, 255 * i / 16);
        }
        // shift the second lowest gray level to be closed to black, using 1/32
        map1.put(1, 255 / 32);


        map.put(0, 255 / 32);
        map.put(1, 255 * 6 / 16);
        map.put(2, 255 * 10 / 16);
        map.put(3, 255 * 14 / 16);
        map.put(4, 255 * 16 / 16);
//        map1.put(0, 255 * 2 / 16);
//        map1.put(1, 255 * 8 / 16);
//        map1.put(2, 255 * 14 / 16);



        int a = 255;
        int index = 0;
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width/2; x++) {

                double rnd = Math.random();
                if (rnd < 0.19) {
                    index = 0;
                    if (countgreyLeft[0] == 439) {
                        x--;
                        continue;
                    }
                }
                else if (rnd < 0.57) {
                    index = 1;
                    if (countgreyLeft[1] == 879) {
                        x--;
                        continue;
                    }
                }
                else if (rnd < 0.69) {
                    index = 2;
                    if (countgreyLeft[2] == 277) {
                        x--;
                        continue;
                    }
                }
                else if (rnd < 0.98) {
                    index = 3;
                    if (countgreyLeft[3] == 671) {
                        x--;
                        continue;
                    }
                }
                else {
                    index = 4;
                    if (countgreyLeft[4] == 46) {
                        x--;
                        continue;
                    }
                }
//                else if (rnd < 0.1642 + 0.4517) {
//                    index = 1;
//                    if (countgrey3[1] == 1044) {
//                        y--;
//                        continue;
//                    }
//                }

                int r = map.get(index);
                int g = map.get(index);
                int b = map.get(index);
                countgreyLeft[index] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);

            }

            for (int x = width/2; x < width; x++) {
                int ind_ = (int) (Math.random() * 17);
                if (countgreyRight[ind_] == 68 * 68 / (17*2)) {
                    x--;
                    continue;
                }
                int r = map1.get(ind_);
                int g = map1.get(ind_);
                int b = map1.get(ind_);
                countgreyRight[ind_] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);

            }




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
        System.out.println("right" + Arrays.toString(countgreyRight));
        try {
            f = new File("./new914_5.png");
            ImageIO.write(img, "png", f);
//            f = new File("./outputgrey_mask.png");
//            ImageIO.write(img1, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        NewTexture sol = new NewTexture();
        sol.generateGreyImg();
    }

}
