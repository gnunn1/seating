package com.redhat.demo.model;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.springframework.stereotype.Component;

@Component
@CsvRecord(separator = ",", skipFirstLine = true)
public class ReservationCsvRecord {

    @DataField(pos = 1, required = true, trim = true)
    private String name;

    @DataField(pos = 2, trim = true, defaultValue = " ")
    private String email;

    @DataField(pos = 3, trim = true, defaultValue = " ")
    private String seatName;

    @DataField(pos = 4, trim = true, defaultValue = " ")
    private String seatNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "[CSV RECORD:: Name: " + this.name +
                "; E-Mail: " + this.email +
                "; seatName: " + this.seatNo +
                "; seatNo: " + this.seatNo +
                "]";
    }
}