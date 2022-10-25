
import controller.Features;
import model.ImageOperations;
import view.GUIView;
import java.awt.Image;

/**
 * Mocks the GUIView interface. This is just so the view is not created each time
 * you call it.
 */

public class GUIMock implements GUIView {

  @Override
  public String file() {
    return null;
  }


  @Override
  public void addFeatures(Features features) {
    //does not need to do anything, since in the view this method assigns features to
    //buttons, and that behavior is not being tested here
  }

  @Override
  public Image makeImage(ImageOperations image) {
    return null;
  }

  @Override
  public void updateImage(ImageOperations im) {
    //does not need to do anything, since in the view this method
    // updates the image , and that behavior is not being tested here

  }

  @Override
  public String saveFile() {
    return null;
  }
}
