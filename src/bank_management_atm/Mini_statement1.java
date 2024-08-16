package bank_management_atm;
import java.awt.*;
import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

public class Mini_statement1  extends JFrame{

String pinString;

	public Mini_statement1(String pinString) 
	{
		// TODO Auto-generated constructor stub
		this.pinString = pinString;
		
		setSize(800,600);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("MINI STATEMENT");
		setLayout(new BorderLayout());
		String Columns[]={"PIN Number", "Card Number", "Form no.", "Amount deposited", "Deposit date"};
		
		DefaultTableModel tablemodel = new DefaultTableModel(Columns,0);
		JTable table = new JTable(tablemodel);
		
		JScrollPane scroll = new JScrollPane(table);
//		scroll.setBounds(0,0,850,100);
		
		
		// Fetching the data
	
		
		String Columns_with [] ={"Withdrawal amt","Withdrawal date","Type"};
		DefaultTableModel dd = new DefaultTableModel(Columns_with,0);
		JTable table1 = new JTable(dd);
		
		JScrollPane scroll_1 = new JScrollPane(table1);
		
		add(scroll_1,BorderLayout.SOUTH);
		add(scroll,BorderLayout.CENTER);
		setVisible(true);
		
		Conn c = new Conn();
		
		try {
String query = "SELECT l.form_no, l.pin_number, l.card_number, b.amount, b.date " +
               "FROM bank b " +
               "INNER JOIN login l " +
               "ON l.pin_number = b.pin_number " +
               "WHERE l.pin_number = ?";

PreparedStatement pp = c.co.prepareStatement(query);
pp.setString(1, pinString);

			ResultSet res = pp	.executeQuery();

			while (res.next()) {
				String form = res.getString("form_no");
				String pin = res.getString("pin_number");
				String card = res.getString("card_number");
				String amount_depo = res.getString("amount");
				String date = res.getString("date");

				
				tablemodel.addRow(new Object[] {pin,card,form,amount_depo,date});
				
			}
		} 
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "error fetching the data","again",JOptionPane.WARNING_MESSAGE);
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println(e);
		}
		
		
		
		try {

			String query2 = " select with_amount , with_date , type FROM withdrawal where pin_number = ?";
			PreparedStatement pp1 = c.co.prepareStatement(query2);
			pp1.setString(1, pinString);
			ResultSet res2 = pp1.executeQuery(); 
			while (res2.next()) {
				String with_amt = res2.getString("with_amount");
				String with_date = res2.getString("with_date");
				String type= res2.getString("type");
				
				dd.addRow(new Object[] {with_amt,with_date,type});
				
			}
			
			
		}
		
		catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "error fetching the data","again",JOptionPane.WARNING_MESSAGE);

		e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
new Mini_statement1("6692");
	}
}
