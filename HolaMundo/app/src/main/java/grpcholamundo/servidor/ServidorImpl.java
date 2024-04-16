package grpcholamundo.servidor;

import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc.SaludoServiceImplBase;

import io.grpc.stub.StreamObserver;

public class ServidorImpl extends SaludoServiceImplBase {
    
    @Override
    public void saludo(SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        // Se construye la respuesta a enviarle al cliente
        SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado("Hola " + request.getNombre()).build();

        // En gRPC se utilzia onNext para enviar la respuesta
        // En llamadas unarias, solo se llama una vez
        responseObserver.onNext(respuesta);

        // Avisa que se ha terminado
        responseObserver.onCompleted();
    }

}


