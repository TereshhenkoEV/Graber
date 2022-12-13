import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main{
    private static final String URL = "https://www.nalog.gov.ru/rn77/rss/";

    public static void main(String args[]) {

        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            URL url = new URL(URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                File file = new File("test.xml");
                outputStream = new FileOutputStream(file);
                int byteRead = -1;
                byte[] buffer = new byte[1024];
                while ((byteRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0,byteRead);
                }
            }
        } catch (IOException e) {
            System.out.println("Нет соединения " + e.toString());
        } finally {
            try {
                inputStream.close();
                outputStream.close();

            } catch (IOException e) {}
        }

    }
}