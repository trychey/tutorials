package com.baeldung.bulkandbatchapi.controller;

import com.baeldung.bulkandbatchapi.request.Customer;
import com.baeldung.bulkandbatchapi.request.CustomerBulkRequest;
import com.baeldung.bulkandbatchapi.response.BulkStatus;
import com.baeldung.bulkandbatchapi.response.CustomerBulkResponse;
import com.baeldung.bulkandbatchapi.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class BulkController {

    private final CustomerService customerService;

    @Autowired
    public BulkController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/customers")
    public ResponseEntity<List<Customer>> createCustomers(@RequestHeader(value = "X-ActionType") String actionType, @RequestBody @Valid @Size(min = 1, max = 20) List<Customer> customers) {
        List<Customer> customerList = actionType.equals("bulk") ? customerService.createCustomers(customers) :
                Collections.singletonList(customerService.createCustomer(customers.get(0)).orElse(null));

        return new ResponseEntity<>(customerList, HttpStatus.CREATED);
    }

    @PostMapping(path = "/customers/bulk")
    public ResponseEntity<List<CustomerBulkResponse>> bulkProcessCustomers(@RequestBody @Valid @Size(min = 1, max = 20) List<CustomerBulkRequest> customerBulkRequests) {
        List<CustomerBulkResponse> customerBulkResponseList = new ArrayList<>();

        customerBulkRequests.forEach(customerBulkRequest -> {
            List<Customer> customers = customerService.processCustomers(customerBulkRequest.getCustomers(), customerBulkRequest.getBulkActionType());
            BulkStatus bulkStatus = getBulkStatus(customerBulkRequest.getCustomers(), customers);
            customerBulkResponseList.add(CustomerBulkResponse.getCustomerBatchResponse(customers, customerBulkRequest.getBulkActionType(), bulkStatus));
        });

        return new ResponseEntity<>(customerBulkResponseList, HttpStatus.MULTI_STATUS);
    }

    private BulkStatus getBulkStatus(List<Customer> customersInRequest, List<Customer> customersProcessed) {
        BulkStatus bulkStatus;

        if (customersProcessed.size() == customersInRequest.size()) bulkStatus = BulkStatus.PROCESSED;
        else if (customersProcessed.size() < customersInRequest.size()) bulkStatus = BulkStatus.PARTIALLY_PROCESSED;
        else bulkStatus = BulkStatus.NOT_PROCESSED;

        return bulkStatus;
    }
}
