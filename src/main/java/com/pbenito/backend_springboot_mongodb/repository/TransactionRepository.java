package com.pbenito.backend_springboot_mongodb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pbenito.backend_springboot_mongodb.dto.SalesByWeekDTO;
import com.pbenito.backend_springboot_mongodb.dto.TransactionDateDTO;
import com.pbenito.backend_springboot_mongodb.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
	
	@Query("{transactionId:'?0'}")
	Transaction findTransactionByTransactionId(String transactionId);
	
	@Query(value="{amountEuro:'?0'}", fields="{'transactionId' : 1, 'operationDate' : 1}")
	List<Transaction> findAll(double amountEuro);

        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { day: { $dayOfYear: '$operationDate' }, year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $sort: { '_id.year': 1, '_id.day': 1 } }"
        })
        List<Map<String, Object>> getSalesByDay();
/* 
        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { week: { $toInt: { $isoWeek: '$operationDate' } } , year: { $toInt: { $year: '$operationDate' } } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $sort: { '_id.year': 1, '_id.week': 1 } }"
        })
        List<SalesByWeekDTO> getSalesByMonth(); //TODO no es eso */

        
        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { week: { $week: '$operationDate' }, year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $project: { _id: 0, week: '$_id.week', year: '$_id.year', newId: { $concat: [ { $toString: '$_id.week' }, '-', { $toString: '$_id.year' } ] }, totalAmount: 1 } }",
                "{ $sort: { year: 1, week: 1 } }"
            })
        List<SalesByWeekDTO> getSalesByWeek();
            
        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $sort: { '_id.year': 1 } }"
        })
        List<Map<String, Object>> getSalesByYear();

        @Query(value = "{}", fields = "{'operationDate': 1, '_id': 0}")
        List<TransactionDateDTO> findTransactionDates();

}
//TODO Encontrar transacciones no venta
