
package arkanoid;

public class Utils {

    private static final java.util.Random RANDOM = new java.util.Random();

    // losowanie liczby float
    
    public static int random(int max) {
        return (int) (RANDOM.nextDouble() * max);
    }

    // zwracanie znaku
    
    public static int sign(double n) {
        if (n == 0) {
            return 0;
        }
        if (n > 0) {
            return 1;
        } else {
            return -1;
        }
    }

}
