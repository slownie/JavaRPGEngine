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
    }

    public static void main (String [] args)
    {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        // Engine Variables
        State currentState = State.INTRO;
        int turnNumber = 1;
        int input; // Player Input
        int victory = 0; // 0 = no victory, 1 = Player victory, 2 = Enemy victory


        // Player Stats
        String pName = "Player";
        int [] pBStats = {10, 5, 5, 2, 4, 3, 1}; // Base Stats
        int [] pCStats = {10, 5, 5, 2, 4, 3, 1}; // Current Stats

        // Enemy Stats
        String eName = "Enemy";
        int [] eBStats = {4, 0, 5, 0, 2, 3, 2}; // Base Stats
        int [] eCStats = {4, 0, 5, 0, 2, 3, 2}; // Current Stats

        // Battle Loop
        while (victory == 0)
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
                    System.out.println("Turn: " + turnNumber);
                    System.out.println("[" + pName + " - HP: " + pCStats[0] + "/" + pBStats[0] + "]");
                    System.out.println("[" + eName + " - HP: " + eCStats[0] + "/" + eBStats[0] + "]");

                    System.out.println("===Player Phase===");
                    System.out.println("Enter a command:\n1) Attack 2) Skills 3) Items 4) Escape");
                    input = sc.nextInt();

                    switch (input)
                    {
                        // Attack
                        case 1: {
                            attack(pName, pCStats, eName, eCStats);
                            break;
                        }

                        // Skill Menu
                        case 2:
                        {
                            break;
                        }
                    }

                    // Victory check
                    if (eCStats[0] == 0)
                    {
                        victory = 1;
                        break;
                    } else {
                        currentState = State.ETURN;
                        break;
                    }
                }

                // Enemy Turn
                case ETURN:
                {
                    System.out.println("===Enemy Phase===");
                    // AI? What's that?
                    attack(eName, eCStats, pName, pCStats);


                    // Defeat check
                    if (pCStats[0] == 0)
                    {
                        victory = 2;
                        break;
                    } else {
                        currentState = State.PTURN;
                        break;
                    }
                }
            }
        }

        // Victory
        if (victory == 1)
        {
            System.out.println("You win!");
        } else {
            System.out.println("Game over!");
        }
    }

    static void attack(String aName, int [] aStats, String bName, int [] bStats)
    {
        // Damage calculations
        int damage = aStats[2] - bStats[6]; // Damage calculation is a.atk - b.def
        if (damage < 0) damage = 0; // Prevents damage from being negative
        bStats[0] -= damage;
        if (bStats[0] < 0) bStats[0] = 0; // Prevents hp from being negative

        // Print statements
        System.out.println(aName+" attacks!");
        System.out.println(bName+" takes "+damage+" damage.");
        System.out.println(bName+" has "+bStats[0]+" HP left.");
    }
}