package csc439team3.cardgame;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    public int getPlayers(){
        System.out.println("Please Enter the Number of Players");
        int n = scanner.nextInt();
        return n;
    }

    public void displayHand(Player player){
        System.out.println("Player " + player.id + ":\n");
        for(int i = 0; i<6; i++){
            System.out.print("[" + (i+1) + "]" + " " + player.hand.get(i).printCard());
            if(i==2 || i == 5){
                System.out.println("\n");
            }
        }
    }

    public int getAction(){
        System.out.println("Draw card from [1]deck or [2]discard pile? Enter the corresponding number. Enter -1 to quit.");
        int action = scanner.nextInt();
        return action;
    }

    public int keepOrDiscard(Card card){
        System.out.println("You drew: " + card.printCard());
        System.out.println("Which card would you like to replace from your hand? Enter the corresponding number.\n" +
                "Or type 0 to discard.");
        int swapWith = scanner.nextInt();
        return swapWith;
    }

    public int keep(Card card){
        System.out.println("You drew: " + card.printCard());
        System.out.println("Which card would you like to replace from your hand? Enter the corresponding number.\n" +
                "You may not discard.");
        int swapWith = scanner.nextInt();
        return swapWith;
    }

    public void quitGame(){
        System.out.println("Thank you for playing!");
    }

}
