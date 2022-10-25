package model;

import java.awt.Color;
import java.util.Map;

/**
 * Interface that represents all the commands that our ImageProcessor can process.
 */
public interface ImageOperations {

  /**
   * Flips a Picture Vertically by mutating its 2D Array of Pixels.
   *
   * @return a new mutated image.
   */
  Image flipVertical();

  /**
   * Flips a Picture Horizontally by mutating its 2D Array of Pixels.
   *
   * @return a new mutated image.
   */
  Image flipHorizontal();

  /**
   * Increases the color values for the for all the SinglePixels in this picture by a given value.
   *
   * @param increment The value to brighten by
   */
  Image brighten(int increment);

  /**
   * Decreases the color values for the for all the SinglePixels in this picture by a given value.
   *
   * @param increment The value to darken by
   */
  Image darken(int increment);

  /**
   * Sets each pixel in the image to the red component of each pixel.
   *
   * @return a new mutated image.
   */
  Image redGrey();

  /**
   * Sets each pixel in the image to the green component of each pixel.
   *
   * @return a new mutated image.
   */
  Image greenGrey();

  /**
   * Sets each pixel in the image to the blue component of each pixel.
   *
   * @return a new mutated image.
   */
  Image blueGrey();

  /**
   * Converts an image so each pixel is the average of the three components for each pixel.
   *
   * @return a new mutated image.
   */
  Image intensity();

  /**
   * Converts an image so each pixel is the luma value.
   *
   * @return a new mutated image.
   */
  Image luma();

  /**
   * Converts an image so each pixel is the maximum value of
   * the three components.
   *
   * @return a new mutated image.
   */
  Image value();

  /**
   * In charge of saving an image and outputting it in PPM format to a
   * given filename that the user chooses.
   *
   * @param newFilename name of the file to be saved
   */
  void save(String newFilename);


  /**
   * Creates a map for the red pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  Map<Integer, Integer> redPixels();

  /**
   * Creates a map for the green pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  Map<Integer, Integer> greenPixels();

  /**
   * Creates a map for the blue pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  Map<Integer, Integer> bluePixels();

  /**
   * Creates a map for the intensity of the pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  Map<Integer, Integer> intensityPixels();


  /**
   * Gets the largest number in the map.
   * @param map the given map
   * @return the largest number
   */
  int largestNumber(Map<Integer, Integer> map);


  /**
   * Return the current color at this location.
   * @param row the row number
   * @param col the col number
   * @return Color
   */
  Color currentColor(int row, int col);


  /**
   * This method will apply either the blur filter or sharpen filter depending on which
   * input is put in.
   *
   * @param type String that will be either blur or sharpen.
   * @return New image that has a filter applied to it
   */
  Image blurAndSharpen(String type);

  /**
   * This method will apply either the greyscale filter or sepia filter depending on which
   * input is put in.
   *
   * @param type String that will be either greyscale or sepia.
   * @return New image that has a filter applied to it
   */
  Image greyscaleAndSepia(String type);

  /**
   * This copies the array so you don't mutate the original.
   *
   * @return a new array
   */
  Pixel[][] returnCopy();

  /**
   * Return size of the row of the array.
   * @return int row size
   */
  int returnRowSize();

  /**
   * Return size of the col of the array.
   * @return int col size
   */
  int returnColSize();
}
