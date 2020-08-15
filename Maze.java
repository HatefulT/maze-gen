import java.util.Random;
import java.util.ArrayList;

class Maze {
  int w;
  int h;
  MazeCell[][] grid;
  int startX;
  int startY;

  Maze(int _w, int _h, int _startX, int _startY) {
    w = _w;
    h = _h;
    grid = new MazeCell[w][h];
    for(int x=0; x<w; x++)
      for(int y=0; y<h; y++)
        grid[x][y] = new MazeCell();
    startX = _startX;
    startY = _startY;
    // endX = _endX;
    // endY = _endY;
  }
  void generate() {
    ArrayList<int[]> stack = new ArrayList<int[]>();

    int currentX = startX;
    int currentY = startY;

    Random prng = new Random();
    while (true) {
      int[] p = new int[]{ (currentY>0 && !grid[currentX][currentY-1].visited) ? 1 : 0,
                           (currentX<w-1 && !grid[currentX+1][currentY].visited) ? 1 : 0,
                           (currentY<h-1 && !grid[currentX][currentY+1].visited) ? 1 : 0,
                           (currentX>0 && !grid[currentX-1][currentY].visited) ? 1 : 0 };
      int sp = p[0] + p[1] + p[2] + p[3];
      if(sp != 0) {
        int r = prng.nextInt(sp);
        int side = -1;
        for(int i=0; i<4; i++) {
          r -= p[i];
          if(r < 0) {
            side = i;
            break;
          }
        }
        grid[currentX][currentY].visited = true;
        stack.add(new int[]{ currentX, currentY });
        if(side == 0) {
          grid[currentX][currentY].walls[0] = false;
          currentY --;
          grid[currentX][currentY].walls[2] = false;
        } else if(side == 1) {
          grid[currentX][currentY].walls[1] = false;
          currentX ++;
          grid[currentX][currentY].walls[3] = false;
        } else if(side == 2) {
          grid[currentX][currentY].walls[2] = false;
          currentY ++;
          grid[currentX][currentY].walls[0] = false;
        } else {
          grid[currentX][currentY].walls[3] = false;
          currentX --;
          grid[currentX][currentY].walls[1] = false;
        }
      } else {
        grid[currentX][currentY].visited = true;
        if(stack.size() == 0) {
          break;
        }
        currentX = stack.get(stack.size()-1)[0];
        currentY = stack.get(stack.size()-1)[1];
        stack.remove(stack.size()-1);
      }
    }
  }
}
