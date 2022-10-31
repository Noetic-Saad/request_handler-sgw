package com.noeticworld.sgw.subscriber.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vendor_dcb_plans", schema = "public", catalog = "sgw")
public class VendorDcbPlansEntity {
    private long id;
    private Integer operatorId;
    private String dcbServiceId;
    private Timestamp cdate;
    private Integer isRecursive;
    private Integer pricePoint;
    private Integer mtMessageSettingId;
    private Integer subscriptionCycleId;
    private Integer vendorPlanId;
    private String taxAmount;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "operator_id")
    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    @Basic
    @Column(name = "dcb_service_id")
    public String getDcbServiceId() {
        return dcbServiceId;
    }

    public void setDcbServiceId(String dcbServiceId) {
        this.dcbServiceId = dcbServiceId;
    }

    @Basic
    @Column(name = "cdate")
    public Timestamp getCdate() {
        return cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }

    @Basic
    @Column(name = "is_recursive")
    public Integer getIsRecursive() {
        return isRecursive;
    }

    public void setIsRecursive(Integer isRecursive) {
        this.isRecursive = isRecursive;
    }

    @Basic
    @Column(name = "price_point")
    public Integer getPricePoint() {
        return pricePoint;
    }

    public void setPricePoint(Integer pricePoint) {
        this.pricePoint = pricePoint;
    }

    @Basic
    @Column(name = "mt_message_setting_id")
    public Integer getMtMessageSettingId() {
        return mtMessageSettingId;
    }

    public void setMtMessageSettingId(Integer mtMessageSettingId) {
        this.mtMessageSettingId = mtMessageSettingId;
    }

    @Basic
    @Column(name = "subscription_cycle_id")
    public Integer getSubscriptionCycleId() {
        return subscriptionCycleId;
    }

    public void setSubscriptionCycleId(Integer subscriptionCycleId) {
        this.subscriptionCycleId = subscriptionCycleId;
    }

    @Basic
    @Column(name = "vendor_plan_id")
    public Integer getVendorPlanId() {
        return vendorPlanId;
    }

    public void setVendorPlanId(Integer vendorPlanId) {
        this.vendorPlanId = vendorPlanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorDcbPlansEntity that = (VendorDcbPlansEntity) o;
        return id == that.id &&
                Objects.equals(operatorId, that.operatorId) &&
                Objects.equals(dcbServiceId, that.dcbServiceId) &&
                Objects.equals(cdate, that.cdate) &&
                Objects.equals(isRecursive, that.isRecursive) &&
                Objects.equals(pricePoint, that.pricePoint) &&
                Objects.equals(mtMessageSettingId, that.mtMessageSettingId) &&
                Objects.equals(subscriptionCycleId, that.subscriptionCycleId) &&
                Objects.equals(vendorPlanId, that.vendorPlanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operatorId, dcbServiceId, cdate, isRecursive, pricePoint, mtMessageSettingId, subscriptionCycleId, vendorPlanId);
    }

    @Basic
    @Column(name = "tax_amount")
    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }
}
