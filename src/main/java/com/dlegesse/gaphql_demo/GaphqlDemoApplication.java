package com.dlegesse.gaphql_demo;

import com.dlegesse.gaphql_demo.Entity.alloydb.Product;
import com.dlegesse.gaphql_demo.Entity.cloudsql.Employee;
import com.dlegesse.gaphql_demo.Repo.alloydb.ProductRepository;
import com.dlegesse.gaphql_demo.Repo.cloudsql.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@SpringBootApplication
@EnableTransactionManagement
public class GaphqlDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GaphqlDemoApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	@Qualifier("cloudSqlTransactionManager") // Qualify for Cloud SQL's transaction manager
	private PlatformTransactionManager cloudSqlTransactionManager;

	@Autowired
	@Qualifier("alloyDbTransactionManager") // Qualify for AlloyDB's transaction manager
	private PlatformTransactionManager alloyDbTransactionManager;


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application starting data initialization...");

		// --- Initialize Cloud SQL Data ---
		System.out.println("------------------------------------");
		System.out.println("Attempting to insert sample data into Cloud SQL Employee table...");

		// Start a new transaction for Cloud SQL
		DefaultTransactionDefinition defCloudSql = new DefaultTransactionDefinition();
		TransactionStatus statusCloudSql = cloudSqlTransactionManager.getTransaction(defCloudSql);

		try {
			// For auto-generated IDs, pass null. Adjust constructor as needed.
			Employee employee1 = new Employee(null, "John Doe", "1990-01-15");
			Employee employee2 = new Employee(null, "Jack Stone", "1998-10-15");
			Employee savedEmployee1 = employeeRepository.save(employee1);
			Employee savedEmployee2 = employeeRepository.save(employee2);
			System.out.println("Saved Employee to Cloud SQL: " + savedEmployee1);
			cloudSqlTransactionManager.commit(statusCloudSql); // Commit the transaction
		} catch (Exception e) {
			System.err.println("Error saving Employee to Cloud SQL: " + e.getMessage());
			cloudSqlTransactionManager.rollback(statusCloudSql); // Rollback on error
			throw e; // Re-throw to indicate failure
		}
		System.out.println("------------------------------------");


		// --- Initialize AlloyDB Product Data ---
		System.out.println("------------------------------------");
		System.out.println("Attempting to insert sample data into AlloyDB Product table...");

		// Start a new transaction for AlloyDB
		DefaultTransactionDefinition defAlloyDb = new DefaultTransactionDefinition();
		TransactionStatus statusAlloyDb = alloyDbTransactionManager.getTransaction(defAlloyDb);

		try {
			Product laptop = new Product(null, "Gaming Laptop", 1200.00);
			Product headphone = new Product(null, "Head Phones", 120.00);
			Product product1 = productRepository.save(laptop);
			Product product2 = productRepository.save(headphone);
			System.out.println("Saved Product to AlloyDB: " + product1);
			alloyDbTransactionManager.commit(statusAlloyDb);
		} catch (Exception e) {
			System.err.println("Error saving Product to AlloyDB: " + e.getMessage());
			alloyDbTransactionManager.rollback(statusAlloyDb); // Rollback on error
			throw e; // Re-throw to indicate failure
		}
		System.out.println("------------------------------------");

		System.out.println("Application startup complete and data initialization finished.");
	}



}
