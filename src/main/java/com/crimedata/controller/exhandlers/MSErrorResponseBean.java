package com.crimedata.controller.exhandlers;

import  com.fasterxml.jackson.annotation.JsonView;


@JsonView
public class MSErrorResponseBean {

	public interface LimitedErrorView {};
	public interface FullErrorView extends LimitedErrorView{};
	
	@JsonView(FullErrorView.class)
    private String type;
	
	@JsonView(LimitedErrorView.class)
    private String title;
	@JsonView(LimitedErrorView.class)
    private String httpStatusCode;
	@JsonView(LimitedErrorView.class)
    private String messageDetail;
	@JsonView(LimitedErrorView.class)
    private String errorInstance;


    /**
     * 
     * @param type - example: "/errors/invalid-postcode-value"
     * @param title - example: "Invalid postcode"
     * @param httpStatusCode - example: 404
     * @param messageDetail - example: "Request failed due to invalid postcode"
     * @param errorInstance - example: "/postcoderequest/log/***"
     */
    public MSErrorResponseBean(String type, String title, String httpStatusCode, String messageDetail, String errorInstance) {
        super();
        this.messageDetail = messageDetail;
        this.type= type;
        this.title = title;
        this.httpStatusCode = httpStatusCode;
        this.errorInstance = errorInstance;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the httpStatusCode
	 */
	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 * @return the errorInstance
	 */
	public String getErrorInstance() {
		return errorInstance;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param httpStatusCode the httpStatusCode to set
	 */
	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * @param errorInstance the errorInstance to set
	 */
	public void setErrorInstance(String errorInstance) {
		this.errorInstance = errorInstance;
	}
}