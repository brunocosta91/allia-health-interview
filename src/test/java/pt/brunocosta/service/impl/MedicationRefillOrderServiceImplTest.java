package pt.brunocosta.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.brunocosta.domain.enums.MedicationRefillOrderStatus;
import pt.brunocosta.domain.enums.TreatmentPlanStatus;
import pt.brunocosta.domain.model.MedicationRefillOrder;
import pt.brunocosta.domain.model.TreatmentPlan;
import pt.brunocosta.repository.MedicationRefillOrderRepository;
import pt.brunocosta.repository.TreatmentPlanRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MedicationRefillOrderServiceImplTest {
    @Mock
    private MedicationRefillOrderRepository orderRepository;
    @Mock
    private TreatmentPlanRepository treatmentPlanRepository;
    @InjectMocks
    private MedicationRefillOrderServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldSetTreatmentPlanAndSaveOrder() {
        UUID planId = UUID.randomUUID();
        TreatmentPlan plan = new TreatmentPlan();
        plan.setStartDate( LocalDate.now().minusDays(10) );
        plan.setEndDate( LocalDate.now().plusDays(10) );
        plan.setStatus( TreatmentPlanStatus.ACTIVE );
        MedicationRefillOrder order = new MedicationRefillOrder();
        when(treatmentPlanRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(orderRepository.save(any())).thenReturn(order);

        MedicationRefillOrder result = service.create(planId);
        assertNotNull(result);
        verify(treatmentPlanRepository, times(1)).findById(planId);
    }

    @Test
    void getById_shouldReturnOrderIfExists() {
        UUID id = UUID.randomUUID();
        MedicationRefillOrder order = new MedicationRefillOrder();
        order.setId(id);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        MedicationRefillOrder result = service.getById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    void getById_shouldThrowIfNotFound() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> service.getById(id));
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    void list_shouldCallRepositorySearch() {
        UUID planId = UUID.randomUUID();
        MedicationRefillOrderStatus status = MedicationRefillOrderStatus.APPROVED;
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now();
        MedicationRefillOrder order1 = new MedicationRefillOrder();
        MedicationRefillOrder order2 = new MedicationRefillOrder();
        List<MedicationRefillOrder> orders = Arrays.asList(order1, order2);
        when(orderRepository.search(planId, status, start, end)).thenReturn(orders);

        List<MedicationRefillOrder> result = service.list(planId, status, start, end);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).search(planId, status, start, end);
    }

    @Test
    void updateStatus_shouldUpdateOrderStatusAndSave() {
        UUID id = UUID.randomUUID();
        MedicationRefillOrder order = new MedicationRefillOrder();
        order.setId(id);
        order.setStatus(MedicationRefillOrderStatus.REQUESTED);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        MedicationRefillOrder result = service.updateStatus(id, MedicationRefillOrderStatus.APPROVED);
        assertNotNull(result);
        assertEquals(MedicationRefillOrderStatus.APPROVED, result.getStatus());
        verify(orderRepository, times(1)).findById(id);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void updateStatus_shouldThrowIfOrderNotFound() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> service.updateStatus(id, MedicationRefillOrderStatus.APPROVED));
        verify(orderRepository, times(1)).findById(id);
    }
}

