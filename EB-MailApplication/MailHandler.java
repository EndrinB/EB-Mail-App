import java.io.*;
import java.util.*;

public class MailHandler{
   File mail = new File("mail.txt");
   private LinkedList<Integer> userKeys = new LinkedList();
   private HashMap<Integer,String> allMail = new HashMap<Integer,String>();
   MailHandler(){
   try{
    Scanner scan = new Scanner(mail);
    scan.useDelimiter("[,\n]");
    while(scan.hasNextLine()){
      String s = scan.nextLine();
      int uKey = Integer.parseInt(s.charAt(0) +"");
      String allMsg = s.substring(2);
      userKeys.add(uKey);
      allMail.put(uKey,allMsg);
    }
    scan.close();
    
   }catch(Exception e){}
   }
   
   public boolean containsMailKey(int k){
      return (userKeys.contains(k));
   }
   public void userRemoved(Integer[] array,int key){
               userKeys.remove(new Integer(key));
               allMail.remove(key);
               int count = 0;
               try{
            FileWriter wr = new FileWriter(mail,false);
            BufferedWriter bw = new BufferedWriter(wr);
            PrintWriter pw = new PrintWriter(bw);
            while(count <= array.length-1){
               wr.write(0+array[count] +"," + allMail.get(array[count]) +"\n" );
               count++;
            }
            wr.flush();
            wr.close();
         
         
         }catch(Exception e){}
   }
}