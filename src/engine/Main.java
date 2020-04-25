package engine;

import java.util.Scanner;

public class Main
{
    public static void main (String [] args)
    {
        // Engine Variables
        int turnNumber = 1;
        boolean playerPhase = true; // Since its 1 v 1, no need for positions
        int input; // User's input

        // Player Variables
        String pName = "Player";
        int [] pStats = {20, 14, 5, 5, 4, 3, 3, 1};

        // Enemy Variables
        String eName = "Enemy";
        int [] eStats = {8, 10, 6, 0, 2, 2, 1, 1};

        Scanner sc = new Scanner(System.in);

        // Battle Loop
        while (true)
        {
            if (playerPhase)
            {
                // Player Phase
                System.out.println("===Player Phase===");
                System.out.println("1) Attack, 2) Skills, 3) Items, 4) Escape\nEnter a command:");
                input = sc.nextInt();

                switch (input)
                {
                    //Attack
                    case 1:
                        attack(pName, pStats, eName, eStats);
                        break;
                    // Skills
                    case 2:
                        break;
                    //Items
                    case 3:
                        break;
                    //Escape
                    case 4:
                        break;

                    // Invalid Input
                    default:
                        System.out.println("Error: Invalid Input");
                        break;
                }
                if (eStats[0] == 0) break;
                playerPhase = false;

            } else {
                // Enemy Phase
                System.out.println("===Enemy Phase===");
                attack(eName, eStats, pName, pStats); // AI what's that?
                if (pStats[0] == 0) break;
                playerPhase = true;
            }
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