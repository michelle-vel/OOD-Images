package model;

/**
 * Main Model. This interface represents an image the Image Processor can store. Contains all
 * the operations our image processor can perform on a given image and stores
 * it in a hashmap.
 */
public interface IImage {
  /**
   * Performs a command (flip vertical) on an image with old name and saves it as new name.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void flipVertical(String oldName, String newName);

  /**
   * Performs a command (flip horizontal) on an image with old name and saves it as new name.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void flipHorizontal(String oldName, String newName);

  /**
   * Performs a command (brighten) on an image with old name and saves it as new name.
   *
   * @param oldName   the name of the old file
   * @param newName   the name of the mutated file
   * @param increment the amount to increase
   */
  void brighten(String oldName, String newName, int increment);

  /**
   * Performs a command (darken) on an image with old name and saves it as new name.
   *
   * @param oldName   the name of the old file
   * @param newName   the name of the mutated file
   * @param increment the amount to decrease
   */
  void darken(String oldName, String newName, int increment);

  /**
   * Sets each pixel in the image to the red component of each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void redGrayscale(String oldName, String newName);

  /**
   * Sets each pixel in the image to the green component of each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void greenGrayscale(String oldName, String newName);

  /**
   * Sets each pixel in the image to the blue component of each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void blueGrayscale(String oldName, String newName);

  /**
   * Converts an image so each pixel is the luma value.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void luma(String oldName, String newName);

  /**
   * Converts an image so each pixel is the maximum value of
   * the three components.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void value(String oldName, String newName);

  /**
   * Converts an image so each pixel is the average of the three components for each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  void intensity(String oldName, String newName);

  /**
   * In charge of saving an image and outputting it in PPM format to a
   * given filename that the user chooses.
   *
   * @param filepath the location to save the image
   * @param newName  name to save the file as
   */
  void save(String filepath, String newName);

  /**
   * In charge of loading an image and storing it in a hashmap with a new name.
   *
   * @param imageArr the image as a pixel of arrays
   * @param name     name to load the file as
   */
  void load(Pixel[][] imageArr, String name);

  /**
   * Blurs an image and stores it in hashmap.
   * @param oldName the old name of the image.
   * @param newName the new name of the image.
   */
  void blur(String oldName, String newName);

  /**
   * Sharpens an image and stores it in hashmap.
   * @param oldName the old name of the image.
   * @param newName the new name of the image.
   */
  void sharpen(String oldName, String newName);

  /**
   * Greyscales an image and stores it in hashmap.
   * @param oldName the old name of the image.
   * @param newName the new name of the image.
   */
  void greyscale(String oldName, String newName);

  /**
   * Sepia an image and stores it in hashmap.
   * @param oldName the old name of the image.
   * @param newName the new name of the image.
   */
  void sepia(String oldName, String newName);

  /**
   * Gets an image from the hashmap.
   * @param name the name of the image.
   */
  ImageOperations getter(String name);
}

