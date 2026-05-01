# 🛡️ Proyecto Seguridad JWT & Dashboard Financiero
Este proyecto implementa un sistema de autenticación **Stateless** utilizando **Spring Boot**, **OAuth2** y **JWT** con firmas **RSA**.

## 🚀 Funcionalidades
El servidor orquesta múltiples servicios externos para ofrecer un dashboard informativo:
*   **Seguridad:** Validación de identidad mediante tokens Bearer.
*   **Finanzas:** Precio del **Bitcoin** (Binance) y **Dólar observado** (DolarAPI Chile).
*   **Clima:** Información meteorológica de **Santiago** en tiempo real (Open-Meteo).
*   **Sistema:** Fecha y hora exacta del servidor.

## 🛠️ Endpoints Protegidos
Recuerda enviar el Header `Authorization: Bearer <tu_token>` para acceder:
1.  `GET /api/actividad/hola` -> Mensaje de bienvenida base.
2.  `GET /api/actividad/dashboard` -> Dashboard con clima y finanzas.

---
**Desarrollado por:** Diego Alexis Muñoz Hormazabal
**Institución:** Duoc UC - Ingeniería en Informática (2026)