package commands;

import model.IImage;

/**
 * Converts an image so each pixel is the average of the three components for each pixel.
 */
public class Intensity implements ICommand {
  private final String oldName;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public Intensity(String oldName, String newName) {
    this.oldName = oldName;
    this.newName = newName;
  }

  /**
   * Executes the command.
   *
   * @param img the image to convert to blueGrayscale.
   */
  @Override
  public void run(IImage img) {
    img.intensity(oldName, newName);
  }
}
