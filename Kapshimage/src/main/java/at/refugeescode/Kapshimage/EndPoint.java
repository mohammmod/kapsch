package at.refugeescode.Kapshimage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class EndPoint {

    private static String photoPath = "/home/mohammad/Programming/kapsch/Kapshimage/src/main/resources/static/images";
    private static String photoPathFromDataBase = "/home/mohammad/Programming/kapsch/Kapshimage/src/main/resources/imagesFromMongo";
    private Photo image;
    private Repository imageRepository;

    public EndPoint(Repository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping
    String mainPage() {
        return "home";
    }

    @GetMapping("/delete")
    String mainRPs() {
        imageRepository.deleteAll();
        return "redirect:/";
    }


    @PostMapping("/addImage")
    String addImage(Photo image, @RequestParam("file") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
        if (!imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                File serverFile = new File(photoPath + File.separator + imageFile.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                stream.
                image.setImage(bytes);
                image.setName(imageFile.getOriginalFilename());
                this.image = image;
                imageRepository.save(this.image);
                redirectAttributes.addFlashAttribute("flash.message", "Successfully Image uploaded");

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("flash.message", "Failed Image to upload");
                return "@@@@You failed to upload " + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + " because the file was empty.";
        }
        return "redirect:/";
    }
    @PostMapping("/choose")
    String choose(@RequestParam("id") String id, @RequestParam("c") Category c) {

        Optional<Photo> byId = imageRepository.findById(id);
        byId.get().setCategory(c);
        imageRepository.deleteById(id);
        imageRepository.save(byId.get());

        return "redirect:/";
    }

    @ModelAttribute("image")
    Photo addImage() {
        return new Photo();
    }

    @ModelAttribute("images")
    List<Photo> getParticipants() {
        return imageRepository.findAll();
    }



}
