import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public void grayImg() {
        //image dimension
        int width = 64;
        int height = 64;
        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        BufferedImage img1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //file object
        File f = null;
        // generate random scale of grey
//        Random rand = new Random();
//        int randNumber = rand.nextInt(256);

        // count number of each grey scale
        int[] countgrey17 = new int[17];
        Arrays.fill(countgrey17,0);
        int[] countgrey3 = new int[3];
        Arrays.fill(countgrey3,0);



        ArrayList<Integer> avg = new ArrayList<>();
        for(int i=0; i<256;){
            avg.add(i);
            i += 15;
        }
        int a = 255;



//        int randNumber =rand.nextInt(MAX - MIN + 1) + MIN

        for(int y = 0; y < height; y++){

            for(int x = 0; x < width/2; x++){
                int ind_ = (int) (Math.random() * 17);
                int r = avg.get(ind_);
                int g = avg.get(ind_);
                int b = avg.get(ind_);
                countgrey17[ind_]+=1;
                int r1,g1,b1;
                if(ind_>=14){
                    r1 = 255;
                    g1 = 255;
                    b1 = 255;
                }
                else{
                    r1 = 0;
                    g1 = 0;
                    b1 = 0;
                }

                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
                int p1 = (a<<24) | (r1<<16) | (g1<<8) | b1; //pixel

                img.setRGB(x, y, p);
                img1.setRGB(x, y, p1);
            }
            for(int x = width/2; x < width; x++){
//                int a = (int)(Math.random()*256); //alpha
                ArrayList<Integer> avg1 = new ArrayList<>();
                avg1.add(90);
                avg1.add(135);
                avg1.add(225);
                int ind = (int) (Math.random() * 3);
                int r = avg1.get(ind);
                int g = avg1.get(ind);
                int b = avg1.get(ind);
                countgrey3[ind]+=1;
                int r1,g1,b1;
                if(ind>=2){
                    r1 = 255;
                    g1 = 255;
                    b1 = 255;
                }
                else{
                    r1 = 0;
                    g1 = 0;
                    b1 = 0;
                }


                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
                int p1 = (a<<24) | (r1<<16) | (g1<<8) | b1; //pixel

                img.setRGB(x, y, p);
//                img1.setRGB(x, y, p1);
            }
        }
        //write image
        System.out.println("3-scale"+Arrays.toString(countgrey3));
        System.out.println("17-scale"+Arrays.toString(countgrey17));
        try{
            f = new File("./output1.png");
            ImageIO.write(img, "png", f);
//            f = new File("./outputgrey_mask.png");
//            ImageIO.write(img1, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    ///////////////////////////////////////////////////////////
    public void colorImg() {
        int width = 64;
        int height = 64;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        File f = null;
        ArrayList<Integer> avg = new ArrayList<>();
        for(int i=0; i<256; i++){
            avg.add(i);
            i += 15;
        }
        int a = 255;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width/2; x++){
                int rr = (int) (Math.random() * 16);
                int gg = (int) (Math.random() * 16);
                int bb = (int) (Math.random() * 16);
                int r = avg.get(rr);
                int g = avg.get(gg);
                int b = avg.get(bb);
                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
                img.setRGB(x, y, p);
            }
            for(int x = width/2; x < width; x++){
                ArrayList<Integer> cavg = new ArrayList<>();
                cavg.add(30);
//                cavg.add(105);
                cavg.add(225);
                int rr = (int) (Math.random() * 2);
                int gg = (int) (Math.random() * 2);
                int bb = (int) (Math.random() * 2);
                int r = cavg.get(rr);
                int g = cavg.get(gg);
                int b = cavg.get(bb);
                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
                img.setRGB(x, y, p);
            }
        }
        try{
            f = new File("./colorOutput2.png");
            ImageIO.write(img, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }
    ///////////////////////////////////////////////////////////

    public static void main(String[] args) {
        Main sol = new Main();
        sol.grayImg();
        //sol.colorImg();
    }
}
