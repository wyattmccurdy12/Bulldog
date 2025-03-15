package src;

public class BulldogGame {
    public static void main(String[] args) {
        BulldogGameModel model = new BulldogGameModel();
        BulldogGameView view = new BulldogGameView();
        new BulldogGameController(model, view);
    }
}