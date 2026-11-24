package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        CharacterManager manager = new CharacterManager();

        manager.addCharacter(new Character("Arthur", CharacterType.WARRIOR, 6));
        manager.addCharacter(new Character("Lyra", CharacterType.MAGE, 5));
        manager.addCharacter(new Character("Robin", CharacterType.ARCHER, 3));
        manager.addCharacter(new Character("Elena", CharacterType.HEALER, 4));
        manager.addCharacter(new Character("Garrick", CharacterType.WARRIOR, 2));
        manager.addCharacter(new Character("Seraphina", CharacterType.MAGE, 8));
        manager.addCharacter(new Character("Kael", CharacterType.ARCHER, 5));
        manager.addCharacter(new Character("Mirelle", CharacterType.HEALER, 1));

        SwingUtilities.invokeLater(() -> new MainWindow(manager).setVisible(true));
    }
}