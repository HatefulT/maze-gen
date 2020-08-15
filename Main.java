import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.File;

class Main {
  public static void main(String[] args) {
    int W = 100;
    long t1 = System.currentTimeMillis();

    Maze maze = new Maze(W, W, 0, 0);
    maze.generate();

    long t2 = System.currentTimeMillis();
    System.out.println("Done in: " + (t2-t1));

    double[][][] image = new double[3][3*W][3*W];
    for(int x=0; x<W; x++)
      for(int y=0; y<W; y++) {
        for(int i=0; i<3; i++) {
          image[i][3*x+1][3*y+1] = 0;
          if(maze.grid[x][y].walls[0]) {
            image[i][3*x-1+1][3*y-1+1] = 1.;
            image[i][3*x  +1][3*y-1+1] = 1.;
            image[i][3*x+1+1][3*y-1+1] = 1.;
          }
          if(maze.grid[x][y].walls[1]) {
            image[i][3*x+1+1][3*y-1+1] = 1.;
            image[i][3*x+1+1][3*y  +1] = 1.;
            image[i][3*x+1+1][3*y+1+1] = 1.;
          }
          if(maze.grid[x][y].walls[2]) {
            image[i][3*x-1+1][3*y+1+1] = 1.;
            image[i][3*x  +1][3*y+1+1] = 1.;
            image[i][3*x+1+1][3*y+1+1] = 1.;
          }
          if(maze.grid[x][y].walls[3]) {
            image[i][3*x-1+1][3*y-1+1] = 1.;
            image[i][3*x-1+1][3*y  +1] = 1.;
            image[i][3*x-1+1][3*y+1+1] = 1.;
          }
        }
      }
    saveImage(image, "output.png");
  }
  public static void saveImage(double[][][] a, String filename) {
    BufferedImage image = new BufferedImage(a[0].length, a[0][0].length, BufferedImage.TYPE_INT_RGB);
    for (int x=0; x<a[0].length; x++) {
      for (int y=0; y<a[0][0].length; y++) {
        int r = (int) (a[0][x][y]*255);
        int g = (int) (a[1][x][y]*255);
        int b = (int) (a[2][x][y]*255);
        image.setRGB(x, y, (r << 16) + (g << 8) + b );
      }
    }

    File f = new File(filename);
    try {
      ImageIO.write(image, "png", f);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
