/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author manu
 */
public abstract class CardDeck<T extends CombatElement> {
    private ArrayList<T> cardDeck;
    
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    protected void addCard(T card){
        cardDeck.add(card);
    }
    
    protected abstract void addCards();
    
    public T nextCard(){
        if(cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck);
        }
        
        T card = cardDeck.get(0);
        cardDeck.remove(card);
        return card;
        
    }
}


/*
Comprueba si puedes añadir al parámetro T en la clase CardDeck la siguiente restricción: 
    <T extends CombatElement>


Si solo se pone public class CardDeck<T>, Java permitiría crear barajas de cualquier cosa, 
por ejemplo: una CardDeck<String> o una CardDeck<Integer>.
Esto no es correcto porque solo queremos crear barajas de CombatElement(Weapon y Shield).

Al poner public class CardDeck<T extends CombatElement>, se impone una regla de seguridad:

Solo se pueden crear barajas de objetos que sean "hijos" (subclases) de CombatElement.
*/

/*
Al añadir <T extends CombatElement>, le dices al compilador: 
"Esta clase es una plantilla, pero solo acepto tipos que sean hijos de CombatElement". 
Así garantizas que solo se puedan crear barajas de armas o escudos.
*/