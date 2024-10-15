package dev.pc.configs;

import java.util.Map;
import com.cloudinary.Cloudinary;
import dev.pc.utils.Constant;
import java.util.HashMap;

public class CloudinaryConfig {

	public static Cloudinary cloudinary() {
		Map<String, String> config = new HashMap<>();
		config.put("cloud_name", Constant.CLOUD_NAME);
		config.put("api_key", Constant.API_KEY);
		config.put("api_secret", Constant.API_SECRET);

		return new Cloudinary(config);
	}
}
