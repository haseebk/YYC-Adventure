class hay {
  PVector location;
  PVector velocity;
  PVector gravity;
  void displayHaybale(float xPos, float y) {
    stroke(255);
    strokeWeight(2);
    fill(#DAC586);
    ellipse(xPos, y, 100, 100);
  }
}
