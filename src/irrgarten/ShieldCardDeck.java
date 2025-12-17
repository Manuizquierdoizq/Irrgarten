/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author manu
 */
public class ShieldCardDeck extends CardDeck<Shield>{

    @Override
    protected void addCards(){
    final int NUM_CARDS = 10;

    for(int i=0;i<NUM_CARDS;i++){
            Shield s = new Shield(Dice.shieldPower(),Dice.usesLeft());
            addCard(s);
        }
    }
}
