package at.refugeescode.Kapshimage.repository;

import at.refugeescode.Kapshimage.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface Repository extends MongoRepository<Photo, String> {
    List<Photo> findAll();
    Optional<Photo> findById(String s);
}
