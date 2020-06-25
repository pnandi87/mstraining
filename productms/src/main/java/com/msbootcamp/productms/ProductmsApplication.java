package com.msbootcamp.productms;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.msbootcamp.productms.model.Product;
import com.msbootcamp.productms.model.ProductCategory;
import com.msbootcamp.productms.model.ProductTag;
import com.msbootcamp.productms.repo.ProductRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class ProductmsApplication {

	@Autowired
	private ProductRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductmsApplication.class, args);
	}

	public Map<Integer, Product> productSeeds = new HashMap<Integer, Product>();

	@Bean
	Map<Integer, Product> productSeeds() {
		return productSeeds;
	}

	@Bean	
	CommandLineRunner getAllBeans(ApplicationContext context)
	{
		return(a) ->{
		int count = context.getBeanDefinitionCount();
		System.out.println("Todal beans : " + count );
		
		String[] beanNames = context.getBeanDefinitionNames();
		for (int i=0; i<count; i++)
			System.out.println("Bean " + i + ": " + beanNames[i].toString());
		
		seedItUp();
			};
	}
	
	@Transactional
	public void seedItUp() {
	/*	Product p = new Product(11, "Kitchen Chimney", "6x4. Non-exhaust", ProductCategory.KITCHENELECTRONIC, 200.87);
		repo.save(p);
		p = new Product(21, "Persian Carpet", "9x9. Handwoven", ProductCategory.FURNISHING, 1000.45);
		repo.save(p);
		p = new Product(31, "Space Craft Lego", "580 pieces", ProductCategory.TOY, 776.00);
		repo.save(p);
*/
		
		Product p = new Product(1, "Kitchen Chimney", "6x4. Non-exhaust", ProductCategory.KITCHENELECTRONIC, 200.87);
		p.getTags().add(new ProductTag(1, "kitchen"));
		productSeeds.put(1, p);
		repo.save(p);
		p = new Product(2, "Persian Carpet", "9x9. Handwoven", ProductCategory.FURNISHING, 1000.45);
		p.getTags().add(new ProductTag(2, "wool"));
		productSeeds.put(2, p);
		repo.save(p);
		p = new Product(3, "Space Craft Lego", "580 pieces", ProductCategory.TOY, 776.00);
		p.getTags().add(new ProductTag(3, "plastic"));
		productSeeds.put(3, p);
		repo.save(p); 
		
		/*
		 * ptrepo.save(new ProductTag(3, "plastic")); ptrepo.save(new ProductTag(3,
		 * "blocks")); ptrepo.save(new ProductTag(3, "shapes")); ptrepo.save(new
		 * ProductTag(2, "wool")); ptrepo.save(new ProductTag(2, "royal"));
		 * ptrepo.save(new ProductTag(2, "living")); ptrepo.save(new ProductTag(1,
		 * "kitchen")); ptrepo.save(new ProductTag(1, "fresh")); ptrepo.save(new
		 * ProductTag(1, "carbon"));
		 */
	}
}
