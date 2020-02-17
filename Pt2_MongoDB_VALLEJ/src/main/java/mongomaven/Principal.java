package mongomaven;

import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Principal {

	static MongoClient mongo = new MongoClient();
	static MongoCollection<Document> colMovies;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static void main(String[] args) {
		/*
		 * Para hacer una conexion local
		 */
		
		  System.out.println("Prueba conexión MongoDB"); 
		  mongo = crearConexionLocalHost();
		  
		  if (mongo != null) { System.out.println("Lista de bases de datos: ");
		  
		  } else { 
			  System.out.println("Error: Conexión no establecida"); }
		  
		  printCollection(colMovies);
		 

	}

	/*
	 * Conexion localhost MongoDB
	 */
	public static MongoClient crearConexionLocalHost() {
		mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("Practica2");
		colMovies = database.getCollection("movies");
		return mongo;
	}
	
	/*
	 * 
	 * Recorro las db de la conexion para imprimirlas
	 */
	private static void printDatabases() {
		List<String> dbs = mongo.getDatabaseNames();
		for (String db : dbs) {
			System.out.println(" - " + db);
		}
	}
	
	/* ********************* Consultas / Buscar documentos ********************* */
	
	/*
	 * 1. Obtener todos los documentos
	 */
	public static void printCollection(MongoCollection<Document> collection) {
		FindIterable<Document> docs = collection.find();
		for (Document doc : docs) {
			String json = gson.toJson(doc);
			System.out.println(json);
			
		}
	}
	
	/*
	 * 2. Obtener documentos con writer igual a "Quentin Tarantino" 
	 */
	/*
	 * 3. Obtener documentos con actors que incluyan a "Brad Pitt" 
	 */
	/*
	 * 4. Obtener documentos con franchise igual a "The Hobbit" 
	 */
	/*
	 * 5. Obtener todas las películas de los 90s. 
	 */
	/*
	 * 6. Obtener las películas estrenadas entre el año 2000 y 2010.
	 */
	
	/* ********************* Actualizar Documentos ********************* */
	
	/*
	 * 1. Agregar sinopsis a "The Hobbit: An Unexpected Journey" : "A reluctant hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home - and the gold within it - from the dragon Smaug." 
	 */
	/*
	 * 2. Agregar sinopsis a "The Hobbit: The Desolation of Smaug" : "The dwarves, along with Bilbo Baggins and Gandalf the Grey, continue their quest to reclaim Erebor, their homeland, from Smaug. Bilbo Baggins is in possession of a mysterious and magical ring." 
	 */
	/*
	 * 3. Agregar una actor llamado "Samuel L. Jackson" a la película "Pulp Fiction"
	 */
	
	/* ********************* Busqueda por Texto / Text Search ********************* */
	
	/*
	 * 1. Encontrar las películas que en la sinopsis contengan la palabra "Bilbo" 
	 */
	/*
	 * 2. Encontrar las películas que en la sinopsis contengan la palabra "Gandalf" 
	 */
	/*
	 * 3. Encontrar las películas que en la sinopsis contengan la palabra "Bilbo" y no la palabra "Gandalf" 
	 */
	/*
	 * 4. Encontrar las películas que en la sinopsis contengan la palabra "dwarves" ó "hobbit" 
	 */
	/*
	 * 5. Encontrar las películas que en la sinopsis contengan la palabra "gold" y "dragon"
	 */
	
	/* ********************* Eliminar Documentos ********************* */
	
	/*
	 * 1. Eliminar la película "Pee Wee Herman's Big Adventure" 
	 */
	/*
	 * 2. Eliminar la película "Avatar"
	 */
	

}
