package breakout;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lives {
    public static final int TOTALLIVES = 3;
    private static String heartImage = "heart.gif";
    public static LinkedList<ImageView> myLives = new LinkedList<>();
    private static final double LIFESIZE = 12;
    public static ArrayList<Text> youLoseText = new ArrayList<>();

    public static void resetLives(){
        for(int i = 0; i < TOTALLIVES; i ++ ){
           ImageView life = new ImageView(heartImage);
           life.setX(LIFESIZE * i + Main.MARGIN);
           life.setY(Main.SIZE/2 - life.getBoundsInLocal().getHeight()- Main.MARGIN);
           myLives.add(life);
        }
    }

    public static void setyouLoseText(){
        Text youLose = new Text("You Lose!");
        Text playAgain = new Text("Press DOWN to play again");
        playAgain.setX(Main.SIZE/2 - playAgain.getBoundsInParent().getHeight()/2);
        playAgain.setY(Main.SIZE/4 + youLose.getBoundsInParent().getHeight()/2 + Main.MARGIN + 20);
        playAgain.setFill(Color.WHITE);
        playAgain.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        youLose.setX(Main.SIZE/2);
        youLose.setY(Main.SIZE/4);
        youLose.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        youLose.setFill(Color.RED);
        youLoseText.add(playAgain);
        youLoseText.add(youLose);
    }

    public static ArrayList<Text> getYouLoseText(){
        return youLoseText;
    }

    public static LinkedList<ImageView> getLives(){
        return myLives;
    }

    public static ImageView removeLife(){
        ImageView life = myLives.removeFirst();
        return life;
    }

    public static int getLifeCount(){
        return myLives.size();
    }


}
