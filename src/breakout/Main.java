package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;

import java.awt.*;
import java.security.Key;
import java.security.Signature;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main extends Application {
    public static final String TITLE = "Brick Breaker";
    public static final int SIZE = 700;
    public static final int MARGIN = 10;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final int BOUNCER_SPEED = 150;
    public static final int PADDLE_SPEED = 60;
    public static final Paint GROWER_COLOR = Color.AQUAMARINE;
    public static final double GROWER_RATE = 1.1;
    public static final int GROWER_WIDTH = 100;
    public static final int GROWER_HEIGHT = 12;
    private static final int myLevelCount = 0;
    private static final int myPowerUpFallSpeed =150;
    private static Group root = new Group();
    private static int PaddleHitCount = 0;
    private boolean BrickBombOn = false;
    private static int rightCount = 0;
    private static int leftCount = 0;
    private static ArrayList<Text> pressUpText  = new ArrayList<>();
    private static boolean moveBall = false;
    public static final double PaddleY = SIZE/2 - 50;
    private static int consecutiveBricksHits = 0;



    private static Rectangle myPaddle;
    private Text myTitle;
    private Text myPoints;
    private int myPointValue;
    private static int BOMB_Direction = 1;



    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        // some things needed to remember during game
        Scene myScene = setupGame();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

//    private void setSplashScreen(){
//        Text welcomeStatement = new Text("Welcome to Brick Breaker ");
//        welcomeStatement.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
//        welcomeStatement.setX(SIZE/2 - welcomeStatement.getBoundsInLocal().getWidth()/2);
//        welcomeStatement.setY(25);
//        root.getChildren().add(welcomeStatement);
//        Text pressEnter = new Text("Press UP to continue");
//
//        pressEnter.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
//        pressEnter.setX((SIZE/2 - pressEnter.getBoundsInLocal().getWidth()/2));
//        pressEnter.setY(30 + welcomeStatement.getBoundsInLocal().getHeight());
//        root.getChildren().add(pressEnter);
//        splashScreenText.add(welcomeStatement);
//        splashScreenText.add(pressEnter);
//    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame () {
        Scene scene = new Scene(root, SIZE, SIZE/2, BACKGROUND);
            if(myLevelCount == 0){
                Brick.makeLevelOneBricks();
                PowerUps.makeLevelOnePowerUps();
                Lives.resetLives();
            }
            myPaddle = new Rectangle(SIZE / 2, PaddleY, GROWER_WIDTH, GROWER_HEIGHT);
            myPaddle.setFill(GROWER_COLOR);
            // order added to the group is the order in which they are drawn
            Balls.addBouncer();
            addPerminateText();
            addChildren();
            addBallText();
            // respond to input
            scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
            scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));

        return scene;
    }

    private void addChildren(){
        root.getChildren().add(myPaddle);
        root.getChildren().add(myTitle);
        root.getChildren().addAll(PowerUps.getPowerUps());
        root.getChildren().addAll(Brick.getBricks());
        root.getChildren().addAll(Balls.getBouncers());
        root.getChildren().addAll(Lives.getLives());
    }

    private void step (double elapsedTime) {
        // update "actors" attributes
        checkCollisons();
        checkPowerUpCollisons();
        checkLives();
        moveBouncers(elapsedTime);
        PowerUps.managePaddleLengthPowerUp();
        changeToOriginalPaddleSize();
        if (myPaddle.getX() <= - myPaddle.getWidth()){
            myPaddle.setX(SIZE-myPaddle.getWidth());
        }
        if(myPaddle.getX()>= SIZE){
            myPaddle.setX(0);
        }
        if(BrickBombOn){
            ArrayList<ImageView> bombs = PowerUps.getBombs();
            for(ImageView bomb: bombs){
                bomb.setY(bomb.getY() + BOMB_Direction * myPowerUpFallSpeed * elapsedTime);
            }
        }
        ArrayList<ImageView> powerups = PowerUps.getPowerUps();
        ArrayList<String[]> powerupinfo = PowerUps.getPowerUpTypes();
        for(int i = 0; i < powerups.size(); i ++){
           if(powerupinfo.get(i)[1].equals("true")){
               powerups.get(i).setY(powerups.get(i).getY() + myPowerUpFallSpeed * elapsedTime);
           }
        }
    }

    public static void checkLives(){
        int numLives = Lives.getLifeCount();
        if(numLives == 0){
            Lives.setyouLoseText();
            root.getChildren().addAll(Lives.getYouLoseText());
        }
        if(numLives != 0 && Balls.getBouncers().size() == 0){
            ImageView bouncer = Balls.addBouncer();
            root.getChildren().add(bouncer);
            setMoveBallOff();
            addBallText();
        }
    }

    public static void addBallText(){
        int numLives = Lives.getLives().size();
        Text pressUp = new Text("Press UP to begin");
        Text numLifeCount = new Text("Number of Lives:" + " " + numLives);
        pressUpText.add(pressUp);
        pressUpText.add(numLifeCount);
        pressUp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        numLifeCount.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pressUp.setFill(Color.WHITE);
        numLifeCount.setFill(Color.LIGHTPINK);
        pressUp.setX(SIZE/2 - pressUp.getBoundsInLocal().getWidth()/2);
        pressUp.setY(SIZE/4);
        numLifeCount.setX(SIZE/2 - numLifeCount.getBoundsInLocal().getWidth()/2);
        numLifeCount.setY(SIZE/4 + MARGIN + pressUp.getBoundsInLocal().getHeight());
        root.getChildren().add(pressUp);
        root.getChildren().add(numLifeCount);
    }

    public static void removeText(ArrayList<Text> text){
        root.getChildren().removeAll(text);
    }




    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT) {
            if(moveBall){
                leftCount = 0;
                rightCount+= .5;
                myPaddle.setX(myPaddle.getX() + (PADDLE_SPEED * (1+ rightCount)));
            }
        }
        else if (code == KeyCode.LEFT) {
            if(moveBall){
                rightCount=0;
                leftCount+=.5;
                myPaddle.setX(myPaddle.getX() - (PADDLE_SPEED * (1 + leftCount)));
            }
        }
        else if(code == KeyCode.UP){
            if(Lives.myLives.size()!=0){
                removeText(pressUpText);
                moveBall = true;
            }
        }
        else if(code == KeyCode.DOWN){
            root = new Group();
            setupGame();
        }
    }

    private void handleMouseInput (double x, double y) {
        if (myPaddle.contains(x, y)) {
            myPaddle.setScaleX(myPaddle.getScaleX() * GROWER_RATE);
            myPaddle.setScaleY(myPaddle.getScaleY() * GROWER_RATE);
        }
    }

    public void checkCollisons(){
        ArrayList<ImageView> bouncers = Balls.getBouncers();
        ArrayList<int[]> bouncerinfo = Balls.getBouncerInfo();
        for(int k = 0; k < bouncers.size(); k ++){
            if (myPaddle.getBoundsInParent().intersects(bouncers.get(k).getBoundsInParent())) {
                bouncerinfo.get(k)[1] *= -1;
                myPaddle.setFill(HIGHLIGHT);
                if(PowerUps.getPaddleExpansionOn()){
                    PaddleHitCount++;
                }
                consecutiveBricksHits = 0;
            }

            else {
                myPaddle.setFill(GROWER_COLOR);
            }
            if(BrickBombOn){
                for(ImageView bomb : PowerUps.getBombs()){
                    if(myPaddle.getBoundsInLocal().intersects(bomb.getBoundsInParent())){
                        BOMB_Direction *= -1;
                    }
                }
            }
            for(ImageView brick : Brick.getBricks()){
                if(BrickBombOn){
                    ArrayList<ImageView> bombs = PowerUps.getBombs();
                    for(ImageView bomb : bombs){
                    if(bomb.getBoundsInParent().intersects(brick.getBoundsInParent())){
                        double x = brick.getX();
                        double y = brick.getY();
                        root.getChildren().remove(bomb);
                        root.getChildren().remove(brick);
                        Brick.removeBrick(brick);
                        for(ImageView b : Brick.getBricks()){
                            if(b.getX() >= x + 70 && b.getX() < x - 70 && b.getY()>= y+70 && b.getY()< x - 70) {
                                Brick.removeBrick(b);
                                root.getChildren().remove(brick);
                             }
                        }
                    }
                }
            }
                if(brick.getBoundsInParent().intersects(bouncers.get(k).getBoundsInParent())){
                bouncerinfo.get(k)[1] *= -1;
                consecutiveBricksHits++;
                ArrayList<ImageView> powerups =  PowerUps.getPowerUps();
                    for(int i = 0; i < powerups.size(); i++ ){
                        if(powerups.get(i).getBoundsInParent().intersects((brick.getBoundsInParent()))){
                        String[] powerUpInfo = PowerUps.getPowerUpTypes().get(i);
                        powerUpInfo[1] = "true";
                    }
                }
                root.getChildren().remove(brick);
                myPointValue += 100 * (1 + (consecutiveBricksHits * 0.5));
                addPoints();
                Brick.removeBrick(brick);
                break;
            }
        }
    }

}
    public void checkPowerUpCollisons(){
        ArrayList<ImageView> powerUps= PowerUps.getPowerUps();
        for(int i = 0; i < powerUps.size(); i ++){
            if(powerUps.get(i).getBoundsInParent().intersects(myPaddle.getBoundsInParent())){

                String powerUpType = PowerUps.getPowerUpTypes().get(i)[0];
                if(powerUpType.equals("laserpower.gif")){
                    myPaddle.setWidth(myPaddle.getWidth() * 1.5);
                    PowerUps.setPaddleExpanderBooleanOn();
                    powerUps.get(i).setY(900);
                }
                if(powerUpType.equals("extraballpower.gif")){
                    root.getChildren().add(Balls.addBouncer());
                    powerUps.get(i).setY(900);
                }
                if(powerUpType.equals("sizepower.gif")){
                    ArrayList<ImageView> newBricks = Brick.addBricksPowerUp();
                    for(ImageView brick : newBricks){
                        root.getChildren().add(brick);
                    }
                    powerUps.get(i).setY(900);

                }
                if(powerUpType.equals("pointspower.gif")){
                    BrickBombOn = true;
                    ImageView bomb = powerUps.get(i);
                    PowerUps.removeBombFromPowerUps(bomb);
                    PowerUps.addBomb(bomb);
                }
            }
        }
    }

   public static void moveBouncers(double elapsedTime){
        ArrayList<ImageView> bouncers = Balls.getBouncers();
        ArrayList<int[]> bouncerInfo = Balls.getBouncerInfo();
        for(int i = 0; i < bouncers.size(); i++){
            if(moveBall){
                bouncers.get(i).setX(bouncers.get(i).getX() + BOUNCER_SPEED * bouncerInfo.get(i)[0] * elapsedTime);
                bouncers.get(i).setY(bouncers.get(i).getY() + BOUNCER_SPEED * bouncerInfo.get(i)[1] * elapsedTime * 1.5);
            }
            if(!moveBall){
                bouncers.get(i).setX(myPaddle.getX() + GROWER_WIDTH/2);
                bouncers.get(i).setY(myPaddle.getY()- GROWER_HEIGHT - MARGIN);
            }
            if (bouncers.get(i).getX() >= (SIZE - 14) || bouncers.get(i).getX() < 0) {
                int[] thisBouncerInfo = bouncerInfo.get(i);
                thisBouncerInfo[0] *= -1;
            }
            if (bouncers.get(i).getY() <= 0) {
                int[] thisBouncerInfo = bouncerInfo.get(i);
                thisBouncerInfo[1] *= -1;
            }
            if(bouncers.get(i).getY() >= (SIZE/2)){
               root.getChildren().remove(bouncers.get(i));
               Balls.removeBouncer(bouncers.get(i));
               if(Balls.getBouncers().size() == 0){
                   ImageView life =  Lives.removeLife();
                   root.getChildren().remove(life);
               }
            }
        }
   }

   public static int getHitCount(){
      return PaddleHitCount;
   }

   public static void setHitCount(int count){
        PaddleHitCount = count;
   }

   public void changeToOriginalPaddleSize(){
        if(!PowerUps.getPaddleExpansionOn()){
            myPaddle.setWidth(GROWER_WIDTH);
        }
   }

   public void addPerminateText(){
       myTitle = new Text();
       myPoints = new Text();
       myTitle.setText("Brick Breaker");
       myTitle.setY(SIZE/2 -10);
       myTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
       myTitle.setX(SIZE/2 - myTitle.getBoundsInLocal().getWidth()/2);
       myTitle.setFill(Color.LIGHTCORAL);
       addPoints();
   }

   public void addPoints(){
        root.getChildren().remove(myPoints);
        myPoints.setText("Points:" + " " + myPointValue);
        myPoints.setY(SIZE/2 - 10);
        myPoints.setX(SIZE/2  + myTitle.getBoundsInLocal().getWidth()/2 + 10);
        myPoints.setFill(Color.LIGHTPINK);
        myPoints.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        root.getChildren().add(myPoints);
   }

   public static double getPaddleX(){
        return myPaddle.getX();
   }
   public static void setMoveBallON(){
        moveBall = true;
   }
   public static void setMoveBallOff(){moveBall = false;}
   public static void main (String[] args) {
        launch(args);
    }
}

