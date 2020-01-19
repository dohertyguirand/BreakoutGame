package breakout;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lives {
    public static final int TOTALLIVES = 3;
    private static String heartImage = "heart.gif";
    public static LinkedList<ImageView> myLives = new LinkedList<>();
    private static final double LIFESIZE = 12;

    public static void resetLives(){
        for(int i = 0; i < TOTALLIVES; i ++ ){
           ImageView life = new ImageView(heartImage);
           life.setX(LIFESIZE * i + Main.MARGIN);
           life.setY(Main.SIZE/2 - life.getBoundsInLocal().getHeight()- Main.MARGIN);
           myLives.add(life);
        }
    }

    public static LinkedList<ImageView> getLives(){
        return myLives;
    }

    public static ImageView removeLife(){
        ImageView life = myLives.removeFirst();
        return life;
    }

    public static int getLifeCount(){
        return myLives.size();
    }


}
