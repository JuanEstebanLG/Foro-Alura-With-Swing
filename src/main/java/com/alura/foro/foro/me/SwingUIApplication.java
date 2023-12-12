package com.alura.foro.foro.me;

import com.alura.foro.foro.me.swingComponents.Login.LoginFrame;

import javax.swing.*;

public class SwingUIApplication {

    public static void iniciarAppSwing() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new LoginFrame();
        });
    }
}
