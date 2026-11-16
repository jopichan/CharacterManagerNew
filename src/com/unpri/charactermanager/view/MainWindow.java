package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

    private CharacterManager manager;
    private JPanel listPanel;
    private JLabel averageLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager v0.4");
        setSize(350, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        loadCharacters();
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(AppTheme.BACKGROUND);

        JLabel title = new JLabel("Daftar Karakter");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(20, 10, 20, 10));

        mainPanel.add(title, BorderLayout.NORTH);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(AppTheme.BACKGROUND);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(AppTheme.BACKGROUND);

        averageLabel = new JLabel();
        averageLabel.setForeground(Color.WHITE);

        JButton addButton = new JButton("Tambah Karakter");

        addButton.addActionListener(e -> tambahKarakter());

        bottomPanel.add(averageLabel, BorderLayout.WEST);
        bottomPanel.add(addButton, BorderLayout.EAST);

        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadCharacters() {

        listPanel.removeAll();

        for (Character c : manager.getCharacters()) {

            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel(c.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel infoLabel = new JLabel(
                    c.getType() + " | Level " + c.getLevel()
            );

            card.add(nameLabel);
            card.add(Box.createVerticalStrut(10));
            card.add(infoLabel);

            card.setMaximumSize(new Dimension(300, 90));
            card.setBorder(new EmptyBorder(15, 15, 15, 15));

            switch (c.getType()) {

                case WARRIOR:
                    card.setBackground(AppTheme.WARRIOR);
                    break;

                case MAGE:
                    card.setBackground(AppTheme.MAGE);
                    break;

                case ARCHER:
                    card.setBackground(AppTheme.ARCHER);
                    break;

                case HEALER:
                    card.setBackground(AppTheme.HEALER);
                    break;
            }

            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(10));
        }

        averageLabel.setText(
                String.format(
                        "Rata-rata Level: %.2f",
                        manager.getAverageLevel()
                )
        );

        listPanel.revalidate();
        listPanel.repaint();
    }

    private void tambahKarakter() {

        String nama = JOptionPane.showInputDialog(
                this,
                "Masukkan Nama Karakter"
        );

        if (nama == null || nama.trim().isEmpty()) {
            return;
        }

        CharacterType tipe =
                (CharacterType) JOptionPane.showInputDialog(
                        this,
                        "Pilih Tipe Karakter",
                        "Tipe Karakter",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        CharacterType.values(),
                        CharacterType.WARRIOR
                );

        if (tipe == null) {
            return;
        }

        String levelInput = JOptionPane.showInputDialog(
                this,
                "Masukkan Level"
        );

        if (levelInput == null) {
            return;
        }

        try {

            int level = Integer.parseInt(levelInput);

            manager.addCharacter(
                    new Character(
                            nama,
                            tipe,
                            level
                    )
            );

            loadCharacters();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Level harus berupa angka!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}