package engine;
import java.util.Scanner;

import data.PartyMember;
import data.Enemy;

public class Main
{
    enum State
    {
        INTRO, // Intro
        PTURN, // Player Turn
        ACTION, // Action
        ETURN, // Enemy Turn
        BEND, // Battle End
    }

    public static void main (String [] args)
    {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        // Engine Variables
        State currentState = State.INTRO;
        int turnNumber = 1;
        int input; // Player Input


        // Player Stats
        String pName = "Player";
        int [] pBStats = {10, 5, 5, 2, 4, 3, 1}; // Base Stats
        int [] pCStats = {10, 5, 5, 2, 4, 3, 1}; // Current Stats

        // Enemy Stats
        String eName = "Enemy";
        int [] eBStats = {4, 0, 5, 0, 2, 3, 0}; // Base Stats
        int [] eCStats = {4, 0, 5, 0, 2, 3, 0}; // Current Stats

        // Battle Loop
        while (true)
        {
            switch(currentState)
            {
                // Intro State
                case INTRO:
                {
                    while(true)
                    {
                        System.out.println(eName+" approaches!");
                        System.out.println("Press 1 to continue...");
                        input = sc.nextInt();
                        if (input == 1)
                        {
                            currentState = State.PTURN;
                            break;
                        } else {
                            System.out.println("That's not what I told you to push.");
                        }
                    }
                    break;
                }

                // Player Turn State
                case PTURN:
                {
                    // Displays turn number and health
                    System.out.println("Turn: "+turnNumber);
                    System.out.println("["+pName+" - HP: "+pBStats[0]+"/"+pCStats[0]+"]");
                    System.out.println("["+eName+" - HP: "+eBStats[0]+"/"+eCStats[0]+"]");

                    System.out.println("===Player Phase===");
                    System.out.println("Enter a command:\n1) Attack 2) Skills 3) Items 4) Escape");
                    input = sc.nextInt();

                    switch (input)
                    {
                        
                    }
                    break;

                }
            }
        }
    }
}