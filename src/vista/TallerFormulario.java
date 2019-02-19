package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;

import controlador.TallerController;
import entities.Taller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class TallerFormulario extends JInternalFrame{

	private boolean actualizar=false;
	private JFrame frame;
	public JTextField getTfNombre() {
		return tfNombre;
	}
	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JTextField getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JTextField tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JTextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JTextField getTfLongitud() {
		return tfLongitud;
	}

	public void setTfLongitud(JTextField tfLongitud) {
		this.tfLongitud = tfLongitud;
	}

	public JTextField getTfLatitud() {
		return tfLatitud;
	}

	public void setTfLatitud(JTextField tfLatitud) {
		this.tfLatitud = tfLatitud;
	}

	public JTextField getTfImagen() {
		return tfImagen;
	}

	public void setTfImagen(JTextField tfImagen) {
		this.tfImagen = tfImagen;
	}
	private JTextField tfNombre = new JTextField();
	private JTextField tfDireccion = new JTextField();
	private JTextField tfTelefono = new JTextField();
	private JTextField tfLongitud = new JTextField();
	private JTextField tfLatitud = new JTextField();
	private JTextField tfImagen = new JTextField();
	private Taller tallerActualizar;
	private TallerController tallerController;
	private int posicionTabla;
	private int pagina;
	private int numResultados;
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TallerFormulario taller = new TallerFormulario();
					taller.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public TallerFormulario(TallerController tallerController) {
		initialize();
		this.tallerController=tallerController;
		this.frame.setVisible(true);
		
		
	}

	public TallerFormulario(boolean actualizar, TallerController tallerController, int posicionTabla, int pagina,
			int numResultados,Taller taller) {
		super();
		initialize();
		
		this.actualizar = actualizar;
		this.tallerController = tallerController;
		this.posicionTabla = posicionTabla;
		this.pagina = pagina;
		this.numResultados = numResultados;
		this.tallerActualizar=taller;
		setFields();
		this.frame.setVisible(true);
	}
	private void setFields() {
		tfNombre.setText(tallerActualizar.getNombre());
		tfDireccion.setText(tallerActualizar.getDireccion());
		tfTelefono.setText(tallerActualizar.getTelefono());
		tfLatitud.setText(""+tallerActualizar.getLatitud());
		tfLongitud.setText(""+tallerActualizar.getLongitud());
		tfImagen.setText(tallerActualizar.getNombre());
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 525, 404);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		JPanel panelBotones = new JPanel();
		
		JPanel panelFormulario = new JPanel();
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblDireccion = new JLabel("Direccion");
		
		JLabel lblTelefono = new JLabel("Telefono");
		
		JLabel lblLongitud = new JLabel("Longitud");
		
		JLabel lblLatitud = new JLabel("Latitud");
		
		JLabel lblImagen = new JLabel("Imagen");
		
	//	tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		//tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		
	//	tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		
		//tfLongitud = new JTextField();
		tfLongitud.setColumns(10);
		
		//tfLatitud = new JTextField();
		tfLatitud.setColumns(10);
		
		//tfImagen = new JTextField();
		tfImagen.setColumns(10);
		GroupLayout gl_panelFormulario = new GroupLayout(panelFormulario);
		gl_panelFormulario.setHorizontalGroup(
			gl_panelFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFormulario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFormulario.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFormulario.createSequentialGroup()
							.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFormulario.createSequentialGroup()
							.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFormulario.createSequentialGroup()
							.addComponent(lblLongitud, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tfLongitud, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFormulario.createSequentialGroup()
							.addComponent(lblLatitud, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tfLatitud, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFormulario.createSequentialGroup()
							.addComponent(lblImagen, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tfImagen, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_panelFormulario.setVerticalGroup(
			gl_panelFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLongitud, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLongitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatitud, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLatitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImagen, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfImagen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelFormulario.setLayout(gl_panelFormulario);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnCancelar);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				String numeroRegexp = "^[0-9]+([.][0-9]+)?$";
				float latitud,longitud;
				if(!Pattern.matches(numeroRegexp, tfLatitud.getText())) {
					setWarningMsg("Introduce solo numeros en la Lotitud y longitud,separados por una coma", "Error");}
				else {
				Taller nuevo=new Taller(
						tfNombre.getText(),
						tfDireccion.getText(),
						tfTelefono.getText(),
						Float.parseFloat(tfLatitud.getText()),
						Float.parseFloat(tfLongitud.getText()),
						tfImagen.getText()
						);
				if(actualizar) {
				tallerController.actualizar(posicionTabla, pagina, numResultados, nuevo);
				}
				else {
					tallerController.insertarTaller(nuevo);
				}
			}
			}
		});
		panelBotones.add(btnInsertar);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(panelFormulario);
		frame.getContentPane().add(panelBotones);
	}
	public static void setWarningMsg(String text,String msg){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog(msg);
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
}
