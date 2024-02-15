import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;


public class SpielFeldUI
{

	private JFrame frmBadmintonSpielfeld;
	private static JTextField textFieldTeam1;
	private static JTextField textFieldTeam2;
	private static JTextField textFieldSpieler1;
	private static JTextField textFieldSpieler2;
	private static JTextField textFieldSpieler3;
	private static JTextField textFieldSpieler4;
	private static JTextField textFieldTeam1Punkte;
	private static JTextField textFieldTeam2Punkte;
	
	
	
	
	private JLabel lblTeam1;
	private JLabel lblTeam2;
	private JLabel lblSpieler1;
	private JLabel lblSpieler2;
	private JLabel lblSpieler3;
	private JLabel lblSpieler4;
	private JLabel lblTeam1Punkte;
	private JLabel lblTeam2Punkte;
	
	private JButton btnMinusTeam1;
	private JButton btnPlusTeam2;
	private JButton btnMinusTeam2;
	private JButton btnPlusTeam1;
	private JButton btnSpielStarten;
	
	
	private static Connection myConn;
	private JTextField textFieldTeam1Gewinnsaetze;
	private JTextField textFieldTeam2Gewinnsaetze;
	private JCheckBox chckbxSpieler1;
	private JCheckBox chckbxSpieler2;
	private JCheckBox chckbxSpieler3;
	private JCheckBox chckbxSpieler4;
	private JButton btnSpielNeuStarten;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		String url = "jdbc:mysql://localhost:3306/spielerdb";
		String user = "root";
		String password = "M0949250125m";
		
