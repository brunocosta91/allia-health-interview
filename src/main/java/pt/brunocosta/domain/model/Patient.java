package pt.brunocosta.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table( name = "patients" )
public class Patient {

  @Id
  @GeneratedValue
  private UUID id;

  @Column( nullable = false )
  private String name;

  @Column( nullable = false, unique = true )
  private String email;

}