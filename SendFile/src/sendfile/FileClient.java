package sendfile;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileClient {

    private Socket s;

    public FileClient(String host, int port, String dir) {
        try {
            s = new Socket(host, port);
            sendFile(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String dir) throws IOException {
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        File file = new File(dir);
        FileInputStream fis = new FileInputStream(file);

        dos.writeLong(file.length());

        byte[] buffer = new byte[4096];

        while (fis.read(buffer) > 0) {
            dos.write(buffer);
        }

        fis.close();
        dos.close();

    }

    public static void main(String[] args) {
        FileClient fc = new FileClient("localhost", 1988, "src//ClientFile//1.jpg");
    }

}
