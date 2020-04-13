import java.io.*;
import java.util.*;

/** Message class handles the messages of the user(specified by the key from the instructor)
  * it can add,remove,give messages depending on the parameters its methods ask
  *
  * @author EndrinB */
public class Mail{
   File mail = new File("mail.txt");
   List<Integer> from;
   List<Integer> to;
   MailHandler msg;
   Scanner scan;
   int key;
   FileWriter writer;
   
   /** Message constructor writes the users key in the message file if it hasn't
     * been created yet 
     * @param key this Object handels the messages for the user with that key
     * @param created tells if the user is already signed in the message file so it doesn't write him/her again */ 
   Mail(int key){
      this.key = key; 
   }
  
   
   /** getMessages method returns a list of all the messages the user has added */
   public List<String> getMail(){
      List<String> msg = new ArrayList<>();
      from = new ArrayList<>();
      try{
         scan = new Scanner(mail);      // this method creates a ArrayList Object , and using an algorithm 
         while(scan.hasNextLine()){         // it creates an String variables wich has the a Line of messages it checks the first character of the string
            String s = scan.nextLine(); // wich has the users key and if it is the same as our users it creates a substring starting from 2 index(removes
            s.trim();
            String[] msgs = s.split("\\.");
            int k = Integer.parseInt(msgs[2]);     // the key and ",") and trims if there were any spaces in the start ,then i created a array and splited the messages
            if(this.key == k){ // from the "," reggex and used a while loop and added all those messages to the list               
               msg.add(msgs[1]);
               from.add(Integer.parseInt(msgs[0]));
       
            }            
         }
      }      
      catch(Exception e){}
      scan.close();
      return msg;
   }
   public List<String> getSentMail(){
      List<String> mailSent = new ArrayList<>();
      to = new ArrayList<Integer>();
      try{
         scan = new Scanner(mail);      // this method creates a ArrayList Object , and using an algorithm 
         while(scan.hasNextLine()){         // it creates an String variables wich has the a Line of messages it checks the first character of the string
            String s = scan.nextLine()+""; // wich has the users key and if it is the same as our users it creates a substring starting from 2 index(removes
            s.trim();
            String[] msgs = s.split("\\.");
            int k = Integer.parseInt(msgs[0]);     // the key and ",") and trims if there were any spaces in the start ,then i created a array and splited the messages
            if(key == k){ // from the "," reggex and used a while loop and added all those messages to the list               
               mailSent.add(msgs[1]);
               to.add(Integer.parseInt(msgs[2]));
            }            
         }
      }      
      catch(Exception e){}
      scan.close();
      return mailSent;
   }
   public List<Integer> fromKeys(){
    
    return from;
   }
   public List<Integer> toKeys(){
    return to;
   }
   /** addMessage it adds the message to the file
     * @param message the message to be added */
   public String sendMail(String message,int receiverKey){               
      try{
         writer = new FileWriter(mail,true);
         BufferedWriter bw = new BufferedWriter(writer);
         PrintWriter pw = new PrintWriter(bw);
                                                         // writes every line to the message file
         pw.println(this.key + "." + message + "." + receiverKey);
            
         pw.flush();
         pw.close();
         
      }catch(Exception e){}
      return message;
   }
      
}

