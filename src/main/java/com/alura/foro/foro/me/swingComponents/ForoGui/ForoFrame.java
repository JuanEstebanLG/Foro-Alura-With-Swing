package com.alura.foro.foro.me.swingComponents.ForoGui;

import com.alura.foro.foro.me.infra.exceptions.TokenNullException;
import com.alura.foro.foro.me.infra.exceptions.TokenSubjectNullException;
import com.alura.foro.foro.me.infra.exceptions.TokenVerificationException;
import com.alura.foro.foro.me.infra.services.JwtService;


import javax.swing.*;



public class ForoFrame extends  JFrame {
    private JPanel BackGround;
    private JPanel NavBar;
    private JButton settings;
    private JTextField postBox;
    private JPanel rightMenu;
    private JPanel main;
    private JLabel labelTopics;
    private JLabel labelTrends;
    private JLabel labelNewDiscutions;
    private JLabel labelOut;
    private JLabel userLabel;
    private JButton sendButton;
    private JPanel postSection;

    private final JwtService tokenService;



    public ForoFrame(String tokenTarget) throws TokenVerificationException, TokenSubjectNullException, TokenNullException {
        tokenService = new JwtService();
        setContentPane(BackGround);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1500, 1100);
        setLocationRelativeTo(null);
        postBox.setSize(1000, 500);
        NavBar.setSize(1000, 100);
        settings.setSize(100,100 );

        postSection.setSize(1400, 100);


        String token = tokenTarget.replace("JWT", "").replace( ":", "")
                .replace("\"", "").replace("{", "").replace("}", "");
        System.out.println(token);
        String usuario =  tokenService.getSubject(token);
        userLabel.setText(usuario);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
