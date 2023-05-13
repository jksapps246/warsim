
package utility;

public class Printer {

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
  
    // Declaring the color a Custom declaration
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";    
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Printer() {}//deault constructor

    public void welcome() throws InterruptedException{//welcome screen
        clearScreen();//clear screen
        
        String welcome = "╔═════════════════════════════════════════════════════════════════════════════╗\n";
        welcome = welcome + "║                                                                       "+ANSI_RED+"____  "+ANSI_RESET+"║\n";
        welcome = welcome + "║           .---.   "+ANSI_GREEN+",---,"+ANSI_RESET+"       ,-.----.    "+ANSI_YELLOW+".--.--."+ANSI_RESET+"      ,---,        "+ANSI_RED+",'  , `."+ANSI_RESET+"║\n"; 
        welcome = welcome + "║          /. ./|  "+ANSI_GREEN+"\'  .' \\"+ANSI_RESET+"      \\    /  \\  "+ANSI_YELLOW+"/  /    \'"+ANSI_RESET+". ,`--.\' |     "+ANSI_RED+",-+-,.\' _ |"+ANSI_RESET+"║\n"; 
        welcome = welcome + "║      .--\'.  \' ; "+ANSI_GREEN+"/  ;    \'."+ANSI_RESET+"    ;   :    \\"+ANSI_YELLOW+"|  :  /`. /"+ANSI_RESET+" |   :  :  "+ANSI_RED+",-+-. ;   , ||"+ANSI_RESET+"║\n"; 
        welcome = welcome + "║     /__./ \\ : |"+ANSI_GREEN+":  :       \\"+ANSI_RESET+"   |   | .\\ :"+ANSI_YELLOW+";  |  |--`"+ANSI_RESET+"  :   |  \' "+ANSI_RED+",--.\'|\'   |  ;|"+ANSI_RESET+"║\n"; 
        welcome = welcome + "║ .--\'.  \'   \\\' ."+ANSI_GREEN+":  |   /\\   \\"+ANSI_RESET+"  .   : |: |"+ANSI_YELLOW+"|  :  ;_"+ANSI_RESET+"    |   :  |"+ANSI_RED+"|   |  ,\', |  \':"+ANSI_RESET+"║\n"; 
        welcome = welcome + "║/___/ \\ |    \' \'"+ANSI_GREEN+"|  :  \' ;.   :"+ANSI_RESET+" |   |  \\ : "+ANSI_YELLOW+"\\  \\    `."+ANSI_RESET+" \'   \'  ;"+ANSI_RED+"|   | /  | |  ||"+ANSI_RESET+"║\n"; 
        welcome = welcome + "║;   \\  \\;      :"+ANSI_GREEN+"|  |  ;/  \\   \\"+ANSI_RESET+"|   : .  /  "+ANSI_YELLOW+"`----.   \\"+ANSI_RESET+"|   |  |"+ANSI_RED+"\'   | :  | :  |,"+ANSI_RESET+"║\n"; 
        welcome = welcome + "║ \\   ;  `      |"+ANSI_GREEN+"\'  :  | \\  \\ ,\'"+ANSI_RESET+";   | |  \\  "+ANSI_YELLOW+"__ \\  \\  |"+ANSI_RESET+"\'   :  ;"+ANSI_RED+";   . |  ; |--\' "+ANSI_RESET+"║\n"; 
        welcome = welcome + "║  .   \\    .\\  ;"+ANSI_GREEN+"|  |  \'  \'--\'  "+ANSI_RESET+"|   | ;\\  \\"+ANSI_YELLOW+"/  /`--\'  /"+ANSI_RESET+"|   |  \'"+ANSI_RED+"|   : |  | ,    "+ANSI_RESET+"║\n"; 
        welcome = welcome + "║   \\   \\   \' \\ |"+ANSI_GREEN+"|  :  :        "+ANSI_RESET+":   \' | \\."+ANSI_YELLOW+"\'--\'.     /"+ANSI_RESET+" \'   :  |"+ANSI_RED+"|   : '  |/     "+ANSI_RESET+"║\n"; 
        welcome = welcome + "║    :   \'  |--\" "+ANSI_GREEN+"|  | ,\'        "+ANSI_RESET+":   : :-\'   "+ANSI_YELLOW+"`--\'---\'"+ANSI_RESET+"  ;   |.\' "+ANSI_RED+";   | |`-\'      "+ANSI_RESET+"║\n"; 
        welcome = welcome + "║     \\   \\ ;    "+ANSI_GREEN+"`--\'\'          "+ANSI_RESET+"|   |.\'               \'---\'   "+ANSI_RED+"|   ;/          "+ANSI_RESET+"║\n"; 
        welcome = welcome + "║      \'---\"                    `---\'                         "+ANSI_RED+"\'---\'           "+ANSI_RESET+"║\n";
        welcome = welcome + "╚╦════════════════════════════════════════════════════════════════════════════╝\n";
        
        //slow print welcome screen
        slowPrint(welcome,3);
        System.out.println(" ║  Welcome to WarSIM!\n\n\n");
    } // welcome()

