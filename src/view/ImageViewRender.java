package view;

import java.io.IOException;

/**
 * This class represents a view of our program Contains method to render
 * a message to a given destination.
 */

public class ImageViewRender implements ImageView {

  Appendable output;
  //INVARIANT: the output cannot be null

  /**
   * Initializes the text model.
   *
   * @throws IllegalArgumentException If the appendable object is null.
   */
  public ImageViewRender() {
    this.output = System.out;
  }

  /**
   * Initializes the text model and outputs it to the controller.
   * @param output  the message
   * @throws IllegalArgumentException If the appendable object is null.
   */
  public ImageViewRender(Appendable output) throws IllegalArgumentException {
    if (output == null) {
      throw new IllegalArgumentException("cannot be null");
    }
    this.output = output;
  }

  /**
   * This method is shown at the beginning of a program to help
   * users identify the commands available.
   *
   * @throws IOException if you can't append to the output
   */
  public void introMessage() throws IOException {
    this.output.append("possible commands: \n" +
            "load image-path image-name \n" +
            "red-component image-name dest-image-name \n" +
            "blue-component image-name dest-image-name \n" +
            "green-component image-name dest-image-name \n" +
            "value image-name dest-image-name \n" +
            "luma image-name dest-image-name \n" +
            "intensity image-name dest-image-name \n" +
            "horizontal-flip image-name dest-image-name \n" +
            "vertical-flip image-name dest-image-name \n" +
            "brighten increment image-name dest-image-name \n" +
            "darken increment image-name dest-image-name \n" +
            "sepia image-name dest-image-name \n" +
            "greyscale image-name dest-image-name \n" +
            "sharpen image-name dest-image-name \n" +
            "blur image-name dest-image-name \n" +
            "save image-path image-name \n" +
            "quit to quit program" + System.lineSeparator());
  }

  /**
   * This method will write the given message to the output.
   * @param message given message
   * @throws IOException if you can't append to the output
   */
  public void renderMessage(String message) throws IOException {
    this.output.append(message);
  }
}
