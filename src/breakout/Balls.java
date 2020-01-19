package breakout;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Balls {
    public static ArrayList<ImageView> myBouncers = new ArrayList<>();
    public static ArrayList<int[]> myBouncerInfo = new ArrayList<>();
    public static final String BOUNCER_IMAGE = "ball.gif";

    public static ImageView addBouncer(){
        ImageView bouncer = new ImageView(BOUNCER_IMAGE);
        bouncer.setX(Main.getPaddleX() + Main.GROWER_WIDTH);
        bouncer.setY(Main.SIZE/ 2 - 50);
        myBouncers.add(bouncer);
        addBouncerInfo();
        return bouncer;
    }

    public static void addBouncerInfo(){
        int[] info = {1,1};
        myBouncerInfo.add(info);
    }

    public static void removeBouncer(ImageView bouncer){
        myBouncers.remove(bouncer);
        myBouncerInfo.remove(bouncer);
    }
    public static ArrayList<ImageView> getBouncers(){
        return myBouncers;
    }

    public static ArrayList<int[]> getBouncerInfo(){
        return myBouncerInfo;
    }
}
