package com.example.ffm;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.file.Path;

public class TakeuchiFunction {

	private static final SymbolLookup LIBRARY_LOOKUP;

	private static final MethodHandle TAK_HANDLE;

	static {
		try {
			// Load the Rust library
			System.loadLibrary("tak");
			LIBRARY_LOOKUP = SymbolLookup.loaderLookup();

			// Create function descriptor for tak(int, int, int) -> int
			FunctionDescriptor takDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, // return
																							// type
					ValueLayout.JAVA_INT, // x parameter
					ValueLayout.JAVA_INT, // y parameter
					ValueLayout.JAVA_INT // z parameter
			);

			// Find the tak function
			TAK_HANDLE = LIBRARY_LOOKUP.find("tak")
				.map(symbol -> Linker.nativeLinker().downcallHandle(symbol, takDescriptor))
				.orElseThrow(() -> new RuntimeException("Failed to find tak function"));

		}
		catch (Exception e) {
			throw new RuntimeException("Failed to load native library", e);
		}
	}

	public static int tak(int x, int y, int z) {
		try {
			return (int) TAK_HANDLE.invokeExact(x, y, z);
		}
		catch (Throwable t) {
			throw new RuntimeException("Failed to invoke tak function", t);
		}
	}

}