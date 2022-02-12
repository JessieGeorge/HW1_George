import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*******************************************************
 * CS4551 Multimedia Software Systems @ Author: Elaine Kang
 * 
 * 
 * Template Code - demonstrate how to use MImage class
 *******************************************************/

public class CS4551_George {
	
	public static String getImageShortName(MImage img) {
		// remove .ppm from image file name
		String origName = img.getName();
		int len = origName.length();
		String shortName = origName.substring(0, len-4);
		return shortName;
	}
	
	public static void menu(MImage img) throws IOException {
		
		String currentDir = System.getProperty("user.dir");
		String outputLocation = currentDir + "\\HW1_Output\\";
		
		String shortName = getImageShortName(img);
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		String message = "Main Menu-----------------------------------\r\n"
				+ "1. Conversion to Gray-scale Image (24bits->8bits)\r\n"
				+ "2. Conversion to Binary Image "
				+ "using Ordered Dithering (k=4)\r\n"
				+ "3. Conversion to 8bit Indexed Color Image "
				+ "using Uniform Color Quantization (24bits->8bits)\r\n"
				+ "4. Quit\r\n"
				+ "Please enter the task number [1-4]:";
				
		
		int choice = 0;
		
		while (choice != 4) {
			System.out.println(message);
			choice = Integer.parseInt(br.readLine());
			
			switch(choice) {
				case 1:
					Grayscale.main(img, outputLocation, shortName);
					break;
					
				case 2:
					OrderedDithering.main(img, outputLocation, shortName);
					break;
					
				case 3:
					UCQ.main(img, outputLocation, shortName);
					break;
					
				case 4:
					System.exit(0);
					break;
					
				default:
					System.out.println("ERROR: Invalid choice. "
							+ "Please choose from menu options.");
					choice = 0;
			}
			
			System.out.println(" ");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// the program expects one command line argument
		// if there is no command line argument, exit the program
		if (args.length != 1) {
			usage();
			System.exit(1);
		}

		System.out.println("--Welcome to Multimedia Software System--");

		// Create an Image object with the input PPM file name.
		MImage img = new MImage(args[0]);
		System.out.println(img);
		
		System.out.println(" ");
		menu(img);
		
		System.out.println("--Good Bye--");
	}

	public static void usage() {
		System.out.println("\nUsage: java CS4551_George [input_ppm_file]\n");
	}
}
