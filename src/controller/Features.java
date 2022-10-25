package controller;

/**
 * Represents all the possible features to be performed on an image.
 */
public interface Features {
  /**
   * Load an image.
   * @param filepath the given filepath
   */
  void load(String filepath);

  /**
   * Save an image.
   * @param filepath the given filepath
   */
  void save(String filepath);

  /**
   * Blue greyscale an image.
   */
  void blue();

  /**
   * Red greyscale an image.
   */
  void red();

  /**
   * Green greyscale an image.
   */
  void green();

  /**
   * Brighten an image.
   * @param increment the given increment
   */
  void brighten(int increment);

  /**
   * Darken an image.
   * @param increment the given increment
   */
  void darken(int increment);

  /**
   * Horizontally flip an image.
   */
  void flipHorizontal();

  /**
   * Vertically flip an image.
   */
  void flipVertical();

  /**
   * Blur an image.
   */
  void blur();

  /**
   * Greyscale an image.
   */
  void greyscale();

  /**
   * Intensity an image.
   */
  void intensity();

  /**
   * Luma an image.
   */
  void luma();

  /**
   * Sepia an image.
   */
  void sepia();

  /**
   * Sharpen an image.
   */
  void sharpen();

  /**
   * Value an image.
   */
  void value();
}
