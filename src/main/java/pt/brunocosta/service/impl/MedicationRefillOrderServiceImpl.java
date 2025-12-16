package pt.brunocosta.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.brunocosta.domain.enums.TreatmentPlanStatus;
import pt.brunocosta.domain.model.MedicationRefillOrder;
import pt.brunocosta.domain.enums.MedicationRefillOrderStatus;
import pt.brunocosta.domain.model.TreatmentPlan;
import pt.brunocosta.exception.InvalidTreatmentPlanStateException;
import pt.brunocosta.exception.NotFoundRefillOrderException;
import pt.brunocosta.exception.NotFoundTreatmentPlanException;
import pt.brunocosta.exception.TreatmentPlanPeriodOutOfBoundsException;
import pt.brunocosta.repository.MedicationRefillOrderRepository;
import pt.brunocosta.repository.TreatmentPlanRepository;
import pt.brunocosta.service.MedicationRefillOrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicationRefillOrderServiceImpl implements MedicationRefillOrderService {
    private final MedicationRefillOrderRepository orderRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;

    @Override
    public MedicationRefillOrder create(UUID treatmentPlanId ) {
        TreatmentPlan plan = treatmentPlanRepository.findById(treatmentPlanId).orElseThrow( NotFoundTreatmentPlanException::new );

        LocalDate now = LocalDate.now();

        if( plan.getStatus() != TreatmentPlanStatus.ACTIVE ) {
            throw new InvalidTreatmentPlanStateException( "Treatment plan is not active.");
        }

        if( plan.getStartDate().isAfter( now ) || plan.getEndDate().isBefore( now ) ) {
            throw new TreatmentPlanPeriodOutOfBoundsException( "Treatment plan is not active on the current date.");
        }

        MedicationRefillOrder order = new MedicationRefillOrder();
        order.setTreatmentPlan(plan);
        order.setStatus(MedicationRefillOrderStatus.REQUESTED);
        order.setRequestedDate( now );
        return orderRepository.save(order);
    }

    @Override
    public MedicationRefillOrder getById(UUID id) {
        return orderRepository.findById(id).orElseThrow( NotFoundRefillOrderException::new );
    }

    @Override
    public List<MedicationRefillOrder> list(UUID treatmentPlanId, MedicationRefillOrderStatus status, LocalDate start, LocalDate end) {
        return orderRepository.search(treatmentPlanId, status, start, end);
    }

    @Override
    public MedicationRefillOrder updateStatus(UUID id, MedicationRefillOrderStatus status) {
        MedicationRefillOrder order = orderRepository.findById(id).orElseThrow( NotFoundRefillOrderException::new );
        order.setStatus(status);
        return orderRepository.save(order);
    }
}

