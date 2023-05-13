package weapon;
import java.util.Random;

public class Spear extends Weapon {

    //Stats
    private Random randNum = new Random();
    private final int DAMAGE_AMOUNT = 45;
    private final int SKILL_RATING = 55;
    private final int CHANCE = 100;

    //Animated
    private String[][] image;
    private final int IMAGE_COUNT = 6;
    private final int HEIGHT = 3;
    private final int WIDTH = 5;
    private final int LEFT = 0;
    private final int RIGHT = 3;
    private final int ROTATE = 3;
    private final String TYPE = "Strike";
    private final String ANSI_YELLOW = "\u001B[33m";//yellow

    public Spear() {//constructor
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
        
        image[LEFT][0] = "     ";
        image[LEFT][1] = "     ";
        image[LEFT][2] = "====>";

        image[1][0] = "     ";
        image[1][1] = "====>";
        image[1][2] = "     ";

        image[2][0] = "====>";
        image[2][1] = "     ";
        image[2][2] = "     ";       
      
        image[RIGHT][0] = "     ";
        image[RIGHT][1] = "     ";
        image[RIGHT][2] = "<====";

        image[4][0] = "     ";
        image[4][1] = "<====";
        image[4][2] = "     ";

        image[5][0] = "<====";
        image[5][1] = "     ";
        image[5][2] = "     ";

        //store animated information
        super.setInfo(HEIGHT,WIDTH,image,IMAGE_COUNT,ANSI_YELLOW,TYPE,LEFT,RIGHT,ROTATE);
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

            // the players mobility helps the hit chance
            hit += mobility / 10;
            
            // add the players luck
            hit += luck;

            // the weapons skill rating helps the hit chance
            hit += (SKILL_RATING / 10);
        }
        // roll the die, enemy luck helps the roll
        roll = randNum.nextInt(CHANCE+eluck) + 1;
        if(roll <= hit) { // hit
            damage = DAMAGE_AMOUNT;
            damage += (int)Math.round(DAMAGE_AMOUNT * (strength / 100));//increase by strength as a percentage
            if(damage >100) 
                damage -= (int)Math.round(damage * (eluck/10));//reduce over spill damage by enemy luck as a percentage
            if(attackType == 2){//Sattack
                damage += (int)Math.round(damage * SKILL_RATING/100.0);//increase by skill
            }
        }
        else { // miss
            damage = 0;
        }
        return damage;
    }//strike
}//class