    public void printStory() throws InterruptedException{//print story
        clearMessage(3);//clear last 3 lines
        String[] story = new String[4];
        story[0] = " ║ In acient times, countless battles took place between sixteen difference races...\n"+
                   " ║ Each killing the next for the sake of world domination...\n";
        story[1] = " ║ After 30 years of endless battles only three races remained.\n"+
                   " ║ The Humans, The Elf and The Orc.\n"; 
        story[2] = " ║ In an attempt to end the endless war. The Strongest of the three remaining races\n"+
                   " ║ will battle to the death and the victor will be the one true WARRIOR KING of the world.\n";
        story[3] = " ║ You will be one of those warriors battling to be the WARRIOR KING.\n"+
                   " ║ Put your warrior face on and head to the battle field.\n\n\n\n";
        
        //slow print story
        for(int i = 0; i < story.length; i++) {
            slowPrint(story[i],100);
            Thread.sleep(5000);//pause for 5 seconds
            clearMessage(2);//clear last 2 line
        }
    }//printStory

    public void printWarriorMenu() throws InterruptedException{//print warrior menu
        clearScreen();//clear screen
       
        String warriors = "╔═════════════════════════════════════════════════════════════════════════════╗\n";
        warriors = warriors + "║                  Human                 Elf                 Org              ║\n"; 
        warriors = warriors + "║                   __                   ,,                   ππ              ║\n"; 
        warriors = warriors + "║                  qôóp                <(--)>               ç(òó)?            ║\n"; 
        warriors = warriors + "║                //|~~|\\\\              //||\\\\              (/{..}\\)           ║\n"; 
        warriors = warriors + "║                √  ≡≡  √              - == -              W  ╔╗  W           ║\n"; 
        warriors = warriors + "║                  [][]                 [][]                 [][]             ║\n"; 
        warriors = warriors + "║                                                                             ║\n"; 
        warriors = warriors + "║ Health:      125 Bonus(75)       150 Bonus(25)       100 Bonus(75)          ║\n"; 
        warriors = warriors + "║ Strength:    50 Bonus(150)        55 Bonus(125)       75 Bonus(200)         ║\n"; 
        warriors = warriors + "║ Dexterity:   50 Bonus(100)        45 Bonus(125)       25 Bonus(50)          ║\n";
        warriors = warriors + "║ Specials:    40% Stat Boost       30% Stat Boost      30% Stat Boost        ║\n";
        warriors = warriors + "║              20% heal             30% heal            40% heal              ║\n";
        warriors = warriors + "║                                                                             ║\n";
        warriors = warriors + "║  All specials last for 2 turns (activates after every 3 consecutive misses) ║\n"; 
        warriors = warriors + "╚╦════════════════════════════════════════════════════════════════════════════╝\n";
        
        //slow print menu
        slowPrint(warriors,5);
        System.out.println(" ║ Pick a warrior!");
        System.out.println(" ║ 1) Human  2) Elf  3) Orc");
        System.out.printf(" ╚═══╣ "); 
    }//printWarriorMenu

