package notes_personals;

import org.bson.Document;
import java.util.Date;
import java.util.List;

public class Notes {
    private String id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private boolean isArchived;
    private User user;
    private List<String> images;
    private List<String> links;
    private List<String> tags;
    private List<String> comments;

    public Notes(String id, String title, String content, Date createdAt, Date updatedAt, boolean isArchived,
                 User user, List<String> images, List<String> links, List<String> tags,
                 List<String> comments) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isArchived = isArchived;
        this.user = user;
        this.images = images;
        this.links = links;
        this.tags = tags;
        this.comments = comments;
    }
}