package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.exceptions.NotFoundException;
import dev.mouhieddine.recipeapplication.services.ImageService;
import dev.mouhieddine.recipeapplication.services.ImageServiceImpl;
import dev.mouhieddine.recipeapplication.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Controller
public class ImageController {

  private final ImageService imageService;
  private final RecipeService recipeService;

  public ImageController(ImageService imageService, RecipeService recipeService) {
    this.imageService = imageService;
    this.recipeService = recipeService;
  }

  @GetMapping("recipe/{id}/image")
  public String showUploadForm(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(id).block());

    return "recipe/imageuploadform";
  }

  @PostMapping("recipe/{id}/image")
  public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

    imageService.saveImageFile(id, file).block();

    return "redirect:/recipe/" + id + "/show";
  }

  @GetMapping("recipe/{id}/recipeimage")
  public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
    RecipeCommand recipeCommand = recipeService.findCommandById(id).block();
    if (recipeCommand == null) throw new NotFoundException("Recipe not found");

    if (recipeCommand.getImage() != null) {
      byte[] byteArray = ImageServiceImpl.toPrimitives(recipeCommand.getImage());
//      byte[] byteArray = new byte[recipeCommand.getImage().length];
//      int i = 0;
//
//      for (Byte wrappedByte : recipeCommand.getImage()) {
//        byteArray[i++] = wrappedByte; //auto unboxing
//      }

      response.setContentType("image/jpeg");
      InputStream is = new ByteArrayInputStream(byteArray);
      IOUtils.copy(is, response.getOutputStream());
    }
  }
}
