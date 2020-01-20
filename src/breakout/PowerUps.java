package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
//this class controls all of the powerups in the game
//mypoweruptypes is a list of string arrays the first item in the array is the gif name and the second a string staing if the powerup is moving
// the mypowerups and mypoweruptypes must be parallel arrays

public class PowerUps {
    public static List<Node> myPowerUps;
    public static List<String[]> myPowerUpTypes;
    private static final int myPowerUpFallSpeed =150;

    private static String paddleExpanderImage = "laserpower.gif" ;
    private static String additionalBallsImage = "extraballpower.gif";
    private static String brickAdderImage = "sizepower.gif";
    private static String[] myPowerUpImages = {paddleExpanderImage,additionalBallsImage,additionalBallsImage,paddleExpanderImage,paddleExpanderImage,brickAdderImage,additionalBallsImage};
    public static double[] powerUpXLocations = {0, 70, 140, 210, 280,350, 420};
    public static double[] powerUpYLocations = {140, 140, 140,140,140,140,140};
    public static boolean paddleExpanderOn = false;
    private static final int MAXHITCOUNT = 15;

    public static void managePaddleLengthPowerUp(){
        if(paddleExpanderOn){
            int count = Main.getHitCount();
            if(count == MAXHITCOUNT){
                paddleExpanderOn = false;
                Main.setHitCount(0); // resets hit count for next powerup
            }
        }
    }

    public static void moveTruePowerUps(){ //this is how the powerups that are currently moving move
        for(int i = 0; i < myPowerUps.size(); i ++){
            ImageView powerUp = (ImageView) myPowerUps.get(i);
            if(myPowerUpTypes.get(i)[1].equals("true")){
                powerUp.setY(powerUp.getY() + myPowerUpFallSpeed * Main.SECOND_DELAY);
            }
        }
    }

    public static void removePowerUp(Node powerUp){ //used when the paddle catches a powerup and when the powerup reaches bottom of screen
        int index = myPowerUps.indexOf(powerUp);
        myPowerUps.remove(powerUp);
        myPowerUpTypes.remove(index);
    }

    public static List<Node> getPowerUps(){ //used at the start of each level
        if(myPowerUps == null) makeLevelOnePowerUps();
        return myPowerUps;
    }
    public static List<String[]> getPowerUpTypes(){ //used to differentiate the power ups
        if(myPowerUpTypes.size() == 0) makeLevelOnePowerUps();
        return myPowerUpTypes;
    }
    public static void setPaddleExpanderBooleanOn(){
        paddleExpanderOn = true;
    }
    public static boolean getPaddleExpansionOn(){
        return paddleExpanderOn;
    }
    public static void clearPowerUps(){ //clears powerups from main root and their lists
        if(myPowerUps != null){
            Main.removeCollectionFromRoot(myPowerUps);
            myPowerUps.clear();
            myPowerUpTypes.clear();
        }
    }
    private static void makeLevelOnePowerUps(){
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
}
