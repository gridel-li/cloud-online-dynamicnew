package com.sgcc.zj.core.aop.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public @interface PGAsync {
	 
	String name()  default "";    
}
