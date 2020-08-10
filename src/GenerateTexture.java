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

        int[] countgrey17 = new int[17];
        Arrays.fill(countgrey17, 0);
        int[] countgrey3 = new int[3];
        Arrays.fill(countgrey3, 0);


        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < 17; i++) {
            map.put(i, 255 * i / 16);
        }
        // shift the second lowest gray level to be closed to black, using 1/32
        map.put(1, 255 / 32);

        map1.put(0, 255 / 32);
        map1.put(1, 255 * 6 / 16);
        map1.put(2, 255 * 14 / 16);


        int a = 255;
        int index = 0;
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width / 2; x++) {

                double rnd = Math.random();
                if (rnd < 0.1642) {
                    index = 0;
                    if (countgrey3[0] == 380) {
                        x--;
                        continue;
                    }
                }
                else if (rnd < 0.1642 + 0.4517) {
                    index = 1;
                    if (countgrey3[1] == 1044) {
                        x--;
                        continue;
                    }
                }
                else {
                    index = 2;
                    if (countgrey3[2] == 888) {
                        x--;
                        continue;
                    }
                }

                int r = map1.get(index);
                int g = map1.get(index);
                int b = map1.get(index);
                countgrey3[index] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);

            }




            for (int x = width / 2; x < width; x++) {

                int ind_ = (int) (Math.random() * 17);
                if (countgrey17[ind_] == 68 * 68 / (17 * 2)) {
                    x--;
                    continue;
                }
                int r = map.get(ind_);
                int g = map.get(ind_);
                int b = map.get(ind_);
                countgrey17[ind_] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);

            }
        }

        System.out.println("3-scale" + Arrays.toString(countgrey3));
        System.out.println("17-scale" + Arrays.toString(countgrey17));
        try {
            f = new File("./uniform.png");
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
