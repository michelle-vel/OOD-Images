package controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;


import model.IImage;
import commands.Blur;
import commands.Greyscale;
import commands.ICommand;
import commands.Blue;
import commands.Brighten;
import commands.Darken;
import commands.FlipHorizontal;
import commands.FlipVertical;
import commands.Green;
import commands.Intensity;
import commands.Load;
import commands.Luma;
import commands.Red;
import commands.Save;
import commands.Sepia;
import commands.Sharpen;
import commands.Value;
import model.ImageOperations;
import view.GUIView;
import view.ImageView;

import static controller.ImageUtil.readAll;
import static controller.ImageUtil.readPPM;

/**
 * This class represents the controller for our Image Processor.
 * It will execute the command inputted to the user and send it to the model to
 * execute the command.
 */
public class ImageControllerImpl implements ImageController, Features {
  private IImage img;
  private ImageView view;
  private GUIView gui;
  private Readable readable;

  //INVARIANT: img cannot be null, will throw IllegalArgumentException
  //INVARIANT: view cannot be null, will throw IllegalArgumentException
  //INVARIANT: readable cannot be null, will throw IllegalArgumentException

  /**
   * The constructor for Image Controller.
   *
   * @param img      the image the user inputs
   * @param view     the view of the appendable
   * @param readable the readable
   * @throws IllegalArgumentException if any arguments are null
   */
  public ImageControllerImpl(IImage img, ImageView view, Readable readable)
          throws IllegalArgumentException {
    if (img == null || view == null || readable == null) {
      throw new IllegalArgumentException("Given view and readable cannot be null!");
    }
    this.img = img;
    this.view = view;
    this.gui = null;
    this.readable = readable;
  }

  public ImageControllerImpl(IImage img, GUIView gui) {
    this.img = img;
    this.gui = gui;
  }


  /**
   * Initializes the GUIView.
   * @param v the given GUIView object.
   */
  public void setView(GUIView v) {
    v.addFeatures(this);
  }



  /**
   * Runs the Image Processing application. Depending on the view, the application will run,
   * and wait for user input. The controller will then delegate user input to the view and model
   * as needed.
   *
   * @throws IllegalArgumentException if controller cannot read input or transmit output.
   */
  public void drawImage() throws IllegalArgumentException {
    Scanner sc = new Scanner(this.readable);
    ICommand cmd;
    boolean quit = false;

    try {
      view.introMessage();
    } catch (IOException e) {
      throw new IllegalArgumentException("cannot read introMessage or transmit output");
    }
    if (!sc.hasNext()) {
      throw new IllegalArgumentException("cannot read input or transmit output");
    }

    while (!quit) {
      cmd = null;
      try {
        String userInstruction = sc.next();

        switch (userInstruction) {
          case "horizontal-flip":
            cmd = new FlipHorizontal(sc.next(), sc.next());
            break;
          case "vertical-flip":
            cmd = new FlipVertical(sc.next(), sc.next());
            break;
          case "red-component":
            cmd = new Red(sc.next(), sc.next());
            break;
          case "blue-component":
            cmd = new Blue(sc.next(), sc.next());
            break;
          case "green-component":
            cmd = new Green(sc.next(), sc.next());
            break;
          case "brighten":
            cmd = new Brighten(Integer.parseInt(sc.next()), sc.next(), sc.next());
            break;
          case "darken":
            cmd = new Darken(Integer.parseInt(sc.next()), sc.next(), sc.next());
            break;
          case "value":
            cmd = new Value(sc.next(), sc.next());
            break;
          case "intensity":
            cmd = new Intensity(sc.next(), sc.next());
            break;
          case "luma":
            cmd = new Luma(sc.next(), sc.next());
            break;
          case "save":
            cmd = new Save(sc.next(), sc.next());
            break;
          case "load":
            String filename = sc.next();
            String name = sc.next();
            String fileType = filename.substring(filename.lastIndexOf(".") + 1);
            if (fileType.equals("ppm")) {
              cmd = new Load(readPPM(filename), name);
            } else if ((fileType.equals("jpg")) || (fileType.equals("png")) ||
                    (fileType.equals("bmp"))) {
              cmd = new Load(readAll(filename), name);
            }
            break;
          case "blur":
            cmd = new Blur(sc.next(), sc.next());
            break;
          case "sharpen":
            cmd = new Sharpen(sc.next(), sc.next());
            break;
          case "greyscale":
            cmd = new Greyscale(sc.next(), sc.next());
            break;
          case "sepia":
            cmd = new Sepia(sc.next(), sc.next());
            break;
          case "quit": //quit
            quit = true;
            try {
              view.renderMessage("Program successfully quit!");
            } catch (IOException | NoSuchElementException e) {
              throw new IllegalArgumentException("cannot read input or transmit output");
            }
            break;

          default: //error due to unrecognized instruction
            writeMessage();
        }
        if (cmd != null) {
          try {
            cmd.run(img);
          } catch (IllegalArgumentException | NullPointerException | NoSuchElementException e) {
            writeMessage();
          }
        }
      } catch (IllegalArgumentException | NoSuchElementException e) {
        throw new IllegalArgumentException("Scanner is out of inputs!");
      }
    }
  }

  /**
   * Appends a string to the current Appendable to indicate an exception.
   */
  private void writeMessage() {
    try {
      view.renderMessage("Invalid input, try again!");
    } catch (IOException ex) {
      throw new IllegalArgumentException("Cannot read input or transmit output");
    }
  }

  /**
   * Loads an image into the GUIView.
   * @param filepath the given filepath
   */
  @Override
  public void load(String filepath) {
    if (filepath.contains("ppm")) {
      img.load(readPPM(filepath), "Image");
    } else if ((filepath.contains("jpg")) || (filepath.contains("png")) ||
            (filepath.contains("bmp"))) {
      img.load(readAll(filepath), "Image");
    }
    //above will return an array stored into the hashmap
    //have to get that array and call view update image on it
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Saves an image.
   * @param name the given name
   */
  @Override
  public void save(String name) {
    img.save(name, "Image");
  }

  /**
   * Blue greyscales an image.
   */
  @Override
  public void blue() {
    img.blueGrayscale("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Red greyscales an image.
   */
  @Override
  public void red() {
    img.redGrayscale("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Green greyscales an image.
   */
  @Override
  public void green() {
    img.greenGrayscale("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Brightens an image.
   * @param increment the given increment
   */
  @Override
  public void brighten(int increment) {
    img.brighten("Image", "Image", increment);
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);

  }

  /**
   * Darkens an image.
   * @param increment the given increment
   */
  @Override
  public void darken(int increment) {
    img.darken("Image", "Image", increment);
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Horizontally flips an image.
   */
  @Override
  public void flipHorizontal() {
    img.flipHorizontal("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Vertically flips an image.
   */
  @Override
  public void flipVertical() {
    img.flipVertical("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Blurs an image.
   */
  @Override
  public void blur() {
    img.blur("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Greyscales an image.
   */
  @Override
  public void greyscale() {
    img.greyscale("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Intensity an image.
   */
  @Override
  public void intensity() {
    img.intensity("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Luma an image.
   */
  @Override
  public void luma() {
    img.luma("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Sepia an image.
   */
  @Override
  public void sepia() {
    img.sepia("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Sharpens an image.
   */
  @Override
  public void sharpen() {
    img.sharpen("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }

  /**
   * Value an image.
   */
  @Override
  public void value() {
    img.value("Image", "Image");
    ImageOperations image = img.getter("Image");
    gui.updateImage(image);
  }
}
