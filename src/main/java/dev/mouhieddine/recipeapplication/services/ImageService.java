package dev.mouhieddine.recipeapplication.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface ImageService {
  void saveImageFile(String recipeId, MultipartFile file);
}
