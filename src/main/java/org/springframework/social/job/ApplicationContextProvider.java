package org.springframework.social.job;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * Provide Context For Quartz Job Run in background
 * @author Minh Anh Nguyen 
 */
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;

	public ApplicationContext getApplicationContext() {
		return context;
	}

	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		context = ac;
	}
}
