package cl.tellevo.admin.controller;

import cl.tellevo.admin.model.PaymentRouteResponse;
import cl.tellevo.admin.service.GrpcDashboardClient;
import dashboard.Dashboard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final GrpcDashboardClient grpcClient;

    public DashboardController(GrpcDashboardClient grpcClient) {
        this.grpcClient = grpcClient;
    }

    @GetMapping("/overview")
    public ResponseEntity<Map<String, Object>> getOverview() {
        Dashboard.EstadisticasGeneralesReply stats = grpcClient.obtenerEstadisticas();

        Map<String, Object> response = Map.of(
                "usuariosActivos", stats.getUsuariosActivos(),
                "viajesCompletados", stats.getViajesCompletados(),
                "kmsCompartidos", stats.getKmsCompartidos(),
                "co2Ahorrado", stats.getCo2Ahorrado(),
                "pagosRealizados", stats.getPagosRealizados()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/payment-routes")
    public ResponseEntity<List<PaymentRouteResponse>> getPaymentRoutes() {
        try {
            Dashboard.PaymentRoutesReply reply = grpcClient.obtenerRutasDePago();

            List<PaymentRouteResponse> response = reply.getRoutesList().stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Loggear el error
            return ResponseEntity.internalServerError().build();
        }
    }

    private PaymentRouteResponse convertToResponse(Dashboard.PaymentRoute route) {
        return new PaymentRouteResponse(
                route.getRutaId(),
                route.getFechaPago(),
                route.getFechaRuta(),
                route.getTxtOrigen(),
                route.getTxtDestino(),
                route.getNombreConductor(),
                route.getIdPasajeroPago(),
                route.getNombrePagador()
        );
    }
}