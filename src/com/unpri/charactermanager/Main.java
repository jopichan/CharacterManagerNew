package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Character Manager v0.2 ===");
        CharacterManager manager = new CharacterManager();

        // 1. Tambah karakter
        System.out.println("\n--- Tambah Karakter ---");
        System.out.println("Tambah Arjuna : " + manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5)));
        System.out.println("Tambah Merlin : " + manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3)));
        System.out.println("Tambah Athena : " + manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4)));
        System.out.println("Total karakter: " + manager.getTotalCharacters());

        // 2. Duplikat ditolak
        System.out.println("\n--- Uji Duplikat ---");
        boolean berhasil = manager.addCharacter(new Character("Arjuna", CharacterType.MAGE, 10));
        System.out.println("Tambah Arjuna lagi: " + berhasil + (berhasil ? "" : " (DITOLAK, nama duplikat)"));
        System.out.println("Total karakter    : " + manager.getTotalCharacters());

        // 3. findByName
        System.out.println("\n--- Cari Karakter ---");
        System.out.println("Cari Merlin : " + manager.findByName("Merlin"));
        System.out.println("Cari Zeus   : " + manager.findByName("Zeus"));

        // 4. removeCharacter
        System.out.println("\n--- Hapus Karakter ---");
        System.out.println("Hapus Athena: " + manager.removeCharacter("Athena"));
        System.out.println("Hapus Zeus  : " + manager.removeCharacter("Zeus"));
        System.out.println("Total karakter: " + manager.getTotalCharacters());

        // 5. getAllCharacters() mengembalikan salinan, bukan referensi asli
        System.out.println("\n--- Uji Salinan List ---");
        List<Character> salinan = manager.getAllCharacters();
        salinan.clear(); // kosongkan salinan
        System.out.println("Ukuran salinan setelah clear(): " + salinan.size());
        System.out.println("Total di manager (harus tetap): " + manager.getTotalCharacters());

        // Tampilkan semua karakter
        System.out.println("\n--- Semua Karakter ---");
        for (Character c : manager.getAllCharacters()) {
            System.out.println(c);
        }
    }
}