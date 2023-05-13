package warrior;

public class Orc extends Warrior {
    private final int MIN_HEALTH = 100;
    private final int BONUS_HEALTH = 75;
    private final int MIN_STRENGTH = 75;
    private final int BONUS_STRENGTH = 200;
    private final int MIN_DEXTERITY = 25;
    private final int BONUS_DEXTERITY = 50;
    private final int HEAL = 40;
    private final int STAT_BOOST = 30;
    private int luck = 5;

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
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private String[] scolors = new String[5];

    public Orc() {//constructor
        super();
        setStats();
        setImage();
    }
    public Orc(String name) {//overloaded constructor
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
        scolors[2] = ANSI_YELLOW;
        scolors[3] = ANSI_CYAN;
        scolors[4] = ANSI_RED;
        super.setSpecialColors(scolors);

    }//activateSpecial

    private void setStats() {
        super.setHealth(MIN_HEALTH, BONUS_HEALTH);
        super.setStrength(MIN_STRENGTH, BONUS_STRENGTH);
        super.setDexterity(MIN_DEXTERITY, BONUS_DEXTERITY);
        super.setLuck(randNum.nextInt(luck) + 1); // 1 - 5
    }//setStats

    public void setImage(){
        image = new String[IMAGE_COUNT][HEIGHT];
        image[FORWARD][0] = "   ππ   ";
        image[FORWARD][1] = " ç(òó)? ";
        image[FORWARD][2] = "(/{SS}\\)";
        image[FORWARD][3] = "W  ╔╗  W";
        image[FORWARD][4] = "  [][]  ";

        image[LEFT][0] = "   ππ   ";
        image[LEFT][1] = "  (δ'}  ";
        image[LEFT][2] = " <|!)\\. ";
	    image[LEFT][3] = "  / ^ >`";
        image[LEFT][4] = " <`-  \\ ";
      
        image[RIGHT][0] = "  ππ    ";
        image[RIGHT][1] = " {'δ)   ";
        image[RIGHT][2] = "./(!|>  ";
        image[RIGHT][3] = "'< ^ \\  ";
        image[RIGHT][4] = " / -'>  ";

        //store animated information
        super.setInfo(HEIGHT,WIDTH,image,IMAGE_COUNT,ANSI_WHITE,FORWARD,LEFT,RIGHT);
    }//setImage
}//class