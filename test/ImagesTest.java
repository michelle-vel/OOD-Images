

import org.junit.Before;
import org.junit.Test;

import controller.ImageUtil;
import model.Image;
import model.ImageOperations;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the model and all its methods.
 * Also tests readPPM and readAll methods.
 */
public class ImagesTest {

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
  public void testVertical() {
    ImageOperations actualVertical = image.flipVertical();
    Pixel[][] pixel = new Pixel[3][3];
    pixel[0][0] = new Pixel(0, 0, 255);
    pixel[0][1] = new Pixel(0, 0, 255);
    pixel[0][2] = new Pixel(0, 0, 255);
    pixel[1][0] = new Pixel(0, 255, 0);
    pixel[1][1] = new Pixel(0, 255, 0);
    pixel[1][2] = new Pixel(0, 255, 0);
    pixel[2][0] = new Pixel(255, 0, 0);
    pixel[2][1] = new Pixel(255, 0, 0);
    pixel[2][2] = new Pixel(255, 0, 0);
    ImageOperations imageDark = new Image(pixel);
    assertEquals(imageDark, actualVertical);



  }

  @Test
  public void testHorizontal() {
    ImageOperations actualVertical = image.flipVertical();
    Pixel[][] pixel = new Pixel[3][3];
    pixel[0][0] = new Pixel(0, 0, 255);
    pixel[0][1] = new Pixel(0, 0, 255);
    pixel[0][2] = new Pixel(0, 0, 255);
    pixel[1][0] = new Pixel(0, 255, 0);
    pixel[1][1] = new Pixel(0, 255, 0);
    pixel[1][2] = new Pixel(0, 255, 0);
    pixel[2][0] = new Pixel(255, 0, 0);
    pixel[2][1] = new Pixel(255, 0, 0);
    pixel[2][2] = new Pixel(255, 0, 0);
    ImageOperations imageDark = new Image(pixel);
    assertEquals(imageDark, actualVertical);
  }

  @Test
  public void testBrighten() {
    ImageOperations actualBright = image.brighten(50);

    Pixel[][] pixelBright = new Pixel[3][3];
    pixelBright[0][0] = new Pixel(255, 50, 50);
    pixelBright[0][1] = new Pixel(255, 50, 50);
    pixelBright[0][2] = new Pixel(255, 50, 50);
    pixelBright[1][0] = new Pixel(50, 255, 50);
    pixelBright[1][1] = new Pixel(50, 255, 50);
    pixelBright[1][2] = new Pixel(50, 255, 50);
    pixelBright[2][0] = new Pixel(50, 50, 255);
    pixelBright[2][1] = new Pixel(50, 50, 255);
    pixelBright[2][2] = new Pixel(50, 50, 255);
    ImageOperations imageBright = new Image(pixelBright);

    assertEquals(imageBright, actualBright);
  }

  @Test
  public void testDarken() {
    ImageOperations actualDark = image.darken(50);

    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(205, 0, 0);
    pixels[0][1] = new Pixel(205, 0, 0);
    pixels[0][2] = new Pixel(205, 0, 0);
    pixels[1][0] = new Pixel(0, 205, 0);
    pixels[1][1] = new Pixel(0, 205, 0);
    pixels[1][2] = new Pixel(0, 205, 0);
    pixels[2][0] = new Pixel(0, 0, 205);
    pixels[2][1] = new Pixel(0, 0, 205);
    pixels[2][2] = new Pixel(0, 0, 205);
    ImageOperations imageDark = new Image(pixels);
    assertEquals(imageDark, actualDark);
  }

  // FIXME
  @Test
  public void testRedGrayscale() {
    ImageOperations actualRedGrey = image.redGrey();

    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(255, 255, 255);
    pixels[0][1] = new Pixel(255, 255, 255);
    pixels[0][2] = new Pixel(255, 255, 255);
    pixels[1][0] = new Pixel(0, 0, 0);
    pixels[1][1] = new Pixel(0, 0, 0);
    pixels[1][2] = new Pixel(0, 0, 0);
    pixels[2][0] = new Pixel(0, 0, 0);
    pixels[2][1] = new Pixel(0, 0, 0);
    pixels[2][2] = new Pixel(0, 0, 0);
    ImageOperations imageRedGrey = new Image(pixels);
    assertEquals(imageRedGrey, actualRedGrey);
  }

