package cl.tellevo.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRouteResponse {
    @JsonProperty("rutaId")
    private final int rutaId;

    @JsonProperty("fechaPago")
    private final String fechaPago;

    @JsonProperty("fechaRuta")
    private final String fechaRuta;

    @JsonProperty("txtOrigen")
    private final String txtOrigen;

    @JsonProperty("txtDestino")
    private final String txtDestino;

    @JsonProperty("nombreConductor")
    private final String nombreConductor;

    @JsonProperty("idPasajeroPago")
    private final int idPasajeroPago;

    @JsonProperty("nombrePagador")
    private final String nombrePagador;

    public PaymentRouteResponse(int rutaId, String fechaPago, String fechaRuta,
                                String txtOrigen, String txtDestino, String nombreConductor,
                                int idPasajeroPago, String nombrePagador) {
        this.rutaId = rutaId;
        this.fechaPago = fechaPago;
        this.fechaRuta = fechaRuta;
        this.txtOrigen = txtOrigen;
        this.txtDestino = txtDestino;
        this.nombreConductor = nombreConductor;
        this.idPasajeroPago = idPasajeroPago;
        this.nombrePagador = nombrePagador;
    }

    // Getters
    public int getRutaId() { return rutaId; }
    public String getFechaPago() { return fechaPago; }
    public String getFechaRuta() { return fechaRuta; }
    public String getTxtOrigen() { return txtOrigen; }
    public String getTxtDestino() { return txtDestino; }
    public String getNombreConductor() { return nombreConductor; }
    public int getIdPasajeroPago() { return idPasajeroPago; }
    public String getNombrePagador() { return nombrePagador; }
}