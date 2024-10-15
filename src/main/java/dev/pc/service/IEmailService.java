package dev.pc.service;

import dev.pc.dto.request.RegisterRequest;
import dev.pc.entity.User;

public interface IEmailService {
	boolean comfirmRegistered(RegisterRequest request);
	boolean getPassword(User user);
}
