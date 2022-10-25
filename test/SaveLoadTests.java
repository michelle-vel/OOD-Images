import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.ImageUtil;
import model.Image;
import model.ImageOperations;
import model.Pixel;
import view.ImageView;
import view.ImageViewRender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * This class tests our save and load methods.
 */
public class SaveLoadTests {

  private ImageMock mockModel;
  private ImageView mockView;
  private Readable input;
  private Map<String, ImageOperations> storedImages;

  @Before
  public void init() {
    Pixel[][] gridPixel = ImageUtil.readPPM("res/grid.ppm");
    Image gridImage = new Image(gridPixel);
    mockView = new ImageViewRender();
  }

  //PPM Load Save
  @Test
  public void loadPPMTest() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new
            ByteArrayInputStream("load res/grid.ppm grid quit".getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertEquals(" load called with image array, grid.", modelLog.toString());

    // In model: get loaded image and compare to original image
  }

  @Test
  public void SavePPM() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new ByteArrayInputStream(("" +
            "load res/grid.ppm grid\n" +
            "save res/gridB.ppm grid quit").getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    // save loaded image as gridB
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertTrue(modelLog.toString().contains("save called with filepath res/gridB.ppm, " +
            "newname grid."));
  }


  //PNG Load Save
  @Test
  public void loadPNGTest() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new
            ByteArrayInputStream("load res/33grid.png 33grid quit".getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertEquals(" load called with image array, 33grid.", modelLog.toString());

    // In model: get loaded image and compare to original image
  }

  @Test
  public void SavePNG() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new ByteArrayInputStream(("" +
            "load res/33grid.png 33grid\n" +
            "save res/33gridB.png 33grid quit").getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    // save loaded image as 33gridB
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertTrue(modelLog.toString().contains("save called with filepath res/33gridB.png, " +
            "newname 33grid."));
  }

  // JPG Load Save
  @Test
  public void loadJPGTest() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new
            ByteArrayInputStream("load res/33grid.jpg 33grid quit".getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertEquals(" load called with image array, 33grid.", modelLog.toString());

    // In model: get loaded image and compare to original image
  }

  @Test
  public void SaveJPG() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new ByteArrayInputStream(("" +
            "load res/33grid.jpg 33grid\n" +
            "save res/33gridB.jpg 33grid quit").getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    // save loaded image as 33gridB
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertTrue(modelLog.toString().contains("save called with filepath res/33gridB.jpg, " +
            "newname 33grid."));
  }

  //BMP Load Save
  @Test
  public void loadBMPTest() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new
            ByteArrayInputStream("load res/33grid.bmp 33grid quit".getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertEquals(" load called with image array, 33grid.", modelLog.toString());

    // In model: get loaded image and compare to original image
  }

  @Test
  public void SaveBMP() {
    StringBuilder modelLog = new StringBuilder();
    mockModel = new ImageMock(storedImages, modelLog);
    input = new InputStreamReader(new ByteArrayInputStream(("" +
            "load res/33grid.bmp 33grid\n" +
            "save res/33gridB.bmp 33grid quit").getBytes()));
    ImageController controller = new ImageControllerImpl(
            mockModel, mockView, input);
    // save loaded image as 33gridB
    controller.drawImage();

    // Check that the expected methods in the model are called and run from this controller
    assertTrue(modelLog.toString().contains("save called with filepath res/33gridB.bmp, " +
            "newname 33grid."));
  }
}
