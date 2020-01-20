package breakout;

public class Points {
public static final double basePointValue = 100;
public static final double pointMultipler = 1.5;
private static int myPointValue = 0;
public static int consecutiveBricksHits = 0;

    public static void addToConsecutiveBrickHits(){
    consecutiveBricksHits++;
    }
    public static void resetConsecutiveBrickHits(){
    consecutiveBricksHits = 0;
    }
    public static void updateMyPointValue(){
        myPointValue += 100 *(1 +(consecutiveBricksHits * 0.5));
        Texts.updateMyPointsText(myPointValue);
    }
}
