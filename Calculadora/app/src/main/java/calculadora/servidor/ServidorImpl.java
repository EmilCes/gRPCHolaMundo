package calculadora.servidor;

import com.proto.calculadora.Calculadora.CalculadoraRequest;
import com.proto.calculadora.Calculadora.CalculadoraResponse;
import com.proto.calculadora.CalculadoraServiceGrpc.CalculadoraServiceImplBase;

import io.grpc.stub.StreamObserver;

public class ServidorImpl extends CalculadoraServiceImplBase {
    
    @Override
    public void sum(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        int resultado = request.getA() + request.getB();

        CalculadoraResponse respuesta = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        
        responseObserver.onNext(respuesta);

        responseObserver.onCompleted();
    }

    @Override
    public void res(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        int resultado = request.getA() - request.getB();

        CalculadoraResponse respuesta = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        
        responseObserver.onNext(respuesta);

        responseObserver.onCompleted();
    }

    @Override
    public void mul(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        int resultado = request.getA() * request.getB();

        CalculadoraResponse respuesta = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        
        responseObserver.onNext(respuesta);

        responseObserver.onCompleted();
    }

    @Override
    public void div(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        int resultado = request.getA() / request.getB();

        CalculadoraResponse respuesta = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        
        responseObserver.onNext(respuesta);

        responseObserver.onCompleted();
    }

}

