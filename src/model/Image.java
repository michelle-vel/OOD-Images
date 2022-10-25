package model;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements all the methods of ImageOperations by iterating through each pixel
 * of the image and executing a command.
 */
public class Image implements ImageOperations {
  private final Pixel[][] imageArr;
  // INVARIANT: imageArr is not null, and will throw IllegalArgumentException

  /**
   * Constructor for Image that takes in a 2-d pixel array and returns an image.
   *
   * @param imageArr the pixels of an image
   * @throws IllegalArgumentException if array is null
   */
  public Image(Pixel[][] imageArr) throws IllegalArgumentException {
    if (imageArr == null) {
      throw new IllegalArgumentException("cannot be null");
    }

    this.imageArr = imageArr;
  }

  /**
   * This copies the array so you don't mutate the original.
   *
   * @return a new array
   */
  public Pixel[][] returnCopy() {
    Pixel[][] imageNew = new Pixel[imageArr.length][];
    for (int i = 0; i < imageArr.length; i++) {
      imageNew[i] = new Pixel[imageArr[i].length];
      for (int j = 0; j < imageArr[i].length; j++) {
        imageNew[i][j] = imageArr[i][j];
      }
    }
    return imageNew;
  }

  /**
   * Return the current color at this location.
   * @param row the row number
   * @param col the col number
   * @return Color
   */
  public Color currentColor(int row, int col) {
    return this.imageArr[row][col].getColor();
  }


  /**
   * Return size of the row of the array.
   * @return int row size
   */
  public int returnRowSize() {
    return this.imageArr.length;
  }

  /**
   * Return size of the col of the array.
   * @return int col size
   */
  public int returnColSize() {
    return this.imageArr[0].length;
  }

