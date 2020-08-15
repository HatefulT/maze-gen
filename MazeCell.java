class MazeCell {
  boolean[] walls;
  boolean visited;

  MazeCell() {
    visited = false;
    walls = new boolean[]{ true, true, true, true }; // top, rigth, bottom, left
  }
}
