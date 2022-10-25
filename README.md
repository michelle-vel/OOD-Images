# OOD-Images

Image Processor Overview

CONTROLLER --------------------------------------------------------------------------
Interface ImageController:
This interface represents a controller for the Image Processing Application.
It is responsible for handling all inputs and exchanging this information with the
model and view. It asks the user for specific command inputs and determines when to
run and exit the application.

Class ImageControllerImpl:
This class implements ImageController. It takes in a IImage model, ImageView,
and a Readable object for its input. On drawImage(), this controller reads the input from
the Readable, determines if the input is a valid command, and if it is, executes
the corresponding command class.

Class ImageProgram:
This class is to allow for user to run the ImageProcessor using the console.

Class ImageUtil:
This class contains utility methods to read a PPM image and output it as a 2-d array of pixels.

MODEL: ------------------------------------------------------------------------------
Package Commands:
Interface ICommand: This interface consists of a go method which executes a
specific command based on the user's desire. It also includes an outputMessage method
which describes the action that was performed by the execute method.
> 13 Classes that implement ICommand:
flipHorizontal: This class is in change of flipping an image horizontally.
flipVertical: This class is in change of flipping an image vertically.
brighten: This class is in charge of brightening a certain image by amount user inputs.
darken: This class is in charge of darkening a certain image by amount user inputs.
red: This class is in charge of converting an image into a red greyscale image.
green:  This class is in charge of converting an image into a green greyscale image.
blue:  This class is in charge of converting an image into a blue greyscale image.
value: This class is in charge of converting an image so each pixel is the maximum value of
the three components.
intensity: This class is in charge of converting an image so each pixel is the average of
the three components for each pixel
luma: This class is in charge of converting an image so each pixel is the luma value.
save: This class is in charge of saving an image and outputting it in PPM format to a
given filename that the user chooses.
load: This class is in charge of loading an image of the user's liking as a PPM form with
a given filename that the user chooses.

Interface IImage: This interface contains all the operations the Image Processor can
perform on an image.

Class Image: This class represents an image and its data and creates a IImage with a given
width, height, max Color value, and list of all the RGB pixels that make up this image. It
contains methods that apply the commands to each pixel in the image, and essentially mutates
the entire Picture based on the specific command given.

Interface ImageImpl: This interface represents an image that an ImageProcessor model can store.
Represents the new images after a command is performed.

Class ImageModelClass: Stores each image with a new name into a hashmap.

Class Pixel: This class represents a single pixel of an image that has a Red, Green, and
Blue color values. The methods within this class are able to manipulate the individual pixels
based on the specified commands.

VIEW: -------------------------------------------------------------------------------
Interface ImageView: This interface represents the view for an ImageProcessor.
The view can display messages to an Appendable notifying user of any changes made.

Class ImageViewRender: This class renders a message and sends it to the Appendable.
Whatever message needs to be sent to the user is done in the renderMessage method.

SCRIPT OF COMMANDS CONTROLLER WILL ACCEPT: -------------------------------------------
              load image-path image-name
              red-component image-name dest-image-name
              blue-component image-name dest-image-name
              green-component image-name dest-image-name
              value image-name dest-image-name
              luma image-name dest-image-name
              sepia image-name dest-image-name
              greyscale image-name dest-image-name
              intensity image-name dest-image-name
              horizontal-flip image-name dest-image-name
              vertical-flip image-name dest-image-name
              brighten increment image-name dest-image-name
              darken increment image-name dest-image-name
              sepia image-name dest-image-name
              greyscale image-name dest-image-name
              sharpen image-name dest-image-name
              blur image-name dest-image-name
              save image-path image-name
              quit to quit program


HW5 -----------------------------------------------------------------------------------
HW4 CODE CHANGES >>>
- Updated Javadocs for all public methods, classes, constructors
- Add invariants to fields
- Modified ImageView by combining quitMessage, errorMessage, etc. into a general renderMessage.
  renderMessage can display any message assigned to it, removing duplicate code.


HW5 CODE ADDITIONS >>>

Controller Edits:
- Modified ImageProgram to take in a text file as a command line argument like so:
  "-file file-path.txt"
- Added readAll() method in ImageUtil Class that can read all other file types (png, bmp, jpg).
- Modified load command to readPPM or readAll depending on the text file supplied to it (ex. .png)
- In ImageControllerImpl class added cases for new commands: blur, sharpen, sepia, greyscale.

Model Edits:
- In IImage Interface, added blurAndSharpen(String message) and greyscaleAndSepia(String message).
  These methods return an image and manipulate the arrays of pixels to apply a filter.
- In commands, added command class Blur, Sharpen, Sepia, and Greyscale.

View Edits:
- Modified ImageView Interface by combining quitMessage, errorMessage, etc. into a general renderMessage.
  renderMessage can display any message assigned to it, removing duplicate code.
