package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by barbarity on 11/06/15.
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1754246545689924205L;

    private Integer orderkey;
    private Integer custkey;
    private String orderstatus;
    private BigDecimal totalprice;
    private Date orderdate;

    public Integer getOrderkey() {
        return orderkey;
    }

    public void setOrderkey(Integer orderkey) {
        this.orderkey = orderkey;
    }

    public Integer getCustkey() {
        return custkey;
    }

    public void setCustkey(Integer custkey) {
        this.custkey = custkey;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public int hashCode() {
        return (orderkey != null)
                ? (this.getClass().hashCode() + orderkey.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Order) && (orderkey != null)
                ? orderkey.equals(((Order) obj).orderkey)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Order[orderkey=%d,custkey=%d,orderstatus=%s,totalprice=%f,orderdate=%s]",
                orderkey, custkey, orderstatus, totalprice, orderdate);
    }
}
