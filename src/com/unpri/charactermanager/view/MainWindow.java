package com.unpri.charactermanager.view;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {

        setTitle("Character Manager");
        setSize(900,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("DAFTAR KARAKTER"));
        topPanel.add(new JLabel("Nama"));
        topPanel.add(new JTextField(10));

        topPanel.add(new JLabel("Level"));
        topPanel.add(new JTextField(5));

        topPanel.add(new JLabel("Type"));

        topPanel.add(
                new JComboBox<>(
                        new String[]{
                                "WARRIOR",
                                "MAGE",
                                "ARCHER"
                        }));

        topPanel.add(
                new JButton("Tambah"));

        add(topPanel,
                BorderLayout.NORTH);

        JPanel listPanel = new JPanel();

        listPanel.setLayout(
                new BoxLayout(
                        listPanel,
                        BoxLayout.Y_AXIS));

        tambahData(listPanel,"Naruto | Level: 50 | Type: WARRIOR",Color.RED);
        tambahData(listPanel,"Sasuke | Level: 55 | Type: MAGE",Color.BLUE);
        tambahData(listPanel,"Sakura | Level: 48 | Type: ARCHER",Color.GREEN);
        tambahData(listPanel,"Kakashi | Level: 70 | Type: WARRIOR",Color.RED);
        tambahData(listPanel,"Hinata | Level: 45 | Type: ARCHER",Color.GREEN);
        tambahData(listPanel,"Itachi | Level: 80 | Type: MAGE",Color.BLUE);
        tambahData(listPanel,"Gaara | Level: 60 | Type: WARRIOR",Color.RED);
        tambahData(listPanel,"Rock Lee | Level: 58 | Type: WARRIOR",Color.RED);
        tambahData(listPanel,"Neji | Level: 62 | Type: ARCHER",Color.GREEN);
        tambahData(listPanel,"Shikamaru | Level: 65 | Type: MAGE",Color.BLUE);

        JScrollPane scrollPane =
                new JScrollPane(listPanel);

        add(scrollPane,
                BorderLayout.CENTER);

        JPanel legendPanel = new JPanel();

        legendPanel.setLayout(
                new BoxLayout(
                        legendPanel,
                        BoxLayout.Y_AXIS));

        legendPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "LEGENDA"));

        legendPanel.add(new JLabel("WARRIOR"));
        legendPanel.add(new JLabel("MAGE"));
        legendPanel.add(new JLabel("ARCHER"));

        add(legendPanel,
                BorderLayout.EAST);

        JLabel statusLabel =
                new JLabel(
                        "Rata-rata Level: 59.30");

        add(statusLabel,
                BorderLayout.SOUTH);
    }

    private void tambahData(
            JPanel panel,
            String text,
            Color color) {

        JPanel card = new JPanel();

        card.setBackground(color);

        JLabel label =
                new JLabel(text);

        label.setForeground(Color.YELLOW);

        card.add(label);

        panel.add(card);
    }
}