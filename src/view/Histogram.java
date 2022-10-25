package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.geom.AffineTransform;
import java.util.Map;


import javax.swing.JPanel;

import model.ImageOperations;


/**
 * Creates a histogram for the view.
 */
public class Histogram extends JPanel {
  private ImageOperations im;

  public Histogram(ImageOperations im) {
    this.im = im;
  }



  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setVisible(true);
    g.create();

    Graphics2D g2 = (Graphics2D) g;
    AffineTransform newTransform = g2.getTransform();
    g2.translate(0, this.getHeight());
    g2.scale(1, -1);

    //number of pixels
    int maxHeight = Math.max(Math.max(im.largestNumber(im.redPixels()),
            im.largestNumber(im.bluePixels())), im.largestNumber(im.greenPixels()));

    for (Map.Entry<Integer, Integer> element : im.redPixels().entrySet()) {
      g2.setColor(Color.RED);
      double yValue = (double) this.getHeight() / maxHeight;
      double xValue = (double) this.getWidth() / 256.0;
      g2.fillRect((int) xValue * element.getKey(), 0, (int) xValue, (int)
              (element.getValue() * yValue));
    }

    for (Map.Entry<Integer, Integer> element : im.greenPixels().entrySet()) {
      g2.setColor(Color.GREEN);
      double yValue = (double) this.getHeight() / maxHeight;
      double xValue = (double) this.getWidth() / 256.0;
      g2.fillRect((int) xValue * element.getKey(), 0, (int) xValue, (int)
              (element.getValue() * yValue));
    }


    for (Map.Entry<Integer, Integer> element : im.bluePixels().entrySet()) {
      g2.setColor(Color.BLUE);
      double yValue = (double) this.getHeight() / maxHeight;
      double xValue = (double) this.getWidth() / 256.0;
      g2.fillRect((int) xValue * element.getKey(), 0, (int) xValue, (int)
              (element.getValue() * yValue));
    }

    for (Map.Entry<Integer, Integer> element : im.intensityPixels().entrySet()) {
      g2.setColor(Color.GRAY);
      double yValue = (double) this.getHeight() / maxHeight;
      double xValue = (double) this.getWidth() / 256.0;
      g2.fillRect((int) xValue * element.getKey(), 0, (int) xValue, (int)
              (element.getValue() * yValue));
    }

    g2.setTransform(newTransform);
  }
}





