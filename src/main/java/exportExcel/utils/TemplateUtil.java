package exportExcel.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * <p>
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
public abstract class TemplateUtil {
	private final static String FTLS_DIR = "/template";

	public static String loadTemplate(String templateName, Map<String, Object> datas) {
		String templateSource = "";
		try {
			Template template = getConfiguration().getTemplate(templateName, "UTF-8");
			template.setEncoding("UTF-8");
			StringWriter stringWriter = new StringWriter();
			template.process(datas, stringWriter);
			templateSource = stringWriter.toString();
			stringWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return templateSource;
	}

	/**
	 * 获取默认配置。
	 * 
	 * @return 模板配置
	 */
	private static Configuration getConfiguration() {
		Configuration cfg = new Configuration();
		cfg.setCacheStorage(new NullCacheStorage());
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setClassForTemplateLoading(TemplateUtil.class, FTLS_DIR);
		return cfg;
	}
}
