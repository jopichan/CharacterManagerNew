package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Character Manager v0.1 ===");

        Character hero = new Character("Arjuna", CharacterType.WARRIOR, 5);
        Character mage = new Character("Merlin", CharacterType.MAGE, 3);
        Character archer = new Character("Athena", CharacterType.ARCHER, 4);

        System.out.println(hero);
        System.out.println(mage);
        System.out.println(archer);

        System.out.println("\n--- Level Up ---");
        hero.setLevel(hero.getLevel() + 1);
        System.out.println("Nama  : " + hero.getName());
        System.out.println("Jenis : " + hero.getType());
        System.out.println("HP    : " + hero.getHitPoints());
    }
}