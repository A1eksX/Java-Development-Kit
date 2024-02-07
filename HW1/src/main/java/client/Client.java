package client;

import exception.ExceptionAuthorize;
import server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame {
    private ServerWindow server;
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
    private final Client user;

    public Client(ServerWindow server){

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
                    if (server.autorize(user, login.getText())){
                        textArea.append("Соединение установлено\n");
                        textArea.append(server.hystoryMessage().getText());
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
                if (server.workServer()){
                    message();

                }else {
                    textArea.append("Сервер недоступен\n");
                    connectServer();
                }
            }
        });

        fieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){

                        if (server.workServer()){
                            message();
                        }else {
                            textArea.append("Сервер недоступен\n");
                        }
                }
            }
        });
    }

    public void message(){
        String text = fieldMessage.getText();
//        textArea.append(login.getText() + " : "+ text + "\n");         // заносим в поле клиента
        server.message(login.getText() + " : "+ text + "\n");     // записываем в лог-файл

        fieldMessage.setText("");                                      // очистка поля
    }


    public void updateTextUser(String text){
        textArea.append(text);
    }

    public void connectServer(){
        if (!server.workServer()){
            textArea.append("Вы отключены от сервера\n");
        }
    }
    public void setTopPanel(){
        topPanel.setVisible(true);
    }
}

