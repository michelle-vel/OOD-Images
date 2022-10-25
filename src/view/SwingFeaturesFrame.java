package view;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.JFrame;
import javax.swing.BorderFactory;

import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Color;


import controller.Features;
import model.ImageOperations;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Creates a Swing view for the image.
 */
public class SwingFeaturesFrame extends JFrame implements GUIView {
  private final JPanel bottomPanel;
  private final JPanel imagePanel;
  private final JLabel imageLabel;
  private final JButton blueButton;
  private final JButton blurButton;
  private final JButton brightenButton;
  private final JButton darkenButton;
  private final JButton flipHButton;
  private final JButton flipVButton;
  private final JButton greenButton;
  private final JButton greyscaleButton;
  private final JLabel fileOpenDisplay;
  private final JLabel fileSaveDisplay;
  private final JButton intensityButton;
  private final JButton fileOpenButton;
  private final JButton lumaButton;
  private final JButton redButton;
  private final JButton fileSaveButton;
  private final JButton sepiaButton;
  private final JButton sharpenButton;
  private final JButton valueButton;
  private final JTextField brightenInt;
  private final JTextField darkenInt;
  private Popup p;

  /**
   * Constructs a swing view.
   */
  public SwingFeaturesFrame() {
    super();
    setTitle("Swing features");
    setSize(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    imageLabel = new JLabel();
    JScrollPane imageScroll = new JScrollPane(imageLabel);
    imageScroll.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScroll);


    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    bottomPanel = new JPanel();
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);

    //make button a field
    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    JPanel blue = new JPanel();
    blue.setLayout(new FlowLayout());
    dialogBoxesPanel.add(blue);
    blueButton = new JButton("blue component an image");
    blueButton.setActionCommand("Blue");
    blue.add(blueButton);

    JPanel blur = new JPanel();
    blur.setLayout(new FlowLayout());
    dialogBoxesPanel.add(blur);
    blurButton = new JButton("blur an image");
    blurButton.setActionCommand("Blur");
    blur.add(blurButton);


    brightenInt = new JTextField(5);
    JPanel brighten = new JPanel();
    brighten.setLayout(new FlowLayout());
    dialogBoxesPanel.add(brighten);
    brightenButton = new JButton("brighten an image");
    brightenButton.setActionCommand("Brighten");
    brighten.add(brightenButton);
    brighten.add(brightenInt);


    darkenInt = new JTextField(5);
    JPanel darken = new JPanel();
    darken.setLayout(new FlowLayout());
    dialogBoxesPanel.add(darken);
    darkenButton = new JButton("darken an image");
    darkenButton.setActionCommand("Darken");
    darken.add(darkenButton);
    darken.add(darkenInt);


    JPanel flipH = new JPanel();
    flipH.setLayout(new FlowLayout());
    dialogBoxesPanel.add(flipH);
    flipHButton = new JButton("horizontally flip an image");
    flipHButton.setActionCommand("Horizontally flip");
    flipH.add(flipHButton);

    JPanel flipV = new JPanel();
    flipV.setLayout(new FlowLayout());
    dialogBoxesPanel.add(flipV);
    flipVButton = new JButton("vertically flip an image");
    flipVButton.setActionCommand("Vertically flip");
    flipV.add(flipVButton);

    JPanel green = new JPanel();
    green.setLayout(new FlowLayout());
    dialogBoxesPanel.add(green);
    greenButton = new JButton("green component an image");
    greenButton.setActionCommand("Green");
    green.add(greenButton);

    JPanel greyscale = new JPanel();
    greyscale.setLayout(new FlowLayout());
    dialogBoxesPanel.add(greyscale);
    greyscaleButton = new JButton("greyscale an image");
    greyscaleButton.setActionCommand("Greyscale");
    greyscale.add(greyscaleButton);

    JPanel intensity = new JPanel();
    intensity.setLayout(new FlowLayout());
    dialogBoxesPanel.add(intensity);
    intensityButton = new JButton("intensity component an image");
    intensityButton.setActionCommand("intensity");
    intensity.add(intensityButton);

    JPanel luma = new JPanel();
    luma.setLayout(new FlowLayout());
    dialogBoxesPanel.add(luma);
    lumaButton = new JButton("luma component an image");
    lumaButton.setActionCommand("luma");
    luma.add(lumaButton);

