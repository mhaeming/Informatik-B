import java.lang.Math;

/**
 * Arena
 */
public class Arena {

    /**
     * Get the area in which a tribute currently is positioned
     *
     * @param x the tributes x-position in the coordinate-system
     * @param y the tributes y-position in the coordinate system
     * @return the area in which the tribute is positioned, an Integer between 1 and 12
     */
    public int getArea(double x, double y) {
        int area;

        // First, we need to check if the tribute is not positioned outside of the arena. If it is, we return -1
        // For that, we take the distance to the position 0|0
        double distanceToZero = Math.sqrt(x*x + y*y);
        if(distanceToZero > 1.5){
            return -1;
        }
        // Second, we separate between the four possible quadrants of the coordinate system.
        // For each quadrant, we separate between the three areas that are in this quadrant.
        // We need to calculate the value of the angle alpha,
        // which is the angle in the triangle between the line to x, the line to y and the line to x|y
        double opposite = Math.abs(x);
        double hypotenuse = distanceToZero;
        double alpha = 0;
        if(hypotenuse != 0){
            alpha = Math.toDegrees(Math.asin(opposite/hypotenuse));
        }
        if (x >= 0 && x <= 1.5 && y >= 0 && y <= 1.5) { // First case, first quadrant (=right, upper part of coordinate system)

            // Now we separate the cases, 0-30 degrees, 31-60 degrees or 61-90 degrees
            if(alpha <= 30) {
                return 1;
            } else if (alpha <= 60) {
                return 2;
            } else {
                return 3;
            }

        } else if (x < 0 && x >= -1.5 && y >= 0 && y <= 1.5) { // Second case, second quadrant (left, upper)

            // Now we separate the cases, 0-30 degrees, 31-60 degrees or 61-90 degrees
            if(alpha <= 30) {
                return 12;
            } else if (alpha <= 60) {
                return 11;
            } else {
                return 10;
            }

        } else if (x <= 0 && x >= -1.5 && y < 0 && y >= -1.5) { // Third case, third quadrant (=left, lower)

            // Now we separate the cases, 0-30 degrees, 31-60 degrees or 61-90 degrees
            if(alpha <= 30) {
                return 9;
            } else if (alpha <= 60) {
                return 8;
            } else {
                return 7;
            }
        } else if (x >= 0 && x <= 1.5 && y < 0 && y >= -1.5) { // Fourth case, fourth quadrant (=right, lower)

            // Now we separate the cases, 0-30 degrees, 31-60 degrees or 61-90 degrees
            if(alpha <= 30) {
                return 6;
            } else if (alpha <= 60) {
                return 5;
            } else {
                return 4;
            }
        }
    return -1;
    }



}
