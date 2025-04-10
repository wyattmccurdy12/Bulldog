/**
 * The BulldogGame class serves as the entry point for the Bulldog Game application.
 * It initializes the Model, View, and Controller components of the game.
 * 
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Creates an instance of the BulldogGameModel (Model) to manage the game logic.</li>
 *   <li>Creates an instance of the BulldogGameView (View) to handle the user interface.</li>
 *   <li>Creates an instance of the BulldogGameController (Controller) to manage
 *       interactions between the Model and the View.</li>
 * </ul>
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * <p>Written with help from Github Copilot (GPT-4o)</p>
 */

package src;

public class BulldogGame {

    /**
     * The main method serves as the entry point for the application.
     * It initializes the Model, View, and Controller components.
     */
    public static void main(String[] args) {
        BulldogGameModel model = new BulldogGameModel();
        BulldogGameView view = new BulldogGameView();
        BulldogGameController controller = new BulldogGameController(model, view); // Create the controller

        view.registerController(controller);
        model.addObserver(controller); // Add the controller as an observer to the model
    }
}