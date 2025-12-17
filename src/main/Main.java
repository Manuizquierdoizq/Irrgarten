package main;

import irrgarten.Game;
import UI.GUI;
import controller.Controller;
import irrgarten.Game;


public class Main {

    public static void main(String[] args) {
        Game game = new Game(1); // 1 jugador
        GUI gui= new GUI();
        Controller controller = new Controller(game, gui);
        controller.play();
    }
    
}