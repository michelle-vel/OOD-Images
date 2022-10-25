package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import java.awt.FlowLayout;
import java.awt.Dimension;


/**
 * Creates a popup button for errors.
 */
public class PopUpButton extends JFrame {
  private final JButton panel;
  private Popup popNew;
  private final JFrame frame;

  /**
   * Constructs a popup button.
   */
  public PopUpButton() {
    frame = new JFrame();
    panel = new JButton("error, try again");
    setLayout(new FlowLayout());
    frame.setSize(new Dimension(300, 100));
    frame.add(panel);

    panel.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        if (popNew != null) {
          frame.setVisible(true);

          popNew.hide();
        }
      }

    });


    panel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent argument) {
        if (popNew != null) {
          popNew.hide();
          popNew = null;

          frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        }
        popNew = PopupFactory.getSharedInstance().getPopup(PopUpButton.this, panel, 300, 300);
        popNew.show();
        panel.requestFocus();
      }
    });
  }
}