- Added new possible commands to introMessage().
  New Intro Message:
              possible commands
              load image-path image-name
              red-component image-name dest-image-name
              blue-component image-name dest-image-name
              green-component image-name dest-image-name
              value image-name dest-image-name
              luma image-name dest-image-name
              sepia image-name dest-image-name
              greyscale image-name dest-image-name
              intensity image-name dest-image-name
              horizontal-flip image-name dest-image-name
              vertical-flip image-name dest-image-name
              brighten increment image-name dest-image-name
              darken increment image-name dest-image-name
              sepia image-name dest-image-name
              greyscale image-name dest-image-name
              sharpen image-name dest-image-name
              blur image-name dest-image-name
              save image-path image-name
              quit to quit program


TESTING >>>
- To test, we created our own 3x3 pixel image that is an original creation with personal permission
  to use for this assignment. We manually manipulated the images in Gimp, and read these
  into our tests to compare them to the mutated objects. We overrode equals and hashcode with our
  own implementation that checked all fields of an PPM image were equal.

(test/ControllerTest)
- To test the controller we used a MockView and MockModel that updated a log.
  This helped test that the controller properly read in data and transmitted it to the view
  and model.

(test/ SaveLoadTests)
- To test save and load, we used a MockModel that updated a log. We loaded the image and
  checked if the messages in the log displayed accordingly. We the saved the loaded image
  and checked if the messages displayed accordingly to test save.

(test/ImagesTest)
- To test the two read methods in ImageUtil we compared the read pixel values to the string
  representation of our image. Since we made our image, we know what the correct pixel values
  should be.

(test/ViewTest)
- To test view, we called renderMessage() and introMessage() on a view object and compared the
  messages.


HW5 COMPLETE PROGRAMS (All complete) >>>
- Main method supports ability to take in a text file or accept a text entry from the user.
- Load and save conventional file formats (bmp, jpg and png) in addition to ASCII ppm files
  from before.
- (Retain) Load the save images in formats using the same script command as before.
- Able to blur and sharpen an image, keeping in mind that other filtering operations
  may be possible in future.
- Able to produce a grayscale and sepia version of an image using color transformations,
  keeping in mind that other color transformations may be possible in future.
- Able to specify the above operations using script commands: blur, sharpen,
  greyscale, sepia with the same set of parameters
- Able to accept a script file as a command-line option. For example
  "-file name-of-script.txt". If a valid file is provided, the program should run the script
  and exit. If the program is run without any command line options, then it should allow interactive
  entry of script commands as before.
- Able to switch between PPM and conventional file formats (bmp, jpg and png
- Retain support for all previous operations and script commands.



HW6 --------------------------------------------------------------------------------------------


HW5 CODE CHANGES >>>
- No design changes
- Added invariants to fields


HW6 CODE ADDITIONS >>>
Controller Edits:
- Added ViewProgram controller that can run the program with a GUI view.
- Added a Features interface that different features objects can implement. These represent
  all the abilities of the controller paired with the GUI view. The controller takes in a features
  object which is responsible for delegating information to the model and view for each necessary
  method.
- Modified ImageControllerImpl class to work with the feature interface design and GUIView.

Model Edits:
- Added MaskImage class that
- Modified interface ImageOperations with hashmaps to store red, green, and intensity values
  of images for histogram.
- Modified class Image implementing hashmaps to store red, green, and intensity values and max
  value of images for histogram.

View Edits:
- Added class SwingFeatures that creates the frame or window of the GUI and catches exceptions.
- Added class SwingFeaturesFrame that extends JFrame. This class implements GUIView and displays
  the GUI with functionality.
- Added class PopUpButton that pops up and displays error messages when the program receives an
  invalid input.
- Added interface GUIView.
- Added class histogram that draws the histogram at the bottom of the page.


Res/ Folder:
- Added GUI modified images (manhattan-small).


HW6 COMPLETE PROGRAMS (All complete) >>>
- Main method supports ability to take in a text file or accept a text entry from the user,
  or ViewProgram runs a GUI.
- Load and save conventional file formats (bmp, jpg, png, and ppm).
- Able to blur and sharpen an image, keeping in mind that other filtering operations
  may be possible in future.
- Able to produce a grayscale and sepia version of an image using color transformations,
  keeping in mind that other color transformations may be possible in future.
- Able to specify the above operations using script commands: blur, sharpen,
  greyscale, sepia with the same set of parameters
- Able to accept a script file as a command-line option. For example
  "-file name-of-script.txt". If a valid file is provided, the program should run the script
  and exit. If the program is run without any command line options, then it should allow interactive
  entry of script commands as before.
- Able to switch between PPM and conventional file formats (bmp, jpg, png).
- Uses Java Swing to build a GUI that displays images according to user modification.
- Displays a histogram as a line graph at the bottom of the screen at all times. Automatically
  refreshes when an image is manipulated and displays red, green, blue and intensity components.
- Expose all the required features of the program (flips, component visualization, greyscale,
  blurring, sharpening and sepia).
- When saving an image as a PNG/PPM/JPG,saves what the user is currently seeing.
- The user is able to specify suitably the image to be loaded and saved that the user is going to
  process.
- Error conditions are displayed to the user through pop-up messages.


SEE USEME ON INSTRUCTIONS TO RUN PROGRAM >>>
