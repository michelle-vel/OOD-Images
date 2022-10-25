package model;


import java.awt.Color;
import java.util.Objects;

/**
 * This class represents a single pixel of an image that has a Red, Green, and Blue color values.
 * The methods within this class are able to manipulate the individual pixels based on
 * the specified commands.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;
  //INVARIANT: red is a valid int value
  //INVARIANT: green is a valid int value
  //INVARIANT: blue is a valid int value

  /**
   * Constructor to make an individual pixel.
   * @param red integer for red value
   * @param green integer for green value
   * @param blue integer for blue value
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public int getRed() {
    return this.red;
  }

  public int getBlue() {
    return this.blue;
  }

  public int getGreen() {
    return this.green;
  }

  public Color getColor() {
    return new Color(this.red, this.green, this.blue);
  }

  /**
   * Brightens an image by incrementing each pixel.
   *
   * @param increment   the amount to increase brightness for each pixel
=   */
  public void brighten(int increment) throws IllegalArgumentException {
    if (this.red + increment >= 0 && this.red + increment <= 255) {
      this.red += increment;
    }
    if (this.green + increment >= 0 && this.green + increment <= 255) {
      this.green += increment;
    }
    if (this.blue + increment >= 0 && this.blue + increment <= 255) {
      this.blue += increment;
    }

    if (this.red + increment > 255) {
      this.red = 255;
    }

    if (this.green + increment > 255) {
      this.green = 255;
    }

    if (this.blue + increment > 255) {
      this.blue = 255;
    }

  }

  /**
   * Darkens an image by incrementing (subtract) each pixel.
   * @param increment  the amount to subtract from each pixel value
   */
  public void darken(int increment) throws IllegalArgumentException {
    if (this.red - increment >= 0) {
      this.red = this.red - increment;
    }
    if (this.green - increment >= 0) {
      this.green = this.green - increment;
    }
    if (this.blue - increment >= 0) {
      this.blue = this.blue - increment;
    }

    if (this.red - increment < 0) {
      this.red = 0;
    }

    if (this.green - increment < 0) {
      this.green = 0;
    }

    if (this.blue - increment < 0) {
      this.blue = 0;
    }

  }

  public void redGreyscale() {
    this.green = this.red;
    this.blue = this.red;
  }

  public void greenGreyscale() {
    this.red = this.green;
    this.blue = this.green;
  }

  public void blueGreyscale() {
    this.red = this.blue;
    this.green = this.blue;
  }

  /**
   * Takes the average of r,g,b components of pixel and sets each component to average.
   */
  public void intensity() {
    int average = (this.red + this.blue + this.green) / 3;
    this.red = average;
    this.blue = average;
    this.green = average;
  }

  /**
   * Computes the luma and sets each component of pixel to luma value.
   */
  public void luma() {
    int luma = (int) ((0.2126 * this.red) + (0.7152 * this.green) + (0.0722 * this.blue));
    this.red = luma;
    this.blue = luma;
    this.green = luma;
  }

  /**
   * Sets each component of pixel to max value of r,g,b component.
   */
  public void value() {
    int maxVal = Math.max(Math.max(this.red, this.blue), this.green);
    this.red = maxVal;
    this.blue = maxVal;
    this.green = maxVal;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return red == pixel.red && green == pixel.green && blue == pixel.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

}


