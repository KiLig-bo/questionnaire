package org.mgcx.nepm.entity;



import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 空气质量公众监督反馈表
 * @TableName aqi_feedback
 */
@Data
public class AqiFeedback implements Serializable {
    /**
     * 空气质量公众监督反馈信息编号
     */
    private Integer afId;

    /**
     * 反馈公众的用户id（即users表中的user_id）
     */
    private String spId;

    /**
     * 反馈信息所属省编号
     */
    private Integer provinceId;

    /**
     * 反馈信息所属市编号
     */
    private Integer cityId;

    /**
     * 反馈信息所属区编号
     */
    private Integer districtId;

    /**
     * 反馈信息所在区域详细地址
     */
    private String address;

    /**
     * 反馈信息描述
     */
    private String information;

    /**
     * 反馈者对空气质量的数级别的预估等级
     */
    private Integer estimatedGrade;

    /**
     * 反馈时间戳
     */
    private Date afTimestamp;

    /**
     * 指派网格员编号
     */
    private String gmId;

    /**
     * 指派时间戳
     */
    private Date assignTimestamp;

    /**
     * 信息状态（0：未指派，1：已指派，2：指派失败）
     */
    private Integer state;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 下面是一对一关系的数据
     */
    private GridCity gridCity;
    private GridDistrict gridDistrict;
    private GridProvince gridProvince;
    private Aqi aqi;
    private User user;

    //特殊使用

    private User gridManager;

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
        AqiFeedback other = (AqiFeedback) that;
        return (this.getAfId() == null ? other.getAfId() == null : this.getAfId().equals(other.getAfId()))
            && (this.getSpId() == null ? other.getSpId() == null : this.getSpId().equals(other.getSpId()))
            && (this.getProvinceId() == null ? other.getProvinceId() == null : this.getProvinceId().equals(other.getProvinceId()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getDistrictId() == null ? other.getDistrictId() == null : this.getDistrictId().equals(other.getDistrictId()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getInformation() == null ? other.getInformation() == null : this.getInformation().equals(other.getInformation()))
            && (this.getEstimatedGrade() == null ? other.getEstimatedGrade() == null : this.getEstimatedGrade().equals(other.getEstimatedGrade()))
            && (this.getAfTimestamp() == null ? other.getAfTimestamp() == null : this.getAfTimestamp().equals(other.getAfTimestamp()))
            && (this.getGmId() == null ? other.getGmId() == null : this.getGmId().equals(other.getGmId()))
            && (this.getAssignTimestamp() == null ? other.getAssignTimestamp() == null : this.getAssignTimestamp().equals(other.getAssignTimestamp()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAfId() == null) ? 0 : getAfId().hashCode());
        result = prime * result + ((getSpId() == null) ? 0 : getSpId().hashCode());
        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getDistrictId() == null) ? 0 : getDistrictId().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getInformation() == null) ? 0 : getInformation().hashCode());
        result = prime * result + ((getEstimatedGrade() == null) ? 0 : getEstimatedGrade().hashCode());
        result = prime * result + ((getAfTimestamp() == null) ? 0 : getAfTimestamp().hashCode());
        result = prime * result + ((getGmId() == null) ? 0 : getGmId().hashCode());
        result = prime * result + ((getAssignTimestamp() == null) ? 0 : getAssignTimestamp().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", afId=").append(afId);
        sb.append(", spId=").append(spId);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", districtId=").append(districtId);
        sb.append(", address=").append(address);
        sb.append(", information=").append(information);
        sb.append(", estimatedGrade=").append(estimatedGrade);
        sb.append(", afTimestamp=").append(afTimestamp);
        sb.append(", gmId=").append(gmId);
        sb.append(", assignTimestamp=").append(assignTimestamp);
        sb.append(", state=").append(state);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", gridCity=").append(gridCity);
        sb.append(", user=").append(user);

        sb.append("]");
        return sb.toString();
    }
}