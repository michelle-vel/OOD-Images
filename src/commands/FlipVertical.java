package commands;

import model.IImage;

/**
 * Flips the image vertically.
 */
public class FlipVertical implements ICommand {
  private final String oldName;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public FlipVertical(String oldName, String newName) {
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
    img.flipVertical(oldName, newName);
  }
}
