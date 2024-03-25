package me.gnevilkoko.exceptions;

import java.util.ArrayList;
import java.util.Arrays;

public class NotEnoughKeyPairsException extends Exception {
    private ArrayList<String> requiredKeys;

    public NotEnoughKeyPairsException(ArrayList<String> requiredKeys) {
        this.requiredKeys = requiredKeys;
    }

    public ArrayList<String> getRequiredKeys() {
        return requiredKeys;
    }

    @Override
    public String getMessage() {
        return "Require this keys: "+ getRequiredKeys();
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
