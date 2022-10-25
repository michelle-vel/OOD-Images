import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.ImageUtil;
import model.IImage;
import model.Image;
import model.ImageOperations;
import model.ImageModelClass;
import model.Pixel;
import view.ImageView;
import view.ImageViewRender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class tests if the controller executes user commands correctly.
 */
public class ControllerTest {
  private Map<String, ImageOperations> storedImages;
  private Map<String, ImageOperations> storedImages2;
  private IImage contMap;
  private StringBuilder log;
  private ImageView view;



  @Before
  public void init() {
    storedImages = new HashMap<>();
    storedImages2 = new HashMap<>();
    IImage newMap = new ImageModelClass(storedImages);
    contMap = new ImageModelClass(storedImages2);
    log = new StringBuilder();
    view = new ImageViewRender(log);
    Pixel p1 = new Pixel(1, 2, 3);
    Pixel p2 = new Pixel(4, 5, 6);
    Pixel p3 = new Pixel(7, 8, 9);
    Pixel[][] sampleArr1 = new Pixel[][]{{p1}, {p2}, {p3}};
    Pixel[][] sampleArr2 = new Pixel[][]{{p1}, {p2}, {p3}, {p3}, {p2}, {p1}};

    ImageOperations newImage = new Image(sampleArr1);
    ImageOperations newImage2 = new Image(sampleArr2);
  }

  @Test
  public void testInvalidInit() {
    try {
      Reader in = new StringReader("");
      ImageController cont1 = new ImageControllerImpl(null, view, in);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given view and readable cannot be null!")) {
        fail("should have thrown no null exception");
      }
    }

