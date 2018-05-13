package at.refugeescode.Kapshimage.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Document
public class Photo {
    @Id
    private String id;


    private byte [] image;
    private String name;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }
}
