import java.io.*;
import java.util.*;

/** Message class handles the messages of the user(specified by the key from the instructor)
  * it can add,remove,give messages depending on the parameters its methods ask
  *
  * @author EndrinB */
public class Message{
   File messages = new File("messages.txt");
   MessageHandler msg;
   Scanner scan;
   int key;
   FileWriter writer;
   
   /** Message constructor writes the users key in the message file if it hasn't
     * been created yet 
     * @param key this Object handels the messages for the user with that key
     * @param created tells if the user is already signed in the message file so it doesn't write him/her again */ 
   Message(int key){
      this.key = key;

   }
   
   /** getMessages method returns a list of all the messages the user has added */
   public List<String> getMessages(){
      List<String> msg = new ArrayList<>();
      try{
         scan = new Scanner(messages);      // this method creates a ArrayList Object , and using an algorithm 
         while(scan.hasNextLine()){         // it creates an String variables wich has the a Line of messages it checks the first character of the string
            String s = scan.nextLine()+""; // wich has the users key and if it is the same as our users it creates a substring starting from 2 index(removes
            String k = s.charAt(0)+"";     // the key and ",") and trims if there were any spaces in the start ,then i created a array and splited the messages
            if(key == Integer.parseInt(k)){ // from the "," reggex and used a while loop and added all those messages to the list
               s = s.substring(2);
               s = s.trim();
               String[] msgs = s.split(",");
               int count = 0;
               while(count < msgs.length){
                  msg.add(msgs[count]);
                  count++;
               }            
            }
         
         }
         
      }catch(Exception e){}
      scan.close();
      return msg;
   }
   /** addMessage it adds the message to the file
     * @param message the message to be added */
   public String addMessage(String message){               
      List<String> lines = new ArrayList<>();
      try{ 
         scan = new Scanner(messages);
         while(scan.hasNextLine()){             // puts every users messages in an array and the user with the key gets the message added
            String line = scan.nextLine();
            System.out.println(line);
            String k = line.charAt(0)+"";
            if(Integer.parseInt(k) == key){
               lines.add(line + message + ",");
            }else{
               lines.add(line);
            }
         }
         writer = new FileWriter(messages,false);
         BufferedWriter bw = new BufferedWriter(writer);
         PrintWriter pw = new PrintWriter(bw);
         int count = 0;
         while(count < lines.size()){        // writes every line to the message file
            pw.println(lines.get(count));
            count++;
         }
         pw.flush();
         pw.close();
         scan.close();
      }catch(Exception e){}
      return message;
   }
  /** removeMessage method removes the message with the specified index(starts from left to right)
    * @param num the the number of the message */
   public String removeMessage(int num){
      String message = "";
      List<String> lines = new ArrayList<>();
      try{ 
         scan = new Scanner(messages);
         while(scan.hasNextLine()){             // puts every users messages in an array and the user with the key gets the messages splitted
            String line = scan.nextLine();      // and "removes" the message with the number ( when the message counted was at the removed message i just skipped it)
            String k = line.charAt(0)+"";
            if(Integer.parseInt(k) == key){
               line = line.substring(2);
               String[] userLine = line.split(",");
               message = userLine[num-1];
               line = k + ",";
               for(int i= 0 ; i < userLine.length ; i++){
                  if(userLine[i] == userLine[num -1]){
                     continue;}             
                  line = line + userLine[i] + ",";
               }
               lines.add(line);
            }else{
               lines.add(line);
            }
         }
         writer = new FileWriter(messages,false);
         BufferedWriter bw = new BufferedWriter(writer);
         PrintWriter pw = new PrintWriter(bw);
         int count = 0;
         while(count < lines.size()){        // writes every line to the message file
            pw.println(lines.get(count));
            count++;
         }
         pw.flush();
         pw.close();
         scan.close();
      }catch(Exception e){}
      return message;
   }
     
}
