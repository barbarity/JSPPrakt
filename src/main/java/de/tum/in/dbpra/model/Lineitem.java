package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by barbarity on 29/06/15.
 */
public class Lineitem implements Serializable {

    private static final long serialVersionUID = -69407310381972615L;

    private Integer orderkey;
    private Integer partkey;
    private Integer suppkey;
    private Integer linenumber;
    private Integer quantity;
    private BigDecimal extendedprice;
    private BigDecimal discount;
    private BigDecimal tax;

    public Integer getOrderkey() {
        return orderkey;
    }

    public void setOrderkey(Integer orderkey) {
        this.orderkey = orderkey;
    }

    public Integer getPartkey() {
        return partkey;
    }

    public void setPartkey(Integer partkey) {
        this.partkey = partkey;
    }

    public Integer getSuppkey() {
        return suppkey;
    }

    public void setSuppkey(Integer suppkey) {
        this.suppkey = suppkey;
    }

    public Integer getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(Integer linenumber) {
        this.linenumber = linenumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getExtendedprice() {
        return extendedprice;
    }

    public void setExtendedprice(BigDecimal extendedprice) {
        this.extendedprice = extendedprice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public int hashCode() {
        return (orderkey != null && linenumber != null)
                ? (this.getClass().hashCode() + orderkey.hashCode() + linenumber.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Lineitem) && (orderkey != null)
                ? (orderkey.equals(((Lineitem) obj).orderkey) &&
                   linenumber.equals(((Lineitem) obj).linenumber))
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Lineitem[orderkey=%d,partkey=%d,suppkey=%s,linenumber=%d,quantity=%d,extendedprice=%f,discount=%f,tax=%f]",
                orderkey, partkey, suppkey, linenumber, quantity, extendedprice, discount, tax);
    }
}
