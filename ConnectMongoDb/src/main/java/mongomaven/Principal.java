package mongomaven;

import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.QueryBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Principal {

	static MongoClient mongo = new MongoClient();
	static MongoCollection<Document> colConsoles;
	static MongoCollection<Document> colGames;

	public static void main(String[] args) {
		crearConexionMongoAtlas();
		// insertDocumentConsole("PlayStation 1", 100);
		// insertDocumentGame("Super Mario Galaxy", 65, "Nintendo Wii");
		// deleteDocumentConsole("PS4");
		// deleteDocumentGame("Horizon");
		// updateDocumentConsole("Xbox", 1);
		// updateDocumentGame("God of War 3", 1);
		// searchSample(colConsoles, "Xbox");
		// searchSample(colGames, "The Last of Us");
		searchComplex(colGames, "God of War 3", 1);
		/*printCollection(colConsoles);
		System.out.println("------------------------");
		printCollection(colGames);*/

		/*
		 * Para hacer una conexion local
		 */
		/*
		 * System.out.println("Prueba conexión MongoDB"); MongoClient mongo =
		 * crearConexionLocalHost();
		 * 
		 * if (mongo != null) { System.out.println("Lista de bases de datos: ");
		 * printDatabases(mongo);
		 * 
		 * } else { System.out.println("Error: Conexión no establecida"); }
		 */
	}

	/*
	 * Creamos la conexion a la base de datos y iniciamos las collection que tenemos
	 * en la bdd
	 */
	public static void crearConexionMongoAtlas() {
		MongoClientURI uri = new MongoClientURI(
				"mongodb+srv://admin:admin@dbm06vallej-fni6h.mongodb.net/Tienda?retryWrites=true&w=majority");
		try {
			mongo = new MongoClient(uri);
			MongoDatabase database = mongo.getDatabase("Tienda");
			colConsoles = database.getCollection("Consolas");
			colGames = database.getCollection("Juegos");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Conexion localhost MongoDB
	 */
	/*
	 * public static MongoClient crearConexionLocalHost() { MongoClient mongo =
	 * null; mongo = new MongoClient("localhost", 27017);
	 * 
	 * return mongo; }
	 */

	/*
	 * Recorro las db de la conexion para imprimirlas
	 */
	private static void printDatabases(MongoClient mongo) {
		List<String> dbs = mongo.getDatabaseNames();
		for (String db : dbs) {
			System.out.println(" - " + db);
		}
	}

	/*
	 * Metodo para insertar documentos en la collection Consola
	 */
	public static void insertDocumentConsole(String name, int price) {

		Document document = new Document("nombre", name).append("precio", price);

		colConsoles.insertOne(document);
	}

	/*
	 * Metodo para insertar documentos en la collection Juegos
	 */
	public static void insertDocumentGame(String name, int price, String platform) {

		Document document = new Document("nombre", name).append("precio", price).append("plataforma", platform);

		colGames.insertOne(document);
	}

	/*
	 * Metodo para imprimir los datos de una collection
	 */
	public static void printCollection(MongoCollection<Document> collection) {

		FindIterable<Document> docs = collection.find();
		for (Document doc : docs) {
			System.out.println(doc);
		}
	}

	/*
	 * Metodo para eliminar documentos de la collection Consolas
	 */
	public static void deleteDocumentConsole(String name) {

		Document findDocument = new Document("nombre", name);

		colConsoles.findOneAndDelete(findDocument);
	}

	/*
	 * Metodo para eliminar documentos de la collection Games
	 */
	public static void deleteDocumentGame(String name) {

		Document findDocument = new Document("nombre", name);

		colGames.findOneAndDelete(findDocument);
	}

	/*
	 * Metodo que edita el documento de la collection Consolas
	 * se podria hacer en un metodo ya que se pasan los mismos parametros pero lo he dejado a si
	 */
	public static void updateDocumentConsole(String name, int price) {

		Document findDocument = new Document("nombre", name);

		Document updateDocument = new Document("$set", new Document("precio", price));

		colConsoles.findOneAndUpdate(findDocument, updateDocument);
	}

	/*
	 * Metodo que edita el documento de la collection Juegos
	 */
	public static void updateDocumentGame(String name, int price) {

		Document findDocument = new Document("nombre", name);

		Document updateDocument = new Document("$set", new Document("precio", price));

		colGames.findOneAndUpdate(findDocument, updateDocument);
	}
	
	/*
	 * Metodo donde hacemos una consulta basica a una collection filtrando por un solo campo
	 */
	public static void searchSample(MongoCollection<Document> collection, String name) {
		
		Document findDocument = new Document("nombre", name);
		
		MongoCursor<Document> resultDocument = collection.find(findDocument).iterator();
		
		Document result;
		
		while(resultDocument.hasNext()) {
			result = resultDocument.next();
			System.out.println("Nombre: " + result.getString("nombre") + " Precio:  " + result.getInteger("precio"));
		}
	}
	
	/*
	 * Metodo para buscar documentos mas complejos, filtrando por dos campos
	 */
	public static void searchComplex(MongoCollection<Document> collection, String name, int price) {
		
		Document findDocument = new Document("nombre", name).append("precio", price);
		
		MongoCursor<Document> resultDocument = collection.find(findDocument).iterator();
		
		Document result;
		
		while(resultDocument.hasNext()) {
			result = resultDocument.next();
			System.out.println("Nombre: " + result.getString("nombre") + " Precio:  " + result.getInteger("precio"));
		}
	}

	/*
	 * Metodo para hacer la desconexion de MongoDB
	 */
	public void disconnect() {
		mongo.close();
	}
}
