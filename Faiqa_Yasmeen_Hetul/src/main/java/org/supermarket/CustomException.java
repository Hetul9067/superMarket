package org.supermarket;

public class CustomException extends Exception{
    private String msg = "";


    public CustomException(){
        super();

    }

    public CustomException(String message){
        super(message);
        msg = message;
    }
    public CustomException(String message, Exception inner){
        super(message, inner);
    }

    public void processError(int value){
        System.out.println("#########################");
        switch (msg){
            case "error1":
                System.out.println(HardCodedValue.ERROR1+ value+ "!");
                break;
            case "error2":
                System.out.println("");
                break;
        }
        System.out.println("#########################");
    }
}
