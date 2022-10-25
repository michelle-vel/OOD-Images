package commands;

import model.IImage;

/**
 * Saves an image's pixel of arrays by the given name and saves it in a hashmap.
 */
public class Save implements ICommand {
  private final String filepath;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public Save(String filepath, String newName) {
    this.filepath = filepath;
    this.newName = newName;
  }


  @Override
  public void run(IImage img) {
    img.save(filepath, newName);
  }
}
