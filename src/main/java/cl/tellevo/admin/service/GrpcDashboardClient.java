package cl.tellevo.admin.service;

import dashboard.Dashboard;
import dashboard.DashboardServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrpcDashboardClient {

    @Value("${grpc.host:localhost}")
    private String host;

    @Value("${grpc.port:50052}")
    private int port;

    @Value("${grpc.auth.key}")
    private String key;

    public Dashboard.EstadisticasGeneralesReply obtenerEstadisticas() {
        ManagedChannel channel = createChannel();
        try {
            DashboardServiceGrpc.DashboardServiceBlockingStub stub =
                    DashboardServiceGrpc.newBlockingStub(channel);

            Dashboard.DashBoardRequest request = Dashboard.DashBoardRequest.newBuilder()
                    .setKey(key)
                    .build();

            return stub.dashBoardData(request);
        } finally {
            channel.shutdown();
        }
    }

    public Dashboard.PaymentRoutesReply obtenerRutasDePago() {
        ManagedChannel channel = createChannel();
        try {
            DashboardServiceGrpc.DashboardServiceBlockingStub stub =
                    DashboardServiceGrpc.newBlockingStub(channel);

            Dashboard.DashBoardRequest request = Dashboard.DashBoardRequest.newBuilder()
                    .setKey(key) // Usamos la misma key configurada
                    .build();

            return stub.getPaymentRoutes(request);
        } finally {
            channel.shutdown();
        }
    }

    private ManagedChannel createChannel() {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext() // Solo para desarrollo, en producción usar SSL/TLS
                .build();
    }
}