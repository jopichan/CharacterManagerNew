package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    private final List<Character> characters = new ArrayList<>();

    // Tambah karakter. Nama duplikat ditolak (return false).
    public boolean addCharacter(Character character) {
        if (character == null) {
            return false;
        }
        if (characters.contains(character)) { // pakai Character.equals() -> bandingkan nama
            System.out.println("Gagal: karakter dengan nama '"
                    + character.getName() + "' sudah ada.");
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

    // Hapus karakter berdasarkan nama. Return true jika berhasil.
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

    // Mengembalikan SALINAN list, bukan referensi asli,
    // supaya list internal tidak bisa diubah dari luar.
    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }
}