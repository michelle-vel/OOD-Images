package controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.IImage;
import model.ImageModelClass;
import view.GUIView;
import view.ImageView;
import view.ImageViewRender;
import view.SwingFeaturesFrame;


/**
 * This class represents the main class that runs the Image Processor.
 * Supports the ability to input a file to run or run as a text scripting application.
 */
public class ImageProgram {

  /**
   * This is the main class where the user can run commands on the Image Processor.
   *
   * @param args user input command-line args; if empty, run as text scripting application.
   */
  public static void main(String[] args) {
    IImage img1 = new ImageModelClass();
    ImageView view1 = new ImageViewRender();
    Readable rd = new InputStreamReader(System.in);

    // if there are one or more arguments, enter for loop and process files
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        // if args start with file, try to read the text file given
        if (args[i].equals("-file")) {
          try {
            // file reader reads file by absolute file path
            rd = new FileReader(args[i + 1]);
            // create new controller with this readable and execute program
            ImageController cont1 = new ImageControllerImpl(img1, view1, rd);
            cont1.drawImage();
          } catch (IOException e) {
            System.out.println("Error: please input a valid file!");
            return;
          }
        } else if (args[i].equals("-text")) {
          // file reader reads file by absolute file path
          // create new controller with this readable and execute program
          ImageController cont1 = new ImageControllerImpl(img1, view1, rd);
          cont1.drawImage();
        } else if (args.length == 3 && args[0].equals("java") &&
                args[1].equals("-jar")) {
          SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
          GUIView view2 = new SwingFeaturesFrame();
          ImageController cont1 = new ImageControllerImpl(img1, view2);
          cont1.setView(view2);
        }


      }
    }
  }
}