  @Test
  public void testGreenGrayscale() {
    ImageOperations actualGreenGrey = image.greenGrey();

    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(0, 0, 0);
    pixels[0][1] = new Pixel(0, 0, 0);
    pixels[0][2] = new Pixel(0, 0, 0);
    pixels[1][0] = new Pixel(255, 255, 255);
    pixels[1][1] = new Pixel(255, 255, 255);
    pixels[1][2] = new Pixel(255, 255, 255);
    pixels[2][0] = new Pixel(0, 0, 0);
    pixels[2][1] = new Pixel(0, 0, 0);
    pixels[2][2] = new Pixel(0, 0, 0);
    ImageOperations imageGreenGrey = new Image(pixels);
    assertEquals(imageGreenGrey, actualGreenGrey);

  }

  @Test
  public void testBlueGrayscale() {
    ImageOperations actualBlueGrey = image.blueGrey();

    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(0, 0, 0);
    pixels[0][1] = new Pixel(0, 0, 0);
    pixels[0][2] = new Pixel(0, 0, 0);
    pixels[1][0] = new Pixel(0, 0, 0);
    pixels[1][1] = new Pixel(0, 0, 0);
    pixels[1][2] = new Pixel(0, 0, 0);
    pixels[2][0] = new Pixel(255, 255, 255);
    pixels[2][1] = new Pixel(255, 255, 255);
    pixels[2][2] = new Pixel(255, 255, 255);
    ImageOperations imageBlueGrey = new Image(pixels);
    assertEquals(imageBlueGrey, actualBlueGrey);
  }

  @Test
  public void testIntensity() {
    ImageOperations actualIntensity = image.intensity();

    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(85, 85, 85);
    pixels[0][1] = new Pixel(85, 85, 85);
    pixels[0][2] = new Pixel(85, 85, 85);
    pixels[1][0] = new Pixel(85, 85, 85);
    pixels[1][1] = new Pixel(85, 85, 85);
    pixels[1][2] = new Pixel(85, 85, 85);
    pixels[2][0] = new Pixel(85, 85, 85);
    pixels[2][1] = new Pixel(85, 85, 85);
    pixels[2][2] = new Pixel(85, 85, 85);
    ImageOperations imageIntensified = new Image(pixels);
    assertEquals(imageIntensified, actualIntensity);
  }


  @Test
  public void testLuma() {
    ImageOperations actualLuma = image.luma();

    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(54, 54, 54);
    pixels[0][1] = new Pixel(54, 54, 54);
    pixels[0][2] = new Pixel(54, 54, 54);
    pixels[1][0] = new Pixel(182, 182, 182);
    pixels[1][1] = new Pixel(182, 182, 182);
    pixels[1][2] = new Pixel(182, 182, 182);
    pixels[2][0] = new Pixel(18, 18, 18);
    pixels[2][1] = new Pixel(18, 18, 18);
    pixels[2][2] = new Pixel(18, 18, 18);
    ImageOperations imageLuma = new Image(pixels);
    assertEquals(imageLuma, actualLuma);
  }

  @Test
  public void testValue() {
    ImageOperations actualValue = image.value();

    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(255, 255, 255);
    pixels[0][1] = new Pixel(255, 255, 255);
    pixels[0][2] = new Pixel(255, 255, 255);
    pixels[1][0] = new Pixel(255, 255, 255);
    pixels[1][1] = new Pixel(255, 255, 255);
    pixels[1][2] = new Pixel(255, 255, 255);
    pixels[2][0] = new Pixel(255, 255, 255);
    pixels[2][1] = new Pixel(255, 255, 255);
    pixels[2][2] = new Pixel(255, 255, 255);
    ImageOperations imageValue = new Image(pixels);
    assertEquals(imageValue, actualValue);
  }

  @Test
  public void testSharpen() {
    Image sharpen = new Image(ImageUtil.readPPM("res/33gridSharp.ppm"));
    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(31, 31, 255);
    pixels[0][1] = new Pixel(255, 230, 159);
    pixels[0][2] = new Pixel(255, 21, 7);
    pixels[1][0] = new Pixel(230, 126, 255);
    pixels[1][1] = new Pixel(255, 165, 255);
    pixels[1][2] = new Pixel(190, 148, 255);
    pixels[2][0] = new Pixel(255, 82, 11);
    pixels[2][1] = new Pixel(255, 255, 205);
    pixels[2][2] = new Pixel(46, 80, 255);
    //

    ImageOperations imageSharp = new Image(pixels);
    assertEquals(imageSharp, sharpen);
  }

