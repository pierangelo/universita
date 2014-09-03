package app.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import app.dominio.Prodotto;

public class FinestraProdotto extends JFrame implements ListSelectionListener {
  
  private Prodotto[] prodotti;
  private final String[] colonneProdotto = {"Descrizione", "Prezzo (EUR)"};
  private final JLabel labelProdotti = new JLabel("  Selezionare il prodotto e la quantita'");
  private final JButton bottoneOK = new JButton("OK");
  
  private Prodotto selezione;
  
  JTextField fieldQuantita = new JTextField("0", 5);
  int quantita;
  
  public FinestraProdotto() {
    
    super("Selezione prodotto");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    labelProdotti.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    
    this.prodotti = Prodotto.getDefaultProdotti();
    
    Object[][] datiProdotti = getDatiProdotti();
    
    MyTableModelProdotti modelloProdotti = new MyTableModelProdotti(colonneProdotto, datiProdotti);
    JTable tabellaProdotti = new JTable(modelloProdotti);
    tabellaProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tabellaProdotti.setColumnSelectionAllowed(false);
    tabellaProdotti.getSelectionModel().addListSelectionListener(this);
    
    JScrollPane scrollPaneProdotti = new JScrollPane(tabellaProdotti);
    
    // Pannello bottone
    bottoneOK.addActionListener(new ListenerFinestraProdotto(this));
    bottoneOK.setEnabled(false);
    JPanel pannelloBottone = new JPanel();
    pannelloBottone.add(bottoneOK);
    
    // Pannello quantita
    JPanel pannelloQuantita = new JPanel();
    JLabel labelQuantita = new JLabel("Quantita: ");
    pannelloQuantita.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    pannelloQuantita.add(labelQuantita);
    pannelloQuantita.add(fieldQuantita);
    
    JPanel pannelloMain = new JPanel(new BorderLayout());
    pannelloMain.add(scrollPaneProdotti, BorderLayout.CENTER);
    pannelloMain.add(pannelloQuantita, BorderLayout.PAGE_END);
    
    // Aggiungi elementi al container
    Container frmContentPane = this.getContentPane();
    frmContentPane.add(labelProdotti, BorderLayout.PAGE_START);
    frmContentPane.add(pannelloMain, BorderLayout.CENTER);
    frmContentPane.add(pannelloBottone, BorderLayout.PAGE_END);
    
    setSize(new Dimension(800, 400));
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  public Prodotto getProdottoSelezionato() {
    return selezione;
  }
  
  public int getQuantita() {
    return quantita;
  }
  
  // Metodi ausiliari
  
  private Object[][] getDatiProdotti() {
    
    int numProdotti = prodotti.length;
    Prodotto prodotto;
    
    Object[][] datiProdotti = new Object[numProdotti][2];
    
    for (int i = 0; i < numProdotti; i++) {
      prodotto = prodotti[i];
      Object[] dati = {prodotto.toString(), prodotto.getPrezzo()+""};
      datiProdotti[i] = dati;
    }
    
    return datiProdotti;
  }
 

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // TODO Auto-generated method stub
    if (e.getValueIsAdjusting())
      return;
    
    ListSelectionModel selModel = (ListSelectionModel)e.getSource();
    int selIndex = selModel.getMinSelectionIndex();

    selezione = prodotti[selIndex];
    
    if (!bottoneOK.isEnabled())
      bottoneOK.setEnabled(true);
    
    fieldQuantita.requestFocusInWindow();
 
  }
  
  public void aspettaOK() {
    synchronized (getContentPane()) {
      try {
        getContentPane().wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
  }
  
}



// Modelli per tabelle

class MyTableModelProdotti extends AbstractTableModel {
  
  private String[] columnNames;
  private Object[][] data;
  
  public MyTableModelProdotti(String[] colNames, Object[][] data) {
    columnNames = colNames;
    this.data = data;
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public int getRowCount() {
    return data.length;
  }
  
  public String getColumnName(int col) {
    return columnNames[col];
  }

  public Object getValueAt(int row, int col) {
    return data[row][col];
  }
  
  public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
  }
  
  public boolean isCellEditable(int row, int col) {
    return false;
  }

}
