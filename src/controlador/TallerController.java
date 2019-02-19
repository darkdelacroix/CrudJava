package controlador;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import entities.*;
public class TallerController {
	
	//en el controlador ponemos las funciones que llamen al modelo y  que rellenan la vista
	//las acciones de la vista las controla el controlador taller
	//poner le action command a los botones para saber cual esta siendo pulsado
	Taller taller;
	public TallerController() {
		this.taller=new Taller();
	}
	
	
	public LuisTableModel mostrarTalleres(int numPagina,int numResultado) {
		
		return taller.mostrarTalleres(numPagina, numResultado);
	}
	public void insertarTaller(Taller taller) {
		if(taller.getDireccion().equals(""))
		
		this.taller.insertarTaller(taller);
		setWarningMsg("Has introducido un taller","Atención");
	}
	
	public int borrarTalleres(ArrayList<String> alClavesPrimarias,int numRegistros) {
		
		String[] clavesPrimarias=new String[alClavesPrimarias.size()];
		for(int i=0;i<alClavesPrimarias.size();i++) {
			clavesPrimarias[i]=alClavesPrimarias.get(i);
		}
		
		return this.taller.deleteTaller(clavesPrimarias,numRegistros);
		
	}
	
	public void exportarXML(String ruta) {
		this.taller.exportarXML(ruta);
	}
	
	public LuisTableModel buscarTalleres(String nombre,String direccion,String telefono,int numPagina,int numRegistros) {
		
		return this.taller.buscarTalleres(nombre,direccion,telefono,numPagina,numRegistros);
	}
	
	public static void setWarningMsg(String text,String msg){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog(msg);
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
	public void limpiar() {
		this.taller.limpiar();
	}
	public double getPaginaUltima() {
		
		return this.taller.getPaginaUltima();
	}
	
	public void actualizar(int posicionTabla,int pagina,int numResultados,Taller taller) {
		int posicion=(pagina-1)*numResultados+posicionTabla;
		this.taller.actualizarTaller(taller, posicion);
	}
	
} 
