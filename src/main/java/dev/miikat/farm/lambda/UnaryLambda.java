package dev.miikat.farm.lambda;

public interface UnaryLambda<T, R> {
	public T run(R arg);
}
