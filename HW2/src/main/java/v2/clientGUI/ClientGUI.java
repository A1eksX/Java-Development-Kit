package v2.clientGUI;


import v2.client.Client;
import v2.exception.ExceptionAuthorize;
import v2.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame implements ClientView {
    private Server server;
    private Client client;
    private static final int WIDTH = 450;
    private static final int HEIGTH = 350;

    private final JTextArea textArea = new JTextArea(15, 50);

    private JPanel topPanel = new JPanel(new GridLayout());
    private JPanel spacePanel = new JPanel(new GridLayout());
    private JPanel sendPanel = new JPanel(new BorderLayout());
    private JPanel panelLogin = new JPanel(new GridLayout(2, 1));
    private JPanel modePanel = new JPanel(new GridLayout(2, 2));

    private final JButton lgnButton = new JButton("login");
    private final JButton sendButton = new JButton("Send");
    private final JTextField ip = new JTextField("127.0.0.1");
    private final JTextField port = new JTextField("8189");
    private final JTextField login = new JTextField();
    private final JPasswordField password = new JPasswordField("123456");
    private final JTextField fieldMessage = new JTextField();
    private ClientGUI user;

    public ClientGUI(Server server){

        client = new Client(this, server);

        this.server = server;
        user = this;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setTitle("Chat client");

        modePanel.add(ip);
        modePanel.add(port);
        modePanel.add(login);
        modePanel.add(password);

        panelLogin.add(spacePanel, BorderLayout.NORTH); // пустая панель
        panelLogin.add(lgnButton, BorderLayout.SOUTH);  // панель с кнопкой Login

        topPanel.add(modePanel, BorderLayout.WEST);     // верхняя панель
        topPanel.add(panelLogin, BorderLayout.EAST);    // верхняя панель

        sendPanel.add(fieldMessage, BorderLayout.CENTER); // панель ввода текста
        sendPanel.add(sendButton, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(sendPanel, BorderLayout.SOUTH);
        add(scrollPane);

        setVisible(true);

        lgnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (server.autorize(client, login.getText())){
                        textArea.append("Соединение установлено\n");
                        textArea.append(client.historyServer());
                        topPanel.setVisible(false);
                    }else {
                        textArea.append("Сервер недоступен\n");
                    }
                } catch (ExceptionAuthorize ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getStateServer()){
                    sendMessageToServer();

                }else {
                    textArea.append("Сервер недоступен\n");
                }
            }
        });

        fieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (server.getStateServer()){
                        sendMessageToServer();
                    }else {
                        textArea.append("Сервер недоступен\n");
                    }
                }
            }
        });
    }

    public void sendMessageToServer(){
        client.sendMessage(login.getText() + ": " + fieldMessage.getText() + "\n");
        fieldMessage.setText("");
    }

    public void setVisibleTopPanel(){
        topPanel.setVisible(true);
    }

    @Override
    public void showMessage(String message) {
        textArea.append(message);
    }
}
