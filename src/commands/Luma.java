package commands;

import model.IImage;

/**
 * Converts an image so each pixel is the luma value.
 */
public class Luma implements ICommand {
  private final String oldName;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public Luma(String oldName, String newName) {
    this.oldName = oldName;
    this.newName = newName;
  }


  @Override
  public void run(IImage img) {
    img.luma(oldName, newName);
  }
}
