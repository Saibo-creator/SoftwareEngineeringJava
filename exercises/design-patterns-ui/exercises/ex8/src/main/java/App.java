import controller.Controller;
import model.GameType;
import model.Model;
import view.GUIView;
import view.TextView;

public class App {

  /** Starts the "Rock Paper Scissors" game */
  public static void main(String[] args) {
    // TODO: instantiate MVC components so you can play the game
    Model model = new Model();
    Controller controller = new Controller(model);
    if (args.length == 0) {
      throw new IllegalArgumentException(
              "There should be one program argument that" + " can only be \"GUI\" or \"text\".");
    }

    if (args[0].compareTo("GUI") == 0) {
      new GUIView(model, controller);
    } else if (args[0].compareTo("text") == 0) {
      new TextView(model, controller);
    } else {
      throw new IllegalArgumentException("Program argument can only be \"GUI\"" + " or \"text\".");
    }
  }
}
