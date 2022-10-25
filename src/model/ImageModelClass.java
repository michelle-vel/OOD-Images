package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class stores each image with a new name into a hashmap.
 */
public class ImageModelClass implements IImage {

  private final Map<String, ImageOperations> storedImages;
  //INVARIANT: stored imaged will have values of names and images

  /**
   * Constructor that creates a hashmap to store all mutated images.
   */
  public ImageModelClass() {
    this.storedImages = new HashMap<>();
  }

  /**
   * Second constructor that stores mutated images corresponding to a given image name.
   *
   * @param storedImages the loaded or mutated images in a list
   */
  public ImageModelClass(Map<String, ImageOperations> storedImages) {
    this.storedImages = storedImages;
  }

  /**
   * Performs a command (flip vertical) on an image with old name and saves it as new name.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void flipVertical(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).flipVertical());
  }

  /**
   * Performs a command (flip horizontal) on an image with old name and saves it as new name.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void flipHorizontal(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).flipHorizontal());
  }

  /**
   * Performs a command (brighten) on an image with old name and saves it as new name.
   *
   * @param oldName   the name of the old file
   * @param newName   the name of the mutated file
   * @param increment the amount to increase
   */
  @Override
  public void brighten(String oldName, String newName, int increment) {
    this.storedImages.put(newName, this.storedImages.get(oldName).brighten(increment));

  }

  /**
   * Performs a command (darken) on an image with old name and saves it as new name.
   *
   * @param oldName   the name of the old file
   * @param newName   the name of the mutated file
   * @param increment the amount to decrease
   */
  @Override
  public void darken(String oldName, String newName, int increment) {
    this.storedImages.put(newName, this.storedImages.get(oldName).darken(increment));

  }

  /**
   * Sets each pixel in the image to the red component of each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void redGrayscale(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).redGrey());

  }

  /**
   * Sets each pixel in the image to the green component of each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void greenGrayscale(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).greenGrey());
  }

  /**
   * Sets each pixel in the image to the blue component of each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void blueGrayscale(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).blueGrey());

  }

  /**
   * Converts an image so each pixel is the luma value.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void luma(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).luma());

  }

  /**
   * Converts an image so each pixel is the maximum value of
   * the three components.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void value(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).value());

  }

  /**
   * Converts an image so each pixel is the average of the three components for each pixel.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void intensity(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).intensity());

  }

  /**
   * Converts an image with the blur filter.
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void blur(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).blurAndSharpen("blur"));

  }

  /**
   * Converts an image with the sharpen filter.
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void sharpen(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).blurAndSharpen("sharpen"));

  }

  /**
   * Converts an image with the greyscale filter.
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void greyscale(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).greyscaleAndSepia("greyscale"));
  }

  /**
   * Converts an image with the sepia filter.
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  @Override
  public void sepia(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).greyscaleAndSepia("sepia"));
  }

  /**
   * In charge of saving an image and outputting it in PPM format to a given filename
   * that the user chooses.
   *
   * @param filepath the location to save the image
   * @param newName  name to save the file as
   */
  @Override
  public void save(String filepath, String newName) {
    ImageOperations imageToSave = this.storedImages.get(newName);
    imageToSave.save(filepath);
  }

  /**
   * In charge of loading a pixelArray as a new image and storing it in a hashmap with a new name.
   *
   * @param imageArr the given image as a pixel of arrays
   * @param name     name to load the file as
   */
  @Override
  public void load(Pixel[][] imageArr, String name) {
    this.storedImages.put(name, new Image(imageArr));
  }

  /**
   * Gets an image.
   * @param name the name of the image.
   * @return the image
   */
  public ImageOperations getter(String name) {
    return this.storedImages.get(name);
  }
}