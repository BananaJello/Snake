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
    dir = int(random(4));
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
        food.x = int(random(24))*20;
        food.y = int(random(24))*20;
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
        food.x = int(random(24))*20;
        food.y = int(random(24))*20;
      }
      
      
    }
    addPiece();
    score ++;
  }
  }
}
