package engine;

import java.util.Scanner;
import java.util.ArrayList;

import data.PartyMember;
import data.Enemy;

public class Main
{
    public static void main (String [] args)
    {
        // Game Flow Variables
        int turnNumber = 1; // 1 by default
        int position = 0; // 0 by default
        int command;

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        ArrayList<PartyMember> party = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();

        // Create your Party Members and Enemies here!!
        String p1Name = "Warrior";
        int p1Stats [] = {10, 5, 6, 0, 2, 3, 4, 2};
        party.add(new PartyMember(p1Name, p1Stats));

        String e1Name = "Slime";
        int e1Stats [] = {5, 0, 5, 0, 1, 3, 2, 1};
        enemies.add(new Enemy(e1Name, e1Stats));


        // Battle Loop
        while (true)
        {
            System.out.println("Turn Number: "+turnNumber);

            /* If position is between 1 and 3, its the Player's Turn
            *  If position is between 4 and 7, its the Enemy's Turn
            *  The last statement shouldn't happen
            */
            if (position >= 0 && position < 3)
            {
                System.out.println("Player Turn");
                System.out.println(party.get(position).name+" \nHP: "+party.get(position).stats[0]);
                command = sc.nextInt();
            } else if (position >= 4 && position < 8) {
                System.out.println("Enemy Turn");
                turnNumber += 1;
            } else {
                System.out.println("Error: Invalid Position Value");
                System.exit(0);
            }
        }
    }
}
