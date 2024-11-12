package org.mgcx.nepm.entity;



import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName aqi
 */
@Data
public class Aqi implements Serializable {
    /**
     * 
     */
    private Integer aqiId;

    /**
     * 空气质量指数级别等级
     */
    private String level;

    /**
     * 空气质量指数级别评价
     */
    private String chineseExplain;

    /**
     * 空气质量指数级别描述
     */
    private String aqiExplain;

    /**
     * 表示颜色
     */
    private String color;

    /**
     * 健康影响情况
     */
    private String healthImpact;

    /**
     * 建议措施
     */
    private String takeSteps;

    /**
     * 级别最低so2浓度
     */
    private Integer so2Min;

    /**
     * 级别最高so2浓度
     */
    private Integer so2Max;

    /**
     * 级别最低co浓度
     */
    private Integer coMin;

    /**
     * 级别最高co浓度
     */
    private Integer coMax;

    /**
     * 级别最低PM2.5浓度
     */
    private Integer spmMin;

    /**
     * 级别最高PM2.5浓度
     */
    private Integer spmMax;

    /**
     * 级别最低AQI
     */
    private Integer iaqiMin;

    /**
     * 级别最高AQI
     */
    private Integer iaqiMax;

    /**
     * 
     */
    private String remarks;


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
        Aqi other = (Aqi) that;
        return (this.getAqiId() == null ? other.getAqiId() == null : this.getAqiId().equals(other.getAqiId()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getChineseExplain() == null ? other.getChineseExplain() == null : this.getChineseExplain().equals(other.getChineseExplain()))
            && (this.getAqiExplain() == null ? other.getAqiExplain() == null : this.getAqiExplain().equals(other.getAqiExplain()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getHealthImpact() == null ? other.getHealthImpact() == null : this.getHealthImpact().equals(other.getHealthImpact()))
            && (this.getTakeSteps() == null ? other.getTakeSteps() == null : this.getTakeSteps().equals(other.getTakeSteps()))
            && (this.getSo2Min() == null ? other.getSo2Min() == null : this.getSo2Min().equals(other.getSo2Min()))
            && (this.getSo2Max() == null ? other.getSo2Max() == null : this.getSo2Max().equals(other.getSo2Max()))
            && (this.getCoMin() == null ? other.getCoMin() == null : this.getCoMin().equals(other.getCoMin()))
            && (this.getCoMax() == null ? other.getCoMax() == null : this.getCoMax().equals(other.getCoMax()))
            && (this.getSpmMin() == null ? other.getSpmMin() == null : this.getSpmMin().equals(other.getSpmMin()))
            && (this.getSpmMax() == null ? other.getSpmMax() == null : this.getSpmMax().equals(other.getSpmMax()))
            && (this.getIaqiMin() == null ? other.getIaqiMin() == null : this.getIaqiMin().equals(other.getIaqiMin()))
            && (this.getIaqiMax() == null ? other.getIaqiMax() == null : this.getIaqiMax().equals(other.getIaqiMax()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAqiId() == null) ? 0 : getAqiId().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getChineseExplain() == null) ? 0 : getChineseExplain().hashCode());
        result = prime * result + ((getAqiExplain() == null) ? 0 : getAqiExplain().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getHealthImpact() == null) ? 0 : getHealthImpact().hashCode());
        result = prime * result + ((getTakeSteps() == null) ? 0 : getTakeSteps().hashCode());
        result = prime * result + ((getSo2Min() == null) ? 0 : getSo2Min().hashCode());
        result = prime * result + ((getSo2Max() == null) ? 0 : getSo2Max().hashCode());
        result = prime * result + ((getCoMin() == null) ? 0 : getCoMin().hashCode());
        result = prime * result + ((getCoMax() == null) ? 0 : getCoMax().hashCode());
        result = prime * result + ((getSpmMin() == null) ? 0 : getSpmMin().hashCode());
        result = prime * result + ((getSpmMax() == null) ? 0 : getSpmMax().hashCode());
        result = prime * result + ((getIaqiMin() == null) ? 0 : getIaqiMin().hashCode());
        result = prime * result + ((getIaqiMax() == null) ? 0 : getIaqiMax().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aqiId=").append(aqiId);
        sb.append(", level=").append(level);
        sb.append(", chineseExplain=").append(chineseExplain);
        sb.append(", aqiExplain=").append(aqiExplain);
        sb.append(", color=").append(color);
        sb.append(", healthImpact=").append(healthImpact);
        sb.append(", takeSteps=").append(takeSteps);
        sb.append(", so2Min=").append(so2Min);
        sb.append(", so2Max=").append(so2Max);
        sb.append(", coMin=").append(coMin);
        sb.append(", coMax=").append(coMax);
        sb.append(", spmMin=").append(spmMin);
        sb.append(", spmMax=").append(spmMax);
        sb.append(", iaqiMin=").append(iaqiMin);
        sb.append(", iaqiMax=").append(iaqiMax);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}