package com.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min = 4,message="Minimum size of title must be 4")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message="Minimum size of title must be 10")
	private String categoryDescription;
  

}
