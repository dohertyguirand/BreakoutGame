package breakout;

import javax.swing.text.Element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class PowerUps {
    public static ArrayList<ImageView> myPowerUps;
    public static ArrayList<String[]> myPowerUpTypes;

    private static String paddleExpanderImage = "laserpower.gif" ;
    private static String brickBombImage = "pointspower.gif";
    private static String additionalBallsImage = "extraballpower.gif";
    private static String brickAdderImage = "sizepower.gif";
    private static String[] myPowerUpImages = {paddleExpanderImage,additionalBallsImage,paddleExpanderImage,additionalBallsImage,brickAdderImage,paddleExpanderImage,additionalBallsImage};
    public static double[] powerUpXLocations = {0, 70, 140, 210, 280,350, 420};
    public static double[] powerUpYLocations = {140, 140, 140,140,140,140,140};
    public static boolean paddleExpanderOn = false;
    private static final int MAXHITCOUNT = 15;
    public static ArrayList<ImageView> myBombs = new ArrayList<>();





    public static void makeLevelOnePowerUps(){
        myPowerUps = new ArrayList<>();
        myPowerUpTypes = new ArrayList<>();
        for(int i = 0; i < powerUpXLocations.length; i ++){
            ImageView powerUp = new ImageView(myPowerUpImages[i]);
            powerUp.setX(powerUpXLocations[i] + 10);
            powerUp.setY(powerUpYLocations[i] );
            myPowerUps.add(powerUp);
            String[] dummy = {myPowerUpImages[i], "false"};
            myPowerUpTypes.add(dummy);
        }
    }
//

    public static void managePaddleLengthPowerUp(){
        if(paddleExpanderOn){
            int count = Main.getHitCount();
            if(count == MAXHITCOUNT){
                paddleExpanderOn = false;
                Main.setHitCount(0); // resets hit count for next powerup
            }
        }
    }

    public static void removeBombFromPowerUps(ImageView bomb){
        myPowerUps.remove(bomb);
    }

    public static void addBomb(ImageView bomb){
        myBombs.add(bomb);
    }


    public static ArrayList<ImageView> getBombs() {return myBombs; }
    public static ArrayList<ImageView> getPowerUps(){
        return myPowerUps;
    }
    public static ArrayList<String[]> getPowerUpTypes(){
        return myPowerUpTypes;
    }
    public static void setPaddleExpanderBooleanOn(){
        paddleExpanderOn = true;
    }
    public static boolean getPaddleExpansionOn(){
        return paddleExpanderOn;
    }
}
