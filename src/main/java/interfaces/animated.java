package interfaces;

public interface animated {
    int animationStatus = 1;
    void update();
    default int getAnimationStatus(){
        return animationStatus;
    }
}
