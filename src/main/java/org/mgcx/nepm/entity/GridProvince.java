package org.mgcx.nepm.entity;



import java.io.Serializable;
import lombok.Data;
import  java.util.*;

/**
 * 
 * @TableName grid_province
 */
@Data
public class GridProvince implements Serializable {
    /**
     * 省编号
     */
    private Integer provinceId;

    /**
     * 省名字
     */
    private String provinceName;

    /**
     * 省简称
     */
    private String provinceAbbr;

    /**
     * 备注
     */
    private String remarks;



    private static final long serialVersionUID = 1L;

    /**
     * 以下是一对多的关系
     * @param that
     * @return
     */
    private List<GridCity> children;




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
        GridProvince other = (GridProvince) that;
        return (this.getProvinceId() == null ? other.getProvinceId() == null : this.getProvinceId().equals(other.getProvinceId()))
            && (this.getProvinceName() == null ? other.getProvinceName() == null : this.getProvinceName().equals(other.getProvinceName()))
            && (this.getProvinceAbbr() == null ? other.getProvinceAbbr() == null : this.getProvinceAbbr().equals(other.getProvinceAbbr()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());
        result = prime * result + ((getProvinceAbbr() == null) ? 0 : getProvinceAbbr().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", provinceAbbr=").append(provinceAbbr);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }



}