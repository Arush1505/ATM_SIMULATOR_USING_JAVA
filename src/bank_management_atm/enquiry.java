package bank_management_atm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class enquiry extends JFrame implements ActionListener {
 JLabel main_text,balance; String pinString; JButton Cancel;int current_amount;

public enquiry(String pinstring) {
        //TODO Auto-generated constructor stub
    this.pinString = pinstring;
        ImageIcon i1= new ImageIcon(this.getClass().getResource("/icons/atm_machine.jpg"));
		Image i2= i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
		
		ImageIcon i3= new ImageIcon(i2);

		JLabel atm_image = new JLabel(i3);
		atm_image.setBounds(0,0,900,900);
	
        
        
        try{
            Conn c = new Conn();
            String query = "SELECT b.amount, COALESCE(w.with_amount, 0) " + "from bank b "+"INNER JOIN withdrawal w " + " ON b.pin_number = w.pin_number "+" WHERE b.pin_number = ?";
            PreparedStatement pp = c.co.prepareStatement(query);
            pp.setString(1, pinstring);
            int dep_amt=0;int with_amount=0;
            ResultSet rs = pp.executeQuery();  
    
            while (rs.next())    
            {
                dep_amt = dep_amt + rs.getInt(1);
                with_amount = with_amount+ rs.getInt(2);
            }            
            System.out.println(dep_amt);
            System.out.println(with_amount);
            current_amount = dep_amt-with_amount;
        }
        
        catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e, "Please try after some time",JOptionPane.WARNING_MESSAGE);
            }












    main_text = new JLabel("Your Current balance is :");
    main_text.setBounds(270,350,300,25);
    main_text.setFont(new Font("Raleway",Font.BOLD,15));main_text.setForeground(Color.PINK);
    main_text.setBackground(Color.BLACK);
add(main_text);

String currentString = Integer.toString(current_amount);

balance = new JLabel("₹ "+currentString);
balance.setBounds(400,400,300,25);
balance.setFont(new Font("Raleway",Font.BOLD,25));balance.setForeground(Color.GREEN);
balance.setBackground(Color.BLACK);
add(balance);



Cancel= new JButton("BACK");
Cancel.setBounds(240+50+7,637,310,47);
Cancel.setFont(new Font("Raleway",Font.BOLD,20));
Cancel.setBackground(Color.darkGray);
Cancel.setForeground(Color.LIGHT_GRAY);
Cancel.setBorderPainted(false);
Cancel.addActionListener(this);
add(Cancel);	

    
    
    

        setSize(900,900);
        setLocation(150,0);
        setLayout(null);
    //		setUndecorated(true);
    add(atm_image);
    setVisible(true);
    
    
    
    
    
    
}
    
    
    public static void main(String[] args) {
        new enquiry("6692");
    }


    @Override
    public void actionPerformed(ActionEvent ac) {
        // TODO Auto-generated method stub
        if ( ac.getSource()==Cancel){
			setVisible(false);
			new Transactions(pinString).setVisible(true);
		}
    }
    
    
}

