import java.util.*;
import java.io.*;
import java.util.List;
/** Class Database manages the data of the users
  *
  * @author EndrinB
  * 
  */
public class Database{
   HashMap<Integer,User> database = new HashMap<Integer,User>();
   private LinkedList<String> userNames = new LinkedList();
   private LinkedList<Integer> userKey = new LinkedList();
   private MessageHandler msgHandler;
   static File server;
   FileWriter writer;
   int key = 1;
   
   /** Database Constructors acquires the data from the database(txt file) */
   Database()
   {
      try{
         msgHandler = new MessageHandler();
         server = new File("database.txt");
         Scanner scan = new Scanner(server);
         scan.useDelimiter("[, \n]");
         while(scan.hasNext()){
            int keys = Integer.parseInt(scan.next());
            String name = scan.next();
            String password = scan.next();
            User user = new User(name,password,keys,msgHandler.containsMsgKey(keys));
            database.put(keys,user);
            msgHandler.addUser(keys);
            userNames.add(name);
            userKey.add(keys);
            
            if(this.key <= keys){ this.key = keys + 1;}
         
         }
         scan.close();
          
      }catch(Exception e){}  
   
   }
   
   /** getUser method returns an user depending on the key it takes
     * @param key the key associated with the user */
   public User getUser(int key){
      User user = null;
      if(database.containsKey(key)){ 
         user = database.get(key);
      }else{
      }
      return user;
   }
   public boolean checkIfUserExists(String name){
      return userNames.contains(name);
   }
   /** addUser method adds an User ,if the name is taken the new user wont be added
     * to the database
     * @param name the name of the user
     * @param password the password the user has signed with */
   public boolean addUser(String name,String password){
      boolean signed = false;
      if(userNames.contains(name)){
      }else{
         try{
            writer = new FileWriter(server,true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter pw = new PrintWriter(bw);
            User user = new User(name,password,key,false);
            pw.print(key +"," + name + "," + password + "\n");
            database.put(key,user);
            msgHandler.addUser(key);
            userNames.add(name);
            userKey.add(key);
            key++;
            pw.flush();
            pw.close();
            signed = true;
         }catch(Exception e){}
      }
      return signed;
   } 
   
   /** removeUser method removes the user with the specified key
     * from the database,userNames,userPassword and database(txt file)
     * @param key the key associated with the user */
   public void removeUser(int key){
      if(!database.containsKey(key)){System.out.println("User not found");}
      else{
         int count = 0;
         userNames.remove(database.get(key).getName());
         userKey.remove(key);
         database.remove(key);
         Set<Integer> keys = database.keySet();
         Integer[] array = keys.toArray(new Integer[keys.size()]);  
         msgHandler.userRemoved(array,key);
         try{
            FileWriter wr = new FileWriter(server,false);
            BufferedWriter bw = new BufferedWriter(wr);
            PrintWriter pw = new PrintWriter(bw);
            while(count <= keys.size()-1){
               pw.print(0+array[count] +","+ database.get(array[count]).getName() + "," + database.get(array[count]).getPassword() + "\n");
               count++;
            }
            pw.flush();
            pw.close();
         
         
         }catch(Exception e){}
      }
   }
   public void changePassword(int k,String pass){
      List<String> lines = new ArrayList<String>();
      String s = "";
      try{
         Scanner scanner = new Scanner(server);
         while(scanner.hasNextLine()){
            s = scanner.nextLine();
            int id = Integer.parseInt(s.charAt(0)+"");
            if(id == k){
               String[] p = s.split(",");
               p[2] = pass;
               s= p[0]+","+p[1]+","+p[2];
            }
            lines.add(s);
            
         }
         scanner.close();
         int count = 0;
          FileWriter wr = new FileWriter(server,false);
            BufferedWriter bw = new BufferedWriter(wr);
            PrintWriter pw = new PrintWriter(bw);
            while(count < lines.size()){
               pw.print(lines.get(count) +"\n");
               count++;

      }
      pw.flush();
      pw.close();
      }catch(Exception e){}
   }
   public boolean containsUserName(String name){
      
      return userNames.contains(name);
   }
   public int getKey(String name){
      return userKey.get(userNames.indexOf(name));
   }
}

