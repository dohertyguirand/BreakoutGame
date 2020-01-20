package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;



import java.util.ArrayList;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;
import javafx.scene.shape.Rectangle;

public class Texts {

    public static List<Node> mySplashScreenText;
    public static List<Node> myresetBallText;
    public static Text myPointsText;
    private static Text myTitle;
    public static List<Node> youLoseText;

    public static final ImageView welcomescreen = new ImageView("welcomescreen.gif");
    public static final ImageView powerupscreen = new ImageView("powerupsscreen.gif");
    public static final ImageView paddlescreen = new ImageView("paddlescreen.gif");
    public static final ImageView pointsscreen = new ImageView("pointsscreen.gif");

    public static void setMyForeverText(){  // adds a title and points to screen sets points to 0
        myTitle = new Text();
        myTitle.setText("Brick Breaker");
        myTitle.setY(Main.SIZE/2 -10);
        myTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        myTitle.setX(Main.SIZE/2 - myTitle.getBoundsInLocal().getWidth()/2);
        myTitle.setFill(Color.LIGHTCORAL);
        Main.addNodeToRoot(myTitle);
        updateMyPointsText(0);
    }

    public static void updateMyPointsText(int myPointValue){ //used whenever a brick is broken and at initialization of game
        Main.removeNodeFromRoot(myPointsText);
        myPointsText = new Text();
        myPointsText.setText("Points:" + " " + myPointValue);
        myPointsText.setY(Main.SIZE/2 - 10);
        myPointsText.setX(Main.SIZE/2  + myTitle.getBoundsInLocal().getWidth()/2 + 10);
        myPointsText.setFill(Color.LIGHTPINK);
        myPointsText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Main.addNodeToRoot(myPointsText);
    }


    public static List<Node> getResetBallText(){ //used whenever the player loses a life / starts a new level
        if(myresetBallText == null){
            setResetBallText();
        }
        return myresetBallText;
    }

    public static List<Node> getMySplashScreenText(){ //only used at the beginning of the game
        mySplashScreenText = new ArrayList<>();
        mySplashScreenText.add(pointsscreen);
        mySplashScreenText.add(paddlescreen);
        mySplashScreenText.add(powerupscreen);
        mySplashScreenText.add(welcomescreen);
        return mySplashScreenText;
    }
    public static List<Node> getYouLoseText(){ //used when num lives == 0
        if(youLoseText == null){
            setYouLoseText();
        }
        return youLoseText;
    }
    private static void setYouLoseText(){
        Rectangle sqaure = new Rectangle(0,0,700,700);
        sqaure.setFill(Color.BLACK);
        youLoseText = new ArrayList<>();
        Text youLose = new Text("You Lose");
        Text thankYou = new Text("Thank you for Playing!");
        thankYou.setX(0);
        thankYou.setY(Main.SIZE/4 + youLose.getBoundsInLocal().getHeight() + Main.MARGIN);
        thankYou.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        thankYou.setFill(Color.WHITE);
        youLose.setX(0);
        youLose.setY(Main.SIZE/4);
        youLose.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        youLose.setFill(Color.RED);
        youLoseText.add(sqaure);
        youLoseText.add(youLose);
        youLoseText.add(thankYou);
    }

    private static void setResetBallText(){
        myresetBallText = new ArrayList<>();
        int numLives = Lives.getLives().size();
        Text pressUp = new Text("Press UP to start");
        Text numLifeCount = new Text("Number of Lives:" + " " + numLives);
        pressUp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        numLifeCount.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pressUp.setFill(Color.WHITE);
        numLifeCount.setFill(Color.LIGHTPINK);
        pressUp.setX(Main.SIZE/2 - pressUp.getBoundsInLocal().getWidth()/2);
        pressUp.setY(Main.SIZE/4);
        numLifeCount.setX(Main.SIZE/2 - numLifeCount.getBoundsInLocal().getWidth()/2);
        numLifeCount.setY(Main.SIZE/4 + Main.MARGIN + pressUp.getBoundsInLocal().getHeight());
        myresetBallText.add(pressUp);
        myresetBallText.add(numLifeCount);
    }

}
