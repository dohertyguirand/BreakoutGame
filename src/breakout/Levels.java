package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.ArrayList;
import java.util.List;

public class Levels {
    public static int myCurrentLevel = 1;


    private static final int BRICKWIDTH = 70;
    private static final int BRICKHEIGHT = 20;
    private static final int NUMROWS = 8;
    private static final int NUMCOLS = 10;
    private static final String perminanteBricks = "brick5.gif";
    private static final String normalBricks = "brick4.gif";
    private static final String multipleBricks = "brick1.gif";
    private static final String[] BRICKTYPES = {normalBricks,multipleBricks,perminanteBricks};
    private static final String[] LEVELONEBRICKTYPES = {perminanteBricks,multipleBricks,normalBricks,normalBricks,multipleBricks,normalBricks,normalBricks,normalBricks};
    private static final String[] LEVELTWOBRICKTYPES = {perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,normalBricks,normalBricks,normalBricks,normalBricks};
    private static final String[] LEVELTHREEBRICKTYPES = LEVELONEBRICKTYPES;
    private static List<Double> LevelOneBrickXLocations;
    private static List<Double> LevelOneBrickYLocations;
    private static List<Double> LevelTwoBrickXLocations;
    private static List<Double> LevelTwoBrickYLocations;
    private static List<Double> LevelThreeBrickXLocations;
    private static List<Double> LevelThreeBrickYLocations;
    private static List<Node> myBricks = new ArrayList<>();
    private static List<String> myBrickTypes = new ArrayList<>();
    private static List<Node> myPerminateBricks = new ArrayList<>() ;
    private static final int numTimesToHitMultipltHitBrick = 3;


    public static void makeBricks(int levelNum){  // adds bricks to myBricks and myBrickTypes
        if(getBrickXLocations(levelNum) == null || getBrickXLocations(levelNum).size()==0){
            if(levelNum == 1) setLevelOneBrickLocations();
            if(levelNum == 2) setLevelTwoBrickLocations();
            if(levelNum ==3) setLevelThreeBrickLocations();
        }
        for (int i =0; i < getBrickXLocations(levelNum).size(); i ++) {
            for(int k = 0; k< getBrickYLocations(levelNum).size(); k++){
                String bricktype = BRICKTYPES[k%3];
                if(bricktype.equals(Levels.getMultipleBricks())){
                    for(int j = 0; j < numTimesToHitMultipltHitBrick; j ++){
                        ImageView brick = new ImageView(bricktype);
                        brick.setX(getBrickXLocations(levelNum).get(i));
                        brick.setY(getBrickYLocations(levelNum).get(k));
                        myBricks.add(brick);
                        myBrickTypes.add(bricktype);
                    }
                }
                else{
                    ImageView brick = new ImageView(bricktype);
                    brick.setX(getBrickXLocations(levelNum).get(i));
                    brick.setY(getBrickYLocations(levelNum).get(k));
                    if(!bricktype.equals(perminanteBricks)){
                        myBricks.add(brick);
                        myBrickTypes.add(bricktype);
                    }
                    else{
                        myPerminateBricks.add(brick);
                    }
            }

                }
        }
    }

    public static String getNormalBricks(){
        return normalBricks;
    }
    public static String getMultipleBricks(){
        return multipleBricks;
    }


    public static void startNextLevel(){ //used whenever starting a new level
            clearBricks();
            PowerUps.clearPowerUps();
            Main.removeCollectionFromRoot(Balls.getBouncers());
            Balls.clearBalls();
            makeBricks(myCurrentLevel);
            Lives.resetLives();
            GamePlay.turnMOVEBALLOFF();
            Main.addNodeToRoot(Balls.addBouncer());
            Main.addCollectionToRoot(PowerUps.getPowerUps());
            Main.addCollectionToRoot(myBricks);
            Main.addCollectionToRoot(myPerminateBricks);
            Main.addCollectionToRoot(Texts.getResetBallText());
            Main.addCollectionToRoot(Lives.getLives());
    }

    public static List<Node> addBricksPowerUp(){ //adds 20 new bricks used with powerup
        List<Node> ret = new ArrayList<>();
        double[] brickXLocations = new double[NUMCOLS];
        double[] brickYLocations = new double[2];
        for(int i = 0; i < NUMCOLS; i ++){
            brickXLocations[i] = i * BRICKWIDTH;
            if(i < 2){
                brickYLocations[i] = BRICKHEIGHT * i + 160;
            }
        }
        for (double brickXLocation : brickXLocations) {
            for (double brickYLocation : brickYLocations) {
                ImageView brick = new ImageView(Levels.getNormalBricks());
                brick.setX(brickXLocation);
                brick.setY(brickYLocation);
                myBricks.add(brick);
                myBrickTypes.add(Levels.getNormalBricks());
                ret.add(brick);
            }
        }
        return ret;
    }