    JPanel red = new JPanel();
    red.setLayout(new FlowLayout());
    dialogBoxesPanel.add(red);
    redButton = new JButton("red component an image");
    redButton.setActionCommand("red");
    red.add(redButton);

    JPanel sepia = new JPanel();
    sepia.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sepia);
    sepiaButton = new JButton("sepia an image");
    sepiaButton.setActionCommand("sepia");
    sepia.add(sepiaButton);

    JPanel sharpen = new JPanel();
    sharpen.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sharpen);
    sharpenButton = new JButton("sharpen an image");
    sharpenButton.setActionCommand("sharpen");
    sharpen.add(sharpenButton);

    JPanel value = new JPanel();
    value.setLayout(new FlowLayout());
    dialogBoxesPanel.add(value);
    valueButton = new JButton("value an image");
    valueButton.setActionCommand("value");
    value.add(valueButton);

    setVisible(true);
  }

  /**
   * Saves the file name.
   *
   * @return string of the file name.
   */
  public String file() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.showOpenDialog(SwingFeaturesFrame.this);
    File f = fchooser.getSelectedFile();
    try {
      fileOpenDisplay.setText(f.getAbsolutePath());
      p.hide();
    } catch (NullPointerException e) {
      PopUpButton pop = new PopUpButton();
      pop.setVisible(true);
    }
    return f.getAbsolutePath();
  }

  /**
   * Adds the features to manipulate the image to the GUI.
   *
   * @param features the given features interface
   */
  public void addFeatures(Features features) {
    blueButton.addActionListener(evt -> features.blue());
    blurButton.addActionListener(evt -> features.blur());
    brightenButton.addActionListener(evt ->
            features.brighten(Integer.parseInt(brightenInt.getText())));
    darkenButton.addActionListener(evt ->
            features.darken(Integer.parseInt(darkenInt.getText())));
    flipHButton.addActionListener(evt -> features.flipHorizontal());
    flipVButton.addActionListener(evt -> features.flipVertical());
    greenButton.addActionListener(evt -> features.green());
    greyscaleButton.addActionListener(evt -> features.greyscale());
    intensityButton.addActionListener(evt -> features.intensity());
    fileOpenButton.addActionListener(evt -> {
      String name = this.file();
      features.load(name);
    });

    lumaButton.addActionListener(evt -> features.luma());
    redButton.addActionListener(evt -> features.red());
    fileSaveButton.addActionListener(evt -> {
      String filepath = this.saveFile();
      features.save(filepath);
    });
    sepiaButton.addActionListener(evt -> features.sepia());
    sharpenButton.addActionListener(evt -> features.sharpen());
    valueButton.addActionListener(evt -> features.value());
  }

  /**
   * Updates the image after a change has been made.
   *
   * @param im the given image
   */
  public void updateImage(ImageOperations im) {
    this.clearHisto(im);
    Image newImg = makeImage(im).getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(),
            Image.SCALE_SMOOTH);
    this.repaint();
    ImageIcon image = new ImageIcon(newImg);
    JLabel imageL = new JLabel();
    imageL.setIcon(image);
    imageLabel.setIcon(image);

    Histogram histo = new Histogram(im);
    histo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    histo.setPreferredSize(new Dimension(300, 300));
    bottomPanel.add(histo);
    histo.updateUI();
  }


  /**
   * Clears the histogram so a new one can take its place.
   *
   * @param im the given image
   */
  private void clearHisto(ImageOperations im) {
    bottomPanel.removeAll();
    this.repaint();

  }

  /**
   * Saves the file.
   *
   * @return string of the file name.
   */
  public String saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
    String file = "";
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileSaveDisplay.setText(f.getAbsolutePath());
      file = f.getAbsolutePath();
    }
    System.out.println(file);
    return file;
  }

  /**
   * Converts to an image.
   *
   * @param image the given image
   * @return a new BufferedImage
   */
  public Image makeImage(ImageOperations image) {
    BufferedImage newImage = new BufferedImage(image.returnColSize(),
            image.returnRowSize(), TYPE_INT_RGB);
    for (int row = 0; row < image.returnRowSize(); row++) {
      for (int col = 0; col < image.returnColSize(); col++) {
        newImage.setRGB(col, row, image.currentColor(row, col).getRGB());
      }
    }
    return newImage;

  }
}
