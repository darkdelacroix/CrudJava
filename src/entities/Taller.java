package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Taller {
	public String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Float getLatitud() {
		return latitud;
	}
	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}
	public Float getLongitud() {
		return longitud;
	}
	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String direccion;
	public String telefono;
	public Float latitud;
	public Float longitud;
	public String img;
	//faltaria el nombre de la tabla como constante
	public Taller(String nombre, String direccion, String telefono, Float latitud, Float longitud, String img) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.latitud = latitud;
		this.longitud = longitud;
		this.img = img;
		
	}
	Statement statement = null;
	ResultSet resultSet = null;
	Connection conexion =Conection.getConnection();
	public Taller() {
		try {
			statement = conexion.createStatement();
			statement=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery("select * from taller");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertarTaller(Taller taller) {
		try {
			resultSet.moveToInsertRow();
			
			resultSet.updateString("nombre", taller.nombre);
			resultSet.updateString("direccion", taller.direccion);
			resultSet.updateString("telefono", taller.telefono);
			resultSet.updateFloat("latitud", taller.latitud);
			resultSet.updateFloat("longitud", taller.longitud);
			resultSet.updateString("img", taller.img);
			resultSet.insertRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int deleteTaller(String[] clavesPrimarias,int numRegistros) {
		try {
			int rsDelete;
			String query="delete from taller where ";
			for(int i=0;i<clavesPrimarias.length;i++) {
				query+="nombre='"+clavesPrimarias[i]+"'";
				if(i==clavesPrimarias.length-1) {
					
				}else {
					query+=" or ";
				}
			}
			System.out.println(query);
			rsDelete = statement.executeUpdate(query);
			resultSet = statement.executeQuery("select * from taller");
			if (resultSet != null) 
			{
				resultSet.last();    // moves cursor to the last row
				int size2 = resultSet.getRow(); // get row id 
		
			}
			
			return rsDelete;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clavesPrimarias.length;
		
	}

	public Taller mostrarTaller(int posicion) {
		try {
			resultSet.absolute(posicion);
			return new Taller(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getFloat(4),resultSet.getFloat(5),resultSet.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void actualizarTaller(Taller taller,int posicion) {
		try {
			resultSet.absolute(posicion);
			resultSet.updateString("nombre", taller.nombre);
			resultSet.updateString("direccion", taller.direccion);
			resultSet.updateString("telefono", taller.telefono);
			resultSet.updateFloat("latitud", taller.latitud);
			resultSet.updateFloat("longitud", taller.longitud);
			resultSet.updateString("img", taller.img);
			resultSet.updateRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String[] columnNames;
	private Object[][] data;
	private ArrayList<Taller> talleres=new ArrayList();
	private LuisTableModel luisTableModel;
	private double totalPaginas;
	public  LuisTableModel mostrarTalleres(int numPagina,int numResultado) {
		try {
			double size =0;
			int filas=0;
			int filasRS=0;
			if (resultSet != null) 
			{
				resultSet.last();    // moves cursor to the last row
				size = resultSet.getRow(); // get row id 
		
			}
			this.totalPaginas= Math.ceil(size/numResultado);
			int posicionInicial=(numPagina-1)*numResultado;
			resultSet.absolute(posicionInicial);
			int posicionFinal=(numPagina)*numResultado;
			if(posicionFinal-size>0) {
				filas=(int) (numResultado-(posicionFinal-size));
			}else {
				filas=posicionFinal-posicionInicial;
			}
			ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
			int cols=rsmd.getColumnCount();
			String[] columnas = new String[cols+1];
			for (int i=1;i<=cols+1;i++) {
				if(i==cols+1) {
					columnas[i-1]="Selecciona";
				}else {
					columnas[i-1] =rsmd.getColumnName(i);
				}
			}
			Object[][] data=new Object[filas][cols+1];
			while(resultSet.next() && filasRS<filas) {
				for(int j=1;j<=cols+1;j++) {
					if(j==cols+1) {
						data[filasRS][j-1]=new Boolean(false);
					}else {
						data[filasRS][j-1]=new String(""+resultSet.getString(j));
				
					}
				}
				filasRS++;
			}
		
				return new LuisTableModel(columnas,data);
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String escribir() {
		return nombre+direccion;
	}
	public void exportarXML(String ruta) {
		try {
			ArrayList<Taller> talleres=new ArrayList<>();
			resultSet.beforeFirst();
			while(resultSet.next()) {
				Taller taller=new Taller(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getFloat(4),resultSet.getFloat(5),resultSet.getString(6));
				talleres.add(taller);
			}
			ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
			int cols=rsmd.getColumnCount();
			ArrayList<String> columnas = new ArrayList<>();
			for (int i=1;i<=cols;i++) {
					columnas.add(rsmd.getColumnName(i));
			}
			
			XMLGenerator xml=new XMLGenerator(ruta,talleres, columnas);
			try {
				xml.generate("xmlVersion");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	public static void main(String[] args) {
		Taller taller=new Taller();
		taller.mostrarTalleres(1,5);
	}

	public double getPaginaUltima() {
		
		return this.totalPaginas;
	}

	public LuisTableModel buscarTalleres(String nombre, String direccion, String telefono,int numPagina,int numResultado) {
		String buscarString="select * from taller where telefono like ? or nombre like ? or direccion like ?";
	//	String buscarString="select * from taller where nombre=?";
		
		PreparedStatement buscar=null;
		try {
			buscar=conexion.prepareStatement(buscarString);
			buscar.setString(2, nombre);
			buscar.setString(3, direccion);
			buscar.setString(1, telefono);
			resultSet=buscar.executeQuery();
				return mostrarTalleres(numPagina, numResultado);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void limpiar() {
		try {
			resultSet = statement.executeQuery("select * from taller");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
