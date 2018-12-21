package com.wp.week.model;
import java.io.Serializable;

/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class PlantInfoDto implements Serializable {

	/****/
	private Integer id;

	/**粮食编号**/
	private Integer plantNo;

	/**植物名称**/
	private String plantName;

	/**类别所属**/
	private Integer gsno;

	/**产地分布**/
	private String prodarea;

	/**生态习性**/
	private String ecolhabit;

	/****/
	private String imagePath;

	/**二维码**/
	private String dimenCode;


	private String username;
	/****/
	private java.util.Date createTime;

	/****/
	private java.util.Date updateTime;

	/**是否发布**/
	private String isState;

	/****/
	private String remark;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setPlantNo(Integer plantNo){
		this.plantNo = plantNo;
	}

	public Integer getPlantNo(){
		return this.plantNo;
	}

	public void setPlantName(String plantName){
		this.plantName = plantName;
	}

	public String getPlantName(){
		return this.plantName;
	}

	public void setGsno(Integer gsno){
		this.gsno = gsno;
	}

	public Integer getGsno(){
		return this.gsno;
	}

	public void setProdarea(String prodarea){
		this.prodarea = prodarea;
	}

	public String getProdarea(){
		return this.prodarea;
	}

	public void setEcolhabit(String ecolhabit){
		this.ecolhabit = ecolhabit;
	}

	public String getEcolhabit(){
		return this.ecolhabit;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	public String getImagePath(){
		return this.imagePath;
	}

	public void setDimenCode(String dimenCode){
		this.dimenCode = dimenCode;
	}

	public String getDimenCode(){
		return this.dimenCode;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setIsState(String isState){
		this.isState = isState;
	}

	public String getIsState(){
		return this.isState;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
