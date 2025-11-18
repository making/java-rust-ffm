package com.example.ffm;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Check for options
		boolean useJavaImpl = false;
		boolean doWarmup = false;

		for (String arg : args) {
			if ("--java".equals(arg)) {
				useJavaImpl = true;
			}
			if ("--warmup".equals(arg)) {
				doWarmup = true;
			}
		}

		// Perform warmup if requested
		if (doWarmup) {
			performWarmup(useJavaImpl);
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Takeuchi Function Calculator");
		System.out.println("Implementation: " + (useJavaImpl ? "Java" : "Rust (FFM)"));
		System.out.println("Enter 'quit' or 'q' to exit");

		while (true) {
			System.out.print("\nEnter x y z (space separated): ");
			String input = scanner.nextLine().trim();

			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
				System.out.println("Goodbye!");
				break;
			}

			String[] parts = input.split("\\s+");
			if (parts.length != 3) {
				System.out.println("Error: Please enter exactly 3 integers");
				continue;
			}

			try {
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				int z = Integer.parseInt(parts[2]);

				long startTime = System.currentTimeMillis();
				int result = useJavaImpl ? TakeuchiFunctionJ.tak(x, y, z) : TakeuchiFunction.tak(x, y, z);
				long endTime = System.currentTimeMillis();

				System.out.println("tak(" + x + ", " + y + ", " + z + ") = " + result);
				System.out.println("Time: " + (endTime - startTime) + " ms");
			}
			catch (NumberFormatException e) {
				System.out.println("Error: Please enter valid integers");
			}
			catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		scanner.close();
	}

	private static void performWarmup(boolean useJavaImpl) {
		System.out.println("Warming up implementation...");

		if (useJavaImpl) {
			System.out.print("Java warmup: ");
			for (int i = 0; i < 50; i++) {
				TakeuchiFunctionJ.tak(12, 6, 0);
				if ((i + 1) % 10 == 0)
					System.out.print(".");
			}
			System.out.println(" done");
		}
		else {
			System.out.print("Rust warmup: ");
			for (int i = 0; i < 50; i++) {
				TakeuchiFunction.tak(12, 6, 0);
				if ((i + 1) % 10 == 0)
					System.out.print(".");
			}
			System.out.println(" done");
		}
		System.out.println("Warmup completed!\n");
	}

}