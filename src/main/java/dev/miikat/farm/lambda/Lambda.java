package dev.miikat.farm.lambda;

public interface Lambda<T, R> {
	@SuppressWarnings("unchecked")
	public T run(R... args);
}
