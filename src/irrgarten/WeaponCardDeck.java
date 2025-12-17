/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author manu
 */
public class WeaponCardDeck extends CardDeck<Weapon>{
    
    @Override
    protected void addCards(){
    final int NUM_CARDS = 10;

    for(int i=0;i<NUM_CARDS;i++){
            Weapon w = new Weapon(Dice.weaponPower(),Dice.usesLeft());
            addCard(w);
        }
    }
}
