package engine;

import data.PartyMember;
import data.Enemy;
import data.Skill;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

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
        int [] pStats = {20, 14, 5, 5, 4, 3, 3, 1}; // These are modifiable
        Skill s1 = new Skill("Fire", 1, 3);
        Skill s2 = new Skill("Heal", 2, 5);

        // Enemy Variables
        String eName = "Enemy";
        int [] eStats = {8, 10, 6, 0, 2, 2, 1, 0};

        // Final Stats
        // These are used to ensure the HP and MP stats don't go past their intended values
        final int [] pBaseStats = {20, 14};
        final int [] eBaseStats = {8, 10};

        Scanner sc = new Scanner(System.in);

        // Battle Loop
        while (true)
        {
            if (playerPhase)
            {
                // Player Phase
                System.out.println("===Player Phase===");
                System.out.println(pName+": HP - "+pStats[0]+"/"+pBaseStats[0]+" MP - "+pStats[1]+"/"+pBaseStats[1]);
                System.out.println(eName+": HP - "+eStats[0]+"/"+eBaseStats[0]+" MP - "+eStats[1]+"/"+eBaseStats[1]);
                System.out.println("1) Attack, 2) Skills, 3) Items, 4) Escape\nEnter a command:");
                input = sc.nextInt();

                switch (input)
                {
                    //Attack
                    case 1:
                        attack(pName, pStats, eName, eStats);
                        playerPhase = false;
                        break;
                    // Skills
                    case 2:
                        // Place holder, I'll have it list actual skill names later
                        System.out.println("1) Fire, 2) Heal, 3) Go Back");
                        input = sc.nextInt();
                        switch (input)
                        {
                            case 1:
                                if (pStats[1] < s1.cost) {
                                    System.out.println("Need more MP!");
                                } else {
                                    skill(pName, pStats, eName, eStats, s1.name, s1.type, s1.cost, pBaseStats);
                                    playerPhase = false;
                                }
                                break;
                            case 2:
                                if (pStats[1] < s2.cost) {
                                    System.out.println("Need more MP!");
                                } else {
                                    skill(pName, pStats, eName, eStats, s2.name, s2.type, s2.cost, pBaseStats);
                                    playerPhase = false;
                                }
                                playerPhase = false;
                                break;
                            case 3:
                                break;

                            default:
                                System.out.println("Error: Invalid Input");
                                break;
                        }
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

    static void skill(String aName, int [] aStats, String bName, int [] bStats, String sName, int sType, int sCost, int maxStats [])
    {
        int damage;

        switch (sType)
        {
            // Physical Damage
            case 0:
                // Damage calculations
                damage = aStats[2] - bStats[6]; // Damage calculation is a.atk - b.def
                if (damage < 0) damage = 0; // Prevents damage from being negative
                bStats[0] -= damage;
                if (bStats[0] < 0) bStats[0] = 0; // Prevents hp from being negative

                // Print statements
                System.out.println(aName+" does "+sName+"!");
                System.out.println(bName+" takes "+damage+" damage.");
                System.out.println(bName+" has "+bStats[0]+" HP left.");
                break;

            // Magic Damage
            case 1:
                aStats[1] -= sCost;
                damage = aStats[3] - bStats[7]; // Damage calculation is a.mag - b.res
                if (damage < 0) damage = 0; // Prevents damage from being negative
                bStats[0] -= damage;
                if (bStats[0] < 0) bStats[0] = 0; // Prevents hp from being negative

                // Print statements
                System.out.println(aName+" casts "+sName+"!");
                System.out.println(bName+" takes "+damage+" damage.");
                System.out.println(bName+" has "+bStats[0]+" HP left.");
                break;

            // HP Restore
            case 2:
                aStats[0] += 5; // HP is restored at a set rate, maybe change this later?
                if (aStats[0] > maxStats[0]) aStats[0] = maxStats[0]; // Prevents the player from going past their max hp

                // Print statements
                System.out.println(aName+" casts "+sName+"!");
                System.out.println(aName+" restores 5 HP!");
                System.out.println(aName+" has "+aStats[0]+" HP left.");
                break;

            // Stat Buff
            case 3:
                break;

            // This shouldn't happen
            default:
                System.out.println("Error: Invalid Skill Type");
                break;
        }
    }

    static boolean escape(int [] aStats, int [] bStats)
    {
        // Escape calculation, if user's speed is greater than the enemy's speed + 3
        if (aStats[4] > bStats[4] + 3) {
            return true;
        } else {
            return false;
        }
    }
}