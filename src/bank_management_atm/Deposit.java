package bank_management_atm;
import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Deposit extends JFrame implements ActionListener 
{	JLabel main_text;
	JTextField deposit_field;
	JButton Deposit,Cancel;
	String pinString;
	 public Deposit(String pinString)  {
		this.pinString = pinString;
		 ImageIcon i1= new ImageIcon(this.getClass().getResource("/icons/atm_machine.jpg"));
			Image i2= i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
			
			ImageIcon i3= new ImageIcon(i2);

			JLabel atm_image = new JLabel(i3);
			atm_image.setBounds(0,0,900,900);

			
			
			
			
			main_text= new JLabel("Enter  The  Amount  To  Be  Deposited");
			main_text.setBounds(270,330,1000,25);
			main_text.setForeground(Color.WHITE);
			main_text.setFont(new Font("Raleway",Font.BOLD,15));
			add(main_text);
			
			
			deposit_field = new JTextField();
			deposit_field.setBounds(270,380,350,35);
			deposit_field.addActionListener(this);
			add(deposit_field);
			
			
		Deposit = new JButton("DEPOSIT");
		Deposit.setFont(new Font("Open Sans",Font.BOLD,11));main_text.setForeground(Color.white);
		Deposit.setBounds(270,450,100,25);
		Deposit.addActionListener(this);
		
		Deposit.setBackground(Color.BLACK);

		Deposit.setForeground(new Color(120,249,220));
		add	(Deposit);
		
		Cancel= new JButton("CANCEL");
		Cancel.setBounds(495,450,135,25);
		Cancel.setFont(new Font("Raleway",Font.BOLD,10));
		Cancel.addActionListener(this);
		
		Cancel.setBackground(Color.BLACK);

		Cancel.setForeground(Color.RED);
		add(Cancel);	
			
			
			
			
			
			
			
			
			
			setSize(900,900);
			setLocation(150,0);
			setLayout(null);
			
			// setUndecorated(true);
			add(atm_image);
			setVisible(true);
			
		 // TODO Auto-generated constructor stub
	}
	
	
	
	public static void main(String[] args) 
	{
		//  new Deposit("");
	}

	@Override
	public void actionPerformed(ActionEvent ac)
	{
		
		if ( ac.getSource()==Cancel){
			Cancel.setBackground(Color.RED);

			setVisible(false);
			new Transactions(pinString).setVisible(true);
		}

		if(ac.getSource()==deposit_field)
		{
			Deposit.doClick();
		}

		else if(ac.getSource()==Deposit) {

			Deposit.setBackground(Color.BLACK);

			String deposit_amt = deposit_field.getText();
			

			int deposit_int = Integer.parseInt(deposit_amt);

			int confirm1 = JOptionPane.showConfirmDialog(null, "Are you sure want to deposit " + deposit_amt+" rupees", "CONFORMATION", JOptionPane.YES_NO_OPTION);
			if(confirm1 == JOptionPane.NO_OPTION)
			{
				setVisible(false);
				new Deposit(pinString).setVisible(true);
			}
				
			else{

				String check =JOptionPane.showInputDialog("Enter the pin");
				
				if (check.equals(pinString)) {
					
					if (deposit_amt.equals(""))
					{
					JOptionPane.showMessageDialog(null,"ENTER SOME AMOUNT","WARNING",JOptionPane.WARNING_MESSAGE);	
					}
	
					LocalDateTime date = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String formattedDateTime = date.format(formatter);
	
	
				
					Conn c = new Conn();
				
					try {
						String query = "insert into bank (pin_number, date , amount) values (? ,? ,?) ";
						PreparedStatement pp = c.co.prepareStatement(query);
						pp.setString(1, pinString);
						pp.setString(2, formattedDateTime);
						pp.setString(3, deposit_amt);
						// pp.setInt(4,deposit_int);
						int rows_affected = pp.executeUpdate();
						if (rows_affected>0)
						{
							JOptionPane.showMessageDialog(null, "The deposit of "+deposit_amt+" is successful","Congratulation",JOptionPane.DEFAULT_OPTION);
							deposit_field.setText("");

							try 
							{

								String query_with = "INSERT INTO withdrawal (pin_number) SELECT ? FROM dual WHERE NOT EXISTS (SELECT 1 FROM withdrawal  WHERE pin_number = ?)";
								PreparedStatement pp1 = c.co.prepareStatement(query_with);
								pp1.setString(1, pinString);

								pp1.setString(2, pinString);
								int rows_with = pp1.executeUpdate();
								if (rows_with>0) {
									JOptionPane.showMessageDialog(null, "DATA UPDATED");
								}
								else{
									JOptionPane.showMessageDialog(null, "YOUR DATA UPDATED");
									
								}

							}
							
							catch (Exception e) 
							{
								JOptionPane.showMessageDialog(null, e);
							}



						}
				}
				
					catch (Exception e) 
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e, "warning",JOptionPane.WARNING_MESSAGE);
					}

				}	
				else{

					JOptionPane.showMessageDialog(null, "Invalid pin","try again",JOptionPane.WARNING_MESSAGE);
				}




						
				

			}
			
		}
	}
	

}