package ewasteless.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Getter // Lombok annotation to generate getters
@Setter // Lombok annotation to generate setters
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@Entity // Marks this class as an entity
@Table(name = "requests")
public class Request {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generation strategy
    private int request_Id;
    private String unit_Id;
    private String model_Id;
    private String model_Type;
    private String claimee;
    private String email;
    private String description;
    private String status;
    private LocalDateTime created_timestamp;

    public Request(String unit_Id, String model_Id, String model_Type, String claimee, String email, String description) {
        this.unit_Id = unit_Id;
        this.model_Id = model_Id;
        this.model_Type = model_Type;
        this.claimee = claimee;
        this.email = email;
        this.description = description;
        this.status = "pending";
    }
}