  @Test
  public void testBlur() {
    Image blur = new Image(ImageUtil.readPPM("res/33gridBlur.ppm"));
    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(47, 31, 95);
    pixels[0][1] = new Pixel(133, 67, 43);
    pixels[0][2] = new Pixel(96, 8, 37);
    pixels[1][0] = new Pixel(93, 24, 78);
    pixels[1][1] = new Pixel(148, 45, 71);
    pixels[1][2] = new Pixel(54, 26, 111);
    pixels[2][0] = new Pixel(116, 37, 14);
    pixels[2][1] = new Pixel(105, 77, 54);
    pixels[2][2] = new Pixel(29, 15, 88);

    ImageOperations imageBlur = new Image(pixels);
    assertEquals(imageBlur, blur);

  }

  @Test
  public void testSepia() {
    Image sepia = new Image(ImageUtil.readPPM("res/33gridSepia.ppm"));
    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(48, 42, 33);
    pixels[0][1] = new Pixel(255, 255, 205);
    pixels[0][2] = new Pixel(100, 88, 69);
    pixels[1][0] = new Pixel(48, 42, 33);
    pixels[1][1] = new Pixel(100, 88, 69);
    pixels[1][2] = new Pixel(48, 42, 33);
    pixels[2][0] = new Pixel(100, 88, 69);
    pixels[2][1] = new Pixel(255, 255, 205);
    pixels[2][2] = new Pixel(48, 42, 33);

    ImageOperations imageSepia = new Image(pixels);
    assertEquals(imageSepia, sepia);

  }

  @Test
  public void testGreyscale() {
    Image greyscale = new Image(ImageUtil.readPPM("res/33gridGreyscale.ppm"));
    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(18, 18, 18);
    pixels[0][1] = new Pixel(236, 236, 236);
    pixels[0][2] = new Pixel(54, 54, 54);
    pixels[1][0] = new Pixel(18, 18, 18);
    pixels[1][1] = new Pixel(54, 54, 54);
    pixels[1][2] = new Pixel(18, 18, 18);
    pixels[2][0] = new Pixel(54, 54, 54);
    pixels[2][1] = new Pixel(236, 236, 236);
    pixels[2][2] = new Pixel(18, 18, 18);

    ImageOperations imageGrey = new Image(pixels);
    assertEquals(imageGrey, greyscale);
  }

  //readAll tests
  @Test
  public void testReadAllPNG() {
    Image grid = new Image(ImageUtil.readAll("res/33grid.png"));
    Pixel[][] pixel = new Pixel[3][3];
    pixel[0][0] = new Pixel(0,0,255);
    pixel[0][1] = new Pixel(255, 255, 0);
    pixel[0][2] = new Pixel(255, 0, 0);
    pixel[1][0] = new Pixel(0, 0, 255);
    pixel[1][1] = new Pixel(255, 0, 0);
    pixel[1][2] = new Pixel(0,0, 255);
    pixel[2][0] = new Pixel(255, 0, 0);
    pixel[2][1] = new Pixel(255, 255, 0);
    pixel[2][2] = new Pixel(0, 0, 255);
    ImageOperations readPNGgrid = new Image(pixel);
    assertEquals(readPNGgrid, grid);
  }

  @Test
  public void testReadAllJPG() {
    Image grid = new Image(ImageUtil.readAll("res/33grid.jpg"));
    Pixel[][] pixel = new Pixel[3][3];
    pixel[0][0] = new Pixel(11,0,255);
    pixel[0][1] = new Pixel(252, 249, 0);
    pixel[0][2] = new Pixel(255, 0, 19);
    pixel[1][0] = new Pixel(0, 0, 242);
    pixel[1][1] = new Pixel(249, 13, 2);
    pixel[1][2] = new Pixel(0,1, 253);
    pixel[2][0] = new Pixel(249, 0, 13);
    pixel[2][1] = new Pixel(254, 255, 11);
    pixel[2][2] = new Pixel(0, 10, 244);
    ImageOperations readJPGgrid = new Image(pixel);
    assertEquals(readJPGgrid, grid);
  }

  @Test
  public void testReadAllBMP() {
    Image grid = new Image(ImageUtil.readAll("res/33grid.bmp"));
    Pixel[][] pixel = new Pixel[3][3];
    pixel[0][0] = new Pixel(0,0,255);
    pixel[0][1] = new Pixel(255, 255, 0);
    pixel[0][2] = new Pixel(255, 0, 0);
    pixel[1][0] = new Pixel(0, 0, 255);
    pixel[1][1] = new Pixel(255, 0, 0);
    pixel[1][2] = new Pixel(0,0, 255);
    pixel[2][0] = new Pixel(255, 0, 0);
    pixel[2][1] = new Pixel(255, 255, 0);
    pixel[2][2] = new Pixel(0, 0, 255);
    ImageOperations readBMPgrid = new Image(pixel);
    assertEquals(readBMPgrid, grid);
  }
}



