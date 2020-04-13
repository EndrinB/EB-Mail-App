import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

public class Deleter implements ItemListener,ActionListener {
   JButton submit,checkAll,uncheckAll;
   List<JCheckBox> check;
   ArrayList<Integer> messagesDeleted = new ArrayList<Integer>();
   User user;
   JFrame frame;

   public Deleter(User user) {
      this.user = user;
      frame = new JFrame("Deleter");
      JPanel panel = new JPanel(new GridLayout(0, 1));
      Border border = BorderFactory.createTitledBorder("Delete Messages");
      JScrollPane scrPane = new JScrollPane(panel);
      panel.setBorder(border);
      JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
   
      checkAll = new JButton("Check All");
      uncheckAll = new JButton("Uncheck All");
   
      btnPnl.add(checkAll);
      btnPnl.add(uncheckAll);
   
      panel.add(btnPnl, BorderLayout.SOUTH);
      check = new ArrayList<JCheckBox>();
    
      int i = 0;
   
      while(i < user.getMessages().size()){
         check.add(new JCheckBox(user.getMessage(i)));
         panel.add(check.get(i));
         check.get(i).addItemListener(this);
         i++;
      
      }
      submit = new JButton("Submit");
      Container contentPane = frame.getContentPane();
      contentPane.add(scrPane, BorderLayout.CENTER);
      contentPane.add(submit, BorderLayout.SOUTH);
      submit.addActionListener(this);
      checkAll.addActionListener(this);
      uncheckAll.addActionListener(this);
      frame.setSize(400, 450);
      frame.setVisible(true);
   }
   public void itemStateChanged(ItemEvent e){
      JCheckBox d = (JCheckBox)e.getItem();
      if(check.contains(d)){
         int num = check.indexOf(d) +1;
         if(e.getStateChange() == ItemEvent.SELECTED){
            messagesDeleted.add(num);
         }
         if(e.getStateChange() == ItemEvent.DESELECTED){
            messagesDeleted.remove(messagesDeleted.indexOf(num));
         }
      }
   }
   public void actionPerformed(ActionEvent e){
     
      if(e.getSource() == submit){
        Collections.sort(messagesDeleted);
         if(messagesDeleted.size() == 1){
            user.removeMessage(messagesDeleted.get(0));
         }
         else{
            int i = 0;
            int mdeleted = 0;
            while(i < messagesDeleted.size()){
               if(messagesDeleted.get(i) == messagesDeleted.get(messagesDeleted.size()-1)){
               user.removeMessage(messagesDeleted.get(i)-mdeleted);   
               break;
               }
               else{
               user.removeMessage(messagesDeleted.get(i)-mdeleted);   
               i++;
               mdeleted++;
               }
            }
         
         
         }
         
         JOptionPane.showMessageDialog(null,"Messages deleted");
         messagesDeleted.clear();
         frame.setVisible(false);
         frame.dispose();
      }
      if(e.getSource() == checkAll){
         int i = 0;
         while(i < check.size()){
            check.get(i).setSelected(true);
            i++;
         }
      }
      if(e.getSource() == uncheckAll){
         int i = 0;
         while(i < check.size()){
            check.get(i).setSelected(false);
            i++;
         }
      }
   }
}