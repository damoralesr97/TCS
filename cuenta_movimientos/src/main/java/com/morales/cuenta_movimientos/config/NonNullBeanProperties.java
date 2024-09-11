package com.morales.cuenta_movimientos.config;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class NonNullBeanProperties extends BeanUtilsBean {

    @Override
    public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException,
            InvocationTargetException {
        if (value != null) {
            super.copyProperty(bean, name, value);
        }
    }

}
