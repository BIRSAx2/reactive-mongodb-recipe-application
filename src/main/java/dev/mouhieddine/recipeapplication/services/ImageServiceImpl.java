package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

  private final RecipeReactiveRepository recipeReactiveRepository;

  public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
    this.recipeReactiveRepository = recipeReactiveRepository;
  }

  public static Byte[] toObjects(byte[] bytes) {
    return IntStream.range(0, bytes.length)
            .mapToObj(i -> bytes[i])
            .toArray(Byte[]::new);
  }

  public static byte[] toPrimitives(Byte[] oBytes) {
    byte[] bytes = new byte[oBytes.length];
    for (int i = 0; i < oBytes.length; i++) {
      bytes[i] = oBytes[i];
    }
    return bytes;
  }

  @Override
  public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

    Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
            .map(recipe -> {
              try {
                Byte[] byteObjects = toObjects(file.getBytes());
                recipe.setImage(byteObjects);
              } catch (IOException e) {
                e.printStackTrace();
              }
              return recipe;
            });
    recipeReactiveRepository.save(recipeMono.block());
    return Mono.empty();
  }
}
