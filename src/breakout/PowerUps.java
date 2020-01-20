package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;


public class PowerUps {
    public static List<Node> myPowerUps;
    public static List<String[]> myPowerUpTypes;
    private static final int myPowerUpFallSpeed =150;

    private static String paddleExpanderImage = "laserpower.gif" ;
    private static String additionalBallsImage = "extraballpower.gif";
    private static String brickAdderImage = "sizepower.gif";
    private static String[] myPowerUpImages = {additionalBallsImage,additionalBallsImage,additionalBallsImage,additionalBallsImage,additionalBallsImage,additionalBallsImage,additionalBallsImage};
    public static double[] powerUpXLocations = {0, 70, 140, 210, 280,350, 420};
    public static double[] powerUpYLocations = {140, 140, 140,140,140,140,140};
    public static boolean paddleExpanderOn = false;
    private static final int MAXHITCOUNT = 15;

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

    public static void moveTruePowerUps(){
        for(int i = 0; i < myPowerUps.size(); i ++){
            ImageView powerUp = (ImageView) myPowerUps.get(i);
            if(myPowerUpTypes.get(i)[1].equals("true")){
                powerUp.setY(powerUp.getY() + myPowerUpFallSpeed * Main.SECOND_DELAY);
            }
        }
    }

    public static void removePowerUp(Node powerUp){
        int index = myPowerUps.indexOf(powerUp);
        myPowerUps.remove(powerUp);
        myPowerUpTypes.remove(index);
    }

    public static List<Node> getPowerUps(){
        return myPowerUps;
    }
    public static List<String[]> getPowerUpTypes(){
        return myPowerUpTypes;
    }
    public static void setPaddleExpanderBooleanOn(){
        paddleExpanderOn = true;
    }
    public static boolean getPaddleExpansionOn(){
        return paddleExpanderOn;
    }
    public static void clearPowerUps(){
        if(myPowerUps != null){
            Main.removeCollectionFromRoot(myPowerUps);
            myPowerUps.clear();
            myPowerUpTypes.clear();
        }
    }
}
