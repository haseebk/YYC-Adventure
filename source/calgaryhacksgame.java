import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import gifAnimation.*; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class calgaryhacksgame extends PApplet {

PImage menuImg;  
PImage start;
PImage exit;
PImage gameChooserBackground;

PFont pixelFont;

int screen = 0;
int startX = 0;
int startY = 0;

boolean wackamoleRunSetup;

//wackamole game start
int wackAMoleHealth, wackAMoleLife, wackAMoleTime, wackAMoleCurrentTime, wackAMolePosition, wackAMoleDiameter, wackAMoleLastTime, wackAMoleLoseHealthTimer, wackAMoleWait = 1000;
PImage wackAMoleBackground, wackAMoleLargeB, wackAMoleMediumB, wackAMoleSmallB, wackAMoleExitButton, wackAMoleMenuButton;
float wackAMoleX, wackAMoleY;
boolean wackAMoleEnd;
SoundFile wackSound;
//wackamole end

//Bull game
gameSetup GS = new gameSetup();
obstacle[] OB = new obstacle[1];
hay haybale = new hay();
boolean quitGame = false;
int score = 0;
float FLOOR = 675, JUMP = 300, SPD = 10;
float dir, x = 300, y = FLOOR;
int hit = 0;
//Bull end

//Game 4 Start
SoundFile fileG;
PImage ani1, ani2, ani3, ani4;
PImage menuG;
int startXG, startYG;
Gif natureG;
int stages;
PImage info;
animal deer, fox, owl, fish;
SoundFile animalNoise;
boolean soundPlayed;
boolean g4RunSetup;

//Game 4 END

//Game yee
PImage backImige, pic1, pic2, pic3, pic4;
PFont Font;
int stX, stY;
PImage menu;
//Game yeeEnd




SoundFile file;

Gif title;
Gif stampede;
Gif calaway;
Gif nature;
Gif FN;

public void setup() {
  
  
  Parksetup(); 
  yeeSetup();
  wackAMoleSetup();
  menuSetup();
}

public void menuSetup() {
  title = new Gif(this, "title.gif");
  title.play();

  menuImg = loadImage("8bit downtown.png");
  menuImg.resize(width, height);
  start = loadImage("start.png");
  exit = loadImage("exit.png");
  calaway = new Gif(this, "calaway.gif");
  calaway.play();
  nature = new Gif(this, "nature.gif");
  nature.play();
  stampede = new Gif(this, "stampede.gif");
  stampede.play();
  FN = new Gif(this, "FN.gif");
  FN.play();
  gameChooserBackground = loadImage("GCB.png");
  gameChooserBackground.resize(width, height);

  menuSetFont();
  startX = (width/2)-(325/2);
  startY = height/2;

  file = new SoundFile(this, "Xenoblade OST - Unfinished Battle.mp3");
  file.play();
}


public void menuSetFont() {
  pixelFont = createFont("Minecraft.ttf", 100);
  textFont(pixelFont);
  imageMode(CORNER);
  rectMode(CORNER);
  textAlign(CENTER);
}

public void backgroundMusicPlaying(){
  if (file.isPlaying()==true)
      file.stop();
  else
      file.play();
}


public void draw() {
  switch (screen) {
  case 0:
    displayMenu();
    break;
  case 1:
    gameChooser();
    break;
  case 2: //wackamole
    file.stop();
    if (wackamoleRunSetup == false) {
      wackAMoleGameSetup();
    }
    if (wackAMoleEnd==false) {
      wackAMoleDraw();
    } else {
      wackAMoleEndScreen();
    }
    break;
  case 3://cowboy runner
    bullDraw();
    break;
  case 4://nature program
    g4Draw();
    break;
  case 5://FN program
    yeeDraw();
    break;
  }
}




public void displayMenu() {
  background(menuImg);

  image(title, width/2-(1733/2), height/7);

  image(start, startX, startY, 325, 150); //start button
  image(start, startX, startY-25, 325, 150);

  image(exit, startX, startY + 200, 325, 150); //exit button
  image(exit, startX, startY + 175, 325, 150); 

  // start button hovering
  if (mouseX> startX && mouseX< startX+325 && mouseY>startY && mouseY<startY+150) {
    background(menuImg);
    image(title, width/2-(1733/2), height/7);
    image(start, startX, startY, 325, 150);
    image(exit, startX, startY + 200, 325, 150);
    image(exit, startX, startY + 175, 325, 150);
  }

  // exit button hovering
  if (mouseX> startX && mouseX< startX+325 && mouseY>startY+200 && mouseY<startY+350) {
    background(menuImg);
    image(title, width/2-(1733/2), height/7);
    image(exit, startX, startY + 200, 325, 150);
    image(start, startX, startY, 325, 150); 
    image(start, startX, startY-25, 325, 150);
  }
}


public void gameChooser() {
  menuSetFont();
  background(gameChooserBackground);

  image(exit, width-265, height-150, 215, 100); //exit button
  image(exit, width-265, height-175, 215, 100); 

  if (mouseX> width-265 && mouseX< width-50 && mouseY>height-175 && mouseY<height-75) {
    background(gameChooserBackground);
    image(exit, width-265, height-150, 215, 100);
  }


  image(stampede, width/1.35f, height/9, 320, 200);
  image(calaway, width/9, height/9, 320, 200);
  image(nature, width/2.35f, height/9, 320, 200);
  image(FN,width/9,height/1.4f,320,200);
  
  
  //FN
  if (mouseX> width/9 && mouseX< width/9+320 && mouseY>height/1.4f && mouseY<height/1.4f+200) {
    background(gameChooserBackground);
    image(FN, width/9, height/1.4f - height/25, 320, 200);
    textAlign(CENTER);


    strokeWeight(10);
    fill(255);
    rect(width/9, height/2.5f, width/1.3f, height/4, 50);
    fill(0xfffc583b);
    text("Explore Calgary First Nations", width/2, height/1.75f);
    image(calaway, width/9, height/9, 320, 200);
    image(nature, width/2.35f, height/9, 320, 200);
    image(exit, width-265, height-150, 215, 100); //exit button
    image(exit, width-265, height-175, 215, 100);
  }


  //stampede
  if (mouseX> width/1.35f && mouseX< width/1.35f+320 && mouseY>height/9 && mouseY<height/9+200) {
    background(gameChooserBackground);
    image(stampede, width/1.35f, height/9 - height/25, 320, 200);
    textAlign(CENTER);


    strokeWeight(10);
    fill(255);
    rect(width/9, height/2, width/1.3f, height/4, 50);
    fill(0xfffc583b);
    text("Play Cowboy Runner", width/2, height/1.5f);
    image(calaway, width/9, height/9, 320, 200);
    image(nature, width/2.35f, height/9, 320, 200);
    image(exit, width-265, height-150, 215, 100); //exit button
    image(exit, width-265, height-175, 215, 100);
  }
  
  //nature
  if (mouseX> width/2.35f && mouseX< width/2.35f+320 && mouseY>height/9 && mouseY<height/9+200) {
    background(gameChooserBackground);
    image(nature, width/2.35f, height/9 - height/25, 320, 200);
    textAlign(CENTER);


    strokeWeight(10);
    fill(255);
    rect(width/9, height/2, width/1.3f, height/4, 50);
    fill(0xfffc583b);
    text("Find Calgary Wildlife", width/2, height/1.5f);
    image(calaway, width/9, height/9, 320, 200);
    image(stampede, width/1.35f, height/9, 320, 200);
    image(exit, width-265, height-150, 215, 100); //exit button
    image(exit, width-265, height-175, 215, 100);
  }

  //calaway
  if (mouseX> width/9 && mouseX< width/9+320 && mouseY>height/9 && mouseY<height/9+200) {
    background(gameChooserBackground);
    image(calaway, width/9, height/9 - height/25, 320, 200);
    textAlign(CENTER);


    strokeWeight(10);
    fill(255);
    rect(width/9, height/2, width/1.3f, height/4, 50);
    fill(0xfffc583b);
    text("Play Calaway Whack-a-Mole", width/2, height/1.5f);
    image(stampede, width/1.35f, height/9, 320, 200);
    image(nature, width/2.35f, height/9, 320, 200);

    image(exit, width-265, height-150, 215, 100); //exit button
    image(exit, width-265, height-175, 215, 100);
  }
}


public void mousePressed() {
  if (screen==0) {
    if (mouseX> startX && mouseX< startX+325 && mouseY>startY && mouseY<startY+150) {
      screen = 1;
    }
    if (mouseX> startX && mouseX< startX+325 && mouseY>startY+200 && mouseY<startY+350) {
      exit();
    }
  } else if (screen == 1) {
    if (mouseX> width/9 && mouseX< width/9+320 && mouseY>height/9 && mouseY<height/9+200)
      screen =2;
      
      if (mouseX> width/2.35f && mouseX< width/2.35f+320 && mouseY>height/9 && mouseY<height/9+200) {
        screen=4;
        file.stop();
        ParkSetUpStart();
        g4RunSetup = true;
      }
      
      if (mouseX> width/9 && mouseX< width/9+320 && mouseY>height/1.4f && mouseY<height/1.4f+200) {
          screen =5;
          file.stop();
          yeeSetupStart();
      }
      if (mouseX> width/1.35f && mouseX< width/1.35f+320 && mouseY>height/9 && mouseY<height/9+200) {
          screen =3;
          file.stop();
          bullSetup();
      }

    if (mouseX> width-265 && mouseX< width-50 && mouseY>height-175 && mouseY<height-75) {
      exit();
    }
  } else if (screen == 2) {
    if (wackAMoleEnd==false) {
      wackAMoleMousePressed();
    } else {
      wackAMoleMousePressedEndScreen();
    }
  } else if (screen == 3) {
    bullMousePressed();
  } else if (screen == 4) {
    game4MousePressed(); 
  } else if (screen == 5) {
    yeeMousePressed();
  }
}

//Game4 start

public void Parksetup (){
  ani1 = loadImage("Deer.png");
  ani2 = loadImage("Fox.png");
  ani3 = loadImage("great-horned.png");
  ani4 = loadImage("trout.png");
  menuG = loadImage("menu.png");
  natureG = new Gif(this,"natureG.gif");
  fileG = new SoundFile(this , "brook.wav");
  g4RunSetup=false;
}

public void ParkSetUpStart() {
  fileG.play();
  startXG = (width/2)-(325/2);
  startYG = height/2;
  stages = 0;
  natureG.play();
  soundPlayed=false;
}

public void g4Draw() {
    image(natureG, 0,0, width, height);
    natureG.play();
    deer = new animal("Deer",width-1500, height-700,200,200, ani1); // dimensions are 200x200
    fox = new animal("Fox",width-380, height-390,100,161, ani2);  // dimensions are 100x161
    owl = new animal("Owl",width-130, height-1050,100,161, ani3); // dimensions are 910x1000
    fish = new animal("Fish", width-1400, height-340,200,161, ani4);
    image(menuG,width-1900,height-150,215,100); //exit button
    image(menuG,width-1900,height-175,215,100);  
    if (stages!=0) {
      displayAnimal();
    }
}

public void displayAnimal(){
  if (soundPlayed==false) {
    if (stages==1){
     animalNoise = new SoundFile(this ,"doegrunt.wav" );
     animalNoise.play();
    }
    else if (stages==2){
     animalNoise = new SoundFile(this ,"The sounds that a Fox makes.mp3" );
     animalNoise.play();
    } else if (stages==3){
     animalNoise = new SoundFile(this ,"Owl Hoot.mp3" );
     animalNoise.play();
    }else if (stages==4){
     animalNoise = new SoundFile(this ,"splashing.mp3" );
     animalNoise.play();
    }
    soundPlayed=true;
  }
  
  if (stages==1){
   info = loadImage("Deer_info.png");
   image(info, deer.xLoc+202, deer.yLoc+12, 300,300);
  }
  else if (stages==2){
   info = loadImage("Fox_info.png");
   image(info, fox.xLoc-340, fox.yLoc-50, 350, 350);
  } else if (stages==3){
   info = loadImage("Owl_info.png");
   image(info, owl.xLoc-340, owl.yLoc-50, 350, 350);
  }else if (stages==4){
   info = loadImage("trout_info.png");
   image(info, fish.xLoc+155, fish.yLoc-50, 350, 350);
  }
}

public void game4MousePressed() {
    if(mouseX> width-1900 && mouseX< width-1700 && mouseY>height-175 && mouseY<height-75){
      g4RunSetup= false;
      fileG.stop();
      file.play();
      screen=1;
    }else{
    Parksetup();
    }
    if ((mouseX >= deer.xLoc && mouseX <=(200+deer.xLoc))&&(mouseY >= deer.yLoc && mouseY <=(200+deer.yLoc))){
      stages=1;
      soundPlayed=false;
    }
    if ((mouseX >= fox.xLoc && mouseX <=(100+fox.xLoc))&&(mouseY >= fox.yLoc && mouseY <=(161+fox.yLoc))){
      stages=2;
      soundPlayed=false;
    }
    if ((mouseX >= owl.xLoc && mouseX <=(200+owl.xLoc))&&(mouseY >= owl.yLoc && mouseY <=(200+owl.yLoc))){
      stages=3;
      soundPlayed=false;
    }
    if ((mouseX >= fish.xLoc && mouseX <=(200+fish.xLoc))&&(mouseY >= fish.yLoc && mouseY <=(200+fish.yLoc))){
      stages=4;
      soundPlayed=false;
    }
}
//Game 4 End





//wackamole START
public void wackAMoleEndScreen() {
  background(255);
  textFont( createFont("Minecraft.ttf", height/10) );

  fill(148, 34, 34);
  textAlign(CENTER);
  text("Game Over!", width/2, height/5);

  textFont( createFont("Minecraft.ttf", height/10) );

  fill(20);
  text("Survial Time = " + wackAMoleCurrentTime+"s", width/2, height/5+height/7);

  text("Time to Click = " +nf(wackAMoleLoseHealthTimer/1000.0f, 0, 2) + "s", width/2, height/5+height/7+height/7);

  imageMode(CENTER);

  image(wackAMoleMenuButton, width/2, height/5+height/7+height/7+height/7); 


  image(wackAMoleExitButton, width/2, height/5+height/7+height/7+height/7+height/5);
}

public void wackAMoleMousePressedEndScreen() {
  //rect (width/2, height/5+height/7+height/7+height/7, width/7, height/6);
  //rect (width/2, height/5+height/7+height/7+height/7+height/5, width/7, height/6);

  //The Menu button
  if (mouseX> width/2-width/14 && mouseX< width/2+width/14 && mouseY> height/5+height/7+height/7+height/7-height/12 && mouseY<height/5+height/7+height/7+height/7+height/12) {
    //EXIT TO MAIN MENU CODE HERE
    screen = 1;
    file.play();
    wackamoleRunSetup = false;
  }

  //The Exit Button
  if (mouseX> width/2-width/14 && mouseX< width/2+width/14 && mouseY> height/5+height/7+height/7+height/7+height/5-height/12 && mouseY<height/5+height/7+height/7+height/7+height/5+height/12) {
    //EXIT BUTTON
    exit();
  }
}

public void wackAMoleSetup() {
  wackAMoleBackground = loadImage("WackAMoleBackground.png"); 
  wackAMoleBackground.resize(width+width/20, height);
  wackAMoleLargeB = loadImage("largeBarrel.png");
  wackAMoleLargeB.resize(width/7, height/4);
  wackAMoleMediumB = loadImage("MediumBarrel.png"); 
  wackAMoleMediumB.resize(width/8, height/4);
  wackAMoleSmallB = loadImage("SmallBarrel.png"); 
  wackAMoleSmallB.resize(width/14, height/5);
  wackAMoleExitButton = loadImage("wackAMoleExitButton.png");
  wackAMoleExitButton.resize(width/6, height/6);
  wackAMoleMenuButton = loadImage("wackAMoleMenuButton.png");
  wackAMoleMenuButton.resize(width/6, height/6);
  wackSound = new SoundFile(this, "WackAMoleWack.mp3");

  wackAMoleGameSetup();
  wackamoleRunSetup = false;
}

public void wackAMoleGameSetup() {
  wackamoleRunSetup = true;
  wackAMoleLoseHealthTimer = 5000;
  textFont( createFont("Minecraft.ttf", height/22) );
  wackAMoleEnd=false;
  setNewRandomPosition();
  wackAMoleLastTime = millis();
  image(wackAMoleBackground, 0, 0); 
  wackAMoleHealth = 10;
  wackAMoleLife=0;
  wackAMoleCurrentTime=0;
  wackAMoleTime=millis();
}

public void wackAMoleDraw() {
  background(255);

  fill(55);
  imageMode(CENTER);

  //Middle
  image(wackAMoleMediumB, width/2, 6.1f*height/7); 

  //top left
  image(wackAMoleSmallB, 5.61f*width/19, 13.25f*height/22);

  //top right
  image(wackAMoleSmallB, 13.34f*width/19, 13.25f*height/22); 

  //bottom left
  image(wackAMoleLargeB, 3*width/13, 8.5f*height/9); 

  //bottom right
  image(wackAMoleLargeB, 10*width/13, 8.5f*height/9); 

  switch (wackAMolePosition) {
  case 0: //Middle
    wackAMoleX= width/2;
    wackAMoleY= 5*height/7;
    image(wackAMoleMediumB, width/2, 5.35f*height/7); 
    break;
  case 1: //top left
    wackAMoleX= 5.63f*width/19;
    wackAMoleY= 11*height/22;
    image(wackAMoleSmallB, 5.61f*width/19, 12.5f*height/22);
    break;
  case 2: //top right
    wackAMoleX= 13.37f*width/19;
    wackAMoleY= 11*height/22;
    image(wackAMoleSmallB, 13.34f*width/19, 12.5f*height/22); 
    break;
  case 3: //bottom left
    wackAMoleX= 3*width/13;
    wackAMoleY= 7*height/9;
    image(wackAMoleLargeB, 3*width/13, 7.4f*height/9); 
    break;
  case 4: //bottom right
    wackAMoleX= 10*width/13;
    wackAMoleY= 7*height/9;
    image(wackAMoleLargeB, 10*width/13, 7.4f*height/9); 
    break;
  }

  // if time to display the circle is over
  if (millis() - wackAMoleLastTime >= wackAMoleLoseHealthTimer) {     
    // create new circle
    setNewRandomPosition();
    wackAMoleLastTime = millis();
    wackAMoleLife--;
  } 

  imageMode(CORNERS);
  image(wackAMoleBackground, 0, height/50); 

  if (millis() - wackAMoleTime >= wackAMoleWait) {
    wackAMoleCurrentTime++;
    if (wackAMoleLoseHealthTimer>=2100) {
      wackAMoleLoseHealthTimer -=100;
    } else if (wackAMoleLoseHealthTimer>=1550) {
      wackAMoleLoseHealthTimer -=50;
    } else if (wackAMoleLoseHealthTimer>=1025) {
      wackAMoleLoseHealthTimer -=25;
    } else if (wackAMoleLoseHealthTimer>=510) {
      wackAMoleLoseHealthTimer -=10;
    }
    wackAMoleTime = millis();//also update the stored time
  }
  // print to screen

  fill(255);
  rectMode(CORNER);
  rect(0, 0, width/6, height/6);

  fill(255);
  rectMode(CENTER);
  rect(width/2, 0, width/3, height/8);

  fill(55);
  textAlign(CENTER);
  text("Time to click = " +nf(wackAMoleLoseHealthTimer/1000.0f, 0, 2) + "s", width/2, height/20);

  textAlign(LEFT);
  text("Time = " + wackAMoleCurrentTime+"s", width/170, height/20);

  text("Score = " + wackAMoleHealth, width/170, height/10);

  text("Life = " + wackAMoleLife, width/170, height/10+height/20);


  imageMode(CENTER);
  image(wackAMoleMenuButton, width-width/12, height/11); 

  if (wackAMoleHealth <= 0) {
    wackAMoleLife--;
    wackAMoleHealth=10;
  }

  if (wackAMoleLife <0) {
    wackAMoleEnd=true;
  }
}

public void wackAMoleMousePressed() {
  //image(wackAMoleExitButton,width-width/12,height/11); 
  //rectMode(CENTER);
  //rect (width-width/12, height/11,width/7,height/6);

  if (mouseX> width-width/12-width/14 && mouseX< width-width/12+width/14 && mouseY> height/11-height/12 && mouseY<height/11+height/12) {
    //EXIT TO MAIN MENU CODE HERE
    file.play();
    screen = 1;
    wackamoleRunSetup = false;
  }

  switch (wackAMolePosition) {
  case 0: //Middle
    wackAMoleDiameter =width/6;
    break;
  case 1: //top left
    wackAMoleDiameter =width/11;
    break;
  case 2: //top right
    wackAMoleDiameter =width/11;
    break;
  case 3: //bottom left
    wackAMoleDiameter =width/6;
    break;
  case 4: //bottom right
    wackAMoleDiameter =width/6;
    break;
  }
  //ellipse(wackAMoleX, wackAMoleY, wackAMoleDiameter, wackAMoleDiameter);

  if (wackAMoleX - wackAMoleDiameter * .5f <= mouseX && mouseX <= wackAMoleX + wackAMoleDiameter * .5f &&
    wackAMoleY - wackAMoleDiameter * .5f <= mouseY && mouseY <= wackAMoleY + wackAMoleDiameter * .5f) {

    wackSound.play();


    if (wackAMolePosition ==1|| wackAMolePosition ==2) {
      wackAMoleHealth += 2;
    } else {
      wackAMoleHealth += 1;
    }

    if (wackAMoleHealth>=20) {
      wackAMoleHealth =10;
      wackAMoleLife ++;
    }
    // create new circle
    setNewRandomPosition() ;
    wackAMoleLastTime = millis();
  } else {
    wackAMoleHealth -=1;
  }
}

public void setNewRandomPosition() {
  //random number between 0-4
  while (true) {
    int temp = (int)random(0, 5);
    if (wackAMolePosition!=temp) {
      wackAMolePosition =temp;
      break;
    }
  }
}
//wackamole END


//yee Start
public void yeeSetup () {
  backImige = loadImage("Glenbow_Museum2.jpg");
  backImige.resize(width, height);
  pic1 = loadImage("art.png");
  pic2 = loadImage("info.png");
  pic3 = loadImage("bison.png");
  pic4 = loadImage("welcome.png");
  menu = loadImage("menu.png");
}

public void yeeSetupStart() {
  stX = (width/2)-(325/2);
  stY = height/2;
  Font = createFont("Arial", 16, true);
  background(backImige);
  textSize(36);
  text("Four Indigenous experiences near Calgary", 10, 90);
}

public void yeeDraw() {
  animal art = new animal("art", width-1800, height-970, 350, 350, pic1); // dimensions are 200x200
  animal information = new animal("info", width-380, height-390, 150, 150, pic2);  // dimensions are 100x161
  animal bison = new animal("bison", width-330, height-750, 300, 161, pic3); // dimensions are 910x1000
  animal welcome = new animal("welcome", width-1150, height-640, 100, 50, pic4);
  if ((mouseX >= art.xLoc && mouseX <=(200+art.xLoc))&&(mouseY >= art.yLoc && mouseY <=(200+art.yLoc))) {
    mouseClicked(art);
  }
  if ((mouseX >= information.xLoc && mouseX <=(100+information.xLoc))&&(mouseY >= information.yLoc && mouseY <=(161+information.yLoc))) {
    mouseClicked(information);
  }
  if ((mouseX >= bison.xLoc && mouseX <=(200+bison.xLoc))&&(mouseY >= bison.yLoc && mouseY <=(200+bison.yLoc))) {
    mouseClicked(bison);
  }
  if ((mouseX >= welcome.xLoc && mouseX <=(200+welcome.xLoc))&&(mouseY >= welcome.yLoc && mouseY <=(200+welcome.yLoc))) {
    mouseClicked(welcome);
  }
  image(menu, width-1900, height-150, 215, 100); //exit button
  image(menu, width-1900, height-175, 215, 100);
}
public void mouseClicked(animal i) {
  PImage img;
  if (mousePressed == true && i.name == "art") {
    img = loadImage("boutique.png");
    image(img, i.xLoc, i.yLoc+350, 450, 450);
  } else if (mousePressed == true && i.name == "info") {
    img = loadImage("Museum.png");
    image(img, i.xLoc-400, i.yLoc-80, 450, 450);
  } else if (mousePressed == true && i.name == "bison") {
    img = loadImage("food.png");
    image(img, i.xLoc-400, i.yLoc-200, 450, 450);
  } else if (mousePressed == true && i.name == "welcome") {
    img = loadImage("teepee.png");
    image(img, i.xLoc-120, i.yLoc+60, 450, 450);
  }
}
public void yeeMousePressed() {
  if (mouseX> width-1900 && mouseX< width-1700 && mouseY>height-175 && mouseY<height-75) {
    screen = 1;
    file.play();
  }
}
//Yee end

//Bull start



public void bullSetup() {
  background(255);
  frameRate(60);
  textAlign(CORNER); 
  for (int i = 0; i < 1; i++)  OB[i] = new obstacle(i);
  haybale.location = new PVector(width, 900);
  haybale.velocity = new PVector(12, 8);
  haybale.gravity = new PVector(0, 0);
  exit = loadImage("exit.png");
  GS.platform = loadImage("platform.png");
  GS.background = loadImage("background.png");
  GS.background.resize(PApplet.parseInt(width*1.55f), 0);
  GS.platform.resize(width + 600, 0);
  GS.playerSprite = loadImage("cowboy 1.png");
}

public void bullDraw() {
  if (dir != 0) move();
  GS.displayBackground();
  textSize(100);
  fill(0);
  text("Score: "+ score, 50, 100); 
  GS.displayPlatform();
  GS.displaySprite(x, y);
  for (int i = 0; i < 1; i++) {
    OB[i].createObstacle();
    OB[i].checkPos();
    OB[i].xPos = haybale.location.x;
  }
  haybale.location.add(haybale.velocity);

  if ((haybale.location.x > width)) {
    haybale.velocity.x = (haybale.velocity.x - score*50) *(-1) ;
  }
  if (haybale.location.x < -50) {
    haybale.location.x = width + 1;
  }

  if (haybale.location.y > 1050) {
    haybale.velocity.y = haybale.velocity.y * -1;
    haybale.location.y = 900;
  }
  if (haybale.location.y < 900) {
    haybale.velocity.y = haybale.velocity.y * -1;
  }
  SPD = 10 + score;
  image(exit, width-265, 90, 215, 100); //exit button
  image(exit, width-265, 115, 215, 100);
  checkCollision();
}


public void keyPressed() {
  if (key == ' ') {
    if (dir == 0) dir = -SPD;
  }
}



public void bullMousePressed() {
  if (mouseX> width-265 && mouseX< width-50 && mouseY>90 && mouseY<200) {
    exit();
  }
  keyPressed();
}


public void move() {

  if ((y += dir) < JUMP) dir*= -1;
  else if ( y > FLOOR) {
    dir = 0;
    y = FLOOR;
  }
}

public void checkCollision() {
  if ((x+20 < OB[0].xPos + 50 && x + 240 > OB[0].xPos - 50) && (y < haybale.location.y && y + 390 > haybale.location.y)) {
    textAlign(CENTER);  
    fill(0);
    text("SCORE RESET", width/2, height/2); 
    textAlign(CORNER);
    score = 0;
  }
}
//Bull end
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
class gameSetup {
  PImage playerSprite;
  PImage platform;
  PImage loonie;
  PImage toonie;
  PImage background;
  PImage hay;

  public void displayBackground() {
    image(background, 0, 0);
  }
  public void displayPlatform() {
    image(platform, -250, height/1.4f);
  }
  public void displaySprite(float x, float y) {
    image(playerSprite, x, y);
    //image(playerSprite, 150, height/1.5-25);
  }
}
class hay {
  PVector location;
  PVector velocity;
  PVector gravity;
  public void displayHaybale(float xPos, float y) {
    stroke(255);
    strokeWeight(2);
    fill(0xffDAC586);
    ellipse(xPos, y, 100, 100);
  }
}
class obstacle {
  float xPos;
  boolean crashed = false;

  obstacle(int i) {
    xPos = width + (i*200);
  }
  public void createObstacle() {
    haybale.displayHaybale(xPos, haybale.location.y);
  }
  public void checkPos() {
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
  public void settings() {  fullScreen();  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "calgaryhacksgame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
