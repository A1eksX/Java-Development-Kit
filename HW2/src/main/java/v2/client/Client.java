package v2.client;

import v2.clientGUI.ClientView;
import v2.server.Server;

public class Client {
    private ClientView clientView;
    private Server server;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }
    public void disconnectFromServer(){
        clientView.showMessage("Вы отключены от сервера\n");
        clientView.setVisibleTopPanel();
    }
    public void updateTextUser(String text){
        clientView.showMessage(text);
    }
    public String historyServer(){
        return server.getHistory();
    }
    public void sendMessage(String text){
        server.sendMessage(text);
    }
}
