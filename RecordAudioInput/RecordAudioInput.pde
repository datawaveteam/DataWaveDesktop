/* 
   using the LiveInput UGen to patch 
   the audio input from your computer (usually the microphone) to the output.
*/

import ddf.minim.*;
import ddf.minim.ugens.*;
import ddf.minim.spi.*; // for AudioStream
import ddf.minim.effects.*; // for low pass filter

final String ENCODER_DATA = "It worked oh my gosh! My whole life, from conception to birth to death has its climax at this very moment. Thank you programming gods.";

Minim minim;
AudioOutput out;
LiveInput in;
HighPassSP hpf;

Ajm ajm;
FSKModem fskModem;

void setup()
{
  size(512, 200);
  minim = new Minim(this);
  out = minim.getLineOut();
  
  ajm = new Ajm(this);
  fskModem = ajm.createFSKModem(7350, 4900, 1225);
  
  fskModem.write(ENCODER_DATA);
  
  // we ask for an input with the same audio properties as the output.
  //AudioStream inputStream = minim.getInputStream( out.getFormat().getChannels(), 
  //                                                out.bufferSize(), 
  //                                                out.sampleRate(), 
  //                                                out.getFormat().getSampleSizeInBits());
                                                 
  // construct a LiveInput by giving it an InputStream from minim.                                                  
  //in = new LiveInput( inputStream );
  
  // create granulate UGen so we can hear the input being modfied before it goes to the output
  // GranulateSteady grain = new GranulateSteady();
  
  // make a low pass filter with a cutoff frequency of 100 Hz
  // the second argument is the sample rate of the audio that will be filtered
  // it is required to correctly compute values used by the filter
  // hpf = new HighPassSP(1000, in.sampleRate());
  
  // // patch the input through the grain effect to the output
  //in.patch(grain).patch(out);
  
  // patch the input through the low pass filter to the output
  // in.patch(hpf).patch(out);
  
  //in.patch(out);
}

// draw is run many times
void draw()
{
  // erase the window to black
  background( 0 );
  // draw using a white stroke
  stroke( 255 );
  // draw the waveforms
  /*for( int i = 0; i < out.bufferSize() - 1; i++ )
  {
    // find the x position of each buffer value
    float x1  =  map( i, 0, out.bufferSize(), 0, width );
    float x2  =  map( i+1, 0, out.bufferSize(), 0, width );
    // draw a line from one buffer position to the next for both channels
    line( x1, 50 + out.left.get(i)*50, x2, 50 + out.left.get(i+1)*50);
    line( x1, 150 + out.right.get(i)*50, x2, 150 + out.right.get(i+1)*50);
    
    System.out.println(out.left.get(i));
  }*/
}

void mouseMoved()
{
  // map the mouse position to the range [1000, 14000], an arbitrary range of cutoff frequencies
  // float cutoff = map(mouseX, 0, width, 1000, 14000);
  // hpf.setFreq(cutoff);
}
