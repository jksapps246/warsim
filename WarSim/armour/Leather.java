package armour;

public class Leather extends Armour {
    private final int DEFENCE = 45;
    private final int MOBILITY = 55;
    private final String COLOR = "\u001B[32m";//green

    //Animated info
    private String[][] image;
    private final int IMAGE_COUNT = 1;
    private final int HEIGHT = 6;
    private final int WIDTH = 11;
    private final int FORWARD = 0;

    public Leather() {//constructor
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
        image[FORWARD][1] =" / -`-'- \\ ";
        image[FORWARD][2] ="/_|-----|_\\";
        image[FORWARD][3] ="  |-----|  ";
        image[FORWARD][4] ="  |=====|  ";
        image[FORWARD][5] ="  |_____|  ";

        //store animated information
        super.setInfo(HEIGHT,WIDTH,image,IMAGE_COUNT,COLOR,FORWARD);
    }//setImage
}//class
