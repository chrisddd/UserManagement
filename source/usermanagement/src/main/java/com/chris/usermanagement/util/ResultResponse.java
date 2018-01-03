package com.chris.usermanagement.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * The type Result response.
 *
 * @author did
 * @date 2017年12月27日
 */
public class ResultResponse implements Serializable {


    private static final long serialVersionUID = 6737581828061540177L;
    @JsonInclude(content = Include.NON_NULL, value = Include.NON_NULL)
    private Boolean result = false;
    @JsonInclude(content = Include.NON_NULL, value = Include.NON_NULL)
    private String code;
    @JsonInclude(content = Include.NON_NULL, value = Include.NON_NULL)
    private String message;
    @JsonInclude(content = Include.NON_NULL, value = Include.NON_NULL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Object data;

    /**
     * Make error result response.
     *
     * @return result response
     */
    public ResultResponse makeError() {
        this.result = false;
        this.code = Error.GENERAL_ERROR;
        return this;
    }

    /**
     * Make success result response.
     *
     * @return result response
     */
    public ResultResponse makeSuccess() {
        this.result = true;
        this.code = Error.GENERAL_SUCCESS;
        return this;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     * @return the result
     */
    public ResultResponse setResult(Boolean result) {
        this.result = result;
        return this;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     * @return the code
     */
    public ResultResponse setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param messgae the messgae
     * @return the message
     */
    public ResultResponse setMessage(String messgae) {
        this.message = messgae;
        return this;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     * @return the data
     */
    public ResultResponse setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * Copy result response.
     *
     * @param rr the rr
     * @return the result response
     */
    public ResultResponse copy(ResultResponse rr) {
        this.result = rr.result;
        this.code = rr.code;
        this.message = rr.message;
        this.data = rr.data;
        return this;
    }

    /**
     * The type Errors.
     */
    public static class Error {

        /**
         * The constant GENERAL_ERROR.
         */
        public static final String GENERAL_ERROR = "40";

        /**
         * The constant GENERAL_SUCCESS.
         */
        public static final String GENERAL_SUCCESS = "00";

    }


}