  /**
   * Flips a Picture Vertically by mutating its 2D Array of Pixels.
   *
   * @return a new mutated image.
   */
  public Image flipVertical() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length / 2; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) { //
        Pixel p = imageNew[row][col];
        imageNew[row][col] = imageNew[imageNew.length - row - 1][col];
        imageNew[imageNew.length - row - 1][col] = p;
      }
    }
    return new Image(imageNew);
  }

  /**
   * Flips a Picture Horizontally by mutating its 2D Array of Pixels.
   *
   * @return a new mutated image.
   */
  public Image flipHorizontal() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col <= imageNew[row].length / 2; col += 1) {
        Pixel p = imageNew[row][col];
        imageNew[row][col] = imageNew[row][imageNew[col].length - col - 1];
        imageNew[row][imageNew[col].length - col - 1] = p;
      }
    }
    return new Image(imageNew);
  }

  /**
   * Increases the color values for the for all the SinglePixels in this picture by a given value.
   *
   * @param increment The value to brighten by
   */
  public Image brighten(int increment) {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].brighten(increment);
      }
    }
    return new Image(imageNew);
  }

  /**
   * Decreases the color values for the for all the SinglePixels in this picture by a given value.
   *
   * @param increment The value to darken by
   */
  public Image darken(int increment) {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].darken(increment);
      }
    }
    return new Image(imageNew);
  }

  /**
   * Sets each pixel in the image to the red component of each pixel.
   *
   * @return a new mutated image.
   */
  public Image redGrey() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].redGreyscale();
      }
    }
    return new Image(imageNew);
  }

  /**
   * Sets each pixel in the image to the green component of each pixel.
   *
   * @return a new mutated image.
   */
  public Image greenGrey() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].greenGreyscale();
      }
    }
    return new Image(imageNew);
  }

  /**
   * Sets each pixel in the image to the blue component of each pixel.
   *
   * @return a new mutated image.
   */
  public Image blueGrey() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].blueGreyscale();
      }
    }
    return new Image(imageNew);
  }

  /**
   * Converts an image so each pixel is the average of the three components for each pixel.
   *
   * @return a new mutated image.
   */
  public Image intensity() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].intensity();
      }
    }
    return new Image(imageNew);
  }

  /**
   * Converts an image so each pixel is the luma value.
   *
   * @return a new mutated image.
   */
  public Image luma() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].luma();
      }
    }
    return new Image(imageNew);
  }

  /**
   * Converts an image so each pixel is the maximum value of
   * the three components.
   *
   * @return a new mutated image.
   */
  public Image value() {
    Pixel[][] imageNew = this.returnCopy();
    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        imageNew[row][col].value();
      }
    }
    return new Image(imageNew);
  }

  /**
   * In charge of saving an image and outputting it in PPM format to a
   * given filename that the user chooses.
   *
   * @param newFilename name of the file to be saved
   */
  public void save(String newFilename) {
    Pixel[][] imageNew = this.returnCopy();
    StringBuilder fileString = new StringBuilder();
    fileString.append("P3 " + "\n");
    fileString.append(imageNew[0].length + "\n");
    fileString.append(imageNew.length + "\n");
    fileString.append(255 + "\n");
    for (int i = 0; i < imageNew.length; i++) {
      for (int j = 0; j < imageNew[i].length; j++) {
        Pixel p = imageNew[i][j];
        fileString.append(p.getRed() + "\n");
        fileString.append(p.getGreen() + "\n");
        fileString.append(p.getBlue() + "\n");
      }
    }
    File newFile = new File(newFilename);
    try {
      FileOutputStream stream = new FileOutputStream(newFile);
      stream.write(fileString.toString().getBytes());
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns if a certain integer is out of bounds for the color.
   *
   * @param color integer representing a color value
   * @return boolean
   */
  private boolean outOfBounds(double color) {
    return (color > 255 || color < 0);
  }

  /**
   * Cuts off a color integer between 0 and 255.
   *
   * @param color integer representing a color value
   * @return new color value
   */
  private double isInvalid(double color) {
    if (color > 255) {
      color = 255;
    }
    if (color < 0) {
      color = 0;
    }
    return color;
  }

  /**
   * This method will apply either the blur filter or sharpen filter depending on which
   * input is put in.
   *
   * @param type String that will be either blur or sharpen.
   * @return New image that has a filter applied to it
   */

  public Image blurAndSharpen(String type) {
    double[][] filter = new double[0][];

    if (type.equals("blur")) {
      filter = new double[][]{
              {0.0625, 0.125, 0.0625},
              {0.125, 0.25, 0.125},
              {0.0625, 0.125, 0.0625}
      };
    }
    if (type.equals("sharpen")) {
      filter = new double[][]{
              {-0.125, -0.125, -0.125, -0.125, -0.125},
              {-0.125, 0.25, 0.25, 0.25, -0.125},
              {-0.125, 0.25, 1, 0.25, -0.125},
              {-0.125, 0.25, 0.25, 0.25, -0.125},
              {-0.125, -0.125, -0.125, -0.125, -0.125},
      };
    }
    Pixel[][] imageNew = this.returnCopy();

    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        double totalRed = 0;
        double totalGreen = 0;
        double totalBlue = 0;

        for (int rowPixel = 0; rowPixel < filter.length; rowPixel += 1) {
          for (int colPixel = 0; colPixel < filter[rowPixel].length; colPixel += 1) {
            int positionX = row - filter.length / 2 + rowPixel;
            int positionY = col - filter[rowPixel].length / 2 + colPixel;
            Pixel currentPixel;

            if (positionX >= 0 && positionY >= 0 && positionX < imageNew.length && positionY <
                    imageNew[row].length) {
              currentPixel = imageNew[positionX][positionY];
            } else {
              currentPixel = new Pixel(0, 0, 0);
            }
            totalRed += currentPixel.getRed() * filter[rowPixel][colPixel];
            totalGreen += currentPixel.getGreen() * filter[rowPixel][colPixel];
            totalBlue += currentPixel.getBlue() * filter[rowPixel][colPixel];
          }
        }
        if (outOfBounds(totalRed)) {
          totalRed = isInvalid(totalRed);
        }
        if (outOfBounds(totalGreen)) {
          totalGreen = isInvalid(totalGreen);
        }
        if (outOfBounds(totalBlue)) {
          totalBlue = isInvalid(totalBlue);
        }
        Pixel finalPixel = new Pixel((int) totalRed, (int) totalGreen, (int) totalBlue);
        imageNew[row][col] = finalPixel;
      }
    }
    return new Image(imageNew);
  }


  /**
   * This method will apply either the greyscale filter or sepia filter depending on which
   * input is put in.
   *
   * @param type String that will be either greyscale or sepia.
   * @return New image that has a filter applied to it
   */
  public Image greyscaleAndSepia(String type) {
    double[][] filter = new double[0][];
    if (type.equals("greyscale")) {
      filter = new double[][]{
              {0.2126, 0.7152, 0.0722},
              {0.2126, 0.7152, 0.0722},
              {0.2126, 0.7152, 0.0722}};
    }
    if (type.equals("sepia")) {
      filter = new double[][]{
              {0.393, 0.769, 0.189},
              {0.349, 0.686, 0.168},
              {0.272, 0.534, 0.131}};
    }

    Pixel[][] imageNew = this.returnCopy();
    double[] pixelVector = new double[3];
    double[] newVector = new double[3];

    for (int row = 0; row < imageNew.length; row += 1) {
      for (int col = 0; col < imageNew[row].length; col += 1) {
        pixelVector[0] = imageNew[row][col].getRed();
        pixelVector[1] = imageNew[row][col].getGreen();
        pixelVector[2] = imageNew[row][col].getBlue();

        for (int rowPixel = 0; rowPixel < filter.length; rowPixel += 1) {
          for (int colPixel = 0; colPixel < filter[rowPixel].length; colPixel += 1) {
            newVector[rowPixel] += filter[rowPixel][colPixel] * pixelVector[colPixel];
          }
        }
        double red = isInvalid(newVector[0]);
        double green = isInvalid(newVector[1]);
        double blue = isInvalid(newVector[2]);
        Pixel finalPixel = new Pixel((int) red, (int) green, (int) blue);
        imageNew[row][col] = finalPixel;
        newVector[0] = 0;
        newVector[1] = 0;
        newVector[2] = 0;
      }
    }
    return new Image(imageNew);
  }

  /**
   * Creates a map for the red pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  public Map<Integer, Integer> redPixels() {
    Map<Integer, Integer> redMap = new HashMap<>();
    for (int i = 1; i < 256; i++) {
      int amount = 0;
      for (int row = 0; row < imageArr.length; row += 1) {
        for (int col = 0; col < imageArr[row].length; col += 1) {
          if (imageArr[row][col].getRed() == i) {
            amount += 1;
          }
        }
      }
      redMap.put(i, amount);
    }
    return redMap;
  }


  /**
   * Creates a map for the green pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  public Map<Integer, Integer> greenPixels() {
    Map<Integer, Integer> greenMap = new HashMap<>();
    for (int i = 1; i <= 256; i++) {
      int amount = 0;
      for (int row = 0; row < imageArr.length; row += 1) {
        for (int col = 0; col < imageArr[row].length; col += 1) {
          if (imageArr[row][col].getGreen() == i) {
            amount += 1;
          }
        }
      }
      greenMap.put(i, amount);
    }
    return greenMap;
  }


  /**
   * Creates a map for the blue pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  public Map<Integer, Integer> bluePixels() {
    Map<Integer, Integer> blueMap = new HashMap<>();
    for (int i = 1; i <= 256; i++) {
      int amount = 0;
      for (int row = 0; row < imageArr.length; row += 1) {
        for (int col = 0; col < imageArr[row].length; col += 1) {
          if (imageArr[row][col].getBlue() == i) {
            amount += 1;
          }
        }
      }
      blueMap.put(i, amount);
    }
    return blueMap;
  }

  /**
   * Creates a map for the intensity of the pixels. Stores in the value
   * corresponding to the pixel amount.
   * @return map
   */
  public Map<Integer, Integer> intensityPixels() {
    Map<Integer, Integer> intensityMap = new HashMap<>();
    for (int i = 1; i <= 256; i++) {
      int amount = 0;
      for (int row = 0; row < imageArr.length; row += 1) {
        for (int col = 0; col < imageArr[row].length; col += 1) {
          if ((imageArr[row][col].getRed() + imageArr[row][col].getGreen() +
                  imageArr[row][col].getBlue()) / 3 == i) {
            amount += 1;
          }
        }
      }
      intensityMap.put(i, amount);
    }
    return intensityMap;
  }


  /**
   * Gets the largest number in the map.
   * @param map the given map
   * @return the largest number
   */
  public int largestNumber(Map<Integer, Integer> map) {
    int maxValue = Integer.MIN_VALUE;
    for (Map.Entry<Integer, Integer> element : map.entrySet()) {
      if (element.getValue() > maxValue) {
        maxValue = element.getValue();
      }
    }
    return maxValue;
  }




  /**
   * Checks if two objects are equal.
   *
   * @param o An image object
   * @return true if the objects are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Arrays.deepEquals(imageArr, image.imageArr);
  }

  /**
   * Overrides hashcode.
   *
   * @return integer hashcode value
   */
  @Override
  public int hashCode() {
    return Arrays.deepHashCode(imageArr);
  }

}