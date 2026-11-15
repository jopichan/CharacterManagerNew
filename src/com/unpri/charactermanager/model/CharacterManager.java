package com.unpri.charactermanager.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    private final List<Character> characters = new ArrayList<>();

    // Tambah karakter. Nama duplikat ditolak (return false).
    public boolean addCharacter(Character character) {
        if (character == null) {
            return false;
        }
        if (characters.contains(character)) {
            return false;
        }
        characters.add(character);
        return true;
    }

    // Cari karakter berdasarkan nama, return null jika tidak ada.
    public Character findByName(String name) {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    // Hapus karakter berdasarkan nama.
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            return false;
        }
        return characters.remove(target);
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengembalikan SALINAN list, bukan referensi asli.
    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    /**
     * Mencari karakter dengan attackPower tertinggi.
     *
     * @return karakter terkuat, atau null jika belum ada karakter
     */
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }
        Character strongest = characters.get(0);
        for (Character c : characters) {
            if (c.getAttackPower() > strongest.getAttackPower()) {
                strongest = c;
            }
        }
        return strongest;
    }

    /**
     * Menghitung rata-rata level semua karakter.
     *
     * @return rata-rata level, atau 0.0 jika belum ada karakter
     */
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (Character c : characters) {
            total += c.getLevel();
        }
        return (double) total / characters.size();
    }
}