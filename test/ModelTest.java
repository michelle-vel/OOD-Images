import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import model.IImage;
import model.Image;
import model.ImageOperations;
import model.ImageModelClass;
import model.Pixel;
import view.ImageView;
import view.ImageViewRender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the ImageModelClass to ensure images are stored correctly.
 */
public class ModelTest {
  private Map<String, ImageOperations> storedImages;
  private IImage newMap;
  private ImageOperations newImage;
  private Pixel[][] sampleArr1;
  private Pixel p1;
  private Pixel p2;


  @Before
  public void init() {
    storedImages = new HashMap<>();
    Map<String, ImageOperations> storedImages2 = new HashMap<>();
    newMap = new ImageModelClass(storedImages);
    IImage contMap = new ImageModelClass(storedImages2);
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewRender();
    p1 = new Pixel(1, 2, 3);
    p2 = new Pixel(4, 5, 6);
    Pixel p3 = new Pixel(7, 8, 9);
    sampleArr1 = new Pixel[][]{{p1}, {p2}, {p3}};
    Pixel[][] sampleArr2 = new Pixel[][]{{p1}, {p2}, {p3}, {p3}, {p2}, {p1}};

    newImage = new Image(sampleArr1);
    ImageOperations newImage2 = new Image(sampleArr2);
  }

  @Test
  public void invalidInit() {
    try {
      ImageOperations newImg = new Image(null);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("cannot be null")) {
        fail("should have thrown no null exception");
      }
    }
  }


  @Test
  //tests that the function is being placed into the map and the operation is being performed
  public void testImageMaps() {
    newMap.load(sampleArr1, "load1");
    assertEquals(newImage, storedImages.get("load1"));

    ImageOperations flippedV = newImage.flipVertical();
    newMap.flipVertical("load1", "flipV1");
    assertEquals(flippedV, storedImages.get("flipV1"));

    ImageOperations image2 = newImage.flipHorizontal();
    newMap.flipHorizontal("load1", "flipH1");
    assertEquals(image2, storedImages.get("flipH1"));

    ImageOperations image3 = new Image(sampleArr1).brighten(50);
    newMap.brighten("flipH1", "brighten1", 50);
    assertEquals(image3, storedImages.get("brighten1"));

    ImageOperations image4 = new Image(sampleArr1).darken(45);
    newMap.darken("brighten1", "darken1", 45);
    assertEquals(image4, storedImages.get("darken1"));

    ImageOperations image5 = new Image(sampleArr1).greenGrey();
    newMap.greenGrayscale("darken1", "green1");
    assertEquals(image5, storedImages.get("green1"));

    ImageOperations image6 = new Image(sampleArr1).blueGrey();
    newMap.blueGrayscale("green1", "blue1");
    assertEquals(image6, storedImages.get("blue1"));

    ImageOperations image7 = new Image(sampleArr1).redGrey();
    newMap.redGrayscale("blue1", "red1");
    assertEquals(image7, storedImages.get("red1"));

    ImageOperations image8 = new Image(sampleArr1).value();
    newMap.value("green1", "value1");
    assertEquals(image8, storedImages.get("value1"));

    ImageOperations image9 = new Image(sampleArr1).luma();
    newMap.luma("value1", "luma");
    assertEquals(image9, storedImages.get("luma"));

    ImageOperations image10 = new Image(sampleArr1).intensity();
    newMap.intensity("luma", "intensity");
    assertEquals(image10, storedImages.get("intensity"));
  }

  @Test
  public void testPixelBrighten() {
    p1.brighten(50);
    assertEquals(p1.getRed(), 51);
    assertEquals(p1.getGreen(), 52);
    assertEquals(p1.getBlue(), 53);

    p1.brighten(1000);
    assertEquals(p1.getRed(), 255);
    assertEquals(p1.getGreen(), 255);
    assertEquals(p1.getBlue(), 255);
  }

  @Test
  public void testPixelDarken() {
    p2.darken(2);
    assertEquals(p2.getRed(), 2);
    assertEquals(p2.getGreen(), 3);
    assertEquals(p2.getBlue(), 4);

    p2.darken(1000);
    assertEquals(p2.getRed(), 0);
    assertEquals(p2.getGreen(), 0);
    assertEquals(p2.getBlue(), 0);
  }

  @Test
  public void testPixelRedGray() {
    p1.redGreyscale();
    assertEquals(p1.getRed(), 1);
    assertEquals(p1.getGreen(), 1);
    assertEquals(p1.getBlue(), 1);
  }

  @Test
  public void testPixelGreenGray() {
    p1.greenGreyscale();
    assertEquals(p1.getRed(), 2);
    assertEquals(p1.getGreen(), 2);
    assertEquals(p1.getBlue(), 2);
  }

  @Test
  public void testPixelBlueGray() {
    p1.blueGreyscale();
    assertEquals(p1.getRed(), 3);
    assertEquals(p1.getGreen(), 3);
    assertEquals(p1.getBlue(), 3);
  }

  @Test
  public void testPixelIntensity() {
    p1.intensity();
    assertEquals(p1.getRed(), 2);
    assertEquals(p1.getGreen(), 2);
    assertEquals(p1.getBlue(), 2);
  }

  @Test
  public void testPixelLuma() {
    p1.luma();
    assertEquals(p1.getRed(), 1);
    assertEquals(p1.getGreen(), 1);
    assertEquals(p1.getBlue(), 1);
  }

  @Test
  public void testPixelValue() {
    p1.value();
    assertEquals(p1.getRed(), 3);
    assertEquals(p1.getGreen(), 3);
    assertEquals(p1.getBlue(), 3);
  }
}


