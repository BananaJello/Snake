snakeHead snakeHead = new snakeHead();
food food = new food();
static int score;
void setup(){
  size(480, 480);
  background(0);
  score = 0;
}
void draw(){
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
void keyPressed() {
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
