package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;
import java.util.Collection;
import java.util.logging.Level;


public class Main extends Application {
    public static final String TITLE = "Brick Breaker";
    public static final int SIZE = 700;
    public static final int MARGIN = 10;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int BOUNCER_SPEED = 120;
    public static final int PADDLE_SPEED = 50;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final double PaddleY = SIZE/2 - 50;

    private static Group root = new Group();

    private static final Paint BACKGROUND = Color.BLACK;
    private static int myLevelCount = 0;
    private static int PaddleHitCount = 0;
    private static int rightCount = 0;
    private static int leftCount = 0;



    public static boolean LOST = false;
    private static boolean INGAME = false;



    public static Rectangle myPaddle;




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


    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame () {
        Scene scene = new Scene(root, SIZE, SIZE/2, BACKGROUND);
        addPaddle();
        Levels.setLevelOneBrickLocations();
        Levels.startNextLevel();
        Texts.setMyForeverText();
        addCollectionToRoot(Texts.getMySplashScreenText());
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }


    private void step (double elapsedTime) {
        // update "actors" attributes
        if(!LOST){
            GamePlay.everyStep();
            warpPaddle();
        }
    }
    public void addPaddle(){
        myPaddle = new javafx.scene.shape.Rectangle(Main.SIZE/2, Main.PaddleY,PADDLE_WIDTH, PADDLE_HEIGHT);
        ImagePattern paddleImage   = new ImagePattern(new Image(this.getClass().getClassLoader().getResourceAsStream("coustomPaddle.gif")));
        myPaddle.setFill(paddleImage);
        addNodeToRoot(myPaddle);
    }
    public static void warpPaddle(){
        if(myPaddle.getX() < -(myPaddle.getWidth())){
            myPaddle.setX(SIZE - myPaddle.getWidth());
        }
        if(myPaddle.getX()> SIZE){
            myPaddle.setX(0);
        }
    }
    public static int getHitCount(){
        return PaddleHitCount;
    }
    public static void setHitCount(int count){
        PaddleHitCount = count;
    }
    public static void expandPaddle(){
        myPaddle.setWidth(myPaddle.getWidth() * 1.5);
    }
    public static void changeToOriginalPaddleSize(){
        if(!PowerUps.getPaddleExpansionOn()){
            myPaddle.setWidth(PADDLE_WIDTH);
        }
    }
    public static void updatePaddleHitCount(){
        if(PowerUps.getPaddleExpansionOn()){
            PaddleHitCount++;
        }
    }

    public void resetPaddleAndBall(){
        turnLOSTOFF();
        removeNodeFromRoot(myPaddle);
        addPaddle();
        removeCollectionFromRoot(Balls.getBouncers());
        Balls.clearBalls();
        GamePlay.turnMOVEBALLOFF();
        Balls.addBouncer();
        addCollectionToRoot(Texts.getResetBallText());
        addCollectionToRoot(Balls.getBouncers());
        removeCollectionFromRoot(Texts.getYouLoseText());
    }

    public static int updateLevelCount(){
        myLevelCount++;
        return myLevelCount;
    }
    public static void turnLOSTON(){
        LOST = true;
    }
    public static void turnLOSTOFF(){
        LOST = false;
    }

    public static void removeNodeFromRoot(Node node){
        root.getChildren().remove(node);
    }
    public static void removeCollectionFromRoot(Collection<Node> collection){
        root.getChildren().removeAll(collection);
    }



    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        if(INGAME){
            if(code == KeyCode.DIGIT1){
                Levels.changeCurrentLevel(1);
                Levels.startNextLevel();
            }
            if(code == KeyCode.DIGIT2){
                Levels.changeCurrentLevel(2);
                Levels.startNextLevel();
            }
            if(code == KeyCode.DIGIT3){
                Levels.changeCurrentLevel(3);
            }
            if(code == KeyCode.L){
                Lives.addLife();
            }
            if(code == KeyCode.SPACE){
               resetPaddleAndBall();
            }
            if (code == KeyCode.RIGHT) {
                if(GamePlay.moveBall){
                    leftCount = 0;
                    rightCount+= .5;
                    myPaddle.setX(myPaddle.getX() + (PADDLE_SPEED * (1+ rightCount)));
                }
            }
            else if (code == KeyCode.LEFT) {
                if(GamePlay.moveBall){
                    rightCount=0;
                    leftCount+=.5;
                    myPaddle.setX(myPaddle.getX() - (PADDLE_SPEED * (1 + leftCount)));
                }
            }
            else if(code == KeyCode.UP){
                if(Lives.myLives.size()!=0){
                    removeCollectionFromRoot(Texts.getResetBallText());
                    GamePlay.turnMOVEBALLOON();
                }
            }
        }
        if(!INGAME){
            if(code == KeyCode.UP) {

                if(!root.getChildren().contains(Texts.powerupscreen)){
                    root.getChildren().remove(Texts.paddlescreen);
                }
                if(!root.getChildren().contains(Texts.welcomescreen)){
                    root.getChildren().remove(Texts.powerupscreen);
                }
                    root.getChildren().remove(Texts.welcomescreen); // 1 welcomescreen 2 powerupscreen 3 paddlescreen
            }
            if(code == KeyCode.DOWN){
                if(!root.getChildren().contains(Texts.paddlescreen)){
                    removeNodeFromRoot(Texts.pointsscreen);
                }
                if(!root.getChildren().contains(Texts.pointsscreen)) INGAME = true;
            }
        }
    }

    public static void addNodeToRoot(Node node){
        root.getChildren().add(node);
    }

   public static void addCollectionToRoot(Collection <Node> collection){
        root.getChildren().addAll(collection);
    }
   public static double getPaddleX(){
        return myPaddle.getX();
   }
   public static double getPaddleY() {return myPaddle.getY();}

   public static void main (String[] args) {
        launch(args);
    }
}

