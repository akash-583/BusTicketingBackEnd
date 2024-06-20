package com.bus.bookingservice.ExternalClasses;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Bus")
public class BusModel {
	@Id
    @Pattern(regexp = "\\d{5}", message = "Length of the Bus number should be exactly five digits")
    private String busNo;

    @NotBlank(message = "Source cannot be empty")
    @Size(min = 3, max = 30, message = "Source should be between 3 and 30 characters")
    private String source;

    @NotBlank(message = "Destination cannot be empty")
    @Size(min = 3, max = 30, message = "Destination should be between 3 and 30 characters")
    private String destination;

    @Min(value = 0, message = "Fare should be greater than zero")
    @Max(value = 3000, message = "Fare should not be greater than 3000")
    private int fare;

    @Min(value = 0, message = "Seats should be greater than zero")
    @Max(value = 50, message = "Seats should not be greater than 50")
    private int seats;

    @NotBlank(message = "Time cannot be empty")
    private String dropTime;
    
    @NotBlank(message = "Time cannot be empty")
    private String boardingTime;
    
}
