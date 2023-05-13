
import warrior.*;
import armour.*;
import stage.*;
import weapon.*;
import utility.*;

import java.util.Random;

public class Battle {
    //private static Scanner input = new Scanner(System.in);
    private static Random randNum = new Random();
    private static String who = "player";
    private static boolean gameOver = false;
    private static Printer ink = new Printer();
    private static Validator validator = new Validator();
    private static Saving storeData = new Saving();
    private static int[] playerStats = new int[4];
    private static int[] enemyStats = new int[4];
    private static Namer words = new Namer();
    // player objects
    private static Warrior pWarrior;
    private static Armour pArmour;
    private static Weapon pWeapon;

    // enemy objects
    private static Warrior eWarrior;
    private static Armour eArmour;
    private static Weapon eWeapon;

    //animated objects 
    private static Arena arena1;

    public static void main(String[] args) throws Exception{
        // Welcome 
        ink.welcome(); 
        String setupType = "play";

        //run all setup options
        gameOver = startSetup(setupType);

        //////////////////////////////////////////
        // Main Game Loop //
        while(!gameOver) {

            //players turn section
            if(who.equals("player")) {                
                gameOver = attackTurn(who);
                who = "enemy";//change to enemy
            } 
            else {  //enemies turn section
                gameOver = attackTurn(who);

                who = "player";//change to player
            } // enemy else
        } // while
        ink.printGameOver();//print thank you screen 
    } // main()

    private static Boolean startSetup(String setupType) throws Exception{
        Boolean isDone = false;
        int choice = 0;
        if(setupType.equals("play")){
        
            //continue from save file        
            if (storeData.fileExists()){//load options
                setupType = "load";
                choice = validator.validateChoice(setupType,3);
            }
            else {//play options
                choice = validator.validateChoice(setupType,2);
            }

            //menu choice
            switch(choice){
                case 1:{//new game
                    setupType = "new";
                    break;
                }
                case 2:{//exit
                    ink.clearMessage(3);//clear message
                    return true;//exit game
                }
                case 3:{//load
                    isDone = storeData.loadData();//get all saved data
                    if(!isDone){
                        ink.printLoadingFile(storeData.getPlayerName());
                        setupType = "load";
                    }
                    break;
                }
            }
            
        }

        //new game
        if(setupType.equals("new"))
            ink.printStory();//print story
        playerSetup(setupType);// setup player
        enemySetup(setupType);// setup enemy

        //create arena
        if (setupType.equals("load"))
            createArena(storeData.getWeather());//restore saved arena
        else{//create arena by randum
            int weather = randNum.nextInt(3)+1;
            createArena(weather);//create arena
            storeData.storeWeather(weather);//store weather choice
        }
        startArena();
        return isDone;
    }//startSetup

    //////////////////////////////////////////////
    // Player Setup Section
    private static void playerSetup(String type) throws InterruptedException{
        String who = "player";
        if(type.equals( "load")){
            //restore player stats
            int restoreHealth = storeData.getPlayerHealth();
            int[] restoreSetup = storeData.getPlayerSetup();
            int[] restoreStats = storeData.getPlayerStats();
            Boolean restoreSpecial = storeData.getPlayerSpecial();
            int[] restoreSpecialStats = storeData.getPlayerSpecialStats();

            String name = storeData.getPlayerName();

            //restore warrior
            createWarrior(restoreSetup[0], who,name); 

            //restore armour
            createArmour(restoreSetup[1],who); 

            //restore armour color
            pWarrior.setColor(pArmour.getColor());  

            //restore weapon
            createWeapon(restoreSetup[2], who); 
 
            //restore stats
            pWarrior.setStrength(restoreStats[0]);
            pWarrior.setDexterity(restoreStats[1]);
            pWarrior.setHealth(restoreHealth);

            //restore specials
            //restore specials
            pWarrior.setSpecialStats(restoreSpecial,restoreSpecialStats[0],restoreSpecialStats[1]);

            //set stats for display
            playerStats[0] = pWarrior.getStrength();
            playerStats[1] = pWarrior.getDexterity();
            playerStats[2] = pWarrior.getLuck();
            playerStats[3] = pArmour.getMobility();
        }
        else {
        
            //validate name
            String name = validator.validateChoiceString("name",20);         
            
            //warrior menu
            int wWarrior = validator.validateChoice("warrior",3);
            createWarrior(wWarrior, who,name); 
            
            int wArmour = validator.validateChoice("armour",3);
            createArmour(wArmour, who); 
            //set armour color
            pWarrior.setColor(pArmour.getColor());

            int wWeapon = validator.validateChoice("weapon",4);
            createWeapon(wWeapon, who); 
        
            //set stats for display
            playerStats[0] = pWarrior.getStrength();
            playerStats[1] = pWarrior.getDexterity();
            playerStats[2] = pWarrior.getLuck();
            playerStats[3] = pArmour.getMobility();

            //save player stats
            storeData.storeStats(who, playerStats);
            storeData.storeSetup(who, name, wWarrior, wArmour, wWeapon);

            //player special
            storeData.storeSpecial(who,pWarrior.getSpecial(),pWarrior.getSpecialTurns(),pWarrior.getMisses());
        }
    }//playerSetup

