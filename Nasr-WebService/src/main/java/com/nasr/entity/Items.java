/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nasr.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "ITEMS")
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i.itemid,i.itemname,i.price FROM Items i"),
    @NamedQuery(name = "Items.findByItemid", query = "SELECT i.itemid,i.itemname,i.price FROM Items i WHERE i.itemid = :itemid"),
    @NamedQuery(name = "Items.findByItemname", query = "SELECT i FROM Items i WHERE i.itemname = :itemname"),
    @NamedQuery(name = "Items.getItemName", query = "SELECT i.itemname FROM Items i WHERE i.itemid = :itemid")})
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ITEMID")
    private Integer itemid;
    @Size(max = 50)
    @Column(name = "ITEMNAME")
    private String itemname;
    @Column(name = "PRICE")
    private Integer price;
    @OneToMany(mappedBy = "itemid")
    private List<Orders> ordersList;

    public Items() {
    }

    public Items(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return itemname;
    }

}
