package view;

import java.io.IOException;

/**
 * This interface represents the view for an image processor.
 * Can display messages to an Appendable to track the changes made.
 */
public interface ImageView {
  /**
   * Render a message to the given destination.
   *
   * @throws IOException transmission to the given destination fails
   */


  void renderMessage(String message) throws IOException;

  void introMessage() throws IOException;

}
