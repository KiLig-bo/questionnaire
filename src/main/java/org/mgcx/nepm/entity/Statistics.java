package org.mgcx.nepm.entity;



import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 空气质量检测统计表
 * @TableName statistics
 */
@Data
public class Statistics implements Serializable {
    /**
     * 统计信息编号
     */
    private Integer sId;

    /**
     * 所属省编号
     */
    private Integer provinceId;

    /**
     * 所属市编号
     */
    private Integer cityId;

    /**
     * 所属区编号
     */
    private Integer districtId;

    /**
     * 反馈信息所在区域详细地址
     */
    private String address;

    /**
     * 实测空气二氧化硫浓度值(单位:Hg/m3)
     */
    private Integer so2Value;

    /**
     * 空气二氧化硫指数级别
     */
    private Integer so2Level;

    /**
     * 实测空气一氧化碳浓度值(单位:Hg/m3)
     */
    private Integer coValue;

    /**
     * 空气一氧化碳指数级别
     */
    private Integer coLevel;

    /**
     * 实测空气悬浮颗粒物浓度值(单位:Hg/m3)
     */
    private Integer spmValue;

    /**
     * 空气PM2.5指数级别
     */
    private Integer spmLevel;

    /**
     * 实测空气质量指数级别
     */
    private Integer aqiId;

    /**
     * 确认时间戳
     */
    private Date confirmTimestamp;

    /**
     * 所属网格员编号
     */
    private String gmId;

    /**
     * 公众监督员id
     */
    private String fdId;

    /**
     * 反馈信息描述
     */
    private String information;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 以下是一对一的关联
     */
    private GridProvince province;
    private GridCity city;
    private GridDistrict district;
    private Aqi aqi;
    private User gridManager;
    private User feedbacker;



    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Statistics other = (Statistics) that;
        return (this.getSId() == null ? other.getSId() == null : this.getSId().equals(other.getSId()))
            && (this.getProvinceId() == null ? other.getProvinceId() == null : this.getProvinceId().equals(other.getProvinceId()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getDistrictId() == null ? other.getDistrictId() == null : this.getDistrictId().equals(other.getDistrictId()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getSo2Value() == null ? other.getSo2Value() == null : this.getSo2Value().equals(other.getSo2Value()))
            && (this.getSo2Level() == null ? other.getSo2Level() == null : this.getSo2Level().equals(other.getSo2Level()))
            && (this.getCoValue() == null ? other.getCoValue() == null : this.getCoValue().equals(other.getCoValue()))
            && (this.getCoLevel() == null ? other.getCoLevel() == null : this.getCoLevel().equals(other.getCoLevel()))
            && (this.getSpmValue() == null ? other.getSpmValue() == null : this.getSpmValue().equals(other.getSpmValue()))
            && (this.getSpmLevel() == null ? other.getSpmLevel() == null : this.getSpmLevel().equals(other.getSpmLevel()))
            && (this.getAqiId() == null ? other.getAqiId() == null : this.getAqiId().equals(other.getAqiId()))
            && (this.getConfirmTimestamp() == null ? other.getConfirmTimestamp() == null : this.getConfirmTimestamp().equals(other.getConfirmTimestamp()))
            && (this.getGmId() == null ? other.getGmId() == null : this.getGmId().equals(other.getGmId()))
            && (this.getFdId() == null ? other.getFdId() == null : this.getFdId().equals(other.getFdId()))
            && (this.getInformation() == null ? other.getInformation() == null : this.getInformation().equals(other.getInformation()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSId() == null) ? 0 : getSId().hashCode());
        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getDistrictId() == null) ? 0 : getDistrictId().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getSo2Value() == null) ? 0 : getSo2Value().hashCode());
        result = prime * result + ((getSo2Level() == null) ? 0 : getSo2Level().hashCode());
        result = prime * result + ((getCoValue() == null) ? 0 : getCoValue().hashCode());
        result = prime * result + ((getCoLevel() == null) ? 0 : getCoLevel().hashCode());
        result = prime * result + ((getSpmValue() == null) ? 0 : getSpmValue().hashCode());
        result = prime * result + ((getSpmLevel() == null) ? 0 : getSpmLevel().hashCode());
        result = prime * result + ((getAqiId() == null) ? 0 : getAqiId().hashCode());
        result = prime * result + ((getConfirmTimestamp() == null) ? 0 : getConfirmTimestamp().hashCode());
        result = prime * result + ((getGmId() == null) ? 0 : getGmId().hashCode());
        result = prime * result + ((getFdId() == null) ? 0 : getFdId().hashCode());
        result = prime * result + ((getInformation() == null) ? 0 : getInformation().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sId=").append(sId);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", districtId=").append(districtId);
        sb.append(", address=").append(address);
        sb.append(", so2Value=").append(so2Value);
        sb.append(", so2Level=").append(so2Level);
        sb.append(", coValue=").append(coValue);
        sb.append(", coLevel=").append(coLevel);
        sb.append(", spmValue=").append(spmValue);
        sb.append(", spmLevel=").append(spmLevel);
        sb.append(", aqiId=").append(aqiId);
        sb.append(", confirmTimestamp=").append(confirmTimestamp);
        sb.append(", gmId=").append(gmId);
        sb.append(", fdId=").append(fdId);
        sb.append(", information=").append(information);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}