package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import app.dominio.EccezioneMoltMinMax;
import app.dominio.Immobile;
import app.dominio.Proprietario;
import app.dominio.TipoLinkPossiede;

public class FinestraRipartizioneSpese extends JDialog implements ActionListener {
	
	private final String[] colNames = {"Tipo", "Interno", "Piano", "Superficie (mq)", "Millesimi", "Proprietari", "Quota Spesa (Euro)"};
	private final JButton okButton = new JButton("OK");
	
	public FinestraRipartizioneSpese(Map<Immobile, Double> ripartizione) {
		super((JFrame)null, "Ripartizione Spese", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		Object[][] data = buildData(ripartizione);
		
		MyTableModel model = new MyTableModel(data, colNames);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
		
		JTable table = new JTable(model);
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		if (table.getGridColor().equals(Color.white)) {
			table.setGridColor(Color.LIGHT_GRAY);
		}
		
		tablePanel.add(scrollPane);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		okButton.addActionListener(this);
		buttonPanel.add(okButton);
		
		this.getContentPane().add(tablePanel);
		this.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private Object[][] buildData(Map<Immobile, Double> ripartizione) {
		Object[][] data = new Object[ripartizione.size()][colNames.length];
		
		int rowCount = 0;
		Immobile immobile;
		Set<TipoLinkPossiede> linkPossiede = null;
		Proprietario proprietario;
		for (Entry<Immobile, Double> entry : ripartizione.entrySet()) {
			immobile = entry.getKey();
			data[rowCount][0] = immobile.getClass().getSimpleName();
			data[rowCount][1] = immobile.getInterno();
			data[rowCount][2] = immobile.getPiano();
			data[rowCount][3] = immobile.getMetriQuadri();
			data[rowCount][4] = immobile.getMillesimi();
			
			try {
				linkPossiede = immobile.getLinkPossiede();
			} catch (EccezioneMoltMinMax e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String proprietari = "";
			int i = 0;
			for (TipoLinkPossiede link : linkPossiede) {
				proprietario = link.getProprietario();
				proprietari += proprietario.getNome() + " " + proprietario.getCognome() + " (" + link.getQuota() + "%)";
				if (i != linkPossiede.size()-1) {
					proprietari += " - ";
				}
				i++;
			}
			
			data[rowCount][5] = proprietari;
			data[rowCount][6] = entry.getValue();
			
			rowCount++;
		}
		return data;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		this.dispose();
	}
	
	
	// Table Model
	class MyTableModel extends DefaultTableModel {
		
		public MyTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		
		public Class<?> getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }
	    
		
		public boolean isCellEditable(int row, int col) {
	        return false;
	    }

	}

}
