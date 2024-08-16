package bank_management_atm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.EventListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class fast_cash extends JFrame implements ActionListener{

JButton hundred, two_hundred,five_hundred,thousand, two_thousand, five_thousand , exitButton;
	String pinstring;

    
public fast_cash(String pinString)
{
    this.pinstring= pinString;
    ImageIcon i1 = new ImageIcon (this.getClass().getResource("/icons/atm_machine.jpg"));
    Image i2 = i1.getImage().getScaledInstance(900, 900,Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    
    JLabel atm_image = new JLabel(i3);
    atm_image.setBounds(0,0,900,900);
    
    
//

        JLabel main_text =new JLabel("Select fast CASH");
		main_text.setBounds(350,45,400,30);
		main_text.setFont(new Font("Raleway",Font.BOLD,22));main_text.setForeground(Color.white);
		atm_image.add(main_text);

		
		hundred = new JButton("100");
		hundred.setBounds(270,350,100,25);
		hundred.addActionListener(this);
        hundred.setBackground(Color.BLACK);

        hundred.setForeground(new Color(143,198,251));
		add(hundred);
        
		two_hundred = new JButton("200");
		two_hundred.setBounds(270,400,100,25);
		two_hundred.addActionListener(this);
        two_hundred.setBackground(Color.BLACK);

        two_hundred.setForeground(new Color(143,198,251));
		add(two_hundred);

		five_hundred = new JButton("500");
		five_hundred.setFont(new Font("Raleway",Font.BOLD,11));main_text.setForeground(Color.white);
		five_hundred.setBounds(270,450,100,25);
		five_hundred.addActionListener(this);
        five_hundred.setBackground(Color.BLACK);

        five_hundred.setForeground(new Color(143,198,251));
		add(five_hundred);
		
		thousand = new JButton("1000");
		thousand.setBounds(495,350,135,25);
		thousand.setFont(new Font("Raleway",Font.BOLD,11));
        thousand.addActionListener(this);

        thousand.setBackground(Color.BLACK);

        thousand.setForeground(new Color(246,88,88));
		add(thousand);

		two_thousand = new JButton("2000");
		two_thousand.setBounds(495,400,135,25);
		two_thousand.setFont(new Font("Raleway",Font.BOLD,10));
        two_thousand.addActionListener(this);

        two_thousand.setBackground(Color.BLACK);

        two_thousand.setForeground(new Color(246,88,88));
        add(two_thousand);

		five_thousand= new JButton("5000");
		five_thousand.setBounds(495,450,135,25);
		five_thousand.setFont(new Font("Raleway",Font.BOLD,10));
        five_thousand.addActionListener(this);
        
        five_thousand.setBackground(Color.BLACK);

        five_thousand.setForeground(new Color(246,88,88));

        add(five_thousand);


		exitButton = new JButton("BACK");
		exitButton.setBounds(240+50+7,637,310,47);
		exitButton.setFont(new Font("Raleway",Font.BOLD,18));
		exitButton.setBackground(Color.red);
        exitButton.addActionListener(this);
    	add(exitButton);
    
    
//    
    setSize(900,900);
    setLayout(null);
    setLocation(300,0);
    add(atm_image);
    setVisible(true);

}


    public static void main(String[] args) 
    {
        // new fast_cash("6692");
    }





      void fast_cash (int amount_fast)
    {


        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dd = DateTimeFormatter.ofPattern("yyyy-MM-dd      HH:mm:ss");
        String formattedDateTime = date.format(dd);
        

        String check =JOptionPane.showInputDialog("enter the pin");
				
		if (check.equals(pinstring)) 
        {



            try{
            Conn c = new Conn();
            String query = "SELECT b.amount, COALESCE(w.with_amount, 0) " + "from bank b "+"INNER JOIN withdrawal w " + " ON b.pin_number = w.pin_number "+" WHERE b.pin_number = ?";
            PreparedStatement pp = c.co.prepareStatement(query);
            pp.setString(1, pinstring);
            int dep_amt=0;int with_amount=0;int current_amount;
            ResultSet rs = pp.executeQuery();  
    
            while (rs.next())    
            {
                dep_amt = dep_amt + rs.getInt(1);
                with_amount = with_amount+ rs.getInt(2);
            }            
            System.out.println(dep_amt);
            System.out.println(with_amount);
            current_amount = dep_amt-with_amount;
    
            if (current_amount > amount_fast) 
                {
                    try {
                        
                        String query1 = "insert into withdrawal (pin_number,with_amount,with_date,type) values (?,?,?,?)";
                        PreparedStatement pp1 = c.co.prepareStatement(query1);
                
                        pp1.setString(1, pinstring);
                        pp1.setInt(2,amount_fast);
                        pp1.setString(3,formattedDateTime);
                        pp1.setString(4, "FAST CASH");
                        int n = pp1.executeUpdate();
                        if (n>0) {
                            
                            JOptionPane.showMessageDialog(null, "Successfully withdrawal "+ amount_fast+" Rupees");
                            
                                        setVisible(false);
                                        new Transactions(pinstring).setVisible(true);
                                    
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
                    JOptionPane.showMessageDialog(null,"INSUFFICIENT BALANCE", "CANNOT PROCEED", JOptionPane.WARNING_MESSAGE);
                }
            
            }
            
            catch(Exception e)
    
            {
                JOptionPane.showMessageDialog(null,e, "Please try after some time",JOptionPane.WARNING_MESSAGE);
            }
            
        }
        
        else
        {
            
            JOptionPane.showMessageDialog(null,"INVALID PIN", "CANNOT PROCEED",JOptionPane.WARNING_MESSAGE);

        }

        }
        
        


    

    public int cofirmation(int rupees)
    {
        
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to withdrawal " + rupees+" rupees", "CONFORMATION", JOptionPane.YES_NO_OPTION);

        if ( confirm == JOptionPane.YES_OPTION)
        {
           return 1; 
        }
        

        else
        {
            setVisible(false);
            new fast_cash(pinstring).setVisible(true);
            return 0;

        }
    }

    public void actionPerformed(ActionEvent ac)
    {

    if ( ac.getSource() == hundred)
    {   
        hundred.setBackground(new Color(239,206,105));
        int proceed = cofirmation(100);
        if (proceed == 1) {
            fast_cash(100);
        }
    }
    else if ( ac.getSource() == two_hundred)
    {
        two_hundred.setBackground(new Color(239,206,105));
        int proceed = cofirmation(200);
        if (proceed == 1) {
            fast_cash(200);
        }
    }
    else if ( ac.getSource() == five_hundred)
    {
        five_hundred.setBackground(new Color(239,206,105));
        int proceed = cofirmation(500);
        if (proceed == 1) {
            fast_cash(500);
        }
    }
   else if ( ac.getSource() == thousand)
    {

        thousand.setBackground(new Color(239,206,105));
        int proceed = cofirmation(1000);
        if (proceed == 1) {
            fast_cash(1000);
        }
    }
   else if ( ac.getSource() == two_thousand)
    {

        two_thousand.setBackground(new Color(239,206,105));
        int proceed = cofirmation(2000);
        if (proceed == 1) {
            fast_cash(2000);
        }
    }
    else if ( ac.getSource() == five_thousand)
    {
        five_thousand.setBackground(new Color(239,206,105));
        int proceed = cofirmation(5000);
        if (proceed == 1) {
            fast_cash(5000);
        }
    }

    else if ( ac.getSource() == exitButton)
    {
        setVisible(false);
        new Transactions(pinstring);

    }

}



}
