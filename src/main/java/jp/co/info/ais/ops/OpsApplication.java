package jp.co.info.ais.ops;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.info.ais.ops.filter.UserFilter;

@SpringBootApplication
@ServletComponentScan
public class OpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsApplication.class, args);
	}

	@Configuration
	public class WebConfig implements WebMvcConfigurer, WebMvcRegistrations {
		/**
		 * URLにフィルター連結
		 * @return FilterRegistrationBean<DashboardFilter>
		 */
		@Bean
		public FilterRegistrationBean<UserFilter> UserFilter() {
			FilterRegistrationBean<UserFilter> bean = new FilterRegistrationBean<>();
			bean.setFilter(new jp.co.info.ais.ops.filter.UserFilter());
			bean.setUrlPatterns(Arrays.asList("/event","/setting","/detail","/history","/master","/error"));
			return bean;
		}
	}
}
