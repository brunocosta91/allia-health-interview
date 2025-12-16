package pt.brunocosta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.brunocosta.domain.mapper.MedicationRefillOrderMapper;
import pt.brunocosta.domain.model.MedicationRefillOrder;
import pt.brunocosta.domain.enums.MedicationRefillOrderStatus;
import pt.brunocosta.dto.request.MedicationRefillOrderStatusUpdateRequestDto;
import pt.brunocosta.dto.response.MedicationRefillOrderResponseDto;
import pt.brunocosta.service.MedicationRefillOrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/refill-orders")
@RequiredArgsConstructor
public class MedicationRefillOrderController {

    private final MedicationRefillOrderService orderService;
    private final MedicationRefillOrderMapper orderMapper;


    @GetMapping("/{id}")
    public ResponseEntity<MedicationRefillOrderResponseDto> getById(@PathVariable UUID id) {
        MedicationRefillOrder order = orderService.getById(id);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }

    @GetMapping
    public ResponseEntity<List<MedicationRefillOrderResponseDto>> list(
            @RequestParam(required = false) UUID treatmentPlanId,
            @RequestParam(required = false) MedicationRefillOrderStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        List<MedicationRefillOrder> orders = orderService.list(treatmentPlanId, status, start, end);
        List<MedicationRefillOrderResponseDto> dtos = orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MedicationRefillOrderResponseDto> updateStatus(@PathVariable UUID id, @Validated @RequestBody MedicationRefillOrderStatusUpdateRequestDto dto) {
        MedicationRefillOrder updated = orderService.updateStatus(id, MedicationRefillOrderStatus.valueOf(dto.status()));
        return ResponseEntity.ok(orderMapper.toDto(updated));
    }
}

