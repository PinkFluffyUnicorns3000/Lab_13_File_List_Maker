import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;


public class Main {
    public static void main(String[] args){

        String fileName = "C:\\Users\\benhi\\IdeaProjects\\Lab_13_File_List_Maker\\src\\list.txt";

        ArrayList<String> myArrList = new ArrayList<>();
        boolean done = false;
        Scanner in = new Scanner(System.in);
        System.out.println("\nWelcome to list maker enter your option below:");

        do{
            String userInput = SafeInput.getRegExString(in,"A - add : D - delete : " +
                    "V : View : Q - Quit : O - Open : S - Save : C - Clear","[AaDdVvQqOoSsCc]");
             userInput = userInput.toUpperCase();

            switch(userInput){
                case "A":
                    myArrList.add(SafeInput.getNonZeroLenString(in,"What would you like to add?"));
                    saveList(myArrList, fileName);
                    break;
                case "D":
                    for(int x = 0; x < myArrList.size(); x++){
                        System.out.println(x + ": " + myArrList.get(x));
                    }
                    if(myArrList.size() == 0){
                        System.out.println("The list is empty...");
                        break;
                    }
                    int index = SafeInput.getRangedInt(in, "What item would you like to remove?",
                            0, myArrList.size()-1);
                    myArrList.remove(index);
                    saveList(myArrList, fileName);
                    break;
                case "V":
                    System.out.println(" ");
                    for(String f : myArrList){
                        System.out.println(f);
                    }
                    if(myArrList.size() == 0){
                        System.out.println("There is nothing in you list");
                    }
                    break;
                case "Q":
                    boolean confirm = SafeInput.getYNConfirm(in,"Are you sure you wat to quit?");
                    if(confirm) {
                        done = true;
                    }
                    break;
                case "O" :
                    openSave(myArrList, fileName);
                    break;
                case "S" :
                    saveList(myArrList,fileName);
                    break;
                case "C" :
                    myArrList.clear();
                    break;
            }
        }while(!done);
    }
    public static void saveList(ArrayList arrList, String fileName)
    {
        PrintWriter outFile;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve(fileName);

        try
        {
            outFile = new PrintWriter(target.toString());
            for (int i = 0; i < arrList.size(); i++) {
                outFile.println(arrList.get(i));
            }
            outFile.close();
            System.out.printf("File \"%s\" saved!\n", target.getFileName());
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }
    private static void openSave(ArrayList myArrList, String fileName){
        String rec = "";
        String line;

        try {
                Path target = new File(System.getProperty("user.dir")).toPath();
                target = target.resolve(fileName);

                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                rec = br.readLine();
                myArrList.add(rec);
            }
            br.close();
            System.out.println("Successfully opened last save...ENTER V TO VIEW LIST");
            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

