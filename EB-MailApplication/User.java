import java.util.*;

public class User{
   private String name;
   private String password,changedPass;
   private final int KEY;
   private Message msg ;
   private Mail mail ;
   private List<String> messages;
   private List<String> mails;
   private List<String> mailSent;
   private List<Integer> fromKeys = new ArrayList<Integer>();
   private List<Integer> toKeys;
   int msgUnchecked = 0;
   boolean created;
   static int users;
   
   User(String name,String password,int key,boolean created){
      this.name = name;
      this.password = password;
      this.created = created;
      KEY = key;
      users++;
   }
   public void getUserData(){
      msg = new Message(this.KEY);   
      mail = new Mail(this.KEY);
     
   }
   public void setData(){
      messages = msg.getMessages();
      this.mails = mail.getMail();
      this.mailSent = mail.getSentMail();
      this.fromKeys = mail.fromKeys();
      this.toKeys = mail.toKeys();
   }
   public void addMessage(String message){
      messages.add(msg.addMessage(message));
   }
   public void sendMail(String message, int key){
      toKeys.add(key);
      mailSent.add(mail.sendMail(message,key));
   }
   
   public void removeMessage(int msgNum){
      if(messages.size() >= msgNum && msgNum > 0) { 
         messages.remove(msg.removeMessage(msgNum));
      }
   }
   
   public String getMessage(int msgNum){
      if (messages.size() > msgNum && msgNum >= 0) {
         return messages.get(msgNum);
      }
      return "This index ("+msgNum+") exceeds the size of the messages list";
   }
   public String getMail(int mailNum){
      if (mails.size() > mailNum && mailNum >= 0) {
         return mails.get(mailNum);
      }
      return "This index ("+mailNum+") exceeds the size of the messages list";
   }
   public String getMailSent(int mailSNum){
      if((mailSent.size() > mailSNum && mailSNum >= 0)) {
         return mailSent.get(mailSNum);
      }
      return "This index ("+mailSNum+") exceeds the size of the messages list";
   }
   public List<String> getMessages(){
      return messages;
   }
   public List<String> getMails(){
      return mails;
   }
   public List<String> getMailsSent(){
      return mailSent;
   }
   public List<Integer> getFromKeys(){
      return fromKeys;
   }
   public List<Integer> getToKeys(){
      return toKeys;
   }
   public int getToKey(int num){
      return toKeys.get(num);
   }
   public int getFromKey(int num){
      int i = -1;
      if((fromKeys.size() > num && num >= 0)) {
         i = fromKeys.get(num);
      }   
      return i;
   }
   void replaceMessage(int i , String m){
      messages.set(i,m);
   }
   public void addNotification(){
      msgUnchecked++;
   }
   public void resetNotifications(){
      msgUnchecked = 0;
   }
   public int getNotifications(){
      return msgUnchecked;
   }
   
   public int getMsgCount(){
      return messages.size();
   }
   public int getMailCount(){
      return mails.size();
   }
   public int getMailSCount(){
      return mailSent.size();
   }
   
   public String getName(){
      return name;
   }
   
   public String getPassword(){
      return password;
   }
   public void changePassword(String newPass){
      this.password = newPass;
   }
   
   public int getUserKey(){
      return KEY;
   }
   public int getTotalUsers(){
      return users;
   }
}
