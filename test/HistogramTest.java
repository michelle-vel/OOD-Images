import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import model.Image;
import model.ImageOperations;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the histogram.
 */
public class HistogramTest {

  private ImageOperations image;

  @Before
  public void init() {
    Pixel[][] pixel = new Pixel[3][3];
    image = new Image(pixel);

    // image = image of 3 by 3 pixel array
    pixel[0][0] = new Pixel(255, 0, 0);
    pixel[0][1] = new Pixel(255, 0, 0);
    pixel[0][2] = new Pixel(255, 0, 0);
    pixel[1][0] = new Pixel(0, 255, 0);
    pixel[1][1] = new Pixel(0, 255, 0);
    pixel[1][2] = new Pixel(0, 255, 0);
    pixel[2][0] = new Pixel(0, 0, 255);
    pixel[2][1] = new Pixel(0, 0, 255);
    pixel[2][2] = new Pixel(0, 0, 255);
  }

  @Test
  public void testHisto() {

    //these methods are called within paint component
    //these methods test that histogram gets the corrects values
    int numRed = image.redPixels().get(255);
    assertEquals(3, numRed);

    int numGreen = image.greenPixels().get(255);
    assertEquals(3, numGreen);

    int numBlue = image.bluePixels().get(255);
    assertEquals(3, numBlue);

    int numIn = image.intensityPixels().get(85);
    assertEquals(9, numIn);

    assertEquals(image.largestNumber(image.redPixels()), 3);
    assertEquals(image.largestNumber(image.greenPixels()), 3);
    assertEquals(image.largestNumber(image.bluePixels()), 3);

    assertEquals(image.currentColor(0, 0), Color.RED);
    assertEquals(image.currentColor(1, 0), Color.GREEN);
    assertEquals(image.currentColor(2, 0), Color.BLUE);

  }
}
