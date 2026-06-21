package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Category.java
// Author: Abongile Phandle (230670849)
// Date: June 2026

@Entity
@Table(name = "category")
public class Category {

    @Id
    private String categoryId;
    private String categoryName;
    private String description;

    protected Category() {
    }

    private Category(Builder builder) {
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.description = builder.description;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Category [ categoryId = " + categoryId + ", categoryName = " + categoryName + ", description = " + description + " ]";
    }

    public static class Builder {
        private String categoryId;
        private String categoryName;
        private String description;

        public Builder setCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }
        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder copy(Category category) {
            this.categoryId = category.categoryId;
            this.categoryName = category.categoryName;
            this.description = category.description;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
