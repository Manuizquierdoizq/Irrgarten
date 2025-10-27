/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Random;
/**
 *
 * @author manu
 */
public class Dice {
    final private static int MAX_USES = 5;
    final private static float MAX_INTELLIGENCE = 10.0f;
    final private static float MAX_STRENGTH = 10.0f;
    final private static float RESURRECT_PROB = 0.3f;
    final private static int WEAPONS_REWARD = 2;
    final private static int SHIELDS_REWARD = 3;
    final private static int HEALTH_REWARD = 5;
    final private static int MAX_ATTACK = 3;
    final private static int MAX_SHIELD = 2;
    
    private static Random generator = new Random();
    
    public static int randomPos(int max){
        int pos=generator.nextInt(max);
        return pos;
    }
    
    public static int whoStarts(int nplayers){
        int jug=generator.nextInt(nplayers);
        return jug;
    }
    
    public static float randomIntelligence(){
        return generator.nextInt()*MAX_INTELLIGENCE;
    }
    
    public static float randomStrength(){
        return generator.nextInt()*MAX_STRENGTH;
    }
    
    public static boolean resurrectPlayer(){
        return generator.nextFloat() < 0.3f;
    }
    
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD);
    }
    
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD);
    }
    
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
    }
    
    public static float weaponPower(){
        return generator.nextFloat()*MAX_ATTACK;
    }
    
    public static float shieldPower(){
        return generator.nextFloat()*MAX_SHIELD;
    }
    
    public static int usesLeft(){
        return generator.nextInt(MAX_USES);
    }
    
    public static float intensity(float competence){
        return generator.nextFloat()*competence;
    }
    
    public static boolean discardElement(int usesLeft){
        float prob = (MAX_USES-usesLeft)/MAX_USES;
        return generator.nextFloat() < prob;
    }
    
    
    
}
