package bank_management_atm;
import java.awt.Color;


import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.lang.invoke.StringConcatFactory;

// import java.net.HttpRetryException;
// import java.time.YearMonth;

import javax.swing.*;


public class Transactions extends JFrame implements ActionListener  { 
JButton fastcashButton, depositButton, withdrawlButton, exitButton ,ministatementButton, pinchangeButton, balanceenquiryButton;
	String pinstring;
	Transactions(String pinString)
	{

		setResizable(false);
		this.pinstring= pinString;
		ImageIcon i1= new ImageIcon(this.getClass().getResource("/icons/atm_machine.jpg"));
		Image i2= i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
		
		ImageIcon i3= new ImageIcon(i2);

		JLabel atm_image = new JLabel(i3);
		atm_image.setBounds(0,0,900,900);

		JLabel main_text =new JLabel("Please Select your MODE");
		main_text.setBounds(300,45,400,30);
		main_text.setFont(new Font("Raleway",Font.BOLD,22));main_text.setForeground(Color.white);
		atm_image.add(main_text);

		
		fastcashButton = new JButton("FAST CASH");
		fastcashButton.setBounds(270,350,130,25);
		fastcashButton.addActionListener(this);
		fastcashButton.setBackground(Color.BLACK);

		fastcashButton.setForeground(Color.YELLOW);
		add(fastcashButton);
		
		depositButton = new JButton("DEPOSIT");
		depositButton.setBounds(270,400,130,25);
		depositButton.addActionListener(this);
		depositButton.setBackground(Color.BLACK);

		depositButton.setForeground(Color.GREEN);
		add(depositButton);
		
		withdrawlButton = new JButton("WITHDRWAL");
		withdrawlButton.setFont(new Font("Raleway",Font.BOLD,11));main_text.setForeground(Color.white);
		withdrawlButton.setBounds(270,450,130,25);
		withdrawlButton.setBackground(Color.BLACK);

		withdrawlButton.setForeground(Color.YELLOW);
		withdrawlButton.addActionListener(this);
		add(withdrawlButton);

		
		pinchangeButton = new JButton("PIN CHANGE");
		pinchangeButton.setBounds(495,350,135,25);
		pinchangeButton.setFont(new Font("Raleway",Font.BOLD,11));
		pinchangeButton.addActionListener(this);
		pinchangeButton.setBackground(Color.BLACK);

		pinchangeButton.setForeground(Color.GREEN);
		add(pinchangeButton);
		ministatementButton = new JButton("MINI STATEMENT");
		ministatementButton.setBounds(495,400,135,25);
		ministatementButton.setFont(new Font("Raleway",Font.BOLD,10));
		ministatementButton.setBackground(Color.BLACK);

		ministatementButton.setForeground(Color.yellow);
		ministatementButton.addActionListener(this);
		add(ministatementButton);

		balanceenquiryButton= new JButton("BALANCE ENQUIRY");
		balanceenquiryButton.setBounds(495,450,135,25);
		balanceenquiryButton.setFont(new Font("Raleway",Font.BOLD,10));
		balanceenquiryButton.setBackground(Color.BLACK);

		balanceenquiryButton.setForeground(Color.GREEN);
		balanceenquiryButton.addActionListener(this);
		add(balanceenquiryButton);

		exitButton = new JButton("EXIT");
		exitButton.setBounds(240+50+7,637,310,47);
		exitButton.setFont(new Font("Raleway",Font.BOLD,18));
		exitButton.setBackground(Color.red);
		exitButton.addActionListener(this);
;		add(exitButton);

		
		
		setSize(900,900);
		setLocation(150,0);
		setLayout(null);
		
//		setUndecorated(true);
		add(atm_image);
		setVisible(true);
		
		
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub  
//  new Transactions("").setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		// TODO Auto-generated method stub
		if (ac.getSource()==depositButton)
		{
			setVisible(false);
			new Deposit(pinstring).setVisible(true);
		}
		
		if (ac.getSource()==withdrawlButton)
		{
			setVisible(false);
			new Withdrawl(pinstring).setVisible(true);
		}
		
		if (ac.getSource()==fastcashButton)
		{
			setVisible(false);
			new fast_cash(pinstring).setVisible(true);
		}
		
		if (ac.getSource()==ministatementButton)
		{
			new Mini_statement1(pinstring).setVisible(true);
		}
		
		if (ac.getSource()==balanceenquiryButton)
		{
			new enquiry(pinstring).setVisible(true);
		}

		if(ac.getSource() == pinchangeButton)
		{
			setVisible(false);
			new pin_change(pinstring);
		}

		if(ac.getSource() == exitButton)
		{
			setVisible(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

}
