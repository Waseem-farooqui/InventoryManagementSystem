package com.was.inventory.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.was.inventory.Repositories.*;
import com.was.inventory.model.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Waseem ud din
 * @version 0.1
 */
@Controller // This means that this class is a Controller
//@CrossOrigin(origins = "*:8080")
@CrossOrigin(origins = "*:8080")
@RequestMapping(path = "/inventory") // This means URL's start with /demo (after Application path)
public class RequestController {

    private static final Logger logger = LogManager.getLogger(RequestController.class);
    private static final String uploadingdir = System.getProperty("user.dir") + "/imagesrepo/";

    private CustomerRepository customerRepository;
    private PaymentRepository paymentRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private SupplierRepository supplierRepository;
    private ItemRepository itemRepository;
    private SaleRepository saleRepository;
    private PurchaseRepository purchaseRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;
    private ObjectMapper mapper;

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private void RequestController(CustomerRepository customerRepository,
                                   SupplierRepository supplierRepository,
                                   PaymentRepository paymentRepository,
                                   PaymentMethodRepository paymentMethodRepository,
                                   SaleRepository saleRepository,
                                   ItemRepository itemRepository,
                                   PurchaseRepository purchaseRepository,
                                   OrderRepository orderRepository,
                                   CategoryRepository categoryRepository,

                                   ObjectMapper mapper) {

        this.customerRepository = customerRepository;
        this.supplierRepository = supplierRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.saleRepository = saleRepository;
        this.itemRepository = itemRepository;
        this.purchaseRepository = purchaseRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/add/customer", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addCustomer(@NotBlank @RequestBody Customer customer) {

        logger.debug("Request for the inserting the data.");
        ObjectNode responseBody = mapper.createObjectNode();

        logger.debug("Checking the condition of the type.");
        logger.debug("The condition for insertion of customer");
        logger.debug("Creating the reference of the Customer class.");

        try {
            logger.info("Inserting the record into the database via customer");
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the customer"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the Customer data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }

    @RequestMapping(value = "/add/supplier", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addSupplier(@NotBlank @RequestBody Supplier supplier) {

        logger.debug("Request for the inserting the data.");
        ObjectNode responseBody = mapper.createObjectNode();

        logger.debug("Checking the condition of the type.");
        logger.debug("The condition for insertion of supplier");
        logger.debug("Creating the reference of the Customer class.");

        try {
            logger.info("Inserting the record into the database via supplier");
            supplierRepository.save(supplier);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the supplier"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the Customer data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }

    // TODO: 10/20/17 Have to handle the payable and paid on the 2nd transaction.
    @RequestMapping(value = "/add/payment", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addPayment(@NotBlank @RequestBody Payment payment) {

        logger.debug("Request for the inserting the payment data.");
        logger.info(payment.getCustomer());
        ObjectNode responseBody = mapper.createObjectNode();

        try {
            logger.info("Inserting the record into the database via payment");
            paymentRepository.save(payment);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the payment"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the Customer data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }

    @RequestMapping(value = "/add/paymentMethod", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addPaymentMethod(@NotBlank @RequestBody PaymentMethod paymentMethod) {

        logger.debug("Request for the inserting the payment method.");
        ObjectNode responseBody = mapper.createObjectNode();

        try {
            logger.info("Inserting the record into the database via paymentMethod");
            paymentMethodRepository.save(paymentMethod);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the paymentMethod"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the Customer data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }

    @RequestMapping(value = "/add/itemCategory", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addItemCategory(@NotBlank @RequestBody Category category) {

        logger.debug("Request for the inserting the item category.");
        ObjectNode responseBody = mapper.createObjectNode();
        try {
            logger.info("Inserting the record into the database via category");
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the category"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the Item Category data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }


    // TODO: 10/20/17  Have to handle the case in which we have same composite key and inserting the quantity.
    @RequestMapping(value = "/add/item", method = RequestMethod.POST, consumes = "multipart/form-data")
    public @ResponseBody
    ResponseEntity<JsonNode> addItem(@RequestPart("file") MultipartFile file,
                                     @NotBlank @RequestPart("data") String requestBody) {

        ObjectNode responseBody = new ObjectMapper().createObjectNode();
        logger.debug("In item insertion controller method.");


        logger.debug("Creating the reference of the item class.");
        Item item = null;
        try {
            logger.info("Writing the value into the object of the Item using the request.");
            item = mapper.readValue(requestBody, Item.class);

        } catch (IOException e) {
            logger.error("Error while writing the Item data into its object", e);
        }

        // Getting the information about the item so that store with specifics in the database

        if (file.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.NO_CONTENT);
        }
        // Creating the file for writing it into the dirctory
        File newFile = new File(uploadingdir + item.getCategory().getId() + "/" + file.getOriginalFilename());
        newFile.getParentFile().mkdirs();
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            logger.error("Error Occured while trying to write an image file.");
            e.printStackTrace();
        }

        item.setPictureLink(newFile.getAbsolutePath());
        try {
            logger.info("Inserting the record into the Item table via its object");
            itemRepository.save(item);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the payment data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Inserted"));

    }

    @RequestMapping(value = "/add/sale", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addSale(@NotBlank @RequestBody Sale sale) {

        logger.debug("Request for the inserting the item sale.");
        ObjectNode responseBody = mapper.createObjectNode();
        try {

            // TODO "Have to put a check payable must be greater then or equal to paid"
            logger.info("Inserting the record into the database via sale");
            saleRepository.save(sale);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the sale"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the sales data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }

    @RequestMapping(value = "/add/purchase", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addPurchase(@NotBlank @RequestBody Purchase purchase) {

        logger.debug("Request for the inserting the item purchase.");
        ObjectNode responseBody = mapper.createObjectNode();
        try {
            logger.info("Inserting the record into the database via purchase");
            purchaseRepository.save(purchase);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the purchase"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the purchase data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }

    @RequestMapping(value = "/add/order", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> addOrder(@NotBlank @RequestBody Orders orders) {

        logger.debug("Request for the inserting the item orders.");
        ObjectNode responseBody = mapper.createObjectNode();
        try {
            logger.info("Inserting the record into the database via orders");
            orderRepository.save(orders);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("info", "Successfully Inserted the orders"));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while inserting the orders data into its database table.", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody.put("error", e.getCause().toString()));
        }
    }

    @RequestMapping(value = "/get/{type}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<JsonNode> getData(@PathVariable String type) {

        ObjectNode responseBody = new ObjectMapper().createObjectNode();
        logger.debug("Request received to get the data.");
        logger.debug("Checking the condition of the type.");
        switch (type) {
            case "customer": {
                logger.debug("The condition for getting the customers.");
                return ResponseEntity.status(HttpStatus.OK).body(mapper.valueToTree(customerRepository.findAll()));
            }
            case "supplier": {
                logger.debug("The condition for getting of Supplier");
                return ResponseEntity.status(HttpStatus.OK).body(mapper.valueToTree(supplierRepository.findAll()));

            }
//            case "payment":{
//
//                Payment payment = new Payment();
//                Customer customer = new Customer();
//                customer.setId(Integer.parseInt(requestMap.get("customer_id")));
//                Set paymentSet = new HashSet<Payment>();
//
//                break;
//            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody.put("error", "Inserted"));

    }


}
