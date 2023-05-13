package stage;

public class Rain extends Arena {

    private final String ELEMENT = "/"; 
    private final String[] HEADER= new String[4];
    private final String NAME = "Raining";     
    private final int ROWS = 10;
    private final int COLS = 77;
    private final int PADDING = 4;

    public Rain(){//constructor
        super();
        
        //store animated information
        super.setSpace(ROWS,COLS,ELEMENT,getHeader(),PADDING,NAME);
    }
    
    private String[] getHeader(){

        //header for arena
        HEADER[0] = "     .-~~~~-.           .-.             .-~-.                      .-~-.     ";
	    HEADER[1] = " .-~(░░░░░░░░)~-.    .-(░░░)--.     .~-(░░░░░)-~-.      .-.    .~-(░░░░░)-~-.";               
	    HEADER[2] = "(░░░░░░░░░░░░░░░░)  (░░░░░░░░░░)   (░░░░░░░░░░░░░░)  .-(░░░)--.░░░░░░░░░░░░░░";   
	    HEADER[3] = " `-/--/-----/---'  /   /   /   /    `/----/-----/'  (░░░░░░░░░░)/--/---/----/";
        return HEADER;
    }//getHeader
}//class
