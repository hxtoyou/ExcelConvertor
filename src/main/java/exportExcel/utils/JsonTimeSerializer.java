/*
 * Copyright (C) 2010-2020 CCRISE.
 */
package exportExcel.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Timestamp类型的json格式序列化.
 * <p>
 * 将时间类型的数据序列化为"yyyy-MM-dd HH:mm:ss"格式，使用方法：
 * 
 * <pre>
 * &#064;JsonSerialize(using = JsonTimeSerializer.class)
 * public Timestamp getUploadTime() {
 * 	return uploadTime;
 * }
 * </pre>
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
public class JsonTimeSerializer extends JsonSerializer<Timestamp> {
	@Override
	public void serialize(final Timestamp value, final JsonGenerator jgen, final SerializerProvider provider)
			throws IOException {
		jgen.writeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value));
	}
}
