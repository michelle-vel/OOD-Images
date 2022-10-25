import java.util.HashMap;
import java.util.Map;

import model.IImage;
import model.Image;
import model.ImageOperations;
import model.Pixel;

/**
 * This mock class is used to test the controller.
 */
public class ImageMock implements IImage {

  private final Map<String, ImageOperations> storedImages;
  private final StringBuilder log;


  /**
   * Mock constructor that stores images in a hashmap and keeps a log of operations.
   *
   * @param storedImages the given array
   * @param log the appendable output
   */
  public ImageMock(Map<String, ImageOperations> storedImages, StringBuilder log) {
    this.log = log;
    this.storedImages = new HashMap<>();
  }

  /**
   * See interface.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void flipVertical(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).flipVertical());
    log.append(" flip vertical called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void flipHorizontal(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).flipHorizontal());
    log.append(" flip horizontal called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   *
   * @param oldName   the name of the old file
   * @param newName   the name of the mutated file
   * @param increment the amount to increase
   */
  public void brighten(String oldName, String newName, int increment) {
    this.storedImages.put(newName, this.storedImages.get(oldName).brighten(increment));
    log.append(" brighten called with oldname " + oldName + ", newname " + newName + ", increment "
            + increment + ".");

  }

  /**
   * See interface.
   *
   * @param oldName   the name of the old file
   * @param newName   the name of the mutated file
   * @param increment the amount to decrease
   */
  public void darken(String oldName, String newName, int increment) {
    this.storedImages.put(newName, this.storedImages.get(oldName).darken(increment));
    log.append(" darken called with oldname " + oldName + ", newname " + newName + ", increment "
            + increment + ".");

  }

  /**
   * See intergace.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void redGrayscale(String oldName, String newName) {
    ImageOperations img1 = this.storedImages.get(oldName).redGrey();
    this.storedImages.put(newName, img1);
    log.append(" red grayscale called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void greenGrayscale(String oldName, String newName) {
    ImageOperations img1 = this.storedImages.get(oldName).greenGrey();
    this.storedImages.put(newName, img1);
    log.append(" green grayscale called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   *
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void blueGrayscale(String oldName, String newName) {
    ImageOperations img1 = this.storedImages.get(oldName).blueGrey();
    this.storedImages.put(newName, img1);
    log.append(" blue grayscale called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void value(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).value());
    log.append(" value called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void intensity(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).intensity());
    log.append(" intensity called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   * @param oldName the name of the old file
   * @param newName the name of the mutated file
   */
  public void luma(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).luma());
    log.append(" luma called with oldname " + oldName + ", newname " + newName + ".");

  }

  /**
   * See interface.
   *
   * @param image the image as a pixel of arrays
   * @param name     name to load the file as
   */
  public void load(Pixel[][] image, String name) {
    this.storedImages.put(name, new Image(image));
    log.append(" load called with image array, " + name + ".");

  }

  @Override
  public void blur(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).blurAndSharpen("blur"));
    log.append(" blur called with oldname " + oldName + ", newname " + newName + ".");


  }

  @Override
  public void sharpen(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).blurAndSharpen("sharpen"));
    log.append(" sharpen called with oldname " + oldName + ", newname " + newName + ".");

  }

  @Override
  public void greyscale(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).greyscaleAndSepia("greyscale"));
    log.append(" greyscale called with oldname " + oldName + ", newname " + newName + ".");

  }

  @Override
  public void sepia(String oldName, String newName) {
    this.storedImages.put(newName, this.storedImages.get(oldName).greyscaleAndSepia("sepia"));
    log.append(" sepia called with oldname " + oldName + ", newname " + newName + ".");

  }

  @Override
  public ImageOperations getter(String name) {
    return null;
  }

  /**
   * See interface.
   *
   * @param filepath the location to save the image
   * @param newName  name to save the file as
   */
  public void save(String filepath, String newName) {
    ImageOperations imageToSave = this.storedImages.get(newName);
    imageToSave.save(filepath);
    log.append(" save called with filepath " + filepath + ", newname " + newName + ".");

  }
}
