package com.was.inventory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.was.inventory.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetService {

    @Autowired
    ObjectMapper mapper;

    public ArrayNode getCustomer(List<Customer> customers){

        ArrayNode arrayNode = mapper.createArrayNode();

        for(Customer cs : customers){
//            arrayNode.add(mapper.createObjectNode().put(cs))
        }
        return null;
    }
}
