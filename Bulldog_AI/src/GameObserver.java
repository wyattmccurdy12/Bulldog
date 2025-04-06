package src;

/**
 * The GameObserver interface should be implemented by any class
 * that wants to observe changes in the BulldogGameModel.
 */
public interface GameObserver {
    /**
     * Called when the observed game model changes state.
     */
    void update();
}