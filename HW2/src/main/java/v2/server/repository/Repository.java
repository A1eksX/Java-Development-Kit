package v2.server.repository;

public interface Repository {
    String read();
    void save(String text);
}
