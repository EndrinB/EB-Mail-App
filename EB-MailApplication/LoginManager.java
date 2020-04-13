import javax.swing.*;
import java.util.*;
import java.util.stream.*;

public class LoginManager{
   Database data;
   User userLoggedIn;
   public LoginManager(Database data){
      this.data = data;
   }
   
   public boolean signUser(String name , char[] password){
      boolean nameTaken = false;
      if(data.addUser(name,new String(password))){
      JOptionPane.showMessageDialog(null,"User " + name + " has signed up");
      }else{
         nameTaken = true;
      }
      return nameTaken;
   }
   public boolean loginUser(String name,char[] password){
      boolean loginSuccessful = false;
         int key = data.getKey(name);
         User user = data.getUser(key);
         if(user.getName().equals(name) && user.getPassword().equals(new String(password))){
            loginSuccessful = true;
            userLoggedIn = user;
          }
      return loginSuccessful;
   }
   public User getUserLogged(){
      return userLoggedIn;
   }
}