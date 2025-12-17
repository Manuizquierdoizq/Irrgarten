/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author manu
 */
public class Weapon extends CombatElement {
    
    public Weapon(float power, int uses){
        super(power,uses);
    }
    
    public float attack(){
        return super.produceEffect();
    }
    
    // Este método ya ha sido declarado en su clase padre por lo que
    // debo poner override para que se sobreescriba y si dentro hago
    // super.toString() llamo al metodo de la clase padre.
    
    @Override
    public String toString(){
        return "W["+ super.toString() + "]" + ",";
    }
}
