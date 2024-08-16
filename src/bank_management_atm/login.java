package bank_management_atm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.w3c.dom.events.MouseEvent;

import java.sql.*;
import java.util.Timer;

public class login extends JFrame implements ActionListener {

    private JButton clear, signIn, signUp;
    private JTextField cardField;
    private JPasswordField pinField;

    public login() 
	{			setLayout(new BorderLayout());
		
				// Add a header panel with the bank logo
				JPanel headerPanel = new JPanel();
				headerPanel.setBackground(new Color(0x3498db)); // blue background

				ImageIcon i1 = new ImageIcon(this.getClass().getResource("/icons/bank.jpg"));
				Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
				ImageIcon i3 = new ImageIcon(i2);
				JLabel label = new JLabel(i3);
				headerPanel.add(label);
				add(headerPanel, BorderLayout.WEST);
	
				
				JPanel mainPanel = new JPanel();
			
				mainPanel.setLayout(new GridBagLayout());
				
				ImageIcon backgroundImage = new ImageIcon("/icons/RBI.jpg");	
				JLabel backgroundLabel = new JLabel(backgroundImage);
				backgroundLabel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));


				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbc.fill = GridBagConstraints.BOTH;
				gbc.weightx = 1.0;
				gbc.weighty = 1.0;
				mainPanel.add(backgroundLabel, gbc);

				JLabel mainText = new JLabel("Welcome to ATM");
				mainText.setFont(new Font("Open Sans", Font.BOLD, 24));
				mainPanel.add(mainText, new GridBagConstraints(1, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));			// 
			
				JLabel cardNoLabel = new JLabel("ENTER CARD NUMBER:");
				cardNoLabel.setFont(new Font("Raleway", Font.BOLD, 18));
				mainPanel.add(cardNoLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 100, 10, 10), 0, 0));
		
				cardField = new JTextField();
				cardField.setFont(new Font("Open Sans", Font.PLAIN, 18));
				cardField.addActionListener(this);
				mainPanel.add(cardField, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
				
				JLabel pinLabel = new JLabel("ENTER PIN:");
				pinLabel.setFont(new Font("Raleway", Font.BOLD, 18));
				mainPanel.add(pinLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 100, 10, 10), 0, 0));
		
				pinField = new JPasswordField();
				pinField.setFont(new Font("Open Sans", Font.PLAIN, 18));
				mainPanel.add(pinField, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
				pinField.addActionListener(this);
				// Add buttons with modern font and colors
				signIn = new JButton("SIGN IN");
				signIn.setFont(new Font("Open Sans", Font.BOLD, 18));
				signIn.setBackground(new Color(0x3498db)); // blue background
				signIn.setForeground(new Color(0xffffff)); // white text
				signIn.addActionListener(this);
				
				mainPanel.add(signIn, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
			


				clear = new JButton("CLEAR");
				clear.setFont(new Font("Open Sans", Font.BOLD, 18));
				clear.setBackground(new Color(0x3498db)); // blue background
				clear.setForeground(new Color(0xffffff)); // white text
				clear.addActionListener(this);

				mainPanel.add(clear, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		
				signUp = new JButton("SIGN UP");
				signUp.setFont(new Font("Open Sans", Font.BOLD, 18));
				signUp.setBackground(new Color(0x3498db)); // blue background
				signUp.setForeground(new Color(0xffffff)); // white text
				signUp.addActionListener(this);
				mainPanel.add(signUp, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				add(mainPanel, BorderLayout.CENTER);
				mainPanel.setVisible(true);
				setVisible(true);
				pack();
		
				setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear)
		{
			cardField.setText("");
            pinField.setText("");
	
        } 
		
		else if (e.getSource() == signUp)
		
		{
            // signUp.setBackground(new Color(245,244,101));
			setVisible(false);
            new sign_up().setVisible(true);


        } 
		
		else if (e.getSource() == pinField || e.getSource() == cardField )
		{
			signIn.doClick();
		}

		else if (e.getSource() == signIn) 
		
		{	
			signIn.setBackground(new Color(47,246,18));
			
            Conn conn = new Conn();
            String cardString = cardField.getText();
            String pinString = pinField.getText();

			if (cardString.equals("")  || pinString.equals("")) 
			{
				JOptionPane.showMessageDialog(null,"ENTER THE PIN AND PASSWORD", "Cannot Proceed",JOptionPane.WARNING_MESSAGE);
				signIn.setBackground(new Color(0x3498db)); // blue backgroun
				signIn.setForeground(new Color(0xffffff)); // white text

				
			}

            else
			{
				String query = "SELECT * FROM login WHERE card_number = ? AND pin_number = ?";
            	try {
                PreparedStatement preparedStatement = conn.co.prepareStatement(query);
                preparedStatement.setString(1, cardString);
                preparedStatement.setString(2, pinString);

                ResultSet res = preparedStatement.executeQuery();

                if (res.next()) {
                    new Transactions(pinString).setVisible(true);
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Successfully logged in");
                }
				else
				{
					JOptionPane.showMessageDialog(null,"INVALID PIN / NUMBER", "Cannot Proceed",JOptionPane.WARNING_MESSAGE);

					signIn.setBackground(new Color(0x3498db)); // blue background
					signIn.setForeground(new Color(0xffffff)); // white text

				}

                preparedStatement.close();
            } catch (SQLException ex) {
				signIn.setBackground(new Color(0x3498db)); // blue backgroun
				signIn.setForeground(new Color(0xffffff)); // white text

                System.out.println(ex);
                // Handle the exception (log it, show an error message, etc.)
            }

			}


        }
    }

    public static void main(String[] args) {
        new login();
    }
}