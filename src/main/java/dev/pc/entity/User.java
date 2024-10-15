package dev.pc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
@NamedQuery(name = "User.findAll", query = "Select u from User u ")
public class User implements Serializable {

	static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@Column(name = "Username")
	String username;
	@Column(name = "Active")
	boolean active;
	@Column(name = "Code")
	String code;
	@Column(name = "Email")
	String email;
	@Column(name = "Phone")
	@Pattern(regexp = "^\\{8,10}$", message = " Số điện thoại từ 8-10 số")
	@NotEmpty(message = "Hãy nhập số điện thoại")
	String phone;

	@Column(name = "Fullname", columnDefinition = "varchar(255)")
	String fullname;

	@Column(name = "Password", columnDefinition = "varchar(255)")
	String password;

	@Column(name = "Images", columnDefinition = "longtext")
	String images;

	@Column(name = "Created_at", updatable = false)
	LocalDateTime createdAt;

	@Column(name = "Updated_at")
	LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	Role role;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}