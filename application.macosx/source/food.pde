class food{
  int x,y;
  public food(){
    x = int(random(24))*20;
    y = int(random(24))*20;
    while( x == 240 && y == 240){
      x = int(random(24))*20;
      y = int(random(24))*20;
    }
  }
  public void display(){
    fill(255,0,0);
    rect(x,y,20,20);
  }
}
