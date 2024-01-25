package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.Model;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "todo_list")
public class TodoListMysqlEntity implements Model<UUID> {

    /**
     * Todo list entity identifier
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    /**
     * Todo list entity name
     */
    @Column(name = "name", length = 64)
    private String name;

    /**
     * Todo list entity description
     */
    @Column(name = "description", length = 512)
    private String description;

    /**
     * Todo list entity user identifier
     */
    @Column(name = "user_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    /**
     * Todo list entity username
     */
    @Column(name = "username")
    private String userName;

    /**
     * Todo list entity image in base64 format
     */
    @Column(name = "base64_image")
    private String base64Image;

    /**
     * Todo list entity creation date
     */
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    /**
     * Todo list entity modification date
     */
    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

    /**
     * Todo list entity items
     */
    @OneToMany(mappedBy = "todoList", orphanRemoval = true, cascade = CascadeType.ALL)
    @OrderBy("position")
    List<TodoListItemMysqlEntity> items = new ArrayList<>();

    /**
     * Returns todo list entity identifier
     * @return Todo list entity identifier
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * Sets todo list entity identifier
     * @param id Todo list entity identifier
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns todo list entity name
     * @return Todo list entity name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets todo list entity name
     * @param name Todo list entity name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns todo list entity description
     * @return Todo list entity description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets todo list entity description
     * @param description Todo list entity description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns todo list entity user identifier
     * @return Todo list entity user identifier
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets todo list entity user identifier
     * @param userId Todo list entity user identifier
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Returns todo list entity username
     * @return Todo list entity username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets todo list entity username
     * @param userName Todo list entity username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns todo list entity image in base64 format
     * @return Todo list entity image in base64 format
     */
    public String getBase64Image() {
        return base64Image;
    }

    /**
     * Sets todo list entity image in base64 format
     * @param base64Image Todo list entity image in base64 format
     */
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    /**
     * Returns todo list entity creation date
     * @return Todo list entity creation date
     */
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets todo list entity creation date
     * @param createdAt Todo list entity creation date
     */
    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns todo list entity modification date
     * @return Todo list entity modification date
     */
    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Sets todo list entity modification date
     * @param modifiedAt Todo list entity modification date
     */
    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    /**
     * Returns todo list entity items
     * @return Todo list entity items
     */
    public List<TodoListItemMysqlEntity> getItems() {
        return items;
    }

    /**
     * Sets todo list entity items
     * @param items Todo list entity items
     */
    public void setItems(List<TodoListItemMysqlEntity> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoListMysqlEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(userId, that.userId)
                && Objects.equals(userName, that.userName)
                && Objects.equals(base64Image, that.base64Image)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(modifiedAt, that.modifiedAt)
                && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, userId, userName, base64Image, createdAt, modifiedAt, items);
    }

    @Override
    public String toString() {
        return "TodoListMysqlEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", base64Image='" + base64Image + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", items=" + items +
                '}';
    }
}