    try {
      Reader in = new StringReader("");
      ImageController cont2 = new ImageControllerImpl(contMap, null, in);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given view and readable cannot be null!")) {
        fail("should have thrown no null exception");
      }
    }

    try {
      ImageController cont3 = new ImageControllerImpl(contMap, view, null);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given view and readable cannot be null!")) {
        fail("should have thrown no null exception");
      }
    }

    try {
      ImageController cont4 = new ImageControllerImpl(null, null, null);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given view and readable cannot be null!")) {
        fail("should have thrown no null exception");
      }
    }
  }

  @Test
  public void testEmpty() {
    Reader in = new StringReader("");
    ImageController cont1 = new ImageControllerImpl(contMap, view, in);
    try {
      cont1.drawImage();
      fail("should have thrown exception");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("cannot read input or transmit output")) {
        fail("should have thrown illegal argument exception");
      }
    }
  }

  @Test
  public void testInvalidNoQuit() {
    Reader in = new StringReader("hello");
    ImageController cont1 = new ImageControllerImpl(contMap, view, in);
    try {
      cont1.drawImage();
      fail("should have thrown exception");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Scanner is out of inputs!")) {
        fail("should have thrown illegal state exception");
      }
    }
  }


  @Test
  public void testController() {
    Reader in = new StringReader(
            "load res/RGBGrid.ppm rgb quit\n ");
    ImageController cont1 = new ImageControllerImpl(contMap, view, in);
    cont1.drawImage();
    storedImages2.get("rgb");

    Reader inMock = new StringReader(
            "load res/RGBGrid.ppm rgb quit\n ");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController cont1Mock = new ImageControllerImpl(imageMock, view, inMock);
    cont1Mock.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb"));


    Reader in2 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed quit\n");
    ImageController cont2 = new ImageControllerImpl(contMap, view, in2);
    cont2.drawImage();
    storedImages2.get("rgbRed");

    Reader inMock2 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed quit\n ");

    IImage imageMock2 = new ImageMock(storedImages, log);
    ImageController contMock2 = new ImageControllerImpl(imageMock2, view, inMock2);
    contMock2.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed."));

    Reader in3 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue quit\n");
    ImageController cont3 = new ImageControllerImpl(contMap, view, in3);
    cont3.drawImage();
    storedImages2.get("rgbBlue");
    Reader inMock3 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue quit\n");

    IImage imageMock3 = new ImageMock(storedImages, log);
    ImageController contMock3 = new ImageControllerImpl(imageMock3, view, inMock3);
    contMock3.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue"));


    Reader in4 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen quit\n");
    ImageController cont4 = new ImageControllerImpl(contMap, view, in4);
    cont4.drawImage();
    storedImages2.get("rgbGreen");

    Reader inMock4 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen quit\n");

    IImage imageMock4 = new ImageMock(storedImages, log);
    ImageController contMock4 = new ImageControllerImpl(imageMock4, view, inMock4);
    contMock4.drawImage();

    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen"));

    Reader in5 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue quit\n");
    ImageController cont5 = new ImageControllerImpl(contMap, view, in5);
    cont5.drawImage();
    storedImages2.get("rgbValue");

    Reader inMock5 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue quit\n");

    IImage imageMock5 = new ImageMock(storedImages, log);
    ImageController contMock5 = new ImageControllerImpl(imageMock5, view, inMock5);
    contMock5.drawImage();

    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue."));


    Reader in6 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma quit\n");
    ImageController cont6 = new ImageControllerImpl(contMap, view, in6);
    cont6.drawImage();
    storedImages2.get("rgbLuma");

    Reader inMock6 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma quit\n");

    IImage imageMock6 = new ImageMock(storedImages, log);
    ImageController contMock6 = new ImageControllerImpl(imageMock6, view, inMock6);
    contMock6.drawImage();

    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma."));


    Reader in7 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity quit\n");
    ImageController cont7 = new ImageControllerImpl(contMap, view, in7);
    cont7.drawImage();
    storedImages2.get("rgbIntensity");

    Reader inMock7 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity quit\n");

    IImage imageMock7 = new ImageMock(storedImages, log);
    ImageController contMock7 = new ImageControllerImpl(imageMock7, view, inMock7);
    contMock7.drawImage();

    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity."));


    Reader in8 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz quit\n");
    ImageController cont8 = new ImageControllerImpl(contMap, view, in8);
    cont8.drawImage();
    storedImages2.get("rgbHoriz");

    Reader inMock8 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz quit\n");

    IImage imageMock8 = new ImageMock(storedImages, log);
    ImageController contMock8 = new ImageControllerImpl(imageMock8, view, inMock8);
    contMock8.drawImage();

    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz."));


    Reader in9 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbHoriz rgbVert quit\n");
    ImageController cont9 = new ImageControllerImpl(contMap, view, in9);
    cont9.drawImage();
    storedImages2.get("rgbVert");

    Reader inMock9 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbHoriz rgbVert quit\n");

    IImage imageMock9 = new ImageMock(storedImages, log);
    ImageController contMock9 = new ImageControllerImpl(imageMock9, view, inMock9);
    contMock9.drawImage();

    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbHoriz, newname rgbVert."));


    Reader in10 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbIntensity rgbVert " +
                    "brighten 50 rgbVert rgbBrighten quit\n");
    ImageController cont10 = new ImageControllerImpl(contMap, view, in10);
    cont10.drawImage();
    storedImages2.get("rgbBrighten");

    Reader inMock10 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbHoriz rgbVert " +
                    "brighten 50 rgbVert rgbBrighten quit\n");

    IImage imageMock10 = new ImageMock(storedImages, log);
    ImageController contMock10 = new ImageControllerImpl(imageMock10, view, inMock10);
    contMock10.drawImage();

    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbHoriz, newname rgbVert. " +
            "brighten called with oldname rgbVert, newname rgbBrighten, increment 50."));


    Reader in11 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbIntensity rgbVert " +
                    "brighten 50 rgbVert rgbBrighten " +
                    "darken 35 rgbBrighten rgbDarken quit\n");
    ImageController cont11 = new ImageControllerImpl(contMap, view, in11);
    cont11.drawImage();
    storedImages2.get("rgbDarken");

    Reader inMock11 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbIntensity rgbVert " +
                    "brighten 50 rgbVert rgbBrighten " +
                    "darken 35 rgbBrighten rgbDarken quit\n");

    IImage imageMock11 = new ImageMock(storedImages, log);
    ImageController contMock11 = new ImageControllerImpl(imageMock11, view, inMock11);
    contMock11.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbIntensity, newname rgbVert. " +
            "brighten called with oldname rgbVert, newname rgbBrighten, increment 50. " +
            "darken called with oldname rgbBrighten, newname rgbDarken, increment 35"));


    Reader in12 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbIntensity rgbVert " +
                    "brighten 50 rgbVert rgbBrighten " +
                    "darken 35 rgbBrighten rgbDarken " +
                    "blur rgbDarken rgbBlur quit\n");
    ImageController cont12 = new ImageControllerImpl(contMap, view, in12);
    cont12.drawImage();
    storedImages2.get("rgbBlur");

    Reader inMock12 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
                    "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
                    "value rgbGreen rgbValue " +
                    "luma rgbValue rgbLuma " +
                    "intensity rgbLuma rgbIntensity " +
                    "horizontal-flip rgbIntensity rgbHoriz " +
                    "vertical-flip rgbIntensity rgbVert " +
                    "brighten 50 rgbVert rgbBrighten " +
                    "darken 35 rgbBrighten rgbDarken " +
                    "blur rgbDarken rgbBlur quit\n");

    IImage imageMock12 = new ImageMock(storedImages, log);
    ImageController contMock12 = new ImageControllerImpl(imageMock12, view, inMock12);
    contMock12.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbIntensity, newname rgbVert. " +
            "brighten called with oldname rgbVert, newname rgbBrighten, increment 50. " +
            "darken called with oldname rgbBrighten, newname rgbDarken, increment 35. " +
            "blur called with oldname rgbDarken, newname rgbBlur"));

    Reader in13 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen quit\n");
    ImageController cont13 = new ImageControllerImpl(contMap, view, in13);
    cont13.drawImage();
    storedImages2.get("rgbSharpen");

    Reader inMock13 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen quit\n");

    IImage imageMock13 = new ImageMock(storedImages, log);
    ImageController contMock13 = new ImageControllerImpl(imageMock13, view, inMock13);
    contMock13.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbIntensity, newname rgbVert. " +
            "brighten called with oldname rgbVert, newname rgbBrighten, increment 50. " +
            "darken called with oldname rgbBrighten, newname rgbDarken, increment 35. " +
            "blur called with oldname rgbDarken, newname rgbBlur. " +
            "sharpen called with oldname rgbBlur, newname rgbSharpen"));

    Reader in14 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen " +
            "greyscale rgbSharpen rgbGreyscale quit\n");
    ImageController cont14 = new ImageControllerImpl(contMap, view, in14);
    cont14.drawImage();
    storedImages2.get("rgbGreyscale");

    Reader inMock14 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen " +
            "greyscale rgbSharpen rgbGreyscale quit\n");

    IImage imageMock14 = new ImageMock(storedImages, log);
    ImageController contMock14 = new ImageControllerImpl(imageMock14, view, inMock14);
    contMock14.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbIntensity, newname rgbVert. " +
            "brighten called with oldname rgbVert, newname rgbBrighten, increment 50. " +
            "darken called with oldname rgbBrighten, newname rgbDarken, increment 35. " +
            "blur called with oldname rgbDarken, newname rgbBlur. " +
            "sharpen called with oldname rgbBlur, newname rgbSharpen. " +
            "greyscale called with oldname rgbSharpen, newname rgbGreyscale"));

    Reader in15 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen " +
            "greyscale rgbSharpen rgbGreyscale " +
            "sepia rgbGreyscale rgbSepia quit\n");
    ImageController cont15 = new ImageControllerImpl(contMap, view, in15);
    cont15.drawImage();
    storedImages2.get("rgbSepia");

    Reader inMock15 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen " +
            "greyscale rgbSharpen rgbGreyscale " +
            "sepia rgbGreyscale rgbSepia quit\n");

    IImage imageMock15 = new ImageMock(storedImages, log);
    ImageController contMock15 = new ImageControllerImpl(imageMock15, view, inMock15);
    contMock15.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbIntensity, newname rgbVert. " +
            "brighten called with oldname rgbVert, newname rgbBrighten, increment 50. " +
            "darken called with oldname rgbBrighten, newname rgbDarken, increment 35. " +
            "blur called with oldname rgbDarken, newname rgbBlur. " +
            "sharpen called with oldname rgbBlur, newname rgbSharpen. " +
            "greyscale called with oldname rgbSharpen, newname rgbGreyscale. " +
            "sepia called with oldname rgbGreyscale, newname rgbSepia"));

    Reader in16 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen " +
            "greyscale rgbSharpen rgbGreyscale " +
            "sepia rgbGreyscale rgbSepia " +
            "save res/rgbSepia.ppm rgbSepia quit\n");
    ImageController cont16 = new ImageControllerImpl(contMap, view, in16);
    cont16.drawImage();
    storedImages2.get("rgbSepia");

    Reader inMock16 = new StringReader("load res/RGBGrid.ppm rgb red-component rgb rgbRed " +
            "blue-component rgbRed rgbBlue green-component rgbBlue rgbGreen " +
            "value rgbGreen rgbValue " +
            "luma rgbValue rgbLuma " +
            "intensity rgbLuma rgbIntensity " +
            "horizontal-flip rgbIntensity rgbHoriz " +
            "vertical-flip rgbIntensity rgbVert " +
            "brighten 50 rgbVert rgbBrighten " +
            "darken 35 rgbBrighten rgbDarken " +
            "blur rgbDarken rgbBlur " +
            "sharpen rgbBlur rgbSharpen " +
            "greyscale rgbSharpen rgbGreyscale " +
            "sepia rgbGreyscale rgbSepia " +
            "save res/rgbSepia.ppm rgbSepia quit\n");

    IImage imageMock16 = new ImageMock(storedImages, log);
    ImageController contMock16 = new ImageControllerImpl(imageMock16, view, inMock16);
    contMock16.drawImage();
    assertTrue(log.toString().contains("load called with image array, rgb. " +
            "red grayscale called with oldname rgb, newname rgbRed. " +
            "blue grayscale called with oldname rgbRed, newname rgbBlue. " +
            "green grayscale called with oldname rgbBlue, newname rgbGreen. " +
            "value called with oldname rgbGreen, newname rgbValue. " +
            "luma called with oldname rgbValue, newname rgbLuma. " +
            "intensity called with oldname rgbLuma, newname rgbIntensity. " +
            "flip horizontal called with oldname rgbIntensity, newname rgbHoriz. " +
            "flip vertical called with oldname rgbIntensity, newname rgbVert. " +
            "brighten called with oldname rgbVert, newname rgbBrighten, increment 50. " +
            "darken called with oldname rgbBrighten, newname rgbDarken, increment 35. " +
            "blur called with oldname rgbDarken, newname rgbBlur. " +
            "sharpen called with oldname rgbBlur, newname rgbSharpen. " +
            "greyscale called with oldname rgbSharpen, newname rgbGreyscale. " +
            "sepia called with oldname rgbGreyscale, newname rgbSepia. " +
            "save called with filepath res/rgbSepia.ppm, newname rgbSepia"));
  }
  
  @Test
  public void testBMPToPPM() {
    Reader in = new StringReader("load res/33grid.bmp 33grid " +
            "vertical-flip 33grid 33gridVertical " +
            "save res/33gridVertical.ppm 33gridVertical quit\n");
    ImageController cont = new ImageControllerImpl(contMap, view, in);
    cont.drawImage();
    storedImages2.get("33gridVertical");

    Reader inMock = new StringReader("load res/33grid.bmp 33grid " +
            "vertical-flip 33grid 33gridVertical " +
            "save res/33gridVertical.ppm 33gridVertical quit\n");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController contMock = new ImageControllerImpl(imageMock, view, inMock);
    contMock.drawImage();
    assertTrue(log.toString().contains("load called with image array, 33grid. " +
            "flip vertical called with oldname 33grid, newname 33gridVertical. " +
            "save called with filepath res/33gridVertical.ppm, newname 33gridVertical"));
  }

  @Test
  public void testBMPToBMP() {
    Reader in = new StringReader("load res/33grid.bmp 33grid " +
            "vertical-flip 33grid 33gridVertical " +
            "save res/33gridVertical.bmp 33gridVertical quit\n");
    ImageController cont = new ImageControllerImpl(contMap, view, in);
    cont.drawImage();
    storedImages2.get("33gridVertical");

    Reader inMock = new StringReader("load res/33grid.bmp 33grid " +
            "vertical-flip 33grid 33gridVertical " +
            "save res/33gridVertical.bmp 33gridVertical quit\n");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController contMock = new ImageControllerImpl(imageMock, view, inMock);
    contMock.drawImage();
    assertTrue(log.toString().contains("load called with image array, 33grid. " +
            "flip vertical called with oldname 33grid, newname 33gridVertical. " +
            "save called with filepath res/33gridVertical.bmp, newname 33gridVertical"));
  }
  //check that BMP -> BMP and BMP -> PPM are the same image

  @Test
  public void testComparison1() {
    Image bmp = new Image(ImageUtil.readPPM("res/33gridVertical.bmp"));
    Image ppm = new Image(ImageUtil.readPPM("res/33gridVertical.ppm"));
    assertEquals(bmp, ppm);
  }


  @Test
  public void testPNGToPPM() {
    Reader in = new StringReader("load res/33grid.png 33grid " +
            "luma 33grid 33gridLuma " +
            "save res/33gridLuma.ppm 33gridLuma quit\n");
    ImageController cont = new ImageControllerImpl(contMap, view, in);
    cont.drawImage();
    storedImages2.get("33gridLuma");

    Reader inMock = new StringReader("load res/33grid.png 33grid " +
            "luma 33grid 33gridLuma " +
            "save res/33gridLuma.ppm 33gridLuma quit\n");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController contMock = new ImageControllerImpl(imageMock, view, inMock);
    contMock.drawImage();
    assertTrue(log.toString().contains("load called with image array, 33grid. " +
            "luma called with oldname 33grid, newname 33gridLuma. " +
            "save called with filepath res/33gridLuma.ppm, newname 33gridLuma"));
  }

  @Test
  public void testPNGToPNG() {
    Reader in = new StringReader("load res/33grid.png 33grid " +
            "luma 33grid 33gridLuma " +
            "save res/33gridLuma.png 33gridLuma quit\n");
    ImageController cont = new ImageControllerImpl(contMap, view, in);
    cont.drawImage();
    storedImages2.get("33gridRedGrey");

    Reader inMock = new StringReader("load res/33grid.png 33grid " +
            "luma 33grid 33gridLuma " +
            "save res/33gridLuma.png 33gridLuma quit\n");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController contMock = new ImageControllerImpl(imageMock, view, inMock);
    contMock.drawImage();
    assertTrue(log.toString().contains("load called with image array, 33grid. " +
            "luma called with oldname 33grid, newname 33gridLuma. " +
            "save called with filepath res/33gridLuma.png, newname 33gridLuma"));
  }

  @Test
  public void testComparison2() {
    Image png = new Image(ImageUtil.readPPM("res/33gridLuma.png"));
    Image ppm = new Image(ImageUtil.readPPM("res/33gridLuma.ppm"));
    assertEquals(png, ppm);
  }


  @Test
  public void testJPGToPPM() {
    Reader in = new StringReader("load res/33grid.jpg 33grid " +
            "value 33grid 33gridValue " +
            "save res/33gridValue.ppm 33gridValue quit\n");
    ImageController cont = new ImageControllerImpl(contMap, view, in);
    cont.drawImage();
    storedImages2.get("33gridValue");

    Reader inMock = new StringReader("load res/33grid.jpg 33grid " +
            "value 33grid 33gridValue " +
            "save res/33gridValue.ppm 33gridValue quit\n");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController contMock = new ImageControllerImpl(imageMock, view, inMock);
    contMock.drawImage();
    assertTrue(log.toString().contains("load called with image array, 33grid. " +
            "value called with oldname 33grid, newname 33gridValue. " +
            "save called with filepath res/33gridValue.ppm, newname 33gridValue"));
  }

  @Test
  public void testJPGToJPG() {
    Reader in = new StringReader("load res/33grid.jpg 33grid " +
            "value 33grid 33gridValue " +
            "save res/33gridValue.jpg 33gridValue quit\n");
    ImageController cont = new ImageControllerImpl(contMap, view, in);
    cont.drawImage();
    storedImages2.get("33gridValue");

    Reader inMock = new StringReader("load res/33grid.jpg 33grid " +
            "value 33grid 33gridValue " +
            "save res/33gridValue.jpg 33gridValue quit\n");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController contMock = new ImageControllerImpl(imageMock, view, inMock);
    contMock.drawImage();
    assertTrue(log.toString().contains("load called with image array, 33grid. " +
            "value called with oldname 33grid, newname 33gridValue. " +
            "save called with filepath res/33gridValue.jpg, newname 33gridValue"));
  }

  @Test
  public void testComparison3() {
    Image jpg = new Image(ImageUtil.readPPM("res/33gridValue.jpg"));
    Image ppm = new Image(ImageUtil.readPPM("res/33gridValue.ppm"));
    assertEquals(jpg, ppm);
  }

  @Test
  public void testQuit() {
    Reader in = new StringReader("quit");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController cont1Mock = new ImageControllerImpl(imageMock, view, in);
    cont1Mock.drawImage();
    assertTrue(log.toString().contains("Program successfully quit!"));

  }

  @Test
  public void testInvalidFile() {
    try {
      Reader in = new StringReader("load res/33grid.hello 33grid ");
      ImageController cont1 = new ImageControllerImpl(contMap, view, in);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Scanner has reached end of file")) {
        fail("should have thrown scanner exception");
      }
    }
  }

  @Test
  public void testInvalid() {
    Reader in = new StringReader("hello world quit");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController cont1Mock = new ImageControllerImpl(imageMock, view, in);
    cont1Mock.drawImage();
    assertTrue(log.toString().contains("Invalid input, try again!"));
  }

  @Test
  public void testIntroMessage() {
    Reader in = new StringReader("quit");

    IImage imageMock = new ImageMock(storedImages, log);
    ImageController cont1Mock = new ImageControllerImpl(imageMock, view, in);
    cont1Mock.drawImage();
    assertTrue(log.toString().contains("possible commands: \n" +
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
            "quit to quit program"));
  }

  //TEST CORRUPT IO EXCEPTION CLASS
  @Test
  public void testIO() {
    Reader in = new StringReader("hello");
    ImageView newIO = new MockView();
    ImageController cont1 = new ImageControllerImpl(contMap, newIO, in);
    try {
      cont1.drawImage();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("cannot read introMessage or transmit output")) {
        fail("should have thrown illegal argument exception");
      }
    }
  }

}


