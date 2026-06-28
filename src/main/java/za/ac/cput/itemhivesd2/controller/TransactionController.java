package za.ac.cput.transactionentity.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.transactionentity.domain.Transaction;
import za.ac.cput.transactionentity.service.TransactionService;

import java.util.List;
/*
Transaction.java
Transaction controller class
Author:Athini Ngquke(222352302)
Date: 28 March 2026
 */
@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    private final TransactionService serv;

    public TransactionController(TransactionService serv){
        this.serv = serv;
    }
    @PostMapping
    public Transaction create(@RequestBody Transaction record){
        return serv.create(record);
    }
    @PutMapping
    public Transaction update(@RequestBody Transaction record){
        return serv.update(record);
    }
    @GetMapping
    public Transaction read(@PathVariable String id){
        return serv.read(id);
    }
    @GetMapping
    public List<Transaction> getAll(){
        return serv.getAll();
    }
    @DeleteMapping
    public void delete(@PathVariable String id){
        serv.delete(id);
    }


}
