/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;


/**
 *
 * @author manu
 */
public class TestP2 {
    public static void main(String[] args) {
        
        // --------- P2 ---------
        
        System.out.println("--- PRUEBA P2: JAVA ---");

        // 1. Probando Monster
        System.out.println("\n[1. Probando Monster...]");
        Monster m1 = new Monster("Goblin", 5.0f, 3.0f);
        System.out.println(m1); // Prueba toString()
        System.out.println("Attack: " + m1.attack()); // Prueba attack() y Dice
        System.out.println("Dead? " + m1.dead()); // Prueba dead()

        // 2. Probando Player
        System.out.println("\n[2. Probando Player...]");
        Player p1 = new Player('0', 8.0f, 7.0f);
        System.out.println(p1); // Prueba toString() e initialize
        // NOTA: No podemos probar newWeapon o sumWeapons porque son private,
        // así que attack() solo usará la fuerza del jugador.
        System.out.println("Attack: " + p1.attack()); 
        p1.resurrect(); // Prueba resurrect()
        System.out.println("After Resurrect: " + p1);

        // 3. Probando Labyrinth
        System.out.println("\n[3. Probando Labyrinth...]");
        Labyrinth lab = new Labyrinth(5, 5, 4, 4); // 5x5, salida en (4,4)
        System.out.println(lab); // Prueba toString()
        System.out.println("Have a winner? " + lab.haveAWinner()); // Prueba haveAWinner()
        
        lab.addMonster(1, 1, m1); // Prueba addMonster()
        System.out.println("Laberinto con Monstruo:");
        System.out.println(lab); // Debería aparecer una 'M' en (1,1)

        // 4. Probando Game (¡El Test de Integración!)
        // (Esto solo funciona si has comentado configureLabyrinth() en el constructor)
        System.out.println("\n[4. Probando Game...]");
        Game game = new Game(2); // Crea 2 jugadores
        
        // getGameState() es el test definitivo.
        // Prueba la creación de Game, Player, Labyrinth y el log.
        GameState gs = game.getGameState();
        
        System.out.println("--- Estado Inicial del Juego ---");
        System.out.println(gs.getLabyrinth());
        System.out.println("Players: " + gs.getPlayers());
        System.out.println("Monsters: " + gs.getMonsters());
        System.out.println("Current Player: " + gs.getCurrentPlayer());
        System.out.println("Log: " + gs.getLog());
    }
}