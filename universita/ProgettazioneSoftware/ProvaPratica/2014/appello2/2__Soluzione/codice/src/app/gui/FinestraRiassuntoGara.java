package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import app.dominio.*;

public class FinestraRiassuntoGara extends JDialog implements ActionListener {

	private JList listaVincitori;
	private JTable tabellaAtleti;
	private JTabbedPane tabPnl = new JTabbedPane();

	private final JPanel sudPnl = new JPanel();
	private final JButton okBtn = new JButton("OK");

	public FinestraRiassuntoGara(Set<TipoLinkVince> vincitori, Set<TipoLinkPartecipa> linkPartecipanti) {

		super((JFrame)null, "Riassunto della gara e vincitori", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		listaVincitori = popolaListaVincitori(vincitori);
		tabellaAtleti = popolaTabellaClassifica(linkPartecipanti);

		tabPnl.add("Tabella degli Atleti", new JScrollPane(tabellaAtleti));
		tabPnl.add("Lista dei Vincitori", new JScrollPane(listaVincitori));

		okBtn.addActionListener(this);
		sudPnl.add(okBtn);

		getContentPane().add(tabPnl, BorderLayout.CENTER);
		getContentPane().add(sudPnl, BorderLayout.PAGE_END);
	}

	public void visualizza() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JTable popolaTabellaClassifica(Set<TipoLinkPartecipa> linkPartecipanti) {
		String[][] tabella = new String[linkPartecipanti.size()][2];
		int riga = 0;
		Atleta atleta;
		for (TipoLinkPartecipa l : linkPartecipanti) {
			atleta = l.getAtleta();
			tabella[riga][0] = atleta.getNome() + " " + atleta.getCognome() + " ("+ atleta.getNazione() +")";
			tabella[riga++][1] = new DecimalFormat("#.##").format(l.getMtPercorsi());
		}
		String[] intestazioni = { "Atleta", "Lunghezza Salto (mt)"};
		
		MyTableModel model = new MyTableModel(tabella, intestazioni);
		
		JTable table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		if (table.getGridColor().equals(Color.white)) {
			table.setGridColor(Color.LIGHT_GRAY);
		}
		return table;
	}

	private JList popolaListaVincitori(Set<TipoLinkVince> vincitori) {
		Vector<String> nomeVincitori = new Vector<String>();
		Atleta atleta;
		Tavola tavola = null;
		for (TipoLinkVince link : vincitori) {
			atleta = link.getAtleta();
			try {
				tavola = atleta.getLinkPossiede().getTavola();
			} catch (EccezioneMoltMinMax e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nomeVincitori.add(atleta.getNome() + " " + atleta.getCognome() + " ("+ atleta.getNazione() +") su tavola " + tavola.getMarca());
		}
		return new JList(nomeVincitori);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setVisible(false);
		dispose();
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
