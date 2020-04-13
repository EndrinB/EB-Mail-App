import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

public class ChangePass implements ActionListener {
   JButton submit;
   JPasswordField currentPass = new JPasswordField();
   JPasswordField newPass = new JPasswordField();
   JPasswordField confirmNewPass = new JPasswordField();
   String newPassword = "";
   User user;
   Database data;
   JFrame frame;

   public ChangePass(User user,Database data) {
      this.user = user;
      this.data = data;
      frame = new JFrame("Password Changer");
      JLabel a = new JLabel("Current Password");
      JLabel b = new JLabel("New Password");
      JLabel c = new JLabel("Confirm New Password");
      GridLayout lay = new GridLayout(0,1);
      lay.setVgap(8);
      JPanel panel = new JPanel(lay);
      Border border = BorderFactory.createTitledBorder("Change Password");
      JScrollPane scrPane = new JScrollPane(panel);
      panel.setBorder(border);
      currentPass.addActionListener(this);
      newPass.addActionListener(this);
      confirmNewPass.addActionListener(this);
   
      submit = new JButton("Submit");
      Container contentPane = frame.getContentPane();
      contentPane.add(scrPane, BorderLayout.CENTER);
      contentPane.add(submit, BorderLayout.SOUTH);
      submit.addActionListener(this);
      panel.add(a);
      panel.add(currentPass);
      panel.add(b);
      panel.add(newPass);
      panel.add(c);
      panel.add(confirmNewPass);
      frame.setSize(400, 450);
      frame.setVisible(true);
   }
 
   public void actionPerformed(ActionEvent e){
     
      if(e.getSource() == submit){
         if(!user.getPassword().equals(new String(currentPass.getPassword()))){
            JOptionPane.showMessageDialog(null,"Password doesnt match current password");
         }
         else if(user.getPassword().equals(new String(newPass.getPassword()))){
            JOptionPane.showMessageDialog(null,"Current Password is the same as new Password");
         }
         else{
         
            if(new String(newPass.getPassword()).equals(new String(confirmNewPass.getPassword()))){
               newPassword = new String(newPass.getPassword());
               JOptionPane.showMessageDialog(null,"Password Changed");
               user.changePassword(newPassword);
               data.changePassword(user.getUserKey(),newPassword);
               frame.setVisible(false);
               frame.dispose();
            }
            else{
               JOptionPane.showMessageDialog(null,"new Password doesnt match confirm password");
            }
         }
         
      }
   }
}