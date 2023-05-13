package weapon;

public abstract class Weapon {
    
    private int baseDamage;//store base damage
    private int skillRating; //store skill rating    

    //Animated info
    private String[][] images;//store all animated images
    private String[][] body;//store image to use
    private int imageCount;//number of images
    private int currentImage;//which image is being used
    private int height;//height of a single image
    private int width;//width of a single image
    private String color; //color of image
    private String type;//type of weapon attack
    private int rotate;//use to cycle through each image by direction
    private int left;//image position facing left
    private int right;//image position facing right

    public Weapon() { }//default constructor

    //skill and damage methods
    protected void setDamageAmount(int amount) {
        this.baseDamage = amount;
    }//setDamageAmount

    public int getDamageAmount() {
        return baseDamage;
    }//getDamageAmount

    protected void setSkillRating(int amount) {
        this.skillRating = amount;
    }//setSkillRating

    public int getSkillRating() {
        return skillRating;
    }//getSkillRating

    ///////////////////////////////////////////////////////////////////////////    
    // attack type 1 OR 2, player strength
    // player dexterity, armour dex cost
    // weapon skill rating

    // strike method!
    public abstract int strike(int attackType, int strength, 
                               int dexterity, int luck, int dexCost,Boolean special, int eluck); 

    // Animated Methods
    public void setBody(int choice){
        
        //store each string and convert
        char[] oldChar = new char[width];      
        body = new String[height][width];

        //select the image and store into body
        for(int i = 0; i < height; i++){
            oldChar = images[choice][i].toCharArray();
            for(int j = 0; j < width; j++){
                body[i][j] = String.valueOf(oldChar[j]);
            }
        }
        
    }//setBody()

    //set animated information
    protected void setInfo(int height, int width, String[][] images,int imageCount, String color, 
                           String type, int left, int right, int rotate){
        this.height = height;
        this.width = width;
        this.images = images;
        this.imageCount = imageCount;
        this.currentImage = 0;
        this.setBody(currentImage);
        this.color = color;
        this.type = type;
        this.left = left;
        this.right = right;
        this.rotate = rotate;
    }
    //getter methods
    public String[][] getBody(){
        return body;
    }//getBody

    public int getHeight(){
        return height;
    }//getHeight

    public int getWidth(){
        return width;
    }//getWidth

    public int getImageCount(){
        return imageCount;
    }//getImageCount

    public String getColor(){
        return color;
    }//getColor

    public String getType(){
        return type;
    }//getType

    public int getRotate(){
        return rotate;
    }//getRotate

    public int getPositionRight(){
        return right;
    }//getPositionRight

    public int getPositionLeft(){
        return left;
    }//getPositionLeft

}//class