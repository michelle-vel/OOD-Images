package commands;

import model.IImage;

/**
 * This class is in charge of converting an image into a blurred image.
 */
public class Blur implements ICommand {
  private final String oldName;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public Blur(String oldName, String newName) {
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
    img.blur(oldName, newName);
  }
}
