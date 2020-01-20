package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.LinkedList;
import java.util.List;

//this class controls the lives
//

public class Lives {
    public static final int TOTALLIVES = 3;
    private static String heartImage = "heart.gif";
    public static List<Node> myLives;
    private static final double LIFESIZE = 12;


    public static void resetLives(){ //resets lives to starting lives
        if(myLives != null) Main.removeCollectionFromRoot(myLives);
        myLives = new LinkedList<>();
        for(int i = 0; i < TOTALLIVES; i ++ ){
           ImageView life = new ImageView(heartImage);
           life.setX(LIFESIZE * i + Main.MARGIN);
           life.setY(Main.SIZE/2 - life.getBoundsInLocal().getHeight()- Main.MARGIN);
           myLives.add(life);
        }
    }

    public static List<Node> getLives(){ //returns lives
        if(myLives == null) resetLives();
        return myLives;
    }

    public static Node removeLife(){ //removes life from first index
        Node life = myLives.remove(0);
        return life;
    }

    public static int getLifeCount(){
        return myLives.size();
    } //used to tell if the player has lost

    public static void addLife(){ //this is used with cheat key
        ImageView life = new ImageView(heartImage);
        int numLivesAlready = myLives.size();
        life.setX(LIFESIZE *numLivesAlready + Main.MARGIN);
        life.setY(Main.SIZE/2 - life.getBoundsInLocal().getHeight()- Main.MARGIN);
        myLives.add(life);
        Main.addNodeToRoot(life);
    }
}
