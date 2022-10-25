package controller;


import javax.swing.UIManager;
import model.IImage;
import model.ImageModelClass;
import view.GUIView;
import view.PopUpButton;
import view.SwingFeaturesFrame;

/**
 * Runs the view program.
 */
public class ViewProgram {

  /**
   * Method to run the view program.
   * @param args given arguments
   */
  public static void main(String[] args) {
    SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
    IImage img1 = new ImageModelClass();
    GUIView view1 = new SwingFeaturesFrame();
    ImageController cont1 = new ImageControllerImpl(img1, view1);
    cont1.setView(view1);

    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

    } catch (Exception e) {
      PopUpButton pop = new PopUpButton();
      pop.setVisible(true);

    }
  }
}
