class gameSetup {
  PImage playerSprite;
  PImage platform;
  PImage loonie;
  PImage toonie;
  PImage background;
  PImage hay;

  void displayBackground() {
    image(background, 0, 0);
  }
  void displayPlatform() {
    image(platform, -250, height/1.4);
  }
  void displaySprite(float x, float y) {
    image(playerSprite, x, y);
    //image(playerSprite, 150, height/1.5-25);
  }
}
