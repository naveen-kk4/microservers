package com.microservices.loans.Constants;

public final class LoansConstants {
    private LoansConstants(){};
    public static final String  HOME_LOAN = "Home Loan";
    // this is a new update in java 8 where we can specify integer values with underscore for readability
    // upon conversion to byte code java will take care of converting it in proper integer form
     public static final int  NEW_LOAN_LIMIT = 1_00_000;
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Loan created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
     public static final String  STATUS_500 = "500";
     public static final String  MESSAGE_500 = "An error occurred. Please try again or contact Dev team";


}
