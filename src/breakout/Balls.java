package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.ArrayList;

public class Balls {
    public static List<Node> myBouncers = new ArrayList<>();
    public static List<int[]> myBouncerInfo = new ArrayList<>();
    public static final String BOUNCER_IMAGE = "ball.gif";
    public static List<Node> pressDownText = new ArrayList<>();

    public static Node addBouncer() {
        ImageView bouncer = new ImageView(BOUNCER_IMAGE);
        bouncer.setX(Main.getPaddleX() + Main.PADDLE_WIDTH / 2);
        bouncer.setY(Main.PaddleY - bouncer.getBoundsInLocal().getHeight() - 5);
        myBouncers.add(bouncer);
        addBouncerInfo();
        return bouncer;
    }

    public static void addBouncerInfo() {
        int[] info = {1, 1};
        myBouncerInfo.add(info);
    }

    public static void removeBouncer(Node bouncer) {
        myBouncers.remove(bouncer);
        myBouncerInfo.remove(bouncer);
    }

    public static List<Node> getBouncers() {
        return myBouncers;
    }

    public static List<int[]> getBouncerInfo() {
        return myBouncerInfo;
    }

    public static void clearBalls(){
        myBouncers.clear();
        myBouncerInfo.clear();
        Main.removeCollectionFromRoot(myBouncers);
    }
}




