package breakout;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Balls {
    public static ArrayList<ImageView> myBouncers = new ArrayList<>();
    public static ArrayList<int[]> myBouncerInfo = new ArrayList<>();
    public static final String BOUNCER_IMAGE = "ball.gif";
    public static ArrayList<Text> pressDownText = new ArrayList<>();

    public static ImageView addBouncer(){
        ImageView bouncer = new ImageView(BOUNCER_IMAGE);
        bouncer.setX(Main.getPaddleX() + Main.GROWER_WIDTH/2);
        bouncer.setY(Main.PaddleY - bouncer.getBoundsInLocal().getHeight() - Main.MARGIN);
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

    public static void setpressDownText(){
        Text pressDown = new Text("Press DOWN to Begin");
        pressDown.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        pressDown.setX(Main.SIZE/2 - pressDown.getBoundsInLocal().getWidth()/2);
        pressDown.setY(Main.SIZE/4);
        pressDown.setFill(Color.WHITE);
        pressDownText.add(pressDown);
    }
    public static ArrayList<Text> getPressDownText(){
        return pressDownText;
    }
}


