package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Pixel;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the pixels.
   *
   * @param filename the path of the file.
   * @return the file as a pixel of arrays
   */
  public static Pixel[][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    //System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    //System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    // 2-day array of pixels that make up a PPM image
    Pixel[][] imageArray = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        imageArray[i][j] = new Pixel(r, g, b);
        //System.out.println("Color of pixel (" + i + "," + j + "): " + r + "," + g + "," + b);
      }
    }
    return imageArray;
  }

  /**
   * Read an image file in any format and return it as an array of pixels.
   *
   * @param filename the path of the file.
   * @return the file as a pixel of arrays
   */
  public static Pixel[][] readAll(String filename) throws IllegalArgumentException {
    // Check the fileType
    String fileType = filename.substring(filename.lastIndexOf(".") + 1);
    // Read the image whatever the fileType is
    try {
      BufferedImage img = ImageIO.read(new File(filename));
      int width = img.getWidth();
      int height = img.getHeight();
      int max = 255;
      Pixel[][] imageArr;
      imageArr = new Pixel[height][width];

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          int r = new Color(img.getRGB(i, j)).getRed();
          int g = new Color(img.getRGB(i, j)).getGreen();
          int b = new Color(img.getRGB(i, j)).getBlue();

          imageArr[j][i] = new Pixel(r,g,b);
        }
      }
      return imageArr;
    } catch (IOException e) {
      throw new IllegalArgumentException("The given file is invalid!");
    }
  }


  /**
   * Runs the readPPM method on the file with the given filename.
   * @param args   the files given by user.
   */
  public static void main(String[] args)  {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}