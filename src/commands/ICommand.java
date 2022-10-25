package commands;

import model.IImage;

/**
 * This interface represents a Command object that can execute a method based on the respective
 * implementation.
 */
public interface ICommand {

  /**
   * Executes the current command mutating the current model.
   *
   * @param img the image to be mutated
   */
  void run(IImage img);

}
