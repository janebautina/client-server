package timeserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Handler implements Runnable {
  
  private Socket connection;
  
  Handler(Socket connection) {
    this.connection = connection;
  }
  
  @Override
  public void run() {
    try {
      BufferedReader br = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
      OutputStreamWriter osw = 
          new OutputStreamWriter(connection.getOutputStream());
      while (!connection.isClosed()) {
        String str = br.readLine();
  
        if (str.equals("hello")) {
          osw.append("hi\n");
        } else if (str.equals("bye")) {
          osw.append("good bye\n");
          osw.flush();
          break;
        } else {
          osw.append("error\n");
        }
        osw.flush();
      }
      connection.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    }

  
}