    ///////////////////////////////////////////////
    // Enemy Setup Section
    private static void enemySetup(String type) {
        String who = "enemy";
        if(type.equals( "load")){

            int restoreHealth = storeData.getEnemyHealth();
            int[] restoreSetup = storeData.getEnemySetup();
            int[] restoreStats = storeData.getEnemyStats();
            String name = storeData.getEnemyName();
            Boolean restoreSpecial = storeData.getEnemySpecial();
            int[] restoreSpecialStats = storeData.getEnemySpecialStats();

            //restore warrior
            createWarrior(restoreSetup[0], who,name); 

            //restore armour
            createArmour(restoreSetup[1], who); 

            //restore armour color
            eWarrior.setColor(eArmour.getColor());  

            //restore weapon
            createWeapon(restoreSetup[2], who); 
 
            //restore stats
            eWarrior.setStrength(restoreStats[0]);
            eWarrior.setDexterity(restoreStats[1]);
            eWarrior.setHealth(restoreHealth);

            //restore specials
            pWarrior.setSpecialStats(restoreSpecial,restoreSpecialStats[0],restoreSpecialStats[1]);

            //set stats for display
            enemyStats[0] = eWarrior.getStrength();
            enemyStats[1] = eWarrior.getDexterity();
            enemyStats[2] = eWarrior.getLuck();
            enemyStats[3] = eArmour.getMobility();
    

        }
        else {
            int wWarrior = randNum.nextInt(3) + 1;
            createWarrior(wWarrior, who,"");

            // random number from 1 to 3
            int wArmour = randNum.nextInt(3) + 1;
            createArmour(wArmour, who);

            //set armour color
            eWarrior.setColor(eArmour.getColor());

            // random number from 1 to 3
            int wWeapon = randNum.nextInt(3) + 1;
            createWeapon(wWeapon, who);

            //set stats for display
            enemyStats[0] = eWarrior.getStrength();
            enemyStats[1] = eWarrior.getDexterity();
            enemyStats[2] = eWarrior.getLuck();
            enemyStats[3] = eArmour.getMobility(); 

            //save enemy stats
            storeData.storeStats(who, enemyStats);
            storeData.storeSetup(who, eWarrior.getName(), wWarrior, wArmour, wWeapon);

            //enemy special
            storeData.storeSpecial(who,eWarrior.getSpecial(),eWarrior.getSpecialTurns(),eWarrior.getMisses());
        }
    }//enemySetup

    ///////////////////////////////////////////////
    // Helper functions
    private static void createWarrior(int pick, String who, String name) {
        
        switch(pick) {
            case 1: { // human
                if(who.equals("player"))
                    pWarrior = new Human(name);
                else
                    eWarrior = new Human();
                break;
            }
            case 2: { // elf
                if(who.equals("player"))
                    pWarrior = new Elf(name);
                else
                    eWarrior = new Elf();
                break;
            }
            case 3: { // orc
                if(who.equals("player"))
                    pWarrior = new Orc(name);
                else
                    eWarrior = new Orc();
                break;
            }
        }
    } // createWarrior()

    private static void createArmour(int pick, String who) {
        switch(pick) {
            case 1: { // human
                if(who.equals("player"))
                    pArmour = new Leather();
                else
                    eArmour = new Leather();
                break;
            }
            case 2: { // elf
                if(who.equals("player"))
                    pArmour = new Chainmail();
                else
                    eArmour = new Chainmail();
                break;
            }
            case 3: { // orc
                if(who.equals("player"))
                    pArmour = new Platemail();
                else
                    eArmour = new Platemail();
                break;
            }
        }
    } // createArmour()

