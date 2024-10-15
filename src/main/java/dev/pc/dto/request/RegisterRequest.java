package dev.pc.dto.request;

import dev.pc.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
	String username;
	String password;
	String email;
	String code;
	String phone;
	String fullname;
	String images;
	boolean active;
	Role role;
}