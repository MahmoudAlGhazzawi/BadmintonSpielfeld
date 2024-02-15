package spielerui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import spieler.Spieler;
import spielerDao.SpielerDAO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddSpielerDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField vorNameTextField;
	private JTextField nachNameTextField;
	private JTextField emailTextField;

	private SpielerDAO spielerDAO;

	private SpielerSearchApp spielerSearchApp;	

	public AddSpielerDialog(SpielerSearchApp dieSpielerSearchApp, SpielerDAO dieSpielerDAO) {
		this();
		spielerDAO = dieSpielerDAO;
		spielerSearchApp = dieSpielerSearchApp;
	}

	/**
	 * Create the dialog.
	 */
	public AddSpielerDialog()
	{
		setTitle("Add Spieler");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("Vorname");
			contentPanel.add(lblNewLabel, "2, 2, right, fill");
		}
		{
			vorNameTextField = new JTextField();
			contentPanel.add(vorNameTextField, "4, 2, fill, default");
			vorNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nachname");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			nachNameTextField = new JTextField();
			contentPanel.add(nachNameTextField, "4, 4, fill, default");
			nachNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Email");
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			emailTextField = new JTextField();
			contentPanel.add(emailTextField, "4, 6, fill, default");
			emailTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Speichern");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveSpieler();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveSpieler()
	{
//		get Spieler info from GUI
		String vorName = vorNameTextField.getText();
		String nachName = nachNameTextField.getText();
		String email = emailTextField.getText();
		
		Spieler tempSpieler = new Spieler(nachName, vorName, email);
		
		
		try
		{
//			save to the database
			spielerDAO.addSpieler(tempSpieler);
			
//			close dialog
			setVisible(false);
			dispose();
			
//			refresh GUI list
			spielerSearchApp.refreshSpielerView();
			
//			show success message
			JOptionPane.showMessageDialog(spielerSearchApp, "Spieler added succesfully.", 
										  "Spieler Added",
										  JOptionPane.INFORMATION_MESSAGE);
			
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(spielerSearchApp, "Error saving spieler: "
										  + e.getMessage(), "Error",
										  JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
