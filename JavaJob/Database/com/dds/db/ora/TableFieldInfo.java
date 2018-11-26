package com.dds.db.ora;

public class TableFieldInfo 
{
	private transient String fieldName;
	private transient String fieldType;
	private transient boolean pkFieldType;
	
	public String getFieldName() 
	{
		return fieldName;
	}
	public void setFieldName(String fieldName) 
	{
		this.fieldName = fieldName;
	}
	public String getFieldType() 
	{
		return fieldType;
	}
	public void setFieldType(String fieldType) 
	{
		this.fieldType = fieldType;
	}
	public boolean isPkFieldType() 
	{
		return pkFieldType;
	}
	public void setPkFieldType(boolean pkFieldType) 
	{
		this.pkFieldType = pkFieldType;
	}

}
