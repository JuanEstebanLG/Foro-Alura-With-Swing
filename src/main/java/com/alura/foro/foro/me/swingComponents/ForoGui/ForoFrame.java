package com.alura.foro.foro.me.swingComponents.ForoGui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;


public class ForoFrame extends JFrame {

    public ForoFrame(String response) {
        setTitle("Foro - Vista Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());

        // Panel superior con el encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(28, 40, 51)); // Color oscuro
        headerPanel.setPreferredSize(new Dimension(getWidth(), 70));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Foro de Discusión", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Panel central con el contenido del foro
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 245, 245)); // Color de fondo claro
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Crear algunos mensajes ficticios para visualizar el contenido
        for (int i = 1; i <= 5; i++) {
            JPanel postPanel = new JPanel();
            postPanel.setBackground(new Color(255, 255, 255)); // Color blanco para los posts
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Título del mensaje
            JLabel postTitle = new JLabel("Mensaje " + i);
            postTitle.setFont(new Font("Arial", Font.BOLD, 18));
            postTitle.setForeground(new Color(28, 40, 51)); // Color oscuro
            postPanel.add(postTitle);

            // Cuerpo del mensaje
            JTextArea postContent = new JTextArea(5, 40);
            postContent.setText("Contenido del mensaje " + i + " en el foro. Aquí puedes escribir cualquier cosa.");
            postContent.setFont(new Font("Arial", Font.PLAIN, 14));
            postContent.setLineWrap(true);
            postContent.setWrapStyleWord(true);
            postContent.setEditable(false);
            postContent.setBackground(new Color(255, 255, 255)); // Fondo blanco
            postContent.setForeground(new Color(51, 51, 51)); // Texto gris oscuro
            postContent.setBorder(BorderFactory.createEmptyBorder());
            postPanel.add(postContent);

            contentPanel.add(postPanel);
        }

        // Panel de pie de página (sin interacción por ahora)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(28, 40, 51)); // Color oscuro
        footerPanel.setPreferredSize(new Dimension(getWidth(), 50));

        JLabel footerLabel = new JLabel("© 2025 Foro, todos los derechos reservados", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);

        add(footerPanel, BorderLayout.SOUTH);
    }
}
