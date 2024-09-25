package com.korit.senicare.common.object;

import java.util.List;

import com.korit.senicare.repository.resultSet.GetCustomerResultSet;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class Customer {
    private Integer customerNumber;
    private String name;
    private String birth;
    private String location;
    private String chargerName;
    private String chargerId;

    private Customer(GetCustomerResultSet resultSet) {
        this.customerNumber = resultSet.getCustomerNumber();
        this.name = resultSet.getName();
        this.birth = resultSet.getBirth();
        this.location = resultSet.getLocation();
        this.chargerName = resultSet.getChargerName();
        this.chargerId = resultSet.getChargerId();
    }

    public static List<Customer> getList(List<GetCustomerResultSet> resultSets) {

        List<Customer> customers = new ArrayList<>();

        for (GetCustomerResultSet resultSet: resultSets) {
            Customer customer = new Customer(resultSet);
            customers.add(customer);
        }

        return customers;

    }
}