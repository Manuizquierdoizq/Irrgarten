/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author manu
 */
public class Player {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits=0;
    ArrayList<Weapon> weapons;
    ArrayList<Shield> shields;
    
    public Player(char number, float intelligence, float strength){
        this.name="Player#"+number;
        this.number=number;
        this.intelligence=intelligence;
        this.strength=strength;
        this.health=INITIAL_HEALTH;
        this.row=-1;
        this.col=-1;
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
    }
    
    public void resurrect(){
        this.shields.clear();
        this.weapons.clear();
        this.health=INITIAL_HEALTH;
        this.consecutiveHits=0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    
    public char getNumber() {
        return number;
    }
    
    public void setPos(int row, int col){
        this.row=row;
        this.col=col;
    }
    
    public boolean dead(){
        return health<=0;
    }
    
    public Directions move(Directions direction, ArrayList <Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if(size>0 && !contained){
            return validMoves.get(0);
        } else {
            return direction;
        }
    }
     
    public float attack(){
        return strength+sumWeapons();
    }
    
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i=0;i<wReward;i++){
            Weapon wnew = this.newWeapon();
            receiveWeapon(wnew);
        }
        for (int i=0;i<sReward;i++){
            Shield snew = this.newShield();
            receiveShield(snew);
        }
        int extraHealth = Dice.healthReward();
        health+=extraHealth;
    }
    
    public String toString(){
        return "\n\tName = " + name + " ,number = " + number + " ,intelligence = " +
                intelligence + " ,strength = " + strength + " ,health =" + health +
                " ,row = " + row + " ,col = " + col + " ,consecutiveHits= " + 
                consecutiveHits + "\n";
    }
    
        private void receiveWeapon(Weapon w){
            Iterator<Weapon> it = this.weapons.iterator();
            while(it.hasNext()){
               Weapon wi = it.next();
               if(wi.discard()){
                   it.remove();
               }
            }
            int size = weapons.size();
            if(size<MAX_WEAPONS){
                weapons.add(w);
            }
        }
    
    private void receiveShield(Shield s){
            Iterator<Shield> it = this.shields.iterator();
            while(it.hasNext()){
               Shield si = it.next();
               if(si.discard()){
                   it.remove();
               }
            }
            int size = shields.size();
            if(size<MAX_SHIELDS){
                shields.add(s);
            }
    }
    
    private Weapon newWeapon(){
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();
        Weapon weapon = new Weapon(power,uses);
        return weapon;
    }
    
    private Shield newShield(){
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();
        Shield shield = new Shield(protection,uses);
        return shield;
    }
    
    private float sumWeapons(){
        float sum = 0.0f;
        
        for (Weapon w : this.weapons) {
            sum += w.attack();
        }
        
        return sum;
    }
    
    private float sumShields() {
        float sum = 0.0f;
        
        for (Shield s : this.shields) {
            sum += s.protect();
        }
        
        return sum;
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields();
    }
    
    private boolean manageHit(float receivedAttack) {
        float defense = defensiveEnergy();
        if(defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        } else {    
            resetHits();
        }
        if(consecutiveHits == HITS2LOSE || dead()){
            resetHits();
            return true;
        } else {
            return false;
        }
    }
    
    private void resetHits(){
        consecutiveHits=0;
    }
    
    private void gotWounded(){
        health--;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}

