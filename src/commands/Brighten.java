package commands;

import model.IImage;

/**
 * This class brightens the pixels of an image by given increment.
 */
public class Brighten implements ICommand {
  private final int increment;
  private final String oldName;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  /**
   * Constructor for brighten command.
   * @param increment  the amount to increase brightness.
   * @param oldName    the old image name
   * @param newName    the new image name
   */
  public Brighten(int increment, String oldName, String newName) {
    this.increment = increment;
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
    img.brighten(oldName, newName, increment);
  }
}
