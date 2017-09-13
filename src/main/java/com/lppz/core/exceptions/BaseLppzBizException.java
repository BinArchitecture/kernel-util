package com.lppz.core.exceptions;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.lppz.bean.RollbackMsgs;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseLppzBizException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6231055223611382899L;

	private String key;
	private String keySource;
	private String module;
	
	private RollbackMsgs rollbackMsgs;
	
	public RollbackMsgs getRollbackMsgs() {
		return rollbackMsgs;
	}

	public void setRollbackMsgs(RollbackMsgs rollbackMsgs) {
		this.rollbackMsgs = rollbackMsgs;
	}
	@Deprecated
	public String getKey() {
		return key;
	}
	@Deprecated
	public void setKey(String key) {
		this.key = key;
	}
	@Deprecated
	public String getKeySource() {
		return keySource;
	}
	@Deprecated
	public void setKeySource(String keySource) {
		this.keySource = keySource;
	}
	@Deprecated
	public String getModule() {
		return module;
	}
	@Deprecated
	public void setModule(String module) {
		this.module = module;
	}
	@Deprecated
	public BaseLppzBizException() {
	}
	@Deprecated
	public BaseLppzBizException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
	@Deprecated
	public BaseLppzBizException(final String message, final String key, final String keySource, final String module, final Throwable cause)
	{
		super(message, cause);
		this.setKey(key);
		this.setKeySource(keySource);
		this.setModule(module);
	}
	@Deprecated
	public BaseLppzBizException(final String message)
	{
		super(message);
	}
	@Deprecated
	public BaseLppzBizException(final String message, final String key, final String keySource, final String module)
	{
		super(message);
		this.setKey(key);
		this.setKeySource(keySource);
		this.setModule(module);
	}
	@Deprecated
	public BaseLppzBizException(final Throwable cause)
	{
		super(cause);
	}
	@Deprecated
	public BaseLppzBizException(final String key, final String keySource, final String module, final Throwable cause)
	{
		super(cause);
		this.setKey(key);
		this.setKeySource(keySource);
		this.setModule(module);
	}
	
	public BaseLppzBizException(final RollbackMsgs rollbackMsgs, final Throwable cause)
	{
		super(cause);
		this.setRollbackMsgs(rollbackMsgs);
	}
	
	public BaseLppzBizException(final RollbackMsgs rollbackMsgs, final String message)
	{
		super(message);
		this.setRollbackMsgs(rollbackMsgs);
	}
	
	public BaseLppzBizException(final RollbackMsgs rollbackMsgs, final Throwable cause, final String message)
	{
		super(message, cause);
		this.setRollbackMsgs(rollbackMsgs);
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseLppzBizException [key=");
		builder.append(key);
		builder.append(", keySource=");
		builder.append(keySource);
		builder.append(", module=");
		builder.append(module);
		builder.append(", rollbackMsgs=");
		builder.append(rollbackMsgs);
		builder.append("]");
		return builder.toString();
	}
}
