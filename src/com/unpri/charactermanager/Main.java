package com.unpri.charactermanager;

import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainWindow window =
                    new MainWindow();

            window.setVisible(true);

        });
    }
}