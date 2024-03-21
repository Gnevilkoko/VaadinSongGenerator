package me.gnevilkoko.data;

import me.gnevilkoko.Application;

import java.io.*;

public class YamlReader implements Reader {
    private String filePath;

    public YamlReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() {
        File sourceFile = new File(getFullPath());
        if(!sourceFile.exists()){
            initIfNotExist();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(getFullPath()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initIfNotExist() {
        try (InputStream inputStream = Application.class.getResourceAsStream(File.separator + "yamls" + File.separator + filePath);
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException ignore) {}
    }

    private String getFullPath(){
        return System.getProperty("user.dir")+File.separator+filePath;
    }
}
