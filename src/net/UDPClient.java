import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public static Integer port = 11115;
    public static String address;
    public static void main(String[] args) throws Exception {
        System.out.print("Input your port: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        port = Integer.valueOf(br.readLine());
        System.out.print("Input server address: ");
        address = br.readLine();
        MessageSenderC producer = new MessageSenderC(port, address);
        MessageGetterC consumer = new MessageGetterC(port);


        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class MessageSenderC implements Runnable {
    Integer port;
    String address;
    MessageSenderC(Integer p, String addr) {
        port = p;
        address = addr;
    }

    private static DatagramPacket encodePacket(String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        return new DatagramPacket(bytes, bytes.length);
    }
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            String msg = null;
            try {
                msg = port.toString() + ":" + br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            try {
                DatagramSocket socket = new DatagramSocket();
                DatagramPacket packet = encodePacket(msg);
                packet.setSocketAddress(new InetSocketAddress(address, 11111));
                socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class MessageGetterC implements Runnable {
    private static String decodePacket(DatagramPacket packet) {
        return new String(
                packet.getData(),
                packet.getOffset(),
                packet.getOffset() + packet.getLength(),
                StandardCharsets.UTF_8);
    }
    Integer port;
    MessageGetterC(Integer p) {
        port = p;
    }
    public void run() {
        while (true) {

            try (DatagramSocket socket = new DatagramSocket(port)) {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String msg = decodePacket(packet);
                System.out.println(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
