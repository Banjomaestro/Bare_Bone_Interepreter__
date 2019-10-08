package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    static String st;
    static String name;
    static int myIntNumber = 0;
    static int beginning;
    static int end;
    static boolean oldInt;
    static ArrayList<myInt> myInts;
    static BufferedReader myBufferedReader;

    static FileReader myFileReader;
    static ArrayList<String> theCode= new ArrayList<String>();
    static int totalLineNumber=0;
    static int lineNumber;
    static myInt theRightInt;

    public static void main(String[] args) throws FileNotFoundException {

        String filePath_and_Name = JOptionPane.showInputDialog("enter the name of the file with its path to compile (with .txt at the end)");

        File myFile = new File(filePath_and_Name);
        myFileReader=new FileReader(myFile);
        myInts = new ArrayList<myInt>();


            myBufferedReader = new BufferedReader(myFileReader);
            makeCode();


                try {
                    for (lineNumber = 0; lineNumber < totalLineNumber; lineNumber++) {


                        readingInstruction();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

        String endMessage=" ";
        for(int i = 0;i<myIntNumber;i++){
            endMessage = endMessage+myInts.get(i).getNameOfInt()+"=  "+myInts.get(i).getThisInt()+"\n ";
        }


        JOptionPane.showMessageDialog(null,endMessage);

            }





    public static void makeCode(){
        String t="";


            try {
                while ((t = myBufferedReader.readLine()) != null) {

                    theCode.add(t);
                    totalLineNumber++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    public static void readingInstruction() throws IOException {


            st = theCode.get(lineNumber);

        if (st.contains("while")) {


            int testingWhile = lineNumber;
            if(!checkWhileCondition(testingWhile)){
                int numberOfWhileInBetween=0;
                for (int i = lineNumber; i<totalLineNumber;i++){

                    if(theCode.get(i).contains("while")){
                        numberOfWhileInBetween++;
                    }

                    if (theCode.get(i).contains("end")){
                        if (numberOfWhileInBetween==1) {
                            lineNumber = i;
                            break;
                        }

                        numberOfWhileInBetween--;
                    }
                }
            }
        }
        if (st.contains("end")){
            int numberOfEndInbetween=0;
            for (int i = lineNumber; i>=0;i--){

                if (theCode.get(i).contains("end")){
                    numberOfEndInbetween++;
                }
                if (theCode.get(i).contains("while")){

                    if(numberOfEndInbetween==1&&!checkWhileCondition(i))
                        break;
                   else if (numberOfEndInbetween==1&&checkWhileCondition(i)) {
                       lineNumber = i;
                       break;
                   }

                    numberOfEndInbetween--;


                }


            }

        }


            if (st.contains("clear")) {
                oldInt = false;

                if (!myInts.isEmpty()) {
                    forEachInstruction("clear");

                }
                if (!oldInt) {
                    beginning = st.indexOf("clear ") + 6;
                    end = st.indexOf(";");
                    name = st.substring(beginning, end);
                    myInts.add(new myInt(name));
                    System.out.println("created "+name);
                    myIntNumber++;
                }

            }
            if (st.contains("incr")) {
                forEachInstruction("incr");
                System.out.println("incremented " + name);
            }

            if (st.contains("decr")) {
                forEachInstruction("decr");
                System.out.println("decremented " + name);
            }



        }

        public static boolean checkWhileCondition(int daLine) {

            beginning = theCode.get(daLine).indexOf("while ") + 6;
            end = theCode.get(daLine).indexOf(" not");
            String name2 = theCode.get(daLine).substring(beginning, end);

            for (int y = 0; y < myIntNumber; y++) {
                if (myInts.get(y).getNameOfInt().equals(name2)) {
                    theRightInt = myInts.get(y);
                }
            }
            if (theRightInt.getThisInt()==0)
                return false;
            else
                return true;

    }
        public static void forEachInstruction(String Instruction){


            beginning = st.indexOf(Instruction+" ") + Instruction.length()+1;
            if (st.contains("not"))
                end = st.indexOf(" not");
            else
            end = st.indexOf(";");
            name = st.substring(beginning, end);

            for (int i = 0; i < myIntNumber; i++) {
                if (myInts.get(i).getNameOfInt().equals(name)) {
                    myInts.get(i).givesRightInstruction(Instruction);
                    System.out.println("The value of "+myInts.get(i).getNameOfInt()+" is "+myInts.get(i).getThisInt());

                    oldInt=true;

                }
            }
    }


}



