package jp.co.info.ais.ops.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("constant")

public class AppConstant {

	public final static String APP_NAME = "自動補給電力使用 自動連絡システム";


}
