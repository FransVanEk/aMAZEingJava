package nl.novi.amazeing.helpers;

import nl.novi.amazeing.models.position.GraphicsPoint;

public class DrawHelper {
    public static GraphicsPoint rotatePointFromOrigin(int x, int y, double angle) {
        double rad = Math.toRadians(angle);
        int rotatedX = (int) (x * Math.cos(rad) - y * Math.sin(rad));
        int rotatedY = (int) (x * Math.sin(rad) + y * Math.cos(rad));
        return new GraphicsPoint(rotatedX, rotatedY);
    }

    public static double getRotationStepAngle(int currentAngle, int targetAngle, int steps) {
        // Om ervoor te zorgen dat beide hoeken zich binnen 0 en 359 graden bevinden
        currentAngle = normalizeAngle(currentAngle);
        targetAngle = normalizeAngle(targetAngle);

        // Bepaal het verschil tussen de doelhoek en de huidige hoek
        int angleDifference = targetAngle - currentAngle;

        // Als het verschil groter is dan 180 graden, draai dan in de tegenovergestelde richting
        if (Math.abs(angleDifference) > 180) {
            if (angleDifference > 0) {
                angleDifference -= 360;
            } else {
                angleDifference += 360;
            }
        }

        // Bereken de stapgrootte
        return (double) angleDifference / steps;
    }

    // Hulpmethode om ervoor te zorgen dat de hoek binnen 0 en 359 graden ligt
    private static int normalizeAngle(int angle) {
        angle %= 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }
}
