Jessie George
CS4551 HW1

CS4551_George.java is the main class.

getImageShortName function removes .ppm from the image file name, 
which is helpful later when we write output files.

menu function prints menu and calls the other classes based on user's choice.
in the menu function, outputLocation is set to a folder called HW1_Output in 
the current directory. This is where the output ppm images will be stored.

Grayscale.java converts the image to gray-scale using the formula:
int gray = (int) Math.round(0.299 * rgb[0] 
						+ 0.587 * rgb[1] 
						+ 0.114 * rgb[2]);
R,G,B is set to gray.
Output is written to [InputFileName]-gray.ppm



------
CS4551 Multimedia Software Systems
@ Author: Elaine Kang
Computer Science Department
California State University, Los Angeles

Compile requirement
======================================
JDK Version 7.0 or above


Compile Instruction on Command Line:
======================================
javac CS4551_Main.java MImage.java 
or 
javac *.java


Execution Instruction on Command Line:
======================================
java CS4551_Main [inputfile]
e.g.
java CS4551_Main Ducky.ppm
