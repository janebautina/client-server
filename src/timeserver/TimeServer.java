package timeserver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer
{
  public static void main(String[] args)
  {
    try
    {
      ServerSocket socket = new ServerSocket();
      socket.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 12345));
      while (true) {
        Socket connection = socket.accept();
        new Thread(new Handler(connection)).start();
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
