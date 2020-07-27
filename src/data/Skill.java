package data;

public class Skill
{
    public String name;
    public int type; // CHANGE THIS TO ENUM LATER
    public int cost; // The amount of MP needed to use the skill

    public Skill (String name, int type, int cost)
    {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    // Skill Methods
    public void physAttack(String aName, int[] aStats, String bName, int[] bStats)
    {
        
    }

    public void magAttack()
    {

    }
}
