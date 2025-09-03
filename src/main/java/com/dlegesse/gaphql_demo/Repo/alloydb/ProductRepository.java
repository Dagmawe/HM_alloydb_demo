package com.dlegesse.gaphql_demo.Repo.alloydb;

import com.dlegesse.gaphql_demo.Entity.alloydb.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
