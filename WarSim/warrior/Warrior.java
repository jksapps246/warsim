package warrior;
import utility.*;
import java.util.Random;

public abstract class Warrior {

    private Namer namer = new Namer();
    protected Random randNum = new Random();
    protected int health; // how alive is the warrior as in health points
    private int maxHealth;
    protected int strength; // used to add a damage bonus to a 'hit'
    protected int dexterity; // how slow/fast the player moves, helps determine the hit chance
    protected int damageTaken;//store last damage taken
    //////////////////////////////////////////
    //to store current stats for special 
    protected int previous_health;
    protected int previous_strength;
    protected int previous_dexterity;

    private String name; // name of the warrior
    private int luck;
    private int misses;//count consecutive misses
    private Boolean special;//is special activate
    private int special_turns;//count duration of special
    private String[] special_colors;//make warior flashy for special

    //////////////////////////////////////////
    //animated info
    private String[][] images;
    private String[][] body;
    private int imageCount;
    private int currentImage;
    private int height;
    private int width;
    private String color;
    private int forward;
    private int left;
    private int right;
 
    public Warrior() { 
        this.name = namer.getName();
        this.resetSpecial();
    } // zero argument constructor

    public Warrior(String name) {
        this.name = name;
        this.resetSpecial();
    } //overloaded constructor

    //////////////////////////////////////////
    // Health Methods
    public void setHealth(int min, int bonus) {
        // actual health range for HUMAN! 125 - 200
        // actual health range for ELF! 150 - 200
        // actual health range for ORC! 100 - 200
        this.health = randNum.nextInt(bonus) + min;
        this.maxHealth = health;
    }//setHealth

    public int getHealth() {
        return health;
    }//getHealth

    //////////////////////////////////////////
    // Strength Methods
    public void setStrength(int min, int bonus) {
        this.strength = randNum.nextInt(bonus) + min;
    }//setStrength

    public int getStrength() {
        return strength;
    }//getStrength

    //////////////////////////////////////////
    // Dexterity Methods
    public void setDexterity(int min, int bonus) {
        this.dexterity = randNum.nextInt(bonus) + min;
    }//setDexterity
    
    public int getDexterity() {
        return dexterity;
    }//getDexterity

    //////////////////////////////////////////
    //special methods
    public void specialOn(){
        this.special = true;
    }//specialOn

    public void setSpecialColors(String[] colors){
        this.special_colors = colors;
    }//setSpecialColors

    public void cycleColors(){
        int temp = 0;
        for(int i = 0; i < this.special_colors.length; i++){
            if (special_colors[i].equals(color)){
                temp = i+1;//store next location
            }
        }
        if(temp < this.special_colors.length){//set next color
            color = special_colors[temp];
        }
        else {
            color = special_colors[0];
        }
    }//cycleColors
    
    public void reduceSpecialTurns(){
        this.special_turns--;
    }//reduceSpecialTurns

    public void increaseMisses(){
        this.misses++;
    }//increaseMisses

    public abstract void activateSpecial();

    public void deactivateSpecial(){
        restorePreviousStats();
        resetSpecial();
    }//deactivateSpecial
    
    public void resetSpecial(){
        this.misses = 0;
        this.special = false;
        this.special_turns = 2;
    }//resetSpecial
    
    public void storePreviousStats(){
        this.previous_strength = strength; // used to add a damage bonus to a 'hit'
        this.previous_dexterity = dexterity;
    }//storePreviousStats

    public void restorePreviousStats(){
        this.strength = previous_strength; // used to add a damage bonus to a 'hit'
        this.dexterity =previous_dexterity;
    }//restorePreviousStats

    public void boostStats(int boost){
        this.strength += (strength * boost)/100; // used to add a damage bonus to a 'hit'
        this.dexterity += (previous_dexterity * boost)/100;
    }//boostStats

    public int getSpecialTurns(){
        return this.special_turns;
    }//getSpecial_turns

    public int getMisses(){
        return this.misses;
    }//getMisses

    public Boolean getSpecial(){
        return this.special;
    }//getspecial

    public void setSpecialStats( Boolean special, int turns, int misses){
        this.special = special;
        this.special_turns = turns;
        this.misses = misses;
    }//setspecial

    ////////////////////////////////////////////
    // reduces the players health based on an incoming
    // damage amount
    public void takeDamage(int amount) {
        this.health -= amount;
        if(this.health < 0) this.health = 0;
    }//takeDamage

    public String getName() {
        return this.name;
    }//getName

    public void setLuck(int val) {
        this.luck = val;
    }//setLuck

    public int getLuck() {
        return this.luck;
    }//getLuck

    ////////////////////////////////////////////
    //Animated Methods
    protected void setInfo(int height, int width, String[][] images,int imageCount, String color,int forward, int left, int right){
        this.height = height;        
        this.width = width;
        this.images = images;
        this.imageCount = imageCount;
        this.currentImage = 0;
        this.setBody(currentImage);
        this.color = color;
        this.forward = forward;
        this.left = left;
        this.right = right;
    }

    public void setColor(String color){
        this.color = color;
    }//setColor

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
    }//setBody

    public void resetHealth(){
        health = maxHealth;
    }//resetHealth

    ////////////////////////////////////////////
    //Animated getters methods
    public String[][] getBody(){
        return body;
    }//getBody
    
    public int getHeight(){
        return height;
    }//getHeight

    public int getMaxHealth(){
        return maxHealth;
    }//getMaxHealth

    public int getWidth(){
        return width;
    }//getWidth

    public int getImageCount(){
        return imageCount;
    }//getImageCount

    public String getColor(){
        return color;
    }//getColor

    public int getPositionForward(){
        return forward;
    }//getPositionForward

    public int getPositionRight(){
        return right;
    }//getPositionRight

    public int getPositionLeft(){
        return left;
    }//getPositionLeft

    ////////////////////////////////////////////
    //overloaded for loading save file
    public void setStrength(int strength) {
        this.strength = strength;
    }//setStrength

    public void setDexterity(int dex) {
        this.dexterity = dex;
    }//setDexterity

    public void setHealth(int health) {
        this.health = health;
    }//setHealth
}//class
