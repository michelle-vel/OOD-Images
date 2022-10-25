import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import controller.Features;
import model.IImage;
import model.ImageOperations;
import view.GUIView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the different features in the Features interface.
 */
public class FeaturesTest {

  private Map<String, ImageOperations> storedImages;
  private StringBuilder log2;

  @Before
  public void init() {
    storedImages = new HashMap<>();
    log2 = new StringBuilder();

  }

  @Test
  public void testFeaturesMock() {
    IImage imageMock = new ImageMock(storedImages, log2);
    Features featuresMock = new FeaturesMock(log2, imageMock);
    GUIView view = new GUIMock();
    view.addFeatures(featuresMock);
    featuresMock.load("res/33grid.ppm");
    assertEquals(log2.toString(), " load called with image array, Image. " +
            "features load called with filepath res/33grid.ppm");

    featuresMock.blue();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called");

    featuresMock.red();
    assertEquals(log2.toString(), " load called with image array, Image. " +
            "features load called with filepath res/33grid.ppm blue grayscale called with " +
            "oldname Image, newname Image. features blue called red grayscale called with " +
            "oldname Image, newname Image. features red called");

    featuresMock.green();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called");

    featuresMock.brighten(50);
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50");

    featuresMock.darken(100);
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, newname " +
            "Image, increment 50. features brighten called with increment 50 darken called with " +
            "oldname Image, newname Image, increment 100. " +
            "features darken called with increment 100");


    featuresMock.flipHorizontal();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called");

    featuresMock.flipVertical();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called flip vertical called with oldname Image, " +
            "newname Image. features flip vertical called");

    featuresMock.blur();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken " +
            "called with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features " +
            "flip horizontal called flip vertical called with oldname Image, newname Image. " +
            "features flip vertical called blur called with oldname Image, newname Image. " +
            "features blur called");

    featuresMock.greyscale();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called flip vertical called with oldname Image, " +
            "newname Image. features flip vertical called blur called with oldname Image, " +
            "newname Image. features blur called greyscale called with oldname Image, " +
            "newname Image. features greyscale called");

    featuresMock.intensity();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called flip vertical called with oldname Image, " +
            "newname Image. features flip vertical called blur called with oldname Image, " +
            "newname Image. features blur called greyscale called with oldname Image, " +
            "newname Image. features greyscale called intensity called with oldname Image, " +
            "newname Image. features intensity called");

    featuresMock.luma();
    assertEquals(log2.toString(), " load called with image array, Image. features " +
            "load called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called flip vertical called with oldname Image, " +
            "newname Image. features flip vertical called blur called with oldname Image, " +
            "newname Image. features blur called greyscale called with oldname Image, " +
            "newname Image. features greyscale called intensity called with oldname Image, " +
            "newname Image. features intensity called luma called with oldname Image, " +
            "newname Image. features luma called");

    featuresMock.sepia();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called flip vertical called with oldname Image, " +
            "newname Image. features flip vertical called blur called with oldname Image, " +
            "newname Image. features blur called greyscale called with oldname Image, " +
            "newname Image. features greyscale called intensity called with oldname Image, " +
            "newname Image. features intensity called luma called with oldname Image, " +
            "newname Image. features luma called sepia called with oldname Image, " +
            "newname Image. features sepia called");

    featuresMock.sharpen();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image," +
            " newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called flip vertical called with oldname Image, " +
            "newname Image. features flip vertical called blur called with oldname Image, " +
            "newname Image. features blur called greyscale called with oldname Image, " +
            "newname Image. features greyscale called intensity called with oldname Image, " +
            "newname Image. features intensity called luma called with oldname Image, " +
            "newname Image. features luma called sepia called with oldname Image, " +
            "newname Image. features sepia called sharpen called with oldname Image, " +
            "newname Image. features sharpen called");

    featuresMock.value();
    assertEquals(log2.toString(), " load called with image array, Image. features load " +
            "called with filepath res/33grid.ppm blue grayscale called with oldname Image, " +
            "newname Image. features blue called red grayscale called with oldname Image, " +
            "newname Image. features red called green grayscale called with oldname Image, " +
            "newname Image. features green called brighten called with oldname Image, " +
            "newname Image, increment 50. features brighten called with increment 50 darken " +
            "called with oldname Image, newname Image, increment 100. features darken called " +
            "with increment 100 flip horizontal called with oldname Image, newname Image. " +
            "features flip horizontal called flip vertical called with oldname Image, " +
            "newname Image. features flip vertical called blur called with oldname Image, " +
            "newname Image. features blur called greyscale called with oldname Image, " +
            "newname Image. features greyscale called intensity called with oldname Image, " +
            "newname Image. features intensity called luma called with oldname Image, " +
            "newname Image. features luma called sepia called with oldname Image, " +
            "newname Image. features sepia called sharpen called with oldname Image, " +
            "newname Image. features sharpen called value called with oldname Image, " +
            "newname Image. features value called");

  }
}
