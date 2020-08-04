package com.dollarsbank.application;

import com.dollarsbank.controller.DollarsBankController;

/**
 * an console application for basic transactions and data retrieval for customers of the bank.
 */
public class DollarsBankApplication 
{
    public static void main( String[] args )
    {
        new DollarsBankController().run();
    }
}