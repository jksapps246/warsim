package warrior;

public class Human extends Warrior {
    private final int MIN_HEALTH = 125;
    private final int BONUS_HEALTH = 75;
    private final int MIN_STRENGTH = 50;
    private final int BONUS_STRENGTH = 150;
    private final int MIN_DEXTERITY = 50;
    private final int BONUS_DEXTERITY = 100;
    private final int HEAL = 20;
    private final int STAT_BOOST = 40;
    private int luck = 6;

    //Animated info
    private String[][] image;
    private final int IMAGE_COUNT = 3;
    private final int HEIGHT = 5;
    private final int WIDTH = 8;
    private final int FORWARD = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;

    //special colors
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private String[] scolors = new String[5];

    public Human() {//constructor
        super();
        setStats();
        setImage();
    }

    public Human(String name) {//overloaded constructor
        super(name);
        setStats();
        setImage();
    }
    
    @Override
    public void activateSpecial(){
        super.resetSpecial();//set default
        super.specialOn();//turn on special
        super.storePreviousStats();
        //heal warrior
        int health = super.getHealth();
        health += (super.getMaxHealth() * HEAL)/100;
        super.setHealth(health);
        //boost stats
        super.boostStats(STAT_BOOST);
        //special colors
        scolors[0] = super.getColor();//default color
        scolors[1] = ANSI_WHITE;
        scolors[2] = ANSI_CYAN;
        scolors[3] = ANSI_GREEN;
        scolors[4] = ANSI_RED;
        super.setSpecialColors(scolors);
    }//activateSpecial

    private void setStats() {
        super.setHealth(MIN_HEALTH, BONUS_HEALTH);
        super.setStrength(MIN_STRENGTH, BONUS_STRENGTH);
        super.setDexterity(MIN_DEXTERITY, BONUS_DEXTERITY);
        super.setLuck(randNum.nextInt(luck) + 1); // 1 - 15
    }//setStats

    private void setImage(){
        image = new String[IMAGE_COUNT][HEIGHT];
        image[FORWARD][0] = "   __   ";
        image[FORWARD][1] = "  qôóp  ";
        image[FORWARD][2] = "//|~~|\\\\";
        image[FORWARD][3] = "√  ==  √";
        image[FORWARD][4] = "  [][]  ";

        image[LEFT][0] = "   __   ";
        image[LEFT][1] = "  (_'}  ";
        image[LEFT][2] = " <|! \\. ";
	    image[LEFT][3] = "  / ^ >`";
        image[LEFT][4] = " <`-  \\ ";

        image[RIGHT][0] = "  __    ";
        image[RIGHT][1] = " {'_)   ";
        image[RIGHT][2] = "./ !|>  ";
        image[RIGHT][3] = "'< ^ \\  ";
        image[RIGHT][4] = " / -'>  ";

        //store animated information
        super.setInfo(HEIGHT,WIDTH,image,IMAGE_COUNT,ANSI_WHITE,FORWARD,LEFT,RIGHT);
    }//setImage
}//class