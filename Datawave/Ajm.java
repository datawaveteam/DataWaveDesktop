/**
 * This is a template class and can be used to start a new processing library or
 * tool. Make sure you rename this class as well as the name of the example
 * package 'template' to your own lobrary or tool naming convention.
 *
 * @example Hello
 *
 *          (the tag @example followed by the name of an example included in
 *          folder 'examples' will automatically include the example in the
 *          javadoc.)
 *
 */

import processing.core.*;
import ddf.minim.Minim;

public class Ajm {
  PApplet app;
  Minim minim;

  /**
   * a Constructor, usually called in the setup() method in your sketch to
   * initialize and start the library.
   *
   * @example Hello
   * @param theParent
   */
  public Ajm(PApplet theParent) {
    app = theParent;
    minim = new Minim(app);
  }

  public FSKModem createFSKModem() {
    return new FSKModem(minim, 7350, 4900, 1225, 7350, 4900, 1225);
  }

  public FSKModem createFSKModem(float highFreq, float lowFreq, float bitRate) {
    return new FSKModem(minim, highFreq, lowFreq, bitRate, highFreq,
        lowFreq, bitRate);
  }

  public FSKModem createFSKModem(float highFreq, float lowFreq,
      float bitRate, float highFreq2, float lowFreq2, float bitRate2) {
    return new FSKModem(minim, highFreq, lowFreq, bitRate, highFreq2,
        lowFreq2, bitRate2);
  }

  /**
   * Stops AudioJackModem.
   *
   * A call to this method should be placed inside of the stop() function of
   * your sketch. We expect that implemenations of the AudioJackModem
   * interface made need to do some cleanup, so this is how we tell them it's
   * time.
   *
   */
  public void stop() {
    minim.stop();
  }
}
