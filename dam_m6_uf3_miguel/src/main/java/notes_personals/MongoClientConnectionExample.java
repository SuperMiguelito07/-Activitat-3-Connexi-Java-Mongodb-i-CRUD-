package notes_personals;

import com.mongodb.ConnectionString;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;

public class MongoClientConnectionExample {
    public static void main(String[] args) {

        String uri = "mongodb+srv://miguel:123@activitat3.f2avt.mongodb.net/?retryWrites=true&w=majority&appName=Activitat3";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("m6_notes_personals");
            MongoCollection<Document> collection = database.getCollection("notes");

            FindIterable<Document> notes = collection.find();
            for (Document note : notes) {
                System.out.println(note.toJson());
            }

        } catch (Exception e) {
            System.err.println("Error al connectar o operar amb la base de dades: " + e.getMessage());
        }
    }
}