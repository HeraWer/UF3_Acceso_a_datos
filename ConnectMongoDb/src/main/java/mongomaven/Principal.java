package mongomaven;


import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Principal {

	public static void main(String[] args) {
		crearConexionMongoAtlas();
		
		/*
		 * Para hacer una conexion local
		 */
		 /*System.out.println("Prueba conexión MongoDB");
	        MongoClient mongo = crearConexionLocalHost();
	 
	        if (mongo != null) {
	            System.out.println("Lista de bases de datos: ");
	            printDatabases(mongo);
	 
	        } else {
	            System.out.println("Error: Conexión no establecida");
	        }*/
	}
	
	public static void crearConexionMongoAtlas() {
		MongoClientURI uri = new MongoClientURI("mongodb+srv://admin:admin@dbm06vallej-fni6h.mongodb.net/Tienda?retryWrites=true&w=majority");
		try (MongoClient mongo = new MongoClient(uri)){
			MongoDatabase database = mongo.getDatabase("Tienda");
			MongoCollection<Document> collection = database.getCollection("Consolas");
			
			FindIterable<Document> docs = collection.find();
			for (Document doc : docs) {
				System.out.println(doc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 * Conexion localhost MongoDB
	 */
	public static MongoClient crearConexionLocalHost() {
		MongoClient mongo = null;
		mongo = new MongoClient("localhost", 27017);
		
		return mongo;
	}
	
	/*
	 * Recorro las db de la conexion para imprimirlas
	 */
	private static void printDatabases(MongoClient mongo) {
        List<String> dbs = mongo.getDatabaseNames();
        for (String db : dbs) {
            System.out.println(" - " + db);
        }
    }
	
	/*public void insertDocument(String collection, String key, Object value) {

	    // Create the document to insert
	    Document document = new Document(key, value);

	    // Select the collection
	    MongoCollection<Document> col = db.getCollection(collection);

	    // Insert the document in the collection
	    col.insertOne(document);
	  }*/
}
