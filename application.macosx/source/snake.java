import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class snake extends PApplet {

snakeHead snakeHead = new snakeHead();
food food = new food();
static int score;
public void setup(){
  
  background(0);
  score = 0;
}
public void draw(){
  if (snakeHead.alive){
    snakeHead.update();
    food.display();
    fill(255,255,255);
    textSize(20);
    text(score,0,20);
  }else{
    noLoop();
  }
}
public void keyPressed() {
  if (snakeHead.keyInputs){
  if(keyCode == UP && snakeHead.dir != 2){
    snakeHead.dir = 0;
    snakeHead.keyInputs = false;
  }
  if(keyCode == RIGHT && snakeHead.dir != 3){
    snakeHead.dir = 1;
    snakeHead.keyInputs = false;
  }
  if(keyCode == DOWN && snakeHead.dir != 0){
    snakeHead.dir = 2;
    snakeHead.keyInputs = false;
  }
  if(keyCode == LEFT && snakeHead.dir != 1){
    snakeHead.dir = 3;
    snakeHead.keyInputs = false;
  }
  }
}
class food{
  int x,y;
  public food(){
    x = PApplet.parseInt(random(24))*20;
    y = PApplet.parseInt(random(24))*20;
    while( x == 240 && y == 240){
      x = PApplet.parseInt(random(24))*20;
      y = PApplet.parseInt(random(24))*20;
    }
  }
  public void display(){
    fill(255,0,0);
    rect(x,y,20,20);
  }
}
class snakeBody{
  int x,y,px,py;
  snakeBody nextPiece;
  public snakeBody(int x, int y){
    this.x = x;
    this.y = y;
    nextPiece = null;
  }
  public void display(int x, int y){
    px =this.x;
    py =this.y;
    this.x = x;
    this.y = y;
    fill(0,255,0);
    rect(this.x,this.y,20,20);
    if (nextPiece != null){
      nextPiece.display(px,py);
    }
  }
}
class snakeHead{
  int x,y,dir,timer,px,py;
  boolean alive, keyInputs;
  snakeBody piece;
  public snakeHead(){
    keyInputs = true;
    piece = null;
    x = 240;
    y = 240;
    timer = 0;
    dir = PApplet.parseInt(random(4));
    alive = true;
  }
  public void display(){
    fill(0,0,255);
    rect(x,y,20,20);
    if (piece != null){
      piece.display(px,py);
    }
  }
  public void addPiece(){   
    if (piece != null){
      snakeBody currentPiece = piece;
      while (currentPiece.nextPiece != null){
        currentPiece = currentPiece.nextPiece;
      }
      currentPiece.nextPiece = new snakeBody(currentPiece.px,currentPiece.py);
    }else{
      piece = new snakeBody(px,py);
    }
  }
  public boolean died(){
    snakeBody currentPiece = piece;
    while (currentPiece.nextPiece != null){
      if (currentPiece.x == this.x && currentPiece.y == this.y){
      return true;
    }
      currentPiece = currentPiece.nextPiece;
    }
    return false;
  }
  public void update(){
    timer ++;
    if (timer >= 10){
      keyInputs = true;
      background(0);
      display();
      px = x;
      py = y;
      switch(dir){
        case 0: 
          y-=20;
          break;
        case 1: 
          x+=20;
          break;
        case 2: 
          y+=20;
          break;
        case 3: 
          x-=20;
          break;
      }
      timer = 0;
    }
    if (x < 0 || x > 480 || y < 0 || y > 480){
      alive = false;
    }
    if (piece != null && died()){
      alive = false;
    }
    if (x == food.x && y == food.y){
      while( food.x == x && food.y == y){
        food.x = PApplet.parseInt(random(24))*20;
        food.y = PApplet.parseInt(random(24))*20;
      }
      if (piece != null){
      snakeBody currentPiece = piece;
      ArrayList<Integer> xs = new ArrayList<Integer>();
      ArrayList<Integer> ys = new ArrayList<Integer>();
      while (currentPiece.nextPiece != null){
        xs.add(currentPiece.x);
        ys.add(currentPiece.y);
        currentPiece = currentPiece.nextPiece;
      }
      while (xs.contains(food.x) && ys.contains(food.y)){
        food.x = PApplet.parseInt(random(24))*20;
        food.y = PApplet.parseInt(random(24))*20;
      }
      
      
    }
    addPiece();
    score ++;
  }
  }
}
  public void settings() {  size(480, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "snake" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
