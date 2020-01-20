package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.ArrayList;
// this class controls all of the balls
// balls are added at the beginning of each level and when the add ball powerup is caught
// mybouncers and mybouncerinfo must be parallel arrays
public class Balls {
    public static List<Node> myBouncers = new ArrayList<>();
    public static List<int[]> myBouncerInfo = new ArrayList<>();
    public static final String BOUNCER_IMAGE = "ball.gif";
    public static List<Node> pressDownText = new ArrayList<>();

    public static Node addBouncer() { //adds a new bouncer to the list mybouncer does not add to main root so in other classes must be used as Main.addNodeToRoot(Balls.addBouncer())
        ImageView bouncer = new ImageView(BOUNCER_IMAGE);
        bouncer.setX(Main.getPaddleX() + Main.PADDLE_WIDTH / 2);
        bouncer.setY(Main.PaddleY - bouncer.getBoundsInLocal().getHeight() - 5);
        myBouncers.add(bouncer);
        addBouncerInfo();
        return bouncer;
    }

    public static void addBouncerInfo() {
        int[] info = {-1, 1};
        myBouncerInfo.add(info);
    }

    public static void removeBouncer(Node bouncer) { //removes from both lists to maintiain parallelism and removes from root
        myBouncers.remove(bouncer);
        myBouncerInfo.remove(bouncer);
        Main.removeNodeFromRoot(bouncer);
    }

    public static List<Node> getBouncers() {  // returns mybouncers
        return myBouncers;
    }

    public static List<int[]> getBouncerInfo() { // returns bouncerinfo
        return myBouncerInfo;
    }

    public static void clearBalls(){ //removes from main root and from both lists
        myBouncers.clear();
        myBouncerInfo.clear();
        Main.removeCollectionFromRoot(myBouncers);
    }
}




