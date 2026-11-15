package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    // Komponen yang perlu diperbarui saat data berubah
    private final JLabel headerLabel = new JLabel();
    private final JPanel listPanel = new JPanel();
    private final JLabel statLabel = new JLabel();

    // Komponen form tambah karakter
    private final JTextField nameField = new JTextField(10);
    private final JComboBox<CharacterType> typeCombo =
            new JComboBox<>(CharacterType.values());
    private final JSpinner levelSpinner =
            new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
    private final JButton addButton = new JButton("Tambah Karakter");

    public MainWindow(CharacterManager manager) {
        super("Character Manager v0.3");
        this.manager = manager;
        initComponents();
        refreshView();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(580, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Bagian atas: judul + total karakter
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));
        add(headerLabel, BorderLayout.NORTH);

        // Bagian tengah: daftar karakter (diisi oleh refreshView)
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(4, 12, 12, 12));
        add(new JScrollPane(listPanel), BorderLayout.CENTER);

        // Bagian bawah: form tambah karakter + statistik
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Tipe:"));
        formPanel.add(typeCombo);
        formPanel.add(new JLabel("Level:"));
        formPanel.add(levelSpinner);
        formPanel.add(addButton);

        statLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        statLabel.setBorder(BorderFactory.createEmptyBorder(4, 12, 12, 12));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.NORTH);
        bottomPanel.add(statLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event handler: tombol diklik, atau tekan Enter di kolom nama
        addButton.addActionListener(e -> handleAddCharacter());
        nameField.addActionListener(e -> handleAddCharacter());
    }

    // Dipanggil saat tombol "Tambah Karakter" diklik
    private void handleAddCharacter() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama karakter tidak boleh kosong.",
                    "Input tidak valid", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocus();
            return;
        }

        CharacterType type = (CharacterType) typeCombo.getSelectedItem();
        int level = (Integer) levelSpinner.getValue();

        boolean berhasil = manager.addCharacter(new Character(name, type, level));
        if (!berhasil) {
            JOptionPane.showMessageDialog(this,
                    "Karakter bernama '" + name + "' sudah ada.",
                    "Nama duplikat", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocus();
            return;
        }

        // Berhasil: kosongkan form dan segarkan tampilan
        nameField.setText("");
        levelSpinner.setValue(1);
        nameField.requestFocus();
        refreshView();
    }

    // Menggambar ulang daftar, jumlah, dan statistik dari data di manager
    private void refreshView() {
        headerLabel.setText(
                "Daftar Karakter (" + manager.getTotalCharacters() + ")");

        listPanel.removeAll();
        List<Character> characters = manager.getAllCharacters();
        for (Character c : characters) {
            JLabel row = new JLabel(String.format(
                    "%s  |  %s  |  Lv %d  |  HP %d  |  ATK %d",
                    c.getName(), c.getType(), c.getLevel(),
                    c.getHitPoints(), c.getAttackPower()));
            row.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            listPanel.add(row);
        }
        listPanel.revalidate();
        listPanel.repaint();

        statLabel.setText(String.format(
                "Rata-rata Level: %.2f", manager.getAverageLevel()));
    }
}