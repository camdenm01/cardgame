package csc439team3.cardgame;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    /**
     * Get input for the number of players
     * @return number of players
     */
    public int getPlayers(){
        System.out.println("Please Enter the Number of Players");
        int n = scanner.nextInt();
        return n;
    }

    /**
     * Prints the current player's hand so they can see their cards ina 2x3 grid
     * @param player current player
     */
    public void displayHand(Player player){
        System.out.println("Player " + player.id + ":\n");
        for(int i = 0; i<6; i++){
            System.out.print("[" + (i+1) + "]" + " " + player.hand.get(i).printCard());
            if(i==2 || i == 5){
                System.out.println("\n");
            }
        }
    }

    /**
     * Get use input if they want to draw from the deck or discard piles, or quit
     * @return
     */
    public int getAction(){
        System.out.println("Draw card from [1]deck or [2]discard pile? Enter the corresponding number. Enter -1 to quit.");
        int action = scanner.nextInt();
        return action;
    }

    /**
     * After a card is drawn, player inputs which card to replace in their hand, or if it should be discarded
     * @param card
     * @return
     */
    public int keepOrDiscard(Card card){
        System.out.println("You drew: " + card.printCard());
        System.out.println("Which card would you like to replace from your hand? Enter the corresponding number.\n" +
                "Or type 0 to discard.");
        int swapWith = scanner.nextInt();
        return swapWith;
    }

    /**
     * Player must keep a card pulled from the discard pile
     * @param card
     * @return
     */
    public int keep(Card card){
        System.out.println("You drew: " + card.printCard());
        System.out.println("Which card would you like to replace from your hand? Enter the corresponding number.\n" +
                "You may not discard.");
        int swapWith = scanner.nextInt();
        return swapWith;
    }

    /**
     * Output a goodbye message
     */
    public void quitGame(){
        System.out.println("Thank you for playing!");
    }

}
