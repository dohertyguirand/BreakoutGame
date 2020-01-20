package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
    private static final String[] LEVELONEBRICKTYPES = {normalBricks,normalBricks,normalBricks,normalBricks,normalBricks,normalBricks,normalBricks,normalBricks};
    private static final String[] LEVELTWOBRICKTYPES = {perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,perminanteBricks,normalBricks};
    private static final String[] LEVELTHREEBRICKTYPES = LEVELONEBRICKTYPES;
    private static double[] LevelOneBrickXLocations;
    private static double[] LevelOneBrickYLocations;
    private static double[] LevelTwoBrickXLocations;
    private static double[] LevelTwoBrickYLocations;
    private static double[] LevelThreeBrickXLocations;
    private static double[] LevelThreeBrickYLocations;
    private static List<Node> myBricks;
    private static List<String> myBrickTypes;
    private static List<Node> myPerminantBricks;
    private static List<Node> allMyBricks;
    private static final int numTimesToHitMultipltHitBrick = 3;



    public static void setLevelOneBrickLocations(){
        myBricks = new ArrayList<>();
        allMyBricks = new ArrayList<>();
        myBrickTypes = new ArrayList<>();
        myPerminantBricks = new ArrayList<>();
        LevelOneBrickYLocations = new double[NUMROWS];
        LevelOneBrickXLocations = new double[NUMCOLS];
        for(int i = 0; i < NUMCOLS; i ++) {
            LevelOneBrickXLocations[i] = i * BRICKWIDTH;
            if (i < NUMROWS) {
                LevelOneBrickYLocations[i] = BRICKHEIGHT * i;
            }
        }
    }

    public static void setLevelTwoBrickLocations(){
        myBricks = new ArrayList<>();
        allMyBricks= new ArrayList<>();
        myBrickTypes = new ArrayList<>();
        myPerminantBricks = new ArrayList<>();
        LevelTwoBrickYLocations = new double[NUMROWS];
        LevelTwoBrickXLocations = new double[NUMCOLS];
        for(int i = 0; i < NUMCOLS; i ++) {
            LevelTwoBrickXLocations[i] = i * BRICKWIDTH;
            if (i < NUMROWS) {
                LevelTwoBrickYLocations[i] = BRICKHEIGHT * i;
            }
        }
    }

    public static void setLevelThreeBrickLocations(){
        myBricks = new ArrayList<>();
        myBrickTypes = new ArrayList<>();
        allMyBricks = new ArrayList<>();
        myPerminantBricks = new ArrayList<>();
        LevelThreeBrickYLocations = new double[NUMROWS];
        LevelThreeBrickXLocations = new double[NUMCOLS];
        for(int i = 0; i < NUMCOLS; i ++) {
            LevelThreeBrickXLocations[i] = i * BRICKWIDTH;
            if (i < NUMROWS) {
                LevelThreeBrickYLocations[i] = BRICKHEIGHT * i;
            }
        }
    }

    public static void makeBricks(int levelNum){
        for (double brickXLocation : getBrickXLocations(levelNum)) {
            for (int y = 0; y < getBrickYLocations(levelNum).length; y++) {
                String bricktype = Levels.getLevelBrickTypes(myCurrentLevel)[y];
                if(bricktype.equals(Levels.getMultipleBricks())){
                    for(int i = 0; i < numTimesToHitMultipltHitBrick; i ++){
                        ImageView brick = new ImageView(bricktype);
                        brick.setX(brickXLocation);
                        brick.setY(getBrickYLocations(levelNum)[y]);
                        myBricks.add(brick);
                        myBrickTypes.add(bricktype);
                        allMyBricks.add(brick);
                    }
                }
                else{
                    ImageView brick = new ImageView(bricktype);
                    brick.setX(brickXLocation);
                    brick.setY(getBrickYLocations(levelNum)[y]);
                    if(!bricktype.equals(perminanteBricks)){
                        myBricks.add(brick);
                        allMyBricks.add(brick);
                        myBrickTypes.add(bricktype);
                    }
                    if(bricktype.equals(perminanteBricks)){
                        myPerminantBricks.add(brick);
                        allMyBricks.add(brick);
                        myBrickTypes.add(bricktype);
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
    public static String getPerminanteBricks(){
        return perminanteBricks;
    }

    public static String[] getLevelBrickTypes(int levelNum){
        if(levelNum == 1) return LEVELONEBRICKTYPES;
        if(levelNum == 2) return LEVELTWOBRICKTYPES;
        if(levelNum == 3) return LEVELTHREEBRICKTYPES;
        String[] failed = {"",""};
        return failed;
    }

    public static double[] getBrickXLocations(int levelNum){
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
        return new double[1];
        }

    public static double[] getBrickYLocations(int levelNum){
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
            return new double[1];
    }

    public static void startNextLevel(){
            clearBricks();
            PowerUps.clearPowerUps();
            Main.removeCollectionFromRoot(myPerminantBricks);
            Main.removeCollectionFromRoot(Balls.getBouncers());
            Balls.clearBalls();
            makeBricks(myCurrentLevel);
            PowerUps.makeLevelOnePowerUps();
            Lives.resetLives();
            GamePlay.turnMOVEBALLOFF();
            Main.addNodeToRoot(Balls.addBouncer());
            Main.addCollectionToRoot(PowerUps.getPowerUps());
            Main.addCollectionToRoot(myBricks);
            Main.addCollectionToRoot(myPerminantBricks);
            Main.addCollectionToRoot(Texts.getResetBallText());
            Main.addCollectionToRoot(Lives.getLives());
    }

    public static List<Node> addBricksPowerUp(){ //adds 20 new bricks
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
                allMyBricks.add(brick);
                ret.add(brick);
            }
        }
        return ret;
    }

    public static List<Node> getBricks(){
        return myBricks;
    }
    public static List<String>  getBouncerTypes(){
        return myBrickTypes;
    }
    public static void removeBrick(Node brick){
        int index = myBricks.indexOf(brick);
        myBricks.remove(brick);
        myBrickTypes.remove(index);
        allMyBricks.remove(brick);
    }
    public static void changeCurrentLevel(int level){
        myCurrentLevel = level;
    }
    public static void clearBricks(){
        Main.removeCollectionFromRoot(myBricks);
        myBrickTypes.clear();
        myBricks.clear();
        allMyBricks.clear();
    }
    public static int getCurrentLevel(){
        return myCurrentLevel;
    }
    public static List<Node> getAllMyBricks(){
        return allMyBricks;
    }
}