    public static List<Node> getBricks(){  // returns all of the bricks the break
        return myBricks;
    }

    public static void removeBrick(Node brick){  // removes one singular brick from main root and from lists
        int index = myBricks.indexOf(brick);
        myBricks.remove(brick);
        myBrickTypes.remove(index);
        Main.removeNodeFromRoot(brick);
    }
    public static void changeCurrentLevel(int level){  //changes level to level number used with cheat key
        myCurrentLevel = level;
    }
    public static void clearBricks(){  // clears all of the bricks in both the main root and their respective lists
        Main.removeCollectionFromRoot(myBricks);
        Main.removeCollectionFromRoot(myPerminateBricks);
        myBrickTypes.clear();
        myBricks.clear();
        myPerminateBricks.clear();
    }
    public static List<Node> getMyPerminateBricks(){  // returns a list of perminatebricks
        return myPerminateBricks;
    }
    public static int getCurrentLevel(){  // returns the current level number used to update level when using cheat key and when passed previous level
        return myCurrentLevel;
    }





    private static List<Double> getBrickXLocations(int levelNum){
        if(levelNum == 1){
            if(LevelOneBrickXLocations == null){
                setLevelOneBrickLocations();
            }
            return LevelOneBrickXLocations;
        }
        if(levelNum == 2){
            if(LevelTwoBrickXLocations == null){
                setLevelTwoBrickLocations();
            }
            return LevelTwoBrickXLocations;
        }
        if(levelNum == 3){
            if(LevelThreeBrickXLocations == null){
                setLevelThreeBrickLocations();
            }
            return LevelThreeBrickXLocations;
        }
        return new ArrayList<>();
    }

    private static List<Double> getBrickYLocations(int levelNum){
        if(levelNum == 1){
            if(LevelOneBrickYLocations == null){
                setLevelOneBrickLocations();
            }
            return LevelOneBrickYLocations;
        }
        if(levelNum == 2){
            if(LevelTwoBrickYLocations == null){
                setLevelTwoBrickLocations();
            }
            return LevelTwoBrickYLocations;
        }
        if(levelNum == 3){
            if(LevelThreeBrickYLocations == null){
                setLevelThreeBrickLocations();
            }
            return LevelThreeBrickYLocations;
        }
        return new ArrayList<>();
    }
    private static void setLevelOneBrickLocations(){
        myBricks = new ArrayList<>();
        myBrickTypes = new ArrayList<>();
        myPerminateBricks = new ArrayList<>();
        LevelOneBrickYLocations = new ArrayList();
        LevelOneBrickXLocations = new ArrayList();
        for(int i = 0; i < NUMCOLS; i ++) {
            LevelOneBrickXLocations.add((double) i * BRICKWIDTH);
            if(i < NUMROWS)
                LevelOneBrickYLocations.add((double)BRICKHEIGHT * i);
        }
    }

    private static void setLevelTwoBrickLocations() {
        myBricks = new ArrayList<>();
        myBrickTypes = new ArrayList<>();
        myPerminateBricks = new ArrayList<>();
        LevelTwoBrickYLocations = new ArrayList<>();
        LevelTwoBrickXLocations = new ArrayList<>();

        for (int i = 0; i < NUMCOLS; i++) {
            LevelTwoBrickXLocations.add((double) i * 70);
        }
        LevelTwoBrickYLocations.add(0.0);
        for (int i = 0; i < NUMCOLS - 2; i++) {
            LevelTwoBrickXLocations.add((double) 70 + 70 * i);
        }
        LevelTwoBrickYLocations.add(20.0);
        for (int i = 0; i < NUMCOLS - 4; i++) {
            LevelTwoBrickXLocations.add((double) 140 + 70 * i);
        }
        LevelTwoBrickYLocations.add(40.0);
        for(int i = 0; i < NUMCOLS - 6; i ++){
            LevelTwoBrickXLocations.add((double) 210 + 70 *i);
        }
        LevelTwoBrickYLocations.add(60.0);
    }
    private static void setLevelThreeBrickLocations(){
        myBricks = new ArrayList<>();
        myBrickTypes = new ArrayList<>();
        myPerminateBricks = new ArrayList<>();
        LevelThreeBrickYLocations = new ArrayList<>();
        LevelThreeBrickXLocations = new ArrayList<>();
        for(int k = 0; k < NUMROWS; k ++){
            for(int i = 0; i < NUMCOLS; i ++){
                LevelThreeBrickXLocations.add((double)BRICKWIDTH * i );
            }
            LevelThreeBrickYLocations.add((double)BRICKHEIGHT * k);
        }
    }
}
