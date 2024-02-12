package v2.server.ui;

import v2.server.Server;
import v2.server.repository.Repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGI extends JFrame implements ServerView{
    Server server;

    private static final int WIDTH = 350;
    private static final int HEIGTH = 400;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea hideFieldAreaMessage = new JTextArea();
    private final JTextArea serverMessenges = new JTextArea();


    public ServerGI(Repository repository){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setTitle("Chat server");

        mainPanel();

        server = new Server(repository, this);
    }

    private Component mainPanel(){

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                server.setStateServer(true);
                serverMessenges.append("Сервер запущен\n");
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.setStateServer(false);
                serverMessenges.append("Сервер остановлен\n");
                server.disconnectAllUsers();
            }
        });

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(btnStart);
        panel.add(btnStop);

        add(panel, BorderLayout.SOUTH);
        add(serverMessenges);
        setVisible(true);

        return panel;
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    public void messageFieldArea(String text) {
        serverMessenges.append(text);
    }
}
