package com.chris.usermanagement.util;


import com.chris.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
* The type Base controller.
*
* @author did
* @date 2017年12月27日
*/
public abstract class BaseController {

    @Value("${usermanagement.env}")
    private String env;
   /**
    * Gets login user.
    *
    * @return the login user
    */
   public User getLoginUser() {

       User loginUser = (User) RequestContextHolder.getRequestAttributes().getAttribute(Constants.LOGIN_SESSION_VALUE,
               RequestAttributes.SCOPE_SESSION);

       return loginUser;
   }

    /**
     * by default, will set into {@link ModelMap}
     *
     * @return
     */
    @ModelAttribute("currentUser")
    public User getUser() {

        return getLoginUser();
    }

   /**
    * Current user optional.
    *
    * @return optional
    */
   public Optional<User> currentUser() {
       return Optional.ofNullable(getLoginUser());
   }

   /**
    * Process result response.
    *
    * @param taker the taker
    * @return result response
    */
   public ResultResponse process(Taker<ResultResponse> taker) {

       ResultResponse rr = new ResultResponse();

       try {

           rr.setMessage("成功").makeSuccess();
           taker.apply(rr);

       } catch (Exception e) {

           rr.setMessage("失败").makeError().setData(null);


       }

       return rr;

   }

   /**
    * Process xlsm head.
    *
    * @param response the response
    * @param fileName the file name
    */
   protected void processXLSMHead(HttpServletResponse response, String fileName) {

       response.reset();
       int buffer = 4096;

       response.setContentType("application/xls;charset = utf-8");
       response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");

       response.setBufferSize(buffer);
   }

   /**
    * The interface Taker.
    *
    * @param <T> the type parameter
    */
   @FunctionalInterface
   public interface Taker<T> {

       /**
        * Apply.
        *
        * @param rr the rr
        */
       void apply(T rr);

   }

    @ModelAttribute("env")
    public String env() {
        return env;
    }
}
