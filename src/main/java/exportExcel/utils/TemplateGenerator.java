package exportExcel.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import exportExcel.web.entity.HtmlStructure;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 * 
 */
public class TemplateGenerator {
	private static final String ENCODING = "UTF-8";

	/**
	 * 生成文件
	 * 
	 * @param path
	 * @param ftl
	 * @param configItems
	 */
	public static void generateFiles(final String path, final String ftl, HtmlStructure htmlStructure) {
		try {
			Template template = getConfiguration(path).getTemplate(ftl, ENCODING);
			FileUtils.forceMkdir(new File(path));
			Writer writer = new FileWriter(path + "/table.jsp");
			Map<String, HtmlStructure> root = new HashMap<String, HtmlStructure>();
			root.put("root", htmlStructure);
			template.process(root, writer);
			writer.flush();
		} catch (IOException e) {
			System.err.println("读取不到模板文件");
			e.printStackTrace();
		} catch (TemplateException e) {
			System.err.println("模板文件异常");
		}
	}

	/**
	 * 获取默认配置.
	 * 
	 * @return 模板配置
	 * @throws IOException
	 */
	private static Configuration getConfiguration(String path) throws IOException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(path));
		cfg.setCacheStorage(new NullCacheStorage());
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		return cfg;
	}
}