    private static void createWeapon(int pick, String who) {
        switch(pick) {
            case 1: { // Sword
                if(who.equals("player"))
                    pWeapon = new Sword();
                else
                    eWeapon = new Sword();
                break;
            }
            case 2: { // Spear
                if(who.equals("player"))
                    pWeapon = new Spear();
                else
                    eWeapon = new Spear();
                break;
            }
            case 3: { // Axe
                if(who.equals("player"))
                    pWeapon = new Axe();
                else
                    eWeapon = new Axe();
                break;
            }
            case 4: { // Bow
                if(who.equals("player"))
                    pWeapon = new Bow();
                else
                    eWeapon = new Bow();
                break;
            }
        }
    } // createArmour()
    
    private static void createArena(int pick){
        switch(pick) {
            case 1: { // Sunny
                arena1 = new Sunny();
                break;
            }
            case 2: { // Rain
                arena1 = new Rain();
                break;
            }
            case 3: { // Windy
                arena1 = new Windy();
                break;
            }
        }
    }//createArena() 

    private static Boolean attackTurn(String who)  throws Exception{

        int attackType = 0;
        int damage = 0;
        String pAttack = "";
        String eAttack = "";
        Boolean isDone = false;

        /////////////////////////////
        // players turn section
        if(who.equals("player")) {

            //get type of attack
            attackType = validator.validateChoice("attack",3);

            //other options
            //Boolean isDone = false;
            while(attackType == 3){
                isDone = otherOptions();//call options menu
                if(isDone)
                    return isDone;//exit game                    
                else 
                    attackType = validator.validateChoice("attack",3);//attack menu            
            }

            if(attackType != 3){

                //get damage from strike attack
                Boolean special= false;

                //activate special
                if(pWarrior.getMisses() >= 3){

                    //ativate
                    pWarrior.activateSpecial();
                    special = pWarrior.getSpecial();

                    //set stats for display
                    playerStats[0] = pWarrior.getStrength();
                    playerStats[1] = pWarrior.getDexterity();
                }

                //reduce turns
                else if(pWarrior.getSpecialTurns() > 0 && pWarrior.getSpecial()){
                    pWarrior.reduceSpecialTurns();
                }

                //deactivate special
                else if(pWarrior.getSpecialTurns() <= 0){
                    pWarrior.deactivateSpecial();
                    special = pWarrior.getSpecial();
                    //set stats for display
                    playerStats[0] = pWarrior.getStrength();
                    playerStats[1] = pWarrior.getDexterity();
                }
                //set damage
                damage = pWeapon.strike(
                    attackType, 
                    pWarrior.getStrength(),
                    pWarrior.getDexterity(),
                    pWarrior.getLuck(),
                    pArmour.getMobility(),
                    special,
                    eWarrior.getLuck() );

                // reduce damage based on oppontents armour
                if (damage > 0) 
                    damage -= eArmour.getDamageReduction();
                else 
                    pWarrior.increaseMisses();//add another miss

                ////////////////////////////////////////////
                //attacks: 
                //  -jump
                //  -attack
                //  -Sattack
                //  -stand

                //player attacks
                if(attackType == 1) 
                    pAttack = "attack";//set attack
                else  
                    pAttack = "Sattack";//set Super attack
                
                //check if attack hits or miss
                if(damage == 0) 
                    eAttack = "jump";//miss
                else 
                    eAttack = "stand"; //hit    

                //animate damage taken
                moveWarriors(pAttack,eAttack, damage);

                // check the status/health of the opponent
                if(eWarrior.getHealth() <= 0) {
                    
                    //play again?
                    isDone = playAgain();//call options menu
                    if(isDone)
                        return isDone;//exit game            
                }           
            }
        }//player turn end

        /////////////////////////////
        // enemy turn section
        else {
            Boolean special= false;
            
            //activate special
            if(eWarrior.getMisses() >= 3){
                eWarrior.activateSpecial();
                special = eWarrior.getSpecial();
                //set stats for display
                enemyStats[0] = eWarrior.getStrength();
                enemyStats[1] = eWarrior.getDexterity(); 
            }
            
            //reduce turns
            else if(eWarrior.getSpecialTurns() > 0 && eWarrior.getSpecial()){
                eWarrior.reduceSpecialTurns();
            }

            //deactivate special
            else if(eWarrior.getSpecialTurns() <= 0){
                eWarrior.deactivateSpecial();
                special = eWarrior.getSpecial();

                //set stats for display
                enemyStats[0] = eWarrior.getStrength();
                enemyStats[1] = eWarrior.getDexterity();
            }
            attackType = randNum.nextInt(2)+1;

            //set damage
            damage = eWeapon.strike(
                    attackType, 
                    eWarrior.getStrength(),
                    eWarrior.getDexterity(),
                    eWarrior.getLuck(),
                    eArmour.getMobility(),
                    special,
                    pWarrior.getLuck() );

            // reduce damage based on oppontents armour
            if (damage > 0)//hit
                damage -= pArmour.getDamageReduction();
            else 
                eWarrior.increaseMisses();//add anothe miss

            /////////////////////////////////////////////////////
            //attacks: jump,attack,Sattack,stand
            //enemy attacks
            if(attackType == 1) 
                eAttack = "attack";//set attack)
            else  
                eAttack = "Sattack";//set Super attack
            
            //check if attack hits or miss
            if(damage == 0) 
                pAttack = "jump";//miss
            else 
                pAttack = "stand"; //hit   

            //animate damage taken
            moveWarriors(pAttack,eAttack, damage);

            // check the status/health of the opponent
            if(pWarrior.getHealth() <= 0) {
                ink.printWinner(eWarrior.getName());
                
                //play again?
                isDone = playAgain();//call options menu
                if(isDone)
                    return isDone;//exit game
            }   
        }//enemy turn end      

        //pause screen for 5 seconds to view results
        Thread.sleep(5000);

        return gameOver;//exit game
    }//attackTurn

