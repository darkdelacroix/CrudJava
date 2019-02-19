package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import entities.*;
import controlador.*;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class IndexVista extends JFrame {

	private Taller tallerActualizar;
	private double paginaUltima;
	private TallerController tallerController;
	private int paginaActual=1;
	private LuisTableModel tableModel;
	private JPanel contentPane;
	private JTextField tfDireccion;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JTextField textField_4;
	private JTable tableIndex;
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private JButton btnPrimeraPagina;
	private JLabel lblPaginaTotales;
	private JComboBox cmbNumeroRegistros;
	private Taller taller;
	private JLabel lblPaginaActual;

	
	public String elegirRuta() {
		JFileChooser chooser = new JFileChooser(); 
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		if(file!=null) {
		String fullPath = file.getAbsolutePath(); 
		
		return fullPath;
		}
		return "error";
		
	}
	/**
	 * Create the frame.
	 */
	public IndexVista() {
		//taller=new Taller();
		tallerController=new TallerController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1101, 652);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmLogout = new JMenuItem("LogOut");
		mnArchivo.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelCabecera = new JPanel();
		panelCabecera.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JButton btnExportar = new JButton("Exportar a XML");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ruta=elegirRuta();
				if (ruta.equals("error")) {
					setWarningMsg("No has elegido ningun directorio", "error");
				}else {
				tallerController.exportarXML(ruta); 
				}
			}
		});
		
		
		GroupLayout gl_panelCabecera = new GroupLayout(panelCabecera);
		gl_panelCabecera.setHorizontalGroup(
			gl_panelCabecera.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCabecera.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(30)
					.addComponent(btnExportar)
					.addContainerGap(866, Short.MAX_VALUE))
		);
		gl_panelCabecera.setVerticalGroup(
			gl_panelCabecera.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCabecera.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCabecera.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnExportar))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelCabecera.setLayout(gl_panelCabecera);
		
		JPanel panelBuscador = new JPanel();
		panelBuscador.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panelTabla = new JPanel();
		System.out.println(""+paginaUltima);
		JPanel panelPaginador = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelTabla, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
						.addComponent(panelPaginador, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
						.addComponent(panelCabecera, GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
						.addComponent(panelBuscador, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(panelCabecera, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBuscador, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabla, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelPaginador, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		
		btnPrimeraPagina = new JButton("Primera Pagina");
	/*	if(paginaActual==1) {
			btnPrimeraPagina.setEnabled(true);
			btnAnterior.setEnabled(true);
		}*/
		btnPrimeraPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaActual=1;
				tableIndex.setModel(tallerController.mostrarTalleres(paginaActual, (int)cmbNumeroRegistros.getSelectedItem()));
				tableIndex.repaint();
				lblPaginaActual.setText(paginaActual+"");
			}
		});
		btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(paginaActual==1) {
					
				}else {
					paginaActual--;
				}
				tableIndex.setModel(tallerController.mostrarTalleres(paginaActual, (int)cmbNumeroRegistros.getSelectedItem()));
				tableIndex.repaint();
				lblPaginaActual.setText(paginaActual+"");
			}
		});
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(paginaActual<paginaUltima) {
				tableIndex.setModel(tallerController.mostrarTalleres(++paginaActual, (int)cmbNumeroRegistros.getSelectedItem()));
				tableIndex.repaint();
				
				}
				lblPaginaActual.setText(paginaActual+"");

				System.out.println(""+paginaUltima);
			}
		});
		
		JButton btnUltimaPagina = new JButton("Ultima pagina");
		btnUltimaPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableIndex.setModel(tallerController.mostrarTalleres((int)paginaUltima, (int)cmbNumeroRegistros.getSelectedItem()));
				paginaActual=(int)paginaUltima;
				tableIndex.repaint();
				lblPaginaActual.setText(paginaActual+"");
			}
		});
		
		lblPaginaActual = new JLabel("Pagina");
		
		lblPaginaActual.setText(paginaActual+"");
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		lblPaginaTotales = new JLabel("");
		
		cmbNumeroRegistros = new JComboBox();
		cmbNumeroRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(cmbNumeroRegistros.getSelectedIndex()) {
				case 0:
					tableIndex.setModel(tallerController.mostrarTalleres(paginaActual, 5));
					paginaUltima=tallerController.getPaginaUltima();
					tableIndex.repaint();
					break;
				case 1:
					tableIndex.setModel(tallerController.mostrarTalleres(paginaActual, 10));
					paginaUltima=tallerController.getPaginaUltima();
					tableIndex.repaint();
					break;
				case 2:
					tableIndex.setModel(tallerController.mostrarTalleres(paginaActual, 20));
					paginaUltima=tallerController.getPaginaUltima();
					tableIndex.repaint();
					break;
				default:
					tallerController.mostrarTalleres(paginaActual, 10);
					paginaUltima=tallerController.getPaginaUltima();
					break;
					
				}
			}
		});
		cmbNumeroRegistros.setModel(new DefaultComboBoxModel(new Integer[] {5, 10, 20}));
		//cmbNumeroRegistros.setSelectedIndex(1);
		GroupLayout gl_panelPaginador = new GroupLayout(panelPaginador);
		gl_panelPaginador.setHorizontalGroup(
			gl_panelPaginador.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPaginador.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPrimeraPagina)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAnterior)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPaginaActual)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPaginaTotales)
					.addGap(25)
					.addComponent(btnSiguiente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUltimaPagina)
					.addGap(18)
					.addComponent(cmbNumeroRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(491, Short.MAX_VALUE))
		);
		gl_panelPaginador.setVerticalGroup(
			gl_panelPaginador.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPaginador.createSequentialGroup()
					.addGroup(gl_panelPaginador.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrimeraPagina)
						.addComponent(btnAnterior)
						.addComponent(btnSiguiente)
						.addComponent(btnUltimaPagina)
						.addComponent(lblPaginaActual)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPaginaTotales)
						.addComponent(cmbNumeroRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelPaginador.setLayout(gl_panelPaginador);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panelTabla = new GroupLayout(panelTabla);
		gl_panelTabla.setHorizontalGroup(
			gl_panelTabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTabla.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelTabla.setVerticalGroup(
			gl_panelTabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTabla.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableIndex = new JTable();
	
		scrollPane.setViewportView(tableIndex);
		panelTabla.setLayout(gl_panelTabla);
		
		//table.setRowSelectionAllowed(true);
		//table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
       //  table.setColumnSelectionAllowed(true);
      //   table.setRowSelectionAllowed(true);
       //  table.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableIndex.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//tableIndex.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		JButton btActualizar = new JButton("Actualizar");
		btActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre,direccion,telefono,img;
				float latitud,longitud;
				int posicion=0;
				int columnas=tableIndex.getColumnCount();
				int filas=tableIndex.getRowCount();
				for(int i=0;i<filas;i++) {
					if((boolean) tableIndex.getValueAt(i, columnas-1)) {
						
						nombre=(String)tableIndex.getValueAt(i, 0);
						direccion=(String)tableIndex.getValueAt(i, 1);
						telefono=(String)tableIndex.getValueAt(i, 2);
						latitud=Float.parseFloat((String) tableIndex.getValueAt(i, 3));
						longitud=Float.parseFloat((String) tableIndex.getValueAt(i, 4));
						img=(String)tableIndex.getValueAt(i, 5);
						tallerActualizar=new Taller(nombre,direccion,telefono,latitud,longitud,img);
						posicion=i+1;
						TallerFormulario tallerFormulario=new TallerFormulario(true,tallerController,posicion,paginaActual,cmbNumeroRegistros.getSelectedIndex(),tallerActualizar);
							tallerFormulario.setVisible(true);
					}
				}
			
				
					//setWarningMsg("Elige al menos un elemento para borrar", "Atención");
				
			}
		});
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TallerFormulario vista=new TallerFormulario(tallerController);
				vista.setVisible(true);
				
				
			}
		});
		
		JButton btBorrar = new JButton("Borrar");
		btBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int columnas=tableIndex.getColumnCount();
				ArrayList<String> clavesPrimarias=new ArrayList<String>();
				int filas=tableIndex.getRowCount();
				for(int i=0;i<filas;i++) {
					if((boolean) tableIndex.getValueAt(i, columnas-1)) {
						clavesPrimarias.add((String)tableIndex.getValueAt(i, 0));
					}
				}
				
				if(clavesPrimarias.size()>0) {
					int filasAfectadas=tallerController.borrarTalleres(clavesPrimarias,cmbNumeroRegistros.getSelectedIndex());
					paginaUltima=tallerController.getPaginaUltima();
					if(filasAfectadas>0) {
						setWarningMsg("Se han modificado "+filasAfectadas+" registros", "Atención");
						tableIndex.repaint();
					}
				}else {
					setWarningMsg("Elige al menos un elemento para borrar", "Atención");
				}
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblDireccion = new JLabel("Direccion");
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		
		JButton btBuscar = new JButton("Buscar");
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre,direccion,telefono;
				if(tfNombre.getText().equals("")) {
				nombre="$$$";
				}else{ 
					nombre="%"+tfNombre.getText()+"%";
				}
				if(tfDireccion.getText().equals("")) {
					direccion="$$$";
					}else{ 
						direccion="%"+tfDireccion.getText()+"%";
					}
				if(tfTelefono.getText().equals("")) {
					telefono="$$$";
					}else{ 
						telefono="%"+tfTelefono.getText()+"%";
					}
				tableIndex.setModel(tallerController.buscarTalleres(nombre,direccion,telefono, paginaActual,5));
				paginaUltima=tallerController.getPaginaUltima();
							}
		});
		
		JButton btLimpiar = new JButton("Limpiar");
		btLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tallerController.limpiar();
				cmbNumeroRegistros.setSelectedIndex(0);
				tfDireccion.setText("");
				tfNombre.setText("");
				tfTelefono.setText("");
				tableIndex.setModel(tallerController.mostrarTalleres(1, 5));
				tableIndex.repaint();
				
			}
		});
		GroupLayout gl_panelBuscador = new GroupLayout(panelBuscador);
		gl_panelBuscador.setHorizontalGroup(
			gl_panelBuscador.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBuscador.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panelBuscador.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelBuscador.createSequentialGroup()
							.addComponent(lblNombre)
							.addGap(18)
							.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblTelefono)
							.addGap(18)
							.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelBuscador.createSequentialGroup()
							.addComponent(lblDireccion)
							.addGap(18)
							.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelBuscador.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelBuscador.createSequentialGroup()
							.addComponent(btBuscar)
							.addPreferredGap(ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
							.addComponent(btnInsertar))
						.addGroup(gl_panelBuscador.createSequentialGroup()
							.addComponent(btLimpiar)
							.addPreferredGap(ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
							.addComponent(btBorrar)))
					.addGap(18)
					.addComponent(btActualizar)
					.addGap(99))
		);
		gl_panelBuscador.setVerticalGroup(
			gl_panelBuscador.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscador.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBuscador.createParallelGroup(Alignment.BASELINE)
						.addComponent(btActualizar)
						.addComponent(btnInsertar)
						.addComponent(lblNombre)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefono)
						.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btBuscar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelBuscador.createParallelGroup(Alignment.BASELINE)
						.addComponent(btBorrar)
						.addComponent(lblDireccion)
						.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btLimpiar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBuscador.setLayout(gl_panelBuscador);
		contentPane.setLayout(gl_contentPane);
		FillDataTable();
	}
	
	//Aqui se muestran los primeros resultados
	public void FillDataTable() {
	
		tableIndex.setModel(tallerController.mostrarTalleres(paginaActual,5));
		cmbNumeroRegistros.setSelectedIndex(0);
		paginaUltima=tallerController.getPaginaUltima();
		tableIndex.repaint();
	}
	
	public static void setWarningMsg(String text,String msg){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog(msg);
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
	@Override
	public synchronized void addFocusListener(FocusListener l) {
		// TODO Auto-generated method stub
		super.addFocusListener(l);
		tableIndex.setModel(tallerController.mostrarTalleres(paginaActual, cmbNumeroRegistros.getSelectedIndex()));
		tableIndex.repaint();
	}
}
