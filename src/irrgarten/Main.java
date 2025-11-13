package irrgarten;

import irrgarten.Game;
import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;


public class Main {

    public static void main(String[] args) {
        Game game = new Game(2); // 1 jugador
        TextUI view = new TextUI();
        Controller controller = new Controller(game, view);
        controller.play();
    }
    
}