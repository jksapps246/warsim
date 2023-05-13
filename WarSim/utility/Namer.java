package utility;

public class Namer {
    private String[] names = {
        "Vlad the Impaler", 
        "Eric the Evil", 
        "Othello the Insane", 
        "Leather Face", 
        "Cheater of Death", 
        "Haggar of Horror",
        "Dagger of Ferocity", 
        "Sampson the Slayer", 
        "Andy the Axe of Anxiety" };

        private String[] strikeWords = {
            "madly swings", 
            "swings", 
            "rushed in", 
            "moved the earth", 
            "charges in", 
            "took a chance",
            "rampage", 
            "sees a opening" };

        private String[] propelWords = {
            "shoots", 
            "launches", 
            "fires", 
            "squeezes off one"};

    public Namer() {} // empty constructor

    public String getName() {
        int idx = (int) (Math.random() * names.length);
        return names[idx];
    } // getName()

    public String getStrikeWords() {
        int idx = (int) (Math.random() * strikeWords.length);
        return strikeWords[idx];
    } // getName()

    public String getPropelWords() {
        int idx = (int) (Math.random() * propelWords.length);
        return propelWords[idx];
    } // getName()
} // class Namer