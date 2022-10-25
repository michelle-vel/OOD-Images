package controller;

import view.GUIView;

/**
 * This interface represents a controller for this ImageProcessor.
 */
public interface ImageController {

  /**
   * Runs the Image Processing application.
   */
  void drawImage();

  /**
   * Creates the GUI view.
   * @param v the given GUIView object.
   */
  void setView(GUIView v);
}
