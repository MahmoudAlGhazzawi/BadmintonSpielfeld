package spielerui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import spieler.Spieler;

public class SpielerTableModel extends AbstractTableModel
{
	private static final int NACH_NAME_COL = 0;
	private static final int VOR_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	
	private String[] columnNames = {"Nachname", "Vorname", "Email"};
	private List<Spieler> spieler;
	
	public SpielerTableModel(List<Spieler> dieSpieler)
	{
		spieler = dieSpieler;
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}
	
	@Override
	public int getRowCount()
	{
		return spieler.size();
	}
	
	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		Spieler tempSpieler = spieler.get(row);
		
		switch (col)
		{
		case NACH_NAME_COL:
			return tempSpieler.getNachName();
		case VOR_NAME_COL:
			return tempSpieler.getVorName();
		case EMAIL_COL:
			return tempSpieler.getEmail();
		default:
			return tempSpieler.getNachName();
		}
	}
	
	@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}
