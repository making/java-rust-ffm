package com.example.ffm;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class TakeuchiFunctionTest {

	@FunctionalInterface
	interface TakFunction {

		int tak(int x, int y, int z);

	}

	static Stream<TakFunction> takFunctionProvider() {
		return Stream.of(TakeuchiFunction::tak, TakeuchiFunctionJ::tak);
	}

	@ParameterizedTest
	@MethodSource("takFunctionProvider")
	void testBaseCases(TakFunction takFunction) {
		// When y >= x, should return y
		assertThat(takFunction.tak(5, 10, 0)).isEqualTo(10);
		assertThat(takFunction.tak(5, 5, 3)).isEqualTo(5);
		assertThat(takFunction.tak(0, 10, 20)).isEqualTo(10);
	}

	@ParameterizedTest
	@MethodSource("takFunctionProvider")
	void testRecursiveCases(TakFunction takFunction) {
		// Classic test cases for Takeuchi function
		assertThat(takFunction.tak(6, 2, 1)).isEqualTo(6);
		assertThat(takFunction.tak(10, 5, 0)).isEqualTo(10);
		assertThat(takFunction.tak(12, 6, 0)).isEqualTo(12);
	}

	@ParameterizedTest
	@MethodSource("takFunctionProvider")
	void testSmallValues(TakFunction takFunction) {
		assertThat(takFunction.tak(1, 0, 0)).isEqualTo(0);
		assertThat(takFunction.tak(2, 1, 0)).isEqualTo(2);
		assertThat(takFunction.tak(3, 2, 1)).isEqualTo(3);
	}

	@ParameterizedTest
	@MethodSource("takFunctionProvider")
	void testNegativeValues(TakFunction takFunction) {
		assertThat(takFunction.tak(-5, 0, 0)).isEqualTo(0);
		assertThat(takFunction.tak(0, -5, 3)).isEqualTo(3);
	}

}