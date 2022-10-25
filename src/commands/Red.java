package commands;

import model.IImage;

/**
 * This class is in charge of converting an image into a red greyscale image.
 */
public class Red implements ICommand {
  private final String oldName;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public Red(String oldName, String newName) {
    this.oldName = oldName;
    this.newName = newName;
  }


  @Override
  public void run(IImage img) {
    img.redGrayscale(oldName, newName);
  }
}