    public void printArmourMenu() throws InterruptedException{//print armour menu
        clearScreen();//clear screen
       
        String armours = "╔═════════════════════════════════════════════════════════════════════════════╗\n";
        armours = armours + "║                                                                             ║\n";
        armours = armours + "║                 Leather             Chainmail           Platemail           ║\n"; 
        armours = armours + "║            "+ANSI_GREEN+"     __   __       "+ANSI_RED+"       __   __        "+ANSI_BLUE+"     __   __            "+ANSI_RESET+"║\n"; 
        armours = armours + "║            "+ANSI_GREEN+"    / -`-'- \\     "+ANSI_RED+"       / ╬---╬ \\     "+ANSI_BLUE+"      / ┌┼-┼┐ \\           "+ANSI_RESET+"║\n"; 
        armours = armours + "║            "+ANSI_GREEN+"   /_|-----|_\\    "+ANSI_RED+"      /╦|╫╫╫╫╫|╦\\    "+ANSI_BLUE+"     /┬|┼┼┼┼┼|┬\\          "+ANSI_RESET+"║\n"; 
        armours = armours + "║            "+ANSI_GREEN+"     |-----|       "+ANSI_RED+"     ╚╩|╫╫╫╫╫|╩╝      "+ANSI_BLUE+"   └┴|┼┼┼┼┼|┴┘          "+ANSI_RESET+"║\n"; 
        armours = armours + "║            "+ANSI_GREEN+"     |=====|       "+ANSI_RED+"       |╫╫╫╫╫|        "+ANSI_BLUE+"     |┼┼┼┼┼|            "+ANSI_RESET+"║\n"; 
        armours = armours + "║            "+ANSI_GREEN+"     |_____|       "+ANSI_RED+"       |╫╫╫╫╫|        "+ANSI_BLUE+"     |┼┼┼┼┼|            "+ANSI_RESET+"║\n"; 
	    armours = armours + "║                                                                             ║\n";
	    armours = armours + "║                                                                             ║\n";
        armours = armours + "║ Defence:          45                   50                 55                ║\n"; 
        armours = armours + "║ Mobility:         55                   50                 45                ║\n"; 
        armours = armours + "║ Color:            Green                Red                Blue              ║\n";
	    armours = armours + "║                                                                             ║\n";
        armours = armours + "╚╦════════════════════════════════════════════════════════════════════════════╝\n";

        //slow print menu
        slowPrint(armours,5);
        System.out.println(" ║ Pick your armour!");
        System.out.println(" ║ 1) Leather 2) Chainmail 3) Platemail");
        System.out.printf(" ╚═══╣ ");
    }

    public void printWeaponMenu() throws InterruptedException{
        clearScreen();//clear screen
       
        String weapons = "╔═════════════════════════════════════════════════════════════════════════════╗\n";
        weapons = weapons + "║                                                                             ║\n";
        weapons = weapons + "║                                                                             ║\n"; 
        weapons = weapons + "║               Sword            Spear            Axe             Bow         ║\n"; 
        weapons = weapons + "║                                                                             ║\n"; 
        weapons = weapons + "║          "+ANSI_RED+"      /\\                               "+ANSI_BLUE+"╠<)              "+ANSI_GREEN+"+          "+ANSI_RESET+"║\n"; 
        weapons = weapons + "║          "+ANSI_RED+"      ║║              "+ANSI_YELLOW+"====>            "+ANSI_BLUE+"║                "+ANSI_GREEN+"|}>        "+ANSI_RESET+"║\n"; 
        weapons = weapons + "║          "+ANSI_RED+"      ╬╬                               "+ANSI_BLUE+"╩                "+ANSI_GREEN+"+          "+ANSI_RESET+"║\n"; 
	    weapons = weapons + "║                                                                             ║\n";
        weapons = weapons + "║ Damage:        55              45              55               50          ║\n"; 
        weapons = weapons + "║ Skill:         45              55              45               50          ║\n"; 
        weapons = weapons + "║ Color:         Red             Yellow          Blue             Green       ║\n";
	    weapons = weapons + "║                                                                             ║\n";
	    weapons = weapons + "║                                                                             ║\n";
	    weapons = weapons + "║                                                                             ║\n";
        weapons = weapons + "╚╦════════════════════════════════════════════════════════════════════════════╝\n";
        slowPrint(weapons,5);
        System.out.println(" ║ Pick your weapon!");
        System.out.println(" ║ 1) Sword  2) Spear  3) Axe  4) Bow");
        System.out.printf(" ╚═══╣ ");
    }//printWeaponMenu

    public void printAttackMenu() {
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║ Pick your attack!");
        System.out.println(" ║ 1) Attack  2) Strong Attack 3) Options");
        System.out.printf(" ╚═══╣ ");
    }//printAttackMenu

    public void printStrikeResult(String who, int damage, String words) {
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║");
        if (damage == 0) {
            System.out.println(" ║ " + who + " " + words + " and missed!\n\n" );
        }
        else System.out.println(" ║ " + who + " " + words + " and hits for " + damage + " damage!\n\n");
    }//printStrikeResult

    public void printWinner(String who) throws Exception {
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║ "+ who + " is the victor!\n\n\n");
        Thread.sleep(5000);
    }//printWinner

