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

    public static ArrayList<ImageView> mySplashScreenText = new ArrayList<>();
    public static final ImageView welcomescreen = new ImageView("welcomescreen.gif");
    public static final ImageView powerupscreen = new ImageView("powerupsscreen.gif");
    public static final ImageView paddlescreen = new ImageView("paddlescreen.gif");
    public static final ImageView pointsscreen = new ImageView("pointsscreen.gif");

    public static void setSplashScreenText(){
        mySplashScreenText.add(pointsscreen);
        mySplashScreenText.add(paddlescreen);
        mySplashScreenText.add(powerupscreen);
        mySplashScreenText.add(welcomescreen);
    }

    public static ArrayList<ImageView> getMySplashScreenText(){
        return mySplashScreenText;
    }

    public static ImageView getWelcomescreen(){
        return welcomescreen;
    }
    public static ImageView getPowerupscreen(){
        return powerupscreen;
    }
    public static ImageView getPaddlescreen(){
        return paddlescreen;
    }
    public static ImageView getPointsscreen(){
        return pointsscreen;
    }

}
