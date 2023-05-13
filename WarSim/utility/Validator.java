package utility;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Validator {

    private Printer printer = new Printer();
    private Scanner input = new Scanner(System.in);

    public Validator() {}
    
    //validator method
    public int validateChoice(String type, int optionCount) throws InterruptedException{
        int choice = 0;
        boolean isBad = true;
        do {
            try {
                switch(type) {
                    case "warrior": {
                        printer.printWarriorMenu();
                        break;
                    }
                    case "armour": {
                        printer.printArmourMenu();
                        break;
                    }
                    case "weapon": {
                        printer.printWeaponMenu();
                        break;
                    }
                    case "attack": {
                        printer.printAttackMenu();
                        break;
                    }
                    case "options": {
                        printer.printOptions();
                        break;
                    }
                    case "playAgain": {
                        printer.printPlayAgain(0);
                        break;
                    }
                    case "playAgainLoad": {
                        printer.printPlayAgain(1);
                        break;
                    }
                    case "play": {
                        printer.printPlay(0);
                        break;
                    }
                    case "load": {
                        printer.printPlay(1);
                        break;
                    }
                }
                choice = input.nextInt(); // assignment
                if(choice >= 1 && choice <= optionCount) {
                    isBad = false; // allows us to exit
                }
            } // try
            catch(InputMismatchException e) {
                input.nextLine(); // discards the input
                isBad = true;
            } // catch
        } while(isBad == true); // == equality check
        return choice;
    } // validateChoice()

    public String validateChoiceString(String type, int size) throws InterruptedException{
        String choice = "";
        Boolean isBad = true;
        do {
            try {
                switch(type) {
                    case "name": {
                        printer.printNameRequest(size);
                        break;
                    }
                    case "overwrite": {
                        printer.printOverwriteFile();
                        break;
                    }    
                }
                choice = input.next(); // assignment
                if(type.equals("name")){
                    if(choice.length() <= size){
                        isBad = false; // allows us to exit
                    }
                }
                else if(choice.equals("y") || choice.equals("n")) {
                    isBad = false; // allows us to exit
                }
            } // try
            catch(InputMismatchException e) {
                input.nextLine(); // discards the input
                isBad = true;
            } // catch
        } while(isBad == true); // == equality check
        return choice;
    } // validateChoice()
} // class