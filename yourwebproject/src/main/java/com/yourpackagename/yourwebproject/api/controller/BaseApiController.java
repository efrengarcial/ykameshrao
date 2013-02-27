package com.yourpackagename.yourwebproject.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourpackagename.framework.aop.ExceptionHandlerAdvice;
import com.yourpackagename.framework.controller.BaseController;
import com.yourpackagename.framework.data.BaseService;
import com.yourpackagename.framework.data.Entity;
import com.yourpackagename.framework.response.Response;
import com.yourpackagename.framework.validation.Validity;
import com.yourpackagename.yourwebproject.common.Key;
import com.yourpackagename.yourwebproject.common.Message;
import com.yourpackagename.yourwebproject.common.Props;

/**
 * Base API controller to provide basic functionality
 * to the extending classes to handle exceptions
 * and render responses
 *
 * @author: Y Kamesh Rao
 * @created: 3/17/12 9:44 PM
 * @company: &copy; 2012, Kaleidosoft Labs
 */
public class BaseApiController extends BaseController {
    protected @Autowired ExceptionHandlerAdvice exceptionHandlerAdvice;
    protected @Autowired HttpServletRequest request;
    protected @Autowired Key key;
    protected @Autowired Message msg;
    protected @Autowired Props props;
    private Logger log = LoggerFactory.getLogger(BaseApiController.class);


    protected Response serverResponse() {
        Response response = new Response();
        exceptionHandlerAdvice.setResponse(response);
        return response;
    }

    public Response validateAndSaveEntity(Entity entity, BaseService service) {
        Response response = serverResponse();
        try {
            Validity vsEntity = service.validate(entity);
            if (vsEntity.isValid()) {
                service.insert(entity);
                response.setResult(entity);
            } else {
                response.setError(key.vdnCode, vsEntity.errors());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setError(key.iseCode, e.getMessage());
        }

        return response;
    }


    @ExceptionHandler(Exception.class)
    public @ResponseBody Response exceptionHandler(Exception ex) {
        Response response = new Response();
        response.setError(500, ex.getMessage());
        log.error("Response: " + response.toString());
        return response;
    }
}
