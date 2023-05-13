package weapon;
import java.util.Random;

public class Sword extends Weapon {
    private Random randNum = new Random();
    private final int DAMAGE_AMOUNT = 55;
    private final int SKILL_RATING = 45;
    private final int CHANCE = 100;

    //Animated info
    private String[][] image;
    private final int IMAGE_COUNT = 4;
    private final int HEIGHT = 3;
    private final int WIDTH = 5;
    private final int LEFT = 0;
    private final int RIGHT = 2; 
    private final int ROTATE = 2;
    private final String TYPE = "Strike";
    private final String ANSI_RED = "\u001B[31m";//color red

    public Sword() {//constructor
        super();
        setStats();
        setImage();
    }

    private void setStats() {//set stats
        super.setDamageAmount(DAMAGE_AMOUNT);
        super.setSkillRating(SKILL_RATING);
    }//setStats

    public void setImage(){//set animated images
        image = new String[IMAGE_COUNT][HEIGHT];
        image[LEFT][0] = "/\\   ";
        image[LEFT][1] = "║║   ";
        image[LEFT][2] = "╬╬   ";
    
        image[1][0] = "    \\";
        image[1][1] = "  \\  ";
        image[1][2] = "[===>";
        
        image[RIGHT][0] = "   /\\";
        image[RIGHT][1] = "   ║║";
        image[RIGHT][2] = "   ╬╬";

        image[3][0] = "/    ";
        image[3][1] = " /   ";
        image[3][2] = "<===]";

        //store animated information
        super.setInfo(HEIGHT,WIDTH,image,IMAGE_COUNT,ANSI_RED,TYPE,LEFT,RIGHT,ROTATE);
    }//setImage

    @Override
    public int strike(int attackType, int strength, 
                      int dexterity, int luck, int mobility,Boolean special, int eluck) {
        int roll; // the number rolled
        int hit = 0; // the number to hit
        int damage = 0;//damage to player
        if(special){
            hit = CHANCE+luck;//gaurantee to hit
        }
        else{
        
            // based on the attack type, adjust the hit chance
            if(attackType == 1) { // basic attack
                hit += (int)Math.round(dexterity/2) + luck;
            }
            else { // heavy attack
                hit += (int)Math.round(dexterity/2);
            }
            
            // the players dexterity helps the hit chance
            hit += (int)Math.round(mobility / 10);
            
            // the weapons skill helps hurts the hit chance
            hit += (int)Math.round(SKILL_RATING / 10);
            
            // add the players luck
            hit += luck;
        }

        // roll the die        
        roll = randNum.nextInt(CHANCE) + 1;
        roll += eluck;//add enemy luck

        // hit or miss
        if(roll <= hit) { //hit
            damage = DAMAGE_AMOUNT;
            damage += (int)Math.round(DAMAGE_AMOUNT * (strength / 100));//increase by strength as a percentage
            if(damage >100) 
                damage -= (int)Math.round(damage * (eluck/10));//reduce over spill damage by enemy luck as a percentage

            if(attackType == 2)//Sattack
                damage += (int)Math.round(damage * SKILL_RATING/100.0);//increase by skill
        }
        else { // miss
            damage = 0;
        }
        return damage;
    }//strike
}//class