package at.refugeescode.Kapshimage;


import org.springframework.beans.factory.annotation.Autowired;
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
    //   private static String photoPathFromDataBase = "/home/mohammad/Programming/kapsch/Kapshimage/src/main/resources/imagesfromHtml";


    @Autowired
    private Repository imageRepository;
    private Photo image;

    @GetMapping
    String mainPage() {
        return "home";
    }

    @GetMapping("/delete")
    String deleting(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("flash.message", "delete Successfully");
        imageRepository.deleteAll();
        return "redirect:/";
    }


    @PostMapping("/addImage")
    String addPhoto(Photo image, @RequestParam("file") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
        try {
            byte[] bytes = imageFile.getBytes();
            File serverFile = new File(photoPath + File.separator + imageFile.getOriginalFilename());
            BufferedOutputStream bufferStream = new BufferedOutputStream(new FileOutputStream(serverFile));
            bufferStream.write(bytes);
            bufferStream.close();
            image.setImage(bytes);
            image.setName(imageFile.getOriginalFilename());
            this.image = image;
            imageRepository.save(this.image);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully Image uploaded");

        } catch (Exception e) {
            return "you fild to upload photo" + " => " + e.getMessage();
        }
        return "redirect:/";
    }

    @PostMapping("/choosePhoto")
    String choosePhoto(@RequestParam("id") String id, @RequestParam("c") Category c, RedirectAttributes redirectAttributes) {

        Optional<Photo> photo = imageRepository.findById(id);
        photo.get().setCategory(c);
        imageRepository.deleteById(id);
        imageRepository.save(photo.get());
        redirectAttributes.addFlashAttribute("flash.message", "change Category");


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
