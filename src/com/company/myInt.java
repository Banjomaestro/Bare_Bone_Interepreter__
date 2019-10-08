package com.company;

import java.io.IOException;

public class myInt {

    String nameOfInt;
    int thisInt;

    public myInt(String nameOfTheInt){
        nameOfInt=nameOfTheInt;
        thisInt=0;


    }

    public void increment(){
        thisInt++;


    }

    public void decrement(){
        thisInt--;


    }

    public void clear(){
        thisInt=0;

    }

    public String getNameOfInt(){


        return nameOfInt;
    }

    public int getThisInt(){
        return thisInt;
    }

    public void givesRightInstruction(String instructionName){
        if (instructionName.equals("clear"))
            this.clear();
        else if (instructionName.equals("incr"))
            this.increment();
        else if (instructionName.equals("decr"))
            this.decrement();


    }




}
