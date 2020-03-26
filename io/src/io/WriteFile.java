package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteFile {
	public static void main(String[] args) {
		Path path = Paths.get("c:/output.txt");

		// Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write("Hello World !!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