    private static Boolean otherOptions() throws Exception{//save, exit or continue
        Boolean isDone = false;

        //other options      
        int saveType = validator.validateChoice("options",4);
        switch(saveType){
            case 1: {//save and continue
                //store health
                storeData.storeHealth("player", pWarrior.getHealth());
                storeData.storeHealth("enemy", eWarrior.getHealth());
                if(storeData.saveData())//save data
                    ink.printSavingFile();
                break;
            }
            case 2: {//save and exit
                storeData.storeHealth("player", pWarrior.getHealth());
                storeData.storeHealth("enemy", eWarrior.getHealth());
                if(storeData.saveData())//save data
                    ink.printSavingFile();
                    isDone = true;
            }
            case 3: {//exit without saving
                isDone = true;
            }
            case 4: {//cancel
                break;
            }
        }//switch
        return isDone;
    }//otherOptions

    private static Boolean playAgain() throws Exception{
        Boolean isDone = false;
        int play_again = 0;
        if(storeData.fileExists())//file exist
            //play again with load option
            play_again = validator.validateChoice("playAgainLoad", 4);
        else//play again without load option
            play_again = validator.validateChoice("playAgain", 3);
        
        switch(play_again){
            case 1:{//Replay
                //reset health
                pWarrior.resetHealth();
                eWarrior.resetHealth();

                //reset special and misses
                pWarrior.deactivateSpecial();
                eWarrior.deactivateSpecial();
                startArena();//start arena
                break;
            }
            case 2:{//New Game
                ink.welcome();//welcome screen
                isDone = startSetup("new");//run all setup options                
                break;
            }            
            case 3:{//Exit
                ink.clearMessage(3);
                isDone = true;//gameover
                break;
            }
            case 4:{//Load Save file
                isDone = storeData.loadData();//get data from file
                if(!isDone){//get all saved data
                    ink.printLoadingFile(storeData.getPlayerName());//animate loading
                    isDone = startSetup("load");// load saved data 
                }                             
                break;
            }
        }
        return isDone;        
    }//playAgain

