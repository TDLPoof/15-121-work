import java.util.Dictionary;

public class PiDetector {
    public static void main(String[] args) {
        double inCircleCounter = 0;
        double pointsCounter = 0;
        for (int i = 0; i < 10; i++) {
            double x = (Math.random() * 2) - 1;
            double y = (Math.random() * 2) - 1;
            double powX = Math.pow(x, 2);
            double powY = Math.pow(y, 2);
            if (powX + powY < 1) {
                inCircleCounter++;
            }
            pointsCounter++;
        }
        System.out.println((inCircleCounter / pointsCounter) * 4);
    }
}

