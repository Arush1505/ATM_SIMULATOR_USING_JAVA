	package bank_management_atm;

	import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

import java.util.Random;
import java.util.Date;
//import com.toedter.calendar.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class sign_up extends JFrame implements ActionListener
{
	JLabel main_text,application_text,name,Father,Address,DOB,Gender,marital,state,pin;
	long app_form;
	JButton nextButton;
	JTextField nameField,fatherField,addressField,stateField,pinField;
	JDateChooser dob_chooser ;
	JRadioButton maleButton,femaleButton,lgbtqButton,marriedButton,unmarriedButton,divorceButton;


		sign_up()
		{

		getContentPane().setBackground(Color.CYAN);
		setTitle("ATM machine");
		
		setSize(800,800);
		setLocation(100,100);
		setLayout(null);
		

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height - 40; // subtract 40 to account for the task bar
		setBounds(0, 0, width, height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		main_text = new JLabel("SIGN UP (Page 1)");
		main_text.setBounds(650,45,300,100);
		main_text.setFont(new Font("Raleway",Font.BOLD,30));
		add(main_text);
		
		Random rand = new Random();
		long min = 1000L;long max =9000L;
		app_form = min + (long) (rand.nextDouble()*(max- min));
		
		
		application_text= new JLabel("APPLICATION FORM : "+app_form);

		application_text.setFont(new Font("Raleway",Font.BOLD,20));
		application_text.setBounds(50,30,500,100);
		add(application_text);
		
		name= new JLabel("NAME:");
		name.setFont(new Font("Raleway",Font.BOLD,20));
		name.setBounds(200,150,500,100);
		add(name);
		
		nameField = new JTextField();
		nameField.setBounds(500,185,500,30);
		nameField.addActionListener(this);
		add(nameField);
		
		
		Father= new JLabel("Father's Name:");
		Father.setFont(new Font("Raleway",Font.BOLD,20));
		Father.setBounds(200,200,500,100);
		add(Father);
		
		fatherField = new JTextField();
		fatherField.setBounds(500,235,500,30);
		fatherField.addActionListener(this);
		add(fatherField);
		
		
		Address= new JLabel("Address:");
		Address.setFont(new Font("Raleway",Font.BOLD,20));
		Address.setBounds(200,250,500,100);
		add(Address);
		
		addressField = new JTextField();
		addressField.setBounds(500,235+50,500,30);
		addressField.addActionListener(this);
		add(addressField);
		
		DOB= new JLabel("DOB:");
		DOB.setFont(new Font("Raleway",Font.BOLD,20));
		DOB.setBounds(200,300,500,100);
		
		add(DOB) ;
		
		dob_chooser = new JDateChooser();
		dob_chooser.setBounds(500,235+50+50,500,30);
		// dob_chooser.addActionListener(this);
		add(dob_chooser);
		
		Gender = new JLabel("Gender:");
		Gender.setFont(new Font("Raleway", Font.BOLD, 20));
		Gender.setBounds(200,350,500,100); add(Gender);
		
		
		maleButton = new JRadioButton("MALE");
		maleButton.setBounds(500,390,100,20);maleButton.setBackground(Color.cyan);
		add(maleButton);
		
		femaleButton = new JRadioButton("FEMALE");
		femaleButton.setBounds(600,390,100,20); femaleButton.setBackground(Color.cyan);add(femaleButton);
		
		lgbtqButton = new JRadioButton("LGBTQ");
		lgbtqButton.setBounds(700,390,100,20); lgbtqButton.setBackground(Color.cyan);add(lgbtqButton);
		
		ButtonGroup gender_ButtonGroup = new ButtonGroup();
		gender_ButtonGroup.add(maleButton);gender_ButtonGroup.add(femaleButton);gender_ButtonGroup.add(lgbtqButton);
		
		JLabel marital = new JLabel("MARITAL STATUS");
		marital.setFont(new Font("Raleway", Font.BOLD, 20));
		marital.setBounds(200,400,500,100);add(marital);
		
		marriedButton = new JRadioButton("MARRRIED");
		marriedButton.setBounds(500,440,100,20); marriedButton.setBackground(Color.cyan);add(marriedButton);
		
		unmarriedButton = new JRadioButton("UNMARRIED");
		unmarriedButton.setBounds(600,440,125,20); unmarriedButton.setBackground(Color.cyan);add(unmarriedButton);
		
		divorceButton = new JRadioButton("DIVORCE");
		divorceButton.setBounds(722,440,100,20); divorceButton.setBackground(Color.cyan);add(divorceButton);
		
		
		ButtonGroup marital_Buttongroup = new ButtonGroup();
		marital_Buttongroup.add(marriedButton);
		marital_Buttongroup.add(unmarriedButton);
		 marital_Buttongroup.add(divorceButton);
		 
		 state = new JLabel("STATE :");
		 state.setFont(new Font("Raleway", Font.BOLD, 20));
		 state.setBounds(200,450,500,100);add(state);
		 
		 stateField = new JTextField();
		 stateField.setBounds(500,490,500,30);
		 stateField.addActionListener(this);
		 add(stateField);
		 
		 pin = new JLabel("Pin Pode");
		 pin.setFont(new Font("Raleway", Font.BOLD, 20));
		 pin.setBounds(200,500,500,100);add(pin);
		 
		 pinField = new JTextField();
		 pinField.setBounds(500,540,500,30);
		 pinField.addActionListener(this);
		 add(pinField);
		 
		 nextButton = new JButton("NEXT");
		 nextButton.setBounds(700,700,100,20);
		 add(nextButton);
		 nextButton.addActionListener(this);



		 
		 setVisible(true);


		}
		@Override
		public void actionPerformed(ActionEvent e) {		
			

				if (e.getSource() == nameField || e.getSource() == fatherField || e.getSource() == addressField || e.getSource() == stateField || e.getSource() == pinField) {
					// Call the button's action
					nextButton.doClick();
				} 
				
				
				else if (e.getSource() == nextButton) {
					// Your existing code for the next button
					String marital = null;
			
					String gender = null;
					
					String form_no=""+app_form;
					String name = nameField.getText();
					String fathername= fatherField.getText();
					String address= addressField.getText();
					String state= stateField.getText();
					String pin= pinField.getText();
					Date selectedDate = dob_chooser.getDate();
					SimpleDateFormat sDateFormat = new SimpleDateFormat();
					String date = sDateFormat.format(selectedDate);
					
					if(maleButton.isSelected())
					{
						 gender="Male";
					}
					if(femaleButton.isSelected())
					{
						gender="Female";
					}
					if(lgbtqButton.isSelected())
					{
						gender="Chakka";
					}
		
					if(marriedButton.isSelected()) marital="Married"; 
					
					if(unmarriedButton.isSelected()) marital="Unmarried"; 
					
					if(divorceButton.isSelected()) marital="Divorce"; 		
					
						if (name.equals("")) {
							JOptionPane.showMessageDialog(null, "Enter the name bruhh","Name Required",JOptionPane.WARNING_MESSAGE);
						}
						else 
						{
						
							Conn conn = new Conn();
						try {
						 
							String  query = "insert into signup values ('"+form_no+"', '"+name+"' , '"+fathername+"', '"+address+"' , '"+gender+"','"+marital+"'  ,'"+date+"' ,'"+state+"' ,'"+pin+"')";
							
							
							int rows_affected = conn.s.executeUpdate(query);
						
							if (rows_affected > 0) {
								
								
							//		                success = true;
								new signup2(form_no).setVisible(true);
								JOptionPane.showMessageDialog(null,"Deatils enetered successfully" ,"Details entered successfully",JOptionPane.WARNING_MESSAGE);
								setVisible(false);
							}
								
							else {
									JOptionPane.showMessageDialog(null, "FAILED","Kindly Contact Arush Jauhari ",JOptionPane.WARNING_MESSAGE);
								}
						
						}
						
						
						catch (Exception e2) {
							System.out.println(e);
						}
						}
		
				}
			}



				
			 
						
		

		public static void main(String[] args) {
			// new sign_up();

			// TODO Auto-generated method stub
		}

	}
