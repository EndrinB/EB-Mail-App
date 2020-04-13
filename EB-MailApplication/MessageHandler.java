import java.io.*;
import java.util.*;

public class MessageHandler{
   File messages = new File("messages.txt");
   private LinkedList<Integer> userKeys = new LinkedList();
   private HashMap<Integer,String> allMessages = new HashMap<Integer,String>();
   MessageHandler(){
   try{
    Scanner scan = new Scanner(messages);
    scan.useDelimiter("[,\n]");
    while(scan.hasNextLine()){
      String s = scan.nextLine();
      int uKey = Integer.parseInt(s.charAt(0) +"");
      String allMsg = s.substring(2);
      userKeys.add(uKey);
      allMessages.put(uKey,allMsg);
    }
    scan.close();
    
   }catch(Exception e){}
   }
   public void addUser(int key){

   try{
       
        if(userKeys.contains(key)){}
        else{
            FileWriter writer = new FileWriter(messages,true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(key + ",\n");
            
            pw.flush();
            pw.close();   
            userKeys.add(key);
            
            }      
         }
         catch(Exception e){}
        
   }
   public boolean containsMsgKey(int k){
      return (userKeys.contains(k));
   }
   public void userRemoved(Integer[] array,int key){
               userKeys.remove(new Integer(key));
               allMessages.remove(key);
               int count = 0;
               try{
            FileWriter wr = new FileWriter(messages,false);
            BufferedWriter bw = new BufferedWriter(wr);
            PrintWriter pw = new PrintWriter(bw);
            while(count <= array.length-1){
               wr.write(0+array[count] +"," + allMessages.get(array[count]) +"\n" );
               count++;
            }
            wr.flush();
            wr.close();
         
         
         }catch(Exception e){}
   }
}