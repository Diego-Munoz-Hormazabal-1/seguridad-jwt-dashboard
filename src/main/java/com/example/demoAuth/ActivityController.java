package com.example.demoAuth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/actividad")
public class ActivityController {

    /**
     * 1. ACTIVIDAD BASE: "Hola Mundo"
     * Cumple con el requisito mínimo de la unidad 2.1.
     */
    @GetMapping("/hola")
    public Map<String, String> sayHello() {
        return Map.of(
            "mensaje", "Hola Mundo desde Duoc UC",
            "estado", "Autenticado con éxito",
            "unidad", "2.1 - Seguridad con JWT"
        );
    }

    /**
     * 2. VERSIÓN PRO: Dashboard informativo
     * Muestra clima, dólar, bitcoin y hora actual.
     */
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        Map<String, Object> respuesta = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        // Fecha y Hora
        LocalDateTime now = LocalDateTime.now();
        respuesta.put("fecha_actual", now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        respuesta.put("hora_actual", now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // Clima (Open-Meteo)
        try {
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=-33.4569&longitude=-70.6483&current_weather=true";
            Map<String, Object> weatherData = restTemplate.getForObject(weatherUrl, Map.class);
            if (weatherData != null && weatherData.containsKey("current_weather")) {
                respuesta.put("clima_santiago", weatherData.get("current_weather"));
            }
        } catch (Exception e) { respuesta.put("clima_error", "No disponible"); }

        // Dólar CLP (DolarAPI)
        try {
            String dolarUrl = "https://cl.dolarapi.com/v1/cotizaciones/usd";
            Map<String, Object> dolarData = restTemplate.getForObject(dolarUrl, Map.class);
            if (dolarData != null && dolarData.containsKey("venta")) {
                respuesta.put("precio_dolar_clp", dolarData.get("venta"));
            }
        } catch (Exception e) { respuesta.put("dolar_error", "No disponible"); }

        // Bitcoin (Binance)
        try {
            String btcUrl = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
            Map<String, Object> btcData = restTemplate.getForObject(btcUrl, Map.class);
            if (btcData != null && btcData.containsKey("price")) {
                respuesta.put("precio_bitcoin_usd", "$" + btcData.get("price"));
            }
        } catch (Exception e) { respuesta.put("bitcoin_error", "No disponible"); }

        respuesta.put("usuario_solicitante", "Diego Alexis Muñoz"); 
        respuesta.put("institucion", "Duoc UC");

        return respuesta;
    }
}
