package breakout;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Texts {

    public static LinkedList<Text> mySplashScreenText;

    public static void setSplashScreenText(){
        mySplashScreenText = new LinkedList<>();
        Text welcome = new Text("Welcome to Brick Breaker");
        Text pressDown = new Text("Press DOWN to begin");
        welcome.setX(Main.SIZE/2 - welcome.getBoundsInLocal().getWidth()/2);
        pressDown.setX(Main.SIZE/2 - pressDown.getBoundsInLocal().getWidth()/2);
        welcome.setX(Main.SIZE/4);
        pressDown.setX(welcome.getX() + Main.MARGIN);
        welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        pressDown.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        welcome.setFill(Color.HOTPINK);
        welcome.setFill(Color.WHITE);
        mySplashScreenText.add(welcome);
        mySplashScreenText.add(pressDown);
    }

    public static LinkedList<Text> getMySplashScreenText(){
        return mySplashScreenText;
    }

}
