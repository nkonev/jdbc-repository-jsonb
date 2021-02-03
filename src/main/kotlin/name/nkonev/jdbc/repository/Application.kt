package name.nkonev.jdbc.repository

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@RestController
class AppController(private val customerRepository: CustomerRepository) {

	@GetMapping("/user")
	fun getCustomers(): Iterable<Customer> {
		return customerRepository.findAll()
	}

	@PostMapping("/user")
	fun postCustomer(@RequestBody customer: Customer) {
		customerRepository.save(customer)
	}
}