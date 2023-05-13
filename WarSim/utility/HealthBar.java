package utility;

public class HealthBar {

    private Character empty = '▒';//empty slot
    private Character full = '█';//full slot
    private String color;
    private final String TEXT_RESET = "\u001B[0m";
    private final String TEXT_YELLOW = "\u001B[33m";
    private final String TEXT_RED = "\u001B[31m";
    private final String TEXT_GREEN = "\u001B[32m";
    private String bar = "";

    public HealthBar(){}//default constructor

    public String getHealthBar(int health, int maxHealth){
        
        //0 - 100%
        int checkMax = 0;
        int check = 0;
        int remain = 0;
        String status = "";

        if(health > 0){//calculate status count
            checkMax = maxHealth / 20;
            check = health / checkMax;
            remain = health % checkMax;
        }

        //add status to bar
        for(int i = 0; i< 20; i++){
            
            if(i < check ){//add status
                status = status + full;
            }
            else if(remain > 30) {//add remainder
                status = status + full;
                remain = 0;
            } //set default bar
            else status = status + empty;
        } 

        /////////////////////////////////////
        //change color based on health remaining
        //0% - 30% - red
        //31% - 60% - yellow
        //61% - 100% - green
        if (health < (maxHealth * 30)/100) color = TEXT_RED;
        else if(health < (maxHealth * 60)/100) color = TEXT_YELLOW;
        else color = TEXT_GREEN;

        bar = color + status + TEXT_RESET;        
        return bar;
    }//getHealthBar
}//class
