package pt.brunocosta.domain.enums;

public enum MedicationRefillOrderStatus {
  /** The medication refill order has been requested by the patient. */
  REQUESTED,

  /** The medication refill order has been approved by a healthcare provider. */
  APPROVED,

  /** The medication refill order has been rejected by a healthcare provider. */
  REJECTED,

  /** The medication refill order has been dispensed to the patient. */
  FULFILLED
}

