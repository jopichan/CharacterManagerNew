package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/** Semua warna, font, dan border aplikasi ada di sini (layer View). */
public final class AppTheme {

    private AppTheme() { }

    // ===== Warna =====
    public static final Color BACKGROUND = new Color(0x0A0A1E);
    public static final Color PANEL_BG   = new Color(0x0F0F28);
    public static final Color CARD_BG    = new Color(0x12122E);
    public static final Color TEXT       = new Color(0xF2F2F2);
    public static final Color MUTED      = new Color(0x9A9AB8);
    public static final Color GOLD       = new Color(0xFFC107);
    public static final Color OUTLINE    = new Color(0xDCDCF0);
    public static final Color ERROR      = new Color(0xFF5252);

    // ===== Font =====
    public static final Font FONT_TITLE   = new Font(Font.MONOSPACED, Font.BOLD, 28);
    public static final Font FONT_HEADING = new Font(Font.MONOSPACED, Font.BOLD, 15);
    public static final Font FONT_CARD    = new Font(Font.MONOSPACED, Font.BOLD, 15);
    public static final Font FONT_BODY    = new Font(Font.MONOSPACED, Font.BOLD, 13);
    public static final Font FONT_SMALL   = new Font(Font.MONOSPACED, Font.BOLD, 12);

    // ===== Border =====
    /** Garis berwarna setebal {@code thickness} dengan jarak dalam {@code padding}. */
    public static Border outline(Color color, int thickness, int padding) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, thickness),
                BorderFactory.createEmptyBorder(padding, padding, padding, padding));
    }

    // ===== Warna per role =====
    public static Color colorFor(CharacterType type) {
        if (type == null) return MUTED;
        switch (type) {
            case WARRIOR: return new Color(0xFF5252);
            case MAGE:    return new Color(0x29B6F6);
            case ARCHER:  return new Color(0x1DE9B6);
            case HEALER:  return new Color(0xE0F7FA);
            default:      return MUTED;
        }
    }

    /** Deskripsi singkat role untuk panel legenda. */
    public static String describe(CharacterType type) {
        switch (type) {
            case WARRIOR: return "Warrior: Jarak dekat, HP tinggi, pertahanan kuat.";
            case MAGE:    return "Mage: Serangan sihir area, HP rendah, burst damage.";
            case ARCHER:  return "Archer: Jarak jauh, kecepatan tinggi, serangan seimbang.";
            case HEALER:  return "Healer: Penyembuh tim, dukungan pemulihan, pertahanan terbaik.";
            default:      return "";
        }
    }
}