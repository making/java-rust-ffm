# Java-Rust FFM Sample

This project demonstrates how to call Rust functions from Java using Foreign Function & Memory (FFM) API in Java 25.

## Overview

The project implements the Takeuchi function in Rust and calls it from Java using FFM API without JNI.

## Requirements

- Java 25
- Rust (with Cargo)
- Maven 3.x

## Project Structure

```
.
├── rust/                 # Rust library
│   ├── Cargo.toml
│   └── src/
│       └── lib.rs       # Takeuchi function implementation
└── src/                 # Java application
    ├── main/java/com/example/ffm/
    │   └── TakeuchiFunction.java
    └── test/java/com/example/ffm/
        └── TakeuchiFunctionTest.java
```

## Build

### 1. Build Rust library

```bash
cd rust
cargo test
cargo build --release
cd -
```

### 2. Build Java application

```bash
./mvnw clean compile
```

## Run

### Run tests

```bash
./mvnw test
```

### Run main class

#### Run with Rust FFM implementation (default)

```bash
java -cp target/classes -Djava.library.path=rust/target/release com.example.ffm.Main
```

#### Run with Java implementation

```bash
java -cp target/classes com.example.ffm.Main --java
```

#### Run with warmup (recommended for performance testing)

```bash
# Rust FFM implementation with warmup
java -cp target/classes -Djava.library.path=rust/target/release com.example.ffm.Main --warmup

# Java implementation with warmup
java -cp target/classes com.example.ffm.Main --java --warmup
```

## Clean

Remove all build artifacts:

```bash
# Clean Java artifacts
mvn clean

# Clean Rust artifacts
cd rust
cargo clean
```

## Implementation Details

- Rust: Exports `tak` function with C ABI using `#[no_mangle]` and `extern "C"`
- Java: Uses FFM API's `SymbolLookup`, `Linker`, and `MethodHandle` to call the native function