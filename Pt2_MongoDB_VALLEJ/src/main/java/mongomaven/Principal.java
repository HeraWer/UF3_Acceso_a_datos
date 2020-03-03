package mongomaven;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.DBCollectionCountOptions;

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

		if (mongo != null) {
			System.out.println("Lista de bases de datos: ");

		} else {
			System.out.println("Error: Conexión no establecida");
		}

		// printCollection(colMovies);
		// searchWriter(colMovies, "Quentin Tarantino");
		// searchActors(colMovies, "Brad Pitt");
		// searchFranchise(colMovies, "The Hobbit");
		//searchFilm1990();
		//searchFilm20002010();
		updateSynopsis1();
		
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
	 * Metodo donde hacemos una consulta basica a una collection filtrando por un
	 * solo campo
	 */
	public static void searchWriter(MongoCollection<Document> collection, String name) {

		Document findDocument = new Document("writer", name);
		FindIterable<Document> docs = collection.find(findDocument);

		for (Document doc : docs) {
			String json = gson.toJson(doc);
			System.out.println(json);

		}
	}
	// MongoCursor<Document> resultDocument =
	// collection.find(findDocument).iterator();
	/*
	 * while(resultDocument.hasNext()) { result = resultDocument.next();
	 * System.out.println("Nombre: " + result.getString("nombre") + " Precio:  " +
	 * result.getInteger("precio")); }
	 */

	/*
	 * 3. Obtener documentos con actors que incluyan a "Brad Pitt"
	 */

	/*
	 * Metodo donde hacemos una consulta basica a una collection filtrando por un
	 * solo campo
	 */
	public static void searchActors(MongoCollection<Document> collection, String name) {

		Document findDocument = new Document("actors", name);
		FindIterable<Document> docs = collection.find(findDocument);

		for (Document doc : docs) {
			String json = gson.toJson(doc);
			System.out.println(json);

		}
	}

	/*
	 * 4. Obtener documentos con franchise igual a "The Hobbit"
	 */

	/*
	 * Metodo donde hacemos una consulta basica a una collection filtrando por un
	 * solo campo
	 */
	public static void searchFranchise(MongoCollection<Document> collection, String name) {

		Document findDocument = new Document("franchise", name);
		FindIterable<Document> docs = collection.find(findDocument);

		for (Document doc : docs) {
			String json = gson.toJson(doc);
			System.out.println(json);

		}
	}

	/*
	 * 5. Obtener todas las películas de los 90s.
	 */

	public static void searchFilm1990() {
		/*
		 * DB db = mongo.getDB("Practica2"); DBCollection dbc =
		 * db.getCollection("movies"); DBObject query = new BasicDBObject("year", new
		 * BasicDBObject("$gte", 1990)).append("year", new BasicDBObject("$lte", 1999));
		 * DBCursor cursor = dbc.find(query);
		 * 
		 * while(cursor.hasNext()) { System.out.println(cursor.next()); }
		 */
		Document findDocument = new Document("year", new
				  BasicDBObject("$gte", 1990)).append("year", new BasicDBObject("$lte", 1999));

		FindIterable<Document> docs = colMovies.find(findDocument);
		for (Document doc : docs) {
			String json = gson.toJson(doc);
			System.out.println(json);

		}
	}

	/*
	 * 6. Obtener las películas estrenadas entre el año 2000 y 2010.
	 */
	
	// FALTA TERMINAR SALEN MAS RESULTADOS
	public static void searchFilm20002010() {
		
		Document findDocument = new Document("year", new
				  BasicDBObject("$gt", 2000)).append("year", new BasicDBObject("$lt", 2010));

		FindIterable<Document> docs = colMovies.find(findDocument);
		for (Document doc : docs) {
			String json = gson.toJson(doc);
			System.out.println(json);

		}
	}

	/* ********************* Actualizar Documentos ********************* */

	/*
	 * 1. Agregar sinopsis a "The Hobbit: An Unexpected Journey" :
	 * "A reluctant hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home - and the gold within it - from the dragon Smaug."
	 */
	
	public static void updateSynopsis1() {
		Document findDocument = new Document("title", "The Hobbit: An Unexpected Journey");
		BasicDBObject updated = new BasicDBObject("synopsis", "A reluctant hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home - and the gold within it - from the dragon Smaug.");
		colMovies.updateOne(findDocument, updated);
	}
	
	
	/*
	 * 2. Agregar sinopsis a "The Hobbit: The Desolation of Smaug" :
	 * "The dwarves, along with Bilbo Baggins and Gandalf the Grey, continue their quest to reclaim Erebor, their homeland, from Smaug. Bilbo Baggins is in possession of a mysterious and magical ring."
	 */
	/*
	 * 3. Agregar una actor llamado "Samuel L. Jackson" a la película "Pulp Fiction"
	 */

	/*
	 * ********************* Busqueda por Texto / Text Search *********************
	 */

	/*
	 * 1. Encontrar las películas que en la sinopsis contengan la palabra "Bilbo"
	 */
	/*
	 * 2. Encontrar las películas que en la sinopsis contengan la palabra "Gandalf"
	 */
	/*
	 * 3. Encontrar las películas que en la sinopsis contengan la palabra "Bilbo" y
	 * no la palabra "Gandalf"
	 */
	/*
	 * 4. Encontrar las películas que en la sinopsis contengan la palabra "dwarves"
	 * ó "hobbit"
	 */
	/*
	 * 5. Encontrar las películas que en la sinopsis contengan la palabra "gold" y
	 * "dragon"
	 */

	/* ********************* Eliminar Documentos ********************* */

	/*
	 * 1. Eliminar la película "Pee Wee Herman's Big Adventure"
	 */
	/*
	 * 2. Eliminar la película "Avatar"
	 */

}
