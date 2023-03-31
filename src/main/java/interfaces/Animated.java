package interfaces;

public interface Animated
{
    int animationStatus = 1;

    void update();

    default int getAnimationStatus() {
        return animationStatus;
    }
}
