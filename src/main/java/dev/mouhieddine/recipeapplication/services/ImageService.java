package dev.mouhieddine.recipeapplication.services;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface ImageService {
  Mono<Void> saveImageFile(String recipeId, MultipartFile file);
}
