package data;

public class Skill
{
    public String name;
    // CHANGE THIS TO ENUM LATER
    public int type;
    public int cost; // The amount of MP needed to use the skill

    public Skill (String name, int type, int cost)
    {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }
}
