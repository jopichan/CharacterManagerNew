package com.unpri.charactermanager.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    private final List<Character> characters = new ArrayList<>();

    /** @return true jika berhasil, false jika null atau nama sudah dipakai. */
    public boolean addCharacter(Character c) {
        if (c == null || isNameTaken(c.getName())) {
            return false;
        }
        characters.add(c);
        return true;
    }

    public Character findByName(String name) {
        if (name == null) return null;
        String key = name.trim();
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(key)) {
                return c;
            }
        }
        return null;
    }

    public boolean isNameTaken(String name) {
        return findByName(name) != null;
    }

    public boolean removeCharacter(String name) {
        Character c = findByName(name);
        return c != null && characters.remove(c);
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    public int getTotalHp() {
        int sum = 0;
        for (Character c : characters) sum += c.getHp();
        return sum;
    }

    public int getTotalAtk() {
        int sum = 0;
        for (Character c : characters) sum += c.getAtk();
        return sum;
    }

    public int countLevelAtLeast(int minLevel) {
        int count = 0;
        for (Character c : characters) {
            if (c.getLevel() >= minLevel) count++;
        }
        return count;
    }

    /** Mengembalikan salinan, bukan referensi list asli. */
    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }
}