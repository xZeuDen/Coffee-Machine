import java.util.Scanner;

class keyboard {
    private Scanner in;
    //Constructor
    keyboard(){ 
        in = new Scanner(System.in);
    }
    public int readInteger(String promptMsg, String errorMsg){
        int num = 0;
        String strInput;
        boolean valid = false;

        while(valid==false){
            //Prompt the user
            System.out.print(promptMsg);
            //Grab input from keyboard
            strInput = in.nextLine();
            //Try to convert String to int
            try {
                num = Integer.parseInt(strInput);
                valid = true;
            }
            catch(NumberFormatException e){
                System.out.println(errorMsg);
            }
        }
        return num;
    }
    public int readInteger(String promptMsg, String errorMsg, int low, int high){
        int num = 0;
        String strInput;
        boolean valid = false;

        while(valid==false){
            //Prompt the user
            System.out.print(promptMsg);
            //Grab input from keyboard
            strInput = in.nextLine();
            //Try to convert String to int
            try {
                num = Integer.parseInt(strInput);
                if (num >=low && num <= high)
                    valid = true;
                else
                    System.out.println(errorMsg);
            }
            catch(NumberFormatException e){
                System.out.println(errorMsg);
            }
        }
        return num;
    }
    public double readDouble(String promptMsg, String errorMsg){
        double num = 0;
        String strInput;
        boolean valid = false;

        while(valid==false){
            //Prompt the user
            System.out.print(promptMsg);
            //Grab input from keyboard
            strInput = in.nextLine();
            //Try to convert String to int
            try {
                num = Double.parseDouble(strInput);
                valid = true;
            }
            catch(NumberFormatException e){
                System.out.println(errorMsg);
            }
        }
        return num;
    }
}