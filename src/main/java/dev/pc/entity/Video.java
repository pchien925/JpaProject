package dev.pc.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Video")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "VideoId")
	private String videoId;

	@Column(name = "Active")
	private int active;

	@Column(name = "Description", columnDefinition = "NVARCHAR(200)")
	private String description;

	@Column(name = "Poster")
	private String poster;

	@Column(name = "Title", columnDefinition = "NVARCHAR(50)")
	private String title;

	@Column(name = "Views")
	private int views;

	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;
}
