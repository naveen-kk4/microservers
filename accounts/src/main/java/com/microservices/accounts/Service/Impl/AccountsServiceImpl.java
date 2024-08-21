package com.microservices.accounts.Service.Impl;

import com.microservices.accounts.Constants.AccountsConstants;
import com.microservices.accounts.DTO.AccountsDto;
import com.microservices.accounts.DTO.CustomerDto;
import com.microservices.accounts.Entity.Accounts;
import com.microservices.accounts.Entity.Customer;
import com.microservices.accounts.Exception.CustomerAlreadyExistsException;
import com.microservices.accounts.Exception.ResourceNotFoundException;
import com.microservices.accounts.Mapper.AccountsMapper;
import com.microservices.accounts.Mapper.CustomerMapper;
import com.microservices.accounts.Repository.AccountsRepository;
import com.microservices.accounts.Repository.CustomerRepository;
import com.microservices.accounts.Service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    // here we need not Autowire these repos as there is  only a single all args constructor which accepts both these repos
    // so it follows Constructor based DI by default
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) throws CustomerAlreadyExistsException {


    Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if(customerOptional.isPresent())throw new CustomerAlreadyExistsException("Customer already registered with mobile number"+customerDto.getMobileNumber());

        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
    Customer savedCustomer = customerRepository.save(customer);
  accountsRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) throws ResourceNotFoundException {

      Customer customer =  customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts account =  accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account","account",customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer , new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(account, new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;





    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        // here in this method we are fetching account first and then getting customer from there because
        // account number is the primary key which cant be updated
        //likewise cutomer id also , that is why we fetch account first and then cus
        // tomer id from it

        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(Objects.isNull(accountsDto))return false;
        Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts","accountNumber",accountsDto.getAccountNumber().toString())
        );
        AccountsMapper.mapToAccounts(accountsDto,accounts);
        accounts = accountsRepository.save(accounts);
        Long customerId = accounts.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer","customerId",customerId.toString())
        );
        CustomerMapper.mapToCustomer(customerDto,customer);
        customerRepository.save(customer);
        return true;

    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());

        customerRepository.delete(customer);
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        return newAccount;
    }
}
