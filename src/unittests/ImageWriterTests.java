package unittests;


//import org.junit.Test;
import renderer.ImageWriter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.*;



class ImageWriterTests {
//test to check that image writer works correctly
	
	
	
    @Test
    public void writeToImage() {
        String imagename = "img";
        //int width = 1600;
        //int height = 1000;
        int nx =500;
        int ny =800;
        ImageWriter imageWriter = new ImageWriter(imagename, nx, ny);
     //   assertEquals(5, nx);
        for (int col = 0; col < ny; col++) {
            for (int row = 0; row < nx; row++) {
                if (col % 10 == 0 || row % 10 == 0) {
                    imageWriter.writePixel(row, col,new primitives.Color(Color.blue));
                }
            }
        }
        imageWriter.writeToImage();
    }
}