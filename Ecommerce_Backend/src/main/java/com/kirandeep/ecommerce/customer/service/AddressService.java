package com.kirandeep.ecommerce.customer.service;

import com.kirandeep.ecommerce.authentication.entity.AppUser;
import com.kirandeep.ecommerce.customer.dto.AddressDto;
import com.kirandeep.ecommerce.customer.entity.Address;
import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.repository.AddressRepository;
import com.kirandeep.ecommerce.customer.repository.CustomerRepository;
import com.kirandeep.ecommerce.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public void saveAddress(AddressDto addressDto){

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
       AppUser user = (AppUser) authentication.getPrincipal();

     Customer customer =  customerRepository.findByEmail(user.getEmail()).orElseThrow(()->new ResourceNotFoundException("Customer Not Found with this email"+user.getEmail()));

     System.out.println(customer.getEmail());
     Address address = new Address();

        address.setPhoneNumber(addressDto.getPhoneNumber());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setCustomerName(addressDto.getCustomerName());
        address.setCustomer(customer);
     address.setCity(addressDto.getCity());
     address.setCountry(addressDto.getCountry());
     address.setPincode(addressDto.getPincode());
        address.setStreet(addressDto.getStreet());
     address.setState(addressDto.getState());
     address.setLandmark(addressDto.getLandmark());
     addressRepository.save(address);

     System.out.println("Address Saved");

    }

    public List<AddressDto> getAllCustomerAddresses(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();

        Customer customer =   customerRepository.findByEmail(user.getEmail()).orElseThrow(()->new ResourceNotFoundException("Customer Not Found with this email"+user.getEmail()));

       List<Address> addressList = addressRepository.findByCustomer(customer);

       List<AddressDto> addressDtoList = addressList.stream().map(address ->
               {
                   AddressDto addressDto = new AddressDto();
                   addressDto.setId(address.getId());
                   addressDto.setCity(address.getCity());
                   addressDto.setState(address.getState());
                   addressDto.setCountry(address.getCountry());
                   addressDto.setPincode(address.getPincode());
                   addressDto.setStreet(address.getStreet());
                   addressDto.setHouseNumber(address.getHouseNumber());
                   addressDto.setLandmark(address.getLandmark());
                   addressDto.setPhoneNumber(address.getPhoneNumber());
                   addressDto.setCustomerName(address.getCustomerName());
                   return addressDto;
               }
       ).toList();

       return addressDtoList;
    }
}
