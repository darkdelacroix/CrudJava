package entities;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XMLGenerator {

  /*  public static void main(String[] args) {
        String nombre_archivo = "geekyxml";
        ArrayList key = new ArrayList();
        ArrayList value = new ArrayList();

        key.add("opcion1");
        value.add("22");

        key.add("opcion2");
        value.add("22");

        key.add("opcion3");
        value.add("22");

        key.add("opcion4");
        value.add("25");

        try { 
            generate(nombre_archivo);
        } catch (Exception e) {}
    }*/

    private static ArrayList<Taller> talleres;
    private ArrayList<String> key;
    private ArrayList<String> value;
    private static ArrayList<String> columnas;
    private static String ruta;
    public XMLGenerator(String ruta,ArrayList<Taller> talleres,ArrayList<String> columnas) {
    	this.talleres=talleres;
    	this.columnas=columnas;
    	this.ruta=ruta;
    }
    
    public void setKeys() {
    	//key.add
    }
    
    public static void generate(String name) throws Exception{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");

            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value
            for(int i=0; i<talleres.size();i++){
                //Item Node
                Element itemNode = document.createElement("Taller"); 
                //Key Node
                Element keyNode1 = document.createElement(columnas.get(0)); 
                Text nodeKeyValue1 = document.createTextNode(""+talleres.get(i).getNombre());
                keyNode1.appendChild(nodeKeyValue1);   
                Element keyNode2 = document.createElement(columnas.get(1)); 
                Text nodeKeyValue2 = document.createTextNode(""+talleres.get(i).getDireccion());
                keyNode2.appendChild(nodeKeyValue2);  
                Element keyNode3 = document.createElement(columnas.get(2)); 
                Text nodeKeyValue3 = document.createTextNode(""+talleres.get(i).getTelefono());
                keyNode3.appendChild(nodeKeyValue3);  
                Element keyNode4 = document.createElement(columnas.get(3)); 
                Text nodeKeyValue4 = document.createTextNode(""+talleres.get(i).getLatitud());
                keyNode4.appendChild(nodeKeyValue4);  
                Element keyNode5 = document.createElement(columnas.get(4)); 
                Text nodeKeyValue5 = document.createTextNode(""+talleres.get(i).getLongitud());
                keyNode5.appendChild(nodeKeyValue5);  
                Element keyNode6 = document.createElement(columnas.get(5)); 
                Text nodeKeyValue6 = document.createTextNode(""+talleres.get(i).getImg());
                keyNode6.appendChild(nodeKeyValue6);
                //append keyNode and valueNode to itemNode
                itemNode.appendChild(keyNode1);
                itemNode.appendChild(keyNode2);
                itemNode.appendChild(keyNode3);
                itemNode.appendChild(keyNode4);
                itemNode.appendChild(keyNode5);
                itemNode.appendChild(keyNode6);
                //append itemNode to raiz
                raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
            }                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            
      
            Result result = new StreamResult(new java.io.File(ruta,name+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        
    }

}