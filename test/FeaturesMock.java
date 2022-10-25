import controller.Features;
import model.IImage;

import static controller.ImageUtil.readAll;
import static controller.ImageUtil.readPPM;

/**
 * Mocks the different features in the Features interface.
 */
public class FeaturesMock implements Features {
  private final StringBuilder log;
  private final IImage img;

  public FeaturesMock(StringBuilder log, IImage img) {
    this.log = log;
    this.img = img;
  }

  @Override
  public void load(String filepath) {
    if (filepath.contains("ppm")) {
      img.load(readPPM(filepath), "Image");
    } else if ((filepath.contains("jpg")) || (filepath.contains("png")) ||
            (filepath.contains("bmp"))) {
      img.load(readAll(filepath), "Image");
    }
    log.append(" features load called with filepath " + filepath);
  }

  @Override
  public void save(String name) {
    img.save(name, "Image");
    log.append(" features save called with filepath " + name);

  }

  @Override
  public void blue() {
    img.blueGrayscale("Image", "Image");
    log.append(" features blue called");

  }

  @Override
  public void red() {
    img.redGrayscale("Image", "Image");
    log.append(" features red called");

  }

  @Override
  public void green() {
    img.greenGrayscale("Image", "Image");
    log.append(" features green called");

  }

  @Override
  public void brighten(int increment) {
    img.brighten("Image", "Image", increment);
    log.append(" features brighten called with increment " + increment);

  }

  @Override
  public void darken(int increment) {
    img.darken("Image", "Image", increment);
    log.append(" features darken called with increment " + increment);

  }

  @Override
  public void flipHorizontal() {
    img.flipHorizontal("Image", "Image");
    log.append(" features flip horizontal called");

  }

  @Override
  public void flipVertical() {
    img.flipVertical("Image", "Image");
    log.append(" features flip vertical called");

  }

  @Override
  public void blur() {
    img.blur("Image", "Image");
    log.append(" features blur called");

  }

  @Override
  public void greyscale() {
    img.greyscale("Image", "Image");
    log.append(" features greyscale called");

  }

  @Override
  public void intensity() {
    img.intensity("Image", "Image");
    log.append(" features intensity called");

  }

  @Override
  public void luma() {
    img.luma("Image", "Image");
    log.append(" features luma called");

  }

  @Override
  public void sepia() {
    img.sepia("Image", "Image");
    log.append(" features sepia called");

  }

  @Override
  public void sharpen() {
    img.sharpen("Image", "Image");
    log.append(" features sharpen called");

  }

  @Override
  public void value() {
    img.value("Image", "Image");
    log.append(" features value called");

  }
}
