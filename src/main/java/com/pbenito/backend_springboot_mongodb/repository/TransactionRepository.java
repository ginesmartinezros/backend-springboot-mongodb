package com.pbenito.backend_springboot_mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pbenito.backend_springboot_mongodb.dto.SalesByDayDTO;
import com.pbenito.backend_springboot_mongodb.dto.SalesByMonthDTO;
import com.pbenito.backend_springboot_mongodb.dto.SalesByWeekDTO;
import com.pbenito.backend_springboot_mongodb.dto.SalesByYearDTO;
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
                "{ $project: { _id: 0, day: '$_id.day', year: '$_id.year' totalAmount: 1 } }",
                "{ $sort: { '_id.year': 1, '_id.day': 1 } }"
        })
        List<SalesByDayDTO> getSalesByDay();

        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { month: { $month: '$operationDate' }, year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $project: { _id: 0, month: '$_id.month', year: '$_id.year' totalAmount: 1 } }",
                "{ $sort: { year: 1, month: 1 } }"
            })
        List<SalesByMonthDTO> getSalesByMonth();
        
        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { week: { $week: '$operationDate' }, year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $project: { _id: 0, week: '$_id.week', year: '$_id.year' totalAmount: 1 } }",
                "{ $sort: { year: 1, week: 1 } }"
            })
        List<SalesByWeekDTO> getSalesByWeek();
            
        @Aggregation(pipeline = {
                "{ $match: { operationType: 'VENTA', operationDate: { $exists: true, $ne: null } } }",
                "{ $group: { _id: { year: { $year: '$operationDate' } }, totalAmount: { $sum: '$amountEuro' } } }",
                "{ $project: { _id: 0, year: '$_id.year' totalAmount: 1 } }",
                "{ $sort: { '_id.year': 1 } }"
        })

        List<SalesByYearDTO> getSalesByYear();

        @Query(value = "{}", fields = "{'operationDate': 1, '_id': 0}")
        List<TransactionDateDTO> findTransactionDates();

}
//TODO Encontrar transacciones no venta
