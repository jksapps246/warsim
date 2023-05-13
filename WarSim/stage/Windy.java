package stage;

public class Windy extends Arena {
    
    private final String ELEMENT = " "; 
    private final int ROWS = 10;
    private final int COLS = 77;
    private final int PADDING = 4;
    private final String[] HEADER = new String[4];
    private final String NAME = "Windy"; 

    public Windy(){//constructor
        super();
        super.setSpace(ROWS,COLS,ELEMENT,getHeader(),PADDING,NAME);
    }

    public String[] getHeader(){   
             
        //header for arena
        HEADER[0] = "     .-~~~-.           .-.                 .-~-.                    .-~-.    ";
	    HEADER[1] = " .~-(       )-~-.   .-(   )--.         .~-(     )-~-.    .-.    .~-(     )-~-";               
	    HEADER[2] = "(                ) (__________)       (              ).-(   )--.             ";   
	    HEADER[3] = " `--------------'                      `------------'(__________)------------";
        return HEADER;
    }//getHeader
}//class
