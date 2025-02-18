package notes_personals.config;

import org.bson.Document;

public class Note {
    private String _id;
    private String title;
    private String created_at;

    public Note(String _id, String title, String created_at) {
        this._id = _id;
        this.title = title;
        this.created_at = created_at;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Note [\n  _id=" + _id + ",\n  title=" + title + ",\n  created_at=" + created_at + "\n]";
    }

    public Document toDocument() {
        return new Document("_id", this._id)
                .append("title", this.title)
                .append("created_at", this.created_at);
    }
}