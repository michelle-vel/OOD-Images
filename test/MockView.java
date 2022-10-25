import java.io.IOException;

import view.ImageView;

/**
 * MockView class helps to test controller.
 */
public class MockView implements ImageView {

  @Override
  public void renderMessage(String message) throws IOException {
    throw new IOException();
  }

  @Override
  public void introMessage() throws IOException {
    throw new IOException();
  }

}
