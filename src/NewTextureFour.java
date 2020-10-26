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

public class NewTextureFour {
    public void generateGreyImg() {

//        double[] arr5 = new double[]{0.104, 0.224, 0.344, 0.224, 0.104};
//        int[] count5 = new int[5];
//        int count = 0;
//        for (int i = 0; i < 4; i++) {
//            count5[i] = (int) (68*68*arr5[i]);
//            count += count5[i];
//        }
//        count5[4] += (68*68 - count);

        double[] arr3 = new double[]{0.3333, 0.3334, 0.3333};
        int[] count3 = new int[3];
        int count = 0;
        for (int i = 0; i < 2; i++) {
            count3[i] = (int) (68*68*arr3[i]);
            count += count3[i];
        }
        count3[2] += (68*68 - count);




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
        int[] countgreyRight = new int[17];
        Arrays.fill(countgreyRight, 0);


        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < 17; i++) {
            map1.put(i, 255 * i / 16);
        }
        // shift the second lowest gray level to be closed to black, using 1/32
        map1.put(1, 255 / 32);


        map.put(0, 0);
        //map.put(1, 255 * 4 / 16);
        map.put(1, 255 * 8 / 16);
        //map.put(3, 255 * 12 / 16);
        map.put(2, 255 * 16 / 16);
//        map1.put(0, 255 * 2 / 16);
//        map1.put(1, 255 * 8 / 16);
//        map1.put(2, 255 * 14 / 16);



        int a = 255;
        int index = 0;
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                double rnd = Math.random();
                if (rnd < arr3[0]) {
                    index = 0;
                    if (countgreyLeft[0] == count3[0]) {
                        x--;
                        continue;
                    }
                }
                else if (rnd < arr3[0]+arr3[1]) {
                    index = 1;
                    if (countgreyLeft[1] == count3[1]) {
                        x--;
                        continue;
                    }
                }
//                else if (rnd < arr5[0]+arr5[1]+arr5[2]) {
//                    index = 2;
//                    if (countgreyLeft[2] == count5[2]) {
//                        x--;
//                        continue;
//                    }
//                }
//                else if (rnd < arr5[0]+arr5[1]+arr5[2]+arr5[3]) {
//                    index = 3;
//                    if (countgreyLeft[3] == count5[3]) {
//                        x--;
//                        continue;
//                    }
//                }
                else {
                    index = 2;
                    if (countgreyLeft[2] == count3[2]) {
                        x--;
                        continue;
                    }
                }

                int r = map.get(index);
                int g = map.get(index);
                int b = map.get(index);
                countgreyLeft[index] += 1;

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
        //System.out.println("right" + Arrays.toString(countgreyRight));
        try {
            f = new File("./1013/1013_uni3.png");
            ImageIO.write(img, "png", f);
//            f = new File("./outputgrey_mask.png");
//            ImageIO.write(img1, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        NewTextureFour sol = new NewTextureFour();
        sol.generateGreyImg();
    }
}
