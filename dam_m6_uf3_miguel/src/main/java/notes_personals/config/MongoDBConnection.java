package notes_personals.config;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Scanner;

public class MongoDBConnection {
    private static final String URI = "mongodb+srv://miguel:123@activitat3.f2avt.mongodb.net/?retryWrites=true&w=majority&appName=Activitat3";
    private static final String DATABASE_NAME = "m6_notes_personals";
    private static final String COLLECTION_NAME = "notes";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBConnection() {
        try {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DATABASE_NAME);
            collection = database.getCollection(COLLECTION_NAME);
            System.out.println("Conexión exitosa a la base de datos: " + database.getName());
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void inserirNotes(Note note) {
        try {
            Document doc = note.toDocument();
            collection.insertOne(doc);
            System.out.println("Nota insertada correctamente: " + note.getTitle());
        } catch (Exception e) {
            System.err.println("Error al insertar la nota: " + e.getMessage());
        }
    }

    public void imprimirNotes() {
        FindIterable<Document> notes = collection.find();
        for (Document note : notes) {
            System.out.println("Títol: " + note.getString("title"));
            System.out.println("Data de creació: " + note.getString("created_at"));
            System.out.println("-------------------------");
        }
    }

    public void ordenarprDates(String startDate, String endDate) {
        FindIterable<Document> notes = collection.find(Filters.and(
                Filters.gte("created_at", startDate),
                Filters.lte("created_at", endDate)
        ));

        for (Document note : notes) {
            System.out.println("Títol: " + note.getString("title"));
            System.out.println("Data de creació: " + note.getString("created_at"));
            System.out.println("-------------------------");
        }
    }

    public void tancarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada");
        }
    }

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menú");
            System.out.println("1. Insertar una nota");
            System.out.println("2. Mostrar todas las notas");
            System.out.println("3. Mostrar notas por rango de fechas");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    System.out.print("Introduce el ID de la nota: ");
                    String id = scanner.nextLine();
                    System.out.print("Introduce el título de la nota: ");
                    String title = scanner.nextLine();
                    System.out.print("Introduce la fecha de creación (YYYY-MM-DD): ");
                    String createdAt = scanner.nextLine();

                    Note newNote = new Note(id, title, createdAt);
                    connection.inserirNotes(newNote);
                    break;

                case 2:
                    System.out.println("Todas las notas");
                    connection.imprimirNotes();
                    break;

                case 3:
                    System.out.print("Introduce la fecha inicial (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Introduce la fecha final (YYYY-MM-DD): ");
                    String endDate = scanner.nextLine();

                    System.out.println("Notas en el rango de fechas");
                    connection.ordenarprDates(startDate, endDate);
                    break;

                case 4:
                    exit = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        connection.tancarConexion();
        scanner.close();
    }
}