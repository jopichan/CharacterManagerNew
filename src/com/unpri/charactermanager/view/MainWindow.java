package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/** MainWindow v0.6: statistik, form validasi, roster kartu, dan legenda role. */
public class MainWindow extends JFrame {

    private static final int HIGH_LEVEL_THRESHOLD = 5;

    private final CharacterManager manager;

    private final JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
    private final JLabel totalLabel = statLabel();
    private final JLabel highLevelLabel = statLabel();
    private final JLabel hpLabel = statLabel();
    private final JLabel atkLabel = statLabel();
    private final JLabel footerLabel = new JLabel();

    public MainWindow(CharacterManager manager) {
        super("Character Manager v0.6");
        this.manager = manager;

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(0, 12));
        root.setBackground(AppTheme.BACKGROUND);
        root.setBorder(BorderFactory.createEmptyBorder(16, 20, 12, 20));
        setContentPane(root);

        root.add(buildTop(), BorderLayout.NORTH);
        root.add(buildBody(), BorderLayout.CENTER);
        root.add(buildFooter(), BorderLayout.SOUTH);

        refreshList();

        pack();
        setLocationRelativeTo(null);
    }

    // ===== Bagian atas: judul, statistik, form =====
    private JPanel buildTop() {
        JLabel title = new JLabel("CHARACTER MANAGER \u2014 ROSTER");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.GOLD);

        JPanel stats = new JPanel(new GridLayout(2, 2, 20, 6));
        stats.setOpaque(true);
        stats.setBackground(AppTheme.PANEL_BG);
        stats.setBorder(AppTheme.outline(AppTheme.OUTLINE, 2, 14));
        stats.add(totalLabel);
        stats.add(highLevelLabel);
        stats.add(hpLabel);
        stats.add(atkLabel);

        CharacterFormPanel form = new CharacterFormPanel(manager, c -> refreshList());

        JPanel middle = new JPanel(new BorderLayout(0, 12));
        middle.setOpaque(false);
        middle.add(stats, BorderLayout.NORTH);
        middle.add(form, BorderLayout.CENTER);

        JPanel top = new JPanel(new BorderLayout(0, 12));
        top.setOpaque(false);
        top.add(title, BorderLayout.NORTH);
        top.add(middle, BorderLayout.CENTER);
        return top;
    }

    // ===== Bagian tengah: roster kartu + legenda =====
    private JPanel buildBody() {
        gridPanel.setOpaque(false);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(gridPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.PANEL_BG);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel roster = new JPanel(new BorderLayout());
        roster.setBackground(AppTheme.PANEL_BG);
        roster.setBorder(AppTheme.outline(AppTheme.OUTLINE, 2, 10));
        roster.setPreferredSize(new Dimension(640, 440));
        roster.add(scroll, BorderLayout.CENTER);

        JPanel body = new JPanel(new BorderLayout(14, 0));
        body.setOpaque(false);
        body.add(roster, BorderLayout.CENTER);
        return body;
    }

    // ===== Bagian bawah: footer =====
    private JPanel buildFooter() {
        footerLabel.setFont(AppTheme.FONT_SMALL);
        footerLabel.setForeground(AppTheme.TEXT);

        JLabel version = new JLabel("v0.6");
        version.setFont(AppTheme.FONT_HEADING);
        version.setForeground(AppTheme.GOLD);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(true);
        footer.setBackground(AppTheme.PANEL_BG);
        footer.setBorder(AppTheme.outline(AppTheme.MUTED, 1, 10));
        footer.add(footerLabel, BorderLayout.WEST);
        footer.add(version, BorderLayout.EAST);
        return footer;
    }

    /** Membangun ulang kartu dan statistik dari data manager. */
    private void refreshList() {
        gridPanel.removeAll();

        List<Character> all = manager.getAllCharacters();
        int index = 1;
        for (Character c : all) {
            gridPanel.add(createCard(index++, c));
        }

        totalLabel.setText("Total Karakter: " + manager.getTotalCharacters());
        highLevelLabel.setText("Level >= " + HIGH_LEVEL_THRESHOLD + ": "
                + manager.countLevelAtLeast(HIGH_LEVEL_THRESHOLD));
        hpLabel.setText("Total HP: " + manager.getTotalHp());
        atkLabel.setText("Total ATK: " + manager.getTotalAtk());
        footerLabel.setText("Total Karakter: " + manager.getTotalCharacters()
                + " | Form Input Aktif");

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private JPanel createCard(int index, Character c) {
        Color roleColor = AppTheme.colorFor(c.getType());

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(true);
        card.setBackground(AppTheme.CARD_BG);
        card.setBorder(AppTheme.outline(roleColor, 2, 10));

        // Baris judul: "#1 Arthur" + bintang emas untuk level tinggi
        JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titleRow.setOpaque(false);
        titleRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleRow.add(textLabel("#" + index + " " + c.getName(), AppTheme.FONT_CARD, AppTheme.TEXT));
        if (c.getLevel() >= HIGH_LEVEL_THRESHOLD) {
            titleRow.add(textLabel(" \u2605", AppTheme.FONT_CARD, AppTheme.GOLD));
        }

        JLabel roleLine = textLabel(c.getType() + " \u00B7 Lv." + c.getLevel(),
                AppTheme.FONT_SMALL, roleColor);
        JLabel statLine = textLabel("HP: " + c.getHp() + " | ATK: " + c.getAtk()
                + " | Mana: " + c.getMana(), AppTheme.FONT_SMALL, AppTheme.TEXT);

        card.add(titleRow);
        card.add(javax.swing.Box.createVerticalStrut(6));
        card.add(roleLine);
        card.add(javax.swing.Box.createVerticalStrut(6));
        card.add(statLine);

        card.setToolTipText(c.getName() + " - " + c.getType() + ", level " + c.getLevel());
        return card;
    }

    private static JLabel textLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private static JLabel statLabel() {
        return textLabel(" ", AppTheme.FONT_BODY, AppTheme.TEXT);
    }
}