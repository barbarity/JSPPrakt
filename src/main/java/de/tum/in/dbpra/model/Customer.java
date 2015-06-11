package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by barbarity on 10/06/15.
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 7019598412226392529L;

    private Integer custkey;
    private String name;
    private String address;
    private Integer nationkey;
    private String phone;
    private String fax;
    private BigDecimal acctbal;

    public Integer getCustkey() {
        return custkey;
    }

    public void setCustkey(Integer custkey) {
        this.custkey = custkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNationkey() {
        return nationkey;
    }

    public void setNationkey(Integer nationkey) {
        this.nationkey = nationkey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public BigDecimal getAcctbal() {
        return acctbal;
    }

    public void setAcctbal(BigDecimal acctbal) {
        this.acctbal = acctbal;
    }

    @Override
    public int hashCode() {
        return (custkey != null)
                ? (this.getClass().hashCode() + custkey.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Customer) && (custkey != null)
                ? custkey.equals(((Customer) obj).custkey)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Customer[custkey=%d,name=%s,address=%s,nationkey=%d,phone=%s,fax=%s,acctbal=%f]",
                custkey, name, address, nationkey, phone, fax, acctbal);
    }
}
