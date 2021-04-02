/*
 * %W% %E%
 *
 * Copyright (c) 2014, AISOFT and/or its affiliates. All rights reserved.
 * AISOFT Business Management System PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mega.dao.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.mega.dao.core.DaoEntity;

@Entity
@NamedQueries({
		@NamedQuery(name = "InventoryIO.searchQuick", query = "select o from InventoryIO o where o.deleted = 0 and o.companyId = :companyId and (:branchId = 0 or o.branchId = :branchId) and (o.status between :statusFrom and :statusTo) and (lower(o.itemCode) like :itemCode or lower(o.itemName) like :itemName or lower(o.brand) like :brand) and o.inventoryDate between :inventoryFrom and :inventoryTo order by o.inventoryDate desc") })
@Table(name = "inventory_io")
public class InventoryIO extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 8636169141934353739L;
	public static final Integer TYPE_IN = 1;
	public static final Integer TYPE_OUT = -1;

	public static final Integer STATUS_NEW = 1;
	public static final Integer STATUS_REJECT = 2;
	public static final Integer STATUS_WAITING = 3;
	public static final Integer STATUS_SUBMIT = 4;
	public static final Integer STATUS_STOCKER = 5;
	public static final Integer STATUS_APPROVED = 6;
	public static final Integer STATUS_DONE = 8;
	public static final Integer STATUS_CANCELED = -1;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "item_id")
	private Long itemId;

	@Column(name = "item_code")
	private String itemCode = "";

	@Column(name = "item_name")
	private String itemName = "";

	@Column(name = "item_code_origin")
	private String itemCodeOrigin = "";

	@Column(name = "item_name_origin")
	private String itemNameOrigin = "";

	@Column(name = "brand")
	private String brand = "";

	@Column(name = "lot")
	private String lot = "";

	@Column(name = "model")
	private String model = "";

	@Column(name = "serial")
	private String serial = "";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_date")
	private Date expireDate;

	@Column(name = "unit_name")
	private String unitName;

	@Column(name = "qty")
	private Double qty = 0D;

	@Column(name = "price")
	private Double price = 0D;

	@Column(name = "amount")
	private Double amount = 0D;

	@Column(name = "cost_price")
	private Double costPrice = 0D;

	@Column(name = "cost_amount")
	private Double costAmount = 0D;

	@Column(name = "exw")
	private Double exw = 0D;

	@Column(name = "exw_amount")
	private Double exwAmount = 0D;

	@Column(name = "currency")
	private String currency;

	@Column(name = "in_out_type")
	private Integer inOutType = TYPE_IN;

	@Column(name = "inventory_id")
	private Long inventoryId;

	@Column(name = "inventory_code")
	private String inventoryCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inventory_date")
	private Date inventoryDate;

	@Column(name = "content")
	private String content;

	@Column(name = "notes")
	private String notes;

	@Column(name = "inventory_type")
	private Integer inventoryType = 0;

	@Column(name = "partner_type")
	private String partnerType;

	@Column(name = "partner_id")
	private Long partnerId;

	@Column(name = "partner_name")
	private String partnerName;

	@Column(name = "warehouse_id")
	private Long warehouseId;

	@Column(name = "warehouse_code")
	private String warehouseCode;

	@Column(name = "warehouse_name")
	private String warehouseName;

	@Column(name = "warehouse_type_id")
	private Long warehouseTypeId;

	@Column(name = "warehouse_type_code")
	private String warehouseTypeCode;

	@Column(name = "warehouse_type_name")
	private String warehouseTypeName;

	@Column(name = "location_id")
	private Long locationId;

	@Column(name = "location_code")
	private String locationCode;

	@Column(name = "location_name")
	private String locationName;

	@Column(name = "req_inventory_id")
	private Long reqInventoryId;

	@Column(name = "req_inventory_code")
	private String reqInventoryCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "request_date")
	private Date requestDate;

	@Column(name = "requester_id")
	private Long requesterId;

	@Column(name = "requester_name")
	private String requesterName;

	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "internal_invoice_code")
	private String internalInvoiceCode;

	@Column(name = "invoice_code")
	private String invoiceCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "status")
	private Integer status;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "deleted")
	private Integer deleted = 0;

	public InventoryIO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCodeOrigin() {
		return itemCodeOrigin;
	}

	public void setItemCodeOrigin(String itemCodeOrigin) {
		this.itemCodeOrigin = itemCodeOrigin;
	}

	public String getItemNameOrigin() {
		return itemNameOrigin;
	}

	public void setItemNameOrigin(String itemNameOrigin) {
		this.itemNameOrigin = itemNameOrigin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(Double costAmount) {
		this.costAmount = costAmount;
	}

	public Double getExw() {
		return exw;
	}

	public void setExw(Double exw) {
		this.exw = exw;
	}

	public Double getExwAmount() {
		return exwAmount;
	}

	public void setExwAmount(Double exwAmount) {
		this.exwAmount = exwAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getInOutType() {
		return inOutType;
	}

	public void setInOutType(Integer inOutType) {
		this.inOutType = inOutType;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(Integer inventoryType) {
		this.inventoryType = inventoryType;
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Long getWarehouseTypeId() {
		return warehouseTypeId;
	}

	public void setWarehouseTypeId(Long warehouseTypeId) {
		this.warehouseTypeId = warehouseTypeId;
	}

	public String getWarehouseTypeCode() {
		return warehouseTypeCode;
	}

	public void setWarehouseTypeCode(String warehouseTypeCode) {
		this.warehouseTypeCode = warehouseTypeCode;
	}

	public String getWarehouseTypeName() {
		return warehouseTypeName;
	}

	public void setWarehouseTypeName(String warehouseTypeName) {
		this.warehouseTypeName = warehouseTypeName;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getReqInventoryId() {
		return reqInventoryId;
	}

	public void setReqInventoryId(Long reqInventoryId) {
		this.reqInventoryId = reqInventoryId;
	}

	public String getReqInventoryCode() {
		return reqInventoryCode;
	}

	public void setReqInventoryCode(String reqInventoryCode) {
		this.reqInventoryCode = reqInventoryCode;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInternalInvoiceCode() {
		return internalInvoiceCode;
	}

	public void setInternalInvoiceCode(String internalInvoiceCode) {
		this.internalInvoiceCode = internalInvoiceCode;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public boolean isDeleted() {
		return deleted > 0;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted ? 1 : 0;
	}

	@Transient
	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Transient
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public Long getDeletedId() {
		return null;
	}

	@Override
	public void setDeletedId(Long deletedId) {
	}

	@Override
	public Date getDeletedDate() {
		return null;
	}

	@Override
	public void setDeletedDate(Date deletedDate) {
	}

	@Override
	public Long getCreatedId() {
		return null;
	}

	@Override
	public void setCreatedId(Long createdId) {
	}

	@Override
	public Date getCreatedDate() {
		return null;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
	}

	@Override
	public Long getUpdatedId() {
		return null;
	}

	@Override
	public void setUpdatedId(Long updateId) {
	}

	@Override
	public Date getUpdatedDate() {
		return null;
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
	}

	@Override
	public Integer getVersion() {
		return null;
	}

	@Override
	public void setVersion(Integer version) {
	}

}
