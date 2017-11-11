package com.semanticsquare.io;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.Scanner;

public class ReadFromConsole {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// StringBuilder text = new StringBuilder();
//		// System.out.println("Enter text into the console. When done, enter \"end\"");
//		// try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in,
//		// "UTF-8"))){
//		// String line = null;
//		// while((line = in.readLine()) != null && !(line.endsWith("end"))) {
//		// text.append(line).append("\n");
//		// }
//		// } catch (UnsupportedEncodingException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// } catch (IOException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		//
//		// System.out.println("Attempting to write to console...");
//		// try(BufferedWriter out = new BufferedWriter(new
//		// OutputStreamWriter(System.out, "UTF-8"))){
//		// out.write(text.toString());
//		// } catch (UnsupportedEncodingException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// } catch (IOException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		//
//		// System.out.print("Writing to file...");
//		// try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
//		// FileOutputStream("textFromConsole.txt"),"UTF-8"))){
//		// out.write(text.toString());
//		// } catch (UnsupportedEncodingException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// } catch (FileNotFoundException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// } catch (IOException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//
//		System.out.println("Using scanner...Enter text and when done, type in \"end\"");
//		Scanner scan = new Scanner(System.in);
//		StringBuilder l = new StringBuilder();
//		String s = null;
//		while ((s = scan.nextLine()) != null && (!s.equals("end"))) {
//			l.append(s).append("\n");
//		}
//		System.out.println("Here is your input using Scanner");
//		System.out.println(l.toString());
//		System.out.println("You can also enter other primitive variables using scan");
//		System.out.println("Enter an integer");
//		int i = scan.nextInt();
//		System.out.println("Good! Your integer is " + i);
//		System.out.println("Enter a double");
//		double d = scan.nextDouble();
//		System.out.println("Good! Your double is " + d);
//		scan.close();
//
//		System.out.println("Using Scanner to read from file");
//		try(Scanner scanfile = new Scanner(new File("README.md"))){
//			while (scanfile.hasNext()) {
//				System.out.println(scanfile.nextLine());
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
