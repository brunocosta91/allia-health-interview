package pt.brunocosta.service;

import pt.brunocosta.domain.model.MedicationRefillOrder;
import pt.brunocosta.domain.enums.MedicationRefillOrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicationRefillOrderService {
    MedicationRefillOrder create(UUID treatmentPlanId);
    MedicationRefillOrder getById(UUID id);
    List<MedicationRefillOrder> list(UUID treatmentPlanId, MedicationRefillOrderStatus status, LocalDate start, LocalDate end);
    MedicationRefillOrder updateStatus(UUID id, MedicationRefillOrderStatus status);

}

