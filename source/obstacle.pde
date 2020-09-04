class obstacle {
  float xPos;
  boolean crashed = false;

  obstacle(int i) {
    xPos = width + (i*200);
  }
  void createObstacle() {
    haybale.displayHaybale(xPos, haybale.location.y);
  }
  void checkPos() {
    if (xPos<0) {
      xPos+=width;
      crashed = false;
    }
    if (xPos<200 && crashed == false) {
      crashed = true;
      score++;
    }
  }
}
