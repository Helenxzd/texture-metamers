import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GreaterShuffle {
    public void generateTexture() {

        BufferedImage img = new BufferedImage(68, 68, BufferedImage.TYPE_INT_ARGB);
        File f = null;

        List<Integer> li = new ArrayList<Integer>();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 17; i++) {
            map.put(i, 255 * i / 16);
        }
        // shift the second lowest gray level to be closed to black, using 1/32
        map.put(1, 255 / 32);


        int[] grayLevel = new int[]{20, 98, 157, 235};
//        double[] arr4 = new double[]{0.3, 0.2, 0.2, 0.3};
        int[] count4 = new int[]{1387, 925, 925, 1387};

        System.out.println(Arrays.toString(count4));



        int index = 0;
        int count = 0;
        for (int i = 0; i < 68*68; i++) {
            if (count <= count4[index]) {
                li.add(grayLevel[index]);
                count++;
            }
            else {
                count = 0;
                index++;
                i--;
            }
        }

        Collections.shuffle(li);
        System.out.println(li.size());


        int id = 0;
        int a = 255;
        for (int y = 0; y < 68; y++) {
            for (int x = 0; x < 68; x++) {

                int r = li.get(id);
                int g = li.get(id);
                int b = li.get(id);
//                countgreyLeft[index] += 1;

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);
                id++;
            }
        }

        try {
            f = new File("./1026/1_6.png");
            ImageIO.write(img, "png", f);
//            f = new File("./outputgrey_mask.png");
//            ImageIO.write(img1, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }
    public static void main(String[] args) {
        GreaterShuffle sol = new GreaterShuffle();
        sol.generateTexture();
    }
}
