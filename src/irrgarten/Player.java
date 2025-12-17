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
public class Player extends LabyrinthCharacter{
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private char number;
    private int consecutiveHits=0;
    ArrayList<Weapon> weapons;
    ArrayList<Shield> shields;
    private ShieldCardDeck shieldCardDeck;
    private WeaponCardDeck weaponCardDeck;
    
    
    public Player(char number, float intelligence, float strength){
        super("Player#"+number,intelligence,strength,INITIAL_HEALTH);
        this.number=number;
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        
        this.shieldCardDeck = new ShieldCardDeck();
        shieldCardDeck.addCards();
        this.weaponCardDeck = new WeaponCardDeck();
        weaponCardDeck.addCards();   
    }
        
    public Player(Player other){
        super(other);
        this.number=other.number;
        this.weapons = other.weapons;
        this.shields = other.shields;
        shieldCardDeck = other.shieldCardDeck;
        weaponCardDeck = other.weaponCardDeck;
    }
    
    public void resurrect(){
        this.shields.clear();
        this.weapons.clear();
        setHealth(INITIAL_HEALTH);
        this.consecutiveHits=0;
    }
    
    public char getNumber() {
        return number;
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
    
    @Override
    public float attack(){
        return getStrength()+sumWeapons();
    }
    
    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i=0;i<wReward;i++){
            Weapon wnew = weaponCardDeck.nextCard();
            receiveWeapon(wnew);
        }
        for (int i=0;i<sReward;i++){
            Shield snew = shieldCardDeck.nextCard();
            receiveShield(snew);
        }
        int extraHealth = Dice.healthReward();
        setHealth(getHealth()+extraHealth);
    }
    
    @Override
    public String toString(){
        return super.toString() + " Weapons: " +weapons.toString() + " Shields: " + shields.toString();
    }
    
        private void receiveWeapon(Weapon w){
            for(int i=weapons.size()-1;i>=0;i--){
                boolean discard = weapons.get(i).discard();
                if(discard){
                    weapons.remove(weapons.get(i));
                }
            }
            int size = weapons.size();
            if(size<MAX_WEAPONS){
                weapons.add(w);
            }
        }
    
    private void receiveShield(Shield s){
        for(int i=shields.size()-1;i>=0;i--){
            boolean discard = shields.get(i).discard();
            if(discard){
                shields.remove(shields.get(i));
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
    
    protected float sumWeapons(){
        float sum = 0.0f;
        
        for (Weapon w : this.weapons) {
            sum += w.attack();
        }
        
        /*
        Es lo mismo que 
                            float suma = (float) 0.0;
                            for (int i=0; i<weapons.size(); i++){
                            suma += weapons.get(i).attack();
        */
        
        return sum;
    }
    
    protected float sumShields() {
        float sum = 0.0f;
        
        for (Shield s : this.shields) {
            sum += s.protect();
        }
        
        return sum;
    }
    
    protected float defensiveEnergy(){
        return getIntelligence() + sumShields();
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
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}

