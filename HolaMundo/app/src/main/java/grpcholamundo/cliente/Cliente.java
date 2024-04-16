package grpcholamundo.cliente;

import com.proto.saludo.SaludoServiceGrpc;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;

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
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);

        // Construimos la petición envíando un parametro
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("César").build();

        // Usando el stub, realizamos la llamada RPC
        SaludoResponse respuesta = stub.saludo(peticion);

        // Imprimimos la respuesta de RPC
        System.out.println("Respuesta RPC: " + respuesta.getResultado());

        // Terminamos la comunicación
        System.out.println("Apagando...");
        ch.shutdown();
    }
}



