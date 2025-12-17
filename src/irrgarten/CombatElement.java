/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author manu
 */
public abstract class CombatElement {
    private float effect;
    private int uses;

    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){
        if (uses > 0){
            uses--;
            return effect;
        } else return 0;
    } 
    
    public String toString(){
        return effect + ", " + uses;
    }    
    
    /*
    La forma correcta de llamar a un método static desde otra 
    clase es usar el nombre de la clase seguido de un punto y el 
    nombre del método, como en NombreDeLaClase.nombreDelMetodo(). 
    No es necesario crear una instancia de la clase para llamar a un método static. 
    */
    public boolean discard(){
        return Dice.discardElement(uses);
    }

}
