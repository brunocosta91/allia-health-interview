package pt.brunocosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.brunocosta.domain.model.MedicationRefillOrder;
import pt.brunocosta.domain.enums.MedicationRefillOrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicationRefillOrderRepository extends JpaRepository<MedicationRefillOrder, UUID> {
  @Query("""
        select medicationRefillOrder from MedicationRefillOrder medicationRefillOrder
        where (:treatmentPlanId is null or medicationRefillOrder.treatmentPlan.id = :treatmentPlanId)
          and (:status is null or medicationRefillOrder.status = :status)
          and (:start is null or medicationRefillOrder.requestedDate >= :start)
          and (:end is null or medicationRefillOrder.requestedDate <= :end)
    """)
  List<MedicationRefillOrder> search(
      @Param("treatmentPlanId") UUID treatmentPlanId,
      @Param("status") MedicationRefillOrderStatus status,
      @Param("start") LocalDate start,
      @Param("end") LocalDate end
  );
}

