package com.example.Web_Ecommerce.configCustomer;

import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceConfig implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer=customerRepository.findByUsername(username);
        if(!customer.isPresent()){
            throw new UsernameNotFoundException("Can not find the customer with username :"+username);
        }
        return new CustomerDetails(customer.get());
    }
}
