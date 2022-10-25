package commands;

import model.IImage;

/**
 Converts an image so each pixel is the maximum value of the three components.
 */
public class Value implements ICommand {
  private final String oldName;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public Value(String oldName, String newName) {
    this.oldName = oldName;
    this.newName = newName;
  }


  @Override
  public void run(IImage img) {
    img.value(oldName, newName);
  }
}
