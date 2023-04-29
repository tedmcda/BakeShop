package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Products;
import com.example.demo.entities.Requests;
import com.example.demo.entities.Vendors;
import com.example.demo.repositories.RequestRepository;

@RestController
@RequestMapping("/requests")
public class RequestController {
	
	private final String NEW = "New"; 
	private final String REVIEW = "Review";
	private final String APPROVED = "Approved";
	private final String REJECTED = "Rejected";
	private final String REOPENED = "Reopened";

	@Autowired
	private RequestRepository requestRepo;

	@GetMapping("")
	public List<Requests> getAll() {
		List<Requests> requests = requestRepo.findAll();

		return requests;
	}

	@GetMapping("/{id}")
	public Requests getById(@PathVariable int id) {
		Requests request = new Requests();
		Optional<Requests> optionalRequests = requestRepo.findById(id);

		if (optionalRequests.isPresent()) {
			request = optionalRequests.get();
		}

		return request;
	}

	@PostMapping("")
	public Requests createRequest(@RequestBody Requests newRequest) {
		Requests request = new Requests();

		boolean requestExists = requestRepo.findById(newRequest.getId()).isPresent();

		if (!requestExists) {
			request.setStatus(NEW);
			request.setSubmittedDate(LocalDateTime.now());
			
			request = requestRepo.save(newRequest);
		}

		return request;
	}

	@PutMapping("")
	public Requests updateRequest(@RequestBody Requests updatedRequest) {
		Requests request = new Requests();

		boolean requestExists = requestRepo.findById(updatedRequest.getId()).isPresent();

		if (requestExists) {
			request = requestRepo.save(updatedRequest);
		}

		return request;
	}

	@DeleteMapping("/{id}")
	public Requests delete(@PathVariable int id) {
		Requests request = new Requests();
		Optional<Requests> optionalRequest = requestRepo.findById(id);

		boolean requestExists = optionalRequest.isPresent();

		if (requestExists) {
			request = optionalRequest.get();
			requestRepo.deleteById(id);
		}
		return request;
	}
	
	@GetMapping("/list-review/{userId}")
	public List<Requests> getAllForReview(@PathVariable int userId) {
		List<Requests> request = requestRepo.findByStatusAndUserIdNot(REVIEW, userId);
		
		return request;
	}
	
	@PutMapping("/approve")
	public Requests approve(@RequestBody Requests approvedRequest) {
		Requests request = new Requests();
		
		boolean requestExists = requestRepo.existsById(approvedRequest.getId());
		
		if (requestExists) {
			approvedRequest.setStatus(APPROVED);
			
			request = requestRepo.save(approvedRequest);
		}
		
		return request;
		
	}
	
	@PutMapping("/review")
    public Requests review(@RequestBody Requests reviewRequest) {
        Requests request = new Requests();
        boolean requestExists = requestRepo.existsById(reviewRequest.getId());
        if (requestExists) {
            reviewRequest.setSubmittedDate(LocalDateTime.now());
            if (reviewRequest.getTotal() <= 50) {
                reviewRequest.setStatus(APPROVED);
            } else {
            reviewRequest.setStatus(REVIEW);
        }
        request = requestRepo.save(reviewRequest);
    }
        return request; 
    }
		
		@PutMapping("/reject")
		public Requests reject(@RequestBody Requests rejectedRequest) {
			Requests request = new Requests();
			
			boolean requestExists = requestRepo.existsById(rejectedRequest.getId());
			
			if (requestExists) {
				rejectedRequest.setStatus(REJECTED);
				
				request = requestRepo.save(rejectedRequest);
			}
			
			return request;
			
		}
		
		@PutMapping("/reopen")
		public Requests reopen(@RequestBody Requests reopenedRequest) {
			Requests request = new Requests();
			
			boolean requestExists = requestRepo.existsById(reopenedRequest.getId());
			
			if (requestExists) {
				reopenedRequest.setStatus(REOPENED);
				
				request = requestRepo.save(reopenedRequest);
			}
			
			return request;
			
		}
		
	}