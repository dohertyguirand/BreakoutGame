package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.List;


public class GamePlay {
    public static boolean moveBall = false;


    public static void everyStep(){
        checkIfNoBricks();
        checkLives();
        moveBouncers(Main.SECOND_DELAY);
        PowerUps.managePaddleLengthPowerUp();
        Main.changeToOriginalPaddleSize();
        checkCollisons();
        checkPowerUpCollisons();
        PowerUps.moveTruePowerUps();
    }

    private static void checkIfNoBricks() {
        if(Levels.getBricks().size() == 0){
            Levels.changeCurrentLevel(Levels.getCurrentLevel() +1);
            if(Levels.getCurrentLevel() == 3){
            }
            Levels.startNextLevel();
        }
    }
    public static void checkCollisons(){
        List<Node> bouncers = Balls.getBouncers();
        List<int[]> bouncerinfo = Balls.getBouncerInfo();
        for(int k = 0; k < bouncers.size(); k ++){
            if (Main.myPaddle.getBoundsInParent().intersects(bouncers.get(k).getBoundsInParent())){
                ImageView bouncer = (ImageView) bouncers.get(k);
                double ballCollisonLoc = (bouncer.getX());
                double paddleCollisonLoc = Main.getPaddleX();
                if(ballCollisonLoc - paddleCollisonLoc < Main.PADDLE_WIDTH/4 || ballCollisonLoc - paddleCollisonLoc > 3 * Main.PADDLE_WIDTH/4){
                    bouncerinfo.get(k)[0] *= -1;
                }
                bouncerinfo.get(k)[1] *= -1;
                Main.updatePaddleHitCount();
                Points.resetConsecutiveBrickHits();
            }

            for(Node brick : Levels.getAllMyBricks()){

                if(brick.getBoundsInParent().intersects(bouncers.get(k).getBoundsInParent())){
                    bouncerinfo.get(k)[1] *= -1;
                    int indexOfBrick = Levels.getAllMyBricks().indexOf(brick);
                    String bricktype = Levels.getBouncerTypes().get(indexOfBrick);
                    Points.addToConsecutiveBrickHits();
                    List<Node> powerups =  PowerUps.getPowerUps();
                    for(int i = 0; i < powerups.size(); i++ ){
                        if(powerups.get(i).getBoundsInParent().intersects((brick.getBoundsInParent()))){
                            String[] powerUpInfo = PowerUps.getPowerUpTypes().get(i);
                            powerUpInfo[1] = "true";
                        }
                    }
                    if(!bricktype.equals(Levels.getPerminanteBricks())){
                        Main.removeNodeFromRoot(brick);
                        Points.updateMyPointValue();
                        Levels.removeBrick(brick);
                    }
                    break;
                }
            }
        }
    }

    public static void checkPowerUpCollisons(){
        List<Node> powerUps= PowerUps.getPowerUps();
        for(int i = 0; i < powerUps.size(); i ++){
            ImageView powerUp = (ImageView) powerUps.get(i);
            String[] powerupTypes = PowerUps.getPowerUpTypes().get(i);
            if(powerUp.getBoundsInParent().intersects(Main.myPaddle.getBoundsInParent())){
                String powerUpType = powerupTypes[0];
                if(powerUpType.equals("laserpower.gif")){
                    Main.expandPaddle();
                    PowerUps.setPaddleExpanderBooleanOn();
                    PowerUps.removePowerUp(powerUp);
                    Main.removeNodeFromRoot(powerUp);
                }
                if(powerUpType.equals("extraballpower.gif")){
                    Main.addNodeToRoot(Balls.addBouncer());
                    Main.removeNodeFromRoot(powerUp);
                    PowerUps.removePowerUp(powerUp);
                }
                if(powerUpType.equals("sizepower.gif")){
                    Main.addCollectionToRoot(Levels.addBricksPowerUp());
                    Main.removeNodeFromRoot(powerUp);
                    PowerUps.removePowerUp(powerUp);
                }
            }
            if(powerUp.getY() > Main.SIZE/2){
                PowerUps.removePowerUp(powerUp);
                Main.removeNodeFromRoot(powerUp);
            }
        }
    }
    public static void checkLives(){
        int numLives = Lives.getLifeCount();
        if(numLives == 0){
            Texts.setYouLoseText();
            Main.addCollectionToRoot(Texts.getYouLoseText());
            Main.turnLOSTON();
        }
        if(numLives != 0 && Balls.getBouncers().size() == 0){
            Node bouncer = Balls.addBouncer();
            Main.addNodeToRoot(bouncer);
            turnMOVEBALLOFF();
            Main.addCollectionToRoot(Texts.getResetBallText());
        }
    }

    public static void moveBouncers(double elapsedTime){
        List<Node> bouncers = Balls.getBouncers();
        List<int[]> bouncerInfo = Balls.getBouncerInfo();
        for(int i = 0; i < bouncers.size(); i++){
            ImageView bouncer = (ImageView) bouncers.get(i);
            if(moveBall){
                bouncer.setX(bouncer.getX() + Main.BOUNCER_SPEED * bouncerInfo.get(i)[0] * elapsedTime);
                bouncer.setY(bouncer.getY() + Main.BOUNCER_SPEED * bouncerInfo.get(i)[1] * elapsedTime * 1.5);
            }
            if(!moveBall){
                bouncer.setX(Main.getPaddleX() + Main.PADDLE_WIDTH/2);
                bouncer.setY(Main.getPaddleY()- Main.PADDLE_HEIGHT - Main.MARGIN);
            }
            if (bouncer.getX() >= (Main.SIZE - 14) || bouncer.getX() < 0) {
                int[] thisBouncerInfo = bouncerInfo.get(i);
                thisBouncerInfo[0] *= -1;
            }
            if (bouncer.getY() <= 0) {
                int[] thisBouncerInfo = bouncerInfo.get(i);
                thisBouncerInfo[1] *= -1;
            }
            if(bouncer.getY() >= (Main.SIZE/2)){
                Main.removeNodeFromRoot(bouncer);
                Balls.removeBouncer(bouncer);
                if(Balls.getBouncers().size() == 0){
                    Node life =  Lives.removeLife();
                    Main.removeNodeFromRoot(life);
                }
            }
        }
    }

    public static void turnMOVEBALLOFF(){
        moveBall = false;
    }
    public static void turnMOVEBALLOON(){
        moveBall = true;
    }


}
