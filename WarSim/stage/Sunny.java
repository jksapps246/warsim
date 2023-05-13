package stage;

public class Sunny extends Arena {
    
    private final String ELEMENT = " "; 
    private final int ROWS = 10;
    private final int COLS = 77;
    private final String[] HEADER = new String[4];
    private final String NAME = "Sunny"; 
    private final String TEXT_RESET = "\u001B[0m";
    private final String TEXT_YELLOW = "\u001B[33m";
    private final int PADDING = 4;

    public Sunny(){//constructor
        super();
        setSpace(ROWS,COLS,ELEMENT,getHeader(),PADDING,NAME);
    }

    public String[] getHeader(){
        
        //header for arena
        HEADER[0] = TEXT_YELLOW + "   \\*/" + TEXT_RESET + "           ~v~         ~v~                   ~v~                       ";
        HEADER[1] = TEXT_YELLOW + "--**O**--" + TEXT_RESET + "              ~v~                      ~v~   ~v~                    ";
        HEADER[2] = TEXT_YELLOW + "   /*\\" + TEXT_RESET + "                                                  ~v~                  ";
        HEADER[3] = TEXT_YELLOW + "  / | \\" + TEXT_RESET + "              ~v~                  ~v~         ~v~                    ";
        return HEADER;
    }//getHeader
}//class
