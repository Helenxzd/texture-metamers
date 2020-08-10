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



public class DifferentCombination {

    public void generateGreyImg() {

        // 68*68 pixel in all, 68*68/17 for each gray level
        int width = 68;
        int height = 68;

        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //BufferedImage img1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        //file object
        File f = null;

        int[] countGreyLeft = new int[17];
        Arrays.fill(countGreyLeft, 0);
        int[] countGreyRight = new int[17];
        Arrays.fill(countGreyRight, 0);

        // uniform
//        int[] leftLimit = new int[17];
//        Arrays.fill(leftLimit, 136);
//        int[] rightLimit = new int[17];
//        Arrays.fill(rightLimit, 136);

        // 1st--order modulator
        int[] leftLimit = new int[17];
        Arrays.fill(leftLimit, 136);
        int[] rightLimit = new int[17];
        Arrays.fill(rightLimit, 136);

        int[] leftModulator = new int[]{-8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] rightModulator = new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8};

        for (int i = 0; i < 17; i++) {
            leftModulator[i] = leftModulator[i] * 2;
            rightModulator[i] = rightModulator[i] * 2;
        }


        for (int i = 0; i < 17; i++) {
            leftLimit[i] += leftModulator[i];
        }

        for (int i = 0; i < 17; i++) {
            rightLimit[i] += rightModulator[i];
        }


        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 17; i++) {
            map.put(i, 255 * i / 16);
        }

        // shift the second lowest gray level to be closed to black, using 1/32
        map.put(1, 255 / 32);


        int a = 255;
        int index = 0;
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width / 2; x++) {

                int rnd = (int) (Math.random() * 2312);
                index = checkRnd(leftLimit, rnd);
                if (countGreyLeft[index] == leftLimit[index]) {
                    x--;
                    continue;
                }

                int r = map.get(index);
                int g = map.get(index);
                int b = map.get(index);
                countGreyLeft[index] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);
            }


            for (int x = width / 2; x < width; x++) {

                int rnd = (int) (Math.random() * 2312);
                index = checkRnd(rightLimit, rnd);
                if (countGreyRight[index] == rightLimit[index]) {
                    x--;
                    continue;
                }

                int r = map.get(index);
                int g = map.get(index);
                int b = map.get(index);
                countGreyRight[index] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);
            }
        }

        System.out.println("17-scale-left" + Arrays.toString(countGreyLeft));
        System.out.println("17-scale-right" + Arrays.toString(countGreyRight));
        try {
            f = new File("./combo2_mod2.png");
            ImageIO.write(img, "png", f);
//            f = new File("./outputgrey_mask.png");
//            ImageIO.write(img1, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public int checkRnd(int[] arr, int rnd) {
        int[] preSum = new int[17];
        preSum[0] = arr[0];
        for (int i = 1; i < 17; i++) {
            preSum[i] = preSum[i-1] + arr[i];
        }

        int index = 0;
        for (index = 0; index < 17; index++) {
            if (rnd < preSum[index]) {
                return index;
            }
        }
        return 16;
    }

    public static void main(String[] args) {
        DifferentCombination sol = new DifferentCombination();
        sol.generateGreyImg();
    }

}
