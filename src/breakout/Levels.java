package breakout;

public class Levels {
    public static int myCurrentLevel = 0;


    public static void startNextLevel(int levelNum){
        if(levelNum ==0){
            Brick.makeLevelOneBricks();
            PowerUps.makeLevelOnePowerUps();
            Lives.resetLives();
            Balls.addBouncer();
        }
//        if(levelNum ==1){
//            Brick.makeLevelTwoBricks();
//            PowerUps.makeLevelTwoPowerUps();
//            Lives.resetLives();
//            Balls.addBouncer();
//        }
//        if(levelNum ==2){
//            Brick.makeLevelThreeBricks();
//            PowerUps.makeLevelThreePowerUps();
//            Lives.resetLives();
//            Balls.addBouncer();
//        }
    }
}
