package jp.co.info.ais.ops.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("constant")
public class AppConstant {

	//定数
	public static final String APP_NAME = "自動補給電力使用 自動連絡システム";
	public static final String FILE_PATH = "/Users/kimjm/01.WORK_APP/audio";

}
