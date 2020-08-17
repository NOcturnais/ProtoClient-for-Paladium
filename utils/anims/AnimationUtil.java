package fr.noctu.haxx.proto.utils.anims;

/**
 * Created by Arithmo on 4/11/2017 at 2:12 PM.
 */
public class AnimationUtil {

    public static float calculateCompensation(float target, float current, long delta, double speed) {
        float diff = current - target;
        if (delta > 1000) {
            delta = 16;
        }
        if (diff > speed) {
            double xD = (Math.max(speed * delta / (1000 / 60), 0.5));
            current -= xD;
            if (current < target) {
                current = target;
            }
        } else if (diff < -speed) {
            double xD = (Math.max(speed * delta / (1000 / 60), 0.5));
            current += xD;
            if (current > target) {
                current = target;
            }
        } else {
            current = target;
        }
        return current;
    }

}
