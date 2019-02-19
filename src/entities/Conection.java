package entities;
import java.sql.*;

public  class Conection {
	 // Propiedades
    private static Connection conn = null;
    private String driver;
    private String url,db;
    private String usuario;
    private String password;
 
    // Constructor
    private Conection(){
 this.db= "taller";
 this.url = "jdbc:mysql://localhost/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 this.driver = "com.mysql.cj.jdbc.Driver";
 this.usuario = "root";
 this.password = "";
  
 try{
     Class.forName(driver);
     conn = DriverManager.getConnection(url, usuario, password);
     
 }
 catch(ClassNotFoundException | SQLException e){
     e.printStackTrace();
 }
    } // Fin constructor
 
    // Métodos
    public static Connection getConnection(){
  
 if (conn == null){
     new Conection();
 }
  
 return conn;
    } // Fin getConnection
    
    public static void cerrarConexion()
    {
    if (conn != null)
    {
    try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    }
    public static void main(String[] args) {
		Connection conexion=Conection.getConnection();
		if (conexion!=null) {
			System.out.println("bien");
		}
	}

}
