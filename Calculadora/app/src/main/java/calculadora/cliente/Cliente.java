package calculadora.cliente;

import com.proto.calculadora.CalculadoraServiceGrpc;

import javax.swing.JOptionPane;

import com.proto.calculadora.Calculadora.CalculadoraRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
        // Establecemos el servidor gRPC
        String host = "localhost";

        // Establecemos el puerto gRPC
        int puerto = 8081;

        // Creamos el canal de comunicación
        ManagedChannel ch = ManagedChannelBuilder
                .forAddress(host, puerto)
                .usePlaintext()
                .build();
        
        // Obtenemos la referencia al stub
        CalculadoraServiceGrpc.CalculadoraServiceBlockingStub stub = CalculadoraServiceGrpc.newBlockingStub(ch);

        // Iniciamos un ciclo para realizar varias operaciones
        while (true) {
                
            // Se presenta el menú
            String opt = JOptionPane.showInputDialog(
                "Calcular\n" +
                                "Suma............. (1)\n" +
                                "Resta............ (2)\n" +
                                "Multiplicación... (3)\n" +
                                "División......... (4)\n" +
                                "Cancelar para salir");
            
            // Si no selecciona nada, termina
            if (opt == null) {
                break;
            }

            // Obtenemos los dos parametros de la operación
            int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese a"));
            int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese b"));

            CalculadoraRequest peticion = CalculadoraRequest.newBuilder().setA(a).setB(b).build();

            // Menú de operaciones
            switch (opt) {
                case "1":
                    JOptionPane.showMessageDialog(null, a + "+" + b + " = " + stub.sum(peticion).getResultado());
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, a + "-" + b + " = " + stub.res(peticion).getResultado());
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, a + "*" + b + " = " + stub.mul(peticion).getResultado());
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, a + "/" + b + " = " + stub.div(peticion).getResultado());
                    break;
            
                default:
                    break;
            }
        }

        // Terminamos la comunicación
        System.out.println("Apagando...");
        ch.shutdown();
    }
}