    public void printGameOver() throws Exception{//print thank you
        clearScreen();// clear screen

        String exit = "╔═════════════════════════════════════════════════════════════════════════════╗\n";
        exit = exit + "║                                                                             ║\n";        
        exit = exit + "║ "+ANSI_PURPLE+" .---..              .      .   .                                           "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   |  |              |       \\ /                   "+ANSI_YELLOW+"     __//                "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   |  |--. .-.  .--. |.-.     : .-. .  .            "+ANSI_YELLOW+"   /.__.\\               "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   |  |  |(   ) |  | |-.\'     |(   )|  |           "+ANSI_YELLOW+"    \\ \\/ /  "+ANSI_RED+"Bye Bye!     "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   \'  \'  `-`-\'`-\'  `-\'  `-    \' `-\' `--`-    "+ANSI_YELLOW+"       \'__/    \\               "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   .---.          .--. .                            "+ANSI_YELLOW+" \\-      )              "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   |              |   )|            o               "+ANSI_YELLOW+"  \\_____/               "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   |--- .-. .--.  |--\' | .-.  .  .  .  .--. .-..    ____"+ANSI_YELLOW+"|"+ANSI_PURPLE+"_"+ANSI_YELLOW+"|"+ANSI_PURPLE+"______           "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   |   (   )|     |    |(   ) |  |  |  |  |(   |    "+ANSI_YELLOW+"    \" \"                 "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"   \'    `-\' \'     \'    `-`-'`-`--|-\' `-\'  `-`-`|                            "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"                               ;          ._.\'                              "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_PURPLE+"                           `-\'                                              "+ANSI_RESET+"║\n";
        exit = exit + "║ "+ANSI_YELLOW+"                                                             By John King   "+ANSI_RESET+"║\n";  
        exit = exit + "╚═════════════════════════════════════════════════════════════════════════════╝\n";
        slowPrint(exit,3);// slow print
    }//printGameOver

    public void printNameRequest(int size) {// print name request
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║");
        System.out.println(" ║ Enter your warrior name?(Max: " + size + " Characters)");
        System.out.printf(" ╚═══╣ ");
    }//printNameRequest

    public void printOverwriteFile(){
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║ A saved file already exist?");
        System.out.println(" ║ Do you want to override file? (y/n)");
        System.out.printf(" ╚═══╣ ");        
    }//printOverwriteFile
  
    public void printOptions(){
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║ ");
        System.out.println(" ║ 1) Save and Continue 2) Save and Exit 3) Exit without saving 4) Cancel");
        System.out.printf(" ╚═══╣ ");        
    }//printOptions

    public void printPlayAgain(int select){
        clearMessage(3); //clear last 3 lines      
        String[] options = new String[2];
        options[0] = "1) Replay 2) New Game 3) Exit";
        options[1] = "1) Replay 2) New Game 3) Exit 4) Load Saved Game";
        System.out.println(" ║ Play Again?");
        System.out.println(" ║ " + options[select]);
        System.out.printf(" ╚═══╣ ");        
    }//printPlayAgain

    public void printPlay(int select){        
        clearMessage(3);  //clear last 3 lines
        String[] options = new String[2];
        options[0] = "1) New Game 2) Exit";
        options[1] = "1) New Game 2) Exit 3) Load Saved Game";        
        System.out.println(" ║ " + options[select]);
        System.out.println(" ║ ");
        System.out.printf(" ╚═══╣ ");        
    }//printPlay

    public void clearScreen(){
        System.out.println("\033[H\033[2J");//clear screen
        System.out.flush();
    }//clearScreen

    public void clearMessage(int lineCount){
        //go back by number of rows and clear screen from that position
        System.out.print(String.format("\033[%dA\033[J",lineCount));
    }//clearMessage

    public void slowPrint(String message,int slow) throws InterruptedException{
        //slow print message
        for(int i = 0; i < message.length(); i++){
            System.out.printf("%s",message.charAt(i));
            Thread.sleep(slow); //pause in between print
        }
    }//slowPrint

    public void printLoadingFile(String name) throws InterruptedException{
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║ ");
        System.out.printf(" ║ Loading saved data");
        String dots = "....";

        //print with one second pause
        slowPrint(dots, 1000);
        System.out.printf("Done");
        Thread.sleep(2000);
        System.out.printf("\n ║ Welcome back %s\n", name);
        Thread.sleep(3000);
    }//printLoadingFile

    public void printSavingFile() throws InterruptedException{
        clearMessage(3);//clear last 3 lines
        System.out.println(" ║ ");
        System.out.printf(" ║ Saving data");
        String dots = "....";

        //print with one second pause
        slowPrint(dots, 1000);
        System.out.printf("Done\n\n");
        Thread.sleep(2000);
    }//printSavingFile
}//class