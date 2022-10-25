package view;


import java.awt.Image;

import controller.Features;
import model.ImageOperations;

/**
 * Creates a GUIView.
 */
public interface GUIView {

  /**
   * Saves the file name.
   * @return string of the file name.
   */
  String file();

  /**
   * Adds the features to manipulate the image to the GUI.
   * @param features the given features interface
   */
  void addFeatures(Features features);

  /**
   * Converts to an image.
   * @param image the given image
   * @return a new BufferedImage
   */
  Image makeImage(ImageOperations image);

  /**
   * Updates the image after a change has been made.
   * @param im the given image
   */
  void updateImage(ImageOperations im);

  /**
   * Saves the file.
   * @return string of the file name.
   */
  String saveFile();



}
