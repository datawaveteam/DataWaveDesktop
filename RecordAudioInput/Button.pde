class Button {
  
  public int x1, y1, x2, y2;
  public int w = 100, h = 50;
  public color c = color(100, 100, 230), textC = color(255);
  public String text;
  
  public Button(int x1, int y1, String text) {
    this.x1 = x1;
    this.y1 = y1;
    x2 = x1 + abs(w);
    y2 = y1 + abs(h);
    
    this.text = text;
  }
  
  public boolean isHoveredOver() {
    if(mouseX > x1 && mouseX < x2 && mouseY > y1 && mouseY < y2) {
         return true;
    }
    
    return false;
  }
  
  public void draw() {
    // shadow
    noStroke();
    fill(0, 0, 0, 10);
    rect(x1 + 1, y1 + 1, w, h, 2);
    
    // box
    stroke(red(c), green(c), blue(c), 100);
    fill(c);
    if(isHoveredOver()) {
      fill(red(c) - 10, green(c) - 10, blue(c) - 10);
      if(mousePressed) {
        fill(red(c) - 20, green(c) - 20, blue(c) - 20);
      }
    }
    rect(x1, y1, w, h, 2);
    
    // text
    PFont tahoma = createFont("Arial Rounded MT Bold", 16);
    textFont(tahoma);
    textAlign(CENTER);
    fill(textC);
    text(text, x1 + w/2.0, y1 + h/2.0);
  }
}
