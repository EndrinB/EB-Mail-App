import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class LoginPlatform extends JPanel implements MouseListener,ActionListener{
   JFrame frame;
   JTextField name;
   JPasswordField password;
   LoginManager manager;
   Timer timer = new Timer(250,this);
   boolean mouseOverLogin,mouseOverSignUp,userNotFoundError,passwordError,nameTaken;
   Database data;
   
   public LoginPlatform(Database data){
      this.data = data;
      manager = new LoginManager(data);
      frame = new JFrame();
      frame.setSize(825,533);
      addTextFields();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.getContentPane().add(this);
      frame.addMouseListener(this);
      JLabel c = new JLabel("ERROR");
      frame.setVisible(true);
   }
   public void paintComponent(Graphics g){
      ImageIcon image = new ImageIcon("EB-MAIL_LOGIN.png");
      image.paintIcon(this,g,10,0);
      if(mouseOverLogin){
         ImageIcon loginClicked = new ImageIcon("LoginButton_Clicked.png");
         loginClicked.paintIcon(this,g,211,305);
      }
      if(mouseOverSignUp){
         ImageIcon signClicked = new ImageIcon("SignButton_Clicked.png");
         signClicked.paintIcon(this,g,431,304);
      }
      if(userNotFoundError){
         g.setColor(Color.red);
         Font errorFont = new Font("Small Fonts", Font.BOLD,16);
         g.setFont(errorFont);
         g.drawString("User not found",593,180);
      }
      if(nameTaken){
         g.setColor(Color.red);
         Font errorFont = new Font("Small Fonts", Font.BOLD,16);
         g.setFont(errorFont);
         g.drawString("Name Taken",593,180);
      }
      if(passwordError){
         g.setColor(Color.red);
         Font errorFont = new Font("Small Fonts", Font.BOLD,16);
         g.setFont(errorFont);
         g.drawString("Password incorrect",594,252);
      }
   }
   private void addTextFields(){
      name = new JTextField();
      password = new JPasswordField();
      name.setOpaque(false); password.setOpaque(false); 
      name.setFont(new Font("Small Fonts",Font.BOLD,20)); password.setFont(new Font("Small Fonts",Font.BOLD,20));
      frame.add(name); frame.add(password);
      name.setBounds(230, 163, 356, 27); password.setBounds(229, 234, 356, 27);
   }
   private void removeLogin(){
      frame.remove(this);
      frame.remove(name);
      frame.remove(password);
      frame.removeMouseListener(this);  
   }
   public void actionPerformed(ActionEvent e){
      timer.stop();
      mouseOverLogin = false;
      mouseOverSignUp = false;
      frame.repaint();
   }
   public void mousePressed(MouseEvent e) {
   }

   public void mouseReleased(MouseEvent e) {
   
   }

   public void mouseEntered(MouseEvent e) {
   
   }

   public void mouseExited(MouseEvent e) {
   
   }

   public void mouseClicked(MouseEvent e) {
      if((e.getX()>= 220 && e.getX() <= 392) && (e.getY() >= 335 && e.getY() <= 400)){
         mouseOverLogin = true;
         timer.start();
         if(!data.containsUserName(name.getText())){userNotFoundError = true;}
         else{
            userNotFoundError = false;
            if(manager.loginUser(name.getText(),password.getPassword())){
               passwordError = false;
               removeLogin();
               frame.setVisible(false);
               frame.dispose();
               new MailBox(manager.getUserLogged(),frame,manager.data);
               frame.repaint();
            }
            else{
               passwordError = true;
            }
         }
      }
      else{
         mouseOverLogin = false;
         frame.repaint();
      }
      if((e.getX()>= 442 && e.getX() <= 614) && (e.getY() >= 335 && e.getY() <= 400)){
         mouseOverSignUp = true;
         timer.start();
         passwordError = false;
         userNotFoundError = false;
         if(name.getText().length() > 20){JOptionPane.showMessageDialog(null,"User Name too long");}
         else{
            if((new String(password.getPassword()).length() >= 8) && name.getText().length() >= 3){
               nameTaken = manager.signUser(name.getText(),password.getPassword());
               frame.repaint();
            }
            else if(!(new String(password.getPassword()).length() >= 8) && name.getText().length() >= 3){
               JOptionPane.showMessageDialog(null,"Password too short");
            }
            else {
               JOptionPane.showMessageDialog(null,"User name too short");
            }
         }
      }
      else{
         mouseOverSignUp = false;
         frame.repaint();
      }
     
   }

}