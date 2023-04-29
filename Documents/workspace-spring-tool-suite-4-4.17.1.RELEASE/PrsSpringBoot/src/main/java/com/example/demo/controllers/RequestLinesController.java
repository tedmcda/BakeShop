package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.RequestLines;
import com.example.demo.entities.Requests;
import com.example.demo.repositories.RequestLinesRepository;
import com.example.demo.repositories.RequestRepository;

@RestController
@RequestMapping("/RequestLines")
public class RequestLinesController {

	@Autowired
	private RequestLinesRepository requestLineRepo;

	@Autowired
	private RequestRepository requestRepo;

	@GetMapping("")
	public List<RequestLines> getAll() {
		List<RequestLines> requestLines = requestLineRepo.findAll();

		return requestLines;

	}

	@GetMapping("/{id}")
	public RequestLines getById(@PathVariable int id) {
		RequestLines requestLine = new RequestLines();
		Optional<RequestLines> optionalRequestLine = requestLineRepo.findById(id);

		if (optionalRequestLine.isPresent()) {
			requestLine = optionalRequestLine.get();

		}
		return requestLine;
	}

	@PostMapping("")
	public RequestLines createRequestLine(@RequestBody RequestLines newRequestLine) {
		RequestLines requestLine = new RequestLines();

		boolean requestLineExists = requestLineRepo.findById(newRequestLine.getId()).isPresent();

		if (!requestLineExists) {
			requestLine = requestLineRepo.save(newRequestLine);
			recalculateTotal(requestLine.getRequest());
		}

		return requestLine;
	}

	@PutMapping("")
	public RequestLines updateRequestLine(@RequestBody RequestLines updatedRequestLine) {
		RequestLines requestLine = new RequestLines();

		boolean requestLineExists = requestLineRepo.findById(updatedRequestLine.getId()).isPresent();

		if (requestLineExists) {
			requestLine = requestLineRepo.save(updatedRequestLine);
			recalculateTotal(requestLine.getRequest());
		}

		return requestLine;
	}

	@DeleteMapping("/{id}")
	public RequestLines delete(@PathVariable int id) {
		RequestLines requestLine = new RequestLines();
		Optional<RequestLines> optionalRequestLine = requestLineRepo.findById(id);

		boolean requestLineExists = optionalRequestLine.isPresent();

		if (requestLineExists) {
			requestLine = optionalRequestLine.get();
			requestLineRepo.deleteById(id);
			recalculateTotal(requestLine.getRequest());
		}
		return requestLine;
	}

	private void recalculateTotal(Requests request) {

		List<RequestLines> requestLines = requestLineRepo.findByRequestId(request.getId());
		double runningTotal = 0;

		for (RequestLines lineItem : requestLines) {

			double total = lineItem.getProduct().getPrice() * lineItem.getQuantity();

			runningTotal += total;
		}

		request.setTotal(runningTotal);

		requestRepo.save(request);

	}

}
