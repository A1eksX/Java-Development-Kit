package v2;
import v2.clientGUI.ClientGUI;
import v2.server.repository.FileStorage;
import v2.server.repository.Repository;
import v2.server.ui.ServerGI;
/**
 * 1. Реализовать клиент-серверное приложение. Начало его можно увидеть в презентации к первому уроку,
 *    а можно ориентироваться на скриншоты. Результат можно увидеть на скриншотах, которые также можно
 *    найти в материалах к уроку
 * 2. Клиентское приложение должно отправлять сообщения из текстового поля сообщения в серверное приложение
 *    по нажатию кнопки или по нажатию клавиши Enter на поле ввода сообщения; *
 * 3. Продублировать импровизированный лог (историю) чата в файле;
 * 4. При запуске клиента чата заполнять поле истории из файла, если он существует. Обратите внимание,
 * что чаще всего история сообщений хранится на сервере и заполнение истории чата лучше делать при соединении
 * с сервером, а не при открытии окна клиента.
 */
public class Main {
    public static void main(String[] args) {
        Repository repository = new FileStorage();
        ServerGI serverGI = new ServerGI(repository);

        new ClientGUI(serverGI.getServer());
        new ClientGUI(serverGI.getServer());
    }
}