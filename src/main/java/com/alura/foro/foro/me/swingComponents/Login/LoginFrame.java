package com.alura.foro.foro.me.swingComponents.Login;
import com.alura.foro.foro.me.httpHandlers.HttpRequestHandler;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    JTextArea responseTextArea = new JTextArea();
    JFrame frame = new JFrame();

    public LoginFrame() {

        /*
        * Set de frame config

        */

        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        /*Add panel to the frame*/

        JPanel panel = new JPanel();
        frame.add(panel);

        /*Panel config*/

        panel.setSize(frame.getWidth(), frame.getHeight());
        panel.setLayout(null);
        panel.setBackground(Color.decode("#343541"));

        /*Create panel items*/

        JButton sendRequestButton = new JButton("Send Request");
        JTextArea nameTextArea = new JTextArea();
        JTextArea passwordTextArea = new JTextArea();


        /*Add items to the panel*/

        panel.add(sendRequestButton);
        panel.add(responseTextArea);
        panel.add(nameTextArea);
        panel.add(passwordTextArea);

        /*Items Config*/

        sendRequestButton.setBounds(panel.getWidth() - 350, panel.getHeight() - 200, 200, 50);
        sendRequestButton.setBackground(Color.RED);
        sendRequestButton.setForeground(Color.BLACK);

        nameTextArea.setBounds(panel.getWidth() - 350, panel.getHeight() - 700, 200, 70 );
        passwordTextArea.setBounds(panel.getWidth() - 350, panel.getHeight() - 600, 200, 70 );


        /*This function use de sendHttpRequest method from de HttpRequestHandler
        * for send a request */

        sendRequestButton.addActionListener(e -> {

            /*Get the values from the name and password textArea´s*/

            String name = nameTextArea.getText();
            String password = passwordTextArea.getText();


            /*Send de request to the endpoint*/
            String response = HttpRequestHandler.sendHttpRequest(name, password);
            JOptionPane.showMessageDialog(frame, response);
            System.out.println(response);

        });



    }




}
