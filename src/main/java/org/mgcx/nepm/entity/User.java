package org.mgcx.nepm.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户信息表
 * @TableName users
 */
@Data
public class User implements Serializable {
    /**
     * 所有用户的id，uuid生成
     */
    private String userId;

    /**
     * 用户角色（0：管理员，1：网格员，2：公众）
     */
    private Integer role;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名（网格员和公众需要）
     */
    private String realName;

    /**
     * 生日（公众）
     */
    private String birthday;

    /**
     * 性别（1：男，0：女）（网格员和公众需要）
     */
    private Integer sex;

    /**
     * 联系电话（网格员和公众需要）
     */
    private String tel;

    /**
     * 网格区域：省编号
     */
    private Integer gridProvinceId;

    /**
     * 网格区域：市编号
     */
    private Integer gridCityId;
    /**
     * 网格区域：区编号
     */
    private Integer  gridDistrictId;

    /**
     * 网格员和管理员状态（网格员：0-可工作，1-工作中，2-休假，3-其他；管理员：0-启用，1-禁用）
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
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getGridProvinceId() == null ? other.getGridProvinceId() == null : this.getGridProvinceId().equals(other.getGridProvinceId()))
            && (this.getGridCityId() == null ? other.getGridCityId() == null : this.getGridCityId().equals(other.getGridCityId()))
              && (this.getGridDistrictId() == null ? other.getGridDistrictId() == null : this.getGridDistrictId().equals(other.getGridDistrictId()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getGridProvinceId() == null) ? 0 : getGridProvinceId().hashCode());
        result = prime * result + ((getGridCityId() == null) ? 0 : getGridCityId().hashCode());
        result = prime * result + ((getGridDistrictId() == null) ? 0 : getGridDistrictId().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", role=").append(role);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", realName=").append(realName);
        sb.append(", birthday=").append(birthday);
        sb.append(", sex=").append(sex);
        sb.append(", tel=").append(tel);
        sb.append(", gridProvinceId=").append(gridProvinceId);
        sb.append(", gridCityId=").append(gridCityId);
        sb.append(", gridDistrictId=").append(gridDistrictId);
        sb.append(", gridProvince=").append(gridProvince);
        sb.append(", gridCity=").append(gridCity);
        sb.append(", gridDistrict=").append(gridDistrict);
        sb.append(", state=").append(state);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}