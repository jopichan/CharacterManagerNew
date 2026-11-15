package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {
    private int id;
    private final String name;
    private final CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // Constructor 1: karakter baru, HP dan attack dihitung otomatis
    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        this.mana = 0;
    }

    // Constructor 2: memuat karakter dari database
    public Character(int id, String name, CharacterType type, int level,
                     int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = 0;
    }

    private int calculateBaseHP() {
        return 100 + level * 10;
    }

    private int calculateBaseAttack() {
        return 10 + level * 2; // sesuaikan dengan rumus di kodemu
    }

    // Getter
    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }
    public int getMana() { return mana; }

    // Setter (name dan type final, jadi tidak ada setter-nya)
    public void setId(int id) { this.id = id; }
    public void setLevel(int level) { this.level = level; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }
    public void setMana(int mana) { this.mana = mana; }

    // Dua karakter dianggap sama jika namanya sama
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character other = (Character) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Character{id=" + id + ", name='" + name + "', type=" + type
                + ", level=" + level + ", HP=" + hitPoints
                + ", ATK=" + attackPower + ", mana=" + mana + "}";
    }
}