package com.pbenito.backend_springboot_mongodb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

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

        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { week: { $isoWeek: '$operationDate' }, year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $sort: { '_id.year': 1, '_id.week': 1 } }"
        })
        List<Map<String, Object>> getSalesByWeek();

        
        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { month: { $month: '$operationDate' }, year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $sort: { '_id.year': 1, '_id.month': 1 } }"
        })
        List<Map<String, Object>> getSalesByMonth();

        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $sort: { '_id.year': 1 } }"
        })
        List<Map<String, Object>> getSalesByYear();
}
//Encontrar transacciones no venta
