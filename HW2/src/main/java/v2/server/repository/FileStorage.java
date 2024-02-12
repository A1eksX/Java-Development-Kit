package v2.server.repository;

import java.io.*;

public class FileStorage implements Repository {
    private final String PATH = "log.txt" ;
    @Override
    public void save(String text){
        try(FileWriter writer = new FileWriter(PATH, true)) {
            writer.write(text);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String read() {
        StringBuilder builder = new StringBuilder();
        File message = new File(PATH);
        if(message.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(message))) {
                while (reader.ready()){
                    builder.append(reader.readLine()).append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return builder.toString();
    }
}