    ///////////////////////////////////////////////
    // Animation functions
    private static void setWarriorPositions(int pcImage,int pwImage,int paImage,int ecImage,int ewImage,int eaImage,
    int pcRowPos, int pwRowPos, int paRowPos,int pcColPos, int pwColPos, int paColPos,
    int ecRowPos, int ewRowPos, int eaRowPos,int ecColPos, int ewColPos, int eaColPos){

        //set arena
        arena1.setPattern();
        //////////////////////////////////////////////////////////////////
        //player
        /////////////////////////////////////////////////////////////////
        //ammo action and location
        if (pWeapon.getType() == "Propel") {

            pWeapon.setBody(paImage);
            arena1.addCharacter(pWeapon.getBody(),
                ((arena1.getRow()-pWarrior.getHeight())-
                    (pWeapon.getHeight()/2))+paRowPos,
                        pWarrior.getWidth() + paColPos,
                                pWeapon.getColor());
        }
        //set weapon and location
        pWeapon.setBody(pwImage);
        arena1.addCharacter(pWeapon.getBody(),
            ((arena1.getRow()-pWarrior.getHeight())-
                (pWeapon.getHeight()/2)) + pwRowPos,
                    pWarrior.getWidth() + pwColPos,
                                pWeapon.getColor());
        
        if(pWarrior.getSpecial())
            pWarrior.cycleColors();//activate special colors
        else pWarrior.setColor(pArmour.getColor());//reset color
        //warrior and location
        pWarrior.setBody(pcImage);
        arena1.addCharacter(pWarrior.getBody(),

        arena1.getRow()-pWarrior.getHeight()+pcRowPos,pcColPos,pWarrior.getColor());

        //////////////////////////////////////////////////////////////////
        //enemy
        /////////////////////////////////////////////////////////////////

        //ammo action and location
        if (eWeapon.getType() == "Propel") {
            eWeapon.setBody(eaImage);
            arena1.addCharacter(eWeapon.getBody(),
                ((arena1.getRow()-eWarrior.getHeight())+
                    (eWeapon.getHeight()/2))-eaRowPos,
                        (arena1.getCol() - eWarrior.getWidth() - eWeapon.getWidth()) - eaColPos,
                                eWeapon.getColor());
        }

        //set weapon and location
        eWeapon.setBody(ewImage);
        arena1.addCharacter(eWeapon.getBody(),
            ((arena1.getRow()-eWarrior.getHeight())+
                (eWarrior.getHeight()/2))-ewRowPos,//72-5+(5-1)
                    (arena1.getCol() - eWarrior.getWidth() - eWeapon.getWidth()) - ewColPos,
                            eWeapon.getColor());

        if(eWarrior.getSpecial())
            eWarrior.cycleColors();//activate special colors
        else eWarrior.setColor(eArmour.getColor());//reset color
        
        //warrior and location
        eWarrior.setBody(ecImage);
        arena1.addCharacter(eWarrior.getBody(),arena1.getRow()-eWarrior.getHeight()-ecRowPos,
            arena1.getCol() - eWarrior.getWidth() - ecColPos,eWarrior.getColor());
    }//setCharacterPositions()

    private static void Animate(double seconds,int frameRate) throws InterruptedException{
            
        //allow the screen to animate 
        long startTime = System.currentTimeMillis(); //fetch starting time
        
        //run by amount of seconds
        while(false||(System.currentTimeMillis()-startTime)<(seconds * 1000))
        {   
            //move cursor to begin of screen        
            Thread.sleep(frameRate);//pause screen by amount of seconds
            System.out.print("\033[H");
            System.out.flush();
            
            //set background   
            String background = arena1.displayArena(pWarrior.getHealth(),
                                                   pWarrior.getMaxHealth(),
                                                   eWarrior.getHealth(),
                                                   eWarrior.getMaxHealth(),
                                                   playerStats,
                                                   enemyStats);
            
            //display arena
            System.out.println(background);
        }
    }//animate()
    
