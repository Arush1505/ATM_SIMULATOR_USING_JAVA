package bank_management_atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class pin_change extends JFrame implements ActionListener{

    JLabel prev_pin,new_pin;
    JPasswordField prevField,newField;
    String pinString;
    JButton pin_change , Cancel;
public pin_change(String pinString)

{
  this.pinString = pinString;

	ImageIcon i1= new ImageIcon(this.getClass().getResource("/icons/atm_machine.jpg"));
		Image i2= i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
		
		ImageIcon i3= new ImageIcon(i2);

		JLabel atm_image = new JLabel(i3);
		atm_image.setBounds(0,0,900,900);
	


    prev_pin = new JLabel("Current pin");
    prev_pin.setBounds(270,350,130,25);
    // prev_pin.addActionListener(this);
    prev_pin.setFont(new Font("Raleway",Font.BOLD,20));
    prev_pin.setBackground(Color.BLACK);
    prev_pin.setForeground(new Color(244,180,135));
    add(prev_pin);



    prevField = new JPasswordField();
    prevField.setBounds(495,350,135,25);
    // prevField.setFont(new Font("Raleway",Font.BOLD,11));
    add(prevField);



    new_pin = new JLabel("New Pin");
    new_pin.setBounds(270,400,130,25);
    // new_pin.addActionListener(this);
    new_pin.setFont(new Font("Raleway",Font.BOLD,20));
    new_pin.setBackground(Color.BLACK);

    new_pin.setForeground(new Color(104,217,112));
    add(new_pin);



    newField = new JPasswordField();
    newField.setBounds(495,400,135,25);
    // newField.setFont(new Font("Raleway",Font.BOLD,10));

    // newField.addActionListener(this);
    add(newField);


    pin_change = new JButton("Change pin");
    pin_change.setFont(new Font("Raleway",Font.BOLD,15));pin_change.setForeground(Color.GREEN);
    pin_change.setBackground(Color.BLACK);
    pin_change.setBounds(270,450,135,25);
    add	(pin_change);
    pin_change.addActionListener(this);
      
      
      
      Cancel= new JButton("CANCEL");
      Cancel.setBounds(495,450,135,25);
      Cancel.setFont(new Font("Raleway",Font.BOLD,15));Cancel.setForeground(Color.RED);
      Cancel.setBackground(Color.BLACK);
      Cancel.addActionListener(this);
      add(Cancel);	
        
  




    		
		setSize(900,900);
		setLocation(150,0);
		setLayout(null);
		
//		setUndecorated(true);
		add(atm_image);
		setVisible(true);
		
}






    public static void main(String[] args) 
{
    
// new pin_change("");

}






    @Override
    public void actionPerformed(ActionEvent ac) {
      // TODO Auto-generated method stub



      String new_pin = newField.getText();
      String prev_pin = prevField.getText();
      
		if ( ac.getSource()==Cancel){
      Cancel.setBackground(Color.RED);

			setVisible(false);
			new Transactions(pinString).setVisible(true);
		}

    else if ( ac.getSource() == pin_change)
    {
      pin_change.setBackground(Color.YELLOW);

      try {

        Conn c  = new Conn();
        String query = " UPDATE bank  "+"SET pin_number = ? "+"WHERE pin_number = ?";
        PreparedStatement pp = c.co.prepareStatement(query);
        pp.setString(1, new_pin);
        pp.setString(2, prev_pin);
       int rows_affect = pp.executeUpdate();
        
        String query1 = " UPDATE login  "+"SET pin_number = ? "+"WHERE pin_number = ?";
        PreparedStatement pp1 = c.co.prepareStatement(query1);
        pp1.setString(1, new_pin);
        pp1.setString(2, prev_pin);
       int rows_affect1 = pp1.executeUpdate();
       
       String query2 = " UPDATE withdrawal  "+"SET pin_number = ? "+"WHERE pin_number = ?";
       PreparedStatement pp2 = c.co.prepareStatement(query2);
       pp2.setString(1, new_pin);
       pp2.setString(2, prev_pin);
      int rows_affect2 = pp2.executeUpdate();


       if (rows_affect > 0   ) 
       {
        JOptionPane.showMessageDialog(null, " pin updated successfully");
       }

       else{
        JOptionPane.showMessageDialog(null, "Wrong Pin","WARNING",JOptionPane.WARNING_MESSAGE);
       }
        
      }
      
      catch (Exception e)
      {

        JOptionPane.showMessageDialog(null, e,"WARNING",JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();// TODO: handle exception
      }


    }

    }


}
