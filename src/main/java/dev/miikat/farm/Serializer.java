package dev.miikat.farm;

public interface Serializer {
	String serialize(Object o);

	Object deserialize(String data);
}
