package bank_management_atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

public class Withdrawl extends JFrame implements ActionListener {

    JLabel atm_image,main_text;
    JTextField withdrawl_field;
    JButton withdrawal_button,Cancel;
    String pinString;
    Withdrawl(String pinString)
{
    this.pinString = pinString;

    ImageIcon i1 = new ImageIcon(this.getClass().getResource("/icons/atm_machine.jpg"));
    Image i2  = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);

    atm_image =  new JLabel(i3);
    atm_image.setBounds(0,0,900,900);

    main_text= new JLabel("Amount to Withdrawl");
	main_text.setBounds(270,330,1000,25);
	main_text.setForeground(Color.WHITE);
	main_text.setFont(new Font("Raleway",Font.BOLD,15));
	add(main_text);

	withdrawl_field = new JTextField();
	withdrawl_field.setBounds(270,380,350,35);
    withdrawl_field.addActionListener(this);
	add(withdrawl_field);
			
	withdrawal_button = new JButton("Withdraw");
	withdrawal_button.setFont(new Font("Raleway",Font.BOLD,11));main_text.setForeground(Color.white);
	withdrawal_button.setBounds(270,445,135,25);
	withdrawal_button.addActionListener(this);

    
    withdrawal_button.setBackground(Color.BLACK);

    withdrawal_button.setForeground(new Color(243,189,86));
	add	(withdrawal_button);

			
	Cancel= new JButton("CANCEL");
	Cancel.setBounds(495,445,135,25);
	Cancel.setFont(new Font("Raleway",Font.BOLD,10));
    
    Cancel.setBackground(Color.BLACK);

    Cancel.setForeground(Color.RED);
    
	Cancel.addActionListener(this);
	add(Cancel);	

	
	setSize(900,900);
	setLocation(300,0);
	add(atm_image);
	setVisible(true);
	setLayout(null);


}
    

public static void main(String[] args) 
{
//  new Withdrawl("").setVisible(true);;
}


public  void actionPerformed (ActionEvent  ac)
{

    if (ac.getSource()==Cancel)
    {

        Cancel.setBackground(Color.RED);

    setVisible(false);
    new Transactions(pinString).setVisible(true);
    }

    else if(ac.getSource() == withdrawl_field)
    {
        withdrawal_button.doClick();
    }

    else if (ac.getSource() == withdrawal_button) 
    {

        withdrawal_button.setBackground(new Color(242,99,111));
    String with_String = withdrawl_field.getText();
    int with_int = Integer.parseInt(with_String);
    int confirm2 = JOptionPane.showConfirmDialog(null, "Are you sure want to deposit " + with_String+" rupees", "CONFORMATION", JOptionPane.YES_NO_OPTION);
	if(confirm2 == JOptionPane.NO_OPTION)
	{
	setVisible(false);
	new Withdrawl(pinString).setVisible(true);
	}

    else{

        String check =JOptionPane.showInputDialog("enter the pin");
				
		if (check.equals(pinString)) 
        {
                int With_amount= Integer.parseInt(with_String);
                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = date.format(formatter);
                try{
                    Conn c = new Conn();
                    String query = "SELECT b.amount, COALESCE(w.with_amount, 0) " + "from bank b "+"INNER JOIN withdrawal w " + " ON b.pin_number = w.pin_number "+" WHERE b.pin_number = ?";
                    PreparedStatement pp = c.co.prepareStatement(query);
                    pp.setString(1, pinString);

                    int dep_amt=0; int with_amount=0; int current_amount;
                    ResultSet rs = pp.executeQuery();  
            
                    while (rs.next())    
                    {
                        dep_amt = dep_amt + rs.getInt(1);
                        with_amount = with_amount+ rs.getInt(2);
                    }            
                    System.out.println(dep_amt);
                    System.out.println(with_amount);
                    current_amount = dep_amt-with_amount;
            
                    if (current_amount > with_int) 
                        {
                            try {
                                
                                String query1 = "insert into withdrawal (pin_number,with_amount,with_date,type) values (?,?,?,?)";
                                PreparedStatement pp1 = c.co.prepareStatement(query1);
                        
                                pp1.setString(1, pinString);
                                pp1.setInt(2,with_int);
                                pp1.setString(3,formattedDateTime);
                                pp1.setString(4, "WITHDRAWAL");
                                int n = pp1.executeUpdate();
                                if (n>0) {
                                    
                                    JOptionPane.showMessageDialog(null, "Successfully withdrawal "+ with_int+" Rupees");
                                    
                                                setVisible(false);
                                                new Transactions(pinString).setVisible(true);
                                            
                                                pp1.close();
                                }
                            } 
                            catch (Exception e) {
                                // TODO: handle exceptio
                                JOptionPane.showMessageDialog(null,"e", "CANNOT PROCEED", JOptionPane.WARNING_MESSAGE);
                                
                            }
            
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,dep_amt, "CANNOT PROCEED", JOptionPane.WARNING_MESSAGE);
                        }
                    
                    }
            
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e, "Please try after some time",JOptionPane.WARNING_MESSAGE);
                        }
            



        
        
        
        }
        else{   // pin wala else

            JOptionPane.showMessageDialog(null, "Invalid pin","try again",JOptionPane.WARNING_MESSAGE);
        }

    }
            
    
    



}
}
}
