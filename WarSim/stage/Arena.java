
package stage;
import utility.HealthBar;
import java.util.Random;

public abstract class Arena {
    private String[][] space;
    private String[] header;
    private String element;
    private String name;
    private int rows; 
    private int cols;
    private int padding;

    private static HealthBar hbar = new HealthBar();
    private static Random randNum = new Random(); 

    public Arena(){}//default constructor
 
    protected void setSpace(int rows, int cols, String element, String[] header,int padding, String name){
        this.space = new String[rows][cols];
        this.header = header;
        this.element = element;
        this.rows = rows;
        this.cols = cols;
        this.padding = padding;
        this.name = name;
    }//setSpace

    public String[][] getSpace(){
        return this.space;
    }//getSpace

    public int getRow(){
        return rows;
    }//getRow

    public int getCol(){
        return cols;
    }//getCol

    public void setPosition(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
    }//setPosition

    public String displayArena(int pHealth, int pMaxHealth,int eHealth, int eMaxHealth, int[] pStats, int[] eStats){
        String wall = "║";
        String wspace = " ";
        String floor = "═";
        int middleSpacing = this.cols - 58;
        
        //set player stats
        String playerStats;
        playerStats = "S:" + String.format("%1$4s", pStats[0]);
        playerStats = playerStats + " D:" + String.format("%1$3s", pStats[1]);
        playerStats = playerStats + " L:" + String.format("%1$2s", pStats[2]);
        playerStats = playerStats + " M:" + String.format("%1$4s", pStats[3]);

        //set enemy stats
        String enemyStats;
        enemyStats = "S:" + String.format("%1$4s", eStats[0]);
        enemyStats = enemyStats + " D:" + String.format("%1$3s", eStats[1]);
        enemyStats = enemyStats + " L:" + String.format("%1$2s", eStats[2]);
        enemyStats = enemyStats + " M:" + String.format("%1$4s", eStats[3]);

        ///////////////////////////////
        //roof of arena
        String background = "╔" + floor.repeat(space[0].length) + "╗\n" + wall ;

        ///////////////////////////////
        //header of arena
        for(int i = 0; i < header.length; i++){
            background = background + String.format("%1$"+space[0].length+"s", 
            header[i]) + wall +"\n" + wall ;
        }

        ///////////////////////////////
        //body
        for(int i = 0; i< space.length; i++){
            for(int j = 0; j< space[i].length; j++){
                background = background + space[i][j];
            }
            background = background + wall;

            if(i == space.length-1) background = background + "\n";
            else background = background + "\n" + wall;
        }

        ///////////////////////////////
        //floor
        background = background + "╠" + floor.repeat(22) + "╦" + floor.repeat(5) + 
                                  "╦" + floor.repeat(middleSpacing) + "╦"+floor.repeat(5) +
                                  "╦"+floor.repeat(22) + "╣\n";

        //add heath bars for player and enemy
        background = background + "╚╣" + hbar.getHealthBar(pHealth,pMaxHealth) + "╠╩╣" + 
                                  String.format("%1$3s",pHealth) + "╠╝" + 
                                  wspace.repeat((middleSpacing-name.length())/2) + 
                                  name +wspace.repeat((middleSpacing-name.length())/2)+"╚╣" +
                                  String.format("%1$3s",eHealth) + 
                                  "╠╩╣" +  hbar.getHealthBar(eHealth,eMaxHealth) + "╠╝\n";

         background = background + " ╠╣" + playerStats + "╠╝" + wspace.repeat(middleSpacing+2) + 
                                  "╚╣" +  enemyStats + "╠╝\n\n\n";
        return background;//return 
    }//displayArena

    public void clearArena(){
        String background = " ";//empty space
        for(int i = 0; i< space.length; i++){
            for(int j = 0; j< space[i].length; j++){
                space[i][j] = background;//set all space in the arena to empty
            }
        }
    }//clearArena
    
    public void setPattern(){
        //clear arena
        this.clearArena();
        String[][] newSpace = new String[rows][cols];
        newSpace = getSpace();

        //move element
        int location;
        for(int i =0; i<rows; i++){
            for(int j = 0; j < cols/padding; j++){
                //get random location
                location = randNum.nextInt(padding)+1;
                //add corespoding position in column
                location = (j*padding) + location;
                newSpace[i][location-1] = element;
            }
        }
        space = newSpace;
    }//setPattern
    
    public void addCharacter(String[][] character,int rowStart, int colStart, String color){
        String TEXT_RESET = "\u001B[0m";//color reset

        //add animated objects to the arena
        for(int i = 0; i< space.length; i++){
            for(int j = 0; j< space[i].length; j++){
                if (((i>=rowStart) && ((i-rowStart)< character.length )) && 
                    ((j>=colStart) && ((j-colStart)< character[0].length ))){
                        space[i][j] = color + character[i-rowStart][j-colStart]+TEXT_RESET;
                }
            }
        }
    }//addCharacter
}//class
