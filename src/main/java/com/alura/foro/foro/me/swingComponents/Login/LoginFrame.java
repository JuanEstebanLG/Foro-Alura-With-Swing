package com.alura.foro.foro.me.swingComponents.Login;
import com.alura.foro.foro.me.httpHandlers.HttpRequestHandler;
import com.alura.foro.foro.me.swingComponents.ForoGui.ForoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    JTextArea responseTextArea = new JTextArea();
    JFrame frame = new JFrame();

    public LoginFrame() {

        /*
        * Set de frame config

        */

        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);

        /*Add panelForLoginItemsSpace to the frame*/

        JPanel panelForLoginItemsSpace = new JPanel();
        JPanel panelForImage = new JPanel();
        frame.add(panelForLoginItemsSpace);
        frame.add(panelForImage);

        /*Panel config*/

        panelForLoginItemsSpace.setSize(frame.getWidth() / 2, frame.getHeight());
        panelForLoginItemsSpace.setLayout(null);
        panelForLoginItemsSpace.setBackground(Color.WHITE);


        panelForImage.setBounds(500, 0, frame.getWidth() / 2, frame.getHeight());
        panelForImage.setLayout(null);
        panelForImage.setBackground(Color.WHITE);

        final int panelMidWidth = panelForLoginItemsSpace.getWidth() / 2;
        final int panelMidHeight = panelForLoginItemsSpace.getHeight() / 2;


        Font robotoMono = new Font("Roboto Mono", Font.BOLD, 13);

        /*Create panel´s items*/

        JSeparator lineSeparatorFromLogin = new JSeparator();

        JLabel labelForTitle = new JLabel("Login");

        JButton sendRequestButton = new JButton("Ingresar");

        JTextArea nameTextArea = new JTextArea();

        JTextArea passwordTextArea = new JTextArea();


        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\123\\Desktop\\Escritorio\\foro.me\\src\\main\\resources\\media\\backgroundImage.png");
        Image imageResizable = backgroundImage.getImage().getScaledInstance(350, 850, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(imageResizable);


        JLabel labelForImage = new JLabel();



        /*Add items to the panelForLoginItemsSpace*/
        panelForLoginItemsSpace.add(lineSeparatorFromLogin);
        panelForLoginItemsSpace.add(labelForTitle);
        panelForLoginItemsSpace.add(sendRequestButton);
        panelForLoginItemsSpace.add(responseTextArea);
        panelForLoginItemsSpace.add(nameTextArea);
        panelForLoginItemsSpace.add(passwordTextArea);

        panelForImage.add(labelForImage);

        /*Items Config*/

        sendRequestButton.setBounds(panelMidWidth - 100, panelForLoginItemsSpace.getHeight() - 500, 200, 50);
        sendRequestButton.setBackground(Color.decode("#484D7B"));
        sendRequestButton.setOpaque(true);
        sendRequestButton.setBorderPainted(false);
        sendRequestButton.setForeground(Color.WHITE);
        sendRequestButton.setFont(new Font("Arial", Font.BOLD, 20));



        labelForTitle.setBounds(panelForLoginItemsSpace.getWidth() - 300, panelForLoginItemsSpace.getHeight() - 800, 400, 80);
        labelForTitle.setForeground(Color.BLACK);
        labelForTitle.setFont(new Font("Roboto Mono", Font.BOLD, 24));

        lineSeparatorFromLogin.setBounds(panelForLoginItemsSpace.getWidth() - 450, panelForLoginItemsSpace.getHeight() - 710, 400, 5);
        lineSeparatorFromLogin.setBackground(Color.BLACK);

        nameTextArea.setBounds(panelMidWidth - 125, panelForLoginItemsSpace.getHeight() - 670, 250, 30 );
        nameTextArea.setBorder(BorderFactory.createLineBorder(Color.decode("#979797")));
        nameTextArea.setText("Usuario");
        nameTextArea.setFont(robotoMono);

        passwordTextArea.setBounds(panelMidWidth - 125, panelForLoginItemsSpace.getHeight() - 600, 250, 30 );
        passwordTextArea.setBorder(BorderFactory.createLineBorder(Color.decode("#979797")));
        passwordTextArea.setText("Contraseña");
        passwordTextArea.setFont(robotoMono);



        labelForImage.setIcon(backgroundImage);
        labelForImage.setBounds((panelForImage.getWidth() / 2) - 175, panelForImage.getHeight() - 800, 350, 900 );





        nameTextArea.addMouseListener(new MouseAdapter() {
            String innerText = nameTextArea.getText();
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(innerText.equals("Usuario")){
                    nameTextArea.setText("");
                    innerText = nameTextArea.getText();
                }
            }
        });

        passwordTextArea.addMouseListener(new MouseAdapter() {
             String innerText = passwordTextArea.getText();
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(innerText.equals("Contraseña")){
                    passwordTextArea.setText("");
                    innerText = passwordTextArea.getText();
                }
            }
        });

        sendRequestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                /*Visual Effects*/
                sendRequestButton.setBackground(Color.decode("#313661"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                /*Visual Effects*/
                sendRequestButton.setBackground(Color.decode("#484D7B"));
            }

        });

        /*This function use de sendHttpRequest method from de HttpRequestHandler
        * for send a request */

        sendRequestButton.addActionListener(e -> {

            /*Get the values from the name and password textArea´s*/

            String name = nameTextArea.getText();
            String password = passwordTextArea.getText();


            /*Send de request to the endpoint*/
            try{

                String response = HttpRequestHandler.sendLoginRequest(name, password);
                ForoFrame foroframe = new ForoFrame(response);
                foroframe.setVisible(true);
                frame.dispose();

            }catch (Exception exception){
                JOptionPane.showMessageDialog(frame, "Perdón, algo inesperado ha sucedido, Intentalo de nuevo");
            }




        });



    }




}
