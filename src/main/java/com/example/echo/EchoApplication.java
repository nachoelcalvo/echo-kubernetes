package com.example.echo;

import com.example.echo.dao.CustomerRepository;
import com.example.echo.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;

@Slf4j
@SpringBootApplication
public class EchoApplication implements CommandLineRunner {

	@Autowired
	private VaultTemplate vaultTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(EchoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		VaultResponseSupport read = vaultTemplate.read("secret/data/echo");
//		log.info("read response from vault" + read.toString());

//		Customer customer = new Customer("pepe", "garcia");
//		customerRepository.save(customer);

		Customer customerRetrieved = customerRepository.findByFirstName("pepe");

		log.info("Customer retrieved " + customerRetrieved.toString());
	}
}