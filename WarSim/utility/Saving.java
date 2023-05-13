package utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Saving {
    private File file;//store information
    private static Scanner vreader;//scan file
    private String filename;//name of file
    private static Validator validator;
    private String player_name;
    private int[] player_Setup;//warrior,weapon,armour
    private int[] player_Stats;//health, strength,dex,dexcost,luck
    private int player_Health;//health
    private String enemy_name;
    private int[] enemy_Setup;//warrior,weapon,armour
    private int[] enemy_Stats;//health, strength,dex,dexcost,luck
    private int enemy_Health;//health
    private int weather;
    private Boolean pSpecial;
    private Boolean eSpecial;
    private int[] player_Special;
    private int[] enemy_Special;
    

    public Saving(){
        filename = "WarSimSaved.txt";//name of file
        validator = new Validator();
        player_name = "";
        player_Setup = new int[3];//warrior,weapon,armour
        player_Stats = new int[2];//health, strength,dex,dexcost,luck
        player_Health = 0;//health
        enemy_name = "";//enemy name
        enemy_Setup = new int[3];//warrior,weapon,armour
        enemy_Stats = new int[2];//health, strength,dex,dexcost,luck
        enemy_Health = 0;//health
        weather = 0;// weather condition
        pSpecial = false;//player special activated
        eSpecial = false;//enemy special activated
        player_Special = new int[2];
        enemy_Special = new int[2];
    
    }//default constructor

    ///////////////////////////////////
    //store methods
    public void storeSetup(String who, String name,int warrior, int armour,int weapon){

        //player setup
        if (who.equals("player")){
            this.player_name = name;
            this.player_Setup[0] = warrior;
            this.player_Setup[1] = armour;
            this.player_Setup[2] = weapon;
        }
        else {//enemy setup
            this.enemy_name = name;
            this.enemy_Setup[0] = warrior;
            this.enemy_Setup[1] = armour;
            this.enemy_Setup[2] = weapon;
        }
    }//storeSetup

    public void storeStats(String who, int[] stats){
        
        //player stats
        if (who.equals("player")){
            this.player_Stats[0] = stats[0];//strength
            this.player_Stats[1] = stats[1];//dex
        }
        else {//enemy stats
            this.enemy_Stats[0] = stats[0];//strength
            this.enemy_Stats[1] = stats[1];//dex
        }
    }//storeStats

    public void storeSpecial(String who, Boolean special, int turns, int misses){
        
        //player special stats
        if (who.equals("player")){
            this.pSpecial  = special;//special
            this.player_Special[0] = turns;//turns
            this.player_Special[1] = misses;//misses
        }
        else {//enemy special stats
            this.eSpecial  = special;//special
            this.enemy_Special[0] = turns;//turns
            this.enemy_Special[1] = misses;//misses
        }
    }//storeSpecial

    public void storeHealth(String who,int health){
        if (who.equals("player"))
            this.player_Health = health;     
        else
            this.enemy_Health = health;
    }//storeHealth

    public void storeWeather(int weather){
        this.weather = weather;
    }//storeWeather

    public Boolean saveData() throws Exception {
        Boolean isSave = false;
        try{
            file = new java.io.File(filename);
            if(file.exists()) {
                //prompt user to overwrite save file
                String choice = validator.validateChoiceString("overwrite",1); 
                if(choice.equals("y")){

                    //delete file
                    if(file.delete()) 
                        isSave = true;//delete file was successful
                    else 
                        return isSave;//delete file was unsuccessful
                }
                else//exit
                    return isSave;
                //System.exit(1);
            }

            // create the file
            PrintWriter vprint = new PrintWriter(file);
            
            //////////////////////////////
            ///Player information
            
            // write to the new file
            vprint.print("weather: " + this.weather);//store weather
            
            //new line
            vprint.println(); 
            vprint.print("pName: " + this.player_name);//store player name
            
            //new line
            vprint.println(); 
            vprint.print("playerSetup:");
            for (int i = 0; i < player_Setup.length; i++){
                vprint.print(" " + player_Setup[i]);//store player setup information
            }

            //new line
            vprint.println(); 
            vprint.print("playerStats: " + this.player_Health);//store player health
            for (int i = 0; i < player_Stats.length; i++){
                vprint.print(" " + player_Stats[i]);//store player stats
            }

            //new line
            vprint.println(); 
            vprint.print("playerSpecial: " + this.pSpecial);//store player special
            for (int i = 0; i < player_Special.length; i++){
                vprint.print(" " + player_Special[i]);//store player special stats
            }

            //////////////////////////////
            ///Enemy information
            // write to the new file
            
            //new line
            vprint.println();
            vprint.print("eName: " + this.enemy_name);//store enemy name
            
            //new line
            vprint.println(); 
            vprint.print("enemySetup:");
            for (int i = 0; i < enemy_Setup.length; i++){
                vprint.print(" " + enemy_Setup[i]);//store enemy setup information
            }

            //new line
            vprint.println(); 
            vprint.print("enemyStats: " + this.enemy_Health);//store enemy health
            for (int i = 0; i < enemy_Stats.length; i++){
                vprint.print(" " + enemy_Stats[i]);//store enemy stats
            }

            //new line
            vprint.println(); 
            vprint.print("enemySpecial: " + this.eSpecial);//store enemy special
            for (int i = 0; i < enemy_Special.length; i++){
                vprint.print(" " + enemy_Special[i]);//store enemy special stats
            }
            
            // close the file
            vprint.close();
            
            isSave = true;//continue
        }
        catch(IOException e){
            System.out.println("Unable to save data...");
        }        
        return isSave;
    } //saveData

    public Boolean loadData()throws Exception{
        Boolean isDone = false;
        try{//load data from file
            file = new File(filename);
            vreader = new Scanner(file);
            String type = "";
        
            // read the file
            while(vreader.hasNextLine()) {
                type = vreader.next();
                switch(type){
                    case "weather:":{//store weather
                        this.weather = vreader.nextInt();
                        break;
                    }
                    case "pName:":{//store player name
                        this.player_name = vreader.nextLine().trim();
                        break;
                    }
                    case "playerSetup:":{//store player setup information
                        for (int i = 0; i < player_Setup.length; i++){
                            player_Setup[i] = vreader.nextInt();
                        }
                        break;
                    }
                    case "playerStats:":{//store player stats information
                        this.player_Health = vreader.nextInt();
                        for (int i = 0; i < player_Stats.length; i++){
                            player_Stats[i] = vreader.nextInt();
                        }
                        break;
                    }
                    case "playerSpecial:":{//store player special stats information
                        this.pSpecial = vreader.nextBoolean();
                        for (int i = 0; i < player_Special.length; i++){
                            player_Special[i] = vreader.nextInt();
                        }
                        break;
                    }
                    case "eName:":{//store enemy name
                        this.enemy_name = vreader.nextLine().trim();
                        break;
                    }
                    case "enemySetup:":{//store enemy setup information
                        for (int i = 0; i < enemy_Setup.length; i++){
                            enemy_Setup[i] = vreader.nextInt();
                        }
                        break;
                    }
                    case "enemyStats:":{//store enemy stats information
                        this.enemy_Health = vreader.nextInt();
                        for (int i = 0; i < enemy_Stats.length; i++){
                            enemy_Stats[i] = vreader.nextInt();
                        }
                        break;
                    }
                    case "enemySpecial:":{//store enemy special stats information
                        this.eSpecial = vreader.nextBoolean();
                        for (int i = 0; i < enemy_Special.length; i++){
                            enemy_Special[i] = vreader.nextInt();
                        }
                        break;
                    }                    
                }//switch
            }//while           
        }//try
        catch(IOException e){
            System.out.println("Unable to load Saved file...");
            isDone = true;//exit
        }

        // close the file
        vreader.close();
        return isDone;
    }//loadData

    public Boolean fileExists(){
        file = new java.io.File(filename);
        if(file.exists()) 
             return true;
        else
            return false;
    }//fileExists

    /////////////////////////////////////////////
    //Store Methods
    public int getPlayerHealth(){
        return player_Health;
    }
    public int[] getPlayerStats(){
        return player_Stats;
    }
    public int[] getPlayerSpecialStats(){
        return player_Special;
    }
    public Boolean getPlayerSpecial(){
        return pSpecial;
    }
    public int[] getPlayerSetup(){
        return player_Setup;
    }
    public String getPlayerName(){
        return player_name;
    }
    public int getEnemyHealth(){
        return enemy_Health;
    }
    public int[] getEnemyStats(){
        return enemy_Stats;
    }
    public int[] getEnemySpecialStats(){
        return enemy_Special;
    }
    public Boolean getEnemySpecial(){
        return eSpecial;
    }
    public int[] getEnemySetup(){
        return enemy_Setup;
    }
    public String getEnemyName(){
        return enemy_name;
    }
    public int getWeather(){
        return weather;
    }

} // class