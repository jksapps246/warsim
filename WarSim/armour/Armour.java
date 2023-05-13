package armour;

public abstract class Armour {
    protected int damageReduction;
    protected int mobility;

    //animated info
    private String[][] image;
    private int imageCount;
    private int height;
    private int width;
    private String color;
    private int forward;

    public Armour() { }//default constructor

    ///////////////////////////////////
    // Damage Reduction Methods
    protected void setDamageReduction(int amount) {
        this.damageReduction = amount;
    }//setDamageReduction

    protected void setInfo(int height, int width, String[][] image,int imageCount, String color,int forward){
        this.height = height;        
        this.width = width;
        this.imageCount = imageCount;
        this.color = color;
        this.forward = forward;
        
        //select the image and store into body
        char[] oldChar = new char[width];      
        this.image = new String[height][width];        
        for(int i = 0; i < height; i++){
            oldChar = image[forward][i].toCharArray();
            for(int j = 0; j < width; j++){
                this.image[i][j] = String.valueOf(oldChar[j]);
            }
        }
    }//setInfo

    public int getDamageReduction() {
        return damageReduction;
    }//getDamageReduction

    // Dexterity Cost Methods
    protected void setMobility(int amount) {
        this.mobility = amount;
    }//setDexterityCost

    public int getMobility() {
        return mobility;
    }//getDexterityCost

    public String getColor() {
        return color;
    }
    public int getHeight(){
        return height;
    }//getColor

    public int getWidth(){
        return width;
    }//getWidth

    public int getImageCount(){
        return imageCount;
    }//getImageCount

    public int getPositionForward(){
        return forward;
    }//getPositionForward
}//class
