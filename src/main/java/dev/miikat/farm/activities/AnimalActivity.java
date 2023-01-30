package dev.miikat.farm.activities;

import java.io.Serializable;

public interface AnimalActivity extends Serializable {
	public String getName();

	public boolean isAvailable();

	public void doActivity();
}