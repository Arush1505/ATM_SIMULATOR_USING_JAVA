package bank_management_atm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.*;

//import com.toedter.calendar.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class signup2 extends JFrame implements ActionListener
{
JLabel main_text,religion,Cateogory,Income,EducationalQualification,Occupation,Pan, Aadhar,Senior,existing;
JButton nextButton;
@SuppressWarnings("rawtypes")
JComboBox religionOption,categoryOption,IncomeOption,EducationalOption,occupationOption;
JTextField AadharFeild,PanFeild;
JRadioButton Senior_yes,Senior_no,exist_yes,exist_no;
String formString;
    @SuppressWarnings({ "unchecked", "rawtypes" })
	signup2(String form_no)
    {

    formString = form_no	;
    getContentPane().setBackground(Color.CYAN);
        
    setSize(850,850);
    setLocation(500,120);
    setLayout(null);
    main_text = new JLabel("ADDITIONAL DETAILS");
    main_text.setBounds(650,45,500,100);
    main_text.setFont(new Font("Raleway",Font.BOLD,30));
    add(main_text);
    religion= new JLabel("RELIGION:");
    religion.setFont(new Font("Raleway",Font.BOLD,20));
    religion.setBounds(200,150,500,100);
    add(religion);


    String religionOptions[] = {"Hindu","Muslim","Sikh","Christian","Other"};
    religionOption = new JComboBox(religionOptions);
    religionOption.setBounds(500,185,500,30);
    add(religionOption);


    Cateogory= new JLabel("Cateogory:");
    Cateogory.setFont(new Font("Raleway",Font.BOLD,20));
    Cateogory.setBounds(200,200,500,100);
    add(Cateogory);

    String categoryOptions[] = {"General","OBC","SC","ST","Other"};
    categoryOption = new JComboBox(categoryOptions);
    categoryOption.setBounds(500,235,500,30);
    add(categoryOption);


    Income= new JLabel("Income:");
    Income.setFont(new Font("Raleway",Font.BOLD,20));
    Income.setBounds(200,250,500,100);
    add(Income);

String IncomeOptions[] ={"Null`","<1,50,000","<2,50,000","<5,00,000","Upto 10,00,000","Above 10,00,000"};

    IncomeOption = new JComboBox(IncomeOptions);
    IncomeOption.setBounds(500,235+50,500,30);
    add(IncomeOption);

     EducationalQualification= new JLabel("EducationalQualification:");
    EducationalQualification.setFont(new Font("Raleway",Font.BOLD,20));
    EducationalQualification.setBounds(200,300,500,100);
    add(EducationalQualification) ;

String EducationalOptions []={"Non-Graduate","Graduate","Post-Graduate","Doctrate","Others"};
    EducationalOption = new JComboBox(EducationalOptions);
    EducationalOption.setBounds(500,235+50+50,500,30);
    add(EducationalOption);

      Occupation = new JLabel("Occupation:");
     Occupation.setFont(new Font("Raleway", Font.BOLD, 20));
    Occupation.setBounds(200,350,500,100);
    add(Occupation);

    String occupationOptions[] = {"Salaried","Self-Employmed","Business","Student","Retired","Others"};
     occupationOption = new JComboBox(occupationOptions);
    occupationOption.setBounds(500,390,100,20);
    occupationOption.setBackground(Color.cyan);
    add(occupationOption);


    Pan = new JLabel("Pan Number:");
    Pan.setFont(new Font("Raleway", Font.BOLD, 20));
    Pan.setBounds(200,400,500,100);
    add(Pan);

    PanFeild = new JTextField();
    PanFeild.setBounds(500,438,500,30);
    add(PanFeild);

    Aadhar = new JLabel("Aadhaar number:");
    Aadhar.setFont(new Font("Raleway", Font.BOLD, 20));
    Aadhar.setBounds(200,455,500,100);
    add(Aadhar);

    AadharFeild = new JTextField();
    AadharFeild.setBounds(500,490,500,30);
    add(AadharFeild);
    
    Senior = new JLabel("Senior Citizen: ");
    Senior.setFont(new Font("Raleway", Font.BOLD, 20));
    Senior.setBounds(200,600-50,500,30);
    add(Senior);
    
    
    Senior_yes = new JRadioButton("Yes");
    Senior_yes.setBackground(Color.CYAN);
    Senior_yes.setBounds(500,600-50,50,30);
    add(Senior_yes);
    
    Senior_no = new JRadioButton("No");
    Senior_no.setBackground(Color.CYAN);
    Senior_no.setBounds(700,600-50,50,30);
    add(Senior_no);
    
    ButtonGroup SeniorButton = new ButtonGroup();
    SeniorButton.add(Senior_no);
    SeniorButton.add(Senior_yes);
    
    
    
    existing = new JLabel("EXISTING ACCOUNT: ");
    existing.setBackground(Color.BLACK);
    existing.setFont(new Font("Raleway", Font.BOLD, 20));
    existing.setBounds(200,650-50,300,30);
    add(existing);
    
    
    exist_yes = new JRadioButton("Yes");
    exist_yes.setBackground(Color.CYAN);
    exist_yes.setBounds(500,650-50,50,30);
    add(exist_yes);
    
    exist_no = new JRadioButton("No");
    exist_no.setBackground(Color.CYAN);
    exist_no.setBounds(700,650-50,50,30);
    add(exist_no);
    
    ButtonGroup ExistButton = new ButtonGroup();
    ExistButton.add(exist_no);
    ExistButton.add(exist_yes);
    nextButton = new JButton("NEXT");
    nextButton.setBounds(700,700,100,20);
    nextButton.addActionListener(this);
    add(nextButton);
    
    // setExtendedState(JFrame.MAXIMIZED_BOTH);
    // add(mainPanel, BorderLayout.CENTER);
    // mainPanel.setVisible(true);
    // setVisible(true);
    // pack();

    // setDefaultCloseOperation(EXIT_ON_CLOSE);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height - 40; // subtract 40 to account for the task bar
    setBounds(0, 0, width, height);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
String senior = null; String exist= null; 
    	String religion = (String) religionOption.getSelectedItem();
    	String category = (String) categoryOption.getSelectedItem();
    	String income = (String) IncomeOption.getSelectedItem();
    	String education = (String) EducationalOption.getSelectedItem();
    	String occupation = (String) occupationOption.getSelectedItem();    	
    	String aadhar = AadharFeild.getText();
    	String  pan= PanFeild.getText();
    	
    	if (Senior_yes.isSelected()) { senior = "yes";  }
    	if (Senior_no.isSelected()) { senior = "no";  }
    	
    	if (exist_yes.isSelected()) {exist="yes";}
    	
    	if (exist_no.isSelected()) {exist="no";}
    	
    	
    	try {
		if(aadhar.equals("") || pan.equals("")) {
			
			JOptionPane.showMessageDialog(null, "Fill All The Feilds","WARNING", JOptionPane.WARNING_MESSAGE);
		
		}	
		else{
			
			Conn c= new Conn();
		String queryString = "insert into signup2 values('"+formString+"','"+religion+"' ,'"+category+"','"+income+"','"+education+"','"+occupation+"','"+senior+"','"+exist+"','"+aadhar+"','"+pan+"')";
		int rows_affected = c.s.executeUpdate(queryString);
		
		
		if(rows_affected > 0 )
		{
			JOptionPane.showMessageDialog(null, "succesfully entered","congratulations", JOptionPane.WARNING_MESSAGE);
			setVisible(false);
			new signup3(formString).setVisible(true);
		}
		
		
		}
		
    	} 
    	
    	catch (Exception e2) {
		System.out.println(e2);
    		JOptionPane.showMessageDialog(null, "e2","error", JOptionPane.WARNING_MESSAGE);	
		}
    }

    
    public static void main(String[] args) {
    //    new signup2("").setVisible(true);;
    	
    }

}