    private static void startArena() throws InterruptedException{

        //int imageCount = 0;
        double seconds = 0.1;
        int frameRate = 50;
        int moveCount = 15;
        int speed = 1;
        int cColPos = 4 - moveCount;//character position
        int wColPos = 4 -moveCount;//weapon position
        int aColPos = 1 - moveCount;//ammo position


        //////////////////////////////////////////////////////////////////
        //player defaults position
        int pcColPos = cColPos;//character position
        int pwColPos = wColPos;//weapon position
        int paColPos = aColPos;//ammo position

        int pcRowPos = 0;//character position
        int pwRowPos = 1;//weapon position
        int paRowPos = 1;//ammo position

        int pcImage = pWarrior.getPositionLeft();
        int pwImage = pWeapon.getPositionLeft();
        int paImage = pwImage;
        //////////////////////////////////////////////////////////////////
        //enemy defaults position

        int ecColPos = cColPos;//character position
        int ewColPos = wColPos;//weapon position
        int eaColPos = aColPos;//ammo position

        int ecRowPos = 0;//character position
        int ewRowPos = 2;//weapon position
        int eaRowPos = 1;//ammo position

        int ecImage = eWarrior.getPositionRight();
        int ewImage = eWeapon.getPositionRight();
        int eaImage = ewImage;//ammo arrow facing left    

        for(int i = 0; i< moveCount; i++) {//move images on screen
                            
        
        
            //move all
            pcColPos+=speed;
            pwColPos+=speed;
            paColPos+=speed;  

            ecColPos+=speed;
            ewColPos+=speed;
            eaColPos+=speed;   
            //move characters
            setWarriorPositions(
                pcImage,pwImage,paImage,ecImage,ewImage,eaImage,                    
                pcRowPos,pwRowPos,paRowPos,pcColPos,pwColPos,paColPos,
                ecRowPos,ewRowPos,eaRowPos,ecColPos,ewColPos,eaColPos
            );
            Animate(seconds,frameRate);
            
        }
    }
    
