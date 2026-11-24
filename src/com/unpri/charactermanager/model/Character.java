package com.unpri.charactermanager.model;

/** Model murni: tidak boleh mengimpor javax.swing atau java.awt. */
public class Character {
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 99;

    // Rumus statistik turunan dari level
    private static final int BASE_HP = 100;
    private static final int HP_PER_LEVEL = 10;
    private static final int BASE_ATK = 10;
    private static final int ATK_PER_LEVEL = 5;
    private static final int BASE_MANA = 50;

    private final String name;
    private final CharacterType type;
    private final int level;

    public Character(String name, CharacterType type, int level) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (type == null) {
            throw new IllegalArgumentException("Role tidak boleh null");
        }
        if (level < MIN_LEVEL || level > MAX_LEVEL) {
            throw new IllegalArgumentException(
                    "Level harus " + MIN_LEVEL + "-" + MAX_LEVEL);
        }
        this.name = name.trim();
        this.type = type;
        this.level = level;
    }

    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }

    public int getHp()   { return BASE_HP + level * HP_PER_LEVEL; }
    public int getAtk()  { return BASE_ATK + level * ATK_PER_LEVEL; }
    public int getMana() { return BASE_MANA; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        return name.equalsIgnoreCase(((Character) o).name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return name + " (" + type + ", Lv " + level + ")";
    }
}