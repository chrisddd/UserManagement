package com.chris.usermanagement.util;

import java.lang.annotation.*;

/**
 * The type Permission 权限注解 用于检查权限 规定访问权限
 *
 * @author Administrator
 * @date 2018/1/8
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permission {
    /**
     * Value string [ ].
     *
     * @return the string [ ]
     */
    String[] value() default {};

    /**
     * Auth privileges string [ ].
     *
     * @return the string [ ]
     */
    String[] authPrivileges() default {};

    /**
     * Roles string [ ].
     *
     * @return the string [ ]
     */
    String[] roles() default {};
}

