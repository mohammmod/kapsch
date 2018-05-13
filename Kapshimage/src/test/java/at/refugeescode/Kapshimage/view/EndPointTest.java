package at.refugeescode.Kapshimage.view;


import at.refugeescode.Kapshimage.model.Category;
import at.refugeescode.Kapshimage.model.Photo;
import at.refugeescode.Kapshimage.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EndPointTest {

    private Repository repository ;
    private Photo photo;



    @BeforeEach
    void initialize() {
        //Repository repository
       // repository= Mockito.mock(Repository.class);
        photo= Mockito.mock(Photo.class);

        Repository repository = this.repository;

    }

    @Test
    void deleting() {
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }
    @Test
    void choosePhoto() {
        byte[] fakeBytes = new byte[20];
        new Random().nextBytes(fakeBytes);
        photo.setCategory(Category.C);
        photo.setName("so");
        photo.setImage(fakeBytes);
        repository.save(photo);
        assertEquals(1,repository.findAll().size());
    }

    @Test
    void addImage() {
    }
}