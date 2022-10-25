package commands;

import model.IImage;
import model.Pixel;

/**
 * Loads an image's pixel of arrays by the given name and saves it in a hashmap.
 */
public class Load implements ICommand {
  private final Pixel[][] imageArr;
  private final String newName;
  //INVARIANT: old name must correspond to something that was in the hashmap,
  //or input will be invalid
  //INVARIANT: new name must be valid and not null


  public Load(Pixel[][] imageArr, String newName) {
    this.imageArr = imageArr;
    this.newName = newName;
  }


  @Override
  public void run(IImage img) {
    Pixel[][] imageNew = new Pixel[imageArr.length][];
    for (int i = 0; i < imageArr.length; i++) {
      imageNew[i] = new Pixel[imageArr[i].length];
      for (int j = 0; j < imageArr[i].length; j++) {
        imageNew[i][j] = imageArr[i][j];
      }
    }
    img.load(imageNew, newName);
  }
}
