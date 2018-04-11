package jakebacker.webfiletransfer;

import java.io.File;
import java.io.IOException;

public class Runner {
	private static final String[] FLAG_NAMES = {"-u", "-v", "-h"};
	private static final String[] ALT_FLAG_NAMES = {"--upload", "--verbose", "--help"};
	private static boolean[] flags = {false, false, false};

	private static File file;

	public static void main(String[] args) {
		setup(args);
		if (flags[2]) {
			printUsage();
			System.exit(1);
		}

		if (flags[0]) {
			System.out.println("Uploading not yet supported!");
			System.exit(0);
		}

		try {
			new Server(file, flags[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setup(String[] args) {
		for (String s : args) {
			if (s.startsWith("-")) {
				for (int i=0; i<flags.length; i++) {
					if (s.equals(FLAG_NAMES[i]) || s.equals(ALT_FLAG_NAMES[i])) {
						flags[i] = true;
					}
				}
			}
		}

		try {
			file = new File(args[args.length - 1]);
		} catch (NullPointerException e) {
			System.out.println("Error: File or path does not exist!");
			System.exit(1);
		}

		if (!file.exists()) {
			System.out.println("Error: File/Path does not exist!");
			System.exit(1);
		}

		if (file.isFile() && flags[0]) {
			System.out.println("Error: Path does not exist!");
			System.exit(1);
		}

		if (file.isDirectory() && !flags[0]) {
			System.out.println("Error: File does not exist!");
			System.exit(1);
		}
	}

	public static void printUsage() {
		/*
		Usage:
			file-transfer <options> path\to\file

		Options:
			-u, --upload: Set to upload mode. Path will be used as output directory
			-v, --verbose: Verbose output

		 */

		System.out.println("Usage:");
		System.out.println("\tfile-transfer <options> path\\to\\file");
		System.out.println();
		System.out.println("Options:");
		System.out.println("\t-u, --upload: Set to upload mode. Path will be used as output directory");
		System.out.println("\t-v, --verbose: Verbose output");
	}
}
