package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.function.Consumer;

/** Form tambah karakter dengan validasi bertahap dan pesan inline. */
public class CharacterFormPanel extends JPanel {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 20;

    private final CharacterManager manager;
    private final Consumer<Character> onCharacterAdded;

    private final JTextField nameField = new JTextField(18);
    private final JComboBox<CharacterType> roleBox = new JComboBox<>(CharacterType.values());
    private final JSpinner levelSpinner =
            new JSpinner(new SpinnerNumberModel(1, Character.MIN_LEVEL, Character.MAX_LEVEL, 1));
    private final JButton addButton = new JButton("+ Tambah Karakter");
    private final JLabel errorLabel = new JLabel(" ");

    private final Border normalBorder = AppTheme.outline(AppTheme.MUTED, 2, 4);
    private final Border errorBorder = AppTheme.outline(AppTheme.ERROR, 2, 4);

    public CharacterFormPanel(CharacterManager manager, Consumer<Character> onCharacterAdded) {
        this.manager = manager;
        this.onCharacterAdded = onCharacterAdded;

        setLayout(new BorderLayout(0, 8));
        setOpaque(true);
        setBackground(AppTheme.PANEL_BG);
        setBorder(AppTheme.outline(AppTheme.GOLD, 2, 12));

        JLabel heading = new JLabel("TAMBAH KARAKTER BARU");
        heading.setFont(AppTheme.FONT_HEADING);
        heading.setForeground(AppTheme.GOLD);
        add(heading, BorderLayout.NORTH);

        add(buildInputRow(), BorderLayout.CENTER);

        errorLabel.setFont(AppTheme.FONT_SMALL);
        errorLabel.setForeground(AppTheme.ERROR);
        add(errorLabel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> submit());
        nameField.addActionListener(e -> submit());
    }

    private JPanel buildInputRow() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        row.setOpaque(false);

        nameField.setFont(AppTheme.FONT_BODY);
        nameField.setBackground(AppTheme.BACKGROUND);
        nameField.setForeground(AppTheme.TEXT);
        nameField.setCaretColor(AppTheme.TEXT);
        nameField.setBorder(normalBorder);

        roleBox.setFont(AppTheme.FONT_SMALL);

        levelSpinner.setFont(AppTheme.FONT_SMALL);
        ((JSpinner.DefaultEditor) levelSpinner.getEditor()).getTextField().setColumns(3);

        addButton.setFont(AppTheme.FONT_SMALL);
        addButton.setBackground(AppTheme.GOLD);
        addButton.setForeground(AppTheme.BACKGROUND);
        addButton.setOpaque(true);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));

        row.add(fieldLabel("Nama:"));
        row.add(nameField);
        row.add(fieldLabel("Role:"));
        row.add(roleBox);
        row.add(fieldLabel("Level (" + Character.MIN_LEVEL + "-" + Character.MAX_LEVEL + "):"));
        row.add(levelSpinner);
        row.add(addButton);
        return row;
    }

    private JLabel fieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_BODY);
        label.setForeground(AppTheme.TEXT);
        return label;
    }

    /**
     * Memeriksa berurutan: kelengkapan, panjang, format, keunikan.
     * Hanya mengembalikan pesan (null = valid); tidak menyentuh komponen.
     */
    String validateName(String raw) {
        String name = (raw == null) ? "" : raw.trim();
        if (name.isEmpty()) {
            return "Nama wajib diisi.";
        }
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            return "Nama harus " + MIN_NAME_LENGTH + "-" + MAX_NAME_LENGTH + " karakter.";
        }
        if (!name.matches("[\\p{L} ]+")) {
            return "Nama hanya boleh berisi huruf dan spasi.";
        }
        if (manager.isNameTaken(name)) {
            return "Nama '" + name + "' sudah terdaftar. Gunakan nama lain.";
        }
        return null;
    }

    private void submit() {
        clearError();

        String name = nameField.getText().trim();
        nameField.setText(name);

        String error = validateName(name);
        if (error != null) {
            showError(error);
            return;
        }

        try {
            levelSpinner.commitEdit();
        } catch (ParseException ex) {
            showError("Level harus berupa angka " + Character.MIN_LEVEL + "-" + Character.MAX_LEVEL + ".");
            return;
        }
        int level = ((Number) levelSpinner.getValue()).intValue();
        CharacterType type = (CharacterType) roleBox.getSelectedItem();

        try {
            Character c = new Character(name, type, level);
            if (!manager.addCharacter(c)) {
                showError("Karakter gagal ditambahkan.");
                return;
            }
            onCharacterAdded.accept(c);
            nameField.setText("");
            levelSpinner.setValue(Character.MIN_LEVEL);
            nameField.requestFocusInWindow();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage() + ".");
        }
    }

    private void showError(String message) {
        errorLabel.setText("Error: " + message);
        nameField.setBorder(errorBorder);
        nameField.requestFocusInWindow();
    }

    private void clearError() {
        errorLabel.setText(" ");
        nameField.setBorder(normalBorder);
    }
}