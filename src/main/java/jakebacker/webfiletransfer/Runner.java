package jakebacker.webfiletransfer;

import java.io.File;

public class Runner {


	private static final String[] FLAG_NAMES = {"-u", "-v"};
	private static final String[] ALT_FLAG_NAMES = {"--upload", "--verbose"};
	private static boolean[] flags = new boolean[2];

	private static File file;

	public static void main(String[] args) {
		setup(args);
	}

	public static void setup(String[] args) {
		for (String s : args) {
			if (s.startsWith("-")) {
				for (int i=0; i<flags.length; i++) {
					flags[i] = s.equals(FLAG_NAMES[i]) || s.equals(ALT_FLAG_NAMES[i]);
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
			file-transfer <options> path/to/file

		Options:
			-u, --upload: Set to upload mode. Path will be used as output directory
			-v, --verbose: Verbose output

		 */
	}
}