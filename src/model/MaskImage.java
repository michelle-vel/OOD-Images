package model;

/**
 * Attempts to partially manipulate image.
 */
public class MaskImage {
  Pixel[][] imageArr;

  /**
   * Constructor for Image that takes in a 2-d pixel array and returns an image.
   *
   * @param imageArr the pixels of an image
   * @throws IllegalArgumentException if array is null
   */
  public MaskImage(Pixel[][] imageArr) throws IllegalArgumentException {
    this.imageArr = imageArr;
  }

  /**
   * Make a BW image.
   * @return Image new image
   */
  public Image makeBWImage() {
    //change to black and white: if the pixel is greater than 255/2 set it to black,
    //otherwise set to white
    Pixel[][] bwImage = new Pixel[imageArr.length][];
    for (int row = 0; row < imageArr.length; row += 1) {
      for (int col = 0; col < imageArr[row].length; col += 1) {
        if (imageArr[row][col].getRed() + imageArr[row][col].getGreen() +
                imageArr[row][col].getBlue() < 255 / 2) {
          bwImage[row][col] = new Pixel(0, 0, 0);
        } else {
          bwImage[row][col] = new Pixel(255, 255, 255);
        }
      }
    }
    return new Image(bwImage);
  }

  /**
   * Blurs and sharpens the image.
   * @param type String type
   * @return Image new image
   */
  public Image bwBlurSharpen(String type) {
    if (type.equals("blur")) {
      return makeBWImage().blurAndSharpen("blur");
    } else {
      return makeBWImage().blurAndSharpen("sharpen");
    }
  }

  /**
   * Greyscales and sepia the image.
   * @param type String type
   * @return Image new image
   */
  public Image bwGreyscaleSepia(String type) {
    if (type.equals("greyscale")) {
      return makeBWImage().greyscaleAndSepia("greyscale");
    } else {
      return makeBWImage().greyscaleAndSepia("sepia");
    }
  }

  /**
   * Red component the image.
   * @return Image new image
   */
  public Image bwRed() {
    return makeBWImage().redGrey();
  }

  /**
   * Green component the image.
   * @return Image
   */
  public Image bwGreen() {
    return makeBWImage().greenGrey();
  }

  /**
   * Blue component the image.
   * @return Image new image
   */
  public Image bwBlue() {
    return makeBWImage().blueGrey();
  }

}
