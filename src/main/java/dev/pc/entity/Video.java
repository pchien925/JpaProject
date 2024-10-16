package dev.pc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Video")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Video_id")
	private String videoId;

	@Column(name = "Title")
	private String title;

	@Column(name = "Format")
	private String format;
	
	@Column(name = "Status")
	private Integer status;

	@Column(name = "Thumbnail_url")
	private String thumbnailUrl;

	@Column(name = "Created_at")
	private LocalDateTime createdAt;

	@Column(name = "Video_url")
	private String videoUrl;

	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}
