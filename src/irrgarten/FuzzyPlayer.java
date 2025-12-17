/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author manu
 */
public class FuzzyPlayer extends Player {
    public FuzzyPlayer(Player other){
        super(other);
    }
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if(size>0 && contained){
            return Dice.nextStep(direction, validMoves, getIntelligence());
        } else {
            validMoves.remove(direction);
            return Dice.nextStep(validMoves.get(0), validMoves, getIntelligence());
        }
    }
    @Override
    public float attack(){
        return Dice.intensity(getStrength()) + sumWeapons();
    }
    @Override
    protected float defensiveEnergy(){
        return Dice.intensity(getIntelligence()) + sumShields();
    }
    @Override
    public String toString(){
        return "Fuzzy: " + super.toString();
    }
}
