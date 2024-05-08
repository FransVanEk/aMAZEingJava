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
        currentAngle = normalizeAngle(currentAngle);
        targetAngle = normalizeAngle(targetAngle);
        int angleDifference = calculateAngleDifference(currentAngle, targetAngle);
        return (double) angleDifference / steps;
    }

    private static int calculateAngleDifference(int currentAngle, int targetAngle) {
        int angleDifference = targetAngle - currentAngle;
        if (Math.abs(angleDifference) > 180) {
            angleDifference = adjustLargeAngleDifference(angleDifference);
        }
        return angleDifference;
    }

    private static int adjustLargeAngleDifference(int angleDifference) {
        if (angleDifference > 0) {
            return angleDifference - 360;
        } else {
            return angleDifference + 360;
        }
    }

    private static int normalizeAngle(int angle) {
        angle %= 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }
}
