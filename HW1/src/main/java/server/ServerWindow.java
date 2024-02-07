package server;

import client.Client;
import exception.ExceptionAuthorize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Задача: Создать простейшее окно управления сервером (по сути, любым),
 * содержащее две кнопки (JButton) – запустить сервер и остановить сервер.
 * Кнопки должны просто логировать нажатие (имитировать запуск и
 * остановку сервера, соответственно) и выставлять внутри интерфейса
 * соответствующее булево isServerWorking.
 */
public class ServerWindow extends JFrame {
    private static final int WIDTH = 350;
    private static final int HEIGTH = 400;
    public final String PATH = "log.txt" ;             // "src/main/java/server/log.txt";
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea fieldAreaMessage = new JTextArea();
    private final JTextArea serverMessenges = new JTextArea();

    List<Client> clientList;
    private boolean isServerWorking = false;

    public ServerWindow(){
        clientList = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setTitle("Chat server");

        mainPanel();

    }
    private Component mainPanel(){

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                isServerWorking = true;
                readLogFile();
                serverMessenges.append("Сервер запущен\n");
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                serverMessenges.append("Сервер остановлен\n");
                clientList.forEach(Client::connectServer);
                clientList.forEach(Client::setTopPanel);
            }
        });

//        fieldAreaMessage.setLineWrap(true);
//        fieldAreaMessage.setWrapStyleWord(true);


        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(btnStart);
        panel.add(btnStop);

        add(panel, BorderLayout.SOUTH);
 //       add(fieldAreaMessage);
        add(serverMessenges);
        setVisible(true);

        return panel;
    }

    public JTextArea hystoryMessage(){
        return fild();
    }

    public void message(String text){
        if (!isServerWorking){
            return;
        }
        addTextInTextArea(text);
        saveInLogFile(text);
        messageUsers(text);
    }
    private void messageUsers(String text){
        for (Client client:clientList) {
            client.updateTextUser(text);
        }
    }
    public boolean workServer(){
        if (isServerWorking){
            return true;
        }else {
            return false;
        }
    }

    private JTextArea readLogFile(){
        File message = new File(PATH);
        if(message.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(message))) {
                while (reader.ready()){
                    fieldAreaMessage.append(reader.readLine() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fieldAreaMessage;
    }

    private JTextArea fild(){
        return fieldAreaMessage;
    }


    public boolean autorize(Client client, String login) throws ExceptionAuthorize {
        if (isServerWorking){
            if (clientList.contains(login)){
                throw new ExceptionAuthorize(login + " Такое имя уже занято");
            }
            clientList.add(client);
            serverMessenges.append(login + " подключился\n");

            return true;
        }
        return false;
    }

    // выводим текст клиентов на сервере
    private void addTextInTextArea(String text){
        serverMessenges.append(text);
    }

    // записываем в лог-файл
    private void saveInLogFile(String text){
        try(FileWriter file = new FileWriter(PATH, true)) {
            file.write(text );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}