		try
		{
//			1. get connection to database
			myConn = DriverManager.getConnection(url, user, password);
			
//			2. create a statement
//			Statement myStmt = myConn.createStatement();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SpielFeldUI window = new SpielFeldUI();
					window.frmBadmintonSpielfeld.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpielFeldUI()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmBadmintonSpielfeld = new JFrame();
		frmBadmintonSpielfeld.setTitle("Badminton Spielfeld");
		frmBadmintonSpielfeld.setBounds(100, 100, 1105, 771);
		frmBadmintonSpielfeld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBadmintonSpielfeld.getContentPane().setLayout(null);
		
		frmBadmintonSpielfeld.setLocationRelativeTo(null);
		
		btnPlusTeam1 = new JButton("");
		btnPlusTeam1.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		btnPlusTeam1.setEnabled(false);
		btnPlusTeam1.setIcon(new ImageIcon("C:\\Users\\mahmo\\Downloads\\plus(1).png"));
		btnPlusTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(textFieldTeam1Punkte.getText());
				
				textFieldTeam1Punkte.setText("" + (i + 1));
				
				if(team1HatEinSetGewonnen())
				{
					textFieldTeam1Punkte.setText("" + 0);
					textFieldTeam2Punkte.setText("" + 0);
					
					
					int x = Integer.parseInt(textFieldTeam1Gewinnsaetze.getText());
					textFieldTeam1Gewinnsaetze.setText("" + (x + 1));
				}
				
				if(team1HatGewonnen())
				{
					JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch Team " + textFieldTeam1.getText() + " hat gewonnen!");
					
					spielNeuStarten();
				}
			}
		});
		
		chckbxSpieler4 = new JCheckBox("Aufschlag");
		chckbxSpieler4.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSpieler4.setFont(new Font("Unispace", Font.PLAIN, 18));
		chckbxSpieler4.setBounds(629, 438, 145, 39);
		frmBadmintonSpielfeld.getContentPane().add(chckbxSpieler4);
		
		chckbxSpieler3 = new JCheckBox("Aufschlag");
		chckbxSpieler3.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSpieler3.setFont(new Font("Unispace", Font.PLAIN, 18));
		chckbxSpieler3.setBounds(629, 214, 145, 39);
		frmBadmintonSpielfeld.getContentPane().add(chckbxSpieler3);
		
		chckbxSpieler2 = new JCheckBox("Aufschlag");
		chckbxSpieler2.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSpieler2.setFont(new Font("Unispace", Font.PLAIN, 18));
		chckbxSpieler2.setBounds(315, 438, 145, 39);
		frmBadmintonSpielfeld.getContentPane().add(chckbxSpieler2);
		
		chckbxSpieler1 = new JCheckBox("Aufschlag");
		chckbxSpieler1.setFont(new Font("Unispace", Font.PLAIN, 18));
		chckbxSpieler1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSpieler1.setBounds(315, 214, 145, 39);
		frmBadmintonSpielfeld.getContentPane().add(chckbxSpieler1);
		btnPlusTeam1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPlusTeam1.setBackground(UIManager.getColor("TextPane.disabledBackground"));
		btnPlusTeam1.setForeground(Color.BLACK);
		btnPlusTeam1.setBounds(10, 164, 83, 73);
		frmBadmintonSpielfeld.getContentPane().add(btnPlusTeam1);
		
		textFieldTeam1 = new JTextField();
		textFieldTeam1.setFont(new Font("Unispace", Font.PLAIN, 13));
		textFieldTeam1.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		textFieldTeam1.setBounds(202, 42, 145, 31);
		frmBadmintonSpielfeld.getContentPane().add(textFieldTeam1);
		textFieldTeam1.setColumns(10);
		
		lblTeam1 = new JLabel("Team 1");
		lblTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam1.setFont(new Font("Unispace", Font.PLAIN, 19));
		lblTeam1.setBounds(119, 42, 75, 31);
		frmBadmintonSpielfeld.getContentPane().add(lblTeam1);
		
		lblTeam2 = new JLabel("Team 2");
		lblTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam2.setFont(new Font("Unispace", Font.PLAIN, 19));
		lblTeam2.setBounds(719, 45, 75, 25);
		frmBadmintonSpielfeld.getContentPane().add(lblTeam2);
		
		textFieldTeam2 = new JTextField();
		textFieldTeam2.setFont(new Font("Unispace", Font.PLAIN, 13));
		textFieldTeam2.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		textFieldTeam2.setColumns(10);
		textFieldTeam2.setBounds(804, 42, 145, 31);
		frmBadmintonSpielfeld.getContentPane().add(textFieldTeam2);
		
		btnMinusTeam1 = new JButton("");
		btnMinusTeam1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMinusTeam1.setBackground(UIManager.getColor("TextPane.disabledBackground"));
		btnMinusTeam1.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		btnMinusTeam1.setEnabled(false);
		btnMinusTeam1.setForeground(Color.BLACK);
		btnMinusTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textFieldTeam1Punkte.getText()) == 0)
				{
					
				}
				else
				{
					int i = Integer.parseInt(textFieldTeam1Punkte.getText());
					textFieldTeam1Punkte.setText("" + (i - 1));
				}
			}
		});
		btnMinusTeam1.setIcon(new ImageIcon("C:\\Users\\mahmo\\Downloads\\minus(1).png"));
		btnMinusTeam1.setBounds(10, 396, 83, 73);
		frmBadmintonSpielfeld.getContentPane().add(btnMinusTeam1);
		
		btnPlusTeam2 = new JButton("");
		btnPlusTeam2.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		btnPlusTeam2.setEnabled(false);
		btnPlusTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(textFieldTeam2Punkte.getText());
				
				textFieldTeam2Punkte.setText("" + (i + 1));
				
				if(team2HatEinSetGewonnen())
				{
					textFieldTeam1Punkte.setText("" + 0);
					textFieldTeam2Punkte.setText("" + 0);
					
					
					int x = Integer.parseInt(textFieldTeam2Gewinnsaetze.getText());
					textFieldTeam2Gewinnsaetze.setText("" + (x + 1));
				}
				
				if(team2HatGewonnen())
				{
					JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch Team " + textFieldTeam2.getText() + " hat gewonnen!");
					
					spielNeuStarten();
				}
			}
		});
		btnPlusTeam2.setIcon(new ImageIcon("C:\\Users\\mahmo\\Downloads\\plus(1).png"));
		btnPlusTeam2.setForeground(Color.BLACK);
		btnPlusTeam2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPlusTeam2.setBackground(UIManager.getColor("TextPane.disabledBackground"));
		btnPlusTeam2.setBounds(993, 164, 83, 73);
		frmBadmintonSpielfeld.getContentPane().add(btnPlusTeam2);
		
		btnMinusTeam2 = new JButton("");
		btnMinusTeam2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMinusTeam2.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		btnMinusTeam2.setEnabled(false);
		btnMinusTeam2.setForeground(new Color(0, 0, 0));
		btnMinusTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textFieldTeam2Punkte.getText()) == 0)
				{
					
				}
				else
				{
					int i = Integer.parseInt(textFieldTeam2Punkte.getText());
					textFieldTeam2Punkte.setText("" + (i - 1));
				}
			}
		});
		btnMinusTeam2.setIcon(new ImageIcon("C:\\Users\\mahmo\\Downloads\\minus(1).png"));
		btnMinusTeam2.setBounds(993, 396, 83, 73);
		frmBadmintonSpielfeld.getContentPane().add(btnMinusTeam2);
		
		lblSpieler1 = new JLabel("Spieler 1");
		lblSpieler1.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblSpieler1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpieler1.setBounds(186, 178, 106, 22);
		frmBadmintonSpielfeld.getContentPane().add(lblSpieler1);
		
		lblSpieler2 = new JLabel("Spieler 2");
		lblSpieler2.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblSpieler2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpieler2.setBounds(186, 409, 106, 22);
		frmBadmintonSpielfeld.getContentPane().add(lblSpieler2);
		
		lblSpieler3 = new JLabel("Spieler 3");
		lblSpieler3.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblSpieler3.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpieler3.setBounds(804, 178, 106, 22);
		frmBadmintonSpielfeld.getContentPane().add(lblSpieler3);
		
		lblSpieler4 = new JLabel("Spieler 4");
		lblSpieler4.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblSpieler4.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpieler4.setBounds(804, 409, 106, 22);
		frmBadmintonSpielfeld.getContentPane().add(lblSpieler4);
		
		textFieldSpieler1 = new JTextField();
		textFieldSpieler1.setFont(new Font("Unispace", Font.PLAIN, 13));
		textFieldSpieler1.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		textFieldSpieler1.setColumns(10);
		textFieldSpieler1.setBounds(315, 164, 145, 36);
		frmBadmintonSpielfeld.getContentPane().add(textFieldSpieler1);
		
		textFieldSpieler2 = new JTextField();
		textFieldSpieler2.setFont(new Font("Unispace", Font.PLAIN, 13));
		textFieldSpieler2.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2));
		textFieldSpieler2.setColumns(10);
		textFieldSpieler2.setBounds(315, 396, 145, 35);
		frmBadmintonSpielfeld.getContentPane().add(textFieldSpieler2);
		
		textFieldSpieler3 = new JTextField();
		textFieldSpieler3.setFont(new Font("Unispace", Font.PLAIN, 13));
		textFieldSpieler3.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2, true));
		textFieldSpieler3.setColumns(10);
		textFieldSpieler3.setBounds(629, 164, 145, 36);
		frmBadmintonSpielfeld.getContentPane().add(textFieldSpieler3);
		
		textFieldSpieler4 = new JTextField();
		textFieldSpieler4.setFont(new Font("Unispace", Font.PLAIN, 13));
		textFieldSpieler4.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 2));
		textFieldSpieler4.setColumns(10);
		textFieldSpieler4.setBounds(629, 396, 145, 35);
		frmBadmintonSpielfeld.getContentPane().add(textFieldSpieler4);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\mahmo\\Desktop\\badminton\\bad.png"));
		lblNewLabel_1.setBounds(103, 76, 882, 497);
		frmBadmintonSpielfeld.getContentPane().add(lblNewLabel_1);
		
		textFieldTeam1Punkte = new JTextField();
		textFieldTeam1Punkte.setBorder(new LineBorder(UIManager.getColor("TextPane.disabledBackground"), 2, true));
		textFieldTeam1Punkte.setText("0");
		textFieldTeam1Punkte.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTeam1Punkte.setFont(new Font("Unispace", Font.PLAIN, 18));
		textFieldTeam1Punkte.setEditable(false);
		textFieldTeam1Punkte.setBounds(332, 584, 119, 31);
		frmBadmintonSpielfeld.getContentPane().add(textFieldTeam1Punkte);
		textFieldTeam1Punkte.setColumns(10);
		
		textFieldTeam2Punkte = new JTextField();
		textFieldTeam2Punkte.setBorder(new LineBorder(UIManager.getColor("TextPane.disabledBackground"), 2, true));
		textFieldTeam2Punkte.setText("0");
		textFieldTeam2Punkte.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTeam2Punkte.setFont(new Font("Unispace", Font.PLAIN, 18));
		textFieldTeam2Punkte.setEditable(false);
		textFieldTeam2Punkte.setColumns(10);
		textFieldTeam2Punkte.setBounds(866, 584, 119, 31);
		frmBadmintonSpielfeld.getContentPane().add(textFieldTeam2Punkte);
		
		lblTeam1Punkte = new JLabel("Team 1 Punkte:");
		lblTeam1Punkte.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblTeam1Punkte.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam1Punkte.setBounds(113, 584, 154, 31);
		frmBadmintonSpielfeld.getContentPane().add(lblTeam1Punkte);
		
		lblTeam2Punkte = new JLabel("Team 2 Punkte:");
		lblTeam2Punkte.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam2Punkte.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblTeam2Punkte.setBounds(650, 584, 154, 31);
		frmBadmintonSpielfeld.getContentPane().add(lblTeam2Punkte);
		
		btnSpielStarten = new JButton("Spiel Starten");
		btnSpielStarten.setBackground(new Color(255, 99, 71));
		btnSpielStarten.setBorder(new LineBorder(UIManager.getColor("TextPane.disabledBackground"), 3, true));
		btnSpielStarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSpielStartenIsAktiviert())
				{
					btnMinusTeam1.setEnabled(true);
					btnMinusTeam2.setEnabled(true);
					btnPlusTeam1.setEnabled(true);
					btnPlusTeam2.setEnabled(true);
					
					textFieldSpieler1.setEditable(false);
					textFieldSpieler2.setEditable(false);
					textFieldSpieler3.setEditable(false);
					textFieldSpieler4.setEditable(false);
					textFieldTeam1.setEditable(false);
					textFieldTeam2.setEditable(false);
					
					btnSpielNeuStarten.setEnabled(true);
					
					try
					{
						addiereSpieler();
					} 
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
					
					btnSpielStarten.setEnabled(false);
				}
			}
		});
		btnSpielStarten.setFont(new Font("Unispace", Font.BOLD, 24));
		btnSpielStarten.setBounds(438, 11, 217, 54);
		frmBadmintonSpielfeld.getContentPane().add(btnSpielStarten);
		
		JLabel lblTeam1Gewinnsaetze = new JLabel("Team 1 Gewinnsätze:");
		lblTeam1Gewinnsaetze.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblTeam1Gewinnsaetze.setBounds(113, 626, 209, 22);
		frmBadmintonSpielfeld.getContentPane().add(lblTeam1Gewinnsaetze);
		
		JLabel lblTeam2Gewinnsaetze = new JLabel("Team 2 Gewinnsätze:");
		lblTeam2Gewinnsaetze.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblTeam2Gewinnsaetze.setBounds(650, 626, 209, 22);
		frmBadmintonSpielfeld.getContentPane().add(lblTeam2Gewinnsaetze);
		
		textFieldTeam1Gewinnsaetze = new JTextField();
		textFieldTeam1Gewinnsaetze.setBorder(new LineBorder(UIManager.getColor("TextPane.disabledBackground"), 2, true));
		textFieldTeam1Gewinnsaetze.setFont(new Font("Unispace", Font.PLAIN, 18));
		textFieldTeam1Gewinnsaetze.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTeam1Gewinnsaetze.setText("0");
		textFieldTeam1Gewinnsaetze.setEditable(false);
		textFieldTeam1Gewinnsaetze.setBounds(332, 625, 119, 31);
		frmBadmintonSpielfeld.getContentPane().add(textFieldTeam1Gewinnsaetze);
		textFieldTeam1Gewinnsaetze.setColumns(10);
		
		textFieldTeam2Gewinnsaetze = new JTextField();
		textFieldTeam2Gewinnsaetze.setBorder(new LineBorder(UIManager.getColor("TextPane.disabledBackground"), 2, true));
		textFieldTeam2Gewinnsaetze.setFont(new Font("Unispace", Font.PLAIN, 18));
		textFieldTeam2Gewinnsaetze.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTeam2Gewinnsaetze.setText("0");
		textFieldTeam2Gewinnsaetze.setEditable(false);
		textFieldTeam2Gewinnsaetze.setColumns(10);
		textFieldTeam2Gewinnsaetze.setBounds(866, 626, 119, 31);
		frmBadmintonSpielfeld.getContentPane().add(textFieldTeam2Gewinnsaetze);
		
		btnSpielNeuStarten = new JButton("Spiel Neu Starten!");
		btnSpielNeuStarten.setEnabled(false);
		btnSpielNeuStarten.setBorder(new LineBorder(new Color(240, 240, 240), 3, true));
		btnSpielNeuStarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spielNeuStarten();
			}
		});
		btnSpielNeuStarten.setFont(new Font("Unispace", Font.PLAIN, 22));
		btnSpielNeuStarten.setBounds(419, 667, 256, 54);
		frmBadmintonSpielfeld.getContentPane().add(btnSpielNeuStarten);
	}
	
	private boolean team1HatEinSetGewonnen()
	{
		int team1Punkte = Integer.parseInt(textFieldTeam1Punkte.getText());
		int team2Punkte = Integer.parseInt(textFieldTeam2Punkte.getText());
		int team1MinusTeam2 = team1Punkte - team2Punkte;
		
		if((team1Punkte >= 21 && team1MinusTeam2 >= 2) || team1Punkte == 30)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean team2HatEinSetGewonnen()
	{
		int team1Punkte = Integer.parseInt(textFieldTeam1Punkte.getText());
		int team2Punkte = Integer.parseInt(textFieldTeam2Punkte.getText());
		int team2MinusTeam1 = team2Punkte - team1Punkte;
		
		if((team2Punkte >= 21 && team2MinusTeam1 >= 2) || team2Punkte == 30)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean team1HatGewonnen()
	{
		int i = Integer.parseInt(textFieldTeam1Gewinnsaetze.getText());
		
		if(i == 2)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean team2HatGewonnen()
	{
		int i = Integer.parseInt(textFieldTeam2Gewinnsaetze.getText());
		
		if(i == 2)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean btnSpielStartenIsAktiviert()
	{
		if(!textFieldSpieler1.getText().isEmpty() && !textFieldSpieler2.getText().isEmpty() &&
		   !textFieldSpieler3.getText().isEmpty() && !textFieldSpieler4.getText().isEmpty() &&
		   !textFieldTeam1.getText().isEmpty() && !textFieldTeam1.getText().isEmpty())
		{
			return true;
		}
		return false;
	}
	
	private static void addiereSpieler() throws Exception
	{
		PreparedStatement myStmt = null;
		
		try
		{
//			prepare statement
			String sqlSpieler1 = "insert into spieler " + " (spieler_name, team_name)" + " values (?, ?)";
			String sqlSpieler2 = "insert into spieler " + " (spieler_name, team_name)" + " values (?, ?)";
			String sqlSpieler3 = "insert into spieler " + " (spieler_name, team_name)" + " values (?, ?)";
			String sqlSpieler4 = "insert into spieler " + " (spieler_name, team_name)" + " values (?, ?)";
			
//			set params
			myStmt = myConn.prepareStatement(sqlSpieler1);
			myStmt.setString(1, textFieldSpieler1.getText());
			myStmt.setString(2, textFieldTeam1.getText());
			myStmt.executeUpdate();
			
			myStmt = myConn.prepareStatement(sqlSpieler2);
			myStmt.setString(1, textFieldSpieler2.getText());
			myStmt.setString(2, textFieldTeam1.getText());
			myStmt.executeUpdate();
			
			myStmt = myConn.prepareStatement(sqlSpieler3);
			myStmt.setString(1, textFieldSpieler3.getText());
			myStmt.setString(2, textFieldTeam2.getText());
			myStmt.executeUpdate();
			
			myStmt = myConn.prepareStatement(sqlSpieler4);
			myStmt.setString(1, textFieldSpieler4.getText());
			myStmt.setString(2, textFieldTeam2.getText());
			myStmt.executeUpdate();
			

			
		} 
		finally
		{
			if (myStmt != null)
			{

			}

			if (myConn != null)
			{
				myConn.close();
			}
		}
	}
	
	private void spielNeuStarten()
	{
		textFieldSpieler1.setText("");
		textFieldSpieler1.setEditable(true);
		textFieldSpieler2.setText("");
		textFieldSpieler2.setEditable(true);
		textFieldSpieler3.setText("");
		textFieldSpieler3.setEditable(true);
		textFieldSpieler4.setText("");
		textFieldSpieler4.setEditable(true);
		
		textFieldTeam1.setText("");
		textFieldTeam1.setEditable(true);
		textFieldTeam2.setText("");
		textFieldTeam2.setEditable(true);
		
		textFieldTeam1Punkte.setText("" + 0);
		textFieldTeam2Punkte.setText("" + 0);
		
		textFieldTeam1Gewinnsaetze.setText("" + 0);
		textFieldTeam2Gewinnsaetze.setText("" + 0);
		
		btnSpielStarten.setEnabled(false);
		
		btnMinusTeam1.setEnabled(false);
		btnMinusTeam2.setEnabled(false);
		btnPlusTeam1.setEnabled(false);
		btnPlusTeam2.setEnabled(false);
		
		chckbxSpieler1.setSelected(false);
		chckbxSpieler2.setSelected(false);
		chckbxSpieler3.setSelected(false);
		chckbxSpieler4.setSelected(false);
	}
}
