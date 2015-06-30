package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by barbarity on 10/06/15.
 */
public class Part implements Serializable {

    private static final long serialVersionUID = 9085777939392860171L;

    private Integer partkey;
    private String name;
    private String type;
    private Integer size;
    private Integer container;
    private BigDecimal retailprice;

    public Integer getPartkey() {
        return partkey;
    }

    public void setPartkey(Integer partkey) {
        this.partkey = partkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getContainer() {
        return container;
    }

    public void setContainer(Integer container) {
        this.container = container;
    }

    public BigDecimal getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(BigDecimal retailprice) {
        this.retailprice = retailprice;
    }

    @Override
    public int hashCode() {
        return (partkey != null)
                ? (this.getClass().hashCode() + partkey.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Part) && (partkey != null)
                ? partkey.equals(((Part) obj).partkey)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Part[partkey=%d,name=%s,type=%s,size=%d,container=%d,retailprice=%f]",
                partkey, name, type, size, container, retailprice);
    }
}
