class animal{
  String name;
  int xLoc;
  int yLoc;
  
  public animal(String name ,int x, int y, int sx, int sy,PImage i){
    this.name = name;
    this.xLoc = x;
    this.yLoc = y;
    image(i,x,y,sx,sy);
  }

}
