package armour;

public class Chainmail extends Armour {
    private final int DEFENCE = 50;
    private final int MOBILITY = 50;
    private final String COLOR = "\u001B[31m";//red
    //Animated info
    private String[][] image;
    private final int IMAGE_COUNT = 1;
    private final int HEIGHT = 6;
    private final int WIDTH = 11;
    private final int FORWARD = 0;

    public Chainmail() {//constructor
        super();
        setStats();
        setImage();
    }

    private void setStats() {
        super.setDamageReduction(DEFENCE);
        super.setMobility(MOBILITY);
    }//setStats

    public void setImage(){
        image = new String[IMAGE_COUNT][HEIGHT];        
        image[FORWARD][0] ="  __   __  ";
        image[FORWARD][1] =" / ╬---╬ \\ ";
        image[FORWARD][2] ="/╦|╫╫╫╫╫|╦\\";
        image[FORWARD][3] ="╚╩|╫╫╫╫╫|╩╝";
        image[FORWARD][4] ="  |╫╫╫╫╫|  ";
        image[FORWARD][5] ="  |╫╫╫╫╫|  ";

        //store animated information
        super.setInfo(HEIGHT,WIDTH,image,IMAGE_COUNT,COLOR,FORWARD);
    }//setImage
}//class
