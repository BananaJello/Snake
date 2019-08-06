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
