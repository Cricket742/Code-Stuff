import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class Chatbot {
    //Instructions: Ask the chat bot a question, if it's in the preset answers it will tell you about itself. If not it will remember what you say for next time.
    public static void main(String[] args) {
        System.out.println("What would you like to know?");
        Scanner in = new Scanner(System.in);
        String question= in.nextLine();
        String[] lore= {"Who are you","What are your pronouns", "Do you have a partner"};
        String[] loreAnswers= {"I am Squesus, Grand Wizard of Neptune","She/they", "Yes, her name is Sick Radicus,she is also a powerful spell caster"};
        boolean loreOrNot= isLore(question, lore);
        ArrayList<String> newQuestions= new ArrayList<String>();
        ArrayList<String> newAnswers= new ArrayList<String>();
        //reads new questions
        try {
            File questionsFile = new File("newQuestion.txt");
            Scanner myReader = new Scanner(questionsFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              newQuestions.add(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          //read new answers
          try {
            File answerFile = new File("newAnswers.txt");
            Scanner myReader = new Scanner(answerFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              newAnswers.add(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        if(loreOrNot== true){
            int loreLoc = 0;
            for(int i=0;i<lore.length;i++){
                if(lore[i].equalsIgnoreCase(question)==true){
                loreLoc=i;
                }
            } 
            System.out.println(loreAnswers[loreLoc]);
        }else{
            boolean already= false;
            for(int i=0;i<newQuestions.size();i++){
                if(newQuestions.get(i).equalsIgnoreCase(question)){
                    System.out.println(newAnswers.get(i));
                    already= true;
                }
            }
            if(already==false){
                try {
                        FileWriter myWriter = new FileWriter("newQuestion.txt", true);
                        BufferedWriter writer= new BufferedWriter(myWriter);
                        PrintWriter pr = new PrintWriter(writer);
                        pr.println(question+"\n");
                        pr.close();
                        writer.close();
                        myWriter.close();
                    } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                    }
                    System.out.println("What do you want me to say?");
                    try {
                        FileWriter myWriter = new FileWriter("newAnswers.txt", true);
                        BufferedWriter writer= new BufferedWriter(myWriter);
                        PrintWriter pr = new PrintWriter(writer);
                        pr.println(in.nextLine()+"\n");
                        pr.close();
                        writer.close();
                        myWriter.close();
                    } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                          }
                    System.out.println("I will remember that");
            }
        }
        in.close();
    }

    public static boolean isLore(String question, String[] lore){
        for(int i=0;i<lore.length;i++){
            if(lore[i].equalsIgnoreCase(question)==true){
                return true;
            }
        }
        return false;
    }

}

