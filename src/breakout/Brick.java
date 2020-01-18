package breakout;


import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Brick {
    private static ArrayList<ImageView> myBricks = new ArrayList<>();;
    private static final String metalBrickImage = "brick3.gif";
    private static final String woodBrickImage = "brick5.gif";
    private static final String[] Brick_Images = {"brick1.gif", "brick1.gif", "brick2.gif", "brick2.gif", "brick4.gif", "brick4.gif", "brick5.gif", "brick5.gif"};
    private static final int BRICKWIDTH = 70;
    private static final int BRICKHEIGHT = 20;
    private static final int NUMROWS = 8;
    private static final int NUMCOLS = 10;


    public static void makeLevelOneBricks(){
        double[] brickXLocations = new double[NUMCOLS];
        double[] brickYLocations = new double[NUMROWS];
        for(int i = 0; i < NUMCOLS; i ++) {
           brickXLocations[i] = i * BRICKWIDTH;
           if (i < NUMROWS) {
               brickYLocations[i] = BRICKHEIGHT * i;
           }
        }
        for(int x = 0; x < brickXLocations.length; x++){
            for(int y = 0; y < brickYLocations.length; y ++){
                ImageView brick = new ImageView(Brick_Images[y]);
                brick.setX(brickXLocations[x]);
                brick.setY(brickYLocations[y]);
                myBricks.add(brick);
            }
        }
    }

    public static ArrayList<ImageView> addBricksPowerUp(){ //adds 20 new bricks
        ArrayList<ImageView> ret = new ArrayList<>();
        double[] brickXLocations = new double[NUMCOLS];
        double[] brickYLocations = new double[2];
        for(int i = 0; i < NUMCOLS; i ++){
            brickXLocations[i] = i * BRICKWIDTH;
            if(i < 2){
                brickYLocations[i] = BRICKHEIGHT * i;
            }
        }
        for(int i = 0; i < brickXLocations.length; i++){
            for(int y = 0; y < brickYLocations.length; y ++){
                ImageView brick = new ImageView(metalBrickImage);
                brick.setX(brickXLocations[i]);
                brick.setY(brickYLocations[y]);
                myBricks.add(brick);
                ret.add(brick);
            }
        }
        return ret;
    }

    public static ArrayList<ImageView> getBricks(){
        return myBricks;
    }
    public static void removeBrick(ImageView brick){
        myBricks.remove(brick);
    }


}
