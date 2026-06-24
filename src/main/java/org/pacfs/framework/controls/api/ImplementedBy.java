package org.pacfs.framework.controls.api;

import org.pacfs.framework.controls.internals.ControlBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ibi on 08/05/2026.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImplementedBy {

    Class<?> value() default ControlBase.class;
}
