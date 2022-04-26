package csc439team3.cardgame;

import java.util.Scanner;

/**
 * View is the only class that displays output and gets input
 */
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
     * Get input for number of holes
     * @return
     */
    public int getHoles(){
        System.out.println("Enter the number of holes you would like to play");
        int n = scanner.nextInt();
        return n;
    }

    /**
     * Prints the current player's hand so they can see their cards in a 2x3 grid. Also shows top of discard pile.
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
     * Displays the top of discard pile to player
     * @param deck
     */
    public void displayTopOfDiscard(Deck deck){
        System.out.println("Top of discard pile: " + deck.discard.get(deck.discard.size()-1).printCard());
    }

    /**
     * Get user input if they want to draw from the deck or discard piles, or quit
     * @return
     */
    public int getAction(){
        System.out.println("Draw card from [1]deck or [2]discard pile? [3]View scores. Enter the corresponding number. Enter -1 to quit.");
        int action = scanner.nextInt();
        return action;
    }

    /**
     * If a player declines to swap, they may reveal a card. This method gets which card to reaveal.
     * @return card index to reveal
     */
    public int wantToReveal(){
        System.out.println("Since you declined to swap in a card, you may still reveal a card.");
        System.out.println("Enter the corresponding number or 0 if you do not want to reveal a card.");
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


    public void printScoreboard(int totalHoles, int remainingHoles, Player[] players){
        int currentHole = totalHoles - remainingHoles + 1;
        System.out.println("Current Hole: " + currentHole);
        System.out.println("Total Holes: " + totalHoles);
        for(Player player: players){
            System.out.println("Player " + player.id + ": " + player.score);
        }
    }

    public void printWinner(Player[] players){
        System.out.println("The winner is: Player " + players[0].id);
    }

}
