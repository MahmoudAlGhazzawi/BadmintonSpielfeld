package spielerui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import spieler.Spieler;
import spielerDao.SpielerDAO;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.JScrollBar;


public class SpielerSearchApp extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldNachName;
	private JButton btnNewButton;
	
	private SpielerDAO spielerDAO;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SpielerSearchApp frame = new SpielerSearchApp();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpielerSearchApp()
	{
//		Create the DAO
		try
		{
			spielerDAO = new SpielerDAO();
		} 
		catch (Exception e)
		{
			JOptionPane.showConfirmDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Spieler Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNachName = new JLabel("Nachname eingeben");
		lblNachName.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNachName);
		
		textFieldNachName = new JTextField();
		panel.add(textFieldNachName);
		textFieldNachName.setColumns(10);
		
		btnNewButton = new JButton("Suchen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Get nach name from the textfield
				
//				Call DAO and get Spieler for nach name
				
//				if nach name is empty, then get all Spieler
				
//				print out Spieler
				
				try
				{
					String nachName = textFieldNachName.getText();
					
					List<Spieler> spieler = null;
					
					if(nachName != null && nachName.trim().length() > 0)
					{
						spieler = spielerDAO.searchSpieler(nachName);
					}
					else
					{
						spieler = spielerDAO.getAllSpieler();
					}
					
//				create the model anmd update the "table"
					SpielerTableModel model = new SpielerTableModel(spieler);
					
					table.setModel(model);
					
					/*
					for(Spieler temp: spieler)
					{
						System.out.println(temp);
					}
					*/
				} 
				catch (Exception e2)
				{
					JOptionPane.showConfirmDialog(SpielerSearchApp.this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnNewButton_1 = new JButton("Add Spieler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				create dialog
				AddSpielerDialog dialog = new AddSpielerDialog(SpielerSearchApp.this, spielerDAO);
				
//				show dialog
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_1);
	}

	public void refreshSpielerView()
	{
		try
		{
			List<Spieler> spieler = spielerDAO.getAllSpieler();
			
//			create the model and update the "table"
			SpielerTableModel model = new SpielerTableModel(spieler);
			
			table.setModel(model);
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
