package com.qa.TDL.utils;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.util.HashSet;
import java.util.Set;

import javax.validation.metadata.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SpringUtils {

	public static void mergeNotNull(Object src, Object trgt) {
		copyProperties(src, trgt, getNullPropName(src));
	}

	private static String[] getNullPropName(Object src) {
		final BeanWrapper wrappedSrcObj = new BeanWrapperImpl(src);
		Set<String> pName = new HashSet<>();
		for (PropertyDescriptor desc : wrappedSrcObj.getPropertyDescriptors()) {
			if (wrappedSrcObj.getPropertyValue(desc.getName()) == null)
				pName.add(desc.getName());

		}
		return pName.toArray(new String[pName.size()]);
		}
	}

}
