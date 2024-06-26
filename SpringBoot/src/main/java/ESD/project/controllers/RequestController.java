package ESD.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ESD.project.DTO.RequestDTO;
import ESD.project.model.Request;
import ESD.project.service.RabbitMQPublisher;
import ESD.project.service.RequestService;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    

    @PostMapping
    public ResponseEntity<String> addRequest(@RequestBody RequestDTO requestDTO) {
        try {
            // Assuming addRequest returns the ID of the newly added request
            String requestId = Integer.toString(requestService.addRequest(requestDTO.getModel_Id(),
                                                         requestDTO.getUnit_Id(),
                                                         requestDTO.getModel_Type(),
                                                         requestDTO.getClaimee(),
                                                         requestDTO.getEmail(),
                                                         requestDTO.getDescription()
                                                        ));

            return ResponseEntity.ok("Request added with ID: " + requestId);
        } catch (Exception e) {
            // Simplified the catch block to catch all exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding Request: " + e.getMessage());
        }
    }

    @PatchMapping("/{requestId}/status")
    public ResponseEntity<String> updateRequestStatus(
        @PathVariable int requestId, 
        @RequestBody String newStatus,
        @RequestHeader("claimee") String claimee,
        @RequestHeader("email") String email
        ) {
        try {
            requestService.updateRequestStatus(requestId, newStatus);
            
            // Publish message to RabbitMQ
            
            // rabbitMQPublisher.publishRequestStatusUpdate(requestId, newStatus, claimee, email);


            return ResponseEntity.ok("Request status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating request status: " + e.getMessage());
        }
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Request> getRequest(@PathVariable int requestId) {
        try {
            Request request = requestService.getRequestById(requestId);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }


    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int requestId) {
        try {
            requestService.deleteRequest(requestId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

