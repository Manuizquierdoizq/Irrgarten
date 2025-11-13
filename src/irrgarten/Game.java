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
        this.configureLabyrinth();
        this.labyrinth.spreadPlayers(this.players);
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        log = "";
        boolean dead = currentPlayer.dead();
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            if(direction != preferredDirection){
                logPlayerNoOrders();
            }
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster==null){
                logNoMonster();
            } else {
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        } else {
            manageResurrection();
        }
        
        boolean endGame = finished();
        
        if(!endGame){
            nextPlayer();
        }
        return endGame;
    }
    
    public GameState getGameState(){
        GameState gs = new GameState(this.labyrinth.toString(),
                this.players.toString(),this.monsters.toString(),this.currentPlayerIndex,
                this.finished(),this.log);
        return gs;
    }
    
    private void configureLabyrinth(){
        this.labyrinth.addBlock(Orientation.VERTICAL, 2, 3, 4);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 5, 0, 5);
        
        Monster m1 = new Monster("Monstruo 1", Dice.randomIntelligence(), Dice.randomStrength());
        Monster m2 = new Monster("Monstruo 2", Dice.randomIntelligence(), Dice.randomStrength());
        
        this.labyrinth.addMonster(1, 2, m1);
        this.labyrinth.addMonster(5, 8, m2);
        this.monsters.add(m1);
        this.monsters.add(m2);
    }
    
    private void nextPlayer(){
        currentPlayerIndex++;
        currentPlayerIndex = currentPlayerIndex % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validmoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validmoves);
        return output;
    }
    
    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && rounds<MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            rounds++;
            
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            if(!lose){
                float newplayerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(newplayerAttack);
            }
        }
        logRounds(rounds, MAX_ROUNDS);
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        } else {
            logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            logResurrected();
        } else {
            logPlayerSkipTurn();
        }
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