    private static void moveWarriors(String pAction, String eAction, int damage)throws InterruptedException{
        
        //int imageCount = 0;
        double seconds = 0.1;
        int frameRate = 50;
        int cycles = 1;
        int moveCount = 16;
        boolean tookdamage = false;

        //////////////////////////////////////////////////////////////////
        //player
        /////////////////////////////////////////////////////////////////
        //defaults
        String pmove = "forward";
        int pspeed = 6;
        int pmoveCount = moveCount;
        int pcycles = cycles;
        String pwhat = "ammo";

        int pcColPos = 4;//character position
        int pwColPos = 4;//weapon position
        int paColPos = 1;//ammo position

        int pcRowPos = 0;//character position
        int pwRowPos = 1;//weapon position
        int paRowPos = 1;//ammo position

        int pcImage = pWarrior.getPositionLeft();
        int pwImage = pWeapon.getPositionLeft();
        int paImage;//ammo arrow facing left
        if(pWeapon.getType() == "Propel"){
            if(pAction == "Sattack") paImage = pWeapon.getPositionLeft()+2;
            else paImage = pWeapon.getPositionLeft()+1;
        }
        else paImage = eWeapon.getPositionLeft();
        
        //set moves
        if (pAction == "attack" || pAction == "Sattack"){
            pmove = "forward";//which direction
            if (pWeapon.getType() == "Propel") {
                pwhat = "ammo";//which image moves
                pcycles = 1;// 1 = forward, 2 = foward and back 
                pmoveCount = 16;//number of moves per cycle
                pspeed = 6;//distance of movement
            }
            else { 
                pwhat = "character";
                pcycles = 2;// 1 = forward, 2 = foward and back 
                pmoveCount = 15;//number of moves per cycle
                pspeed = 3;//distance of movement
            }
        }
        else if (pAction == "jump"){
            pmove = "up";//which direction
            pwhat = "character";//which image moves
            pmoveCount = 5;//number of moves per cycle
            pcycles = 2;// 1 = forward/up, 2 = foward/up and back/down
            pspeed = 1;//distance of each movement
        }

        //////////////////////////////////////////////////////////////////
        //enemy
        /////////////////////////////////////////////////////////////////
        
        //defaults
        String emove = "forward";
        int espeed = 6;
        int emoveCount = moveCount;
        int ecycles = cycles;
        String ewhat = "character";

        int ecColPos = 4;//character position
        int ewColPos = 4;//weapon position
        int eaColPos = 1;//ammo position

        int ecRowPos = 0;//character position
        int ewRowPos = 2;//weapon position
        int eaRowPos = 1;//ammo position

        int ecImage = eWarrior.getPositionRight();
        int ewImage = eWeapon.getPositionRight();
        int eaImage;//ammo arrow facing left

        //propel images
        if(eWeapon.getType() == "Propel"){
            if(eAction == "Sattack") eaImage = eWeapon.getPositionRight()+2;
            else eaImage = eWeapon.getPositionRight()+1;
        }
        else eaImage = eWeapon.getPositionRight();

        //set moves
        if (eAction == "attack"|| eAction == "Sattack"){
            emove = "forward";//which direction
            if (eWeapon.getType() == "Propel") {
                ewhat = "ammo";//which image moves
                //ecycles = 1;// 1 = forward, 2 = foward and back 
                //emoveCount = 16;//number of moves per cycle
                espeed = 6;//distance of movement
            }
            else { 
                ewhat = "character";
                ecycles = 2;// 1 = forward, 2 = foward and back 
                //emoveCount = 15;//number of moves per cycle
                espeed = 3;//distance of movement
            }
        }
        else if (eAction == "jump"){
            emove = "up";//which direction
            ewhat = "character";//which image moves
            emoveCount = 5;//number of moves per cycle
            ecycles = 2;// 1 = forward/up, 2 = foward/up and back/down
            espeed = 1;//distance of each movement
        }
        ////////////////////////////////////////////////////////////////////

        //set highest cycles
        if(pcycles<ecycles)
            cycles = ecycles;
         else  cycles = pcycles;

        //set hightest moves
        if(pmoveCount<emoveCount)
            moveCount = emoveCount;
        else moveCount = pmoveCount;

        //set characters starting postion
        setWarriorPositions(
            pcImage,pwImage,paImage,ecImage,ewImage,eaImage,                    
            pcRowPos,pwRowPos,paRowPos,pcColPos,pwColPos,paColPos,
            ecRowPos,ewRowPos,eaRowPos,ecColPos,ewColPos,eaColPos
        );

        for(int j = 0; j< cycles; j++) {  //number of directional moves
            for(int i = 0; i< moveCount; i++) {//move images on screen
                                
                /////////////////////////////////
                //player
                /////////////////////////////////
                //change direction
                //forward or/and back direction
                if (pAction == "attack"|| pAction == "Sattack"){
                    if (pmove == "forward") {
                        if (pwhat == "ammo") paColPos+=pspeed;//move ammo                   
                        
                        else if (pwhat == "character") {
                            //move all
                            pcColPos+=pspeed;
                            pwColPos+=pspeed;
                            paColPos+=pspeed;  
                        }
                    } 
                    else if (pmove == "backwards"){
                        if (pwhat == "ammo") paColPos-=pspeed;//move ammo
                        else if (pwhat == "character") {
                            //move all
                            pcColPos-=pspeed;
                            pwColPos-=pspeed;
                            paColPos-=pspeed;  
                        }
                    }
                }
                
                //up or/and down direction
                else if(pAction == "jump"){
                    if (pmove == "up") {
                        if(i <= pmoveCount) {
                            //move all up
                            paRowPos-=pspeed; 
                            pwRowPos-=pspeed;
                            pcRowPos-=pspeed;
                        }
                    } 
                    else if (pmove == "down"){
                        if(i <= pmoveCount) {
                            //move all down
                            paRowPos+=pspeed;
                            pwRowPos+=pspeed;
                            pcRowPos+=pspeed;
                        }
                    }
                }

                /////////////////////////////////
                //enemy
                /////////////////////////////////
                //change direction
                //forward or/and back direction
                if (eAction == "attack"|| eAction == "Sattack"){
                    if (emove == "forward") {
                        if (ewhat == "ammo") eaColPos+=espeed;//move ammo                   
                        
                        else if (ewhat == "character") {
                            //move all
                            ecColPos+=espeed;
                            ewColPos+=espeed;
                            eaColPos+=espeed;  
                        }
                    } 
                    else if (emove == "backwards"){
                        if (ewhat == "ammo") eaColPos-=espeed;//move ammo
                        else if (ewhat == "character") {
                            //move all
                            ecColPos-=espeed;
                            ewColPos-=espeed;
                            eaColPos-=espeed;  
                        }
                    }
                }
                //up or/and down direction
                else if(eAction == "jump"){
                    if (emove == "up") {
                        if(i <= emoveCount) {
                            //set new position going up
                            eaRowPos+=espeed; 
                            ewRowPos+=espeed;
                            ecRowPos+=espeed;
                        }
                    } 
                    else if (emove == "down"){
                        if(i <= emoveCount) {
                            //set new position going down
                            eaRowPos-=espeed;
                            ewRowPos-=espeed;
                            ecRowPos-=espeed;
                        }
                    }
                }
                //////////////////////////////////////
                //animate image here*
                //character.nextImage();

                
                //move characters
                setWarriorPositions(
                    pcImage,pwImage,paImage,ecImage,ewImage,eaImage,                    
                    pcRowPos,pwRowPos,paRowPos,pcColPos,pwColPos,paColPos,
                    ecRowPos,ewRowPos,eaRowPos,ecColPos,ewColPos,eaColPos
                );
                Animate(seconds,frameRate);
                
            }
            //initiate attack variables
            int attackCount = 0;
            int attackCycle = 0;
            int ptemp = pwImage;
            int etemp = ewImage;
            String attackType ="";
            
            //set action counts
            if( pAction.equals("Sattack")){//player super attack
                attackCycle = 5;
                attackCount = pWeapon.getRotate();
                attackType = pWeapon.getType();
            }
            else if( eAction.equals("Sattack")){//enemy super attack
                attackCycle = 5;
                attackCount = eWeapon.getRotate();
                attackType = eWeapon.getType();
            }  
            
            if( pAction.equals("attack")){//player basic attack
                attackCycle = 2;
                attackCount = pWeapon.getRotate();
                attackType = pWeapon.getType();
            } 
            else if( eAction.equals("attack")){//enemy basic attack
                attackCycle = 2;
                attackCount = eWeapon.getRotate();
                attackType = eWeapon.getType();
            } 
            
            if (tookdamage == false ) {//the attack was already made
                //animate strike attack
                if (attackType == "Strike"){
                    for(int k = 0; k<attackCycle; k++){
                        for(int i = 0; i < attackCount; i++){
                            
                            //set all animated positions
                            setWarriorPositions(
                            pcImage,ptemp,paImage,ecImage,etemp,eaImage,                    
                            pcRowPos,pwRowPos,paRowPos,pcColPos,pwColPos,paColPos,
                            ecRowPos,ewRowPos,eaRowPos,ecColPos,ewColPos,eaColPos
                            );
                            Animate(seconds,frameRate);//run animation
                            //set next image
                            if (pAction.equals("Sattack") || pAction.equals("attack")) ptemp++;
                            if (eAction.equals("Sattack") || eAction.equals("attack")) etemp++;
                        }
                        ptemp = pwImage;//reset starting image
                        etemp = ewImage;//reset starting image
                    }
                    if (pAction.equals("Sattack")) 
                        pAction = "attack";//reset
                    if (eAction.equals("Sattack")) 
                        eAction = "attack";//reset
                }            

                //set damage
                if(eAction.equals("stand")) 
                    eWarrior.takeDamage(damage);
                if(pAction.equals("stand")) 
                    pWarrior.takeDamage(damage);
                
                if(!tookdamage){//print action taken
                    String word = "";
                    if(eAction.equals("stand") || eAction.equals("jump")) {

                        //get color commentary
                        if(pWeapon.getType().equals("Propel"))
                            word = words.getPropelWords();//add propel words
                        else 
                            word = words.getStrikeWords();// strike words

                        if(damage == 0)//print miss results                            
                            ink.printStrikeResult(pWarrior.getName(), damage, word);
                        else//print strike results
                            ink.printStrikeResult(pWarrior.getName(), damage, word);
                    }
                    else if(pAction.equals("stand") || pAction.equals("jump")) {

                        //get color commentary
                        if(eWeapon.getType().equals("Propel"))
                            word = words.getPropelWords();//add propel words
                        else 
                            word = words.getStrikeWords();// strike words
                        
                        if(damage == 0)//print miss results                            
                            ink.printStrikeResult(eWarrior.getName(), damage, word);
                        else//print strike results
                            ink.printStrikeResult(eWarrior.getName(), damage, word);
                    }
                    
                }
                tookdamage = true;
            }

            //change direction
            if (!pwhat.equals("ammo")) {//player direction
                if (pmove.equals("forward")) pmove = "backwards";
                else if (pmove.equals("up")) pmove = "down";
            }
            if (!ewhat.equals("ammo")) {//enemy direction
                if (emove.equals("forward")) emove = "backwards";
                else if (emove.equals("up")) emove = "down";
            }
        }    
    }//moveCharacter()
} // class