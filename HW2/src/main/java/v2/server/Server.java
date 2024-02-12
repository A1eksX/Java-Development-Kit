package v2.server;
import v2.exception.ExceptionAuthorize;
import v2.client.Client;
import v2.server.repository.Repository;
import v2.server.ui.ServerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Задача: Создать простейшее окно управления сервером (по сути, любым),
 * содержащее две кнопки (JButton) – запустить сервер и остановить сервер.
 * Кнопки должны просто логировать нажатие (имитировать запуск и
 * остановку сервера, соответственно) и выставлять внутри интерфейса
 * соответствующее булево isServerWorking.
 */
public class Server {
    List<Client> clientList;
    List<String> clientName;
    Repository repository;
    ServerView serverView;
    private boolean isServerWorking = false;

    public Server(Repository repository, ServerView serverView) {
        clientList = new ArrayList<>();
        clientName = new ArrayList<>();

        this.repository = repository;
        this.serverView = serverView;
    }

    public String getHistory(){
        return repository.read();
    }

    public void sendMessage(String text){
        if (!isServerWorking){
            return;
        }
        serverView.messageFieldArea(text);
        repository.save(text);
        messageUsers(text);
    }
    private void messageUsers(String text){
        for (Client client:clientList) {
            client.updateTextUser(text);
        }
    }
    public boolean getStateServer(){
        return isServerWorking;
    }
    public void setStateServer(boolean state){
        isServerWorking = state;
    }
    public void disconnectAllUsers(){
        clientList.forEach(Client::disconnectFromServer);
        clientList.clear();
        clientName.clear();
    }

    public boolean autorize(Client client, String login) throws ExceptionAuthorize {
        if (isServerWorking){
            if (clientName.contains(login)){
                throw new ExceptionAuthorize(login + " Такое имя уже занято");
            }
            clientList.add(client);
            clientName.add(login);
            serverView.messageFieldArea(login + " подключился\n");
            return true;
        }
        return false;
    }
}