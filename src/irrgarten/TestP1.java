/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Arrays;

/**
 *
 * @author manu
 */
public class TestP1 {
    public static void main(String[] args) {
        
        // --------- P1 ---------
        
        // --------- Weapon y Shield (uso básico) ---------
        Weapon w = new Weapon(1.5f, 3);   // (power, uses)
        Shield s = new Shield(2.0f, 2);   // (power, uses)

        System.out.println("w = " + w);
        System.out.println("s = " + s);

        System.out.println("w.attack() = " + w.attack());
        System.out.println("w.attack() = " + w.attack());   // para ver decremento de usos
        System.out.println("w.discard()? " + w.discard());

        System.out.println("s.protect() = " + s.protect()); // si tu Shield no tiene protect(), elimina esta línea
        System.out.println("s.discard()? " + s.discard());

        // --------- Enumerados ---------
        System.out.println("\n=== Enums ===");
        System.out.println("Directions:    " + Arrays.toString(Directions.values()));
        System.out.println("Orientation:   " + Arrays.toString(Orientation.values()));
        System.out.println("GameCharacter: " + Arrays.toString(GameCharacter.values()));

        // --------- GameState ---------
        System.out.println("\n=== GameState ===");
        GameState gs = new GameState(
                "Labyrinth[..]",
                "Players[..]",
                "Monsters[..]",
                0,
                false,
                "Inicio del juego"
        );
        System.out.println("currentPlayer=" + gs.getCurrentPlayer() +
                ", labyrinth=" + gs.getLabyrinth());

        
        // Ejemplo de cómo estructurarlo:
        int resurrectCount = 0;
        System.out.println("\n=== Probando Dice 100 veces ===");

        for (int i = 0; i < 100; i++) {
            // --------- Dice (sin usar constantes internas) ---------
            //System.out.println("\n=== Dice ===");
            //System.out.println("whoStarts(4):        " + Dice.whoStarts(4));
            //System.out.println("randomPos(10):       " + Dice.randomPos(10));
            //System.out.println("randomIntelligence(): " + Dice.randomIntelligence());
            //System.out.println("randomStrength():     " + Dice.randomStrength());
            //System.out.println("resurrectPlayer():    " + Dice.resurrectPlayer());
            //System.out.println("weaponsReward():      " + Dice.weaponsReward());
            //System.out.println("shieldsReward():      " + Dice.shieldsReward());
            //System.out.println("healthReward():       " + Dice.healthReward());
            //
            //int uses = Dice.usesLeft();
            //System.out.println("usesLeft():           " + uses);
            //System.out.println("discardElement(uses): " + Dice.discardElement(uses));
            //
            //System.out.println("weaponPower():        " + Dice.weaponPower());
            //System.out.println("shieldPower():        " + Dice.shieldPower());
            //System.out.println("intensity(5.0f):      " + Dice.intensity(5.0f));
            //
            //System.out.println("whoStarts(4): " + Dice.whoStarts(4));
            //System.out.println("randomIntelligence(): " + Dice.randomIntelligence());

            // Para el booleano:
            if (Dice.resurrectPlayer()) {
                resurrectCount++;
            }
        }

        // Al final del bucle, imprime el resultado del contador:
        System.out.println("Veces resucitado (de 100): " + resurrectCount);
    }
}