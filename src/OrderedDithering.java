import java.io.File;

public class OrderedDithering {
	
	static MImage grayImg;
	
	public static void setGrayImg(MImage originalImg, 
			String outputLocation, String imageShortName) {
		
		String grayImgPath = outputLocation + imageShortName + "-gray.ppm";
		File grayFile = new File(grayImgPath);
		
		System.out.println("Checking if gray-scale image "
				+ "already exists " + grayImgPath);
		if (!grayFile.exists()) {
			System.out.println("Gray-scale image does not exist. "
					+ "Generating it.");
			Grayscale.main(originalImg, outputLocation, imageShortName);
		} else {
			System.out.println("Gray-scale image exists.");
		}
		
		grayImg = new MImage(grayImgPath); 		
	}
	
	public static void main(MImage img, String outputLocation, 
			String imageShortName) {
		
		setGrayImg(img, outputLocation, imageShortName);
		
		System.out.println("Applying Ordered Dithering to gray-scale image ...");
		
		int k = 4;
		int[][] D = {{0, 8, 2, 10},
					 {12, 4, 14, 6},
					 {3, 11, 1, 9},
					 {15, 7, 13, 5}};
		
		int w = grayImg.getW();
		int h = grayImg.getH();
		int[] rgb = new int[3];
		
		/* 
		 * Testing: 
		 * checking the pixel with minimum color.
		 * 
		 * checking the maximum possible number of black pixels 
		 * for ordered dithering by comparing to the highest number 
		 * in D matrix which is 15.
		 * 
		 * REMOVETHIS
		 */
		int min = 255; // initialize to highest possible color value
		int minX = 0;
		int minY = 0;
		int maxPossibleBlack = 0;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				grayImg.getPixel(x, y, rgb);
				if (rgb[0] < min) {
					min = rgb[0];
					minX = x;
					minY = y;
				}
				
				if (rgb[0] <= 15) {
					maxPossibleBlack++;
				}
				
			}
		}
		
		System.out.println("\nTESTING:"); 
		System.out.println("Minimum color value = " + min + 
				" at pixel location x = " + minX + " and y = " + minY);
		System.out.println("Max possible number of black pixels "
				+ "after ordered dithering would be = " + maxPossibleBlack);
		System.out.println();
		/* ---- END OF TESTING ---- */
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				
				int i = x % k;
				int j = y % k;
				
				grayImg.getPixel(x, y, rgb);
				
				// Testing darkest pixel ... REMOVETHIS
				if (x == 107 && y == 74) {
					System.out.println("\nTESTING DARKEST PIXEL:"); 
					grayImg.printPixel(x, y);
					System.out.println("i = " + i + ", j = " + j + ", D[i][j] = " + D[i][j]); // REMOVETHIS
				}
				
				if (rgb[0] > D[j][i]) {
					// white // TODO: double check if this should be white or black
					rgb[0] = 255;
					rgb[1] = 255;
					rgb[2] = 255;
					
				} else {
					rgb[0] = 0;
					rgb[1] = 0;
					rgb[2] = 0;
				}
				
				grayImg.setPixel(x, y, rgb);
				
				// Testing darkest pixel ... REMOVETHIS
				if (x == 107 && y == 74) {
					grayImg.printPixel(x, y);
					System.out.println(); 
				}
				
			}
		}
		
		// Save it into another PPM file.
		grayImg.write2PPM(outputLocation + imageShortName + "-OD4.ppm");
	}
}
