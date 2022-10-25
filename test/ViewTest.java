import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageView;
import view.ImageViewRender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the view to ensure it is render messages correctly.
 */
public class ViewTest {
  private ImageView view1;
  private Appendable output;

  @Before
  public void init() {
    output = new StringBuilder();
    view1 = new ImageViewRender(output);
  }

  @Test
  public void testInvalidInit() {
    try {
      ImageView newView = new ImageViewRender(null);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("cannot be null")) {
        fail("should have thrown no null exception");
      }

    }
  }

  @Test
  public void testQuitMessage() {
    try {
      view1.renderMessage("program quit");
      assertEquals("program quit", output.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testIntroMessage() {
    try {
      view1.introMessage();
      assertEquals("possible commands: \n" +
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
              "quit to quit program\n", output.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
}