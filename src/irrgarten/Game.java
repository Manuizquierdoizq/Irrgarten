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
public class Game {
    private final static int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private Labyrinth labyrinth;
    private Player currentPlayer;
    private ArrayList<Monster>monsters;
    private ArrayList<Player>players;
    
    public Game(int nPlayers){
        this.monsters = new ArrayList<>();
        this.players = new ArrayList<>();
        
        for(int i=0;i<nPlayers;i++){
            Player p = new Player((char)('0'+ i), Dice.randomIntelligence(), Dice.randomStrength());
            this.players.add(p);
        }
        
        this.currentPlayerIndex = Dice.whoStarts(nPlayers);
        
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
        int nRows = 10;
        int nCols = 10;
        int exitRow = 9;
        int exitCol = 9;
        this.labyrinth = new Labyrinth(nRows, nCols, exitRow, exitCol);
        
        this.log = ""; // Empezamos con un log vacío
        //this.configureLabyrinth(); // Llama al método privado de esta clase
//        this.labyrinth.spreadPlayers(this.players);
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState(){
        GameState gs = new GameState(this.labyrinth.toString(),
                this.players.toString(),this.monsters.toString(),this.currentPlayerIndex,
                this.finished(),this.log);
        return gs;
    }
    
    private void configureLabyrinth(){
        throw new UnsupportedOperationException();
    }
    
    private void nextPlayer(){
        currentPlayerIndex++;
        currentPlayerIndex = currentPlayerIndex % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner){
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection(){
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon(){
        log += "El jugador ha ganado el combate.\n";
    }
    
    private void logMonsterWon(){
        log += " El monstruo ha ganado el combate.\n";
    }   
    
    private void logResurrected(){
        log += " El jugador ha resucitado.\n";
    }
    
    private void logPlayerSkipTurn(){
        log += " El jugador ha perdido el turno por estar muerto.\n";
    }
    
    private void logPlayerNoOrders(){
        log += " El jugador no ha seguido las instrucciones del jugador(humano).\n";
    }
    
    private void logNoMonster(){
        log += " El jugador no ha podido moverse a la celda indicada o esta vacia.\n";
    }
    
    private void logRounds(int rounds, int max){
        log += " Se han producido el maximo de rondas permitidas en el combate.\n";
    }
}
