package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.services.ImageService;
import dev.mouhieddine.recipeapplication.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Slf4j
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
    model.addAttribute("recipe", recipeService.findCommandById(id));

    return "recipe/imageuploadform";
  }

  @PostMapping("recipe/{id}/image")
  public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
    imageService.saveImageFile(id, file).subscribe();
    return "redirect:/recipe/" + id + "/show";
  }

//  @GetMapping("recipe/{id}/recipeimage")
//  public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
//    RecipeCommand recipeCommand = recipeService.findCommandById(id).block();
//    if (recipeCommand == null) throw new NotFoundException("Recipe not found");
//    if (recipeCommand.getImage() != null) {
//      byte[] byteArray = ImageServiceImpl.toPrimitives(recipeCommand.getImage());
//      response.setContentType("image/jpeg");
//      InputStream is = new ByteArrayInputStream(byteArray);
//      IOUtils.copy(is, response.getOutputStream());
//    }
//}
}
