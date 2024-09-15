package br.com.fiap.reservaSalaAPI.controller;

import br.com.fiap.reservaSalaAPI.Empresa;
import br.com.fiap.reservaSalaAPI.SalaDeReuniao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/fiap/salas")
public class ReservaSalaController {

    private final Empresa empresa;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

    public ReservaSalaController(Empresa empresa) {
        this.empresa = empresa;
    }

    @PostMapping
    public ResponseEntity<SalaDeReuniao> criarSalaDeReuniao(@RequestBody SalaDeReuniao sala) {
        return ResponseEntity.ok(empresa.criarSalaDeReuniao(sala.getTipo()));
    }

    @GetMapping
    public ResponseEntity<List<SalaDeReuniao>> buscarSalasDaEmpresa() {
        return ResponseEntity.ok(empresa.getSalas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDeReuniao> buscarSalaDeReuniao(@PathVariable int id) {
        return ResponseEntity.ok(empresa.getSalas().get(id));
    }

    @GetMapping("/{id}/reservas")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> buscarReservasEntreDatas(
            @PathVariable int id,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim) {

        SalaDeReuniao sala = empresa.getSalas().get(id);

        if (tipo != null && tipo.equals("ordenado")) {
            return ResponseEntity.ok(sala.listarReservasOrdenadas());
        }

        if (dataInicio != null && dataFim != null) {
            LocalDateTime dataInicioFormatada = LocalDateTime.parse(dataInicio, formatter);
            LocalDateTime dataFimFormatada = LocalDateTime.parse(dataFim, formatter);
            return ResponseEntity.ok(sala.listarReservasEntreDatas(dataInicioFormatada, dataFimFormatada));
        } else {
            return ResponseEntity.ok(sala.listarReservas());
        }
    }

    @PostMapping("{id}/reservas")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> criarReserva(
            @PathVariable int id,
            @RequestBody Map<String, Set<String>> reservas) {

        Map<LocalDateTime, Set<String>> reservasCriadas = new HashMap<>();
        SalaDeReuniao sala = empresa.getSalas().get(id);

        reservas.forEach((dataReuniao, participantes) -> {
            LocalDateTime dataReuniaoFormatted = LocalDateTime.parse(dataReuniao, formatter);
            sala.reservarSala(dataReuniaoFormatted, participantes);
            reservasCriadas.put(dataReuniaoFormatted, participantes);
        });
        return ResponseEntity.ok(reservasCriadas);
    }

    @GetMapping("/{id}/reservas/{dataReserva}")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> buscarReservaPorData(
            @PathVariable int id,
            @PathVariable String dataReserva) {

        LocalDateTime formattedDate = LocalDateTime.parse(dataReserva, formatter);
        SalaDeReuniao sala = empresa.getSalas().get(id);
        return ResponseEntity.ok(sala.listarReservaPorData(formattedDate));
    }

    @DeleteMapping("/{id}/reservas/{dataReserva}")
    public ResponseEntity<Void> deletarReservaPorData(
            @PathVariable int id,
            @PathVariable String dataReserva) {

        LocalDateTime formattedDate = LocalDateTime.parse(dataReserva, formatter);
        SalaDeReuniao sala = empresa.getSalas().get(id);
        sala.cancelarReserva(formattedDate);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reservas/{dataReserva}")
    public ResponseEntity<Void> atualizarReserva(
            @PathVariable int id,
            @PathVariable String dataReserva,
            @RequestBody Map<String, Set<String>> reserva) {

        LocalDateTime formattedDate = LocalDateTime.parse(dataReserva, formatter);
        SalaDeReuniao sala = empresa.getSalas().get(id);

        reserva.forEach((dataReuniao, participantes) -> {
            LocalDateTime dataReuniaoFormatted = LocalDateTime.parse(dataReuniao, formatter);
            sala.atualizarReserva(formattedDate, dataReuniaoFormatted, participantes);
        });
        return ResponseEntity.ok().build();
    }
}
