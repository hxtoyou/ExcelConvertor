/*
 * Copyright (C) 2010-2020 CCRISE.
 */
package exportExcel.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * Timestamp类型的json格式反序列化.
 * <p>
 * 接受"yyyy-MM-dd HH:mm:ss"格式或者"yyyy-MM-dd'T'HH:mm:ss"格式的数据，使用方法：
 * 
 * <pre>
 * &#064;JsonDeserialize(using = JsonTimeDeserializer.class)
 * public Timestamp getCreateTime() {
 * 	return createTime;
 * }
 * </pre>
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
public class JsonTimeDeserializer extends JsonDeserializer<Timestamp> {
	@Override
	public Timestamp deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
		if (jp.getText() == null || jp.getText().isEmpty()) {
			return null;
		} else {
			try {
				return new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jp.getText().replace('T', ' '))
						.getTime());
			} catch (ParseException e) {
				return null;
			}
		}
	}
}
